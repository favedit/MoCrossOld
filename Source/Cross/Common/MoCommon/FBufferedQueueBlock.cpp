#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造缓冲队列块。</T>
//============================================================
FBufferedQueueBlock::FBufferedQueueBlock(){
   _count = 0;
   _countMax = 0;
}

//============================================================
// <T>析构缓冲队列块。</T>
//
// @param capacity 数据容量
//============================================================
FBufferedQueueBlock::~FBufferedQueueBlock(){
}

//============================================================
// <T>测试数据是否能够写入。</T>
//
// @param length 数据长度
// @return 是否能够写入
//============================================================
TBool FBufferedQueueBlock::TestPushAble(TInt length){
   TInt free = _capacity - _position - sizeof(TInt32);
   return (length <= free);
}

//============================================================
// <T>测试数据是否能够读取。</T>
//
// @param length 数据长度
// @return 是否能够读取
//============================================================
TBool FBufferedQueueBlock::TestPopAble(TInt length){
   // 检查容量
   TInt position = _position + sizeof(TInt32);
   if(position > _length){
      return EFalse;
   }
   // 检查数据长度
   TInt32 dataLength = *(TInt32*)(_pMemory + _position);
   if(0 == dataLength){
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>将一个数据写入管道。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @return 压入是否成功
//============================================================
EStreamResult FBufferedQueueBlock::Push(TAnyC* pData, TInt length){
   // 检查参数
   MO_ASSERT(pData);
   // 检查长度
   if(0 == length){
      MO_WARN(TC("Write empty data. (data=0x%08X, length=%d)"), pData, length);
      return EStreamResult_WriteEmpty;
   }
   // 判断管道是否可以写入
   TInt free = _length - _position;
   TInt dataSize = sizeof(TInt32) + length;
   if(dataSize > free){
      MO_WARN(TC("Current queue is full. (data=0x%08X, capacity=%d, position=%d, free=%d, length=%d)"), pData, _capacity, _position, free, length);
      return EStreamResult_WriteFull;
   }
   // 写入数据
   FStreamBlockHead* pHead = (FStreamBlockHead*)(_pMemory + _position);
   pHead->length = length;
   MO_LIB_MEMORY_COPY(pHead->data, free, pData, length);
   // 设置位置
   _position += sizeof(TInt32) + length;
#ifdef _MO_DEBUG
   _count++;
   if(_count > _countMax){
      _countMax = _count;
   }
#endif // _DEBUG
   return EStreamResult_Success;
}

//============================================================
// <T>从管道内弹出一个完整的数据。</T>
// <P>先读出数据长度(4byte)，再读出数据内容。</P>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 读出数据的长度，为0表示没有读出有效数据，为-1表示读出缓冲不够
//============================================================
EStreamResult FBufferedQueueBlock::Pop(TAny* pData, TInt capacity, TInt* pLength){
   // 检查参数
   MO_ASSERT(pData);
   MO_ASSERT(capacity > 0);
   // 检查容量
   TInt position = _position + sizeof(TInt32);
   if(position >= _length){
      return EStreamResult_ReadEnd;
   }
   // 检查数据长度
   FStreamBlockHead* pHead = (FStreamBlockHead*)(_pMemory + _position);
   // 检查长度
   if(pHead->length > capacity){
      MO_WARN(TC("Current data capacity is not enouth. (data=0x%08X, capacity=%d, length=%d)"), pData, capacity, pHead->length);
      return EStreamResult_ReadCapacity;
   }
   // 检查有效
   if(position + pHead->length > _length){
      MO_WARN(TC("Current data is invalid. (data=0x%08X, capacity=%d, length=%d)"), pData, capacity, pHead->length);
      return EStreamResult_ReadEnd;
   }
   // 读取数据
   MO_LIB_MEMORY_COPY(pData, capacity, pHead->data, pHead->length);
   // 设置位置
#ifdef _MO_DEBUG
   _count--;
#endif // _DEBUG
   _position += sizeof(TInt32) + pHead->length;
   *pLength = pHead->length;
   return EStreamResult_Success;
}

//============================================================
// <T>跟踪信息。</T>
//
// @param pTrack 跟踪内容
//============================================================
void FBufferedQueueBlock::Track(MString* pTrack){
   TInt percent = _lengthMax * 100 / _capacity;
   pTrack->AppendFormat(TC("block=0x%08X, capacity=%d/%d(%d%), length=%d, position=%d, count=%d/%d"), this, _lengthMax, _capacity, percent, _length, _position, _count, _countMax);
}

MO_NAMESPACE_END

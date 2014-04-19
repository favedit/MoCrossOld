#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造缓冲数据流分块。</T>
//============================================================
FBufferedPipeBlock::FBufferedPipeBlock(){
}

//============================================================
// <T>析构缓冲数据流分块。</T>
//============================================================
FBufferedPipeBlock::~FBufferedPipeBlock(){
}

//============================================================
// <T>测试数据是否能够读取。</T>
//
// @param length 数据长度
// @return 是否能够读取
//============================================================
TBool FBufferedPipeBlock::TestReadAble(TInt length){
   TInt position = _position + length;
   return (position <= _length);
}

//============================================================
// <T>测试数据是否能够写入。</T>
//
// @param length 数据长度
// @return 是否能够写入
//============================================================
TBool FBufferedPipeBlock::TestWriteAble(TInt length){
   TInt free = _capacity - _position;
   return (length <= free);
}

//============================================================
// <T>从管道内测试一块数据。</T>
//
// @param pData 数据指针
// @param capacity 数据容量
// @param pLength 读取长度
// @return 处理结果
//============================================================
EStreamResult FBufferedPipeBlock::Peek(TAny* pData, TInt length, TInt *pLength){
   // 检查参数
   MO_ASSERT(pData);
   // 检查有效性
   if((0 == length) || (0 == _length) || (_position == _length)){
      *pLength = 0;
      return EStreamResult_Success;
   }
   // 检查数据长度
   TInt lengthPeek = length;
   if(_position + length > _length){
      lengthPeek = _length - _position;
   }
   // 读取数据
   MO_LIB_MEMORY_COPY(pData, length, _pMemory + _position, lengthPeek);
   // 设置位置
   *pLength = lengthPeek;
   return EStreamResult_Success;
}

//============================================================
// <T>从管道内读取一块数据。</T>
//
// @param pData 数据指针
// @param capacity 数据容量
// @param pLength 读取长度
// @return 处理结果
//============================================================
EStreamResult FBufferedPipeBlock::Read(TAny* pData, TInt length, TInt *pLength){
   // 检查参数
   MO_ASSERT(pData);
   // 检查容量
   if((0 == length) || (0 == _length) || (_position == _length)){
      *pLength = 0;
      return EStreamResult_Success;
   }
   // 检查数据长度
   TInt lengthRead = length;
   if(_position + length > _length){
      lengthRead = _length - _position;
   }
   // 读取数据
   MO_LIB_MEMORY_COPY(pData, length, _pMemory + _position, lengthRead);
   // 设置位置
   _position += lengthRead;
   _lengthTotalRead += lengthRead;
   *pLength = lengthRead;
   return EStreamResult_Success;
}

//============================================================
// <T>将一个数据写入管道。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @param pLength 写入长度
// @return 处理结果
//============================================================
EStreamResult FBufferedPipeBlock::Write(TAnyC* pData, TInt length, TInt *pLength){
   // 检查参数
   MO_ASSERT(pData);
   // 检查长度
   if((0 == length) || (_position == _length)){
      *pLength = 0;
      return EStreamResult_Success;
   }
   // 判断管道是否可以写入
   TInt lengthWrite = length;
   if(_position + length > _length){
      lengthWrite = _length - _position;
   }
   // 写入数据
   MO_LIB_MEMORY_COPY(_pMemory + _position, _length - _position, pData, lengthWrite);
   // 设置位置
   _position += lengthWrite;
   _lengthTotalWrite += lengthWrite;
   *pLength = lengthWrite;
   return EStreamResult_Success;
}

//============================================================
// <T>跟踪信息。</T>
//
// @param pTrack 跟踪内容
//============================================================
void FBufferedPipeBlock::Track(MString* pTrack){
   TInt percent = _lengthMax * 100 / _capacity;
   pTrack->AppendFormat(TC("block=0x%08X, capacity=%d/%d(%d%), total_read=%8d, total_write=%8d, length=%8d, position=%8d"),
         this, _lengthMax, _capacity, percent, _lengthTotalRead, _lengthTotalWrite, _length, _position);
}

MO_NAMESPACE_END

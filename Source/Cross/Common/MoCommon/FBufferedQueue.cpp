#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造缓冲队列。</T>
//============================================================
FBufferedQueue::FBufferedQueue(){
   _lockCd = EStreamLock_Lock;
   _countPush = 0;
   _lengthPush = 0;
   _countPop = 0;
   _lengthPop = 0;
   _pPool = MO_CREATE(FStreamBlockPool);
   MO_CLEAR(_pReadBlock);
   _pReadBlocks = MO_CREATE(FBufferedQueueBlockList);
   MO_CLEAR(_pWriteBlock);
   _pWriteBlocks = MO_CREATE(FBufferedQueueBlockList);
}

//============================================================
// <T>析构缓冲队列。</T>
//============================================================
FBufferedQueue::~FBufferedQueue(){
   MO_DELETE(_pReadBlocks);
   MO_DELETE(_pWriteBlocks);
   MO_DELETE(_pPool);
}

//============================================================
// <T>将一个完整信息写入管道。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @return 压入是否成功
//============================================================
TBool FBufferedQueue::InnerPush(TAnyC* pData, TInt length){
   TBool result = EFalse;
   // 测试写满
   if(NULL != _pWriteBlock){
      if(!_pWriteBlock->TestPushAble(length)){
         // 读取处理
         _pWriteBlock->FlipForRead();
         // 放入读取集合
         _pReadBlocks->Push(_pWriteBlock);
         _pWriteBlock = NULL;
      }
   }
   // 收集内存
   if(NULL == _pWriteBlock){
      _pWriteBlock = (FBufferedQueueBlock*)_pPool->Alloc();
   }
   // 写入数据
   if(NULL != _pWriteBlock){
      EStreamResult resultCd = _pWriteBlock->Push(pData, length);
      if(EStreamResult_Success == resultCd){
         _countPush++;
         _lengthPush += length;
         result = ETrue;
         //MO_DEBUG("Push data success. (length=%d, write=%d)", _length, length);
      }else{
         MO_ERROR(TC("Push data failue. (length=%d)"), result);
      }
   }
   return result;
}

//============================================================
// <T>从管道内弹出一个完整的数据。</T>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 读出数据的长度，为0表示没有读出有效数据，为-1表示读出缓冲不够
//============================================================
TInt FBufferedQueue::InnerPop(TAny* pData, TInt capacity){
   TInt result = 0;
   // 获得读取块
   if(NULL == _pReadBlock){
      if(!_pReadBlocks->IsEmpty()){
         _pReadBlock = _pReadBlocks->Shift();
      }
   }
   // 将写入缓冲交换过来
   if(NULL == _pReadBlock){
      if(NULL != _pWriteBlock){
         if(!_pWriteBlock->IsEmpty()){
            _pReadBlock = _pWriteBlock;
            _pReadBlock->FlipForRead();
            MO_CLEAR(_pWriteBlock);
         }
      }
   }
   // 测试是否能够读取数据
   if(NULL != _pReadBlock){
      TInt length;
      EStreamResult resultCd = _pReadBlock->Pop(pData, capacity, &length);
      if(EStreamResult_Success == resultCd){
         _countPop++;
         _lengthPop += length;
         result = length;
         // MO_DEBUG("Read length. (length=%d, read=%d)", _length, length);
      }else if(EStreamResult_ReadEnd == resultCd){
         _pPool->Free(_pReadBlock);
         _pReadBlock = NULL;
      }else if(EStreamResult_ReadCapacity == resultCd){
         result = -1;
      }
   }
   return result;
}

//============================================================
// <T>获得总数。</T>
//
// @return 总数
//============================================================
TInt FBufferedQueue::Count(){
   TInt count = 0;
   _locker.Enter();
   count = (TInt)(_countPush - _countPop);
   _locker.Leave();
   return count;
}

//============================================================
// <T>测试是否能写入指定长度的数据。</T>
//
// @param length 数据长度
// @return 压入是否成功
//============================================================
TBool FBufferedQueue::TestPushAble(TInt length){
   return ETrue;
}

//============================================================
// <T>测试是否能从管道内弹出一个数据。</T>
//
// @return 读出数据的长度，为0表示没有读出有效数据，为-1表示读出缓冲不够
//============================================================
TBool FBufferedQueue::TestPopAble(){
   return EFalse;
}

//============================================================
// <T>将一个完整信息写入管道。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @return 压入是否成功
//============================================================
TBool FBufferedQueue::Push(TAnyC* pData, TInt length){
   TBool result = EFalse;
   switch(_lockCd){
      case EStreamLock_Unlock:{
         result = InnerPush(pData, length);
         break;
      }
      case EStreamLock_Lock:{
         _locker.Enter();
         result = InnerPush(pData, length);
         _locker.Leave();
         break;
      }
      default:{
         MO_FATAL(TC("Unknown lock mode."));
         break;
      }
   }
   return result;
}

//============================================================
// <T>从管道内弹出一个完整的数据。</T>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 读出数据的长度，为0表示没有读出有效数据，为-1表示读出缓冲不够
//============================================================
TInt FBufferedQueue::Pop(TAny* pData, TInt capacity){
   TInt result = 0;
   switch(_lockCd){
      case EStreamLock_Unlock:{
         result = InnerPop(pData, capacity);
         break;
      }
      case EStreamLock_Lock:{
         _locker.Enter();
         result = InnerPop(pData, capacity);
         _locker.Leave();
         break;
      }
      default:{
         MO_FATAL(TC("Unknown lock mode."));
         break;
      }
   }
   return result;
}

//============================================================
// <T>阻塞式将一个完整信息写入管道。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @return 压入是否成功
//============================================================
TBool FBufferedQueue::BlockedPush(TAnyC* pData, TInt length){
   TBool result = EFalse;
   while(ETrue){
      result = Push(pData, length);
      // 检测结果
      if(result){
         break;
      }else{
         MO_LIB_MICRO_SLEEP(0);
      }
   }
   return result;
}

//============================================================
// <T>阻塞式从管道内弹出一个完整的数据。</T>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 读出数据的长度，为0表示没有读出有效数据，为-1表示读出缓冲不够
//============================================================
TInt FBufferedQueue::BlockedPop(TAny* pData, TInt capacity){
   TBool result = EFalse;
   while(ETrue){
      result = Pop(pData, capacity);
      // 检测结果
      if(0 == result){
         MO_LIB_MICRO_SLEEP(0);
      }else{
         break;
      }
   }
   return result;
}

//============================================================
// <T>获得队列信息。</T>
//
// @param pInfo 队列信息
//============================================================
TBool FBufferedQueue::FetchInfo(SQueueInfo* pInfo){
   // 检查参数
   if(NULL == pInfo){
      return EFalse;
   }
   // 设置变量
   pInfo->count = (TInt32)(_countPush - _countPop);
   pInfo->length = (TInt32)(_lengthPush - _lengthPop);
   pInfo->countPush = _countPush;
   pInfo->lengthPush = _lengthPush;
   pInfo->countPop = _countPop;
   pInfo->lengthPop = _lengthPop;
   return ETrue;
}

//============================================================
// <T>输出内部信息。</T>
//============================================================
void FBufferedQueue::Dump(){
   TInt count = (TInt)(_countPush - _countPop);
   TInt length = (TInt)(_lengthPush - _lengthPop);
   TFsDump dump;
   dump.AppendFormat(TC("Buffered queue dump. (capacity=%d, count=%d, length=%d)\n"), _pPool->Capacity(), count, length);
   _pPool->Track(&dump);
   MO_INFO((TCharC*)dump);
}

MO_NAMESPACE_END

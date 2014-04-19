#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造缓冲队列。</T>
//============================================================
FBufferedPipe::FBufferedPipe(){
   _lockCd = EStreamLock_Lock;
   _countPush = 0;
   _lengthPush = 0;
   _countPop = 0;
   _lengthPop = 0;
   _pPool = MO_CREATE(FStreamBlockPool);
   MO_CLEAR(_pReadBlock);
   _pReadBlocks = MO_CREATE(FBufferedPipeBlockList);
   MO_CLEAR(_pWriteBlock);
   _pWriteBlocks = MO_CREATE(FBufferedPipeBlockList);
}

//============================================================
// <T>析构缓冲队列。</T>
//============================================================
FBufferedPipe::~FBufferedPipe(){
   MO_DELETE(_pPool);
   MO_DELETE(_pReadBlocks);
   MO_DELETE(_pWriteBlocks);
}

//============================================================
// <T>从管道内检索一块数据。</T>
// <P>可能会引起交换区，尽量检索较少数据。</P>
//
// @param pData 数据指针
// @param capacity 数据长度
// @param pLength 检出长度
// @return 处理结果
//============================================================
EStreamResult FBufferedPipe::InnerPeek(TAny* pData, TInt length, TInt* pLength){
   TByte* pReader = (TByte*)pData;
   TInt remain = length;
   // 读取首块数据
   if(remain > 0){
      if(NULL != _pReadBlock){
         TInt lengthPeek = 0;
         if(EStreamResult_Success == _pReadBlock->Peek(pReader, remain, &lengthPeek)){
            pReader += lengthPeek;
            remain -= lengthPeek;
         }
      }
   }
   // 读取集合
   if(remain > 0){
      // 处理链表
      if(!_pReadBlocks->IsEmpty()){
         TListIteratorC<FBufferedPipeBlock*> iterator = _pReadBlocks->IteratorC();
         while((remain > 0) && iterator.Next()){
            FBufferedPipeBlock* pBlock = *iterator;
            TInt lengthRead = 0;
            if(EStreamResult_Success == pBlock->Peek(pReader, remain, &lengthRead)){
               pReader += lengthRead;
               remain -= lengthRead;
            }
         }
      }
   }
   // 读取写入区
   if(remain > 0){
      FBufferedPipeBlock* pReadBlock = NULL;
      // 交换写入区
      WriteLock();
      if(NULL != _pWriteBlock){
         if(!_pWriteBlock->IsEmpty()){
            _pWriteBlock->FlipForRead();
            pReadBlock = _pWriteBlock;
            _pReadBlocks->Push(_pWriteBlock);
            MO_CLEAR(_pWriteBlock);
         }
      }
      WriteUnlock();
      // 读取数据块
      if(NULL != pReadBlock){
         TInt lengthPeek = 0;
         if(EStreamResult_Success == pReadBlock->Peek(pReader, remain, &lengthPeek)){
            pReader += lengthPeek;
            remain -= lengthPeek;
         }
      }
   }
   *pLength = length - remain;
   return EStreamResult_Success;
}

//============================================================
// <T>从管道内弹出一个完整的数据。</T>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 读出数据的长度，为0表示没有读出有效数据，为-1表示读出缓冲不够
//============================================================
EStreamResult FBufferedPipe::InnerRead(TAny* pData, TInt length, TInt* pLength){
   TByte* pReader = (TByte*)pData;
   TInt remain = length;
   while(remain > 0){
      // 获得读取块
      if(NULL == _pReadBlock){
         if(!_pReadBlocks->IsEmpty()){
            _pReadBlock = _pReadBlocks->Shift();
         }
      }
      // 交换写入区
      if(NULL == _pReadBlock){
         WriteLock();
         if(NULL != _pWriteBlock){
            if(!_pWriteBlock->IsEmpty()){
               _pWriteBlock->FlipForRead();
               _pReadBlock = _pWriteBlock;
               MO_CLEAR(_pWriteBlock);
            }
         }
         WriteUnlock();
      }
      // 测试是否能够读取数据
      if(NULL != _pReadBlock){
         TInt lengthRead = 0;
         if(EStreamResult_Success == _pReadBlock->Read(pReader, remain, &lengthRead)){
            pReader += lengthRead;
            remain -= lengthRead;
            _countPop++;
            _lengthPop += lengthRead;
         }
         // 测试是否已经读取完成
         if(_pReadBlock->TestReadEnd()){
            _pPool->Free(_pReadBlock);
            _pReadBlock = NULL;
         }
      }else{
         break;
      }
   }
   *pLength = length - remain;
   return EStreamResult_Success;
}

//============================================================
// <T>将一个完整信息写入管道。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @param pLength 写入长度
// @return 压入是否成功
//============================================================
EStreamResult FBufferedPipe::InnerWrite(TAnyC* pData, TInt length, TInt* pLength){
   // 写入全部数据
   TByte* pWriter = (TByte*)pData;
   TInt remain = length;
   while(remain > 0){
      // 收集内存
      if(NULL == _pWriteBlock){
         _pWriteBlock = (FBufferedPipeBlock*)_pPool->Alloc();
         if(NULL == _pWriteBlock){
            return EStreamResult_WriteCapacity;
         }
      }
      // 测试写入
      if(NULL != _pWriteBlock){
         // 写入数据
         TInt lengthWrite = 0;
         if(EStreamResult_Success == _pWriteBlock->Write(pWriter, remain, &lengthWrite)){
            pWriter += lengthWrite;
            remain -= lengthWrite;
            _countPush++;
            _lengthPush += lengthWrite;
         }
         // 测试结束
         if(_pWriteBlock->TestWriteEnd()){
            // 读取处理
            _pWriteBlock->FlipForRead();
            // 放入读取集合
            ReadLock();
            _pReadBlocks->Push(_pWriteBlock);
            _pWriteBlock = NULL;
            ReadUnlock();
         }
      }else{
         break;
      }
   }
   *pLength = length - remain;
   return EStreamResult_Success;
}

//============================================================
// <T>获得管道的长度。</T>
//
// @return 数据长度
//============================================================
TInt FBufferedPipe::Length(){
   return _lengthPush - _lengthPop;
}

//============================================================
// <T>测试是否能写入指定长度的数据。</T>
//
// @param length 数据长度
// @return 压入是否成功
//============================================================
TBool FBufferedPipe::TestWriteAble(TInt length){
   return ETrue;
}

//============================================================
// <T>测试是否能从管道内弹出一个数据。</T>
//
// @return 读出数据的长度，为0表示没有读出有效数据，为-1表示读出缓冲不够
//============================================================
TBool FBufferedPipe::TestReadAble(TInt length){
   return EFalse;
}

//============================================================
// <T>从管道内获得一个数据。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @param pLength 读取长度
// @return 处理结果
//============================================================
EStreamResult FBufferedPipe::Peek(TAny* pData, TInt length, TInt* pLength){
   EStreamResult resultCd = EStreamResult_Unknown;
   switch(_lockCd){
      case EStreamLock_Unlock:{
         resultCd = InnerPeek(pData, length, pLength);
         break;
      }
      case EStreamLock_Lock:{
         ReadLock();
         resultCd = InnerPeek(pData, length, pLength);
         ReadUnlock();
         break;
      }
      default:{
         MO_FATAL(TC("Unknown lock mode."));
         break;
      }
   }
   return resultCd;
}

//============================================================
// <T>从管道内读取一个数据。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @param pLength 读取长度
// @return 处理结果
//============================================================
EStreamResult FBufferedPipe::Read(TAny* pData, TInt length, TInt* pLength){
   EStreamResult resultCd = EStreamResult_Unknown;
   switch(_lockCd){
      case EStreamLock_Unlock:{
         resultCd = InnerRead(pData, length, pLength);
         break;
      }
      case EStreamLock_Lock:{
         ReadLock();
         resultCd = InnerRead(pData, length, pLength);
         ReadUnlock();
         break;
      }
      default:{
         MO_FATAL(TC("Unknown lock mode."));
         break;
      }
   }
   return resultCd;
}

//============================================================
// <T>向管道内写入一个数据。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @param pLength 读取长度
// @return 处理结果
//============================================================
EStreamResult FBufferedPipe::Write(TAnyC* pData, TInt length, TInt* pLength){
   EStreamResult resultCd = EStreamResult_Unknown;
   switch(_lockCd){
      case EStreamLock_Unlock:{
         resultCd = InnerWrite(pData, length, pLength);
         break;
      }
      case EStreamLock_Lock:{
         WriteLock();
         resultCd = InnerWrite(pData, length, pLength);
         WriteUnlock();
         break;
      }
      default:{
         MO_FATAL(TC("Unknown lock mode."));
         break;
      }
   }
   return resultCd;
}

//============================================================
// <T>重置数据。</T>
//
// @return 处理结果
//============================================================
TBool FBufferedPipe::Reset(){
   _countPush = 0;
   _lengthPush = 0;
   _countPop = 0;
   _lengthPop = 0;
   _pPool->Reset();
   MO_CLEAR(_pReadBlock);
   _pReadBlocks->Clear();
   MO_CLEAR(_pWriteBlock);
   _pWriteBlocks->Clear();
   return ETrue;
}

//============================================================
// <T>输出内部信息。</T>
//============================================================
void FBufferedPipe::Dump(){
   TInt count = _countPush - _countPop;
   TInt length = _lengthPush - _lengthPop;
   TFsDump dump;
   dump.AppendFormat(TC("Buffered queue dump. (capacity=%d, count=%d, length=%d)\n"), _pPool->Capacity(), count, length);
   _pPool->Track(&dump);
   MO_INFO((TCharC*)dump);
}

MO_NAMESPACE_END

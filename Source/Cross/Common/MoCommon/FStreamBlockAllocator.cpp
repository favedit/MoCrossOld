#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造缓冲队列块。</T>
//============================================================
FStreamBlockAllocator::FStreamBlockAllocator(){
   _blockCapacity = 0;
   _blockLimit = 4;
}

//============================================================
// <T>析构缓冲队列块。</T>
//
// @param capacity 数据容量
//============================================================
FStreamBlockAllocator::~FStreamBlockAllocator(){
   // 删除所有使用对象
   if(!_pUsingItems->IsEmpty()){
      TListIteratorC<FStreamBlock*> iterator = _pUsingItems->IteratorC();
      while(iterator.Next()){
         Delete(*iterator);
      }
      _pUsingItems->Clear();
   }
   // 删除所有自由对象
   if(!_pFreeItems->IsEmpty()){
      TListIteratorC<FStreamBlock*> iterator = _pFreeItems->IteratorC();
      while(iterator.Next()){
         Delete(*iterator);
      }
      _pFreeItems->Clear();
   }
}

//============================================================
// <T>删除管道块。</T>
//
// @param pBlock 管道块
//============================================================
void FStreamBlockAllocator::Delete(FStreamBlock* pBlock){
   MO_DELETE(pBlock);
}

//============================================================
// <T>收集一个管道块。</T>
//
// @return 管道块
//============================================================
FStreamBlock* FStreamBlockAllocator::Alloc(){
   FStreamBlock* pBlock = NULL;
   //............................................................
   _locker.Enter();
   // 检查是否有空闲对象
   if(_pFreeItems->IsEmpty()){
      FStreamBlock* pAlloc = Create();
      pAlloc->ForceCapacity(_blockCapacity);
      Store(pAlloc);
   }
   // 收集一个分块
   pBlock = AllocFirst();
   _locker.Leave();
   //............................................................
   // 设置为写操作
   pBlock->FlipForWrite();
   return pBlock;
}

//============================================================
// <T>释放一个管道块。</T>
//
// @param pBlock 管道块
//============================================================
void FStreamBlockAllocator::Free(FStreamBlock* pBlock){
   MO_ASSERT(pBlock);
   FStreamBlock* pFree = NULL;
   //............................................................
   _locker.Enter();
   // 释放分块
   FreeLast(pBlock);
   // 检查容量
   TInt freeCount = _pFreeItems->Count();
   if(freeCount > 0){
      if(freeCount > _blockLimit){
         pFree = _pFreeItems->Shift();
      }
   }
   _locker.Leave();
   //............................................................
   // 删除对象
   if(NULL != pFree){
      Delete(pFree);
   }
}

//============================================================
// <T>跟踪信息。</T>
//
// @param pTrack 跟踪内容
//============================================================
void FStreamBlockAllocator::Track(MString* pTrack){
   // 计算信息
   //TFsDump dump;
   //TInt index = 0;
   //............................................................
   TInt usingCount = _pUsingItems->Count();
   TInt64 lengthRead = 0;
   TInt64 lengthWrite = 0;
   TListIteratorC<FStreamBlock*> iteratorUsing = _pUsingItems->IteratorC();
   while(iteratorUsing.Next()){
      FStreamBlock* pBlock = *iteratorUsing;
      lengthRead += pBlock->LengthTotalRead();
      lengthWrite += pBlock->LengthTotalWrite();
      //if(index > 0){
      //   dump.Append("\n");
      //}
      //dump.AppendFormat("   %4d - U:", index);
      //pBlock->Track(&dump);
      //index++;
   }
   //............................................................
   TInt freeCount = _pFreeItems->Count();
   //TListIteratorC<FStreamBlock*> iteratorFree = _pFreeItems->IteratorC();
   //while(iteratorFree.Next()){
      //FStreamBlock* pBlock = *iteratorFree;
      //if(index > 0){
      //   dump.Append("\n");
      //}
      //dump.AppendFormat("   %4d - F:", index);
      //pBlock->Track(&dump);
      //index++;
   //}
   //............................................................
   // 追加信息
   TInt count = usingCount + freeCount;
   pTrack->AppendFormat(TC("\n   ") MO_FMT_OBJECT_NAME TC(" block_capacity=%d, count=%d, using_count=%d, free_count=%d, read_length=%ld, write_length=%ld"),
         TC("Buffered stream block"), _blockCapacity, count, usingCount, freeCount, lengthRead, lengthWrite);
   //if(count > 0){
   //   pTrack->AppendFormat("\n%s", (TCharC*)dump);
   //}
}

MO_NAMESPACE_END

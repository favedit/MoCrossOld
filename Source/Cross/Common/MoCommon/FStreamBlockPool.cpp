#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造缓冲队列块。</T>
//============================================================
FStreamBlockPool::FStreamBlockPool(){
   _capacity = 0;
   _blockCapacity = 0;
   MO_CLEAR(_pAllocator);
   _pBlocks = MO_CREATE(FStreamBlockList);
}

//============================================================
// <T>析构缓冲队列块。</T>
//
// @param capacity 数据容量
//============================================================
FStreamBlockPool::~FStreamBlockPool(){
   Reset();
   MO_DELETE(_pBlocks);
}

//============================================================
// <T>收集一个管道块。</T>
//
// @return 管道块
//============================================================
FStreamBlock* FStreamBlockPool::Alloc(){
   FStreamBlock* pBlock = NULL;
   //............................................................
   _locker.Enter();
   // 检查总容量
   TInt blockCount = _pBlocks->Count() + 1;
   TInt capacity = _blockCapacity * (blockCount + 1);
   if(capacity > _capacity){
      MO_ERROR(TC("Capacity is too small. (capacity=%d, block_capacity=%d, block_count=%d, block_alloc=%d)"), _capacity, _blockCapacity, blockCount, capacity);
   }else{
      // 收集一个分块对象
      pBlock = _pAllocator->Alloc();
      _pBlocks->Push(pBlock);
   }
   _locker.Leave();
   //............................................................
   return pBlock;
}

//============================================================
// <T>释放一个管道块。</T>
//
// @param pBlock 管道块
//============================================================
void FStreamBlockPool::Free(FStreamBlock* pBlock){
   MO_ASSERT(pBlock);
   //............................................................
   _locker.Enter();
   // 释放分块
   if(_pBlocks->Contains(pBlock)){
      _pBlocks->Remove(pBlock);
      _pAllocator->Free(pBlock);
   }else{
      MO_ERROR(TC("Free block is not exists. (capacity=%d, block_capacity=%d, block_count=%d)"), _capacity, _blockCapacity, _pBlocks->Count());
   }
   _locker.Leave();
   //............................................................
}

//============================================================
// <T>重置数据。</T>
//
// @return 处理结果
//============================================================
TBool FStreamBlockPool::Reset(){
   _locker.Enter();
   // 释放所有内存块
   TListIteratorC<FStreamBlock*> iterator = _pBlocks->IteratorC();
   while(iterator.Next()){
      FStreamBlock* pBlock = *iterator;
      _pAllocator->Free(pBlock);
   }
   _pBlocks->Clear();
   _locker.Leave();
   return ETrue;
}

//============================================================
// <T>跟踪信息。</T>
//
// @param pTrack 跟踪内容
//============================================================
void FStreamBlockPool::Track(MString* pTrack){
   pTrack->AppendFormat(TC("Buffered queue dump. (capacity=%d\n"), _capacity);
   TListIteratorC<FStreamBlock*> iterator = _pBlocks->IteratorC();
   while(iterator.Next()){
      FStreamBlock* pBlock = *iterator;
      pTrack->Append(TC("   "));
      pBlock->Track(pTrack);
      pTrack->Append(TC("\n"));
   }
}

MO_NAMESPACE_END

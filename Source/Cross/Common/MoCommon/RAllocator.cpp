#include "MoCmMemory.h"
#include "MoCmString8.h"

MO_NAMESPACE_BEGIN

//============================================================
TThreadLocker RAllocator::_locker;
IMemoryStorage* RAllocator::_pStorage = NULL;
FMemoryAllocator* RAllocator::_pMemoryAllocator = NULL;
FBlockAllocatorFreeSet* RAllocator::_pBlockAllocatorSet = NULL;
FBlockAllocatorFreeList* RAllocator::_pBlockAllocators = NULL;
FBlockLockAllocatorFreeList* RAllocator::_pBlockLockAllocators = NULL;

//============================================================
// <T>构造内存收集器。</T>
//============================================================
void RAllocator::Create(){
   _pStorage = MO_MEM_CREATE(FAllocatorStorage);
   // 创建不定长内存收集器
//   _pMemoryAllocator = MO_MEM_CREATE(FMemoryLockAllocator);
//   // 创建块内存列表
//   _pBlockAllocatorSet = MO_MEM_CREATE(FBlockAllocatorFreeSet);
//   // 创建块内存列表
//   _pBlockAllocators = MO_MEM_CREATE(FBlockAllocatorFreeList);
//   // 创建加锁块内存列表
//   _pBlockLockAllocators = MO_MEM_CREATE(FBlockLockAllocatorFreeList);
}

//============================================================
// <T>释放内存收集器。</T>
//============================================================
void RAllocator::Destroy(){
//   // 释放所有加锁块内存列表
//   if(!_pBlockLockAllocators->IsEmpty()){
//      TListIteratorC<FBlockLockAllocator*> iterator = _pBlockLockAllocators->IteratorC();
//      while(iterator.Next()){
//         FBlockLockAllocator* pBlockLockAllocator = iterator.Get();
//         MO_MEM_DELETE(pBlockLockAllocator);
//      }
//   }
//   MO_PTR_DELETE(_pBlockLockAllocators);
//   // 释放所有块内存列表
//   if(!_pBlockAllocatorSet->IsEmpty()){
//      TSetIterator<TUint, FBlockAllocator*> iterator = _pBlockAllocatorSet->IteratorC();
//      while(iterator.Next()){
//         FBlockAllocator* pBlockAllocator = iterator.Value();
//         MO_MEM_DELETE(pBlockAllocator);
//      }
//   }
//   MO_PTR_DELETE(_pBlockAllocatorSet);
//   // 释放所有块内存列表
//   if(!_pBlockAllocators->IsEmpty()){
//      TListIteratorC<FBlockAllocator*> iterator = _pBlockAllocators->IteratorC();
//      while(iterator.Next()){
//         FBlockAllocator* pBlockAllocator = iterator.Get();
//         MO_MEM_DELETE(pBlockAllocator);
//      }
//   }
//   MO_MEM_DELETE(_pBlockAllocators);
//   // 释放不定长内存收集器
//   MO_MEM_DELETE(_pMemoryAllocator);
//   // 不能删除，自引用
//   FAllocatorStorage* pStorage = _pStorage;
//   _pStorage = NULL;
   MO_MEM_DELETE(_pStorage);
}

//============================================================
// <T>获得存储器。</T>
//
// @return 存储器
//============================================================
IMemoryStorage* RAllocator::Storage(){
   return _pStorage;
}

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param size 内存大小
// @return 内存指针
//============================================================
TAny* RAllocator::Alloc(TUint size){
#ifdef _MO_DEBUG
   if(NULL != _pStorage){
      return _pStorage->Alloc(NULL, NULL, (TInt)size, NULL, 0);
   }else{
      TAny* pAlloc = malloc(sizeof(TAny*) + size);
      *(TInt*)pAlloc = 0;
      return (TByte*)pAlloc + sizeof(TAny*);
   }
#else
   return malloc(size);
#endif // _DEBUG
};

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param pTypeName 类型名称
// @param size 内存大小
// @param pFileName 文件名称
// @param lineNumber 文件行数
// @return 内存指针
//============================================================
TAny* RAllocator::Alloc(TChar8C* pTypeName, TUint size, TChar8C* pFileName, TInt lineNumber){
#ifdef _MO_DEBUG
   if(NULL != _pStorage){
      return _pStorage->Alloc(NULL, pTypeName, (TInt)size, pFileName, lineNumber);
   }else{
      TAny* pAlloc = malloc(sizeof(TAny*) + size);
      *(TInt*)pAlloc = 0;
      return (TByte*)pAlloc + sizeof(TAny*);
   }
#else
   return malloc(size);
#endif // _DEBUG
};

//============================================================
// <T>释放内存。</T>
//
// @param pMemory 内存指针
//============================================================
void RAllocator::Free(TAny* pMemory){
   MO_ASSERT(pMemory);
#ifdef _MO_DEBUG
   if(NULL != _pStorage){
      _pStorage->Free(pMemory);
   }else{
      TAny* pAlloc = (TByte*)pMemory - sizeof(TAny*);
      free(pAlloc);
   }
#else
   free(pMemory);
#endif // _DEBUG
}

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param size 内存大小
// @return 内存指针
//============================================================
TAny* RAllocator::TypeAlloc(TUint size){
#ifdef _MO_DEBUG
   if(NULL != _pStorage){
      return _pStorage->Alloc(NULL, NULL, (TInt)size, NULL, 0);
   }else{
      TAny* pAlloc = malloc(sizeof(TAny*) + size);
      *(TInt*)pAlloc = 0;
      return (TByte*)pAlloc + sizeof(TAny*);
   }
#else
   return malloc(size);
#endif // _DEBUG
};

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param pTypeName 类型名称
// @param size 内存大小
// @param pFileName 文件名称
// @param lineNumber 文件行数
// @return 内存指针
//============================================================
TAny* RAllocator::TypeAlloc(TChar8C* pTypeName, TUint size, TChar8C* pFileName, TInt lineNumber){
#ifdef _MO_DEBUG
   if(NULL != _pStorage){
      return _pStorage->Alloc(NULL, pTypeName, (TInt)size, pFileName, lineNumber);
   }else{
      TAny* pAlloc = malloc(sizeof(TAny*) + size);
      *(TInt*)pAlloc = 0;
      return (TByte*)pAlloc + sizeof(TAny*);
   }
#else
   return malloc(size);
#endif // _DEBUG
};

//============================================================
// <T>释放内存。</T>
//
// @param pMemory 内存指针
//============================================================
void RAllocator::TypeFree(TAny* pMemory){
   MO_ASSERT(pMemory);
#ifdef _MO_DEBUG
   if(NULL != _pStorage){
      _pStorage->Free(pMemory);
   }else{
      TAny* pAlloc = (TByte*)pMemory - sizeof(TAny*);
      free(pAlloc);
   }
#else
   free(pMemory);
#endif // _DEBUG
}

//============================================================
FBlockAllocator* RAllocator::BlockAllocatorAlloc(TInt size){
   FBlockAllocator* pAllocator = MO_PTR_CREATE(FBlockAllocator, size);
   _locker.Enter();
   _pBlockAllocators->Push(pAllocator);
   _locker.Leave();
   return pAllocator;
}

//============================================================
void RAllocator::BlockAllocatorFree(FBlockAllocator* pAllocator){
   MO_ASSERT(pAllocator);
   _locker.Enter();
   _pBlockAllocators->Remove(pAllocator);
   _locker.Leave();
   MO_PTR_DELETE(pAllocator);
}

//============================================================
FBlockLockAllocator* RAllocator::BlockLockAllocatorAlloc(TInt size){
   FBlockLockAllocator* pAllocator = MO_PTR_CREATE(FBlockLockAllocator, size);
   _locker.Enter();
   _pBlockLockAllocators->Push(pAllocator);
   _locker.Leave();
   return pAllocator;
}

//============================================================
void RAllocator::BlockLockAllocatorFree(FBlockLockAllocator* pAllocator){
   MO_ASSERT(pAllocator);
   _locker.Enter();
   _pBlockLockAllocators->Remove(pAllocator);
   _locker.Leave();
   MO_PTR_DELETE(pAllocator);
}

//============================================================
TBool RAllocator::CalculateStatistics(TUint& used, TUint& total){
   used = 0;
   total = 0;
   // 非加锁内存运行信息
   if(!_pBlockAllocators->IsEmpty()){
      TListIteratorC<FBlockAllocator*> iterator = _pBlockAllocators->IteratorC();
      while(iterator.Next()){
         FBlockAllocator* pAllocator = iterator.Get();
         used += pAllocator->GetMemoryUsed();
         total += pAllocator->GetMemoryTotal();
      }
   }
   // 加锁内存运行信息
   if(!_pBlockLockAllocators->IsEmpty()){
      TListIteratorC<FBlockLockAllocator*> iterator = _pBlockLockAllocators->IteratorC();
      while(iterator.Next()){
         FBlockLockAllocator* pAllocator = iterator.Get();
         used += pAllocator->GetMemoryUsed();
         total += pAllocator->GetMemoryTotal();
      }
   }
   return ETrue;
}

//============================================================
void RAllocator::DumpTrack(){
   TInt memoryUsed = 0;
   TInt memoryTotal = 0;
   //MO_STATIC_TRACK("-- Track memory - %s --------------------------------------", "begin");
   // 非加锁内存运行信息
   if(!_pBlockAllocators->IsEmpty()){
      TListIteratorC<FBlockAllocator*> iterator = _pBlockAllocators->IteratorC();
      while(iterator.Next()){
         FBlockAllocator* pAllocator = iterator.Get();
         memoryUsed += pAllocator->GetMemoryUsed();
         memoryTotal += pAllocator->GetMemoryTotal();
         pAllocator->Dump();
      }
   }
   // 加锁内存运行信息
   if(!_pBlockLockAllocators->IsEmpty()){
      TListIteratorC<FBlockLockAllocator*> iterator = _pBlockLockAllocators->IteratorC();
      while(iterator.Next()){
         FBlockLockAllocator* pAllocator = iterator.Get();
         memoryUsed += pAllocator->GetMemoryUsed();
         memoryTotal += pAllocator->GetMemoryTotal();
         pAllocator->Dump();
      }
   }
   //MO_STATIC_TRACK("-- Track memory - end -- used=0x%08X, total=0x%08X ------------------", memoryUsed, memoryTotal);
}

MO_NAMESPACE_END

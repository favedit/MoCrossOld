#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建线程内存。</T>
//============================================================
FThreadMemory::FThreadMemory(){
   // 构造块内存存储列表
   _pBlockAllocators = MO_PTR_CREATE(FBlockAllocatorFreeSet);
}

//============================================================
// <T>释放线程内存块。</T>
//============================================================
FThreadMemory::~FThreadMemory(){
   // 释放块内存列表
   TSetIterator<TUint, FBlockAllocator*> iterator = _pBlockAllocators->IteratorC();
   while(iterator.Next()){
      RAllocator::BlockAllocatorFree(iterator.Value());
   }
   MO_PTR_DELETE(_pBlockAllocators);
}

//============================================================
// <T>收集线程内存块。</T>
//
// @param size 内存大小
// @return 内存块
//============================================================
TAny* FThreadMemory::Alloc(TUint size){
   FBlockAllocator* pAllocator = _pBlockAllocators->Get(size);
   if(NULL == pAllocator){
      pAllocator = RAllocator::BlockAllocatorAlloc(size);
      _pBlockAllocators->Set(size, pAllocator);
   }
   return pAllocator->Alloc(size);
}

//============================================================
// <T>收集线程内存块。</T>
//
// @param pTypeName 类名
// @param size 内存大小
// @param pFileName 文件名称
// @param line 文件行号
// @return 内存块
//============================================================
TAny* FThreadMemory::Alloc(TCharC* pTypeName, TUint size, TChar8C* pFileName, TInt fileLine){
   FBlockAllocator* pAllocator = _pBlockAllocators->Get(size);
   if(NULL == pAllocator){
      pAllocator = RAllocator::BlockAllocatorAlloc(size);
      _pBlockAllocators->Set(size, pAllocator);
   }
   return pAllocator->Alloc(NULL, pTypeName, size, pFileName, fileLine);
}

//============================================================
// <T>释放线程内存块。</T>
//
// @param pMemory 内存对象
//============================================================
void FThreadMemory::Free(TAny* pMemory){
   MO_ASSERT(pMemory);
   // 获得当前使用的实例
   TInt* pAlloc = ((TInt*)pMemory) - 1;
   IAllocator* pAllocator = (IAllocator*)pAlloc[0];
   // 释放类型内存块
   pAllocator->Free(pMemory);
}

MO_NAMESPACE_END

#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造块内存实例。</T>
//
// @param typeSize 原子大小
//============================================================
FMemoryAllocator::FMemoryAllocator(){
   MO_CLEAR(_pAlloc);
}

//============================================================
// <T>析构块内存实例。</T>
//============================================================
FMemoryAllocator::~FMemoryAllocator(){
   // 删除所有收集的区
   /*while(NULL != _pUsed){
      SMemoryEntry* pAtom = _pUsed;
      _pUsed = _pUsed->pNext;
      MO_PTR_DELETE(pAtom);
   }*/
   // 删除所有收集的区
   while(NULL != _pAlloc){
      TAny* pMemory = _pAlloc->pMemory;
      _pAlloc = _pAlloc->pNext;
#ifdef _MO_WINDOWS
      _aligned_free(pMemory);
#else
      free(pMemory);
#endif
   }
}

//============================================================
// <T>扩展原子内存。</T>
// <P>收集内存的第一块为收集链表，第二块开始为前部分为块信息，后部分为数据区。</P>
//
// @param pEntry 内存原子实例
//============================================================
void FMemoryAllocator::EntryExtend(){
   // 计算要收集的个数
   TInt atomSize = sizeof(SMemoryEntry);
   // TODO: TInt pageSize = getpagesize();
   TInt pageSize = 8192;
   TInt count = MO_LIB_MAX(pageSize / atomSize, MO_OBJECT_CAPACITY);
   // 扩展内存
   TUint allocSize = sizeof(SMemoryEntry) + atomSize * count;
   TByte* pMemory = (TByte*)MO_ALIGNED_ALLOC(allocSize, sizeof(TInt));
   MO_ASSERT(pMemory);
   memset(pMemory, 0, allocSize);
   // 保存第一块内存区
   SMemoryEntry* pAllocAtom = (SMemoryEntry*)pMemory;
   pAllocAtom->pMemory = pMemory;
   // 压入已收集区
   if(NULL != _pAlloc){
      _pAlloc->pPrior = pAllocAtom;
   }
   pAllocAtom->pNext = _pAlloc;
   _pAlloc = pAllocAtom;
   // 初始化所有内存原子
   pMemory += sizeof(SMemoryEntry);
   TInt n = -1;
   while(++n < count){
      // 链初始化
      SMemoryEntry* pEntry = (SMemoryEntry*)pMemory;
      pEntry->pAllocator = this;
      pMemory += atomSize;
      // 压入未使用的队列
      if(NULL != _pUnused){
         _pUnused->pPrior = pEntry;
      }
      pEntry->pNext = _pUnused;
      _pUnused = pEntry;
   }
}

//============================================================
// <T>收集一个内存原子实例。</T>
//
// @return 内存原子实例
//============================================================
SMemoryEntry* FMemoryAllocator::EntryAlloc(){
   // 如果没有可使用的内存，则扩展缓冲区
   if(NULL == _pUnused){
      EntryExtend();
   }
   // 获得一个可用的类型内存
   SMemoryEntry* pEntry = _pUnused;
   _pUnused = pEntry->pNext;
   // 压入使用中的队列
   //EntryPush(pEntry);
   // 记录收集操作
   pEntry->Alloc();
   // 返回收集的内存
   return pEntry;
}

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param size 内存大小
// @return 内存指针
//============================================================
TAny* FMemoryAllocator::Alloc(TInt size){
   MO_ASSERT(size > 0);
   SMemoryEntry* pEntry = EntryAlloc();
   MO_ASSERT(pEntry);
   TByte* pMemory = (TByte*)MO_ALIGNED_ALLOC(size, sizeof(TInt));
   pEntry->Link(pMemory, size);
   return pEntry->pMemory;
}

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param pOwnerName 拥有者名称
// @param pTypeName 类型名称
// @param size 内存大小
// @param pFileName 文件名称
// @param fileLine 文件行数
// @return 内存指针
//============================================================
TAny* FMemoryAllocator::Alloc(TCharC* pOwnerName, TCharC* pTypeName, TInt size, TChar8C* pFileName, TInt lineNumber){
   MO_ASSERT(size > 0);
   SMemoryEntry* pEntry = EntryAlloc();
   MO_ASSERT(pEntry);
   TByte* pMemory = (TByte*)MO_ALIGNED_ALLOC(size, sizeof(TInt));
   pEntry->SetTypeName(pTypeName);
   pEntry->SetFileInfo(pFileName, lineNumber);
   pEntry->Link(pMemory, size);
   return pEntry->pMemory;
}

//============================================================
// <T>释放内存。</T>
//
// @param pMemory 内存指针
//============================================================
void FMemoryAllocator::Free(TAny* pMemory){
   // 获得当前使用的实例
   MO_ASSERT(pMemory);
   TInt* pAlloc = ((TInt*)pMemory) - 1;
   SMemoryEntry* pEntry = (SMemoryEntry*)pAlloc[0];
   MO_ASSERT(pEntry);
   // 检查内存转换正确性
   MO_ASSERT(pEntry->pAllocator == this);
   MO_ASSERT(pEntry->pMemory == pMemory);
   // 释放内存
   MO_ALIGNED_FREE(pEntry->pAlloc);
   // 记录释放操作
   pEntry->Free();
   // 压入未使用的队列
   //MLinkedEntryC<SMemoryEntry*>::EntryFree(pEntry);
}

MO_NAMESPACE_END

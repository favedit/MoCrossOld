#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造块内存实例。</T>
//
// @param typeSize 原子大小
//============================================================
FBlockAllocator::FBlockAllocator(TInt blockSize){
   MO_CLEAR(_pName);
   _count = 0;
   _blockSize = blockSize;
   _pEntries = MO_CREATE(FMemoryEntryList);
//   MO_CLEAR(_pAlloc);
//   MO_CLEAR(_pFirst);
//   MO_CLEAR(_pLast);
//   MO_CLEAR(_pUnused);
   //_total = 0;
   //_memoryTotal = 0;
}

//============================================================
// <T>析构块内存实例。</T>
//============================================================
FBlockAllocator::~FBlockAllocator(){
   // 删除所有收集的区
//   while(NULL != _pAlloc){
//      TAny* pMemory = _pAlloc->pAlloc;
//      _pAlloc = _pAlloc->pNext;
//      MO_ALIGNED_FREE(pMemory);
//   }
   MO_DELETE(_pEntries);
}

//============================================================
// <T>扩展原子内存。</T>
// <P>收集内存的第一块为收集链表，第二块开始为前部分为块信息，后部分为数据区。</P>
//
// @param pEntry 内存原子实例
//============================================================
void FBlockAllocator::EntryExtend(){
//   MO_ASSERT(_blockSize > 0);
//   // 计算要收集的个数，使要收集的内存尽量落在页内
//   TInt atomSize = sizeof(SMemoryEntry) + sizeof(TInt) + _blockSize;
//   // TODO: TInt pageSize = getpagesize();
//   TInt pageSize = 8192;
//   TInt count = MO_MAX((pageSize - sizeof(SMemoryEntry)) / atomSize, MO_OBJECT_CAPACITY);
//   TUint allocSize = sizeof(SMemoryEntry) + atomSize * count;
//   //_total += count;
//   //_memoryTotal += allocSize;
//   // 收集内存
//   TByte* pMemory = (TByte*)MO_ALIGNED_ALLOC(allocSize, sizeof(TInt));
//   MO_ASSERT(pMemory);
//   memset(pMemory, 0, allocSize);
//   // 保存第一块内存区
//   SMemoryEntry* pAllocAtom = (SMemoryEntry*)pMemory;
//   pAllocAtom->pMemory = pMemory;
//   pMemory += sizeof(SMemoryEntry);
//   // 压入已收集区
//   if(NULL != _pAlloc){
//      _pAlloc->pPrior = pAllocAtom;
//   }
//   pAllocAtom->pNext = _pAlloc;
//   _pAlloc = pAllocAtom;
//   // 初始化所有内存原子
//   while(--count >= 0){
//      // 链初始化
//      SMemoryEntry* pEntry = (SMemoryEntry*)pMemory;
//      pEntry->pAllocator = this;
//      //pEntry->Link(pMemory + sizeof(SMemoryEntry), _atomSize);
//      pMemory += atomSize;
//      // 压入未使用的队列
//      if(NULL != _pUnused){
//         _pUnused->pPrior = pEntry;
//      }
//      pEntry->pNext = _pUnused;
//      _pUnused = pEntry;
//   }
}

//============================================================
// <T>将链表节点压入尾位置。</T>
//
// @param pEntry 内存原子实例
//============================================================
void FBlockAllocator::EntryPush(SMemoryEntry* pEntry){
//   MO_ASSERT(pEntry);
//   if(NULL == _pLast){
//      _pFirst = pEntry;
//   }else{
//      _pLast->pNext = pEntry;
//   }
//   pEntry->pPrior = _pLast;
//   _pLast = pEntry;
//   // 设置内容
//   _count++;
//   pEntry->pNext = NULL;
}

//============================================================
// <T>从链表上删除链表节点。</T>
//
// @param pEntry 内存原子实例
//============================================================
void FBlockAllocator::EntryRemove(SMemoryEntry* pEntry){
//   MO_ASSERT(pEntry);
//   SMemoryEntry* pPrior = pEntry->pPrior;
//   SMemoryEntry* pNext = pEntry->pNext;
//   // 处理前节点
//   if(NULL == pPrior){
//      _pFirst = pNext;
//   }else{
//      pPrior->pNext = pNext;
//   }
//   // 处理后节点
//   if(NULL == pNext){
//      _pLast = pPrior;
//   }else{
//      pNext->pPrior = pPrior;
//   }
//   // 设置内容
//   _count--;
//   // 释放节点
//   pEntry->pNext = _pUnused;
//   _pUnused = pEntry;
}

//============================================================
// <T>收集一个内存原子实例。</T>
//
// @return 内存原子实例
//============================================================
SMemoryEntry* FBlockAllocator::EntryAlloc(){
//   // 如果没有可使用的内存，则扩展缓冲区
//   if(NULL == _pUnused){
//      EntryExtend();
//   }
//   // 获得一个可用的类型内存
//   SMemoryEntry* pEntry = _pUnused;
//   _pUnused = pEntry->pNext;
//   // 压入使用中的队列
//   EntryPush(pEntry);
//   // 记录收集操作
//   pEntry->Alloc();
//   // 返回收集的内存
//   return pEntry;
   return NULL;
}

//============================================================
// <T>获取类名称。</T>
//
// @return 类名称
//============================================================
TCharC* FBlockAllocator::Name(){
   return _pName;
}

//============================================================
// <T>设置类名称。</T>
//
// @param pName 类名称
//============================================================
void FBlockAllocator::SetName(TCharC* pName){
   _pName = pName;
}

//============================================================
// <T>获取已使用内存。</T>
//
// @return 已使用内存
//============================================================
TInt FBlockAllocator::GetMemoryUsed(){
   return _blockSize * _count;
}

//============================================================
// <T>获取总使用内存。</T>
//
// @return 总使用内存
//============================================================
TInt FBlockAllocator::GetMemoryTotal(){
   //return _memoryTotal;
   return 0;
}

//============================================================
// <T>收集一块定长的内存。</T>
//
// @param size 内存大小
// @return 内存指针
//============================================================
TAny* FBlockAllocator::Alloc(TInt size){
   SMemoryEntry* pEntry = EntryAlloc();
   MO_ASSERT(pEntry);
   return pEntry->pMemory;
}

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param pOwnerName 拥有者名称
// @param pTypeName 类型名称
// @param size 内存大小
// @param pFileName 文件名称
// @param lineNumber 文件行数
// @return 内存指针
//============================================================
TAny* FBlockAllocator::Alloc(TCharC* pOwnerName, TCharC* pTypeName, TInt size, TChar8C* pFileName, TInt lineNumber){
   // MO_DEBUG("new %s(%d)\n", pTypeName, size);
   //MO_ASSERT(_blockSize == size);
   SMemoryEntry* pEntry = EntryAlloc();
   MO_ASSERT(pEntry);
   pEntry->SetTypeName(pTypeName);
   pEntry->SetFileInfo(pFileName, lineNumber);
   return pEntry->pMemory;
}

//============================================================
// <T>释放内存。</T>
//
// @param pMemory 内存指针
//============================================================
void FBlockAllocator::Free(TAny* pMemory){
   // 获得当前使用的实例
   MO_ASSERT(pMemory);
   TInt* pAlloc = ((TInt*)pMemory) - 1;
   //SMemoryEntry* pEntry = MO_CAST_FORCE<TAny*, SMemoryEntry*>(pAlloc);
   SMemoryEntry* pEntry = (SMemoryEntry*)pAlloc[0];
   MO_ASSERT(pEntry);
   // 检查内存转换正确性
   MO_ASSERT(pEntry->pAllocator == this);
   MO_ASSERT(pEntry->pMemory == pMemory);
   // 记录释放操作
   pEntry->Free();
   _count--;
   // 压入未使用的队列
   EntryRemove(pEntry);
}

//============================================================
void FBlockAllocator::Dump(){
   //MO_TRACK("Size %08d memory is used. [used=%d, free=%d, total=%d, usedTotal=%d, allocTotal=%d]", _atomSize, _count, _total - _count, _total, GetMemoryUsed(), _memoryTotal);
//   SMemoryEntry* pEntry = _pFirst;
//   while(NULL != pEntry){
//      //MO_TRACK("-- Block [type=%s, alloc=%d/%d file=%s(%d)]", pEntry->pTypeName, pEntry->allocCount, pEntry->freeCount, pEntry->pFileName, pEntry->fileLine);
//      pEntry = pEntry->pNext;
//   }
}

//============================================================
FBlockLockAllocator::FBlockLockAllocator(TUint atomSize){
}

//============================================================
FBlockLockAllocator::~FBlockLockAllocator(){
}

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param size 内存大小
// @return 内存指针
//============================================================
TAny* FBlockLockAllocator::Alloc(TInt size){
   _locker.Enter();
   TAny* pMemory = FBlockAllocator::Alloc(size);
   _locker.Leave();
   return pMemory;
}

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param pOwnerName 拥有者名称
// @param pTypeName 类型名称
// @param size 内存大小
// @param pFileName 文件名称
// @param lineNumber 文件行数
// @return 内存指针
//============================================================
TAny* FBlockLockAllocator::Alloc(TCharC* pOwnerName, TCharC* pTypeName, TInt size, TChar8C* pFileName, TInt lineNumber){
   _locker.Enter();
   TAny* pMemory = FBlockAllocator::Alloc(pOwnerName, pTypeName, size, pFileName, lineNumber);
   _locker.Leave();
   return pMemory;
}

//============================================================
// <T>释放内存。</T>
//
// @param pMemory 内存指针
//============================================================
void FBlockLockAllocator::Free(TAny* pMemory){
   _locker.Enter();
   FBlockAllocator::Free(pMemory);
   _locker.Leave();
}

MO_NAMESPACE_END

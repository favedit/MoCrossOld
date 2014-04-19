#include "MoCmSharedList.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造共享链表节点收集器。</T>
//============================================================
FSharedListAllocator::FSharedListAllocator(){
   _capacity = 0;
   _entryCapacity = 0;
   _gHead = NULL;
   _gLists = 0;
   _gEntries = NULL;
}

//============================================================
// <T>设置链表容量。</T>
//============================================================
void FSharedListAllocator::SetCapacity(TInt capacity){
   _capacity = capacity;
}

//============================================================
// <T>设置节点容量。</T>
//============================================================
void FSharedListAllocator::SetEntryCapacity(TInt entryCapacity){
   _entryCapacity = entryCapacity;
}

//============================================================
// <T>共享内存初始化。</T>
//============================================================
void FSharedListAllocator::OnSharedInitialize(){
   // 初始化头信息
   _gHead->listCount = 0;
   _gHead->listUnused = 0;
   _gHead->entryCount = 0;
   _gHead->entryUnused = 0;
   // 初始化链表头信息
   for(TInt32 n = 0; n < _capacity; n++){
      SSharedListHead& list = _gLists[n];
      list.count = 0;
      list.index = n;
      list.first = -1;
      list.last = -1;
      list.prior = n - 1;
      list.next = (_capacity - 1 == n) ? -1 : n + 1;
   }
   // 初始化链表节点信息
   for(TInt32 n = 0; n < _entryCapacity; n++){
      SSharedListEntry& entry = _gEntries[n];
      entry.list = -1;
      entry.index = n;
      entry.prior = n - 1;
      entry.next = (_entryCapacity - 1 == n) ? -1 : n + 1;
      entry.code = -1;
   }
}

//============================================================
// <T>分配共享内存。</T>
//============================================================
void FSharedListAllocator::OnSharedLink(TShareSegment& segment){
   MO_ASSERT(_capacity > 0);
   MO_ASSERT(_entryCapacity > 0);
   _gHead = segment.TypeAlloc<SHead>();
   _gLists = segment.TypeAlloc<SSharedListHead>(_capacity);
   _gEntries = segment.TypeAlloc<SSharedListEntry>(_entryCapacity);
}

//============================================================
// <T>计算共享内存大小。</T>
//============================================================
TSize FSharedListAllocator::SharedCapacity(){
   TSize capacity = sizeof(SHead);
   capacity += sizeof(SSharedListHead) * _capacity;
   capacity += sizeof(SSharedListEntry) * _entryCapacity;
   return capacity;
}

//============================================================
// <T>收集一个未使用的链表。</T>
//============================================================
SSharedListHead* FSharedListAllocator::AllocList(){
   // 获得未使用的索引
   TInt index = _gHead->listUnused;
   MO_ASSERT(index >= 0);
   _gHead->listUnused = _gLists[index].next;
   _gHead->listCount++;
   return &_gLists[index];
}

//============================================================
// <T>释放一个链表。</T>
//============================================================
void FSharedListAllocator::FreeList(SSharedListHead* pList){
   MO_ASSERT(pList);
   TInt index = pList->index;
   pList->first = -1;
   pList->last = -1;
   _gLists[index].next = _gHead->listUnused;
   _gHead->listUnused = index;
   _gHead->listCount--;
}

//============================================================
// <T>收集一个节点。</T>
//============================================================
SSharedListEntry& FSharedListAllocator::AllocEntry(){
   // 获得未使用的索引
   TInt index = _gHead->entryUnused;
   MO_ASSERT(index >= 0);
   SSharedListEntry& entry = _gEntries[index];
   _gHead->entryUnused = entry.next;
   _gHead->entryCount++;
   return entry;
}

//============================================================
// <T>释放一个索引位置。</T>
//============================================================
void FSharedListAllocator::FreeEntry(SSharedListEntry& entry){
   TInt index = entry.index;
   _gEntries[index].next = _gHead->entryUnused;
   _gHead->entryUnused = index;
   _gHead->entryCount--;
}

//============================================================
// <T>释放一个索引链表。</T>
//============================================================
void FSharedListAllocator::FreeEntries(SSharedListHead* pList){
   if(pList->count > 0){
      _gEntries[pList->last].next = _gHead->entryUnused;
      _gHead->entryUnused = pList->first;
      _gHead->entryCount -= pList->count;
   }
}

MO_NAMESPACE_END

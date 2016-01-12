#ifndef __MO_CM_POOL_H__
#define __MO_CM_POOL_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_ENTRY_H__
#include "MoCmEntry.h"
#endif // __MO_CM_ENTRY_H__

#ifndef __MO_CM_VECTOR_H__
#include "MoCmVector.h"
#endif // __MO_CM_VECTOR_H__

#ifndef __MO_CM_LIST_H__
#include "MoCmList.h"
#endif // __MO_CM_LIST_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>实例池对象。</T>
//
// @struct
//============================================================
enum EInstancedFlag{
   EInstancedFlag_Unknown = 0,
   EInstancedFlag_Using   = 1,
};

//============================================================
// <T>对象缓冲池。</T>
//
// @history 130320 MAOCY 创建
//============================================================
template <typename T>
class MPool{
public:
   typedef FList<T> FItemList;
protected:
   FItemList* _pUsingItems;
   FItemList* _pFreeItems;
public:
   //------------------------------------------------------------
   // <T>构造收集模块。</T>
   MPool(){
      _pUsingItems = MO_CREATE(FItemList);
      _pFreeItems = MO_CREATE(FItemList);
   }
   //------------------------------------------------------------
   // <T>析构收集模块。</T>
   MO_ABSTRACT ~MPool(){
      MO_DELETE(_pFreeItems);
      MO_DELETE(_pUsingItems);
   }
public:
   //------------------------------------------------------------
   // <T>获得是否有对象。</T>
   MO_INLINE TBool HasItem(){
      TBool hasUsing = !_pUsingItems->IsEmpty();
      TBool hasFree = !_pFreeItems->IsEmpty();
      return hasUsing | hasFree;
   }
   //------------------------------------------------------------
   // <T>获得对象个数。</T>
   MO_INLINE TInt Count(){
      TInt usingCount = _pUsingItems->Count();
      TInt freeCount = _pFreeItems->Count();
      return usingCount + freeCount;
   }
   //------------------------------------------------------------
   // <T>获得是否有使用对象。</T>
   MO_INLINE TBool HasUsingItem(){
      return !_pUsingItems->IsEmpty();
   }
   //------------------------------------------------------------
   // <T>获得使用对象集合。</T>
   MO_INLINE FItemList* UsingItems(){
      return _pUsingItems;
   }
   //------------------------------------------------------------
   // <T>获得使用对象个数。</T>
   MO_INLINE TInt UsingCount(){
      return _pUsingItems->Count();
   }
   //------------------------------------------------------------
   // <T>获得是否有空闲对象。</T>
   MO_INLINE TBool HasFreeItem(){
      return !_pFreeItems->IsEmpty();
   }
   //------------------------------------------------------------
   // <T>获得空闲对象个数。</T>
   MO_INLINE TInt FreeCount(){
      return _pFreeItems->Count();
   }
   //------------------------------------------------------------
   // <T>获得空闲对象集合。</T>
   MO_INLINE FItemList* FreeItems(){
      return _pFreeItems;
   }
public:
   //------------------------------------------------------------
   // <T>获得使用列表。</T>
   T Store(T pItem){
      MO_CHECK(pItem, return pItem);
#ifdef _MO_DEBUG
      if(NULL != pItem){
         if(!_pFreeItems->Contains(pItem)){
            _pFreeItems->Push(pItem);
         }else{
            MO_ERROR(TC("Storage item is already exists. (using_count=%d, free_count=%d)"),
                  _pUsingItems->Count(), _pFreeItems->Count());
         }
      }
#else
      _pFreeItems->Push(pItem);
#endif // _MO_DEBUG
      return pItem;
   }
   //------------------------------------------------------------
   // <T>从首部收集一个对象。</T>
   T AllocFirst(){
      T pItem = NULL;
      if(!_pFreeItems->IsEmpty()){
         pItem = _pFreeItems->Shift();
         _pUsingItems->Push(pItem);
      }else{
         MO_ERROR(TC("Free item is empty. alloc item failure. (using_count=%d, free_count=%d)"),
               _pUsingItems->Count(), _pFreeItems->Count());
      }
      return pItem;
   }
   //------------------------------------------------------------
   // <T>从尾部收集一个对象。</T>
   T AllocLast(){
      T pItem = NULL;
      if(!_pFreeItems->IsEmpty()){
         pItem = _pFreeItems->Pop();
         _pUsingItems->Push(pItem);
      }else{
         MO_ERROR(TC("Free item is empty. alloc item failure. (using_count=%d, free_count=%d)"),
               _pUsingItems->Count(), _pFreeItems->Count());
      }
      return pItem;
   }
   //------------------------------------------------------------
   // <T>释放一个对象到缓冲池最顶部。</T>
   TBool FreeFirst(T pItem){
      MO_CHECK(pItem, return EFalse);
      // 从使用集合移除
#ifdef _MO_DEBUG
      if(_pUsingItems->Contains(pItem)){
         _pUsingItems->Remove(pItem);
      }else{
         MO_ERROR(TC("Free item is not exists in use collection. (using_count=%d, free_count=%d)"),
               _pUsingItems->Count(), _pFreeItems->Count());
      }
      // 放入释放集合中
      if(!_pFreeItems->Contains(pItem)){
         _pFreeItems->Unshift(pItem);
      }else{
         MO_ERROR(TC("Free item is already exists in free collection. (using_count=%d, free_count=%d)"),
               _pUsingItems->Count(), _pFreeItems->Count());
      }
#else
      _pUsingItems->Remove(pItem);
      _pFreeItems->Unshift(pItem);
#endif // _MO_DEBUG
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>释放一个对象到缓冲池最底部。</T>
   TBool FreeLast(T pItem){
      MO_CHECK(pItem, return EFalse);
      // 从使用集合移除
#ifdef _MO_DEBUG
      if(_pUsingItems->Contains(pItem)){
         _pUsingItems->Remove(pItem);
      }else{
         MO_ERROR(TC("Free item is not exists in use collection. (using_count=%d, free_count=%d)"),
               _pUsingItems->Count(), _pFreeItems->Count());
      }
      // 放入释放集合中
      if(!_pFreeItems->Contains(pItem)){
         _pFreeItems->Push(pItem);
      }else{
         MO_ERROR(TC("Free item is already exists in free collection. (using_count=%d, free_count=%d)"),
               _pUsingItems->Count(), _pFreeItems->Count());
      }
#else
      _pUsingItems->Remove(pItem);
      _pFreeItems->Push(pItem);
#endif // _MO_DEBUG
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>移除一个对象。</T>
   TBool Remove(T pItem){
      MO_CHECK(pItem, return EFalse);
      // 移除使用中对象
      _pUsingItems->Remove(pItem);
      // 移除空闲中对象
      _pFreeItems->Remove(pItem);
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>释放所有对象。</T>
   void Clear(){
      _pUsingItems->Clear();
      _pFreeItems->Clear();
   }
   //------------------------------------------------------------
   // <T>释放所有对象。</T>
   void Release(){
      // 删除所有使用对象
      if(!_pUsingItems->IsEmpty()){
         TListIteratorC<T> iterator = _pUsingItems->IteratorC();
         while(iterator.Next()){
            T pItem = *iterator;
            MO_DELETE(pItem);
         }
         _pUsingItems->Clear();
      }
      // 删除所有自由对象
      if(!_pFreeItems->IsEmpty()){
         TListIteratorC<T> iterator = _pFreeItems->IteratorC();
         while(iterator.Next()){
            T pItem = *iterator;
            MO_DELETE(pItem);
         }
         _pFreeItems->Clear();
      }
   }
};

//============================================================
// <T>变长对象缓冲池。</T>
//
// @tool
//============================================================
template <typename T>
class TPool : public MPool<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长缓冲池。</T>
   TPool(){
   }
   //------------------------------------------------------------
   // <T>析构变长缓冲池。</T>
   ~TPool(){
   }
};

//============================================================
// <T>变长对象缓冲池。</T>
//
// @class
//============================================================
template <typename T>
class FPool :
      public FObject,
      public MPool<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   FPool(){
   }
   //------------------------------------------------------------
   // <T>析构变长读写链表。</T>
   ~FPool(){
   }
};

//============================================================
// <T>对象存储缓冲池。</T>
//
// @history 130320 MAOCY 创建
//============================================================
template <typename T>
class MStoragePool{
public:
   typedef FList<T> FItemList;
   typedef FVector<T> FItemVector;
protected:
   FItemList* _pItems;
   FItemList* _pUsingItems;
   FItemList* _pFreeItems;
   FItemVector* _pStorage;
public:
   //------------------------------------------------------------
   // <T>构造对象存储缓冲池。</T>
   MStoragePool(){
      _pItems = MO_CREATE(FItemList);
      _pUsingItems = MO_CREATE(FItemList);
      _pFreeItems = MO_CREATE(FItemList);
      _pStorage = MO_CREATE(FItemVector);
   }
   //------------------------------------------------------------
   // <T>析构对象存储缓冲池。</T>
   MO_ABSTRACT ~MStoragePool(){
      MO_DELETE(_pItems);
      MO_DELETE(_pUsingItems);
      MO_DELETE(_pFreeItems);
      MO_DELETE(_pStorage);
   }
public:
   //------------------------------------------------------------
   // <T>获得是否有对象。</T>
   MO_INLINE TBool HasItem(){
      return !_pItems->IsEmpty();
   }
   //------------------------------------------------------------
   // <T>获得对象个数。</T>
   MO_INLINE TInt Count(){
      return _pItems->Count();
   }
   //------------------------------------------------------------
   // <T>获得对象集合。</T>
   MO_INLINE FItemList* Items(){
      return _pItems;
   }
   //------------------------------------------------------------
   // <T>获得是否有使用对象。</T>
   MO_INLINE TBool HasUsingItem(){
      return !_pUsingItems->IsEmpty();
   }
   //------------------------------------------------------------
   // <T>获得使用对象个数。</T>
   MO_INLINE TInt UsingCount(){
      return _pUsingItems->Count();
   }
   //------------------------------------------------------------
   // <T>获得使用对象集合。</T>
   MO_INLINE FItemList* UsingItems(){
      return _pUsingItems;
   }
   //------------------------------------------------------------
   // <T>获得是否有空闲对象。</T>
   MO_INLINE TBool HasFreeItem(){
      return !_pFreeItems->IsEmpty();
   }
   //------------------------------------------------------------
   // <T>获得自由对象个数。</T>
   MO_INLINE TInt FreeCount(){
      return _pFreeItems->Count();
   }
   //------------------------------------------------------------
   // <T>获得空闲对象集合。</T>
   MO_INLINE FItemList* FreeItems(){
      return _pFreeItems;
   }
   //------------------------------------------------------------
   // <T>获得存储列表。</T>
   MO_INLINE FItemVector* Storage(){
      return _pStorage;
   }
public:
   //------------------------------------------------------------
   // <T>根据索引查找对象。</T>
   MO_INLINE T* FindByIndex(TInt index){
      return _pStorage->Nvl(index, NULL);
   }
   //------------------------------------------------------------
   // <T>根据索引获得对象。</T>
   MO_INLINE T* GetByIndex(TInt index){
      return _pStorage->Get(index);
   }
public:
   //------------------------------------------------------------
   // <T>获得下一个可用的索引。</T>
   TInt FindNextIndex(TBool checked = ETrue){
      // 查找存在和可放的
      TInt count = _pStorage->Count();
      if(checked){
         for(TInt n = 0; n < count; n++){
            T pFind = _pStorage->Get(n);
            if(NULL == pFind){
               return n;
            }
         }
      }
      // 设置位置
      return count;
   }
   //------------------------------------------------------------
   // <T>存储对象。</T>
   TInt Store(T pItem, TBool checked = ETrue){
      MO_ASSERT(pItem);
      // 放入集合
      _pItems->Push(pItem);
      // 放入自由集合
      _pFreeItems->Push(pItem);
      // 放入存储集合
      TInt index = -1;
      if(checked){
         index = StorageStore(pItem);
      }else{
         index = _pStorage->Count();
         _pStorage->Push(pItem);
      }
      return index;
   }
   //------------------------------------------------------------
   // <T>存储对象在存储集合中。</T>
   TInt StorageStore(T pItem){
      MO_ASSERT(pItem);
      // 查找存在和可放的
      TInt findIndex = -1;
      TInt count = _pStorage->Count();
      for(TInt n = 0; n < count; n++){
         T pFind = _pStorage->Get(n);
         if(NULL == pFind){
            if(-1 == findIndex){
               findIndex = n;
            }
         }else if(pFind == pItem){
            return n;
         }
      }
      // 设置位置
      TInt index = -1;
      if(-1 == findIndex){
         index = _pStorage->Count();
         _pStorage->Push(pItem);
      }else{
         index = findIndex;
         _pStorage->Set(findIndex, pItem);
      }
      return index;
   }
   //------------------------------------------------------------
   // <T>存储对象在存储集合中。</T>
   TInt StorageRemove(T pItem){
      MO_ASSERT(pItem);
      // 查找存在和可放的
      TBool result = EFalse;
      TInt count = _pStorage->Count();
      for(TInt n = 0; n < count; n++){
         T pFind = _pStorage->Get(n);
         if(pFind == pItem){
            _pStorage->Set(n, NULL);
            result = ETrue;
         }
      }
      return result;
   }
   //------------------------------------------------------------
   // <T>从首部收集一个对象。</T>
   T AllocFirst(){
      T pItem = NULL;
      if(!_pFreeItems->IsEmpty()){
         pItem = _pFreeItems->Shift();
         _pUsingItems->Push(pItem);
      }else{
         MO_FATAL(TC("Free item is empty. alloc item failure. (total=%d, used=%d, free=%d)"),
               _pItems->Count(), _pUsingItems->Count(), _pFreeItems->Count());
      }
      return pItem;
   }
   //------------------------------------------------------------
   // <T>从尾部收集一个对象。</T>
   T AllocLast(){
      T pItem = NULL;
      if(!_pFreeItems->IsEmpty()){
         pItem = _pFreeItems->Pop();
         _pUsingItems->Push(pItem);
      }else{
         MO_FATAL(TC("Free item is empty. alloc item failure. (total=%d, used=%d, free=%d)"),
               _pItems->Count(), _pUsingItems->Count(), _pFreeItems->Count());
      }
      return pItem;
   }
   //------------------------------------------------------------
   // <T>释放一个对象到缓冲池最顶部。</T>
   void FreeFirst(T pItem){
      MO_ASSERT(pItem);
      if(!_pUsingItems->Contains(pItem)){
         MO_FATAL(TC("Free item is not exists collection. (total=%d, used=%d, free=%d)"),
               _pItems->Count(), _pUsingItems->Count(), _pFreeItems->Count());
      }
      _pUsingItems->Remove(pItem);
      _pFreeItems->Unshift(pItem);
   }
   //------------------------------------------------------------
   // <T>释放一个对象到缓冲池最底部。</T>
   void FreeLast(T pItem){
      MO_ASSERT(pItem);
      if(!_pUsingItems->Contains(pItem)){
         MO_FATAL(TC("Free item is not exists collection. (total=%d, used=%d, free=%d)"),
               _pItems->Count(), _pUsingItems->Count(), _pFreeItems->Count());
      }
      _pUsingItems->Remove(pItem);
      _pFreeItems->Push(pItem);
   }
   //------------------------------------------------------------
   // <T>移除一个对象。</T>
   void Remove(T pItem){
      MO_ASSERT(pItem);
      // 移除对象
      _pItems->Remove(pItem);
      // 移除使用中对象
      _pUsingItems->Remove(pItem);
      // 移除空闲中对象
      _pFreeItems->Remove(pItem);
      // 清空数组
      StorageRemove(pItem);
   }
   //------------------------------------------------------------
   // <T>清空所有对象。</T>
   void Clear(){
      _pItems->Clear();
      _pUsingItems->Clear();
      _pFreeItems->Clear();
      _pStorage->Clear();
   }
   //------------------------------------------------------------
   // <T>释放所有对象。</T>
   void Release(){
      // 删除所有子对象
      TListIteratorC<T> iterator = _pItems->IteratorC();
      while(iterator.Next()){
         T pItem = *iterator;
         MO_DELETE(pItem);
      }
      // 清空集合
      _pItems->Clear();
      _pUsingItems->Clear();
      _pFreeItems->Clear();
      _pStorage->Clear();
   }
};

//============================================================
// <T>变长对象缓冲池。</T>
//
// @tool
//============================================================
template <typename T>
class TStoragePool : public MStoragePool<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长缓冲池。</T>
   TStoragePool(){
   }
   //------------------------------------------------------------
   // <T>析构变长缓冲池。</T>
   ~TStoragePool(){
   }
};

//============================================================
// <T>变长对象缓冲池。</T>
//
// @class
//============================================================
template <typename T>
class FStoragePool :
      public FObject,
      public MStoragePool<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   FStoragePool(){
   }
   //------------------------------------------------------------
   // <T>析构变长读写链表。</T>
   ~FStoragePool(){
   }
};

//============================================================
// <T>对象入口基类。</T>
//
// @struct
//============================================================
class MO_CM_DECLARE MObjectEntry{
protected:
   TInt16 _entryType;
   TInt16 _entryFlag;
   MObjectEntry* _pEntryPrior;
   MObjectEntry* _pEntryNext;
public:
   //------------------------------------------------------------
   // <T>构造对象入口基类。</T>
   MObjectEntry(){
      _entryType = 0;
      _entryFlag = 0;
      MO_CLEAR(_pEntryPrior);
      MO_CLEAR(_pEntryNext);
   }
public:
   //------------------------------------------------------------
   // <T>获得入口类型。</T>
   MO_INLINE TInt EntryType(){
      return _entryType;
   }
   //------------------------------------------------------------
   // <T>设置入口类型。</T>
   MO_INLINE void SetEntryType(TInt entryType){
      _entryType = (TInt16)entryType;
   }
   //------------------------------------------------------------
   // <T>获得入口标志。</T>
   MO_INLINE TInt EntryFlag(){
      return _entryFlag;
   }
   //------------------------------------------------------------
   // <T>设置入口标志。</T>
   MO_INLINE void SetEntryFlag(TInt entryFlag){
      _entryFlag = (TInt16)entryFlag;
   }
   //------------------------------------------------------------
   // <T>获得入口前一个。</T>
   MO_INLINE MObjectEntry* EntryPrior(){
      return _pEntryPrior;
   }
   //------------------------------------------------------------
   // <T>设置入口前一个。</T>
   MO_INLINE void SetEntryPrior(MObjectEntry* pEntryPrior){
      _pEntryPrior = pEntryPrior;
   }
   //------------------------------------------------------------
   // <T>获得入口后一个。</T>
   MO_INLINE MObjectEntry* EntryNext(){
      return _pEntryNext;
   }
   //------------------------------------------------------------
   // <T>设置入口后一个。</T>
   MO_INLINE void SetEntryNext(MObjectEntry* pEntryNext){
      _pEntryNext = pEntryNext;
   }
public:
   //------------------------------------------------------------
   // <T>判断入口是否使用中。</T>
   MO_INLINE TBool IsEntryUsing(){
      return EInstancedFlag_Using == (_entryFlag & EInstancedFlag_Using);
   }
   //------------------------------------------------------------
   // <T>设置入口是否使用中。</T>
   MO_INLINE void SetEntryUsing(TBool value){
      if(value){
         _entryFlag |= EInstancedFlag_Using;
      }else{
         _entryFlag &= ~EInstancedFlag_Using;
      }
   }
};

//============================================================
// <T>只读对象入口迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TObjectEntryIteratorC{
public:
   typedef MObjectEntry SEntry;
protected:
   SEntry* _pStart;
   SEntry* _pCurrent;
public:
   //------------------------------------------------------------
   // <T>构造只读对象入口迭代器。</T>
   TObjectEntryIteratorC(){
      MO_CLEAR(_pStart);
      MO_CLEAR(_pCurrent);
   }
   //------------------------------------------------------------
   // <T>构造只读对象入口迭代器。</T>
   TObjectEntryIteratorC(const TObjectEntryIteratorC& iterator){
      _pStart = iterator._pStart;
      _pCurrent = iterator._pCurrent;
   }
   //------------------------------------------------------------
   // <T>构造只读对象入口迭代器。</T>
   TObjectEntryIteratorC(SEntry* pStart){
      _pStart = pStart;
      MO_CLEAR(_pCurrent);
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* operator *(){
      return _pCurrent;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* operator->(){
      return _pCurrent;
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE TBool IsEmpty(){
      return (NULL == _pCurrent);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasNext(){
      return (NULL == _pCurrent) ? (NULL != _pStart) : (NULL != _pCurrent->EntryNext());
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE TBool Next(){
      _pCurrent = (NULL == _pCurrent) ? _pStart : _pCurrent->EntryNext();
      return (NULL != _pCurrent);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasPrior(){
      return (NULL == _pCurrent) ? (NULL != _pStart) : (NULL != _pCurrent->EntryPrior());
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE TBool Prior(){
      _pCurrent = (NULL == _pCurrent) ? _pStart : _pCurrent->EntryPrior();
      return (NULL != _pCurrent);
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* Get(){
      return (T*)_pCurrent;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   template <typename C>
   MO_INLINE C Get(){
      return (C)_pCurrent;
   }
public:
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
      _pCurrent = NULL;
   }
};

//============================================================
// <T>对象入口缓冲池。</T>
//
// @history 130320 MAOCY 创建
//============================================================
template <typename T>
class MObjectEntryPool{
public:
   typedef TInt (*HComparer)(T source, T target);
   typedef MObjectEntry SEntry;
   typedef TObjectEntryIteratorC<T> TIteratorC;
protected:
   TInt32 _capacity;
   TInt32 _count;
   SEntry* _pFirst;
   SEntry* _pLast;
   SEntry* _pFree;
public:
   //------------------------------------------------------------
   // <T>构造对象入口缓冲池。</T>
   MObjectEntryPool(){
      _capacity = 0;
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pFree);
   }
   //------------------------------------------------------------
   // <T>析构对象入口缓冲池。</T>
   MO_ABSTRACT ~MObjectEntryPool(){
   }
protected:
   //------------------------------------------------------------
   // <T>收集一个未使用的节点。</T>
   MO_INLINE SEntry* EntryAlloc(){
      SEntry* pEntry = NULL;
      if(NULL == _pFree){
         pEntry = MO_CREATE(T);
         _capacity++;
      }else{
         pEntry = _pFree;
         _pFree = _pFree->EntryNext();
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个被使用的节点。</T>
   MO_INLINE void EntryFree(SEntry* pEntry){
      MO_ASSERT(pEntry);
      pEntry->SetEntryNext(_pFree);
      _pFree = pEntry;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入尾位置。</T>
   MO_INLINE void EntryPush(SEntry* pEntry){
      MO_ASSERT(pEntry);
      if(NULL == _pLast){
         _pFirst = pEntry;
      }else{
         _pLast->SetEntryNext(pEntry);
      }
      pEntry->SetEntryPrior(_pLast);
      pEntry->SetEntryNext(NULL);
      _pLast = pEntry;
      _count++;
   }
   //------------------------------------------------------------
   // <T>从链表上删除链表节点。</T>
   MO_INLINE void EntryRemove(SEntry* pEntry){
      MO_ASSERT(pEntry);
      // 处理前节点
      if(NULL == pEntry->EntryPrior()){
         _pFirst = pEntry->EntryNext();
      }else{
         pEntry->EntryPrior()->SetEntryNext(pEntry->EntryNext());
      }
      // 处理后节点
      if(NULL == pEntry->EntryNext()){
         _pLast = pEntry->EntryPrior();
      }else{
         pEntry->EntryNext()->SetEntryPrior(pEntry->EntryPrior());
      }
      // 设置内容
      _count--;
   }
public:
   //------------------------------------------------------------
   // <T>获得是否有使用对象。</T>
   MO_INLINE TBool HasUsing(){
      return _count > 0;
   }
   //------------------------------------------------------------
   // <T>获得使用对象个数。</T>
   MO_INLINE TInt UsingCount(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得首位置的数据。</T>
   MO_INLINE T* UsingFirst() const{
      return (T*)_pFirst;
   }
   //------------------------------------------------------------
   // <T>获得尾位置的数据。</T>
   MO_INLINE T* UsingLast() const{
      return (T*)_pLast;
   }
public:
   //------------------------------------------------------------
   // <T>获得使用中首位置的只读数据迭代器。</T>
   MO_INLINE TIteratorC UsingIteratorC() const{
      return TIteratorC(_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得使用中尾位置的只读数据迭代器。</T>
   MO_INLINE TIteratorC UsingLastIteratorC() const{
      return TIteratorC(_pLast);
   }
   //------------------------------------------------------------
   // <T>获得释放中的只读数据迭代器。</T>
   MO_INLINE TIteratorC FreeIteratorC() const{
      return TIteratorC(_pFree);
   }
public:
   //------------------------------------------------------------
   // <T>从当前数组中是否含有指定数据。</T>
   MO_INLINE TBool Contains(T* pItem) const{
      if(_count > 0){
         // 比较所有项目
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry == pItem){
               return ETrue;
            }
            pEntry = pEntry->EntryNext();
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>收集一个对象。</T>
   MO_INLINE T* Alloc(){
      // 收集一个未使用得节点
      SEntry* pEntry = EntryAlloc();
      // 设置为可用
      pEntry->SetEntryUsing(ETrue);
      // 将节点压入尾部
      EntryPush(pEntry);
      return (T*)pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个对象到缓冲池。</T>
   MO_INLINE TBool Free(T* pItem){
      MO_ASSERT(pItem);
      // 设置为不可用
      SEntry* pEntry = (SEntry*)pItem;
      pEntry->SetEntryUsing(EFalse);
      // 释放节点
      EntryRemove(pEntry);
      EntryFree(pEntry);
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>清空所有对象。</T>
   MO_INLINE void Clear(){
      // 释放链表
      if(_count > 0){
         _pLast->SetEntryNext(_pFree);
         _pFree = _pFirst;
      }
      // 重置属性
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
   }
   //------------------------------------------------------------
   // <T>释放所有对象。</T>
   MO_INLINE void Release(){
      // 清空所有对象
      Clear();
      // 释放所有对象
      SEntry* pEntry = _pFree;
      while(NULL != pEntry){
         SEntry* pNext = pEntry->EntryNext();
         MO_DELETE(pEntry);
         pEntry = pNext;
      }
      MO_CLEAR(_pFree);
      _capacity = 0;
   }
public:
   //------------------------------------------------------------
   // <T>显示跟踪信息。</T>
   MO_INLINE void Dump(){
      TInt entryCapacity = sizeof(SEntry);
      TInt entrySize = entryCapacity * _capacity;
      MO_DEBUG("Instanced pool. (capacity=%d, entry_capacity=%d, entry_size=%d, using_count=%d, free_count=%d)",
            _capacity, entryCapacity, entrySize, _count, _capacity - _count);
   }
};

//============================================================
// <T>对象入口缓冲池。</T>
//
// @tool
//============================================================
template <typename T>
class TObjectEntryPool :
      public MObjectEntryPool<T>{
public:
   //------------------------------------------------------------
   // <T>构造对象入口缓冲池。</T>
   TObjectEntryPool(){
   }
   //------------------------------------------------------------
   // <T>析构对象入口缓冲池。</T>
   MO_ABSTRACT ~TObjectEntryPool(){
   }
};

//============================================================
// <T>对象入口缓冲池。</T>
//
// @class
//============================================================
template <typename T>
class FObjectEntryPool :
      public FObject,
      public MObjectEntryPool<T>{
public:
   //------------------------------------------------------------
   // <T>构造对象入口缓冲池。</T>
   FObjectEntryPool(){
   }
   //------------------------------------------------------------
   // <T>析构对象入口缓冲池。</T>
   MO_ABSTRACT ~FObjectEntryPool(){
   }
};

//============================================================
// <T>实例池对象。</T>
//
// @struct
//============================================================
class MO_CM_DECLARE MInstancedPoolObject{
protected:
   TInt32 _objectIndex;
   TInt16 _objectType;
   TInt16 _objectFlag;
public:
   //------------------------------------------------------------
   // <T>构造实例池对象。</T>
   MInstancedPoolObject(){
      _objectIndex = -1;
      _objectType = 0;
      _objectFlag = 0;
   }
public:
   //------------------------------------------------------------
   // <T>获得索引。</T>
   MO_INLINE TInt ObjectIndex(){
      return _objectIndex;
   }
   //------------------------------------------------------------
   // <T>设置索引。</T>
   MO_INLINE void SetObjectIndex(TInt objectIndex){
      _objectIndex = (TInt32)objectIndex;
   }
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE TInt ObjectType(){
      return _objectType;
   }
   //------------------------------------------------------------
   // <T>设置类型。</T>
   MO_INLINE void SetObjectType(TInt objectType){
      _objectType = (TInt16)objectType;
   }
   //------------------------------------------------------------
   // <T>获得标志。</T>
   MO_INLINE TInt ObjectFlag(){
      return _objectFlag;
   }
   //------------------------------------------------------------
   // <T>设置标志。</T>
   MO_INLINE void SetObjectFlag(TInt objectFlag){
      _objectFlag = (TInt16)objectFlag;
   }
public:
   //------------------------------------------------------------
   // <T>判断对象是否使用中。</T>
   MO_INLINE TBool IsObjectUsing(){
      return EInstancedFlag_Using == (_objectFlag & EInstancedFlag_Using);
   }
   //------------------------------------------------------------
   // <T>设置对象是否使用中。</T>
   MO_INLINE void SetObjectUsing(TBool value){
      if(value){
         _objectFlag |= EInstancedFlag_Using;
      }else{
         _objectFlag &= ~EInstancedFlag_Using;
      }
   }
};

//============================================================
// <T>实例缓冲池节点。</T>
//
// @struct
//============================================================
template <typename T>
struct SInstancedPoolEntry{
public:
   TInt32 prior;
   TInt32 next;
   T item;
};

//============================================================
// <T>只读实体池迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TInstancedIteratorC{
public:
   typedef SInstancedPoolEntry<T> SEntry;
protected:
   TInt _start;
   TInt _index;
   SEntry* _pEntities;
public:
   //------------------------------------------------------------
   // <T>构造只读实体池迭代器。</T>
   TInstancedIteratorC(){
      _start = -1;
      _index = -1;
      MO_CLEAR(_pEntities);
   }
   //------------------------------------------------------------
   // <T>构造只读实体池迭代器。</T>
   TInstancedIteratorC(const TInstancedIteratorC& iterator){
      _start = iterator._start;
      _index = iterator._index;
      _pEntities = iterator._pEntities;
   }
   //------------------------------------------------------------
   // <T>构造只读实体池迭代器。</T>
   TInstancedIteratorC(TInt first, SEntry* pEntities){
      _start = first;
      _index = -1;
      _pEntities = pEntities;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* operator *(){
      SEntry& entry = _pEntities[_index];
      return &entry.item;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* operator->(){
      SEntry& entry = _pEntities[_index];
      return &entry.item;
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE TBool IsEmpty(){
      return (-1 == _index);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasNext(){
      return (-1 == _index) ? (-1 != _start) : (-1 != _pEntities[_index].next);
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE TBool Next(){
      _index = (-1 == _index) ? _start : _pEntities[_index].next;
      return (-1 != _index);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasPrior(){
      return (-1 == _index) ? (-1 != _start) : (-1 != _pEntities[_index].prior);
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE TBool Prior(){
      _index = (-1 == _index) ? _start : _pEntities[_index].prior;
      return (-1 != _index);
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* Get(){
      SEntry& entry = _pEntities[_index];
      return &entry.item;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   template <typename C>
   MO_INLINE C Get(){
      SEntry& entry = _pEntities[_index];
      return (C)&entry.item;
   }
public:
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
      _index = -1;
   }
};

//============================================================
// <T>实例缓冲池。</T>
//
// @history 130320 MAOCY 创建
//============================================================
template <typename T>
class MInstancedPool{
public:
   typedef TInt (*HComparer)(T source, T target);
   typedef SInstancedPoolEntry<T> SEntry;
   typedef TInstancedIteratorC<T> TIteratorC;
protected:
   TInt _capacity;
   TInt _count;
   TInt32 _first;
   TInt32 _last;
   TInt32 _free;
   SEntry* _pEntries;
public:
   //------------------------------------------------------------
   // <T>构造收集模块。</T>
   MInstancedPool(){
      _capacity = 0;
      _count = 0;
      _first = -1;
      _last = -1;
      _free = -1;
      MO_CLEAR(_pEntries);
   }
   //------------------------------------------------------------
   // <T>析构收集模块。</T>
   MO_ABSTRACT ~MInstancedPool(){
   }
public:
   //------------------------------------------------------------
   // <T>强制容量。</T>
   void ForceCapacity(TInt capacity){
      MO_ASSERT(capacity > 0);
      // 释放旧内存
      MO_FREE(_pEntries);
      // 收集内存
      _pEntries = MO_TYPES_ALLOC(SEntry, capacity);
      RTypes<SEntry>::Clear(_pEntries, capacity);
      // 放入自由池
      _count = 0;
      _first = -1;
      _last = -1;
      _free = -1;
      for(TInt n = 0; n < capacity; n++){
         SEntry& entry = _pEntries[n];
         // 构建对象
         new(&entry.item)T();
         // 设置属性
         entry.prior = -1;
         entry.item.SetObjectIndex(n);
         // 放入缓冲
         entry.next = _free;
         _free = n;
      }
      _capacity = capacity;
   }
   //------------------------------------------------------------
   // <T>扩充容量。</T>
   void ExtendCapacity(TInt count){
      TInt capacity = _capacity + count;
      // 收集内存
      SEntry* pEntries = MO_TYPES_ALLOC(SEntry, capacity);
      // 复制数据
      if(NULL != _pEntries){
         // 复制处理
         if(_capacity > 0){
            RTypes<SEntry>::Copy(pEntries, _pEntries, _capacity);
         }
         // 释放旧内存
         MO_FREE(_pEntries);
      }
      // 清空内存
      RTypes<SEntry>::Clear(pEntries + _capacity, count);
      // 放入自由池
      for(TInt n = 0; n < count; n++){
         SEntry& entry = pEntries[_capacity + n];
         // 构建对象
         new(&entry.item)T();
         // 设置属性
         entry.prior = -1;
         entry.item.SetObjectIndex(_capacity + n);
         // 放入缓冲
         entry.next = _free;
         _free = n;
      }
      _capacity = capacity;
   }
protected:
   //------------------------------------------------------------
   // <T>收集一个未使用的节点。</T>
   MO_INLINE SEntry& EntryAlloc(){
      // 查看未使用节点中是否有自由节点
      if(-1 == _free){
         MO_FATAL("Alloc entry failure. pool is full. (count=%d)", _count);
      }
      SEntry& entry = _pEntries[_free];
      _free = entry.next;
      return entry;
   }
   //------------------------------------------------------------
   // <T>释放一个被使用的节点。</T>
   MO_INLINE void EntryFree(SEntry& entry){
      entry.next = _free;
      _free = entry.item.ObjectIndex();
   }
   //------------------------------------------------------------
   // <T>将链表节点压入首位置。</T>
   MO_INLINE TInt EntryGet(TInt index) const{
      MO_ASSERT_RANGE(index, 0, _count);
      // 获得指定位置数据
      TInt find = _first;
      while(--index >= 0){
         find = _pEntries[find].next;
      }
      return find;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入尾位置。</T>
   MO_INLINE void EntryPush(SEntry& entry){
      TInt index = entry.item.ObjectIndex();
      if(-1 == _last){
         _first = index;
      }else{
         _pEntries[_last].next = index;
      }
      entry.prior = _last;
      entry.next = -1;
      _last = index;
      _count++;
   }
   //------------------------------------------------------------
   // <T>从链表上删除链表节点。</T>
   MO_INLINE void EntryRemove(SEntry& entry){
      // 处理前节点
      if(-1 == entry.prior){
         _first = entry.next;
      }else{
         _pEntries[entry.prior].next = entry.next;
      }
      // 处理后节点
      if(-1 == entry.next){
         _last = entry.prior;
      }else{
         _pEntries[entry.next].prior = entry.prior;
      }
      // 设置内容
      _count--;
   }
public:
   //------------------------------------------------------------
   // <T>获得容量。</T>
   MO_INLINE TInt Capacity(){
      return _capacity;
   }
   //------------------------------------------------------------
   // <T>获得是否有使用对象。</T>
   MO_INLINE TBool HasUsingItem(){
      return _count > 0;
   }
   //------------------------------------------------------------
   // <T>获得使用对象个数。</T>
   MO_INLINE TInt UsingCount(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得首位置的数据。</T>
   MO_INLINE T* UsingFirst() const{
      if(-1 != _first){
         SEntry& entry = _pEntries[_first];
         return &entry.item;
      }
      return NULL;
   }
   //------------------------------------------------------------
   // <T>获得尾位置的数据。</T>
   MO_INLINE T* UsingLast() const{
      if(-1 != _last){
         SEntry& entry = _pEntries[_last];
         return &entry.item;
      }
      return NULL;
   }
   //------------------------------------------------------------
   // <T>获得是否有空闲对象。</T>
   MO_INLINE TBool HasFreeItem(){
      return (_capacity - _count) > 0;
   }
   //------------------------------------------------------------
   // <T>获得空闲对象个数。</T>
   MO_INLINE TInt FreeCount(){
      return _capacity - _count;
   }
   //------------------------------------------------------------
   // <T>根据索引获得对象。</T>
   MO_INLINE T* Get(TInt index) const{
      MO_CHECK_RANGE(index, 0, _capacity, return NULL);
      SEntry& entry = _pEntries[index];
      return &entry.item;
   }
   //------------------------------------------------------------
   // <T>根据索引获得使用中的对象。</T>
   MO_INLINE T* FindUsing(TInt index) const{
      MO_CHECK_RANGE(index, 0, _capacity, return NULL);
      SEntry& entry = _pEntries[index];
      T* pItem = &entry.item;
      if(pItem->IsObjectUsing()){
         return pItem;
      }
      return NULL;
   }
   //------------------------------------------------------------
   // <T>根据索引获得使用中的对象。</T>
   MO_INLINE T* GetUsing(TInt index) const{
      MO_CHECK_RANGE(index, 0, _capacity, return NULL);
      SEntry& entry = _pEntries[index];
      T* pItem = &entry.item;
      if(pItem->IsObjectUsing()){
         return pItem;
      }
      MO_DEBUG_FATAL("Object is freeed. (index=%d)", index);
      return NULL;
   }
   //------------------------------------------------------------
   // <T>查找指定位置的数据。</T>
   MO_INLINE T* FindListByIndex(TInt index) const{
      MO_CHECK_RANGE(index, 0, _capacity, return NULL);
      TInt find = EntryGet(index);
      if(-1 != find){
         SEntry& entry = _pEntries[find];
         return &entry.item;
      }
      MO_DEBUG_FATAL("Can't find item on index. (index=%d)", index);
      return NULL;
   }
   //------------------------------------------------------------
   // <T>获得指定位置的数据。</T>
   MO_INLINE T* GetListByIndex(TInt index) const{
      MO_CHECK_RANGE(index, 0, _capacity, return NULL);
      TInt find = EntryGet(index);
      if(-1 != find){
         SEntry& entry = _pEntries[find];
         return &entry.item;
      }
      MO_DEBUG_FATAL("Can't find item on index. (index=%d)", index);
      return NULL;
   }
public:
   //------------------------------------------------------------
   // <T>获得使用中首位置的只读数据迭代器。</T>
   MO_INLINE TIteratorC UsingIteratorC() const{
      return TIteratorC(_first, _pEntries);
   }
   //------------------------------------------------------------
   // <T>获得使用中尾位置的只读数据迭代器。</T>
   MO_INLINE TIteratorC UsingLastIteratorC() const{
      return TIteratorC(_last, _pEntries);
   }
   //------------------------------------------------------------
   // <T>获得释放中的只读数据迭代器。</T>
   MO_INLINE TIteratorC FreeIteratorC() const{
      return TIteratorC(_free, _pEntries);
   }
public:
   //------------------------------------------------------------
   // <T>从当前数组中是否含有指定数据。</T>
   MO_INLINE TBool Contains(T* pItem) const{
      if(_count > 0){
         // 比较所有项目
         TInt index = _first;
         while(-1 != index){
            SEntry& entry = _pEntries[index];
            T* pFind = &entry.item;
            if(pFind == pItem){
               return ETrue;
            }
            index = entry.next;
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>收集一个对象。</T>
   MO_INLINE T* Alloc(){
      // 收集一个未使用得节点
      SEntry& entry = EntryAlloc();
      // 设置数据内容
      T* pItem = &entry.item;
      // 设置为可用
      pItem->SetObjectUsing(ETrue);
      // 将节点压入尾部
      EntryPush(entry);
      return pItem;
   }
   //------------------------------------------------------------
   // <T>释放一个对象到缓冲池。</T>
   MO_INLINE TBool Free(T* pItem){
      MO_ASSERT(pItem);
      // 检查存在性
#ifdef _MO_DEBUG
      if(!Contains(pItem)){
         MO_FATAL("Free item is not exists in using collection. (capacity=%d, using_count=%d, free_count=%d)",
               _capacity, _count, _capacity - _count);
         return EFalse;
      }
#endif // _MO_DEBUG
      // 设置为不可用
      pItem->SetObjectUsing(EFalse);
      // 释放节点
      TInt index = pItem->ObjectIndex();
      SEntry& entry = _pEntries[index];
      EntryRemove(entry);
      EntryFree(entry);
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>使用排序器对集合对象进行排序</T>
   void Sort(HComparer hComparer){
      MO_ASSERT(hComparer);
      //if((_count > 1) && (-1 != _first) && (-1 != _last)) {
      //   RInstancedList<SEntry, T>::Sort(_first, _first, _last, hComparer);
      //}
   }
   //------------------------------------------------------------
   // <T>清空所有对象。</T>
   MO_INLINE void Clear(){
      // 释放链表
      if(_count > 0){
         _pEntries[_last].next = _free;
         _free = _first;
      }
      // 重置属性
      _count = 0;
      _first = -1;
      _last = -1;
   }
   //------------------------------------------------------------
   // <T>释放所有对象。</T>
   MO_INLINE void Release(){
      // 释放所有对象
      for(TInt n = 0; n < _capacity; n++){
         SEntry& entry = _pEntries[n];
         T* pItem = &entry.item;
         pItem->~T();
      }
      MO_FREE(_pEntries);
      // 重置属性
      _capacity = 0;
      _count = 0;
      _first = -1;
      _last = -1;
      _free = -1;
   }
public:
   //------------------------------------------------------------
   // <T>显示跟踪信息。</T>
   MO_INLINE void Dump(){
      TInt entryCapacity = sizeof(SEntry);
      TInt entrySize = entryCapacity * _capacity;
      MO_DEBUG("Instanced pool. (capacity=%d, entry_capacity=%d, entry_size=%d, using_count=%d, free_count=%d)",
            _capacity, entryCapacity, entrySize, _count, _capacity - _count);
   }
};

//============================================================
// <T>变长实例缓冲池。</T>
//
// @tool
//============================================================
template <typename T>
class TInstancedPool :
      public MInstancedPool<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长实例缓冲池。</T>
   TInstancedPool(){
   }
   //------------------------------------------------------------
   // <T>析构变长实例缓冲池。</T>
   ~TInstancedPool(){
   }
};

//============================================================
// <T>变长实例缓冲池。</T>
//
// @class
//============================================================
template <typename T>
class FInstancedPool :
      public FObject,
      public MInstancedPool<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长实例缓冲池。</T>
   FInstancedPool(){
   }
   //------------------------------------------------------------
   // <T>析构变长实例缓冲池。</T>
   MO_ABSTRACT ~FInstancedPool(){
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_POOL_H__

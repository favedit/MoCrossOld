#ifndef __MO_CM_ENTRY_H__
#define __MO_CM_ENTRY_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>实例池对象。</T>
//
// @struct
//============================================================
enum EIteratorStatus{
   EIteratorStatus_Loop   = 0,
   EIteratorStatus_Finish = 1,
};

//============================================================
// <T>实例池对象。</T>
//
// @struct
//============================================================
enum EIteratorForward{
   EIteratorForward_Next  = 0,
   EIteratorForward_Prior = 1,
};

//============================================================
// <T>原子内存收集器接口。</T>
//
// @face
//============================================================
template <typename T>
class IAtomAllocator : public IDispose{
public:
   MO_VIRTUAL T* Alloc() = 0;
   MO_VIRTUAL void Free(T* pAtom) = 0;
   MO_VIRTUAL T* MultiAlloc(TUint count) = 0;
   MO_VIRTUAL void MultiFree(TAny* pAtoms) = 0;
   MO_VIRTUAL T** MultiPtrAlloc(TUint count) = 0;
   MO_VIRTUAL void MultiPtrFree(T** ppAtomss) = 0;
};

//============================================================
// <T>原子内存收集器。</T>
//
// @face
//============================================================
template <typename T>
class MAtomAllocator : public IAtomAllocator<T>{
public:
   //------------------------------------------------------------
   // 构造原子内存收集器
   MAtomAllocator(){
   }
   //------------------------------------------------------------
   // 析构原子内存收集器
   ~MAtomAllocator(){
   }
public:
   //------------------------------------------------------------
   // 收集一个原子
   MO_OVERRIDE T* Alloc(){
      return MO_TYPE_ALLOC(T);
   };
   //------------------------------------------------------------
   // 释放一个原子
   MO_OVERRIDE void Free(T* pAtom){
      MO_FREE(pAtom);
   }
   //------------------------------------------------------------
   // 收集多个原子
   MO_OVERRIDE T* MultiAlloc(TUint count){
      T* pAlloc = MO_TYPES_ALLOC(T, count);
      RTypes<T>::Clear(pAlloc, count);
      return pAlloc;
   }
   //------------------------------------------------------------
   // 释放多个原子
   MO_OVERRIDE void MultiFree(TAny* pAtoms){
      MO_FREE(pAtoms);
   }
   //------------------------------------------------------------
   // 收集多个原子指针
   MO_OVERRIDE T** MultiPtrAlloc(TUint count){
      T** ppAlloc = MO_TYPES_ALLOC(T*, count);
      RTypes<T*>::Clear(ppAlloc, count);
      return ppAlloc;
   }
   //------------------------------------------------------------
   // 释放多个原子指针
   MO_OVERRIDE void MultiPtrFree(T** pAtoms){
      MO_FREE(pAtoms);
   }
};

//============================================================
template <typename T>
class FAllocatorStack{
public:
   T* _pUnused;
public:
   //------------------------------------------------------------
   FAllocatorStack(){
      MO_CLEAR(_pUnused);
   }
   //------------------------------------------------------------
   ~FAllocatorStack(){
   }
public:
   //------------------------------------------------------------
   T* Alloc(){
      T* pValue = NULL;
      if(NULL != _pUnused){
         pValue = _pUnused;
         _pUnused = _pUnused->pNext;
         pValue->pNext = NULL;
      }
      return pValue;
   }
   //------------------------------------------------------------
   void Free(T* pValue){
      MO_ASSERT(pValue);
      pValue->pNext = _pUnused;
      _pUnused = pValue;
   }
};

//============================================================
// <T>只读节点迭代器。</T>
//
// @tool
// @history 100318 MAOCY 创建
//============================================================
template <typename E>
class MEntryIteratorC{
protected:
   E* _pStart;
   E* _pEntry;
protected:
   //------------------------------------------------------------
   // <T>内部初始化。</T>
   MO_INLINE void InnerInitialize(E* pEntry){
      _pStart = pEntry;
      _pEntry = NULL;
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE TBool IsEmpty(){
      return (NULL == _pEntry);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasNext(){
      return (NULL == _pEntry) ? (NULL != _pStart) : (NULL != _pEntry->pNext);
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE TBool Next(){
      _pEntry = (NULL == _pEntry) ? _pStart : _pEntry->pNext;
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasPrior(){
      return (NULL == _pEntry) ? (NULL != _pStart) : (NULL != _pEntry->pPrior);
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE TBool Prior(){
      _pEntry = (NULL == _pEntry) ? _pStart : _pEntry->pPrior;
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
      _pEntry = NULL;
   }
};

//============================================================
// <T>只读节点迭代器。</T>
//
// @tool
// @history 100202 MAOCY 创建
//============================================================
template <typename E>
class TEntryIteratorC : public MEntryIteratorC<E>{
public:
   //------------------------------------------------------------
   // 构造只读节点迭代器
   TEntryIteratorC(){
      Initialize((E)NULL);
   }
   //------------------------------------------------------------
   // 构造只读节点迭代器
   TEntryIteratorC(const TEntryIteratorC& iterator){
      Initialize(iterator._pStart);
   }
   //------------------------------------------------------------
   // 构造只读节点迭代器
   TEntryIteratorC(E pEntry){
      Initialize(pEntry);
   }
public:
   //------------------------------------------------------------
   // 判断当前节点是否相等
   inline TBool operator==(const TEntryIteratorC& iterator) const{
      return this->_pEntry == iterator._pEntry;
   }
   //------------------------------------------------------------
   // 判断当前节点是否不相等
   inline TBool operator!=(const TEntryIteratorC& iterator) const{
      return this->_pEntry != iterator._pEntry;
   }
   //------------------------------------------------------------
   // 获得当前节点
   inline E& operator *() const{
      return this->_pEntry;
   }
   //------------------------------------------------------------
   // 获得当前节点
   inline E operator ->() const{
      return this->_pEntry;
   }
public:
   //------------------------------------------------------------
   // 判断数据内容是否相等
   inline TBool Equals(E pEntry){
      return this->_pEntry == pEntry;
   }
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   inline E Get(){
      return this->_pEntry;
   }
};

//============================================================
// <T>节点收集器。</T>
// <P>节点上必须存在pNext指针。</P>
//
// @tool
// @param E:entry 节点类型
//============================================================
template <typename E>
class TEntryStorage{
protected:
   E* _pUnused;
public:
   //------------------------------------------------------------
   // 构造节点收集器
   TEntryStorage(){
      Initialize();
   }
   //------------------------------------------------------------
   // 析构节点收集器
   ~TEntryStorage(){
      Release();
   }
public:
   //------------------------------------------------------------
   // 初始化内容
   void Initialize(){
      _pUnused = NULL;
   }
   //------------------------------------------------------------
   // 释放内容
   void Release(){
      while(NULL != _pUnused){
         E* pEntry = _pUnused;
         MO_FREE(pEntry);
         _pUnused = pEntry;
      }
   }
public:
   //------------------------------------------------------------
   // 收集一个未使用的节点
   E* Alloc(){
      E* pEntry = NULL;
      // 查看未使用节点中是否有自由节点
      if(NULL != _pUnused){
         // 收集未使用的节点
         pEntry = _pUnused;
         _pUnused = _pUnused->pNext;
      }else{
         // 收集新节点
         pEntry = MO_TYPE_ALLOC(E);
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // 释放一个被使用的节点
   void Free(E* pEntry){
      MO_ASSERT(pEntry);
      pEntry->pNext = _pUnused;
      _pUnused = pEntry;
   }
   //------------------------------------------------------------
   // 释放一个被使用的节点
   void Free(E* pFirst, E* pLast){
      MO_ASSERT(pFirst);
      MO_ASSERT(pLast);
      pLast->pNext = _pUnused;
      _pUnused = pFirst;
   }
};

//============================================================
// <T>节点收集器。</T>
// <P>节点上必须存在pNext指针。</P>
//
// @tool
// @param E:entry 节点类型
// @param C:capacity 最大容量
//============================================================
template <typename E, TInt C>
class TFixEntryStorage{
protected:
   E* _pUnused;
   TInt _alloc;
   E _entries[C];
public:
   //------------------------------------------------------------
   // 构造节点收集器
   TFixEntryStorage(){
      _pUnused = NULL;
      _alloc = 0;
   }
public:
   //------------------------------------------------------------
   // 初始化内容
   void Initialize(){
      _pUnused = NULL;
      _alloc = 0;
   }
public:
   //------------------------------------------------------------
   // 收集一个未使用的节点
   E* Alloc(){
      E* pEntry = NULL;
      // 查看未使用节点中是否有自由节点
      if(NULL != _pUnused){
         // 收集未使用的节点
         pEntry = _pUnused;
         _pUnused = _pUnused->pNext;
      }else{
         // 收集新节点
         MO_ASSERT(_alloc < C);
         pEntry = &_entries[_alloc++];
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // 释放一个被使用的节点
   void Free(E* pEntry){
      MO_ASSERT(pEntry);
      pEntry->pNext = _pUnused;
      _pUnused = pEntry;
   }
   //------------------------------------------------------------
   // 释放一个被使用的节点
   void Free(E* pFirst, E* pLast){
      MO_ASSERT(pFirst);
      MO_ASSERT(pLast);
      pLast->pNext = _pUnused;
      _pUnused = pFirst;
   }
};


//============================================================
// <T>节点收集器。</T>
// <P>节点上必须存在pNext指针。</P>
//
// @tool
// @param E:entry 节点类型
//============================================================
template <typename E>
class TEntryFlatStorage : public TEntryStorage<E>{
protected:
   E** _pMemory;
public:
   //------------------------------------------------------------
   // 构造节点收集器
   TEntryFlatStorage(){
      Initialize();
   }
   //------------------------------------------------------------
   // 析构节点收集器
   ~TEntryFlatStorage(){
      Release();
   }
public:
   //------------------------------------------------------------
   // 初始化内容
   void Initialize(){
      TEntryStorage<E>::Initialize();
      _pMemory = NULL;
   }
   //------------------------------------------------------------
   // 释放内容
   void Release(){
      TEntryStorage<E>::Release();
      MO_FREE(_pMemory);
   }
public:
   //------------------------------------------------------------
   // 调整节点容量大小
   E** FlatAlloc(TInt size){
      E** ppEntities = MO_TYPES_ALLOC(E*, size);
      RTypes<E*>::Clear(ppEntities, size);
      return ppEntities;
   }
   //------------------------------------------------------------
   // 调整节点容量大小
   void FlatFree(E** pPtr){
      MO_FREE(pPtr);
   }
};

//============================================================
// <T>节点收集器。</T>
// <P>节点上必须存在pNext指针。</P>
//
// @tool
// @param E:entry 节点类型
// @param C:capacity 最大容量
//============================================================
template <typename E, TInt C>
class TFixEntryFlatStorage : public TFixEntryStorage<E, C>{
protected:
   E* _pEntries[C];
public:
   //------------------------------------------------------------
   // 构造节点收集器
   TFixEntryFlatStorage(){
      RTypes<E*>::Clear(_pEntries, C);
   }
public:
   //------------------------------------------------------------
   // 初始化内容
   void Initialize(){
      TFixEntryStorage<E, C>::Initialize();
      RTypes<E*>::Clear(_pEntries, C);
   }
public:
   //------------------------------------------------------------
   // 调整节点容量大小
   E** FlatAlloc(TInt size){
      MO_ASSERT(size < C);
      return _pEntries;
   }
   //------------------------------------------------------------
   // 调整节点容量大小
   void FlatFree(E** pPtr){
      MO_FREE(pPtr);
   }
};

//============================================================
// <T>只读有序双链表。</T>
// <P>E{E pPrior, E pNext, ...}</P>
//
// @base
//============================================================
template <typename E>
class MEntriesC{
protected:
   TInt _count;
   E* _pFirst;
   E* _pLast;
protected:
   //------------------------------------------------------------
   // <T>内部初始化。</T>
   MO_INLINE void InnerInitialize(){
      _count = 0;
      _pFirst = NULL;
      _pLast = NULL;
   }
protected:
   //------------------------------------------------------------
   // <T>将链表节点压入首位置。</T>
   MO_INLINE E* EntryGet(TInt index) const{
      MO_ASSERT_RANGE(index, 0, this->_count);
      // 获得第一个
      if(0 == index){
         return this->_pFirst;
      }
      // 获得指定位置数据
      E* pEntry = this->_pFirst;
      while(--index >= 0){
         pEntry = pEntry->pNext;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入首位置。</T>
   MO_INLINE void EntryUnshift(E* pEntry){
      MO_ASSERT(pEntry);
      if(NULL == _pFirst){
         _pLast = pEntry;
      }else{
         _pFirst->pPrior = pEntry;
      }
      pEntry->pPrior = NULL;
      pEntry->pNext = _pFirst;
      _pFirst = pEntry;
      _count++;
   }
   //------------------------------------------------------------
   // <T>弹出链表的首节点。</T>
   MO_INLINE E* EntryShift(){
      E* pEntry = _pFirst;
      if(NULL != pEntry){
         // 读指针指向下一个位置
         _pFirst = _pFirst->pNext;
         if(NULL == _pFirst){
            _pLast = NULL;
         }else{
            _pFirst->pPrior = NULL;
         }
         // 设置内容
         _count--;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入尾位置。</T>
   MO_INLINE void EntryPush(E* pEntry){
      MO_ASSERT(pEntry);
      if(NULL == _pLast){
         _pFirst = pEntry;
      }else{
         _pLast->pNext = pEntry;
      }
      pEntry->pPrior = _pLast;
      pEntry->pNext = NULL;
      _pLast = pEntry;
      _count++;
   }
   //------------------------------------------------------------
   // <T>弹出链表的尾节点。</T>
   MO_INLINE E* EntryPop(){
      E* pEntry = _pLast;
      if(NULL != pEntry){
         _pLast = _pLast->pPrior;
         if(NULL == _pLast){
            _pFirst = NULL;
         }else{
            _pLast->pNext = NULL;
         }
         // 设置内容
         _count--;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>在指定链表节点后插入一个新链表节点。</T>
   MO_INLINE void EntryInsert(E* pPrior, E* pEntry){
      MO_ASSERT(pPrior);
      MO_ASSERT(pEntry);
      E* pNext = pPrior->pNext;
      pPrior->pNext = pEntry;
      pEntry->pPrior = pPrior;
      if(NULL == pNext){
         pEntry->pNext = NULL;
         _pLast = pEntry;
      }else{
         pNext->pPrior = pEntry;
         pEntry->pNext = pNext;
      }
   }
   //------------------------------------------------------------
   // <T>从链表上删除链表节点。</T>
   MO_INLINE void EntryRemove(E* pEntry){
      MO_ASSERT(pEntry);
      E* pPrior = pEntry->pPrior;
      E* pNext = pEntry->pNext;
      // 处理前节点
      if(NULL == pPrior){
         _pFirst = pNext;
      }else{
         pPrior->pNext = pNext;
      }
      // 处理后节点
      if(NULL == pNext){
         _pLast = pPrior;
      }else{
         pNext->pPrior = pPrior;
      }
      // 设置内容
      _count--;
   }
   //------------------------------------------------------------
   // <T>清空所有使用中的节点。</T>
   MO_INLINE void EntryClear(){
      // 清空内容
      _count = 0;
      _pFirst = NULL;
      _pLast = NULL;
   }
};

//============================================================
// <T>只读节点迭代器。</T>
//
// @tool
// @history 100202 MAOCY 创建
//============================================================
template <typename E>
class MLinkedIteratorC{
protected:
   E _pStart;
   E _pEntry;
protected:
   //------------------------------------------------------------
   // 内部初始化
   inline void Initialize(E pEntry){
      _pStart = pEntry;
      _pEntry = NULL;
   }
public:
   //------------------------------------------------------------
   // 获得当前节点
   inline E& operator *() const{
      return this->_pEntry;
   }
   //------------------------------------------------------------
   // 获得当前节点
   inline E operator ->() const{
      return this->_pEntry;
   }
public:
   //------------------------------------------------------------
   // 重置位置
   inline void Reset(){
      _pEntry = NULL;
   }
   //------------------------------------------------------------
   // 判断是否存在下一个位置
   inline TBool HasNext(){
      return (NULL == _pEntry) ? (NULL != _pStart) : (NULL == _pEntry->pNext);
   }
   //------------------------------------------------------------
   // 移动到下一个位置
   inline TBool Next(){
      _pEntry = (NULL == _pEntry) ? _pStart : _pEntry->pNext;
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // 判断是否存在下一个位置
   inline TBool HasPrior(){
      return (NULL == _pEntry) ? (NULL != _pStart) : (NULL == _pEntry->pPrior);
   }
   //------------------------------------------------------------
   // 移动到上一个位置
   inline TBool Prior(){
      _pEntry = (NULL == _pEntry) ? _pStart : _pEntry->pPrior;
      return (NULL != _pEntry);
   }
};

//============================================================
// <T>只读节点迭代器。</T>
//
// @tool
// @history 100202 MAOCY 创建
//============================================================
template <typename E>
class TLinkedIteratorC : public MLinkedIteratorC<E>{
public:
   //------------------------------------------------------------
   // 构造只读节点迭代器
   TLinkedIteratorC(){
      Initialize((E)NULL);
   }
   //------------------------------------------------------------
   // 构造只读节点迭代器
   TLinkedIteratorC(const MLinkedIteratorC<E>& iterator){
      Initialize(iterator._pStart);
   }
   //------------------------------------------------------------
   // 构造只读节点迭代器
   TLinkedIteratorC(E pEntry){
      Initialize(pEntry);
   }
public:
   //------------------------------------------------------------
   // 判断当前节点是否相等
   inline TBool operator==(const MLinkedIteratorC<E>& iterator) const{
      return this->_pEntry == iterator._pEntry;
   }
   //------------------------------------------------------------
   // 判断当前节点是否不相等
   inline TBool operator!=(const MLinkedIteratorC<E>& iterator) const{
      return this->_pEntry != iterator._pEntry;
   }
   //------------------------------------------------------------
   // 获得当前节点
   inline operator const E() const{
      return this->_pEntry;
   }
public:
   //------------------------------------------------------------
   // 判断数据内容是否相等
   inline TBool Equals(E pEntry){
      return this->_pEntry == pEntry;
   }
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   inline E Get(){
      return this->_pEntry;
   }
};

//============================================================
// <T>只读有序双链表</T>
// <P>E{E pPrior, E pNext}</P>
//
// @base
//============================================================
template <typename E>
class MLinkedEntryC{
protected:
   TInt _count;
   E _pFirst;
   E _pLast;
   E _pUnused;
protected:
   //------------------------------------------------------------
   // 内部初始化
   inline void Initialize(){
      _count = 0;
      _pFirst = NULL;
      _pLast = NULL;
      _pUnused = NULL;
   }
protected:
   //------------------------------------------------------------
   // 将链表节点压入首位置
   inline void EntryUnshift(E pEntry){
      MO_ASSERT(pEntry);
      // 压入首位置
      if(NULL == _pFirst){
         _pLast = pEntry;
      }else{
         _pFirst->pPrior = pEntry;
      }
      pEntry->pNext = _pFirst;
      _pFirst = pEntry;
      // 设置内容
      _count++;
      pEntry->pPrior = NULL;
   }
   //------------------------------------------------------------
   // 弹出链表的首节点
   inline E EntryShift(){
      E pEntry = _pFirst;
      if(NULL != _pFirst){
         // 读指针指向下一个位置
         _pFirst = _pFirst->pNext;
         if(NULL == _pFirst){
            _pLast = NULL;
         }else{
            _pFirst->pPrior = NULL;
         }
         // 设置内容
         _count--;
         EntryFree(pEntry);
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // 将链表节点压入尾位置
   inline void EntryPush(E pEntry){
      MO_ASSERT(pEntry);
      if(NULL == _pLast){
         _pFirst = pEntry;
      }else{
         _pLast->pNext = pEntry;
      }
      pEntry->pPrior = _pLast;
      _pLast = pEntry;
      // 设置内容
      _count++;
      pEntry->pNext = NULL;
   }
   //------------------------------------------------------------
   // 弹出链表的尾节点
   inline E EntryPop(){
      E pEntry = _pLast;
      if(NULL != _pLast){
         _pLast = _pLast->pPrior;
         if(NULL == _pLast){
            _pFirst = NULL;
         }else{
            _pLast->pNext = NULL;
         }
         // 设置内容
         _count--;
         EntryFree(pEntry);
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // 在指定链表节点后插入一个新链表节点
   inline void EntryInsert(E pPrior, E pEntry){
      MO_ASSERT(pPrior);
      MO_ASSERT(pEntry);
      E pNext = pPrior->pNext;
      pPrior->pNext = pEntry;
      pEntry->pPrior = pPrior;
      if(NULL == pNext){
         pEntry->pNext = NULL;
         _pLast = pEntry;
      }else{
         pNext->pPrior = pEntry;
         pEntry->pNext = pNext;
      }
   }
   //------------------------------------------------------------
   // 从链表上删除链表节点
   inline void EntryRemove(E pEntry){
      MO_ASSERT(pEntry);
      E pPrior = pEntry->pPrior;
      E pNext = pEntry->pNext;
      // 处理前节点
      if(NULL == pPrior){
         _pFirst = pNext;
      }else{
         pPrior->pNext = pNext;
      }
      // 处理后节点
      if(NULL == pNext){
         _pLast = pPrior;
      }else{
         pNext->pPrior = pPrior;
      }
      // 设置内容
      _count--;
      EntryFree(pEntry);
   }
   //------------------------------------------------------------
   // 将节点放入未使用位置
   inline void EntryFree(E pEntry){
      MO_ASSERT(pEntry);
      pEntry->pNext = _pUnused;
      _pUnused = pEntry;
   }
   //------------------------------------------------------------
   // 清空所有使用中的节点，放入未使用链表中循环使用
   inline void EntryClear(){
      if(_count > 0){
         // 将使用节点全部放入回收节点
         _pLast->pNext = _pUnused;
         _pUnused = _pFirst;
         // 清空内所有内容
         _count = 0;
         _pFirst = NULL;
         _pLast = NULL;
      }
   }
};

//============================================================
// <T>只读有序双链表</T>
// <P>T{T pPrior, T pNext}</P>
//
// @base
//============================================================
template <typename E>
class MEntryListC : public MLinkedEntryC<E>{
public:
   typedef MLinkedEntryC<E> MLinkedC;
   typedef TEntryIteratorC<E> TIteratorC;
public:
   //------------------------------------------------------------
   // 判断当前链表和指定链表中所有数据内容是否相等。
   TBool operator==(const MEntryListC<E>& list) const{
      return Equals(list);
   }
   //------------------------------------------------------------
   // 判断当前链表和指定链表中所有数据内容是否不相等。
   TBool operator!=(const MEntryListC<E>& list) const{
      return !Equals(list);
   }
public:
   //------------------------------------------------------------
   // 获得是否为空
   TBool IsEmpty() const{
      return (0 == this->_count);
   }
   //------------------------------------------------------------
   // 获得总数
   TInt Count() const{
      return this->_count;
   }
   //------------------------------------------------------------
   // 判断当前链表和指定链表是否相等
   TBool Equals(const MEntryListC<E>& list) const{
      // 比较数量
      if(this->_count != list._count){
         return EFalse;
      }
      // 比较所有项目
      E pEntry = this->_pFirst;
      TIteratorC iterator = list.IteratorC();
      while(iterator.Next()){
         if(pEntry != iterator.Get()){
            return EFalse;
         }
         pEntry = pEntry->pNext;
      }
      return ETrue;
   }
   //------------------------------------------------------------
   // 获得首位置的数据
   E First() const{
      return this->_pFirst;
   }
   //------------------------------------------------------------
   // 获得尾位置的数据
   E Last() const{
      return this->_pLast;
   }
   //------------------------------------------------------------
   // 获得首位置的只读数据迭代器
   TIteratorC IteratorC() const{
      return TIteratorC(this->_pFirst);
   }
   //------------------------------------------------------------
   // 获得尾位置的只读数据迭代器
   TIteratorC LastIteratorC() const{
      return TIteratorC(this->_pLast);
   }
   //------------------------------------------------------------
   // 获得指定位置的数据
   E Get(TInt index) const{
      MO_ASSERT_RANGE(index, 0, this->_count);
      // 获得第一个
      if(0 == index){
         return this->_pFirst;
      }
      // 获得指定位置数据
      E pEntry = this->_pFirst;
      while(--index >= 0){
         pEntry = pEntry->pNext;
      }
      return pEntry->value;
   }
   //------------------------------------------------------------
   // 在指定链表迭代器中查找指定数据
   TBool Find(const TIteratorC& iterator, E pEntry) const{
      while(iterator.Next()){
         if(iterator.Equals(pEntry)){
            return ETrue;
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // 在当前链表中查找指定数据
   TIteratorC FindC(E pEntry) const{
      TIteratorC& iterator = IteratorC();
      if(Find(iterator, pEntry)){
         return iterator;
      }
      return LastIteratorC();
   }
};

//============================================================
// <T>有序双链表</T>
// <P>T{T pPrior, T pNext}</P>
//
// @base
//============================================================
template <typename E>
class MEntryList : public MEntryListC<E>{
public:
   typedef TEntryIteratorC<E> TIteratorC;
public:
   //------------------------------------------------------------
   MEntryList(){
   }
   //------------------------------------------------------------
   ~MEntryList(){
   }
public:
   //------------------------------------------------------------
   // 接受一个链表到当前链表中。
   void Assign(const MEntryList<E>& list){
      Clear();
      Append(list);
   }
   //------------------------------------------------------------
   // 追加一个链表到当前链表中。
   void Append(const MEntryListC<E>& list){
      TIteratorC iterator = list.IteratorC();
      while(iterator.Next()){
         Push(iterator.Get());
      }
   }
   //------------------------------------------------------------
   // 将数据压入首位置
   void Unshift(E entry){
      EntryUnshift(entry);
   }
   //------------------------------------------------------------
   // 弹出链表的首数据
   E Shift(){
      return MEntryListC<E>::EntryShift();
   }
   //------------------------------------------------------------
   // 将数据压入尾位置
   void Push(E entry){
      EntryPush(entry);
   }
   //------------------------------------------------------------
   // 弹出链表的尾数据
   E Pop(){
      return MEntryListC<E>::EntryPop();
   }
   //------------------------------------------------------------
   // 从链表上删除数据
   void Remove(E entry){
      EntryRemove(entry);
   }
   //------------------------------------------------------------
   // 清空所有数据
   void Clear(){
      MEntryListC<E>::EntryClear();
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_ENTRY_H__


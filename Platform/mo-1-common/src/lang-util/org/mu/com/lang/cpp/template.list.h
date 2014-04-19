#ifndef __MO_CM_LIST_H__
#define __MO_CM_LIST_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

#define MO_COMPLIST_THRESHOLD 8

MO_NAMESPACE_BEGIN

//============================================================
// <T>链表的节点定义。</T>
//
// @struct
//============================================================
template <typename T>
struct SListEntry{
   /// @attribute 前一个入口
   SListEntry* priorPtr;
   /// @attribute 下一个入口
   SListEntry* nextPtr;
   /// @attribute 数据内容
   T value;
};

//============================================================
// <T>内存排序类。</T>
//
// @tool
// @class E 入口类型
// @class T 内容类型
// @history 100318 MAOCY 创建
//============================================================
template <typename E, typename T>
class RList{
public:
   typedef TInt (*HComparer)(T source, T target);
protected:
   //------------------------------------------------------------
   // <T>双向链表的快速排序。</T>
   static MO_INLINE E* InnerPartion(E* pLeft, E* pRight, HComparer hComparer = NULL){
      T value = pLeft->value;
      while(pLeft != pRight){
         //从后面往前换
         while((pLeft != pRight) && (hComparer(pRight->value, value) >= 0)){
            pRight = pRight->priorPtr;
         }
         // 交换位置
         T temp1 = pLeft->value;
         pLeft->value = pRight->value;
         pRight->value = temp1;
         //从前往后换
         while((pLeft != pRight) && (hComparer(pLeft->value, value) <= 0)){
            pLeft = pLeft->nextPtr;
         }
         // 交换位置
         T temp2 = pLeft->value;
         pLeft->value = pRight->value;
         pRight->value = temp2;
      }
      return pLeft;
   }
public:
   //------------------------------------------------------------
   // <T>双向链表的快速排序。</T>
   static void Sort(E* pLeft, E* pRight, HComparer hComparer = NULL){
      E* pTemp = NULL;
      pTemp = InnerPartion(pLeft, pRight, hComparer);
      if(pLeft != pTemp){
         Sort(pLeft, pTemp->priorPtr, hComparer);
      }
      if(pRight != pTemp){
         Sort(pTemp->nextPtr, pRight, hComparer);
      }
   }
};

//============================================================
// <T>只读链表的迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TListIteratorC{
public:
   typedef SListEntry<T> SEntry;
protected:
   SEntry* _pStart;
   SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TListIteratorC(){
      MO_CLEAR(_pStart);
      MO_CLEAR(_pEntry);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TListIteratorC(const TListIteratorC& iterator){
      _pStart = iterator._pStart;
      _pEntry = iterator._pEntry;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TListIteratorC(SEntry* pEntry){
      _pStart = pEntry;
      _pEntry = NULL;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T& operator *() const{
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T operator->() const{
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
public:
   //------------------------------------------------------------
   // <T>获得开始节点。</T>
   MO_INLINE SEntry* Start() const{
      return _pStart;
   }
   //------------------------------------------------------------
   // <T>获得当前节点。</T>
   MO_INLINE SEntry* Entry() const{
      return _pEntry;
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
      return (NULL == _pEntry) ? (NULL != _pStart) : (NULL != _pEntry->nextPtr);
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE TBool Next(){
      _pEntry = (NULL == _pEntry) ? _pStart : _pEntry->nextPtr;
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasPrior(){
      return (NULL == _pEntry) ? (NULL != _pStart) : (NULL != _pEntry->priorPtr);
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE TBool Prior(){
      _pEntry = (NULL == _pEntry) ? _pStart : _pEntry->priorPtr;
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
      _pEntry = NULL;
   }
public:
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE TBool Equals(T value){
      MO_ASSERT(_pEntry);
      return _pEntry->value == value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T Get(){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
};

//============================================================
// <T>可写链表的迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TListIterator{
public:
   typedef SListEntry<T> SEntry;
protected:
   SEntry* _pStart;
   SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TListIterator(){
      MO_CLEAR(_pStart);
      MO_CLEAR(_pEntry);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TListIterator(const TListIteratorC<T>& iterator){
      _pStart = iterator.Start();
      _pEntry = iterator.Entry();
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TListIterator(const TListIterator& iterator){
      _pStart = iterator._pStart;
      _pEntry = iterator._pEntry;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TListIterator(SEntry* pEntry){
      _pStart = pEntry;
      _pEntry = NULL;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T& operator *() const{
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T operator->() const{
      MO_ASSERT(_pEntry);
      return _pEntry->value;
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
      return (NULL == _pEntry) ? (NULL != _pStart) : (NULL != _pEntry->nextPtr);
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE TBool Next(){
      _pEntry = (NULL == _pEntry) ? _pStart : _pEntry->nextPtr;
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasPrior(){
      return (NULL == _pEntry) ? (NULL != _pStart) : (NULL != _pEntry->priorPtr);
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE TBool Prior(){
      _pEntry = (NULL == _pEntry) ? _pStart : _pEntry->priorPtr;
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
      _pEntry = NULL;
   }
public:
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE TBool Equals(T value){
      MO_ASSERT(_pEntry);
      return _pEntry->value == value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T Get(){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>设置当前位置的数据名称。</T>
   MO_INLINE void Set(T value) const{
      MO_ASSERT(_pEntry);
      _pEntry->value = value;
   }
};

//============================================================
// <T>双向链表。</T>
//
// @history 091208 MAOCY 创建
//============================================================
template <typename T>
class MList{
public:
   typedef TInt (*HComparer)(T source, T target);
   typedef SListEntry<T> SEntry;
   typedef TListIteratorC<T> TIteratorC;
   typedef TListIterator<T> TIterator;
protected:
   TInt _count;
   SEntry* _pFirst;
   SEntry* _pLast;
   SEntry* _pUnused;
public:
   //------------------------------------------------------------
   // <T>构造双向链表。</T>
   MList(){
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pUnused);
   }
   //------------------------------------------------------------
   // <T>析构双向链表。</T>
   MO_ABSTRACT ~MList(){
   }
protected:
   MO_VIRTUAL SEntry* EntryCreate() = 0;
   MO_VIRTUAL void EntryRelease(SEntry* pEntry) = 0;
protected:
{source_entry_list}
public:
   //------------------------------------------------------------
   // <T>判断当前链表和指定链表中所有数据内容是否相等。</T>
   MO_INLINE TBool operator==(const MList<T>& list) const{
      return Equals(&list);
   }
   //------------------------------------------------------------
   // <T>判断当前链表和指定链表中所有数据内容是否不相等。</T>
   MO_INLINE TBool operator!=(const MList<T>& list) const{
      return !Equals(&list);
   }
   //------------------------------------------------------------
   // <T>追加一个内容到当前链表中。</T>
   MO_INLINE void operator+=(T value){
      Push(value);
   }
   //------------------------------------------------------------
   // <T>从当前链表中删除一个内容。</T>
   MO_INLINE void operator-=(T value){
      Remove(value);
   }
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   MO_INLINE void operator+=(const MList<T>* pList){
      Append(pList);
   }
public:
   //------------------------------------------------------------
   // <T>获得是否为空。</T>
   MO_INLINE TBool IsEmpty() const{
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>获得总数。</T>
   MO_INLINE TInt Count() const{
      return _count;
   }
   //------------------------------------------------------------
   // <T>判断当前链表和指定链表是否相等。</T>
   TBool Equals(const MList<T>* pList) const{
      MO_ASSERT(pList);
      // 比较数量
      if(_count != pList->Count()){
         return EFalse;
      }
      // 比较所有项目
      SEntry* pEntry = _pFirst;
      TIteratorC iterator = pList->IteratorC();
      while((NULL != pEntry) && iterator.Next()){
         if(!iterator.Equals(pEntry->value)){
            return EFalse;
         }
         pEntry = pEntry->nextPtr;
      }
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>获得首位置的数据。</T>
   T First() const{
      return (NULL != _pFirst) ? _pFirst->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得尾位置的数据。</T>
   T Last() const{
      return (NULL != _pLast) ? _pLast->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得首位置的只读数据迭代器。</T>
   TIteratorC IteratorC() const{
      return TIteratorC(_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得尾位置的只读数据迭代器。</T>
   TIteratorC LastIteratorC() const{
      return TIteratorC(_pLast);
   }
   //------------------------------------------------------------
   // <T>从当前数组中是否含有指定数据。</T>
   TBool Contains(T value) const{
      if(_count > 0){
         // 比较所有项目
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == value){
               return ETrue;
            }
            pEntry = pEntry->nextPtr;
         }
      }
      return EFalse;
   }
public:
   //------------------------------------------------------------
   // <T>查找指定位置的数据。</T>
   T FindByIndex(TInt index) const{
      SEntry* pEntry = EntryGet(index);
      return (NULL != pEntry) ? pEntry->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得指定位置的数据。</T>
   T GetByIndex(TInt index) const{
      SEntry* pEntry = EntryGet(index);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>在当前链表中查找指定数据。</T>
   TIteratorC FindC(T value) const{
      if(_count > 0){
         // 比较所有项目
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == value){
               return TIteratorC(pEntry);
            }
            pEntry = pEntry->nextPtr;
         }
      }
      return TIteratorC();
   }
   //------------------------------------------------------------
   // <T>在指定链表迭代器中查找指定数据。</T>
   TBool FindC(TIteratorC& iterator, T value) const{
      while(iterator.Next()){
         if(iterator.Equals(value)){
            return ETrue;
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>复制对象到外部数组。</T>
   TInt CopyTo(T* pBuffer, TInt capacity) const{
      TInt count = MO_LIB_MIN(_count, capacity);
      if(count > 0){
         TInt n = 0;
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            pBuffer[n++] = pEntry->value;
            pEntry = pEntry->nextPtr;
         }
      }
      return count;
   }
public:
   //------------------------------------------------------------
   // <T>获得首位置的只读数据迭代器。</T>
   TIterator Iterator(){
      return TIterator(_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得尾位置的只读数据迭代器。</T>
   TIterator LastIterator(){
      return TIterator(_pLast);
   }
   //------------------------------------------------------------
   // <T>在当前链表中查找指定数据。</T>
   TIterator Find(T value){
      if(_count > 0){
         SEntry* pFind = _pFirst;
         while(NULL != pFind){
            if(pFind->value == value){
               return TIterator(pFind);
            }
            pFind = pFind->nextPtr;
         }
      }
      return TIterator();
   }
public:
   //------------------------------------------------------------
   // <T>接受一个链表到当前链表中。</T>
   void Assign(const MList<T>* pList){
      MO_ASSERT(pList);
      Clear();
      Append(pList);
   }
   //------------------------------------------------------------
   // <T>接受一个链表到当前链表中。</T>
   void Assign(TIteratorC& iterator){
      Clear();
      Append(iterator);
   }
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   void Append(const MList<T>* pList){
      MO_ASSERT(pList);
      TIteratorC iterator = pList->IteratorC();
      while(iterator.Next()){
         Push(*iterator);
      }
   }
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   void Append(TIteratorC& iterator){
      while(iterator.Next()){
         Push(*iterator);
      }
   }
   //------------------------------------------------------------
   // <T>将数据压入首位置。</T>
   void Unshift(T value){
      // 收集一个未使用得节点
      SEntry* pEntry = EntryAlloc();
      // 将节点压入尾部
      pEntry->value = value;
      EntryUnshift(pEntry);
   }
   //------------------------------------------------------------
   // <T>弹出链表的首数据。</T>
   T Shift(){
      SEntry* pEntry = EntryShift();
      MO_ASSERT(pEntry);
      T value = pEntry->value;
      EntryFree(pEntry);
      return value;
   }
   //------------------------------------------------------------
   // <T>将数据压入尾位置。</T>
   void Push(T value){
      // 收集一个未使用得节点
      SEntry* pEntry = EntryAlloc();
      // 将节点压入尾部
      pEntry->value = value;
      EntryPush(pEntry);
   }
   //------------------------------------------------------------
   // <T>弹出链表的尾数据。</T>
   T Pop(){
      SEntry* pEntry = EntryPop();
      MO_ASSERT(pEntry);
      T value = pEntry->value;
      EntryFree(pEntry);
      return value;
   }
   //------------------------------------------------------------
   // <T>从链表上删除数据。</T>
   void Remove(T value){
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            SEntry* pNext = pEntry->nextPtr;
            if(pEntry->value == value){
               // 移除节点
               EntryRemove(pEntry);
               // 释放节点
               EntryFree(pEntry);
            }
            pEntry = pNext;
         }
      }
   }
   //------------------------------------------------------------
   // <T>使用排序器对集合对象进行排序</T>
   void Sort(HComparer hComparer){
      MO_ASSERT(hComparer);
      if((_count > 1) && _pFirst && _pLast) {
         RList<SEntry, T>::Sort(_pFirst, _pLast, hComparer);
      }
   }
   //------------------------------------------------------------
   // <T>清空所有数据。</T>
   void Clear(){
      if(_count > 0){
         // 重用节点
         _pLast->nextPtr = _pUnused;
         _pUnused = _pFirst;
      }
      // 重置内容
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
   }
   //------------------------------------------------------------
   // <T>释放处理。</T>
   void Release(){
      // 将所有节点设置为未使用
      if(_count > 0){
         _pLast->nextPtr = _pUnused;
         _pUnused = _pFirst;
      }
      // 删除所有节点
      SEntry* pFind = _pUnused;
      while(NULL != pFind){
         SEntry* pNext = pFind->nextPtr;
         EntryRelease(pFind);
         pFind = pNext;
      }
      // 设置内容
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pUnused);
   }
};

//============================================================
// <T>变长栈读写链表。</T>
//
// @tool
//============================================================
template <typename T>
class TList : public MList<T>{
public:
   typedef SListEntry<T> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   TList(){
   }
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   TList(const TList<T>& list){
      Append(&list);
   }
   //------------------------------------------------------------
   // <T>析构变长读写链表。</T>
   MO_ABSTRACT ~TList(){
      this->Release();
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      SEntry* pEntry = MO_TYPE_ALLOC(SEntry);
      MO_ASSERT(pEntry);
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_FREE(pEntry);
   }
public:
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   MO_INLINE void operator=(const MList<T>& list){
      Assign(&list);
   }
};

//============================================================
// <T>变长堆读写链表。</T>
//
// @tool
//============================================================
template <typename T>
class FList :
      public FObject,
      public MList<T>{
public:
   typedef SListEntry<T> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   FList(){
   }
   //------------------------------------------------------------
   // <T>析构变长读写链表。</T>
   MO_ABSTRACT ~FList(){
      this->Release();
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      SEntry* pEntry = MO_TYPE_ALLOC(SEntry);
      MO_ASSERT(pEntry);
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_FREE(pEntry);
   }
public:
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   MO_INLINE void operator=(const MList<T>& list){
      this->Assign(list.IteratorC());
   }
};

//============================================================
// <T>变长堆读写链表。</T>
//
// @tool
//============================================================
template <typename T>
class FFreeList :
      public FFree,
      public MList<T>{
public:
   typedef SListEntry<T> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   FFreeList(){
   }
   //------------------------------------------------------------
   // <T>析构变长读写链表。</T>
   MO_ABSTRACT ~FFreeList(){
      this->Release();
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      SEntry* pEntry = MO_MEM_CREATE(SEntry);
      MO_ASSERT(pEntry);
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_MEM_DELETE(pEntry);
   }
public:
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   MO_INLINE void operator=(const MList<T>& list){
      this->Assign(list.IteratorC());
   }
};

//============================================================
// <T>定长读写链表。</T>
//
// @template
// @type T 节点类型
// @param S 数据长度
//============================================================
template<typename T, TInt S>
class TFixList : public MList<T>{
public:
   typedef SListEntry<T> SEntry;
protected:
   TInt _alloc;
   SEntry _entries[S];
public:
   //------------------------------------------------------------
   // <T>构造定长读写链表。</T>
   TFixList(){
      _alloc = 0;
   }
   //------------------------------------------------------------
   // <T>构造定长读写链表。</T>
   TFixList(const MList<T>& list){
      _alloc = 0;
      this->Assign(&list);
   }
   //------------------------------------------------------------
   // <T>析构定长读写链表。</T>
   MO_ABSTRACT ~TFixList(){
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      MO_ASSERT(_alloc < S);
      return &_entries[_alloc++];
   }
   //------------------------------------------------------------
   // <T>释放一个节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
   }
public:
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   MO_INLINE void operator=(const MList<T>& list){
      this->Assign(&list);
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_LIST_H__

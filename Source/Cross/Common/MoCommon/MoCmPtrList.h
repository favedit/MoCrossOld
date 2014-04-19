#ifndef __MO_CM_PTR_LIST_H__
#define __MO_CM_PTR_LIST_H__

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
struct SPtrListEntry
{
public:
   /// @attribute 前一个入口
   SPtrListEntry* priorPtr;
   /// @attribute 下一个入口
   SPtrListEntry* nextPtr;
   /// @attribute 数据内容
   GPtr<T> value;
public:
   //------------------------------------------------------------
   // <T>构造链表的节点定义。</T>
   MO_INLINE SPtrListEntry(){
      MO_CLEAR(priorPtr);
      MO_CLEAR(nextPtr);
   }
public:
   //------------------------------------------------------------
   // <T>重置内容。</T>
   MO_INLINE void Reset(){
      MO_CLEAR(priorPtr);
      MO_CLEAR(nextPtr);
      value = NULL;
   }
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
class RPtrList{
public:
   typedef TInt (*HComparer)(T* source, T* target);
protected:
   //------------------------------------------------------------
   // <T>双向链表的快速排序。</T>
   static MO_INLINE E* InnerPartion(E* pLeft, E* pRight, HComparer hComparer = NULL){
      T* value = pLeft->value;
      while(pLeft != pRight){
         //从后面往前换
         while((pLeft != pRight) && (hComparer(pRight->value, value) >= 0)){
            pRight = pRight->priorPtr;
         }
         // 交换位置
         T* temp1 = pLeft->value;
         pLeft->value = pRight->value;
         pRight->value = temp1;
         //从前往后换
         while((pLeft != pRight) && (hComparer(pLeft->value, value) <= 0)){
            pLeft = pLeft->nextPtr;
         }
         // 交换位置
         T* temp2 = pLeft->value;
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
class TPtrListIteratorC{
public:
   typedef SPtrListEntry<T> SEntry;
protected:
   SEntry* _pStart;
   SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TPtrListIteratorC(){
      MO_CLEAR(_pStart);
      MO_CLEAR(_pEntry);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TPtrListIteratorC(const TPtrListIteratorC& iterator){
      _pStart = iterator._pStart;
      _pEntry = iterator._pEntry;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TPtrListIteratorC(SEntry* pEntry){
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
   MO_INLINE T* Get(){
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
class TPtrListIterator{
public:
   typedef SPtrListEntry<T> SEntry;
protected:
   SEntry* _pStart;
   SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TPtrListIterator(){
      MO_CLEAR(_pStart);
      MO_CLEAR(_pEntry);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TPtrListIterator(const TPtrListIteratorC<T>& iterator){
      _pStart = iterator.Start();
      _pEntry = iterator.Entry();
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TPtrListIterator(const TPtrListIterator& iterator){
      _pStart = iterator._pStart;
      _pEntry = iterator._pEntry;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TPtrListIterator(SEntry* pEntry){
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
   MO_INLINE TBool Equals(T* pValue){
      MO_ASSERT(_pEntry);
      return _pEntry->value == pValue;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* Get(){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>设置当前位置的数据名称。</T>
   MO_INLINE void Set(T* pValue) const{
      MO_ASSERT(_pEntry);
      _pEntry->value = pValue;
   }
};

//============================================================
// <T>双向链表。</T>
//
// @history 091208 MAOCY 创建
//============================================================
template <typename T>
class MPtrList{
public:
   typedef TInt (*HComparer)(T source, T target);
   typedef SPtrListEntry<T> SEntry;
   typedef TPtrListIteratorC<T> TIteratorC;
   typedef TPtrListIterator<T> TIterator;
protected:
   TInt _count;
   SEntry* _pFirst;
   SEntry* _pLast;
   SEntry* _pUnused;
public:
   //------------------------------------------------------------
   // <T>构造双向链表。</T>
   MPtrList(){
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pUnused);
   }
   //------------------------------------------------------------
   // <T>析构双向链表。</T>
   MO_ABSTRACT ~MPtrList(){
   }
protected:
   MO_VIRTUAL SEntry* EntryCreate() = 0;
   MO_VIRTUAL void EntryRelease(SEntry* pEntry) = 0;
protected:
   //------------------------------------------------------------
   // <T>收集一个未使用的节点。</T>
   MO_INLINE SEntry* EntryAlloc(){
      SEntry* pEntry = NULL;
      // 查看未使用节点中是否有自由节点
      if(NULL != _pUnused){
         // 收集未使用的节点
         pEntry = _pUnused;
         _pUnused = _pUnused->nextPtr;
      }else{
         // 收集新节点
         pEntry = this->EntryCreate();
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个被使用的节点。</T>
   MO_INLINE void EntryFree(SEntry* pEntry){
      MO_ASSERT(pEntry);
      pEntry->Reset();
      pEntry->nextPtr = _pUnused;
      _pUnused = pEntry;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入首位置。</T>
   MO_INLINE SEntry* EntryGet(TInt index) const{
      MO_ASSERT_RANGE(index, 0, _count);
      // 获得第一个
      if(0 == index){
         return _pFirst;
      }
      // 获得指定位置数据
      SEntry* pEntry = _pFirst;
      while(--index >= 0){
         pEntry = pEntry->nextPtr;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入首位置。</T>
   MO_INLINE void EntryUnshift(SEntry* pEntry){
      MO_ASSERT(pEntry);
      if(NULL == _pFirst){
         _pLast = pEntry;
      }else{
         _pFirst->priorPtr = pEntry;
      }
      pEntry->priorPtr = NULL;
      pEntry->nextPtr = _pFirst;
      _pFirst = pEntry;
      _count++;
   }
   //------------------------------------------------------------
   // <T>弹出链表的首节点。</T>
   MO_INLINE SEntry* EntryShift(){
      SEntry* pEntry = _pFirst;
      if(NULL != pEntry){
         // 读指针指向下一个位置
         _pFirst = _pFirst->nextPtr;
         if(NULL == _pFirst){
            _pLast = NULL;
         }else{
            _pFirst->priorPtr = NULL;
         }
         // 设置内容
         _count--;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入尾位置。</T>
   MO_INLINE void EntryPush(SEntry* pEntry){
      MO_ASSERT(pEntry);
      if(NULL == _pLast){
         _pFirst = pEntry;
      }else{
         _pLast->nextPtr = pEntry;
      }
      pEntry->priorPtr = _pLast;
      pEntry->nextPtr = NULL;
      _pLast = pEntry;
      _count++;
   }
   //------------------------------------------------------------
   // <T>弹出链表的尾节点。</T>
   MO_INLINE SEntry* EntryPop(){
      SEntry* pEntry = _pLast;
      if(NULL != pEntry){
         _pLast = _pLast->priorPtr;
         if(NULL == _pLast){
            _pFirst = NULL;
         }else{
            _pLast->nextPtr = NULL;
         }
         // 设置内容
         _count--;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>在指定链表节点后插入一个新链表节点。</T>
   MO_INLINE void EntryInsert(SEntry* pPrior, SEntry* pEntry){
      MO_ASSERT(pPrior);
      MO_ASSERT(pEntry);
      SEntry* pNext = pPrior->nextPtr;
      pPrior->nextPtr = pEntry;
      pEntry->priorPtr = pPrior;
      if(NULL == pNext){
         pEntry->nextPtr = NULL;
         _pLast = pEntry;
      }else{
         pNext->priorPtr = pEntry;
         pEntry->nextPtr = pNext;
      }
   }
   //------------------------------------------------------------
   // <T>从链表上删除链表节点。</T>
   MO_INLINE void EntryRemove(SEntry* pEntry){
      MO_ASSERT(pEntry);
      SEntry* pPrior = pEntry->priorPtr;
      SEntry* pNext = pEntry->nextPtr;
      // 处理前节点
      if(NULL == pPrior){
         _pFirst = pNext;
      }else{
         pPrior->nextPtr = pNext;
      }
      // 处理后节点
      if(NULL == pNext){
         _pLast = pPrior;
      }else{
         pNext->priorPtr = pPrior;
      }
      // 设置内容
      _count--;
   }
public:
   //------------------------------------------------------------
   // <T>判断当前链表和指定链表中所有数据内容是否相等。</T>
   MO_INLINE TBool operator==(const MPtrList<T>& list) const{
      return Equals(&list);
   }
   //------------------------------------------------------------
   // <T>判断当前链表和指定链表中所有数据内容是否不相等。</T>
   MO_INLINE TBool operator!=(const MPtrList<T>& list) const{
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
   MO_INLINE void operator+=(const MPtrList<T>* pPtrList){
      Append(pPtrList);
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
   TBool Equals(const MPtrList<T>* pPtrList) const{
      MO_ASSERT(pPtrList);
      // 比较数量
      if(_count != pPtrList->Count()){
         return EFalse;
      }
      // 比较所有项目
      SEntry* pEntry = _pFirst;
      TIteratorC iterator = pPtrList->IteratorC();
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
   MO_INLINE T* First() const{
      return (NULL != _pFirst) ? _pFirst->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得尾位置的数据。</T>
   MO_INLINE T* Last() const{
      return (NULL != _pLast) ? _pLast->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得首位置的只读数据迭代器。</T>
   MO_INLINE TIteratorC IteratorC() const{
      return TIteratorC(_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得尾位置的只读数据迭代器。</T>
   MO_INLINE TIteratorC LastIteratorC() const{
      return TIteratorC(_pLast);
   }
   //------------------------------------------------------------
   // <T>从当前数组中是否含有指定数据。</T>
   TBool Contains(T* pValue) const{
      if(_count > 0){
         // 比较所有项目
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == pValue){
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
   T* FindByIndex(TInt index) const{
      SEntry* pEntry = EntryGet(index);
      return (NULL != pEntry) ? pEntry->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得指定位置的数据。</T>
   T* GetByIndex(TInt index) const{
      SEntry* pEntry = EntryGet(index);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>在当前链表中查找指定数据。</T>
   TIteratorC FindC(T* pValue) const{
      if(_count > 0){
         // 比较所有项目
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == pValue){
               return TIteratorC(pEntry);
            }
            pEntry = pEntry->nextPtr;
         }
      }
      return TIteratorC();
   }
   //------------------------------------------------------------
   // <T>在指定链表迭代器中查找指定数据。</T>
   TBool FindC(TIteratorC& iterator, T* pValue) const{
      while(iterator.Next()){
         if(iterator.Equals(pValue)){
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
   MO_INLINE TIterator Iterator(){
      return TIterator(_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得尾位置的只读数据迭代器。</T>
   MO_INLINE TIterator LastIterator(){
      return TIterator(_pLast);
   }
   //------------------------------------------------------------
   // <T>在当前链表中查找指定数据。</T>
   TIterator Find(T* pValue){
      if(_count > 0){
         SEntry* pFind = _pFirst;
         while(NULL != pFind){
            if(pFind->value == pValue){
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
   void Assign(const MPtrList<T>* pPtrList){
      MO_ASSERT(pPtrList);
      Clear();
      Append(pPtrList);
   }
   //------------------------------------------------------------
   // <T>接受一个链表到当前链表中。</T>
   void Assign(TIteratorC& iterator){
      Clear();
      Append(iterator);
   }
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   void Append(const MPtrList<T>* pPtrList){
      MO_ASSERT(pPtrList);
      TIteratorC iterator = pPtrList->IteratorC();
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
   void Unshift(T* pValue){
      // 收集一个未使用得节点
      SEntry* pEntry = EntryAlloc();
      // 将节点压入尾部
      pEntry->value = pValue;
      EntryUnshift(pEntry);
   }
   //------------------------------------------------------------
   // <T>弹出链表的首数据。</T>
   T* Shift(){
      SEntry* pEntry = EntryShift();
      MO_ASSERT(pEntry);
      T* pValue = pEntry->value;
      EntryFree(pEntry);
      return pValue;
   }
   //------------------------------------------------------------
   // <T>将数据压入尾位置。</T>
   void Push(T* pValue){
      // 收集一个未使用得节点
      SEntry* pEntry = EntryAlloc();
      // 将节点压入尾部
      pEntry->value = pValue;
      EntryPush(pEntry);
   }
   //------------------------------------------------------------
   // <T>弹出链表的尾数据。</T>
   T* Pop(){
      SEntry* pEntry = EntryPop();
      MO_ASSERT(pEntry);
      T* pValue = pEntry->value;
      EntryFree(pEntry);
      return pValue;
   }
   //------------------------------------------------------------
   // <T>从链表上删除数据。</T>
   void Remove(T* pValue){
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            SEntry* pNext = pEntry->nextPtr;
            if(pEntry->value == pValue){
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
         RPtrList<SEntry, T>::Sort(_pFirst, _pLast, hComparer);
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
class GPtrList : public MPtrList<T>{
public:
   typedef SPtrListEntry<T> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   GPtrList(){
   }
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   GPtrList(const GPtrList<T>& list){
      Append(&list);
   }
   //------------------------------------------------------------
   // <T>析构变长读写链表。</T>
   MO_ABSTRACT ~GPtrList(){
      this->Release();
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      SEntry* pEntry = MO_TYPE_CREATE(SEntry);
      MO_ASSERT(pEntry);
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_DELETE(pEntry);
   }
public:
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   MO_INLINE void operator=(const MPtrList<T>& list){
      Assign(&list);
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_PTR_LIST_H__

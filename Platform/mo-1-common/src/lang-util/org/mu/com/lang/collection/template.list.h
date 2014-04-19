#ifndef __MO_CM_LIST_H__
#define __MO_CM_LIST_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_INTERFACE_H__
#include "MoCmInterface.h"
#endif // __MO_CM_INTERFACE_H__

#define MO_CM_LIST_SORT_THRESHOLD 7

MO_NAMESPACE_BEGIN

//============================================================
// <T>链表的节点定义。</T>
//
// @struct
//============================================================
template <typename T>
struct SListEntry{
public:
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
// @history 100318 MAOCY 创建
//============================================================
template <typename E, typename T>
class RList{
public:
   typedef TInt (*HComparer)(const T source, const T target, TAny* pCondition);
public:
   //------------------------------------------------------------
   // <T>用于元素比较少的双向链表的插入排序。</T>
   static void InsertSort(E* pHead, E* pTail, HComparer hComparer, TAny* pParams) {
      if(pTail && pHead && pHead != pTail) {
         T temp;
         for(E *p = pHead->nextPtr; p != pTail->nextPtr; p = p->nextPtr) {
            for(E *pp = p; pp != pHead; pp = pp->priorPtr) {
               if(hComparer(pp->value, pp->priorPtr->value, pParams) < 0) {
                  temp = pp->value;
                  pp->value = pp->priorPtr->value;
                  pp->priorPtr->value = temp;
               }else{
                  break;
               }
            }
         }
      }
   }
   //------------------------------------------------------------
   // <T>用于元素比较多的双向链表的快速排序。</T>
   static void QuickSort(E* pHead, E* pTail, HComparer hComparer, TAny* pParams){
      if(pTail && pHead && pHead != pTail) {
         T temp;
         T data = pHead->value;
         E* pLast = pHead;
         for(E *p = pHead->nextPtr; p != pTail->nextPtr; p = p->nextPtr) {
            if(hComparer(p->value, data, pParams) < 0) {
               pLast = pLast->nextPtr;
               temp = pLast->value;
               pLast->value = p->value;
               p->value = temp;
            }
         }
         temp = pLast->value;
         pLast->value = pHead->value;
         pHead->value = temp;
         if(pLast != pHead){
            QuickSort(pHead, pLast->priorPtr, hComparer, pParams);
         }
         if(pLast != pTail){
            QuickSort(pLast->nextPtr, pTail, hComparer, pParams);
         }
      }
   }
};

//============================================================
// <T>只读链表迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TListIteratorC{
public:
   typedef SListEntry<T> SEntry;
protected:
   // @attribute 开始节点
   const SEntry* _pFirst;
   // @attribute 结束节点
   const SEntry* _pLast;
   // @attribute 当前节点
   const SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造只读链表迭代器。</T>
   TListIteratorC(){
   }
   //------------------------------------------------------------
   // <T>构造只读链表迭代器。</T>
   TListIteratorC(const TListIteratorC<T>& iterator){
      _pFirst = iterator._pFirst;
      _pLast = iterator._pLast;
      _pEntry = iterator._pEntry;
   }
   //------------------------------------------------------------
   // <T>构造只读链表迭代器。</T>
   TListIteratorC(const SEntry* pFirst, const SEntry* pLast, const SEntry* pEntry = NULL){
      _pFirst = pFirst;
      _pLast = pLast;
      _pEntry = pEntry;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const T& operator *() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const T operator->() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE( TBool IsEmpty() ){
      return (NULL == _pEntry);
   }
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE( TBool Equals(T value) ){
      MO_ASSERT(_pEntry);
      return _pEntry->value == value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( T GetC() ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   template <typename C>
   MO_INLINE( C GetC() ){
      MO_ASSERT(_pEntry);
      return (C)_pEntry->value;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasNext() ){
      return (NULL == _pEntry) ? (NULL != _pFirst) : (NULL != _pEntry->nextPtr);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasPrior() ){
      return (NULL == _pEntry) ? (NULL != _pFirst) : (NULL != _pEntry->priorPtr);
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE( TBool Next() ){
      _pEntry = (NULL == _pEntry) ? _pFirst : _pEntry->nextPtr;
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE( TBool Prior() ){
      _pEntry = (NULL == _pEntry) ? _pFirst : _pEntry->priorPtr;
      return (NULL != _pEntry);
   }
};

//============================================================
// <T>链表迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TListIterator{
public:
   typedef SListEntry<T> SEntry;
protected:
   // @attribute 开始节点
   SEntry* _pFirst;
   // @attribute 结束节点
   SEntry* _pLast;
   // @attribute 当前节点
   SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造链表迭代器。</T>
   TListIterator(){
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pEntry);
   }
   //------------------------------------------------------------
   // <T>构造链表迭代器。</T>
   TListIterator(const TListIterator& iterator){
      _pFirst = iterator._pFirst;
      _pLast = iterator._pLast;
      _pEntry = iterator._pEntry;
   }
   //------------------------------------------------------------
   // <T>构造链表迭代器。</T>
   TListIterator(SEntry* pFirst, SEntry* pLast, SEntry* pEntry = NULL){
      _pFirst = pFirst;
      _pLast = pLast;
      _pEntry = pEntry;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const T& operator *() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const T operator->() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE( TBool IsEmpty() ){
      return (NULL == _pEntry);
   }
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE( TBool Equals(T value) ){
      MO_ASSERT(_pEntry);
      return _pEntry->value == value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( T& Get() ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   template <typename C>
   MO_INLINE( C& Get() ){
      MO_ASSERT(_pEntry);
      return (C)_pEntry->value;
   }
   //------------------------------------------------------------
   // <T>设置当前位置的数据名称。</T>
   MO_INLINE( void Set(const T& value) ){
      MO_ASSERT(_pEntry);
      _pEntry->value = value;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasNext() ){
      return (NULL == _pEntry) ? (NULL != _pFirst) : (NULL != _pEntry->nextPtr);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasPrior() ){
      return (NULL == _pEntry) ? (NULL != _pFirst) : (NULL != _pEntry->priorPtr);
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE( TBool Next() ){
      _pEntry = (NULL == _pEntry) ? _pFirst : _pEntry->nextPtr;
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE( TBool Prior() ){
      _pEntry = (NULL == _pEntry) ? _pFirst : _pEntry->priorPtr;
      return (NULL != _pEntry);
   }
};

//============================================================
// <T>双向链表。</T>
//
// @history 120616 MAOCY 创建
//============================================================
template <typename T>
class MList{
public:
   // @type 比较函数
   typedef TInt (*HComparer)(const T source, const T target, TAny* pCondition);
   // @type 入口类型
   typedef SListEntry<T> SEntry;
   // @type 只读迭代类型
   typedef TListIteratorC<T> TIteratorC;
   // @type 迭代类型
   typedef TListIterator<T> TIterator;
protected:
   // @attribute 个数
   TInt _count;
   // @attribute 首指针
   SEntry* _pFirst;
   // @attribute 尾指针
   SEntry* _pLast;
   // @attribute 未使用指针
   SEntry* _pFree;
public:
   //------------------------------------------------------------
   // <T>构造双向链表。</T>
   MList(){
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pFree);
   }
   //------------------------------------------------------------
   // <T>析构双向链表。</T>
   ~MList(){
      InnerRelease();
   }
protected:
   //------------------------------------------------------------
   // <T>创建一个入口对象。</T>
   MO_INLINE( SEntry* InnerEntryCreate() ){
      return MO_MEM_CREATE(SEntry);
   }
   //------------------------------------------------------------
   // <T>删除一个入口对象。</T>
   MO_INLINE( void InnerEntryDelete(SEntry* pEntry) ){
      MO_MEM_DELETE(pEntry);
   }
{source_inner}
{source_1}
};

//============================================================
// <T>变长栈读写链表。</T>
//
// @tool
//============================================================
template <typename T>
class TList : public MList<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   TList(){
   }
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   TList(const MList<T>& list){
      this->Append(&list);
   }
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   TList(const TList<T>& list){
      this->Append(&list);
   }
};

//============================================================
// <T>变长堆读写链表。</T>
//
// @tool
//============================================================
template <typename T>
class FList :
      public FFree,
      public MList<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   FList(){
   }
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   FList(const MList<T>& list){
      this->Append(&list);
   }
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   FList(const FList<T>& list){
      this->Append(&list);
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
class TFixList{
public:
   // @type 比较函数
   typedef TInt (*HComparer)(const T source, const T target, TAny* pCondition);
   // @type 入口类型
   typedef SListEntry<T> SEntry;
   // @type 只读迭代类型
   typedef TListIteratorC<T> TIteratorC;
   // @type 迭代类型
   typedef TListIterator<T> TIterator;
protected:
   // @attribute 个数
   TInt _count;
   // @attribute 首指针
   SEntry* _pFirst;
   // @attribute 尾指针
   SEntry* _pLast;
   // @attribute 未使用指针
   SEntry* _pFree;
   // @attribute 使用个数
   TInt _allocCount;
   // @attribute 内存数据
   TByte memory[sizeof(SEntry) * S];
public:
   //------------------------------------------------------------
   // <T>构造定长读写链表。</T>
   TFixList(){
   }
   //------------------------------------------------------------
   // <T>构造定长读写链表。</T>
   TFixList(const MList<T>& list){
      this->Assign(&list);
   }
protected:
   //------------------------------------------------------------
   // <T>创建一个入口对象。</T>
   MO_INLINE( SEntry* InnerEntryCreate() ){
      // 查看未使用节点中是否有自由节点
      MO_ASSERT(_allocCount + 1 < S);
      // 收集新节点
      SEntry* pEntries = (SEntry*)memory;
      SEntry* pEntry = pEntries[_allocCount++];
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>删除一个入口对象。</T>
   MO_INLINE( void InnerEntryDelete(SEntry* pEntry) ){
   }
{source_inner}
{source_2}
};

MO_NAMESPACE_END

#endif // __MO_CM_LIST_H__

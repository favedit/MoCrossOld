#ifndef __MO_CM_SHAREDENTRY_H__
#define __MO_CM_SHAREDENTRY_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_SHARED_H__
#include "MoCmShared.h"
#endif // __MO_CM_SHARED_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>共享链表节点收集器头定义。</T>
//
// @struct
// @history 100326 MAOCY 创建
//============================================================
struct SSharedEntryAllocatorHead{
   TInt count;
   TInt unused;
};

//============================================================
// <T>共享链表节点收集器。</T>
//
// @template
// @type E entry 节点类型
// @history 100326 MAOCY 创建
//============================================================
template <typename E>
class FSharedEntryAllocator : public MShared{
protected:
   TInt _capacity;
   SSharedEntryAllocatorHead* _gHead;
   E* _gEntries;
public:
   //------------------------------------------------------------
   // 构造只读迭代器
   FSharedEntryAllocator(){
      _capacity = 0;
      _gHead = NULL;
      _gEntries = NULL;
   }
public:
   //------------------------------------------------------------
   // 设置容量
   void SetCapacity(TInt capacity){
      _capacity = capacity;
   }
   //------------------------------------------------------------
   // 计算共享内存大小
   TSize SharedCapacity(){
      TSize size = sizeof(SSharedEntryAllocatorHead);
      size += sizeof(E) * _capacity;
      return size;
   }
   //------------------------------------------------------------
   // 分配共享内存
   void SharedLink(TShareSegment segment){
      _gHead = segment.TypeAlloc<SSharedEntryAllocatorHead>();
      _gEntries = segment.TypeAlloc<E>(_capacity);
      MShared::SharedLink(segment);
   }
   //------------------------------------------------------------
   // 共享内存初始化
   void SharedInitialize(){
      _gHead->count = 0;
      if(_capacity > 0){
         for(TInt n=0; n<_capacity; n++){
            if(0 == n){
               _gEntries[n].prior = -1;
               _gEntries[n].next = n + 1;
            }else if(_capacity - 1 == n){
               _gEntries[n].prior = n - 1;
               _gEntries[n].next = -1;
            }else{
               _gEntries[n].prior = n - 1;
               _gEntries[n].next = n + 1;
            }
         }
         _gHead->unused = 0;
      }else{
         _gHead->unused = -1;
      }
   }
public:
   //------------------------------------------------------------
   // 重载取值操作符
   E* operator *(){
      return _gEntries;
   }
   //------------------------------------------------------------
   // 重载数组操作符
   E& operator [](TInt index){
      MO_ASSERT_RANGE(index, 0, _capacity);
      return _gEntries[index];
   }
public:
   //------------------------------------------------------------
   // 获得节点指针
   E* Entries(){
      return _gEntries;
   }
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   E& Get(TInt index){
      MO_ASSERT_RANGE(index, 0, _capacity);
      return _gEntries[index];
   }
public:
   //------------------------------------------------------------
   // 收集一个索引位置
   TInt Alloc(){
      // 获得未使用的索引
      TInt index = _gHead->unused;
      MO_ASSERT_RANGE(index, 0, _capacity);
      _gHead->unused = _gEntries[index].next;
      _gHead->count++;
      return index;
   }
   //------------------------------------------------------------
   // 收集一个索引位置
   E& Alloc(TInt& index){
      // 获得未使用的索引
      index = _gHead->unused;
      MO_ASSERT_RANGE(index, 0, _capacity);
      _gHead->unused = _gEntries[index].next;
      _gHead->count++;
      return _gEntries[index];
   }
   //------------------------------------------------------------
   // 释放一个索引位置
   void Free(TInt index){
      MO_ASSERT_RANGE(index, 0, _capacity);
      _gEntries[index].next = _gHead->unused;
      _gHead->unused = index;
      _gHead->count--;
   }
   //------------------------------------------------------------
   // 释放一个索引位置
   void Free(TInt count, TInt first, TInt last){
      MO_ASSERT_RANGE(first, 0, _capacity);
      MO_ASSERT_RANGE(last, 0, _capacity);
      _gEntries[last].next = _gHead->unused;
      _gHead->unused = first;
      _gHead->count -= count;
   }
};

//============================================================
// <T>只读共享链表迭代器。</T>
//
// @template
// @type E entry 节点类型
// @history 100326 MAOCY 创建
//============================================================
template <typename E>
class TSharedEntryIteratorC{
protected:
   E* _pEntries;
   TInt _start;
   TInt _current;
public:
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedEntryIteratorC(){
      _pEntries = NULL;
      _start = -1;
      _current = -1;
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedEntryIteratorC(const TSharedEntryIteratorC<E>& iterator){
      _pEntries = iterator._pEntries;
      _start = iterator._start;
      _current = iterator._current;
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedEntryIteratorC(E* pEntries, TInt start){
      _pEntries = pEntries;
      _start = start;
      _current = -1;
   }
public:
   //------------------------------------------------------------
   // 获得当前节点
   inline E operator *() const{
      return _pEntries[_current];
   }
   //------------------------------------------------------------
   // 获得当前节点
   inline E operator ->() const{
      return _pEntries[_current];
   }
public:
   //------------------------------------------------------------
   // 重置位置
   inline void Reset(){
      _current = -1;
   }
   //------------------------------------------------------------
   // 移动到下一个位置
   inline TBool Next(){
      _current = (_current < 0) ? _start : _pEntries[_current].next;
      return (_current >= 0);
   }
   //------------------------------------------------------------
   // 移动到上一个位置
   inline TBool Prior(){
      _current = (_current < 0) ? _start : _pEntries[_current].prior;
      return (_current >= 0);
   }
public:
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   inline E& Get(){
      return _pEntries[_current];
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_SHAREDENTRY_H__

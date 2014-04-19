//============================================================
// <T>共享链表。</T>
// <P>所谓共享指链表的所有节点共享一个内存区域。</P>
// <P>   FSharedEntryAllocator：管理节点内存区域。</P>
// <P>   TSharedList：使用自身关联的节点内存区域管理自身链表结构，自身基本不占内存。</P>
//============================================================
#ifndef __MO_CM_SHAREDLIST_H__
#define __MO_CM_SHAREDLIST_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_SHARED_H__
#include "MoCmShared.h"
#endif // __MO_CM_SHARED_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>共享节点定义。</T>
//
// @struct
// @history 100328 MAOCY 创建
//============================================================
struct SSharedListEntry{
   // 列表索引
   TInt32 list;
   // 节点索引
   TInt32 index;
   // 前节点索引
   TInt32 prior;
   // 后节点索引
   TInt32 next;
   // 代码
   TInt32 code;
};

//============================================================
// <T>共享链表头定义。</T>
//
// @struct
// @history 100330 MAOCY 创建
//============================================================
struct SSharedListHead{
   // 总数
   TInt32 count;
   // 节点索引
   TInt32 index;
   // 首节点索引
   TInt32 first;
   // 尾节点索引
   TInt32 last;
   // 前节点索引
   TInt32 prior;
   // 后节点索引
   TInt32 next;
};

//============================================================
// <T>共享链表节点收集器。</T>
// <P>内存结构：[Head][SSharedListHead*capacity][SSharedEntry*entryCapacity]</P>
//
// @template
// @type E entry 节点类型
// @history 100326 MAOCY 创建
//============================================================
class MO_CM_DECLARE FSharedListAllocator :
      public FObject,
      public MShared,
      public MNamed{
public:
   struct SHead{
      TInt32 listCount;   // 链表总数
      TInt32 listUnused;  // 链表未使用索引
      TInt32 entryCount;  // 节点总数
      TInt32 entryUnused; // 节点未使用索引
   };
protected:
   TInt32 _capacity;
   TInt32 _entryCapacity;
   SHead* _gHead;
   SSharedListHead* _gLists;
   SSharedListEntry* _gEntries;
public:
   FSharedListAllocator();
public:
   void SetCapacity(TInt capacity);
   void SetEntryCapacity(TInt entryCapacity);
   void OnSharedInitialize();
   void OnSharedLink(TShareSegment& segment);
   TSize SharedCapacity();
public:
   //------------------------------------------------------------
   // 获得链表数组指针
   MO_INLINE SSharedListHead* Lists(){
      return _gLists;
   }
   //------------------------------------------------------------
   // 获得节点指针
   MO_INLINE SSharedListEntry* Entries(){
      return _gEntries;
   }
public:
   SSharedListHead* AllocList();
   void FreeList(SSharedListHead* pList);
   SSharedListEntry& AllocEntry();
   void FreeEntry(SSharedListEntry& entry);
   void FreeEntries(SSharedListHead* pList);
};

//============================================================
// <T>只读共享链表迭代器。</T>
//
// @class
// @history 100326 MAOCY 创建
//============================================================
class MO_CM_DECLARE TSharedListIteratorC{
protected:
   SSharedListEntry* _pEntries;
   TInt _start;
   TBool _skip;
   TInt _next;
   TInt _current;
public:
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedListIteratorC(){
      _pEntries = NULL;
      _start = -1;
      _skip = EFalse;
      _current = -1;
      _next = -1;
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedListIteratorC(const TSharedListIteratorC& iterator){
      _pEntries = iterator._pEntries;
      _start = iterator._start;
      _skip = EFalse;
      _current = iterator._current;
      _next = -1;
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedListIteratorC(SSharedListEntry* pEntries, TInt start){
      _pEntries = pEntries;
      _start = start;
      _skip = EFalse;
      _current = -1;
      _next = -1;
   }
public:
   //------------------------------------------------------------
   // 获得当前节点
   inline TInt32 operator *() const{
      MO_ASSERT(_current >= 0);
      return _pEntries[_current].code;
   }
   //------------------------------------------------------------
   // 获得当前节点
   inline TInt32 operator ->() const{
      MO_ASSERT(_current >= 0);
      return _pEntries[_current].code;
   }
public:
   //------------------------------------------------------------
   // 重置位置
   inline void Reset(){
      _current = -1;
      _next = -1;
   }
   //------------------------------------------------------------
   // 移动到下一个位置
   inline TBool Next(){
      if(_skip){
         _current = _next;
         _skip = EFalse;
      }else{
         _current = (_current < 0) ? _start : _pEntries[_current].next;
      }
      return (_current >= 0);
   }
   //------------------------------------------------------------
   // 移动到上一个位置
   inline TBool Prior(){
      if(_skip){
         _current = _next;
         _skip = EFalse;
      }else{
         _current = (_current < 0) ? _start : _pEntries[_current].prior;
      }
      return (_current >= 0);
   }
public:
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   inline TInt Get() const{
      MO_ASSERT(_current >= 0);
      return _pEntries[_current].code;
   }
};

//============================================================
// <T>共享链表迭代器。</T>
//
// @class
// @history 101111 MAOCY 创建
//============================================================
class TSharedList;
class MO_CM_DECLARE TSharedListIterator : public TSharedListIteratorC{
public:
   friend class TSharedList;
protected:
   TSharedList* _pList;
public:
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedListIterator(){
      MO_CLEAR(_pList);
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedListIterator(const TSharedListIteratorC& iterator) :
         TSharedListIteratorC(iterator){
      MO_CLEAR(_pList);
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedListIterator(TSharedList* pList, SSharedListEntry* pEntries, TInt start) :
         TSharedListIteratorC(pEntries, start){
      _pList = pList;
   }
public:
   //------------------------------------------------------------
   TBool Delete();
};

//============================================================
// <T>共享链表。</T>
//
// @struct
// @type E:entry 节点类型
// @history 100326 MAOCY 创建
//============================================================
class MO_CM_DECLARE TSharedList{
public:
   typedef TSharedListIteratorC TIteratorC;
public:
   FSharedListAllocator* _pAllocator;
   SSharedListHead* _gHead;
   SSharedListEntry* _gEntries;
public:
   void Initialize(FSharedListAllocator* pAllocator);
   void Release();
public:
   TBool operator==(const TSharedList& list) const;
   TBool operator!=(const TSharedList& list) const;
   void operator+=(const TSharedList& list);
public:
   TBool IsEmpty() const;
   TInt Count() const;
   TBool Equals(const TSharedList& list) const;
   TBool Contains(TInt32 code);
   TSharedListIteratorC IteratorC() const;
   TSharedListIteratorC LastIteratorC() const;
public:
   void Assign(const TSharedList& list);
   void Append(const TSharedList& list);
   void Unshift(TInt32 code);
   TInt32 Shift();
   void Push(TInt32 code);
   TInt Pop();
   TInt32 Delete(TInt index);
   TInt32 Delete(TSharedListIterator& iterator);
   TBool Remove(TInt32 code);
   void Clear();
};

MO_NAMESPACE_END

#endif // __MO_CM_SHAREDLIST_H__

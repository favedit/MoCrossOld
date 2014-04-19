#ifndef __MO_CM_SHAREDSET_H__
#define __MO_CM_SHAREDSET_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_SHARED_H__
#include "MoCmShared.h"
#endif // __MO_CM_SHARED_H__

MO_NAMESPACE_BEGIN

#define HASH(key, size)	((key) % (size) + 1)

template <class T>
struct SSharedSetEntry {
   TUint32 nlink;
   TUint32 plink;
   TUint32 key;
   TUint32 next;
   //TUint32 prev;
   //TUint32 slot;
   T data;
};

//============================================================
// <T>只读共享链表迭代器。</T>
//
// @template
// @type E:entry 节点类型
// @history 100326 MAOCY 创建
//============================================================
template <class T>
class TSharedSetIteratorC{
protected:
   SSharedSetEntry<T>* _pEntries;
   TUint32 _current;
   TUint32 _head;
public:
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedSetIteratorC()
   {
      _pEntries = 0;
      _current = 0;
      _head = 0;
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedSetIteratorC(const TSharedSetIteratorC<T>& iterator)
   {
      _pEntries = iterator._pEntries;
      _current = iterator._current;
      _head = iterator._head;
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedSetIteratorC(SSharedSetEntry<T>* pEntries, TUint32 head)
   {
      _pEntries = pEntries;
      _current = 0;
      _head = head;
   }
public:
   //------------------------------------------------------------
   // 获得当前节点
   inline const T& operator *() const{
      MO_ASSERT(_current > 0);
      return _pEntries[_current].data;
   }
   //------------------------------------------------------------
   // 获得当前节点
   inline const T* operator ->() const{
      MO_ASSERT(_current > 0);
      return &_pEntries[_current].data;
   }
public:
   //------------------------------------------------------------
   // 重置位置
   inline void Reset(){
      _current = 0;
   }
   //------------------------------------------------------------
   inline const SSharedSetEntry<T>* GetEntry() const{
      MO_ASSERT(_current > 0);
      return &_pEntries[_current];
   }
   //------------------------------------------------------------
   // 移动到下一个位置
   inline TBool Next(){
      _current = !_current ? _head : _pEntries[_current].nlink;
      return _current;
   }
public:
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   inline const T& Get() const{
      MO_ASSERT(_pEntries[_current].hash > 0);
      return _pEntries[_current].data;
   }
};

template <class T>
class MO_CM_DECLARE TSharedSet : public MShared{
public:
   struct SSharedSetHead {
      TUint32 _count;
   };
public:
   typedef TSharedSetIteratorC<T> TIteratorC;
private:
   SSharedSetEntry<T> * _gEntries;
   SSharedSetHead *_gHead;
   TUint32 *_slot;
   TUint32 _size;
   TUint32 _capacity;
   TUint32 _head;
   TUint32 _empty;

protected:
   //============================================================
   // 从内存池分配元素，以供使用
   //============================================================
   TUint32 InnerPop(){
      TUint32 ret = 0;
      if(_empty) {
         ret = _empty;
         _empty = _gEntries[_empty].nlink;

         _gEntries[ret].next = 0;
         //_gEntries[ret].prev = 0;
         _gEntries[ret].nlink = _head;
         _gEntries[ret].plink = 0;
         if(_head) {
            _gEntries[_head].plink = ret;
		 }
         _head = ret;
      }
      MO_ASSERT(ret > 0);
      return ret;
   }

   //============================================================
   // 元素归还内存池
   //============================================================
   void InnerPush(TUint32 n){
      TUint32 tmp = _gEntries[n].nlink;
      if(tmp) {
         _gEntries[tmp].plink = _gEntries[n].plink;
	  }

      tmp = _gEntries[n].plink;
      if(tmp) {
         _gEntries[tmp].nlink = _gEntries[n].nlink;
	  } else {
         _head = _gEntries[n].nlink;
	  }

      _gEntries[n].nlink = _empty;
      _gEntries[n].plink = 0;
      _empty = n;
   }

   //============================================================
   // 由Delete函数使用，用于删除一个元素
   //============================================================
   inline void Remove(TUint32 slot, TUint32 prev, TUint32 pos){
      if(prev) {
         _gEntries[prev].next = _gEntries[pos].next;
	  } else {
         _slot[slot] = _gEntries[pos].next;
	  }
   }

public:
   //============================================================
   void OnSharedInitialize(){
     Clear();
   }
   //============================================================
   void OnSharedLink(TShareSegment& segment){
      MO_ASSERT(_size > 0);
     MO_ASSERT(_capacity > 0)
      _gHead = segment.TypeAlloc<SSharedSetHead>();
      _gEntries = segment.TypeAlloc<SSharedSetEntry<T> >(_capacity + 1);
     _slot = segment.TypeAlloc<TUint32>(_size + 1);
   }
   //============================================================
   void SetCapacity(TUint32 capacity, TUint32 slot){
      _size = slot;
     _capacity = capacity;
   }
 
   //============================================================
   TSize SharedCapacity(){
      return (_capacity + 1) * sizeof(SSharedSetEntry<T>)
           + sizeof(SSharedSetHead) + sizeof(TUint32) * (_size + 1);
   }
   
   //============================================================
   // 判断当前链表和指定链表中所有数据内容是否相等。
   //============================================================
   TBool operator==(const TSharedSet<T>& array) const{
      return Equals(array);
   }
   
   //============================================================
   // 判断当前链表和指定链表中所有数据内容是否不相等。
   //============================================================
   TBool operator!=(const TSharedSet<T>& array) const{
      return !Equals(array);
   }
   
   //============================================================
   // 追加一个链表到当前链表中。
   //============================================================
   void operator+=(const TSharedSet<T>& array){
      Append(array);
   }

   void operator=(const TSharedSet<T>& array){
      Clear();
      Append(array);
   }

   //============================================================
   // 获得是否为空
   //============================================================
   inline TBool IsEmpty() const{
      return (0 == _gHead->_count);
   }
   
   //============================================================
   // 获得总数
   //============================================================
   inline TUint32 Count() const{
      return _gHead->_count;
   }
   
   //============================================================
   // 判断当前链表和指定链表是否相等
   //============================================================
   TBool Equals(const TSharedSet<T>& array) const{
      // 比较数量
      if(_gHead->_count != array._gHead->_count){
         return EFalse;
      }
      // 比较所有项目
      TIteratorC iterator = array.IteratorC();
      while(iterator.Next()){
         if(!Find(iterator.GetEntry()->key)) {
            return EFalse;
         }
      }
      return ETrue;
   }
   
   //============================================================
   // 获得首位置的只读数据迭代器
   //============================================================
   TSharedSetIteratorC<T> IteratorC() const{
      return TIteratorC(_gEntries, _head);
   }
   
   //============================================================
   // 接收一个链表到当前链表中。
   //============================================================
   void Assign(const TSharedSet<T>& array){
      Clear();
      Append(array);
   }
   
   //============================================================
   // 追加一个链表到当前链表中。
   //============================================================
   void Append(const TSharedSet<T>& array){
      TIteratorC iterator = array.IteratorC();
      while(iterator.Next()){
         Set(iterator.GetEntry()->key, iterator.GetEntry()->data);
      }
   }

   //============================================================
   // 从链表上删除链表节点
   //============================================================
   bool Delete(TUint32 key){
      TUint32 slot = HASH(key, _size);
      if(!_slot[slot]) {
         return false;
	  }
      for(TUint32 pos = _slot[slot], prev = 0; pos;
               prev = pos, pos = _gEntries[pos].next) {
         if(_gEntries[pos].key == key) {
            Remove(slot, prev, pos);
            InnerPush(pos);
            --_gHead->_count;
            return true;
         }
      }
      return false;
   }
 
   //============================================================
   // 清空所有数据
   //============================================================
   void Clear(){
      // 释放全部列表
      memset(_gEntries, 0, sizeof(SSharedSetEntry<T>) * (_capacity + 1));
      memset(_slot, 0, sizeof(TUint32) * (_size + 1));
      _gHead->_count = 0;
      _empty = 1;
      _head = 0;
      TUint32 len = _capacity + 1;
      for(TUint32 i = 1; i < len; ++i) {
         _gEntries[i].plink = (i - 1) % len;
         _gEntries[i].nlink = (i + 1) % len;
      }
   }

   //============================================================
   // 查找数据位置
   //============================================================
   TUint32 Find(TUint32 key){
      TUint32 pos = _slot[HASH(key, _size)];
      for(; pos; pos = _gEntries[pos].next) {
         if(_gEntries[pos].key == key) {
            return pos;
		 }
      }
      return 0;
   }

   //============================================================
   // 查找数据
   //============================================================
   bool Search(TUint32 key, T& data){
      TUint32 pos;
      if((pos = Find(key))) {
         data = _gEntries[pos].data;
         return true;
      }
      return false;
   }

   //============================================================
   // 得到数据
   //============================================================
   inline T Get(TUint32 key){
      T data;
      MO_ASSERT(Search(key, data));
      return data;
   }

   //============================================================
   // 设置数据
   //============================================================
   inline void Set(TUint32 key, const T& data){
      TUint32 pos;
      if((pos = Find(key))) {
         _gEntries[pos].data = data;
      } else {
         MO_ASSERT(_gHead->_count < _capacity);

         TUint32 slot = HASH(key, _size);
         pos = InnerPop();

         _gEntries[pos].key = key;
         _gEntries[pos].data = data;
         _gEntries[pos].next = _slot[slot];
         //_gEntries[pos].prev = 0;
         //if(_slot[slot]) {
            //_gEntries[_slot[slot]].prev = pos;
         //}
         _slot[slot] = pos;

         ++_gHead->_count;
      }
   }

   //============================================================
   // 数据元素是否存在
   //============================================================
   inline bool Contains(TUint32 key){
      return Find(key);
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_SHAREDSET_H__


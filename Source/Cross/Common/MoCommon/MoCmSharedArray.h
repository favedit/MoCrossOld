//============================================================
// <T>共享链表。</T>
// <P>所谓共享指链表的所有节点共享一个内存区域。</P>
// <P>   FSharedEntryAllocator：管理节点内存区域。</P>
// <P>   TSharedArray：使用自身关联的节点内存区域管理自身链表结构，自身基本不占内存。</P>
//============================================================
#ifndef __MO_CM_SHAREDARRAY_H__
#define __MO_CM_SHAREDARRAY_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_SHARED_H__
#include "MoCmShared.h"
#endif // __MO_CM_SHARED_H__

#ifndef __MO_CM_LANGUAGE_H__
#include "MoCmLanguage.h"
#endif // __MO_CM_LANGUAGE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>只读共享链表迭代器。</T>
//
// @template
// @type E:entry 节点类型
// @history 100326 MAOCY 创建
//============================================================
template <class T>
class TSharedArrayIteratorC{
protected:
   T* _pEntries;
   TInt _start;
   TInt _count;
   TInt _current;
public:
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedArrayIteratorC(){
      _pEntries = NULL;
      _start = -1;
      _current = -1;
	  _count = 0;
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedArrayIteratorC(const TSharedArrayIteratorC<T>& iterator){
      _pEntries = iterator._pEntries;
      _start = iterator._start;
      _current = iterator._current;
	  _count = iterator._count;
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TSharedArrayIteratorC(T* pEntries, TInt start, TInt length){
      _pEntries = pEntries;
      _start = start;
	  _count = length;
      _current = -1;
   }
public:
   //------------------------------------------------------------
   // 获得当前节点
   inline T operator *() const{
      MO_ASSERT(_current >= 0);
      return _pEntries[_current];
   }
   //------------------------------------------------------------
   // 获得当前节点
   inline const T* operator ->() const{
      MO_ASSERT(_current >= 0);
      return &_pEntries[_current];
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
      _current = _current < 0 ? _start : _current + 1;
	  //++_current;
      return (_current < _count && _current >= 0);
   }
   //------------------------------------------------------------
   // 移动到上一个位置
   inline TBool Prior(){
	  _current = _current < 0 ? _start : _current - 1;
      return (_current >= 0 && _current < _count);
   }
public:
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   inline T Get(){
      MO_ASSERT(_current >= 0 && _current < _count);
      return _pEntries[_current];
   }
};

//============================================================
// <T>共享链表。</T>
//
// @struct
// @type E:entry 节点类型
// @history 100326 MAOCY 创建
//============================================================
template <typename T>
class MO_CM_DECLARE TSharedArray : public MShared{
public:
   struct SSharedArrayHead {
      TUint32 _count;
   };
public:
   typedef TSharedArrayIteratorC<T> TIteratorC;
private:
   T* _gEntries;
   SSharedArrayHead *_gHead;
   TUint32 _size;
public:
   //============================================================
   void OnSharedInitialize(){
      memset(_gEntries, 0, sizeof(T) * _size);
      _gHead->_count = 0;
   }
   //============================================================
   void OnSharedLink(TShareSegment& segment){
      MO_ASSERT(_size > 0);
      _gHead = segment.TypeAlloc<SSharedArrayHead>();
      _gEntries = segment.TypeAlloc<T>(_size);
   }
   //============================================================
   void SetCapacity(TUint32 capacity){
      _size = capacity;
   }
   
   //============================================================
   TSize SharedCapacity(){
      return _size * sizeof(T) + sizeof(SSharedArrayHead);
   }
   
public:
   //============================================================
   // 得到指定元素, 并可更改
   //============================================================
   T& operator[](TUint32 index){
      MO_ASSERT(index < _gHead->_count);
      return _gEntries[index];
   }
   
   //============================================================
   // 判断当前链表和指定链表中所有数据内容是否相等。
   //============================================================
   TBool operator==(const TSharedArray<T>& array) const{
      return Equals(array);
   }
   
   //============================================================
   // 判断当前链表和指定链表中所有数据内容是否不相等。
   //============================================================
   TBool operator!=(const TSharedArray<T>& array) const{
      return !Equals(array);
   }
   
   //============================================================
   // 追加一个链表到当前链表中。
   //============================================================
   void operator+=(const TSharedArray<T>& array){
      Append(array);
   }
   void operator=(const TSharedArray<T>& array){
	   Assign(array);
   }
public:
   //============================================================
   // 获得是否为空
   //============================================================
   TBool IsEmpty() const{
      return (0 == _gHead->_count);
   }
   
   //============================================================
   // 获得总数
   //============================================================
   TUint32 Count() const{
      return _gHead->_count;
   }
   
   //============================================================
   // 判断当前链表和指定链表是否相等
   //============================================================
   TBool Equals(const TSharedArray<T>& array) const{
      // 比较数量
      if(_gHead->_count != array._gHead->_count){
         return EFalse;
      }
      // 比较所有项目
      TInt current = 0;
      TIteratorC iterator = array.IteratorC();
      while(iterator.Next()){
         if(_gEntries[current] != *iterator){
            return EFalse;
         }
         ++current;
      }
      return ETrue;
   }
   
   //============================================================
   // 获得首位置的只读数据迭代器
   //============================================================
   TSharedArrayIteratorC<T> IteratorC() const{
      return TIteratorC(_gEntries, 0, _gHead->_count);
   }
   
   //============================================================
   // 获得尾位置的只读数据迭代器
   //============================================================
   TSharedArrayIteratorC<T> LastIteratorC() const{
      return TIteratorC(_gEntries, _gHead->_count-1, _gHead->_count);
   }
   
   //============================================================
   // 得到指定元素
   //============================================================
   T Get(TUint32 index) const{
      MO_ASSERT_RANGE(index, 0, _gHead->_count);
      return _gEntries[index];
   }
   
   //============================================================
   // 接收一个链表到当前链表中。
   //============================================================
   void Assign(const TSharedArray<T>& array){
      Clear();
      Append(array);
   }
   
   //============================================================
   // 追加一个链表到当前链表中。
   //============================================================
   void Append(const TSharedArray<T>& array){
      TIteratorC iterator = array.IteratorC();
      while(iterator.Next()){
         Push(*iterator);
      }
   }

   //============================================================
   // 插入元素
   //============================================================
   void Insert(TUint32 idx, const T& value){
      MO_ASSERT(_gHead->_count < _size);
      MO_ASSERT(idx <= _gHead->_count);

      TUint32 pos = _gHead->_count++;
      for(TUint32 i = pos - 1; pos > idx; --i, --pos)
         _gEntries[pos] = _gEntries[i];
      
      _gEntries[idx] = value;
   }

   //============================================================
   // 插入多个元素
   //============================================================
   void Insert(TUint32 idx, const T* buf, TUint32 len){
      MO_ASSERT(_gHead->_count + len <= _size);
      MO_ASSERT(idx <= _gHead->_count);
   
      TUint32 src = _gHead->_count - 1;
      _gHead->_count += len;
      TUint32 dst = _gHead->_count - 1;
      TUint32 stop = idx + len - 1;
      for(TUint32 i = src, j = dst; j > stop; --j, --i)
   	   _gEntries[j] = _gEntries[i];
   
      memcpy(&_gEntries[idx], buf, sizeof(T) * len);
   }
   
   //============================================================
   // 将数据压入首位置
   //============================================================
   void Unshift(const T& value){
      // 将当前节点压入首节点
	  Insert(0, value);
   }
   
   //============================================================
   // 弹出链表的首数据
   //============================================================
   T Shift(){
      MO_ASSERT(_gHead->_count > 0);
      T v = _gEntries[0];
      // 设置内容
      TInt32 end = _gHead->_count--;
      for(int i = 0, j = 1; j < end; ++i, ++j) {
   	   _gEntries[i] = _gEntries[j];
      }
      return v;
   }
   
   //============================================================
   // 将数据压入尾位置
   //============================================================
   void Push(const T& v){
      // 收集一个未使用的节点
      MO_ASSERT(_gHead->_count < _size);
      TInt32 pos = _gHead->_count++;
      _gEntries[pos] = v;
   }
   
   //============================================================
   // 弹出链表的尾数据
   //============================================================
   T Pop(){
      MO_ASSERT(_gHead->_count > 0);
      TInt pos = --_gHead->_count;
      // 返回节点
      return _gEntries[pos];
   }
   
   //============================================================
   // 从链表上删除链表节点
   //============================================================
   T Delete(TUint32 index){
      MO_ASSERT(index < _gHead->_count);
      T v = _gEntries[index];
   
      for(int i = index, j = i + 1; j < _gHead->_count; ++i, ++j) {
   	   _gEntries[i] = _gEntries[j];
      }
   
      _gHead->_count--;
      return v;
   }
   
   //============================================================
   // 从链表上删除链表节点, 可删除多个
   //============================================================
   void Remove(const T& v){
   	for(TUint32 i = 0; i < _gHead->_count; ++i) {
         if(_gEntries[i] == v) {
            Delete(i);
			--i;
         }
      }
   }
   
   //============================================================
   // 清空所有数据
   //============================================================
   void Clear(){
      // 释放全部列表
      _gHead->_count = 0;
   }
   
};

MO_NAMESPACE_END

#endif // __MO_CM_SHAREDLIST_H__


#ifndef __MO_CM_VECTOR_H__
#define __MO_CM_VECTOR_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>只读节点迭代器。</T>
//
// @tool
// @history 100318 MAOCY 创建
//============================================================
template <typename T>
class TVectorIteratorC{
protected:
   T* _pMemory;
   TInt _count;
   TInt _index;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TVectorIteratorC(){
      MO_CLEAR(_pMemory);
      _count = 0;
      _index = -1;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TVectorIteratorC(const TVectorIteratorC& iterator){
      _pMemory = iterator._pMemory;
      _count = iterator._count;
      _index = iterator._index;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TVectorIteratorC(T* pMemory, TInt count, TInt index = -1){
      _pMemory = pMemory;
      _count = count;
      _index = index;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T& operator *() const{
      return GetC();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T& operator->() const{
      return GetC();
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE TBool IsEmpty(){
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE TBool Equals(T value){
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(_index, 0, _count);
      return _pMemory[_index] == value;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasNext(){
      return (_count > 0) ? _index + 1 < _count : EFalse;
   }
   //------------------------------------------------------------
   //<T> 移动到下一个位置。</T>
   MO_INLINE TBool Next(){
      if(_count > 0 && (_index + 1 < _count)){
         _index++;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasPrior(){
      return (_count > 0) ? _index - 1 >= 0 : EFalse;
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE TBool Prior(){
      if(_count > 0 && (_index + 1 < _count)){
         _index--;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T& GetC() const{
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(_index, 0, _count);
      return _pMemory[_index];
   }
public:
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
      _index = -1;
   }
};

//============================================================
// <T>只读节点迭代器。</T>
//
// @tool
// @history 100318 MAOCY 创建
//============================================================
template <typename T>
class TVectorIterator{
protected:
   T* _pMemory;
   TInt* _pCount;
   TInt _index;
   TBool _changed;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TVectorIterator(){
      MO_CLEAR(_pMemory);
      MO_CLEAR(_pCount);
      _index = -1;
      _changed = EFalse;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TVectorIterator(const TVectorIterator& iterator){
      _pMemory = iterator._pMemory;
      _pCount = iterator._pCount;
      _index = iterator._index;
      _changed = iterator._changed;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TVectorIterator(T* pMemory, TInt* pCount, TInt index = -1){
      _pMemory = pMemory;
      _pCount = pCount;
      _index = index;
      _changed = EFalse;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T& operator *() const{
      return GetC();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T& operator *(){
      return Get();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T& operator->() const{
      return GetC();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T& operator->(){
      return Get();
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE TBool IsEmpty(){
      return (0 == *_pCount);
   }
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE TBool Equals(T value){
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(_index, 0, *_pCount);
      MO_ASSERT(!_changed);
      return _pMemory[_index] == value;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasNext(){
      TInt count = *_pCount;
      if(count > 0){
         if(_changed){
            return _index < count;
         }else{
            return (_index + 1) < count;
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   //<T> 移动到下一个位置。</T>
   MO_INLINE TBool Next(){
      TInt count = *_pCount;
      if(count > 0){
         if(_changed){
            _changed = EFalse;
            return _index < count;
         }else if(_index + 1 < count){
            _index++;
            return ETrue;
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasPrior(){
      TInt count = *_pCount;
      if(count > 0){
         if(_changed){
            return _index >= 0;
         }else{
            return (_index - 1) >= 0;
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE TBool Prior(){
      TInt count = *_pCount;
      if(count > 0){
         if(_changed){
            _changed = EFalse;
            return _index < count;
         }else if(_index + 1 < count){
            _index--;
            return ETrue;
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T& GetC() const{
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(_index, 0, *_pCount);
      MO_ASSERT(!_changed);
      return _pMemory[_index];
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T& Get(){
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(_index, 0, *_pCount);
      MO_ASSERT(!_changed);
      return _pMemory[_index];
   }
public:
   //------------------------------------------------------------
   // <T>删除当前位置内容。</T>
   MO_INLINE T Delete(){
      TInt count = *_pCount;
      MO_ASSERT_RANGE(_index, 0, count);
      T value = _pMemory[_index];
      if(_index != count - 1){
         RTypes<T>::Move(_pMemory + _index, _pMemory + _index + 1, count - _index);
      }
      *_pCount = count - 1;
      _changed = ETrue;
      return value;
   }
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
      _index = -1;
      _changed = EFalse;
   }
};

//============================================================
// <T>对象数组。</T>
// <P>数据的管理结构，不涉及内存分配和释放。</P>
//
// @manager
// @type T 数据类型
// @history 130416 MAOCY 创建
//============================================================
template <typename T>
class MVector{
public:
   typedef TInt (*HComparer)(const T& source, const T& target);
   typedef TInt (*HComparerObject)(const T& source, const T& target, TAny* pCondition);
   typedef TInt (*HComparerValue)(T source, T target, TAny* pCondition);
   typedef TVectorIteratorC<T> TIteratorC;
   typedef TVectorIterator<T> TIterator;
protected:
   // @attribute 数据指针
   T* _pMemory;
   // @attribute 数据长度
   TInt _count;
   // @attribute 数据容量
   TInt _capacity;
protected:
   //------------------------------------------------------------
   // <T>构造对象数组。</T>
   MVector(){
      MO_CLEAR(_pMemory);
      _count = 0;
      _capacity = 0;
   }
   //------------------------------------------------------------
   // <T>析构对象数组。</T>
   MO_ABSTRACT ~MVector(){
   }
protected:
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_VIRTUAL void InnerResize(TInt size, TBool copy, TBool extends, TBool force) = 0;
public:
   //------------------------------------------------------------
   // 判断当前数组和指定数组中所有数据内容是否相等。
   MO_INLINE TBool operator==(const MVector<T>& values) const{
      return RTypes<T>::Equals(_pMemory, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 判断当前数组和指定数组中所有数据内容是否不相等。
   MO_INLINE TBool operator!=(const MVector<T>& values) const{
      return !RTypes<T>::Equals(_pMemory, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否小于指定数组中数据内容。
   MO_INLINE TBool operator<(const MVector<T>& values) const{
      return RTypes<T>::Compare(_pMemory, _count, values.MemoryC(), values.Count()) < 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否大于指定数组中数据内容。
   MO_INLINE TBool operator>(const MVector<T>& values) const{
      return RTypes<T>::Compare(_pMemory, _count, values.MemoryC(), values.Count()) > 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否小于等于指定数组中数据内容。
   MO_INLINE TBool operator<=(const MVector<T>& values) const{
      return RTypes<T>::Compare(_pMemory, _count, values.MemoryC(), values.Count()) <= 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否大于等于指定数组中数据内容。
   MO_INLINE TBool operator>=(const MVector<T>& values) const{
      return RTypes<T>::Compare(_pMemory, _count, values.MemoryC(), values.Count()) >= 0;
   }
   //------------------------------------------------------------
   // <T>获得只读数据内容。</T>
   MO_INLINE operator const T*() const{
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得数据内容</T>
   MO_INLINE operator T*(){
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得指定位置的只读数据内容。</T>
   MO_INLINE const T& operator[](TInt index) const{
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(index, 0, _count);
      return _pMemory[index];
   }
   //------------------------------------------------------------
   // <T>设置指定位置的数据内容。</T>
   MO_INLINE T& operator[](TInt index){
      MO_ASSERT_RANGE(index, 0, _count);
      return _pMemory[index];
   }
   //------------------------------------------------------------
   // <T>追加一个数据到当前数组尾部。</T>
   MO_INLINE void operator+=(T value){
      Push(value);
   }
   //------------------------------------------------------------
   // <T>追加一个数组到当前数组尾部。</T>
   MO_INLINE void operator+=(const TPtrC<T>& ptr){
      Append(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>追加一个数组到当前数组尾部。</T>
   MO_INLINE void operator+=(const MVector<T>& values){
      Append(values.MemoryC(), values.Count());
   }   
public:
   //------------------------------------------------------------
   // <T>判断内容是否为空。</T>
   MO_INLINE TBool IsEmpty() const{
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>判断内容是否已满。</T>
   MO_INLINE TBool IsFull() const{
      return (_count == _capacity);
   }
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE const T* MemoryC() const{
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE T* Memory(){
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得数据个数。</T>
   MO_INLINE TInt Count() const{
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得数据容量。</T>
   MO_INLINE TInt Capacity() const{
      return _capacity;
   }
   //------------------------------------------------------------
   // <T>获得数据容量（TODO：兼容以前，暂时保留）。</T>
   MO_INLINE TInt Size() const{
      return _capacity;
   }
public:
   //------------------------------------------------------------
   // 当前数组中是否和指定数组相等。
   MO_INLINE TBool Equals(const MVector<T>& values) const{
      return RTypes<T>::Equals(_pMemory, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 查找指定数组是否出现在当前数组的开始位置。
   MO_INLINE TBool StartsWith(const MVector<T>& values) const{
      return RTypes<T>::StartsWith(_pMemory, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 查找指定数组是否出现在当前数组的结束位置。
   MO_INLINE TBool EndsWith(const MVector<T>& values) const{
      return RTypes<T>::EndsWith(_pMemory, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 当前数组中和指定数组的大小。
   MO_INLINE TInt Compare(const MVector<T>& values) const{
      return RTypes<T>::Compare(_pMemory, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>从当前数组中是否含有指定数据。</T>
   MO_INLINE TBool Contains(T value) const{
      TInt result = RTypes<T>::IndexOf(_pMemory, _count, value);
      return (ENotFound != result);
   }
   //------------------------------------------------------------
   // 从当前数组中查找指定值出现的索引位置。
   MO_INLINE TInt IndexOf(T value) const{
      return RTypes<T>::IndexOf(_pMemory, _count, value);
   }
   //------------------------------------------------------------
   // 从当前数组中，从指定位置之后查找指定值出现的索引位置。
   MO_INLINE TInt IndexOf(T value, TInt offset) const{
      MO_ASSERT_RANGE(offset, 0, _count);
      TInt result = RTypes<T>::IndexOf(_pMemory + offset, _count - offset, value);
      return (ENotFound == result) ? result : offset + result;
   }
   //------------------------------------------------------------
   // 从当前数组中查找最后出现的索引位置。
   MO_INLINE TInt LastIndexOf(T value) const{
      return RTypes<T>::LastIndexOf(_pMemory, _count, value);
   }
   //------------------------------------------------------------
   // 从当前数组中查找最后出现的索引位置。
   MO_INLINE TInt LastIndexOf(T value, TInt offset) const{
      MO_ASSERT_RANGE(offset, 0, _count);
      return RTypes<T>::LastIndexOf(_pMemory, _count - offset, value);
   }
public:
   //------------------------------------------------------------
   // <T>获取只读迭代器。</T>
   MO_INLINE TIteratorC IteratorC(){
      return TIteratorC(_pMemory, _count);
   }
   //------------------------------------------------------------
   // <T>获取只读迭代器。</T>
   MO_INLINE TIteratorC IteratorC(TInt index){
      return TIteratorC(_pMemory + index, _count - index);
   }
   //------------------------------------------------------------
   // <T>获取迭代器。</T>
   MO_INLINE TIterator Iterator(){
      return TIterator(_pMemory, &_count);
   }
   //------------------------------------------------------------
   // <T>获取迭代器。</T>
   MO_INLINE TIterator Iterator(TInt index){
      return TIterator(_pMemory + index, &_count - index);
   }
   //------------------------------------------------------------
   // <T>获取第一个对象。</T>
   MO_INLINE T& First(){
      MO_ASSERT(_count > 0);
      return _pMemory[0];
   }
   //------------------------------------------------------------
   // <T>获取最后一个对象。</T>
   MO_INLINE T& Last(){
      MO_ASSERT(_count > 0);
      return _pMemory[_count - 1];
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   MO_INLINE const T& GetC(TInt index) const{
      MO_ASSERT_RANGE(index, 0, _count);
      return _pMemory[index];
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   MO_INLINE const T& GetC(TInt index, const T& value) const{
      if((index >= 0) && (index < _count)){
         return _pMemory[index];
      }
      return value;
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   MO_INLINE T& Get(TInt index){
      MO_ASSERT_RANGE(index, 0, _count);
      return _pMemory[index];
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   MO_INLINE T& Get(TInt index, T& value){
      if((index >= 0) && (index < _count)){
         return _pMemory[index];
      }
      return value;
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   MO_INLINE T Nvl(TInt index, T defaultValue){
      if((index >= 0) && (index < _count)){
         return _pMemory[index];
      }
      return defaultValue;
   }
public:
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE TPtrC<T> PtrC() const{
      return TPtrC<T>(_pMemory, _count);
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE TPtr<T> Ptr(){
      return TPtr<T>(_pMemory, &_count, _capacity);
   }
   //------------------------------------------------------------
   // 获得当前数组中的左边部分数组的引用。
   MO_INLINE TPtrC<T> LeftPtrC(TInt count) const{
      MO_ASSERT_BETWEEN(count, 0, _count);
      return TPtrC<T>(_pMemory, count);
   }
   //------------------------------------------------------------
   // 获得当前数组中的右边部分数组的引用。
   MO_INLINE TPtrC<T> RightPtrC(TInt count) const{
      MO_ASSERT_BETWEEN(count, 0, _count);
      return TPtrC<T>(_pMemory + (_count - count), count);
   }
   //------------------------------------------------------------
   // 获得当前数组中的开始到结尾的引用。
   MO_INLINE TPtrC<T> MidPtrC(TInt offset) const{
      MO_ASSERT_BETWEEN(offset, 0, _count);
      return TPtrC<T>(_pMemory + offset, _count - offset);
   }
   //------------------------------------------------------------
   // 获得当前数组中的部分数组的引用。
   MO_INLINE TPtrC<T> MidPtrC(TInt offset, TInt count) const{
      MO_ASSERT_BETWEEN(offset, 0, _count);
      MO_ASSERT_BETWEEN(count, 0, _count - offset);
      return TPtrC<T>(_pMemory + offset, count);
   }
   //------------------------------------------------------------
   // 获得当前数组中的部分数组的引用。
   MO_INLINE TPtrC<T> SubPtrC(TInt begin, TInt end) const{
      MO_ASSERT_BETWEEN(begin, 0, _count);
      MO_ASSERT_BETWEEN(end, 0, _count);
      MO_ASSERT(begin <= end);
      return TPtrC<T>(_pMemory + begin, end - begin);
   }
public:
   //------------------------------------------------------------
   // 查找指定数组对象在当前数组对象出现的索引位置。
   MO_INLINE TInt Find(const T* pValue, TInt count) const{
      return RTypes<T>::Find(_pMemory, _count, pValue, count);
   }
   //------------------------------------------------------------
   // 查找指定数组对象在当前数组对象出现的索引位置。
   MO_INLINE TInt Find(const MVector<T>& values) const{
      return RTypes<T>::Find(_pMemory, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 查找指定数组对象在当前数组对象出现的索引位置。
   MO_INLINE TInt LastFind(const T* pValue, TInt count) const{
      return RTypes<T>::LastFind(_pMemory, _count, pValue, count);
   }
   //------------------------------------------------------------
   // 查找指定数组对象在当前数组对象出现的索引位置。
   MO_INLINE TInt LastFind(const MVector<T>& values) const{
      return RTypes<T>::LastFind(_pMemory, _count, values.MemoryC(), values.Count());
   }
public:
   //------------------------------------------------------------
   // <T>保证内存大小，不保留以前内容。</T>
   MO_INLINE void ForceSize(TInt size){
      InnerResize(size, ETrue, EFalse, EFalse);
   }
   //------------------------------------------------------------
   // <T>保证内存大小，保留以前内容。</T>
   MO_INLINE void EnsureSize(TInt size){
      InnerResize(size, ETrue, ETrue, EFalse);
   }
   //------------------------------------------------------------
   // <T>接受一个数组指针到当前数组。</T>
   MO_INLINE void Assign(const T* pValues, TInt count){
      // 检查参数
      MO_ASSERT(pValues);
      MO_ASSERT(count >= 0);
      // 复制内容
      if((NULL != pValues) && (count >= 0)){
         InnerResize(count, EFalse, EFalse, EFalse);
         if((_pMemory != pValues) && (count > 0)){
            RTypes<T>::Copy(_pMemory, pValues, count);
         }
         _count = count;
      }
   }
   //------------------------------------------------------------
   // <T>接受一个数组指针到当前数组。</T>
   MO_INLINE void Assign(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>接受一个数组指针到当前数组。</T>
   MO_INLINE void Assign(const MVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>接受一个数组指针到当前数组。</T>
   MO_INLINE void AssignVector(const MVector<T>* pValues){
      MO_ASSERT(pValues);
      Assign(pValues->MemoryC(), pValues->Count());
   }
   //------------------------------------------------------------
   // <T>追加一个数组指针到当前数组尾部。</T>
   MO_INLINE void Append(const T* pValues, TInt count){
      // 检查参数
      MO_ASSERT(pValues);
      MO_ASSERT(count >= 0);
      // 复制内容
      if((NULL != pValues) && (count >= 0)){
         InnerResize(_count + count, ETrue, ETrue, EFalse);
         if((_pMemory != pValues) && (count > 0)){
            RTypes<T>::Copy(_pMemory + _count, pValues, count);
         }
         _count += count;
      }
   }
   //------------------------------------------------------------
   // <T>追加一个变长数组对象到当前数组尾部。</T>
   MO_INLINE void Append(const TPtrC<T>& ptr){
      Append(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>追加一个变长数组对象到当前数组尾部。</T>
   MO_INLINE void Append(const MVector<T>& values){
      Append(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>追加一个变长数组对象到当前数组尾部。</T>
   MO_INLINE void Append(const MVector<T>* pValues){
      MO_ASSERT(pValues);
      Append(pValues->MemoryC(), pValues->Count());
   }
   //------------------------------------------------------------
   // <T>插入一个数据在指定位置。</T>
   MO_INLINE void Insert(TInt index, T value){
      MO_ASSERT_BETWEEN(index, 0, _count);
      InnerResize(_count + 1, ETrue, ETrue, EFalse);
      RTypes<T>::Move(_pMemory + index + 1, _pMemory + index, _count - index);
      _pMemory[index] = value;
      _count++;
   }
   //------------------------------------------------------------
   // <T>插入一个数据指针在指定位置。</T>
   MO_INLINE void Insert(TInt index, const T* pValues, TInt count){
      // 检查参数
      MO_ASSERT_BETWEEN(index, 0, _count);
      MO_ASSERT(pValues);
      MO_ASSERT(count >= 0);
      // 复制内容
      if((index >= 0) && (index <= _count) && (NULL != pValues) && (count >= 0)){
         InnerResize(_count + count, ETrue, ETrue, EFalse);
         if(_count - index > 0){
            RTypes<T>::Move(_pMemory + index + count, _pMemory + index, _count - index);
         }
         if(count > 0){
            RTypes<T>::Copy(_pMemory + index, pValues, count);
         }
         _count += count;
      }
   }
   //------------------------------------------------------------
   // <T>插入一个数组在指定位置。</T>
   MO_INLINE void Insert(TInt index, const MVector<T>& values){
      Insert(index, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>从首部弹出一个数据。</T>
   MO_INLINE T Shift(){
      MO_ASSERT(_count > 0);
      T value = _pMemory[0];
      _count--;
      RTypes<T>::Move(_pMemory, _pMemory + 1, _count);
      return value;
   }
   //------------------------------------------------------------
   // <T>从首部压入一个数据。</T>
   MO_INLINE void Unshift(const T& value){
      InnerResize(_count + 1, ETrue, ETrue, EFalse);
      RTypes<T>::Move(_pMemory + 1, _pMemory, _count);
      _pMemory[0] = value;
      _count++;
   }
   //------------------------------------------------------------
   // <T>从尾部弹出一个数据。</T>
   MO_INLINE T Pop(){
      MO_ASSERT(_count);
      return _pMemory[--_count];
   }
   //------------------------------------------------------------
   // <T>追加一个数据到当前数组尾部。</T>
   MO_INLINE void Push(T value){
      InnerResize(_count + 1, ETrue, ETrue, EFalse);
      _pMemory[_count++] = value;
   }
   //------------------------------------------------------------
   // <T>追加一个唯一数据到当前数组尾部。</T>
   MO_INLINE void PushUnique(T value){
      if(!Contains(value)){
         Push(value);
      }
   }
   //------------------------------------------------------------
   // <T>使用指定内容填充当前数组。</T>
   MO_INLINE void Fill(T value){
      RTypes<T>::Fill(_pMemory, _count, value);
   }
   //------------------------------------------------------------
   // <T>使用指定内容和指定个数填充当前数组。</T>
   MO_INLINE void Fill(T value, TInt count){
      MO_ASSERT_RANGE(count, 0, count)
      RTypes<T>::Fill(_pMemory, count, value);
   }
   //------------------------------------------------------------
   // <T>使用指定内容和指定个数从指定位置填充当前数组。</T>
   MO_INLINE void Fill(T value, TInt offset, TInt count){
      MO_ASSERT_RANGE(count, 0, _count - offset)
      RTypes<T>::Fill(_pMemory + offset, count, value);
   }
   //------------------------------------------------------------
   // <T>从源内容替换为目标内容。</T>
   MO_INLINE void Replace(T source, T target){
      RTypes<T>::Replace(_pMemory, _count, source, target);
   }
   //------------------------------------------------------------
   // <T>TODO:从源数组替换为目标数组。</T>
   MO_INLINE void Replace(const MVector<T>& source, const MVector<T>& target){
      TInt sourceCount = source.Count();
      TInt targetCount = target.Count();
      T* pWriter = _pMemory;
      T* pReader = _pMemory;
      if(sourceCount >= target.Count()){
         // 替换目标缠度小于来源长度时
         TInt count = 0;
         while(ETrue){
            TInt find = RTypes<T>::Find(pReader, _count, source.MemoryC(), sourceCount);
            if(ENotFound != find){
               RTypes<T>::Move(pWriter, pReader, find);
               RTypes<T>::Copy(pWriter + find, target.MemoryC(), targetCount);
               pReader += find + sourceCount;
               pWriter += find + targetCount;
               count++;
            }else{
               RTypes<T>::Move(pWriter, pReader, _count - (pReader - _pMemory));
            }
         }
         _count += (targetCount - sourceCount) * count;
      }else{
         // 计算长度
         TInt count = RTypes<T>::Count(_pMemory, _count, source.MemoryC(), source.Count());
         TInt size = _count + (targetCount - sourceCount) * count;
         InnerResize(size, ETrue, ETrue, EFalse);
         // 替换目标缠度小于来源长度时
         TInt position = _count;
         while(ETrue){
            TInt find = RTypes<T>::LastFind(pReader, position, source.MemoryC(), sourceCount);
            if(ENotFound != find){
               RTypes<T>::Move(pWriter, pReader, find);
               RTypes<T>::Copy(pWriter + find, target.MemoryC(), targetCount);
               pReader += find + sourceCount;
               pWriter += find + targetCount;
               count++;
            }else{
               RTypes<T>::Move(pWriter, pReader, _count - (pReader - _pMemory));
            }
            position += targetCount - sourceCount;
         }
         _count = size;
      }
   }
   //------------------------------------------------------------
   // <T>交换当前数组中两个位置的值。</T>
   MO_INLINE void Swap(TInt index1, TInt index2){
      MO_ASSERT_RANGE(index1, 0, _count);
      MO_ASSERT_RANGE(index2, 0, _count);
      if(index1 != index2){
         T value = _pMemory[index1];
         _pMemory[index1] = _pMemory[index2];
         _pMemory[index2] = value;
      }
   }
   //------------------------------------------------------------
   // <T>删除指定位置的数据。</T>
   MO_INLINE TBool Delete(TInt index, T* pValue = NULL){
      // 检查参数
      MO_ASSERT_RANGE(index, 0, _count);
      // 删除内容
      if((index >= 0) && (index < _count)){
         // 复制内容
         if(NULL != pValue){
            MO_LIB_MEMORY_COPY(pValue, sizeof(T), _pMemory + index, sizeof(T));
         }
         // 复制内存
         if((_count - index) > 0){
            RTypes<T>::Move(_pMemory + index, _pMemory + index + 1, _count - index);
         }
         _count--;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>删除指定位置起的长度的数据。</T>
   // <P>如果长度超过，则删除指定位置到结束位置之间的数据</P>
   MO_INLINE TBool DeleteSegment(TInt offset, TInt count){
      MO_ASSERT_RANGE(offset, 0, _count);
      MO_ASSERT_RANGE(count, 0, _count - offset);
      if((offset >= 0) && (offset < _count) && (count >= 0) && (count < (_count - offset))){
         TInt last = _count - offset - count;
         if(last > 0){
            TInt copy = MO_LIB_MIN(last, count);
            RTypes<T>::Move(_pMemory + offset, _pMemory + offset + count, copy);
         }
         _count -= count;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>移除指定数据。</T>
   // <P>可能会移除多个相同的数据。</P>
   MO_INLINE void Remove(T value){
      TInt n = -1;
      TInt position = 0;
      while(++n < _count){
         if(_pMemory[n] != value){
            _pMemory[position++] = _pMemory[n];
         }
      }
      _count = position;
   }
   //------------------------------------------------------------
   // <T>设置指定索引位置的数据。</T>
   MO_INLINE void Set(TInt index, T value){
      MO_ASSERT_RANGE(index, 0, _count);
      _pMemory[index] = value;
   }
   //------------------------------------------------------------
   // <T>设置指定索引位置的数据，位置不足时将延伸大小。</T>
   MO_INLINE void ExtendSet(TInt index, T value){
      if(index >= _count){
         InnerResize(index + 1, ETrue, ETrue, EFalse);
         _count = index + 1;
      }
      _pMemory[index] = value;
   }
   //------------------------------------------------------------
   // <T>设置数据个数，不保留以前内容。</T>
   MO_INLINE void ForceCount(TInt count){
      InnerResize(count, EFalse, EFalse, EFalse);
      _count = count;
   }
   //------------------------------------------------------------
   // <T>设置数据个数。</T>
   // <P>长度变小后，多余的内容将丢失。如果存储的是指针，一定要自己先释放多余的对象后，再缩小长度。
   MO_INLINE void SetCount(TInt count){
      InnerResize(count, ETrue, EFalse, EFalse);
      _count = count;
   }
   //------------------------------------------------------------
   // <T>设置数据长度，将指定长度的部分的数据设置为指定内容。</T>
   MO_INLINE void SetCount(TInt count, T value){
      InnerResize(count, EFalse, EFalse, EFalse);
      _count = count;
      for(TInt n = 0; n < count; n++){
         _pMemory[n] = value;
      }
   }
   //------------------------------------------------------------
   // <T>设置数据长度。</T>
   // <P>长度变小后，多余的内容将丢失。如果存储的是指针，一定要自己先释放多余的对象后，再缩小长度。
   MO_INLINE void EnsureCount(TInt count){
      InnerResize(count, ETrue, EFalse, EFalse);
      this->_count = count;
   }
   //------------------------------------------------------------
   // <T>使用排序器对集合对象进行排序。</T>
   MO_INLINE void Sort(HComparer hComparer){
      MO_ASSERT(hComparer);
      if((NULL != _pMemory) && (_count > 1)){
         RTypes<T>::SortQuick(_pMemory, _count, hComparer);
      }
   }
   //------------------------------------------------------------
   // <T>使用排序器对集合对象进行排序。</T>
   MO_INLINE void SortObject(HComparerObject hComparer, TAny* pCondition = NULL){
      MO_ASSERT(hComparer);
      if((NULL != _pMemory) && (_count > 1)){
         RTypes<T>::SortQuickObject(_pMemory, _count, hComparer, pCondition);
      }
   }
   //------------------------------------------------------------
   // <T>使用排序器对集合对象进行排序。</T>
   MO_INLINE void SortValue(HComparerValue hComparer, TAny* pCondition = NULL){
      MO_ASSERT(hComparer);
      if((NULL != _pMemory) && (_count > 1)){
         RTypes<T>::SortQuickValue(_pMemory, _count, hComparer, pCondition);
      }
   }
   //------------------------------------------------------------
   // <T>清除所有数据。</T>
   // <P>注意本操作对数据不做任何处理，如果存储指针，一定要先自己释放。只是将长度置为空，可以重新放数据。</P>
   MO_INLINE void Clear(){
      _count = 0;
   }
   //------------------------------------------------------------
   // <T>释放所有数据。</T>
   MO_INLINE void Free(){
      InnerResize(0, EFalse, EFalse, EFalse);
      _count = 0;
   }
};

//============================================================
// <T>变长栈数组。</T>
//
// @tool
// @type T 数据类型
// @history 130416 MAOCY 创建
//============================================================
template <typename T>
class TVector : public MVector<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   TVector(){
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   TVector(TInt capacity){
      InnerResize(capacity, EFalse, EFalse, EFalse);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   TVector(const T* pValues, TInt count){
      Assign(pValues, count);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   TVector(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   TVector(const MVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   TVector(const TVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>析构变长数组。</T>
   MO_ABSTRACT ~TVector(){
      MO_FREE(this->_pMemory);
   }
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定ANSI字符串的内容。</T>
   MO_INLINE void operator=(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定ANSI字符串的内容。</T>
   MO_INLINE void operator=(const MVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定ANSI字符串的内容。</T>
   MO_INLINE void operator=(const TVector& values){
      Assign(values.MemoryC(), values.Count());
   }
protected:
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force){
      // 释放内存
      if(size == 0){
         this->_capacity = 0;
         MO_FREE(this->_pMemory);
         return;
      }
      // 检查大小
      if(!force){
         if(size <= this->_capacity){
            return;
         }
      }
      // 当内存不足时，重新计算内存容量
      TInt capacity = size;
      if(extends){
         capacity = RTypes<T>::CalculateObjectCapacity(this->_capacity, size);
      }
      // 如果收集不成功，则不进行复制数据处理
      T* pAlloc = MO_TYPES_ALLOC(T, capacity);
      RTypes<T>::Clear(pAlloc, capacity);
      MO_ASSERT(pAlloc);
      // 如果存在以前内存
      if(NULL != this->_pMemory){
         // 如果是缩小内存，则检查长度
         if(this->_count > capacity){
            this->_count = capacity;
         }
         // 复制有效数据
         if(copy && (this->_count > 0)){
            MO_LIB_TYPES_COPY(T, pAlloc, capacity, this->_pMemory, this->_count);
         }
         // 释放以前内存
         MO_FREE(this->_pMemory);
      }
      // 设置新的内存
      this->_pMemory = pAlloc;
      this->_capacity = capacity;
   }
};

//============================================================
// <T>变长堆数组。</T>
//
// @tool
// @type T 数据类型
// @history 091225 MAOCY 创建
//============================================================
template <typename T>
class FVector :
      public FObject,
      public MVector<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FVector(){
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FVector(TInt capacity){
      InnerResize(capacity, EFalse, EFalse, EFalse);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FVector(const T* pValues, TInt count){
      Assign(pValues, count);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FVector(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FVector(const MVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FVector(const FVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>析构变长数组。</T>
   MO_ABSTRACT ~FVector(){
      MO_FREE(this->_pMemory);
   }
public:
   //------------------------------------------------------------
   // <T>将当前内容设置为指定数据指针。</T>
   MO_INLINE void operator=(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前内容设置为指定数据数组。</T>
   MO_INLINE void operator=(const MVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>将当前内容设置为指定数据数组。</T>
   MO_INLINE void operator=(const FVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
protected:
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force){
      // 释放内存
      if(size == 0){
         this->_capacity = 0;
         MO_FREE(this->_pMemory);
         return;
      }
      // 检查大小
      if(!force){
         if(size <= this->_capacity){
            return;
         }
      }
      // 当内存不足时，重新计算内存容量
      TInt capacity = size;
      if(extends){
         capacity = RTypes<T>::CalculateObjectCapacity(this->_capacity, size);
      }
      // 如果收集不成功，则不进行复制数据处理
      T* pAlloc = MO_TYPES_ALLOC(T, capacity);
      MO_ASSERT(pAlloc);
      RTypes<T>::Clear(pAlloc, capacity);
      // 如果存在以前内存
      if(NULL != this->_pMemory){
         // 如果是缩小内存，则检查长度
         if(this->_count > capacity){
            this->_count = capacity;
         }
         // 复制有效数据
         if(copy && (this->_count > 0)){
            MO_LIB_TYPES_COPY(T, pAlloc, capacity, this->_pMemory, this->_count);
         }
         // 释放以前内存
         MO_FREE(this->_pMemory);
      }
      // 设置新的内存
      this->_pMemory = pAlloc;
      this->_capacity = capacity;
   }
};

//============================================================
// <T>变长堆数组。</T>
//
// @tool
// @type T 数据类型
// @history 091225 MAOCY 创建
//============================================================
template <typename T>
class FFreeVector :
      public FFree,
      public MVector<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FFreeVector(){
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FFreeVector(TInt capacity){
      InnerResize(capacity, EFalse, EFalse, EFalse);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FFreeVector(const T* pValues, TInt count){
      Assign(pValues, count);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FFreeVector(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FFreeVector(const MVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FFreeVector(const FVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>析构变长数组。</T>
   MO_ABSTRACT ~FFreeVector(){
      MO_MEM_FREE(this->_pMemory);
   }
public:
   //------------------------------------------------------------
   // <T>将当前内容设置为指定数据指针。</T>
   MO_INLINE void operator=(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前内容设置为指定数据数组。</T>
   MO_INLINE void operator=(const MVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>将当前内容设置为指定数据数组。</T>
   MO_INLINE void operator=(const FFreeVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
protected:
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force){
      // 释放内存
      if(size == 0){
         this->_capacity = 0;
         MO_MEM_FREE(this->_pMemory);
         return;
      }
      // 检查大小
      if(!force){
         if(size <= this->_capacity){
            return;
         }
      }
      // 当内存不足时，重新计算内存容量
      TInt capacity = size;
      if(extends){
         capacity = RTypes<T>::CalculateObjectCapacity(this->_capacity, size);
      }
      // 如果收集不成功，则不进行复制数据处理
      T* pAlloc = (T*)MO_MEM_ALLOC(sizeof(T) * capacity);
      MO_ASSERT(pAlloc);
      RTypes<T>::Clear(pAlloc, capacity);
      // 如果存在以前内存
      if(NULL != this->_pMemory){
         // 如果是缩小内存，则检查长度
         if(this->_count > capacity){
            this->_count = capacity;
         }
         // 复制有效数据
         if(copy && (this->_count > 0)){
            MO_LIB_TYPES_COPY(T, pAlloc, capacity, this->_pMemory, this->_count);
         }
         // 释放以前内存
         MO_MEM_FREE(this->_pMemory);
      }
      // 设置新的内存
      this->_pMemory = pAlloc;
      this->_capacity = capacity;
   }
};

//============================================================
// <T>定长数组。</T>
//
// @tool
// @type T 数据类型
// @param S 数据长度
// @history 091207 MAOCY 创建
//============================================================
template <typename T, TInt S>
class TFixVector : public MVector<T>{
protected:
   T _memory[S];
public:
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFixVector(){
      InnerInitialize();
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFixVector(const TPtrC<T>& ptr){
      InnerInitialize();
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFixVector(const MVector<T>& values){
      InnerInitialize();
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFixVector(const TFixVector<T, S>& values){
      InnerInitialize();
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>析构定长数组。</T>
   MO_ABSTRACT ~TFixVector(){
   }
protected:
   //------------------------------------------------------------
   // <T>初始化内存。</T>
   MO_INLINE void InnerInitialize(){
      this->_pMemory = _memory;
      this->_capacity = S;
   }
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force){
      MO_ASSERT(size <= S);
   }
public:
   //------------------------------------------------------------
   // <T>设定当前数据和指定数据指针相等。</T>
   MO_INLINE void operator=(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>设定当前数据和指定数据指针相等。</T>
   MO_INLINE void operator=(const MVector<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>设定当前数据和指定数据指针相等。</T>
   MO_INLINE void operator=(const TFixVector<T, S>& values){
      Assign(values.MemoryC(), values.Count());
   }
};

//============================================================
// <T>定长数组。</T>
//
// @tool
// @type T 数据类型
// @param S 数据长度
// @history 091207 MAOCY 创建
//============================================================
template <typename T, TInt S>
class TFixPtrVector : public MVector<T>{
protected:
   T _memory[S];
   T* _pPtrs[S];
public:
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFixPtrVector(){
      this->InnerInitialize();
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFixPtrVector(const TPtrC<T>& ptr){
      this->InnerInitialize();
      this->Append(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFixPtrVector(const MVector<T>& values){
      this->InnerInitialize();
      this->Append(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFixPtrVector(const TFixPtrVector<T, S>& values){
      this->InnerInitialize();
      this->Append(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>析构定长数组。</T>
   MO_ABSTRACT ~TFixPtrVector(){
   }
protected:
   //------------------------------------------------------------
   // <T>初始化内存。</T>
   MO_INLINE void InnerInitialize(){
      this->_pMemory = _memory;
      this->_count = 0;
      this->_capacity = S;
      // 关联指针
      for(TInt n = 0; n < S; n++){
         _pPtrs[n] = &_memory[n];
      }
   }
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force){
      MO_ASSERT(size <= S);
   }
public:
   //------------------------------------------------------------
   // <T>设定当前数据和指定数据指针相等。</T>
   MO_INLINE void operator=(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>设定当前数据和指定数据指针相等。</T>
   MO_INLINE void operator=(const TFixPtrVector<T, S>& values){
      Assign(values.MemoryC(), values.Count());
   }
public:
   //------------------------------------------------------------
   // <T>设定当前数据和指定数据指针相等。</T>
   MO_INLINE T** Ptrs(){
      return _pPtrs;
   }
};

//============================================================
// <T>实例集合。</T>
//
// @history 130401 MAOCY 创建
//============================================================
template <typename T>
class MInstancedVector{
protected:
   TInt _count;
   T* _pItems;
public:
   //------------------------------------------------------------
   // <T>构造实例集合。</T>
   MInstancedVector(){
      _count = 0;
      MO_CLEAR(_pItems);
   }
   //------------------------------------------------------------
   // <T>析构实例集合。</T>
   MO_ABSTRACT ~MInstancedVector(){
      Release();
   }
public:
   //------------------------------------------------------------
   // <T>强制容量。</T>
   MO_INLINE void ForceCount(TInt count){
      MO_ASSERT(count > 0);
      // 释放旧内存
      MO_FREE(_pItems);
      // 收集内存
      _pItems = MO_TYPES_ALLOC(T, count);
      RTypes<T>::Clear(_pItems, count);
      // 初始化对象
      for(TInt n = 0; n < count; n++){
         T& item = _pItems[n];
         new(&item)T();
      }
      // 设置属性
      _count = count;
   }
public:
   //------------------------------------------------------------
   // <T>获得总数。</T>
   MO_INLINE TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>根据索引获得对象。</T>
   MO_INLINE T* Find(TInt index){
      if((index >= 0) && (index < _count)){
         T& item = _pItems[index];
         return &item;
      }
      return NULL;
   }
   //------------------------------------------------------------
   // <T>根据索引获得对象。</T>
   MO_INLINE T* Get(TInt index){
      MO_ASSERT_RANGE(index, 0, _count);
      T& item = _pItems[index];
      return &item;
   }
public:
   //------------------------------------------------------------
   // <T>从当前数组中是否含有指定数据。</T>
   MO_INLINE TBool Contains(T* pItem) const{
      for(TInt n = 0; n < _count; n++){
         T& item = _pItems[n];
         if(&item == pItem){
            return ETrue;
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>释放所有对象。</T>
   MO_INLINE void Release(){
      // 释放所有对象
      for(TInt n = 0; n < _count; n++){
         T& item = _pItems[n];
         item.~T();
      }
      MO_FREE(_pItems);
      // 重置属性
      _count = 0;
   }
public:
   //------------------------------------------------------------
   // <T>显示跟踪信息。</T>
   MO_INLINE void Dump(){
      TInt itemCapacity = sizeof(T);
      TInt capacity = itemCapacity * _count;
      MO_DEBUG("Instanced vector. (capacity=%d, item_capacity=%d, entry_size=%d)",
            capacity, itemCapacity, _count);
   }
};

//============================================================
// <T>实例集合。</T>
//
// @tool
//============================================================
template <typename T>
class TInstancedVector :
      public MInstancedVector<T>{
public:
   //------------------------------------------------------------
   // <T>构造实例集合。</T>
   TInstancedVector(){
   }
   //------------------------------------------------------------
   // <T>析构实例集合。</T>
   MO_ABSTRACT ~TInstancedVector(){
   }
};

//============================================================
// <T>实例集合。</T>
//
// @class
//============================================================
template <typename T>
class FInstancedVector :
      public FObject,
      public MInstancedVector<T>{
public:
   //------------------------------------------------------------
   // <T>构造实例集合。</T>
   FInstancedVector(){
   }
   //------------------------------------------------------------
   // <T>析构实例集合。</T>
   MO_ABSTRACT ~FInstancedVector(){
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_VECTOR_H__

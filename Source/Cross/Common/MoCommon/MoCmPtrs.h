#ifndef __MO_CM_PTRS_H__
#define __MO_CM_PTRS_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

#ifndef __MO_CM_CLASSBASE_H__
#include "MoCmClassBase.h"
#endif // __MO_CM_CLASSBASE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>只读节点迭代器。</T>
//
// @tool
// @history 140329 MAOCY 创建
//============================================================
template <typename T>
class GPtrIteratorC{
protected:
   GPtr<T>* _pPtrs;
   TInt _count;
   TInt _index;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   GPtrIteratorC(){
      MO_CLEAR(_pPtrs);
      _count = 0;
      _index = -1;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   GPtrIteratorC(const GPtrIteratorC& iterator){
      _pPtrs = iterator._pPtrs;
      _count = iterator._count;
      _index = iterator._index;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   GPtrIteratorC(GPtr<T>* pMemory, TInt count, TInt index = -1){
      _pPtrs = pMemory;
      _count = count;
      _index = index;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* operator *() const{
      return Get();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* operator->() const{
      return Get();
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE TBool IsEmpty(){
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE TBool Equals(T* pValue){
      MO_ASSERT(_pPtrs);
      MO_ASSERT_RANGE(_index, 0, _count);
      GPtr<T>& ptr = _pPtrs[_index];
      return (ptr == pValue);
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
   MO_INLINE T* Get() const{
      MO_ASSERT(_pPtrs);
      MO_ASSERT_RANGE(_index, 0, _count);
      return (T*)_pPtrs[_index];
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
class GPtrIterator{
protected:
   GPtr<T>* _pPtrs;
   TInt* _pCount;
   TInt _index;
   TBool _changed;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   GPtrIterator(){
      MO_CLEAR(_pPtrs);
      MO_CLEAR(_pCount);
      _index = -1;
      _changed = EFalse;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   GPtrIterator(const GPtrIterator& iterator){
      _pPtrs = iterator._pPtrs;
      _pCount = iterator._pCount;
      _index = iterator._index;
      _changed = iterator._changed;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   GPtrIterator(GPtr<T>* pMemory, TInt* pCount, TInt index = -1){
      _pPtrs = pMemory;
      _pCount = pCount;
      _index = index;
      _changed = EFalse;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* operator *(){
      return Get();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* operator->(){
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
   MO_INLINE TBool Equals(T* pValue){
      MO_ASSERT(_pPtrs);
      MO_ASSERT_RANGE(_index, 0, *_pCount);
      MO_ASSERT(!_changed);
      GPtr<T> ptr = _pPtrs[_index];
      return (ptr == pValue);
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
   MO_INLINE T* Get(){
      MO_ASSERT(_pPtrs);
      MO_ASSERT_RANGE(_index, 0, *_pCount);
      MO_ASSERT(!_changed);
      return (T*)_pPtrs[_index];
   }
public:
   //------------------------------------------------------------
   // <T>删除当前位置内容。</T>
   MO_INLINE T* Delete(){
      TInt count = *_pCount;
      MO_ASSERT_RANGE(_index, 0, count);
      GPtr<T>& ptr = _pPtrs[_index];
      T* pValue = ptr.Flip();
      if(_index != count - 1){
         RTypes< GPtr<T> >::Move(_pPtrs + _index, _pPtrs + _index + 1, count - _index);
      }
      *_pCount = count - 1;
      _changed = ETrue;
      return pValue;
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
class MPtrs{
public:
   typedef GPtrIteratorC<T> TIteratorC;
   typedef GPtrIterator<T> TIterator;
protected:
   // @attribute 数据指针
   GPtr<T>* _pPtrs;
   // @attribute 数据长度
   TInt _count;
   // @attribute 数据容量
   TInt _capacity;
protected:
   //------------------------------------------------------------
   // <T>构造对象数组。</T>
   MPtrs(){
      MO_CLEAR(_pPtrs);
      _count = 0;
      _capacity = 0;
   }
   //------------------------------------------------------------
   // <T>析构对象数组。</T>
   MO_ABSTRACT ~MPtrs(){
   }
protected:
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_VIRTUAL void InnerResize(TInt size, TBool copy, TBool extends, TBool force) = 0;
public:
   //------------------------------------------------------------
   // 判断当前数组和指定数组中所有数据内容是否相等。
   MO_INLINE TBool operator==(const MPtrs<T>& values) const{
      return RTypes<T>::Equals(_pPtrs, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 判断当前数组和指定数组中所有数据内容是否不相等。
   MO_INLINE TBool operator!=(const MPtrs<T>& values) const{
      return !RTypes<T>::Equals(_pPtrs, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否小于指定数组中数据内容。
   MO_INLINE TBool operator<(const MPtrs<T>& values) const{
      return RTypes<T>::Compare(_pPtrs, _count, values.MemoryC(), values.Count()) < 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否大于指定数组中数据内容。
   MO_INLINE TBool operator>(const MPtrs<T>& values) const{
      return RTypes<T>::Compare(_pPtrs, _count, values.MemoryC(), values.Count()) > 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否小于等于指定数组中数据内容。
   MO_INLINE TBool operator<=(const MPtrs<T>& values) const{
      return RTypes<T>::Compare(_pPtrs, _count, values.MemoryC(), values.Count()) <= 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否大于等于指定数组中数据内容。
   MO_INLINE TBool operator>=(const MPtrs<T>& values) const{
      return RTypes<T>::Compare(_pPtrs, _count, values.MemoryC(), values.Count()) >= 0;
   }
   //------------------------------------------------------------
   // <T>获得只读数据内容。</T>
   MO_INLINE operator const GPtr<T>*() const{
      return _pPtrs;
   }
   //------------------------------------------------------------
   // <T>获得数据内容</T>
   MO_INLINE operator GPtr<T>*(){
      return _pPtrs;
   }
   //------------------------------------------------------------
   // <T>获得指定位置的只读数据内容。</T>
   MO_INLINE const T* operator[](TInt index) const{
      MO_ASSERT(_pPtrs);
      MO_ASSERT_RANGE(index, 0, _count);
      return (T*)_pPtrs[index];
   }
   //------------------------------------------------------------
   // <T>设置指定位置的数据内容。</T>
   MO_INLINE T* operator[](TInt index){
      MO_ASSERT_RANGE(index, 0, _count);
      return (T*)_pPtrs[index];
   }
   //------------------------------------------------------------
   // <T>追加一个数据到当前数组尾部。</T>
   MO_INLINE void operator+=(T* pValue){
      Push(pValue);
   }
   //------------------------------------------------------------
   // <T>追加一个数组到当前数组尾部。</T>
   MO_INLINE void operator+=(const MPtrs<T>& values){
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
   MO_INLINE const GPtr<T>* MemoryC() const{
      return _pPtrs;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE GPtr<T>* Memory(){
      return _pPtrs;
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
   MO_INLINE TBool Equals(const MPtrs<T>& values) const{
      return RTypes<T>::Equals(_pPtrs, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 查找指定数组是否出现在当前数组的开始位置。
   MO_INLINE TBool StartsWith(const MPtrs<T>& values) const{
      return RTypes<T>::StartsWith(_pPtrs, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 查找指定数组是否出现在当前数组的结束位置。
   MO_INLINE TBool EndsWith(const MPtrs<T>& values) const{
      return RTypes<T>::EndsWith(_pPtrs, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 当前数组中和指定数组的大小。
   MO_INLINE TInt Compare(const MPtrs<T>& values) const{
      return RTypes<T>::Compare(_pPtrs, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>从当前数组中是否含有指定数据。</T>
   MO_INLINE TBool Contains(T* pValue) const{
      TInt result = IndexOf(pValue);
      return (result != ENotFound);
   }
   //------------------------------------------------------------
   // 从当前数组中查找指定值出现的索引位置。
   MO_INLINE TInt IndexOf(T* pValue) const{
      for(TInt n = 0; n < _count; n++){
         GPtr<T>& ptr = _pPtrs[n];
         if(ptr.Get() == pValue){
            return n;
         }
      }
      return ENotFound;
   }
   //------------------------------------------------------------
   // 从当前数组中查找最后出现的索引位置。
   MO_INLINE TInt LastIndexOf(T* pValue) const{
      for(TInt n = _count - 1; n >= 0; n--){
         GPtr<T>& ptr = _pPtrs[n];
         if(ptr.Get() == pValue){
            return n;
         }
      }
      return ENotFound;
   }
public:
   //------------------------------------------------------------
   // <T>获取只读迭代器。</T>
   MO_INLINE TIteratorC IteratorC(){
      return TIteratorC(_pPtrs, _count);
   }
   //------------------------------------------------------------
   // <T>获取只读迭代器。</T>
   MO_INLINE TIteratorC IteratorC(TInt index){
      return TIteratorC(_pPtrs + index, _count - index);
   }
   //------------------------------------------------------------
   // <T>获取迭代器。</T>
   MO_INLINE TIterator Iterator(){
      return TIterator(_pPtrs, &_count);
   }
   //------------------------------------------------------------
   // <T>获取迭代器。</T>
   MO_INLINE TIterator Iterator(TInt index){
      return TIterator(_pPtrs + index, &_count - index);
   }
   //------------------------------------------------------------
   // <T>获取第一个对象。</T>
   MO_INLINE T* First(){
      MO_ASSERT(_count > 0);
      return (T*)_pPtrs[0];
   }
   //------------------------------------------------------------
   // <T>获取最后一个对象。</T>
   MO_INLINE T* Last(){
      MO_ASSERT(_count > 0);
      return (T*)_pPtrs[_count - 1];
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   MO_INLINE const T* GetC(TInt index) const{
      MO_ASSERT_RANGE(index, 0, _count);
      return (T*)_pPtrs[index];
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   MO_INLINE const T* GetC(TInt index, const T* pValue) const{
      if((index >= 0) && (index < _count)){
         return (T*)_pPtrs[index];
      }
      return pValue;
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   MO_INLINE T* Get(TInt index){
      MO_ASSERT_RANGE(index, 0, _count);
      return (T*)_pPtrs[index];
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   MO_INLINE T* Get(TInt index, T* pValue){
      if((index >= 0) && (index < _count)){
         return (T*)_pPtrs[index];
      }
      return pValue;
   }
public:
   //------------------------------------------------------------
   // 获得当前数组中的左边部分数组的引用。
   MO_INLINE TPtrC< GPtr<T> > LeftPtrC(TInt count) const{
      MO_ASSERT_BETWEEN(count, 0, _count);
      return TPtrC< GPtr<T> >(_pPtrs, count);
   }
   //------------------------------------------------------------
   // 获得当前数组中的右边部分数组的引用。
   MO_INLINE TPtrC< GPtr<T> > RightPtrC(TInt count) const{
      MO_ASSERT_BETWEEN(count, 0, _count);
      return TPtrC< GPtr<T> >(_pPtrs + (_count - count), count);
   }
   //------------------------------------------------------------
   // 获得当前数组中的开始到结尾的引用。
   MO_INLINE TPtrC< GPtr<T> > MidPtrC(TInt offset) const{
      MO_ASSERT_BETWEEN(offset, 0, _count);
      return TPtrC< GPtr<T> >(_pPtrs + offset, _count - offset);
   }
   //------------------------------------------------------------
   // 获得当前数组中的部分数组的引用。
   MO_INLINE TPtrC< GPtr<T> > MidPtrC(TInt offset, TInt count) const{
      MO_ASSERT_BETWEEN(offset, 0, _count);
      MO_ASSERT_BETWEEN(count, 0, _count - offset);
      return TPtrC< GPtr<T> >(_pPtrs + offset, count);
   }
   //------------------------------------------------------------
   // 获得当前数组中的部分数组的引用。
   MO_INLINE TPtrC< GPtr<T> > SubPtrC(TInt begin, TInt end) const{
      MO_ASSERT_BETWEEN(begin, 0, _count);
      MO_ASSERT_BETWEEN(end, 0, _count);
      MO_ASSERT(begin <= end);
      return TPtrC< GPtr<T> >(_pPtrs + begin, end - begin);
   }
public:
   //------------------------------------------------------------
   // 查找指定数组对象在当前数组对象出现的索引位置。
   MO_INLINE TInt Find(const T* pValue, TInt count) const{
      return RTypes<T>::Find(_pPtrs, _count, pValue, count);
   }
   //------------------------------------------------------------
   // 查找指定数组对象在当前数组对象出现的索引位置。
   MO_INLINE TInt Find(const MPtrs<T>& values) const{
      return RTypes<T>::Find(_pPtrs, _count, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // 查找指定数组对象在当前数组对象出现的索引位置。
   MO_INLINE TInt LastFind(const T* pValue, TInt count) const{
      return RTypes<T>::LastFind(_pPtrs, _count, pValue, count);
   }
   //------------------------------------------------------------
   // 查找指定数组对象在当前数组对象出现的索引位置。
   MO_INLINE TInt LastFind(const MPtrs<T>& values) const{
      return RTypes<T>::LastFind(_pPtrs, _count, values.MemoryC(), values.Count());
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
   MO_INLINE void Assign(const GPtr<T>* pValues, TInt count){
      // 检查参数
      MO_ASSERT(pValues);
      MO_ASSERT(count >= 0);
      // 复制内容
      if((NULL != pValues) && (count >= 0)){
         InnerResize(count, EFalse, EFalse, EFalse);
         if((_pPtrs != pValues) && (count > 0)){
            RTypes< GPtr<T> >::Copy(_pPtrs, pValues, count);
         }
         _count = count;
      }
   }
   //------------------------------------------------------------
   // <T>接受一个数组指针到当前数组。</T>
   MO_INLINE void Assign(const TPtrC< GPtr<T> >& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>接受一个数组指针到当前数组。</T>
   MO_INLINE void Assign(const MPtrs<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>接受一个数组指针到当前数组。</T>
   MO_INLINE void AssignPointer(const MPtrs<T>* pValues){
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
         if((_pPtrs != pValues) && (count > 0)){
            RTypes<T>::Copy(_pPtrs + _count, pValues, count);
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
   MO_INLINE void Append(const MPtrs<T>& values){
      Append(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>追加一个变长数组对象到当前数组尾部。</T>
   MO_INLINE void Append(const MPtrs<T>* pValues){
      MO_ASSERT(pValues);
      Append(pValues->MemoryC(), pValues->Count());
   }
   //------------------------------------------------------------
   // <T>插入一个数据在指定位置。</T>
   MO_INLINE void Insert(TInt index, T* pValue){
      MO_ASSERT_BETWEEN(index, 0, _count);
      InnerResize(_count + 1, ETrue, ETrue, EFalse);
      RTypes<T>::Move(_pPtrs + index + 1, _pPtrs + index, _count - index);
      _pPtrs[index] = pValue;
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
            RTypes<T>::Move(_pPtrs + index + count, _pPtrs + index, _count - index);
         }
         if(count > 0){
            RTypes<T>::Copy(_pPtrs + index, pValues, count);
         }
         _count += count;
      }
   }
   //------------------------------------------------------------
   // <T>插入一个数组在指定位置。</T>
   MO_INLINE void Insert(TInt index, const MPtrs<T>& values){
      Insert(index, values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>从首部弹出一个数据。</T>
   MO_INLINE T* Shift(){
      MO_ASSERT(_count > 0);
      T value = _pPtrs[0];
      _count--;
      RTypes<T>::Move(_pPtrs, _pPtrs + 1, _count);
      return value;
   }
   //------------------------------------------------------------
   // <T>从首部压入一个数据。</T>
   MO_INLINE void Unshift(T* pValue){
      InnerResize(_count + 1, ETrue, ETrue, EFalse);
      RTypes< GPtr<T> >::Move(_pPtrs + 1, _pPtrs, _count);
      _pPtrs[0] = pValue;
      _count++;
   }
   //------------------------------------------------------------
   // <T>从尾部弹出一个数据。</T>
   MO_INLINE T* Pop(){
      MO_ASSERT(_count);
      return _pPtrs[--_count];
   }
   //------------------------------------------------------------
   // <T>追加一个数据到当前数组尾部。</T>
   MO_INLINE void Push(T* value){
      InnerResize(_count + 1, ETrue, ETrue, EFalse);
      _pPtrs[_count++] = value;
   }
   //------------------------------------------------------------
   // <T>追加一个唯一数据到当前数组尾部。</T>
   MO_INLINE void PushUnique(T* value){
      if(!Contains(value)){
         Push(value);
      }
   }
   //------------------------------------------------------------
   // <T>从源内容替换为目标内容。</T>
   MO_INLINE void Replace(T* pSource, T* pTarget){
      //RTypes<T>::Replace(_pPtrs, _count, source, target);
   }
   //------------------------------------------------------------
   // <T>交换当前数组中两个位置的值。</T>
   MO_INLINE void Swap(TInt index1, TInt index2){
      MO_ASSERT_RANGE(index1, 0, _count);
      MO_ASSERT_RANGE(index2, 0, _count);
      if(index1 != index2){
         T* pValue = _pPtrs[index1];
         _pPtrs[index1] = _pPtrs[index2];
         _pPtrs[index2] = pValue;
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
            MO_LIB_MEMORY_COPY(pValue, sizeof(T), _pPtrs + index, sizeof(T));
         }
         // 复制内存
         if((_count - index) > 0){
            RTypes< GPtr<T> >::Move(_pPtrs + index, _pPtrs + index + 1, _count - index);
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
            RTypes< GPtr<T> >::Move(_pPtrs + offset, _pPtrs + offset + count, copy);
         }
         _count -= count;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>移除指定数据。</T>
   // <P>可能会移除多个相同的数据。</P>
   MO_INLINE void Remove(T* pValue){
      // 数据向前移
      TInt n = -1;
      TInt position = 0;
      while(++n < _count){
         if(_pPtrs[n] != pValue){
            _pPtrs[position++] = _pPtrs[n];
         }
      }
      // 清空尾部数据
      for(TInt n = position; n < _count; n++){
         _pPtrs[n] = NULL;
      }
      // 设置个数
      _count = position;
   }
   //------------------------------------------------------------
   // <T>设置指定索引位置的数据。</T>
   MO_INLINE void Set(TInt index, T* pValue){
      MO_ASSERT_RANGE(index, 0, _count);
      _pPtrs[index] = pValue;
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
   // <T>设置数据长度。</T>
   // <P>长度变小后，多余的内容将丢失。如果存储的是指针，一定要自己先释放多余的对象后，再缩小长度。
   MO_INLINE void EnsureCount(TInt count){
      InnerResize(count, ETrue, EFalse, EFalse);
      _count = count;
   }
   //------------------------------------------------------------
   // <T>清除所有数据。</T>
   MO_INLINE void Clear(){
      if((_pPtrs != NULL) && (_count > 0)){
         for(TInt n = 0; n < _count; n++){
            GPtr<T>& ptr = _pPtrs[n];
            ptr = NULL;
         }
      }
      _count = 0;
   }
   //------------------------------------------------------------
   // <T>释放所有数据。</T>
   MO_INLINE void Free(){
      Clear();
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
class GPtrs : public MPtrs<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   GPtrs(){
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   GPtrs(TInt capacity){
      InnerResize(capacity, EFalse, EFalse, EFalse);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   GPtrs(const T* pValues, TInt count){
      Assign(pValues, count);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   GPtrs(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   GPtrs(const MPtrs<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   GPtrs(const GPtrs<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>析构变长数组。</T>
   MO_ABSTRACT ~GPtrs(){
      this->Clear();
      MO_FREE(this->_pPtrs);
   }
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定ANSI字符串的内容。</T>
   MO_INLINE void operator=(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定ANSI字符串的内容。</T>
   MO_INLINE void operator=(const MPtrs<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定ANSI字符串的内容。</T>
   MO_INLINE void operator=(const GPtrs& values){
      Assign(values.MemoryC(), values.Count());
   }
protected:
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force){
      // 释放内存
      if(size == 0){
         this->_capacity = 0;
         MO_FREE(this->_pPtrs);
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
         capacity = RTypes< GPtr<T> >::CalculateObjectCapacity(this->_capacity, size);
      }
      // 如果收集不成功，则不进行复制数据处理
      GPtr<T>* pAlloc = MO_TYPES_ALLOC(GPtr<T>, capacity);
      MO_ASSERT(pAlloc);
      RTypes< GPtr<T> >::Clear(pAlloc, capacity);
      // 如果存在以前内存
      if(NULL != this->_pPtrs){
         // 如果是缩小内存，则检查长度
         if(this->_count > capacity){
            this->_count = capacity;
         }
         // 复制有效数据
         if(copy && (this->_count > 0)){
            MO_LIB_TYPES_COPY(GPtr<T>, pAlloc, capacity, this->_pPtrs, this->_count);
         }
         // 释放以前内存
         MO_FREE(this->_pPtrs);
      }
      // 设置新的内存
      this->_pPtrs = pAlloc;
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
class GFixPtrs : public MPtrs<T>{
protected:
   T _memory[S];
public:
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   GFixPtrs(){
      InnerInitialize();
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   GFixPtrs(const TPtrC<T>& ptr){
      InnerInitialize();
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   GFixPtrs(const MPtrs<T>& values){
      InnerInitialize();
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   GFixPtrs(const GFixPtrs<T, S>& values){
      InnerInitialize();
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>析构定长数组。</T>
   MO_ABSTRACT ~GFixPtrs(){
      this->Clear();
   }
protected:
   //------------------------------------------------------------
   // <T>初始化内存。</T>
   MO_INLINE void InnerInitialize(){
      this->_pPtrs = _memory;
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
   MO_INLINE void operator=(const MPtrs<T>& values){
      Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>设定当前数据和指定数据指针相等。</T>
   MO_INLINE void operator=(const GFixPtrs<T, S>& values){
      Assign(values.MemoryC(), values.Count());
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_PTRS_H__

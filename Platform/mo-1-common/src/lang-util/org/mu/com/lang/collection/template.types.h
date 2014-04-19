#ifndef __MO_CM_{type_full}_H__
#define __MO_CM_{type_full}_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_INTERFACE_H__
#include "MoCmInterface.h"
#endif // __MO_CM_INTERFACE_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>只读数据迭代器。</T>
//
// @tool
// @history 100318 MAOCY 创建
//============================================================
template <typename T>
class T{type}IteratorC{
protected:
   /// @attribute 当前位置
   TInt _position;
   /// @attribute {length_label}
   TInt _{length};
   /// @attribute 数据内存
   T* _pMemory;
public:
   //------------------------------------------------------------
   // <T>构造只读数据迭代器。</T>
   T{type}IteratorC(){
      _position = 0;
      _{length} = 0;
      MO_CLEAR(_pMemory);
   }
   //------------------------------------------------------------
   // <T>构造只读数据迭代器。</T>
   T{type}IteratorC(const T{type}IteratorC& iterator){
      _position = iterator._position;
      _{length} = iterator._{length};
      _pMemory = iterator._pMemory;
   }
   //------------------------------------------------------------
   // <T>构造只读数据迭代器。</T>
   T{type}IteratorC(TInt length, T* pMemory, TInt position = -1){
      _position = position;
      _{length} = length;
      _pMemory = pMemory;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const T& operator *() const ){
      MO_ASSERT_RANGE(_position, 0, _{length});
      return _pMemory[_position];
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( T& operator *() ){
      MO_ASSERT_RANGE(_position, 0, _{length});
      return _pMemory[_position];
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const T operator->() const ){
      MO_ASSERT_RANGE(_position, 0, _{length});
      return _pMemory[_position];
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( T operator->() ){
      MO_ASSERT_RANGE(_position, 0, _{length});
      return _pMemory[_position];
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const TBool operator==(const T& value) const ){
      MO_ASSERT_RANGE(_position, 0, _{length});
      return (_pMemory[_position] == value);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const TBool operator!=(const T& value) const ){
      MO_ASSERT_RANGE(_position, 0, _{length});
      return (_pMemory[_position] != value);
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE( TBool IsEmpty() const ){
      return (0 == _{length});
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE( TInt length() const ){
      return _{length};
   }
   //------------------------------------------------------------
   // <T>获得数据位置。</T>
   MO_INLINE( TInt Position() const ){
      return _position;
   }
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE( TBool Equals(T value) const ){
      MO_ASSERT_RANGE(_position, 0, _{length});
      return (_pMemory[_position] == value);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const T& GetC() const ){
      MO_ASSERT_RANGE(_position, 0, _{length});
      return _pMemory[_position];
   }
public:
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasNext() const ){
      if(_{length} > 0){
         return (_position + 1) < _{length};
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasPrior() const ){
      if(_{length} > 0){
         return (_position - 1) >= 0;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>移动到下一个位置。</T>
   MO_INLINE( TBool Next() ){
      if((_position + 1) < _{length}){
         _position++;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE( TBool Prior() ){
      if((_position - 1) >= 0){
         _position--;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>重置到开始位置。</T>
   MO_INLINE( void ResetFirst() ){
      _position = -1;
   }
   //------------------------------------------------------------
   // <T>重置到结束位置。</T>
   MO_INLINE( void ResetLast() ){
      _position = _{length};
   }
};

//============================================================
// <T>数据迭代器。</T>
//
// @tool
// @history 100318 MAOCY 创建
//============================================================
template <typename T>
class T{type}Iterator{
protected:
   /// @attribute 当前位置
   TInt _position;
   /// @attribute {length_label}
   TInt* _p{Length};
   /// @attribute 数据内存
   T* _pMemory;
public:
   //------------------------------------------------------------
   // <T>构造数据迭代器。</T>
   T{type}Iterator(){
      _position = 0;
      MO_CLEAR(_p{Length});
      MO_CLEAR(_pMemory);
   }
   //------------------------------------------------------------
   // <T>构造数据迭代器。</T>
   T{type}Iterator(const T{type}Iterator& iterator){
      _position = iterator._position;
      _p{Length} = iterator._p{Length};
      _pMemory = iterator._pMemory;
   }
   //------------------------------------------------------------
   // <T>构造数据迭代器。</T>
   T{type}Iterator(TInt* p{Length}, T* pMemory, TInt position = -1){
      _position = position;
      _p{Length} = p{Length};
      _pMemory = pMemory;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const T& operator *() const ){
      MO_ASSERT(_p{Length});
      MO_ASSERT_RANGE(_position, 0, *_p{Length});
      return _pMemory[_position];
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( T& operator *() ){
      MO_ASSERT(_p{Length});
      MO_ASSERT_RANGE(_position, 0, *_p{Length});
      return _pMemory[_position];
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const T operator->() const ){
      MO_ASSERT(_p{Length});
      MO_ASSERT_RANGE(_position, 0, *_p{Length});
      return _pMemory[_position];
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( T operator->() ){
      MO_ASSERT(_p{Length});
      MO_ASSERT_RANGE(_position, 0, *_p{Length});
      return _pMemory[_position];
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const TBool operator==(const T& value) const ){
      MO_ASSERT(_p{Length});
      MO_ASSERT_RANGE(_position, 0, *_p{Length});
      return (_pMemory[_position] == value);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const TBool operator!=(const T& value) const ){
      MO_ASSERT(_p{Length});
      MO_ASSERT_RANGE(_position, 0, *_p{Length});
      return (_pMemory[_position] != value);
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE( TBool IsEmpty() const ){
      MO_ASSERT(_p{Length});
      TInt length = 0;
      if(NULL != _p{Length}){
         length = *_p{Length};
      }
      return (0 == length);
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE( TInt Length() const ){
      MO_ASSERT(_p{Length});
      TInt length = 0;
      if(NULL != _p{Length}){
         length = *_p{Length};
      }
      return length;
   }
   //------------------------------------------------------------
   // <T>获得数据位置。</T>
   MO_INLINE( TInt Position() const ){
      return _position;
   }
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE( TBool Equals(T value) const ){
      MO_ASSERT(_p{Length});
      MO_ASSERT_RANGE(_position, 0, *_p{Length});
      MO_ASSERT(_pMemory);
      return (_pMemory[_position] == value);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const T& GetC() const ){
      MO_ASSERT(_p{Length});
      MO_ASSERT_RANGE(_position, 0, *_p{Length});
      MO_ASSERT(_pMemory);
      return _pMemory[_position];
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( T& Get() ){
      MO_ASSERT(_p{Length});
      MO_ASSERT_RANGE(_position, 0, *_p{Length});
      MO_ASSERT(_pMemory);
      return _pMemory[_position];
   }
   //------------------------------------------------------------
   // <T>设置当前位置的数据内容。</T>
   MO_INLINE( void Set(T value) ){
      MO_ASSERT(_p{Length});
      MO_ASSERT_RANGE(_position, 0, *_p{Length});
      MO_ASSERT(_pMemory);
      _pMemory[_position] = value;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasNext() const ){
      MO_ASSERT(_p{Length});
      TInt length = 0;
      if(NULL != _p{Length}){
         length = *_p{Length};
      }
      if(length > 0){
         return (_position + 1) < length;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasPrior() const ){
      MO_ASSERT(_p{Length});
      TInt length = 0;
      if(NULL != _p{Length}){
         length = *_p{Length};
      }
      if(length > 0){
         return (_position - 1) >= 0;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>移动到下一个位置。</T>
   MO_INLINE( TBool Next() ){
      MO_ASSERT(_p{Length});
      if((_position + 1) < *_p{Length}){
         _position++;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE( TBool Prior() ){
      if((_position - 1) >= 0){
         _position--;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>重置到开始位置。</T>
   MO_INLINE( void ResetFirst() ){
      _position = -1;
   }
   //------------------------------------------------------------
   // <T>重置到结束位置。</T>
   MO_INLINE( void ResetLast() ){
      MO_ASSERT(_p{Length});
      _position = *_p{Length};
   }
   //------------------------------------------------------------
   // <T>删除当前位置的数据内容。</T>
   MO_INLINE( void Delete() ){
      MO_ASSERT(_p{Length});
      TInt length = *_p{Length};
      MO_ASSERT_RANGE(_position, 0, length);
      T value = _pMemory[index];
      if(_position != length - 1){
         RTypes<T>::CopySafe(_pMemory + _position, _pMemory + _position + 1, length - _position);
      }
      *_p{Length} = length - 1;
      return value;
   }
};

//============================================================
// <T>数组对象的管理接口。</T>
// <P>是数组的只读接口对象，是一个虚类，不允许创建实例。</P>
//
// @manager
// @type T 数据类型
// @history 120616 MAOCY 创建
//============================================================
template <typename T>
class M{type}{
public:
   // @type 比较函数
   typedef TInt (*HComparer)(const T* pSource, const T* pTarget, TAny* pCondition);
   // @type 只读迭代器定义
   typedef T{type}IteratorC<T> TIteratorC;
   // @type 迭代器定义
   typedef T{type}Iterator<T> TIterator;
protected:
   // @attribute 数据容量
   TInt _capacity;
   // @attribute 数据长度
   TInt _{length};
   // @attribute 数据内存
   T* _pMemory;
public:
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   M{type}(){
      _capacity = 0;
      _{length} = 0;
      MO_CLEAR(_pMemory);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   M{type}(TInt capacity){
      _capacity = capacity;
      _{length} = 0;
      _pMemory = InnerAlloc(capacity);
   }
   //------------------------------------------------------------
   // <T>析构变长数组。</T>
   ~M{type}(){
      MO_MEM_FREE(_pMemory);
   }
protected:
   //------------------------------------------------------------
   // <T>收集内存。</T>
   MO_INLINE( T* InnerAlloc(TInt size) ){
      return (T*)MO_MEM_ALLOC(sizeof(T) * size);
   }
   //------------------------------------------------------------
   // <T>释放内存。</T>
   MO_INLINE( void InnerFree(T* pMemory) ){
      MO_MEM_FREE(pMemory);
   }
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   void InnerResize(TInt size, TBool extend, TBool copy){
      if(size > _capacity){
         // 重新计算内存容量
         TInt capacity = size;
         if(extend){
            // 当内存不足时，扩大1.5倍内存，但收集量不得小于默认值<C>MO_MEMORY_CAPACITY</C>的内存。
            capacity = MO_LG_MAX(MO_MEMORY_CAPACITY, capacity);
            if(NULL != _pMemory){
               capacity += capacity >> 1;
            }
         }
         // 如果收集不成功，则不进行复制数据处理
         T* pAlloc = InnerAlloc(capacity);
         MO_ASSERT(pAlloc);
         // 如果以前内存有数据，则复制以前内存内容
         if(NULL != _pMemory){
            if(copy && (_{length} > 0)){
               RTypes<T>::CopyFast(pAlloc, {memory}, _{length});
            }
            InnerFree({memory});
         }
         // 设置新的内存
         _capacity = capacity;
         {memory} = pAlloc;
      }
   }
public:
   //------------------------------------------------------------
   // <T>保证内存大小，不保留以前内容。</T>
   MO_INLINE( void EnsureSize(TInt size) ){
      InnerResize(size, EFalse, EFalse);
   }
   //------------------------------------------------------------
   // <T>保证内存大小扩张，保留以前内容。</T>
   MO_INLINE( void EnsureResize(TInt size) ){
      InnerResize(size, ETrue, ETrue);
   }
{source_1}
public:
   //------------------------------------------------------------
   // <T>从源数组替换为目标数组。</T>
   void Replace(const T* pSource, TInt sourceLength, const T* pTarget, TInt targetLength){
      if(targetLength > sourceLength){
         // 需要重置大小
         TInt count = RTypes<T>::Count({memory}, _{length}, pSource, sourceLength);
         TInt size = (targetLength - sourceLength) * count + _{length};
         T* pBuffer = InnerAlloc(size);
         TInt length = RTypes<T>::Replace({memory}, _{length}, pSource, sourceLength, pTarget, targetLength, pBuffer, size);
         EnsureResize(length);
         RTypes<T>::CopyFast({memory}, pBuffer, length);
         _{length} = length;
         InnerFree(pBuffer);
      }else{
         _{length} = RTypes<T>::Replace({memory}, _{length}, pSource, sourceLength, pTarget, targetLength, {memory}, _capacity);
      }
   }
};
{source_type_1}

//============================================================
// <T>变长栈数组。</T>
//
// @tool
// @type T 数据类型
// @history 091225 MAOCY 创建
//============================================================
template <typename T>
class T{type} : public M{type}<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   T{type}(){
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   T{type}(TInt capacity) : M{type}<T>(capacity){
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   T{type}(const T* pValues, TInt length){
      this->Assign(pValues, length);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   T{type}(const TPtrC<T>& ptr){
      this->Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   T{type}(const M{type}<T>& values){
      this->Assign(values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   T{type}(const T{type}<T>& values){
      this->Assign(values.MemoryC(), values.Length());
   }
public:
   //------------------------------------------------------------
   // <T>接收一个数据指针。</T>
   MO_INLINE( void operator=(const TPtrC<T>& ptr) ){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>接收一个数组对象。</T>
   MO_INLINE( void operator=(const M{type}<T>& values) ){
      Assign(values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>接收一个数组对象。</T>
   MO_INLINE( void operator=(const T{type}<T>& values) ){
      Assign(values.MemoryC(), values.Length());
   }
};
{source_type_2}
//============================================================
// <T>变长堆数组。</T>
//
// @tool
// @type T 数据类型
// @history 091225 MAOCY 创建
//============================================================
template <typename T>
class F{type} :
      public FFree,
      public M{type}<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   F{type}(){
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   F{type}(TInt capacity) : M{type}<T>(capacity){
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   F{type}(const T* pValues, TInt length){
      this->Assign(pValues, length);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   F{type}(const TPtrC<T>& ptr){
      this->Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   F{type}(const F{type}<T>& values){
      this->Assign(values.MemoryC(), values.Length());
   }
public:
   //------------------------------------------------------------
   // <T>接收一个数据指针。</T>
   MO_INLINE( void operator=(const TPtrC<T>& ptr) ){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>接收一个数组对象。</T>
   MO_INLINE( void operator=(const M{type}<T>& values) ){
      Assign(values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>接收一个数组对象。</T>
   MO_INLINE( void operator=(const F{type}<T>& values) ){
      Assign(values.MemoryC(), values.Length());
   }
};
{source_type_3}
//============================================================
// <T>定长数组。</T>
//
// @tool
// @type T 数据类型
// @param S 数据长度
// @history 091207 MAOCY 创建
//============================================================
template <typename T, TInt S>
class TFix{type}{
public:
   // @type 比较函数
   typedef TInt (*HComparer)(const T* pSource, const T* pTarget, TAny* pCondition);
   // @type 只读迭代器定义
   typedef T{type}IteratorC<T> TIteratorC;
   // @type 迭代器定义
   typedef T{type}Iterator<T> TIterator;
protected:
   TInt _{length};
   T _memory[S];
public:
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFix{type}(){
      _{length} = 0;
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFix{type}(T* pMemory, TInt length){
      this->Assign(pMemory, length);
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFix{type}(const TPtrC<T>& ptr){
      this->Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFix{type}(const M{type}<T>& values){
      this->Assign(values.MemoryC(), values.{Length}());
   }
   //------------------------------------------------------------
   // <T>构造定长数组。</T>
   TFix{type}(const TFix{type}<T, S>& values){
      this->Assign(values.MemoryC(), values.{Length}());
   }
public:
   //------------------------------------------------------------
   // <T>接收一个数组对象。</T>
   MO_INLINE( void operator=(const TFix{type}<T, S>& values) ){
      this->Assign(values.MemoryC(), values.{Length}());
   }
public:
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   void InnerResize(TInt size, TBool extend, TBool copy){
      MO_ASSERT(size < S);
   }
{source_2}
};
//------------------------------------------------------------
#define MO_DEF_TFIX{type_full}(C, T) \
template <TInt S> \
class MO_CM_DECLARE C : public TFix{type}<T, S>{ \
public: \
   C(){ \
   } \
   C(const T* pValues, TInt length) : TFix{type}<T, S>(pValues, length){ \
   } \
   C(const TPtrC<T>& ptr) : TFix{type}<T, S>(ptr){ \
   } \
   C(const M{type}<T>& values) : TFix{type}<T, S>(values){ \
   } \
   C(const C<S>& values){ \
      this->Append(values); \
   } \
};
{source_type_4}
MO_NAMESPACE_END

#endif // __MO_CM_{type_full}_H__

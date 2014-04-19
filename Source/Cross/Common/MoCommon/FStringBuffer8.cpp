#include "MoCmString8.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造字符串缓冲。</T>
//============================================================
FStringBuffer8::FStringBuffer8(){
}

//============================================================
// <T>构造字符串缓冲。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
FStringBuffer8::FStringBuffer8(TChar8C* pValue, TInt length){
   Assign(pValue, length);
}

//============================================================
// <T>构造字符串缓冲。</T>
//
// @param ptr 字符串指针
//============================================================
FStringBuffer8::FStringBuffer8(const TString8PtrC& ptr){
   Assign(ptr.MemoryC(), ptr.Length());
}

//============================================================
// <T>构造字符串缓冲。</T>
//
// @param value 字符串
//============================================================
FStringBuffer8::FStringBuffer8(const MString8& value){
   Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>析构字符串缓冲。</T>
//============================================================
FStringBuffer8::~FStringBuffer8(){
   MO_FREE(_pMemory);
}

//============================================================
// <T>调整内存大小。</T>
//
// @param size 大小
// @param copy 复制
// @param extends 扩展
//============================================================
void FStringBuffer8::InnerResize(TInt size, TBool copy, TBool extends, TBool force){
   if(size > _capacity){
      // 当内存不足时，重新计算内存容量
      TInt capacity = size;
      if(extends){
         capacity = RTypes<TChar8>::CalculateTypeCapacity(_capacity, size);
      }
      // 如果收集不成功，则不进行复制数据处理
      TChar8* pAlloc = MO_TYPES_ALLOC(TChar8, capacity);
      MO_ASSERT(pAlloc);
      // 如果存在以前内存
      if(NULL != _pMemory){
         // 如果是缩小内存，则检查长度
         if(_length > capacity){
            _length = capacity;
         }
         // 复制有效数据
         if(copy && (_length > 0)){
            MO_LIB_TYPES_COPY(TChar8, pAlloc, capacity, _pMemory, _length);
         }
         // 释放以前内存
         MO_FREE(_pMemory);
      }
      // 设置新的内存
      _pMemory = pAlloc;
      _pMemory[_length] = 0;
      _capacity = capacity;
   }
}

MO_NAMESPACE_END

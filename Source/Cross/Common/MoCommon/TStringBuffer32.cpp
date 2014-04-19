#include "MoCmString32.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造字符串缓冲。</T>
//============================================================
TStringBuffer32::TStringBuffer32(){
}

//============================================================
// <T>构造字符串缓冲。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
TStringBuffer32::TStringBuffer32(TChar32C* pValue, TInt length){
   Assign(pValue, length);
}

//============================================================
// <T>构造字符串缓冲。</T>
//
// @param ptr 字符串指针
//============================================================
TStringBuffer32::TStringBuffer32(const TString32PtrC& ptr){
   Assign(ptr.MemoryC(), ptr.Length());
}

//============================================================
// <T>构造字符串缓冲。</T>
//
// @param value 字符串
//============================================================
TStringBuffer32::TStringBuffer32(const MString32& value){
   Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>析构字符串缓冲。</T>
//============================================================
TStringBuffer32::~TStringBuffer32(){
   MO_FREE(_pMemory);
}

//============================================================
// <T>调整内存大小。</T>
//
// @param size 大小
// @param copy 复制
// @param extends 扩展
//============================================================
void TStringBuffer32::InnerResize(TInt size, TBool copy, TBool extends, TBool force){
   if(size > _capacity){
      // 当内存不足时，重新计算内存容量
      TInt capacity = size;
      if(extends){
         capacity = RTypes<TChar32>::CalculateTypeCapacity(_capacity, size);
      }
      // 如果收集不成功，则不进行复制数据处理
      TChar32* pAlloc = MO_TYPES_ALLOC(TChar32, capacity);
      MO_ASSERT(pAlloc);
      // 如果存在以前内存
      if(NULL != _pMemory){
         // 如果是缩小内存，则检查长度
         if(_length > capacity){
            _length = capacity;
         }
         // 复制有效数据
         if(copy && (_length > 0)){
            MO_LIB_TYPES_COPY(TChar32, pAlloc, capacity, _pMemory, _length);
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

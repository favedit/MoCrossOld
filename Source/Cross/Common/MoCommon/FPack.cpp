#include "MoCmProperty.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造字符串打包工具。</T>
//============================================================
FPack::FPack(){
}

//============================================================
// <T>构造字符串打包工具。</T>
//============================================================
FPack::FPack(EPropertyPack packCd){
   _packCd = packCd;
}

//============================================================
// <T>析构字符串打包工具。</T>
//============================================================
FPack::~FPack(){
   MO_FREE(_pMemory);
}

//============================================================
// <T>调整内存大小。</T>
// <P>当第一次使用是，会收集第一块内存。
// 当前大小内存不足时，会重新收集指定大小1.5倍的内存存放数据，暂未使用的作为缓冲使用。
// 当内存不再发生大小上改变的时候，可以使用压缩函数释放掉所有未使用的缓冲内存。</P>
//
// @param size 大小
//============================================================
void FPack::InnerResize(TInt size, TBool copy, TBool extends){
   if(size > _capacity){
      // 当内存不足时，重新计算内存容量
      TInt capacity = size;
      if(extends){
         capacity = RTypes<TChar>::CalculateTypeCapacity(_capacity, size);
      }
      // 如果收集不成功，则不进行复制数据处理
      TChar* pAlloc = MO_TYPES_ALLOC(TChar, capacity);
      MO_ASSERT(pAlloc);
      // 如果存在以前内存
      if(NULL != _pMemory){
         // 如果是缩小内存，则检查长度
         if(_length > capacity){
            _length = capacity;
         }
         // 复制有效数据
         if(copy && (_length > 0)){
            MO_LIB_TYPES_COPY(TChar, pAlloc, capacity, _pMemory, _length);
         }
         // 释放以前内存
         MO_FREE(_pMemory);
      }
      // 设置新的内存
      _pMemory = pAlloc;
      _capacity = capacity;
   }
}

MO_NAMESPACE_END

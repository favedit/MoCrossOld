#include "MoCmString8.h"
#include "MoCmString16.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造字符串。</T>
//============================================================
TString8::TString8(){
}

//============================================================
// <T>构造字符串。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
TString8::TString8(TChar8C* pValue, TInt length){
   Assign(pValue, length);
}

//============================================================
// <T>构造字符串。</T>
//
// @param ptr 字符串指针
//============================================================
TString8::TString8(const TString8PtrC& ptr){
   Assign(ptr.MemoryC(), ptr.Length());
}

//============================================================
// <T>构造字符串。</T>
//
// @param value 字符串
//============================================================
TString8::TString8(const MString8& value){
   Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>构造字符串。</T>
//
// @param value 字符串
//============================================================
TString8::TString8(const TString8& value){
   Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>析构字符串。</T>
//============================================================
TString8::~TString8(){
   MO_FREE(_pMemory);
}

//============================================================
// <T>调整内存大小。</T>
//
// @param size 大小
// @param copy 复制
// @param extends 扩展
//============================================================
void TString8::InnerResize(TInt size, TBool copy, TBool extends, TBool force){
   if(size != _capacity){
      // 如果收集不成功，则不进行复制数据处理
      TChar8* pAlloc = MO_TYPES_ALLOC(TChar8, size);
      MO_ASSERT(pAlloc);
      // 如果存在以前内存
      if(NULL != _pMemory){
         // 如果是缩小内存，则检查长度
         if(_length > size){
            _length = size;
         }
         // 复制有效数据
         if(copy && (_length > 0)){
            MO_LIB_TYPES_COPY(TChar8, pAlloc, size, _pMemory, _length);
         }
         // 释放以前内存
         MO_FREE(_pMemory);
      }
      // 设置新的内存
      _pMemory = pAlloc;
      _pMemory[_length] = 0;
      _capacity = size;
   }
}

//============================================================
// <T>将指定UNICODE字符串指针对象添加到当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void TString8::Append8(TChar8C* pValue){
	//   TSize length = RString16::ConvertToString8(pValue);
	//   if(0 == length){
	//      MO_ERROR(TC("Convert error!"));
	//   }else{
	//      InnerInitialize(length + _length);
	//      RString16::ConvertToString8(_pMemory + length, length, pValue);
	//      _length = _length + length;
	//   }
}

//============================================================
// <T>将指定UNICODE字符串指针对象添加到当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void TString8::Append16(TChar16C* pValue){
//   TSize length = RString16::ConvertToString8(pValue);
//   if(0 == length){
//      MO_ERROR(TC("Convert error!"));
//   }else{
//      InnerInitialize(length + _length);
//      RString16::ConvertToString8(_pMemory + length, length, pValue);
//      _length = _length + length;
//   }
}

//============================================================
// <T>将指定UNICODE字符串指针对象添加到当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void TString8::Append32(TChar32C* pValue){
//   TInt length = RString32::ConvertToString8(pValue);
//   if(-1 == length){
//      MO_ERROR("convert error!");
//   }else{
//      InnerInitialize(length + _length);
//      RString32::ConvertToString8(_pMemory + length, length, pValue);
//      _length = _length + length;
//   }
}

MO_NAMESPACE_END

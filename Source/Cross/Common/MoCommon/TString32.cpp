#include "MoCmString32.h"
#include "MoCmString8.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造字符串。</T>
//============================================================
TString32::TString32(){
}

//============================================================
// <T>构造字符串。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
TString32::TString32(TChar32C* pValue, TInt length){
	Assign(pValue, length);
}

//============================================================
// <T>构造字符串。</T>
//
// @param ptr 字符串指针
//============================================================
TString32::TString32(const TString32PtrC& ptr){
	Assign(ptr.MemoryC(), ptr.Length());
}

//============================================================
// <T>构造字符串。</T>
//
// @param value 字符串
//============================================================
TString32::TString32(const MString32& value){
	Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>构造字符串。</T>
//
// @param value 字符串
//============================================================
TString32::TString32(const TString32& value){
	Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>析构字符串。</T>
//============================================================
TString32::~TString32(){
	MO_FREE(_pMemory);
}

//============================================================
// <T>调整内存大小。</T>
//
// @param size 大小
// @param copy 复制
// @param extends 扩展
//============================================================
void TString32::InnerResize(TInt size, TBool copy, TBool extends, TBool force){
	if(size != _capacity){
		// 如果收集不成功，则不进行复制数据处理
		TChar32* pAlloc = MO_TYPES_ALLOC(TChar32, size);
		MO_ASSERT(pAlloc);
		// 如果存在以前内存
		if(NULL != _pMemory){
			// 如果是缩小内存，则检查长度
			if(_length > size){
				_length = size;
			}
			// 复制有效数据
			if(copy && (_length > 0)){
				MO_LIB_TYPES_COPY(TChar32, pAlloc, size, _pMemory, _length);
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
void TString32::Append8(TChar8C* pValue){
}

//============================================================
void TString32::Append16(TChar32C* pValue){
}

//============================================================
void TString32::Append32(TChar32C* pValue){
}
//
//
////============================================================
//// <T>将指定ANSI字符串指针对象赋值给当前字符串。</T>
////
//// @param pValue ANSI字符串指针
////============================================================
//void TString32::AssignAnsi(TChar8C* pValue){
//   TSize length = RString8::ConvertToString32(pValue);
//   if(-1 == length){
//      MO_ERROR(TC("Convert error!"));
//   }else{
//      InnerInitialize(length);
//      Clear();
//      RString8::ConvertToString32(_pMemory, length, pValue);
//      _length = length;
//   }
//}
//
////============================================================
//// <T>将指定ANSI字符串指针对象添加到当前字符串。</T>
////
//// @param pValue ANSI字符串指针
////============================================================
//void TString32::AppendAnsi(TChar8C* pValue){
//   MO_ASSERT(pValue);
//   TSize length = RString8::ConvertToString32(pValue);
//   if(-1 == length){
//      MO_ERROR(TC("Convert error!"));
//   }else{
//      InnerInitialize(length + _length);
//      RString8::ConvertToString32(_pMemory + length, length, pValue);
//      _length = _length + length;
//   }
//}

MO_NAMESPACE_END

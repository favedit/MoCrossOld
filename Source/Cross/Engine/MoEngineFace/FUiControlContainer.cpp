#include "MoFmCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造控件容器。</T>
//============================================================
FUiControlContainer::FUiControlContainer(){
}

//============================================================
// <T>析构控件容器。</T>
//============================================================
FUiControlContainer::~FUiControlContainer(){
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
//============================================================
TResult FUiControlContainer::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取属性
   TResult result = FUiControl::Unserialize(pInput);
   return result;
}

MO_NAMESPACE_END

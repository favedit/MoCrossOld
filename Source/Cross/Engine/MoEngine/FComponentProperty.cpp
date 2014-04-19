#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造组件属性。</T>
//============================================================
FComponentProperty::FComponentProperty(){
}

//============================================================
// <T>析构组件属性。</T>
//============================================================
FComponentProperty::~FComponentProperty(){
}

//============================================================
// <T>序列化数据内容到输出流。</T>
//
// @param pOutput 输出流
//============================================================
TResult FComponentProperty::Serialize(IDataOutput* pOutput){
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
//============================================================
TResult FComponentProperty::Unserialize(IDataInput* pInput){
   return ESuccess;
}

MO_NAMESPACE_END

#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FComponentProperties, FInstance);

//============================================================
// <T>构造组件属性集合。</T>
//============================================================
FComponentProperties::FComponentProperties(){
}

//============================================================
// <T>析构组件属性集合。</T>
//============================================================
FComponentProperties::~FComponentProperties(){
}

//============================================================
// <T>序列化数据内容到输出流。</T>
//
// @param pOutput 输出流
//============================================================
TResult FComponentProperties::Serialize(IDataOutput* pOutput){
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
//============================================================
TResult FComponentProperties::Unserialize(IDataInput* pInput){
   return ESuccess;
}

MO_NAMESPACE_END

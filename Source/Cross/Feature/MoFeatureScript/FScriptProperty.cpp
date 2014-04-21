#include "MoScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScriptProperty, FScriptObject);

//============================================================
// <T>构造脚本属性。</T>
//============================================================
FScriptProperty::FScriptProperty(){
   MO_CLEAR(_pScriptClass);
}

//============================================================
// <T>析构脚本属性。</T>
//============================================================
FScriptProperty::~FScriptProperty(){
}

//============================================================
// <T>获得属性内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FScriptProperty::Get(FScriptInstance* pInstance, TAny* pValue){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>设置属性内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FScriptProperty::Set(FScriptInstance* pInstance, TAny* pValue){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

MO_NAMESPACE_END

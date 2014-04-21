#include "MoScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScriptField, FScriptObject);

//============================================================
// <T>构造脚本字段。</T>
//============================================================
FScriptField::FScriptField(){
   MO_CLEAR(_pScriptClass);
}

//============================================================
// <T>析构脚本字段。</T>
//============================================================
FScriptField::~FScriptField(){
}

//============================================================
// <T>获得字段内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FScriptField::Get(FScriptInstance* pInstance, TAny* pValue){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>设置字段内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FScriptField::Set(FScriptInstance* pInstance, TAny* pValue){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>获得字段字符串内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FScriptField::GetString(FScriptInstance* pInstance, MString* pValue){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>设置字段字符串内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FScriptField::SetString(FScriptInstance* pInstance, TCharC* pValue){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

MO_NAMESPACE_END

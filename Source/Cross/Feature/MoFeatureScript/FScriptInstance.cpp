#include "MoScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScriptInstance, FInstance);

//============================================================
// <T>构造脚本实例。</T>
//============================================================
FScriptInstance::FScriptInstance(){
   MO_CLEAR(_pScriptClass);
}

//============================================================
// <T>析构脚本实例。</T>
//============================================================
FScriptInstance::~FScriptInstance(){
}

//============================================================
// <T>获得字段内容。</T>
//
// @param pFileName 字段名称
// @param pFieldValue 字段内容
// @return 处理结果
//============================================================
TResult FScriptInstance::FieldGet(TCharC* pFileName, TAny* pFieldValue){
   MO_CHECK(_pScriptClass, return ENull);
   FScriptField* pField = _pScriptClass->FindField(pFileName);
   MO_CHECK(pField, return ENull);
   TResult resultCd = pField->Get(this, pFieldValue);
   return resultCd;
}

//============================================================
// <T>设置字段内容。</T>
//
// @param pFileName 字段名称
// @param pFieldValue 字段内容
// @return 处理结果
//============================================================
TResult FScriptInstance::FieldSet(TCharC* pFileName, TAny* pFieldValue){
   MO_CHECK(_pScriptClass, return ENull);
   FScriptField* pField = _pScriptClass->FindField(pFileName);
   MO_CHECK(pField, return ENull);
   TResult resultCd = pField->Set(this, pFieldValue);
   return resultCd;
}

//============================================================
// <T>获得字段字符串内容。</T>
//
// @param pFileName 字段名称
// @param pFieldValue 字段内容
// @return 处理结果
//============================================================
TResult FScriptInstance::FieldGetString(TCharC* pFileName, MString* pFieldValue){
   MO_CHECK(_pScriptClass, return ENull);
   FScriptField* pField = _pScriptClass->FindField(pFileName);
   MO_CHECK(pField, return ENull);
   TResult resultCd = pField->GetString(this, pFieldValue);
   return resultCd;
}

//============================================================
// <T>设置字段字符串内容。</T>
//
// @param pFileName 字段名称
// @param pFieldValue 字段内容
// @return 处理结果
//============================================================
TResult FScriptInstance::FieldSetString(TCharC* pFileName, TCharC* pFieldValue){
   MO_CHECK(_pScriptClass, return ENull);
   FScriptField* pField = _pScriptClass->FindField(pFileName);
   MO_CHECK(pField, return ENull);
   TResult resultCd = pField->SetString(this, pFieldValue);
   return resultCd;
}

MO_NAMESPACE_END

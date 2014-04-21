#include "MoPsmScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMonoField, FScriptField);

//============================================================
// <T>构造脚本字段。</T>
//============================================================
FMonoField::FMonoField(){
   MO_CLEAR(_pMonoField);
}

//============================================================
// <T>析构脚本字段。</T>
//============================================================
FMonoField::~FMonoField(){
}

//============================================================
// <T>获得字段内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FMonoField::Get(FScriptInstance* pInstance, TAny* pValue){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pValue, return ENull);
   FMonoInstance* pMonoInstance = pInstance->Convert<FMonoInstance>();
   mono_field_get_value(pMonoInstance->NativeObject(), _pMonoField, pValue);
   return ESuccess;
}

//============================================================
// <T>设置字段内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FMonoField::Set(FScriptInstance* pInstance, TAny* pValue){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pValue, return ENull);
   FMonoInstance* pMonoInstance = pInstance->Convert<FMonoInstance>();
   mono_field_set_value(pMonoInstance->NativeObject(), _pMonoField, pValue);
   return ESuccess;
}

//============================================================
// <T>获得字段字符串内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FMonoField::GetString(FScriptInstance* pInstance, MString* pValue){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pValue, return ENull);
   FMonoInstance* pMonoInstance = pInstance->Convert<FMonoInstance>();
   MonoString* pMonoString = NULL;
   mono_field_get_value(pMonoInstance->NativeObject(), _pMonoField, &pMonoString);
	TChar* pString = mono_string_to_utf8(pMonoString);
   pValue->Assign(pString);
	mono_free(pString);
   return ESuccess;
}

//============================================================
// <T>设置字段字符串内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FMonoField::SetString(FScriptInstance* pInstance, TCharC* pValue){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pValue, return ENull);
   FMonoMachine* pMachine = (FMonoMachine*)_pScriptClass->ScriptMachine();
   MonoString* pMonoString =mono_string_new(pMachine->NativeDomain(), pValue);
   FMonoInstance* pMonoInstance = pInstance->Convert<FMonoInstance>();
   mono_field_set_value(pMonoInstance->NativeObject(), _pMonoField, &pMonoString);
   return ESuccess;
}

//============================================================
// <T>打开处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonoField::Open(){
   TResult resultCd = FScriptField::Open();
   // 获得类
   MO_CHECK(_pScriptClass, return ENull);
   FMonoClass* pClass = _pScriptClass->Convert<FMonoClass>();
   _pMonoField = mono_class_get_field_from_name(pClass->NativeClass(), _name);
   MO_CHECK(_pMonoField, return NULL);
   return resultCd;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonoField::Close(){
   TResult resultCd = FScriptField::Close();
   MO_CLEAR(_pMonoField);
   return resultCd;
}

MO_NAMESPACE_END

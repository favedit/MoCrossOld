#include "MoPsmScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMonoProperty, FScriptProperty);

//============================================================
// <T>构造脚本属性。</T>
//============================================================
FMonoProperty::FMonoProperty(){
   MO_CLEAR(_pMonoProperty);
}

//============================================================
// <T>析构脚本属性。</T>
//============================================================
FMonoProperty::~FMonoProperty(){
}

//============================================================
// <T>获得字段内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FMonoProperty::Get(FScriptInstance* pInstance, TAny* pValue){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pValue, return ENull);
   FMonoInstance* pMonoInstance = pInstance->Convert<FMonoInstance>();
   MonoObject* pException = NULL;
   MonoObject* pObject = mono_property_get_value(_pMonoProperty, pMonoInstance->NativeObject(), NULL, &pException);
   if(pException == NULL){
      MO_FATAL("Get property failure. (class=%s, property=%s)", _pScriptClass->Name(), (TCharC*)_name);
      return EFailure;
   }
   return ESuccess;
}

//============================================================
// <T>设置字段内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FMonoProperty::Set(FScriptInstance* pInstance, TAny* pValue){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pValue, return ENull);
   FMonoInstance* pMonoInstance = pInstance->Convert<FMonoInstance>();
   MonoObject* pException = NULL;
   mono_property_set_value(_pMonoProperty, pMonoInstance->NativeObject(), (TAny**)pValue, &pException);
   if(pException == NULL){
      MO_FATAL("Set property failure. (class=%s, property=%s)", _pScriptClass->Name(), (TCharC*)_name);
      return EFailure;
   }
   return ESuccess;
}

//============================================================
// <T>获得字段字符串内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FMonoProperty::GetString(FScriptInstance* pInstance, MString* pValue){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pValue, return ENull);
 //  FMonoInstance* pMonoInstance = pInstance->Convert<FMonoInstance>();
 //  MonoString* pMonoString = NULL;
 //  mono_field_get_value(pMonoInstance->NativeObject(), _pMonoProperty, &pMonoString);
	//TChar* pString = mono_string_to_utf8(pMonoString);
 //  pValue->Assign(pString);
	//mono_free(pString);
   return ESuccess;
}

//============================================================
// <T>设置字段字符串内容。</T>
//
// @param pInstance 实例
// @param pValue 内容
// @return 处理结果
//============================================================
TResult FMonoProperty::SetString(FScriptInstance* pInstance, TCharC* pValue){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pValue, return ENull);
   //FMonoMachine* pMachine = (FMonoMachine*)_pScriptClass->ScriptMachine();
   //MonoString* pMonoString =mono_string_new(pMachine->NativeDomain(), pValue);
   //FMonoInstance* pMonoInstance = pInstance->Convert<FMonoInstance>();
   //mono_field_set_value(pMonoInstance->NativeObject(), _pMonoProperty, &pMonoString);
   return ESuccess;
}

//============================================================
// <T>打开处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonoProperty::Open(){
   TResult resultCd = FScriptProperty::Open();
   // 获得类
   MO_CHECK(_pScriptClass, return ENull);
   FMonoClass* pClass = _pScriptClass->Convert<FMonoClass>();
   _pMonoProperty = mono_class_get_property_from_name(pClass->NativeClass(), _name);
   MO_CHECK(_pMonoProperty, return NULL);
   return resultCd;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonoProperty::Close(){
   TResult resultCd = FScriptProperty::Close();
   MO_CLEAR(_pMonoProperty);
   return resultCd;
}

MO_NAMESPACE_END

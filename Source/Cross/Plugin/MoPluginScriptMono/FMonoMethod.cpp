#include "MoPsmScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMonoMethod, FScriptMethod);

//============================================================
// <T>构造脚本函数。</T>
//============================================================
FMonoMethod::FMonoMethod(){
   MO_CLEAR(_pMonoMethod);
}

//============================================================
// <T>析构脚本函数。</T>
//============================================================
FMonoMethod::~FMonoMethod(){
}

//============================================================
// <T>调用函数。</T>
//
// @param pInstance 实例
// @param ppResult 结果
// @param paramCount 参数个数
// @param pParameters 参数集合
// @return 处理结果
//============================================================
TResult FMonoMethod::InvokeParamaters(FScriptInstance* pInstance, TAny** ppResult, TInt paramCount, TAny** pParameters){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(paramCount < MO_SCRIPT_METHOD_PARAM_MAXCNT, return EMemory);
   // 调用函数
   FMonoInstance* pMonoInstance = pInstance->Convert<FMonoInstance>();
   MonoObject* pException = NULL;
   MonoObject* pResult = mono_runtime_invoke(_pMonoMethod, pMonoInstance->NativeObject(), pParameters, &pException);
   // 检查例外
   if(pException != NULL){
      MO_FATAL("Invoke method failure. (method=%s, param_count=%d)", (TCharC*)_name, paramCount);
      return EFailure;
   }
   // 设置输出
   if(ppResult != NULL){
      *ppResult = pResult;
   }
   return ESuccess;
}

//============================================================
// <T>打开处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonoMethod::Open(){
   TResult resultCd = FScriptMethod::Open();
   // 获得脚本类
   MO_CHECK(_pScriptClass, return ENull);
   FMonoClass* pClass = _pScriptClass->Convert<FMonoClass>();
   // 根据函数描述查找类对象
   MonoMethodDesc* pDescriptor = mono_method_desc_new(_descriptor, false);
   MO_FATAL_CHECK(pDescriptor, return EFailure, "Create method descriptor failure. (descriptor=%s)", (TCharC*)_descriptor);
   _pMonoMethod = mono_method_desc_search_in_class(pDescriptor, pClass->NativeClass());
   MO_FATAL_CHECK(_pMonoMethod, return EFailure, "Find method in class failure. (class=%s, method=%s)", pClass->Name(), (TCharC*)_descriptor);
   mono_method_desc_free(pDescriptor);
   return resultCd;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonoMethod::Close(){
   TResult resultCd = FScriptMethod::Close();
   MO_CLEAR(_pMonoMethod);
   return resultCd;
}

MO_NAMESPACE_END

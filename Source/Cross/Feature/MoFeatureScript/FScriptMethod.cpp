#include "MoScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScriptMethod, FScriptObject);

//============================================================
// <T>构造脚本函数。</T>
//============================================================
FScriptMethod::FScriptMethod(){
}

//============================================================
// <T>析构脚本函数。</T>
//============================================================
FScriptMethod::~FScriptMethod(){
}

//============================================================
// <T>设置描述。</T>
//
// @param pDescriptor 描述信息
//============================================================
void FScriptMethod::SetDescriptor(TCharC* pDescriptor){
   _descriptor = pDescriptor;
   // 分割类名
   TInt splitter = _name.IndexOf(TC('('));
   if(splitter != ENotFound){
      _name = _descriptor.LeftStrC(splitter);
   }else{
      _name.Clear();
   }
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
TResult FScriptMethod::InvokeParamaters(FScriptInstance* pInstance, TAny** ppResult, TInt paramCount, TAny** pParameters){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>调用函数。</T>
//
// @param pInstance 实例
// @param ppResult 结果
// @param paramCount 参数个数
// @param params 参数集合
// @return 处理结果
//============================================================
TResult FScriptMethod::Invoke(FScriptInstance* pInstance, TAny** ppResult){
   return InvokeParamaters(pInstance, ppResult, 0, NULL);
}

//============================================================
// <T>调用函数。</T>
//
// @param pInstance 实例
// @param ppResult 结果
// @param pParam1 参数1
// @return 处理结果
//============================================================
TResult FScriptMethod::Invoke1(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1){
   TAny* params[] = {pParam1};
   return InvokeParamaters(pInstance, ppResult, 1, params);
}

//============================================================
// <T>调用函数。</T>
//
// @param pInstance 实例
// @param ppResult 结果
// @param pParam1 参数1
// @param pParam2 参数2
// @return 处理结果
//============================================================
TResult FScriptMethod::Invoke2(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1, TAny* pParam2){
   TAny* params[] = {pParam1, pParam2};
   return InvokeParamaters(pInstance, ppResult, 2, params);
}

//============================================================
// <T>调用函数。</T>
//
// @param pInstance 实例
// @param ppResult 结果
// @param pParam1 参数1
// @param pParam2 参数2
// @param pParam3 参数3
// @return 处理结果
//============================================================
TResult FScriptMethod::Invoke3(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1, TAny* pParam2, TAny* pParam3){
   TAny* params[] = {pParam1, pParam2, pParam3};
   return InvokeParamaters(pInstance, ppResult, 3, params);
}

//============================================================
// <T>调用函数。</T>
//
// @param pInstance 实例
// @param ppResult 结果
// @param pParam1 参数1
// @param pParam2 参数2
// @param pParam3 参数3
// @param pParam4 参数4
// @return 处理结果
//============================================================
TResult FScriptMethod::Invoke4(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1, TAny* pParam2, TAny* pParam3, TAny* pParam4){
   TAny* params[] = {pParam1, pParam2, pParam3, pParam4};
   return InvokeParamaters(pInstance, ppResult, 4, params);
}

//============================================================
// <T>调用函数。</T>
//
// @param pInstance 实例
// @param ppResult 结果
// @param pParam1 参数1
// @param pParam2 参数2
// @param pParam3 参数3
// @param pParam4 参数4
// @param pParam5 参数5
// @return 处理结果
//============================================================
TResult FScriptMethod::Invoke5(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1, TAny* pParam2, TAny* pParam3, TAny* pParam4, TAny* pParam5){
   TAny* params[] = {pParam1, pParam2, pParam3, pParam4, pParam5};
   return InvokeParamaters(pInstance, ppResult, 5, params);
}

//============================================================
// <T>调用函数。</T>
//
// @param pInstance 实例
// @param ppResult 结果
// @param pParam1 参数1
// @param pParam2 参数2
// @param pParam3 参数3
// @param pParam4 参数4
// @param pParam5 参数5
// @param pParam6 参数6
// @return 处理结果
//============================================================
TResult FScriptMethod::Invoke6(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1, TAny* pParam2, TAny* pParam3, TAny* pParam4, TAny* pParam5, TAny* pParam6){
   TAny* params[] = {pParam1, pParam2, pParam3, pParam4, pParam5, pParam6};
   return InvokeParamaters(pInstance, ppResult, 6, params);
}

MO_NAMESPACE_END

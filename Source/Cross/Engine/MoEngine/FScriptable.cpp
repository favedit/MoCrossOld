#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScriptable, FComponent);

//============================================================
// <T>构造脚本对象。</T>
//============================================================
FScriptable::FScriptable(){
}

//============================================================
// <T>析构脚本对象。</T>
//============================================================
FScriptable::~FScriptable(){
}

//============================================================
// <T>功能前置处理。</T>
//
// @return 处理结果
//============================================================
TResult FScriptable::ProcessBefore(SProcessContext* pContext){
   return ESuccess;
}

//============================================================
// <T>功能后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FScriptable::ProcessAfter(SProcessContext* pContext){
   return ESuccess;
}

MO_NAMESPACE_END

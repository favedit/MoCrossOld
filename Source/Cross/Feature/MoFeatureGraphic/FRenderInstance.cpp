#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderInstance, FGraphicInstance);

//============================================================
// <T>构造渲染对象。</T>
//============================================================
FRenderInstance::FRenderInstance(){
   _statusSetup = EFalse;
   MO_CLEAR(_pDevice);
}

//============================================================
// <T>析构渲染对象。</T>
//============================================================
FRenderInstance::~FRenderInstance(){
}

//============================================================
// <T>暂停处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderInstance::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderInstance::Resume(){
   return ESuccess;
}

MO_NAMESPACE_END

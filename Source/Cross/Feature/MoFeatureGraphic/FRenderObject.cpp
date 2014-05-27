#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderObject, FGraphicInstance);

//============================================================
// <T>构造渲染对象。</T>
//============================================================
FRenderObject::FRenderObject(){
   _statusSetup = EFalse;
   MO_CLEAR(_pDevice);
}

//============================================================
// <T>析构渲染对象。</T>
//============================================================
FRenderObject::~FRenderObject(){
}

//============================================================
// <T>暂停处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderObject::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderObject::Resume(){
   return ESuccess;
}

MO_NAMESPACE_END

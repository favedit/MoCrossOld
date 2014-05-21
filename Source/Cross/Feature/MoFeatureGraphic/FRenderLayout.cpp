#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderLayout, FInstance);

//============================================================
// <T>构造渲染布局。</T>
//============================================================
FRenderLayout::FRenderLayout(){
   MO_CLEAR(_pProgram);
   MO_CLEAR(_pRenderable);
}

//============================================================
// <T>析构渲染布局。</T>
//============================================================
FRenderLayout::~FRenderLayout(){
}

MO_NAMESPACE_END

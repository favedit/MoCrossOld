#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderableVisual, FInstance);

//============================================================
// <T>构造渲染可视信息。</T>
//============================================================
FRenderableVisual::FRenderableVisual(){
   MO_CLEAR(_pRenderable);
}

//============================================================
// <T>析构渲染可视信息。</T>
//============================================================
FRenderableVisual::~FRenderableVisual(){
}

MO_NAMESPACE_END

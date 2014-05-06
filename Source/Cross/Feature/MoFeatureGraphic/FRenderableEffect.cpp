#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderableEffect, FInstance);

//============================================================
// <T>构造渲染效果信息。</T>
//============================================================
FRenderableEffect::FRenderableEffect(){
   MO_CLEAR(_pEffect);
   MO_CLEAR(_pLayout);
}

//============================================================
// <T>析构渲染效果信息。</T>
//============================================================
FRenderableEffect::~FRenderableEffect(){
}

MO_NAMESPACE_END

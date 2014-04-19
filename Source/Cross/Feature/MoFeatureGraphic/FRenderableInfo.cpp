#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderableInfo, FInstance);

//============================================================
// <T>构造渲染信息。</T>
//============================================================
FRenderableInfo::FRenderableInfo(){
   MO_CLEAR(_pRenderable);
}

//============================================================
// <T>析构渲染信息。</T>
//============================================================
FRenderableInfo::~FRenderableInfo(){
}

MO_NAMESPACE_END

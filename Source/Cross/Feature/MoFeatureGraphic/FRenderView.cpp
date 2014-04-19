#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderView, FInstance);

//============================================================
// <T>构造渲染相机。</T>
//============================================================
FRenderView::FRenderView(){
   _pRenderables = MO_CREATE(FRenderableCollection);
   _pRenderTargets = MO_CREATE(FRenderTargetCollection);
}

//============================================================
// <T>析构渲染相机。</T>
//============================================================
FRenderView::~FRenderView(){
   MO_DELETE(_pRenderables);
   MO_DELETE(_pRenderTargets);
}

MO_NAMESPACE_END

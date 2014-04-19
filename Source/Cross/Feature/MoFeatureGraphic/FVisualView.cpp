#include "MoFgVisual.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造可见视角。</T>
//============================================================
FVisualView::FVisualView(){
   _pVisuals = MO_CREATE(FVisualCollection);
}

//============================================================
// <T>析构可见视角。</T>
//============================================================
FVisualView::~FVisualView(){
   MO_DELETE(_pVisuals);
}

//============================================================
// <T>增加一个可视对象。</T>
//============================================================
TResult FVisualView::Push(FVisualNode* pVisual){
   _pVisuals->Push(pVisual);
   return ESuccess;
}

MO_NAMESPACE_END

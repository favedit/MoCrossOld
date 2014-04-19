#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderRegion, FInstance);

//============================================================
// <T>构造区域。</T>
//============================================================
FRenderRegion::FRenderRegion(){
   _optionRenderTarget = EFalse;
   _pViews = MO_CREATE(FRenderViewCollection);
   _pLights = MO_CREATE(FLightCollection);
   _pRenderables = MO_CREATE(FRenderableCollection);
   _pVisibleRenderables = MO_CREATE(FRenderableCollection);
   _pTextures = MO_CREATE(FRenderTextureCollection);
}

//============================================================
// <T>析构区域。</T>
//============================================================
FRenderRegion::~FRenderRegion(){
   MO_DELETE(_pViews);
   MO_DELETE(_pLights);
   //MO_DELETE(_pVisibleRenderables);
   MO_DELETE(_pRenderables);
   MO_DELETE(_pTextures);
}

//============================================================
// <T>测试是否可以绘制。</T>
//
// @return 是否可以绘制
//============================================================
TBool FRenderRegion::TestRenderable(){
   if(_pRenderables->IsEmpty()){
      return EFalse;
   }
   return ETrue;
}

MO_NAMESPACE_END

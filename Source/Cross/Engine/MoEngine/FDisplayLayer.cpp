#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FDisplayLayer, FDisplay);

//============================================================
// <T>构造可绘制对象层。</T>
//============================================================
FDisplayLayer::FDisplayLayer(){
   _objectCd |= EComponent_DrawableLayer;
   _layerCd = EDisplayLayer_Unknown;
   MO_CLEAR(_pStageFrame);
   MO_CLEAR(_pDisplays);
   MO_CLEAR(_pParticleController);
}

//============================================================
// <T>析构可绘制对象层。</T>
//============================================================
FDisplayLayer::~FDisplayLayer(){
   MO_DELETE(_pParticleController);
}

//============================================================
// <T>焦点测试处理。</T>
//
// @param pTester 测试信息
// @return 处理结果
//============================================================
TResult FDisplayLayer::OnFocusTest(FFocusTester* pTester){
   pTester->SetStatusInRange(EFalse);
   //pTester->SetStatusChildren(_pChildren != NULL);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FDisplayLayer::Setup(){
   MO_ASSERT(!_pParticleController);
   _pParticleController = MO_CREATE(FParticleController);
   _visualRegion = FVisualRegion::InstanceCreate();
   RVisualManager::Instance().RegionRegister(_visualRegion);
   return ESuccess;
}

//============================================================
// <T>过滤渲染区域。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FDisplayLayer::FilterRegion(FRenderRegion* pRegion){
   FRenderableCollection* pRenderables = pRegion->Renderables();
   pRenderables->Clear();
   return FDisplay::FilterRegion(pRegion);
}

//============================================================
// <T>功能前置处理。</T>
//
// @return 处理结果
//============================================================
TResult FDisplayLayer::ProcessBefore(SProcessContext* pContext){
   // 父逻辑处理
   TResult result = FDisplay::ProcessBefore(pContext);
   // 处理粒子控制器
   if(_pParticleController != NULL){
      _pParticleController->Process();
   }
   return result;
}

//============================================================
// <T>功能后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FDisplayLayer::ProcessAfter(SProcessContext* pContext){
   // 父逻辑处理
   TResult result = FDisplay::ProcessAfter(pContext);
   return result;
}

MO_NAMESPACE_END

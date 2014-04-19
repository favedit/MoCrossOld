#include "MoFgRender.h"
#include "MoFgPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPipeline, FInstance);

//============================================================
// <T>构造渲染管道。</T>
//============================================================
FPipeline::FPipeline(){
   _setuped = EFalse;
   _pPasses = MO_CREATE(FPipelinePassCollection);
}

//============================================================
// <T>析构渲染管道。</T>
//============================================================
FPipeline::~FPipeline(){
   MO_DELETE(_pPasses);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPipeline::OnSetup(){
   if(_setuped){
   }
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPipeline::Setup(){
   TResult result = EFailure;
   if(!_setuped){
      result  = OnSetup();
      _setuped = ETrue;
   }
   return result;
}

//============================================================
// <T>变更大小。</T>
//
// @return 处理结果
//============================================================
TResult FPipeline::Resize(TInt width, TInt height){
   return ESuccess;
}

//============================================================
// <T>绘制开始处理。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FPipeline::DrawBegin(FRenderRegion* pRegion){
   MO_CHECK(pRegion, return ENull);
   return ESuccess;
}

//============================================================
// <T>绘制区域处理。</T>
//
// @return 处理结果
//============================================================
TResult FPipeline::DrawRegion(FRenderRegion* pRegion){
   MO_CHECK(pRegion, return ENull);
   // 视角集合处理
   FRenderViewCollection* pViews = pRegion->Views();
   TInt count = pViews->Count();
   for(TInt n = 0; n < count; n++){
      // 设置区域信息
      FRenderView* pView = pViews->Get(n);
      pRegion->SetActiveView(pView);
      // 渲染过程集合处理
      TInt passCount = _pPasses->Count();
      for(TInt i = 0; i < passCount; i++){
         FPipelinePass* pPass = _pPasses->Get(i);
         pRegion->SetOptionRenderTarget(i == passCount - 1);
         pPass->DrawRegion(pRegion);
      }
   }
   return ESuccess;
}

//============================================================
// <T>绘制结束处理。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FPipeline::DrawEnd(FRenderRegion* pRegion){
   MO_CHECK(pRegion, return ENull);
   return ESuccess;
}

MO_NAMESPACE_END

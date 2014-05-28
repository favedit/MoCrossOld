#include "MoErShadowPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FShadowDepthPass, FPipelinePass);

//============================================================
// <T>构造影子渲染过程。</T>
//============================================================
FShadowDepthPass::FShadowDepthPass(){
   _name = "depth";
   _depthSize.Set(2048, 2048);
   MO_CLEAR(_pRenderTarget);
   MO_CLEAR(_pDepthTexture);
}

//============================================================
// <T>析构影子渲染过程。</T>
//============================================================
FShadowDepthPass::~FShadowDepthPass(){
   MO_DELETE(_pRenderTarget);
   MO_DELETE(_pDepthTexture);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FShadowDepthPass::Setup(){
   TResult result = FPipelinePass::Setup();
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   // 创建颜色纹理
   _pDepthTexture = pRenderDevice->CreateFlatTexture();
   _pDepthTexture->SetOwner(this);
   _pDepthTexture->Size().Assign(_depthSize);
   _pDepthTexture->Setup();
   // 创建渲染目标
   _pRenderTarget = pRenderDevice->CreateTarget();
   _pRenderTarget->SetOptionDepth(ETrue);
   _pRenderTarget->Size().Assign(_depthSize);
   _pRenderTarget->Textures()->Push(_pDepthTexture);
   _pRenderTarget->Setup();
   return result;
}

//============================================================
// <T>绘制开始处理。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FShadowDepthPass::DrawBegin(FRenderRegion* pRegion){
   TResult resultCd = FPipelinePass::DrawBegin(pRegion);
   //............................................................
   // 设置绘制目标
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   // 设置绘制目标
   if(pRegion->OptionRenderTarget()){
      pRenderDevice->SetRenderTarget(NULL);
   }else{
      pRenderDevice->SetRenderTarget(_pRenderTarget);
   }
   return resultCd;
}

//============================================================
// <T>绘制区域处理。</T>
//
// @return 处理结果
//============================================================
TResult FShadowDepthPass::DrawRegion(FRenderRegion* pRegion){
   TResult resultCd = FPipelinePass::DrawRegion(pRegion);
   return resultCd;
}

//============================================================
// <T>绘制结束处理。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FShadowDepthPass::DrawEnd(FRenderRegion* pRegion){
   TResult resultCd = FPipelinePass::DrawEnd(pRegion);
   return resultCd;
}

MO_NAMESPACE_END

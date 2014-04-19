#include "MoErShadowPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FShadowColorPass, FPipelinePass);

//============================================================
// <T>构造影子渲染过程。</T>
//============================================================
FShadowColorPass::FShadowColorPass(){
   _name = "color";
}

//============================================================
// <T>析构影子渲染过程。</T>
//============================================================
FShadowColorPass::~FShadowColorPass(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FShadowColorPass::Setup(){
   TResult result = FPipelinePass::Setup();
   return result;
}

//============================================================
// <T>绘制开始处理。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FShadowColorPass::DrawBegin(FRenderRegion* pRegion){
   FPipelinePass::DrawBegin(pRegion);
   //............................................................
   // 设置绘制目标
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   pRenderDevice->SetRenderTarget();
   return ESuccess;
}

//============================================================
// <T>绘制区域处理。</T>
//
// @return 处理结果
//============================================================
TResult FShadowColorPass::DrawRegion(FRenderRegion* pRegion){
   // 设置纹理
   pRegion->Textures()->PushUnique(_lightDepthTexture);
   // 绘制区域
   FPipelinePass::DrawRegion(pRegion);
   return ESuccess;
}

//============================================================
// <T>绘制结束处理。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FShadowColorPass::DrawEnd(FRenderRegion* pRegion){
   TResult resultCd = FPipelinePass::DrawEnd(pRegion);
   return resultCd;
}

MO_NAMESPACE_END

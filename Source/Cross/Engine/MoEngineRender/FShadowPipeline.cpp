#include "MoErPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FShadowPipeline, FPipeline);

//============================================================
// <T>构造阴影渲染管道。</T>
//============================================================
FShadowPipeline::FShadowPipeline(){
   _name = "shadow";
}

//============================================================
// <T>析构阴影渲染管道。</T>
//============================================================
FShadowPipeline::~FShadowPipeline(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FShadowPipeline::OnSetup(){
   TResult result = FPipeline::OnSetup();
   // 创建阴影渲染过程
   _pShadowDepthPass = MO_CREATE(FShadowDepthPass);
   _pShadowDepthPass->SetPipeline(this);
   _pShadowDepthPass->Setup();
   _pPasses->Push(_pShadowDepthPass);
   // 创建颜色渲染过程
   _pShadowColorPass = MO_CREATE(FShadowColorPass);
   _pShadowColorPass->Setup();
   _pShadowColorPass->SetPipeline(this);
   _pPasses->Push(_pShadowColorPass);
   // 设置关联
   FRenderTexture* pDepthTexture = _pShadowDepthPass->DepthTexture();
   _pShadowColorPass->SetLightDepthTexture(pDepthTexture);
   return result;
}

MO_NAMESPACE_END

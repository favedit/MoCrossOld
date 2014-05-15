#include "MoEngineRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEngineRenderFeature, FFeature);

//============================================================
// <T>构造平台DirectX11功能。</T>
//============================================================
FEngineRenderFeature::FEngineRenderFeature(){
   _name = "DirectX 11";
}

//============================================================
// <T>析构平台DirectX11功能。</T>
//============================================================
FEngineRenderFeature::~FEngineRenderFeature(){
}

//============================================================
// <T>构造类对象。</T>
//
// @return 处理结果
//============================================================
TResult FEngineRenderFeature::Construct(){
   TResult resultCd = FFeature::Construct();
   // 注册枚举信息
   //REnumeratorManager::Instance().Find<>();
   //MO_RENDERENUM_PARAMETER         "EnumRenderParameter"
   //MO_RENDERENUM_ATTRIBUTE         "EnumRenderAttribute"
   //MO_RENDERENUM_SAMPLER           "EnumRenderSampler"
   // 注册渲染管道
   RPipelineManager::Instance().Factory()->Register("simple", FSimplePipeline::Class());
   RPipelineManager::Instance().Factory()->Register("shadow", FShadowPipeline::Class());
   RPipelineManager::Instance().Factory()->Register("deferred", FDeferredPipeline::Class());
   // 注册渲染效果
   REffectManager::Instance().Factory()->Register("simple.color.automatic", FSampleColorAutomaticEffect::Class());
   REffectManager::Instance().Factory()->Register("simple.color.skeleton", FSampleColorSkeletonEffect::Class());
   REffectManager::Instance().Factory()->Register("shadow.depth.automatic", FShadowDepthAutomaticEffect::Class());
   REffectManager::Instance().Factory()->Register("shadow.depth.skeleton", FShadowDepthSkeletonEffect::Class());
   REffectManager::Instance().Factory()->Register("shadow.color.automatic", FShadowColorAutomaticEffect::Class());
   REffectManager::Instance().Factory()->Register("shadow.color.skeleton", FShadowColorSkeletonEffect::Class());
   REffectManager::Instance().Factory()->Register("color", FBlurEffect::Class());
   REffectManager::Instance().Factory()->Register("blur", FBlurEffect::Class());
   return resultCd;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FEngineRenderFeature::Startup(){
   TResult resultCd = FFeature::Startup();
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FEngineRenderFeature::Suspend(){
   TResult resultCd = FFeature::Suspend();
   return resultCd;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FEngineRenderFeature::Resume(){
   TResult resultCd = FFeature::Resume();
   return resultCd;
}

//============================================================
// <T>停止处理。</T>
//
// @return 处理结果
//============================================================
TResult FEngineRenderFeature::Shutdown(){
   TResult resultCd = FFeature::Shutdown();
   return resultCd;
}

//============================================================
// <T>释放类对象。</T>
//
// @return 处理结果
//============================================================
TResult FEngineRenderFeature::Dispose(){
   TResult resultCd = FFeature::Dispose();
   return resultCd;
}

MO_NAMESPACE_END

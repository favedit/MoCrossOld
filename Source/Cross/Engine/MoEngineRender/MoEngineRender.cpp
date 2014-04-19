#include "MoEngineRender.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoEngineRenderInitialize(){
   MO_STATIC_INFO("Engine Render initialize.");
}

//============================================================
// <T>启动渲染引擎处理。</T>
//============================================================
void MoEngineRenderStartup(){
   MO_STATIC_INFO("Engine Render startup.");
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
}

//============================================================
// <T>关闭渲染引擎处理。</T>
//============================================================
void MoEngineRenderShutdown(){
   MO_STATIC_INFO("Engine Render shutdown.");
}

//============================================================
// <T>释放渲染引擎处理。</T>
//============================================================
void MoEngineRenderRelease(){
   MO_STATIC_INFO("Engine Render release.");
}

MO_NAMESPACE_END

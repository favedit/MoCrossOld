#include "MoFeatureGraphic.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FFeatureGraphicFeature, FFeature);

//============================================================
// <T>构造图形功能。</T>
//============================================================
FFeatureGraphicFeature::FFeatureGraphicFeature(){
   _name = "Feature Graphic";
}

//============================================================
// <T>析构图形功能。</T>
//============================================================
FFeatureGraphicFeature::~FFeatureGraphicFeature(){
}

//============================================================
// <T>构造类对象。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureGraphicFeature::Construct(){
   TResult resultCd = FFeature::Construct();
   // 初始化可见管理器
   RVisualManager::Create();
   // 初始化技术管理器
   RTechniqueManager::Create();
   // 枚举创建
   RRenderAttribute::Create();
   RRenderSampler::Create();
   // 注册枚举信息
   return resultCd;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureGraphicFeature::Startup(){
   TResult resultCd = FFeature::Startup();
   // 启动可见管理器
   RVisualManager::Instance().Startup();
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureGraphicFeature::Suspend(){
   TResult resultCd = FFeature::Suspend();
   return resultCd;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureGraphicFeature::Resume(){
   TResult resultCd = FFeature::Resume();
   return resultCd;
}

//============================================================
// <T>停止处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureGraphicFeature::Shutdown(){
   TResult resultCd = FFeature::Shutdown();
   // 停止可见管理器
   RVisualManager::Instance().Shutdown();
   return resultCd;
}

//============================================================
// <T>释放类对象。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureGraphicFeature::Dispose(){
   TResult resultCd = FFeature::Dispose();
   // 释放技术管理器
   RTechniqueManager::Destroy();
   // 释放可见管理器
   RVisualManager::Destroy();
   return resultCd;
}

MO_NAMESPACE_END

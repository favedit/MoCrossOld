#include "MoFeatureGraphic.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FFeatureGraphicFeature, FFeature);

//============================================================
// <T>构造图形功能。</T>
//============================================================
FFeatureGraphicFeature::FFeatureGraphicFeature(){
   _name = "DirectX 11";
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
   return resultCd;
}

//============================================================
// <T>释放类对象。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureGraphicFeature::Dispose(){
   TResult resultCd = FFeature::Dispose();
   return resultCd;
}

MO_NAMESPACE_END

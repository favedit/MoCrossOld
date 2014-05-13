#include "MoPluginScriptMono.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPluginScriptMonoFeature, FFeature);

//============================================================
// <T>构造平台DirectX11功能。</T>
//============================================================
FPluginScriptMonoFeature::FPluginScriptMonoFeature(){
   _name = "DirectX 11";
}

//============================================================
// <T>析构平台DirectX11功能。</T>
//============================================================
FPluginScriptMonoFeature::~FPluginScriptMonoFeature(){
}

//============================================================
// <T>构造类对象。</T>
//
// @return 处理结果
//============================================================
TResult FPluginScriptMonoFeature::Construct(){
   TResult resultCd = FFeature::Construct();
   return resultCd;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FPluginScriptMonoFeature::Startup(){
   TResult resultCd = FFeature::Startup();
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPluginScriptMonoFeature::Suspend(){
   TResult resultCd = FFeature::Suspend();
   return resultCd;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPluginScriptMonoFeature::Resume(){
   TResult resultCd = FFeature::Resume();
   return resultCd;
}

//============================================================
// <T>停止处理。</T>
//
// @return 处理结果
//============================================================
TResult FPluginScriptMonoFeature::Shutdown(){
   TResult resultCd = FFeature::Shutdown();
   return resultCd;
}

//============================================================
// <T>释放类对象。</T>
//
// @return 处理结果
//============================================================
TResult FPluginScriptMonoFeature::Dispose(){
   TResult resultCd = FFeature::Dispose();
   return resultCd;
}

MO_NAMESPACE_END

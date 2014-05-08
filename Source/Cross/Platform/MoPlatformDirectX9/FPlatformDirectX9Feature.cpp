#include "MoPlatformDirectX9.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPlatformDirectX9Feature, FFeature);

//============================================================
// <T>构造平台DirectX9功能。</T>
//============================================================
FPlatformDirectX9Feature::FPlatformDirectX9Feature(){
   _name = "DirectX 9";
}

//============================================================
// <T>析构平台DirectX9功能。</T>
//============================================================
FPlatformDirectX9Feature::~FPlatformDirectX9Feature(){
}

//============================================================
// <T>构造类对象。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX9Feature::Construct(){
   TResult resultCd = FFeature::Construct();
   // 注册渲染设备
   RDeviceManager::Instance().Register(FPd9RenderDevice::Class(), FRenderDevice::Class());
   return resultCd;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX9Feature::Startup(){
   TResult resultCd = FFeature::Startup();
   RTechniqueManager::Instance().Setup();
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX9Feature::Suspend(){
   TResult resultCd = FFeature::Suspend();
   return resultCd;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX9Feature::Resume(){
   TResult resultCd = FFeature::Resume();
   return resultCd;
}

//============================================================
// <T>停止处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX9Feature::Shutdown(){
   TResult resultCd = FFeature::Shutdown();
   return resultCd;
}

//============================================================
// <T>释放类对象。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX9Feature::Dispose(){
   TResult resultCd = FFeature::Dispose();
   // 注销渲染设备
   RDeviceManager::Instance().Unregister(FRenderDevice::Class());
   return resultCd;
}

MO_NAMESPACE_END

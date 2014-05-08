#include "MoPlatformDirectX10.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPlatformDirectX10Feature, FFeature);

//============================================================
// <T>构造平台DirectX10功能。</T>
//============================================================
FPlatformDirectX10Feature::FPlatformDirectX10Feature(){
   _name = "DirectX 10";
}

//============================================================
// <T>析构平台DirectX10功能。</T>
//============================================================
FPlatformDirectX10Feature::~FPlatformDirectX10Feature(){
}

//============================================================
// <T>构造类对象。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX10Feature::Construct(){
   TResult resultCd = FFeature::Construct();
   // 注册渲染设备
   RDeviceManager::Instance().Register(FPd10RenderDevice::Class(), FRenderDevice::Class());
   return resultCd;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX10Feature::Startup(){
   TResult resultCd = FFeature::Startup();
   RTechniqueManager::Instance().Setup();
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX10Feature::Suspend(){
   TResult resultCd = FFeature::Suspend();
   return resultCd;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX10Feature::Resume(){
   TResult resultCd = FFeature::Resume();
   return resultCd;
}

//============================================================
// <T>停止处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX10Feature::Shutdown(){
   TResult resultCd = FFeature::Shutdown();
   return resultCd;
}

//============================================================
// <T>释放类对象。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX10Feature::Dispose(){
   TResult resultCd = FFeature::Dispose();
   // 注销渲染设备
   RDeviceManager::Instance().Unregister(FRenderDevice::Class());
   return resultCd;
}

MO_NAMESPACE_END

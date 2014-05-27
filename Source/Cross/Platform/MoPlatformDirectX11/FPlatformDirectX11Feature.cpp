#include "MoPlatformDirectX11.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPlatformDirectX11Feature, FFeature);

//============================================================
// <T>构造平台DirectX11功能。</T>
//============================================================
FPlatformDirectX11Feature::FPlatformDirectX11Feature(){
   _name = "DirectX 11";
}

//============================================================
// <T>析构平台DirectX11功能。</T>
//============================================================
FPlatformDirectX11Feature::~FPlatformDirectX11Feature(){
}

//============================================================
// <T>构造类对象。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX11Feature::Construct(){
   TResult resultCd = FFeature::Construct();
   // 注册渲染设备
   _renderDevice = FPd11RenderDevice::InstanceCreate();
   RDeviceManager::Instance().Register(_renderDevice);
   return resultCd;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX11Feature::Startup(){
   TResult resultCd = FFeature::Startup();
   RTechniqueManager::Instance().Setup();
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX11Feature::Suspend(){
   TResult resultCd = FFeature::Suspend();
   return resultCd;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX11Feature::Resume(){
   TResult resultCd = FFeature::Resume();
   return resultCd;
}

//============================================================
// <T>停止处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX11Feature::Shutdown(){
   TResult resultCd = FFeature::Shutdown();
   return resultCd;
}

//============================================================
// <T>释放类对象。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformDirectX11Feature::Dispose(){
   TResult resultCd = FFeature::Dispose();
   // 注销设备
   //RDeviceManager::Instance().Unregister(FRenderDevice::Class());
   return resultCd;
}

MO_NAMESPACE_END

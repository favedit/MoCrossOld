#include "MoPlatformOpenGLES2.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPlatformOpenGLES2Feature, FConsole);

//============================================================
// <T>构造平台OpenGL2功能。</T>
//============================================================
FPlatformOpenGLES2Feature::FPlatformOpenGLES2Feature(){
   _name = "OpenGLES2.0";
}

//============================================================
// <T>析构平台OpenGL2功能。</T>
//============================================================
FPlatformOpenGLES2Feature::~FPlatformOpenGLES2Feature(){
}

//============================================================
// <T>构造类对象。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformOpenGLES2Feature::Construct(){
   TResult resultCd = FFeature::Construct();
   // 注册渲染设备
   RDeviceManager::Instance().Register(FPoe2RenderDevice::Class(), FRenderDevice::Class());
   return resultCd;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformOpenGLES2Feature::Startup(){
   TResult resultCd = FFeature::Startup();
   RTechniqueManager::Instance().Setup();
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformOpenGLES2Feature::Suspend(){
   TResult resultCd = FFeature::Suspend();
   return resultCd;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformOpenGLES2Feature::Resume(){
   TResult resultCd = FFeature::Resume();
   return resultCd;
}

//============================================================
// <T>停止处理。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformOpenGLES2Feature::Shutdown(){
   TResult resultCd = FFeature::Shutdown();
   return resultCd;
}

//============================================================
// <T>释放类对象。</T>
//
// @return 处理结果
//============================================================
TResult FPlatformOpenGLES2Feature::Dispose(){
   TResult resultCd = FFeature::Dispose();
   // 注销渲染设备
   RDeviceManager::Instance().Unregister(FRenderDevice::Class());
   return resultCd;
}

MO_NAMESPACE_END

#include "MoPlatformDirectX9.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoEngineDirectX9Initialize(){
   MO_STATIC_INFO("Engine OpenGLES2 initialize.");
   // 注册渲染设备
   RDeviceManager::Instance().Register(FPd9RenderDevice::Class(), FRenderDevice::Class());
}

//============================================================
// <T>启动渲染引擎处理。</T>
//============================================================
void MoEngineDirectX9Startup(){
   MO_STATIC_INFO("Engine OpenGLES2 startup.");
}

//============================================================
// <T>关闭渲染引擎处理。</T>
//============================================================
void MoEngineDirectX9Shutdown(){
   MO_STATIC_INFO("Engine OpenGLES2 shutdown.");
}

//============================================================
// <T>释放渲染引擎处理。</T>
//============================================================
void MoEngineDirectX9Release(){
   MO_STATIC_INFO("Engine OpenGLES2 release.");
}

MO_NAMESPACE_END

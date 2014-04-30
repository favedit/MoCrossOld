#include "MoPlatformOpenGLES2.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoEngineOpenGLES2Initialize(){
   MO_STATIC_INFO("Engine OpenGLES2 initialize.");
   // 初始化技术管理器
   RTechniqueManager::Create();
   // 注册渲染设备
   RDeviceManager::Instance().Register(FEoRenderDevice::Class(), FRenderDevice::Class());
}

//============================================================
// <T>启动渲染引擎处理。</T>
//============================================================
void MoEngineOpenGLES2Startup(){
   MO_STATIC_INFO("Engine OpenGLES2 startup.");
   RTechniqueManager::Instance().Setup();
}

//============================================================
// <T>关闭渲染引擎处理。</T>
//============================================================
void MoEngineOpenGLES2Shutdown(){
   MO_STATIC_INFO("Engine OpenGLES2 shutdown.");
}

//============================================================
// <T>释放渲染引擎处理。</T>
//============================================================
void MoEngineOpenGLES2Release(){
   MO_STATIC_INFO("Engine OpenGLES2 release.");
}

MO_NAMESPACE_END

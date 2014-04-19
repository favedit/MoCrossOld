#include "MoEngineOpenGL.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoEngineOpenGLInitialize(){
   MO_STATIC_INFO("Engine OpenGL initialize.");
   // 初始化字体管理器
   RFontManager::Create(MO_CREATE(FEoFontConsole));
   RFontManager::Instance().Open();
   // 初始化技术管理器
   RTechniqueManager::Create();
   // 注册渲染设备
   RDeviceManager::Instance().Register(FEoRenderDevice::Class(), FRenderDevice::Class());
}

//============================================================
// <T>启动渲染引擎处理。</T>
//============================================================
void MoEngineOpenGLStartup(){
   MO_STATIC_INFO("Engine OpenGL startup.");
   RTechniqueManager::Instance().Setup();
}

//============================================================
// <T>关闭渲染引擎处理。</T>
//============================================================
void MoEngineOpenGLShutdown(){
   MO_STATIC_INFO("Engine OpenGL shutdown.");
}

//============================================================
// <T>释放渲染引擎处理。</T>
//============================================================
void MoEngineOpenGLRelease(){
   MO_STATIC_INFO("Engine OpenGL release.");
}

MO_NAMESPACE_END

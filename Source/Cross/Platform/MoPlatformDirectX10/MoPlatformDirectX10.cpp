#include "MoPlatformDirectX10.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoEngineDirectX10Initialize(){
   MO_STATIC_INFO("Engine DirectX 10 initialize.");
   // 初始化技术管理器
   RTechniqueManager::Create();
   // 注册渲染设备
   RDeviceManager::Instance().Register(FPd10RenderDevice::Class(), FRenderDevice::Class());
}

//============================================================
// <T>启动渲染引擎处理。</T>
//============================================================
void MoEngineDirectX10Startup(){
   MO_STATIC_INFO("Engine DirectX 10 startup.");
   RTechniqueManager::Instance().Setup();
}

//============================================================
// <T>关闭渲染引擎处理。</T>
//============================================================
void MoEngineDirectX10Shutdown(){
   MO_STATIC_INFO("Engine DirectX 10 shutdown.");
}

//============================================================
// <T>释放渲染引擎处理。</T>
//============================================================
void MoEngineDirectX10Release(){
   MO_STATIC_INFO("Engine DirectX 10 release.");
}

MO_NAMESPACE_END

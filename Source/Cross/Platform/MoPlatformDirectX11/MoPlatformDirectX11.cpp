#include "MoPlatformDirectX11.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoEngineDirectX11Initialize(){
   MO_STATIC_INFO("Engine DirectX 11 initialize.");
   // 初始化技术管理器
   RTechniqueManager::Create();
   // 注册渲染设备
   RDeviceManager::Instance().Register(FPd11RenderDevice::Class(), FRenderDevice::Class());
}

//============================================================
// <T>启动渲染引擎处理。</T>
//============================================================
void MoEngineDirectX11Startup(){
   MO_STATIC_INFO("Engine DirectX 11 startup.");
   RTechniqueManager::Instance().Setup();
}

//============================================================
// <T>关闭渲染引擎处理。</T>
//============================================================
void MoEngineDirectX11Shutdown(){
   MO_STATIC_INFO("Engine DirectX 11 shutdown.");
}

//============================================================
// <T>释放渲染引擎处理。</T>
//============================================================
void MoEngineDirectX11Release(){
   MO_STATIC_INFO("Engine DirectX 11 release.");
}

MO_NAMESPACE_END

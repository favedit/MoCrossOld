#include "MoEngine.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>构造游戏库。</T>
//
// @return 运行库
//============================================================
void MoEngineInitialize(){
   MO_STATIC_INFO("Engine initialize.");
   // 初始化环境管理器
   REnvironmentManager::Create();
   // 初始化工作管理器
   RWorkerManager::Create();
   // 初始化解码管理器
   RDecoderManager::Create();
   // 初始化处理管理器
   RProcessorManager::Create();
   //............................................................
   // 初始化设备管理器
   RDeviceManager::Create();
   RDeviceManager::Instance().Setup();
   // 初始化焦点管理器
   RFocusManager::Create();
   //............................................................
   // 初始化资源管理器
   RResourceManager::Create();
   // 初始化材质管理器
   RMaterialManager::Create();
   // 初始化粒子管理器
   RParticleManager::Create();
   RParticleManager::Instance().Setup();
   //............................................................
   // 初始化效果管理器
   REffectManager::Create();
   RPipelineManager::Create();
   //............................................................
   // 初始化舞台管理器
   RStageManager::Create();
   // 初始化引擎管理器
   REngineManager::Create();
   //............................................................
   // 注册渲染设备
   RDeviceManager::Instance().Register(FTimerDevice::Class());
   RDeviceManager::Instance().Register(FKeyboardDevice::Class());
   RDeviceManager::Instance().Register(FMouseDevice::Class());
   RDeviceManager::Instance().Register(FScreenDevice::Class());
}

//============================================================
// <T>启动游戏库。</T>
//============================================================
void MoEngineStartup(){
   MO_STATIC_INFO("Engine startup.");
   // 注册渲染效果
   REffectManager::Instance().Factory()->Register("stage", FStageEffect::Class());
   // 配置引擎管理器
   REngineManager::Instance().Setup();
   // 配置焦点管理器
   RFocusManager::Instance().Setup();
   // 配置舞台管理器
   RStageManager::Instance().Startup();
   //// 配置引擎处理
   //REngineManager::Create();
   //// 启动工作管理器
   //RWorkerManager::Instance().Startup();
   //// 启动资源管理器
   //RResourceManager::Instance().Startup();
}

//============================================================
// <T>停止游戏库。</T>
//============================================================
void MoEngineShutdown(){
   MO_STATIC_INFO("Engine shutdown.");
   // 停止舞台管理器
   RStageManager::Instance().Shutdown();
   //// 停止工作管理器
   //RResourceManager::Instance().Shutdown();
   //// 停止资源管理器
   //RWorkerManager::Instance().Shutdown();
}

//============================================================
// <T>释放游戏库。</T>
//============================================================
void MoEngineRelease(){
   MO_STATIC_INFO("Engine release.");
   // 释放引擎管理器
   REngineManager::Destroy();
   //............................................................
   // 释放粒子管理器
   RParticleManager::Destroy();
   // 释放资源管理器
   RResourceManager::Destroy();
   //............................................................
   // 释放舞台管理器
   RStageManager::Destroy();
   // 释放焦点管理器
   RFocusManager::Destroy();
   // 释放设备管理器
   RDeviceManager::Destroy();
   //............................................................
   // 释放处理管理器
   RProcessorManager::Destroy();
   // 释放解码管理器
   RDecoderManager::Destroy();
   // 释放工作管理器
   RWorkerManager::Destroy();
   // 释放环境管理器
   REnvironmentManager::Destroy();
}

MO_NAMESPACE_END

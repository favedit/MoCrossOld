#include "MoEfDevice.h"
#include "MoEngineFlash.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>构造游戏库。</T>
//
// @return 运行库
//============================================================
void MoFlashInitialize(){
   // 初始化设备管理器
   RDeviceManager::Create(MO_CREATE(FEfDeviceConsole));
   // 启动处理
   RDeviceManager::Instance().Setup();
}

//============================================================
// <T>释放游戏库。</T>
//============================================================
void MoFlashRelease(){
   // 释放设备管理器
   RDeviceManager::Destroy();
}

MO_NAMESPACE_END

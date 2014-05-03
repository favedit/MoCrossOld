#include "MoCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// 网络消息工厂句柄
INetMessageFactory* RNetMessageFactory::_pFactory = NULL;

//============================================================
// <T>初始化核心库。</T>
//
// @return 运行库
//============================================================
void MoCoreInitialize(){
   // 初始化配置管理器
   RConfigurationManager::Create();
   RXmlContainerManager::Create();
   // 初始计时管理器
   RTimerManager::Create();
   // 初始化模块管理器
   RModuleManager::Create();
   RApplication::Instance().InterruptListeners()->Push(RModuleManager::Instance().InterruptListener());
   RApplication::Instance().ReloadListeners()->Push(RModuleManager::Instance().ReloadListener());
   RApplication::Instance().UnloadListeners()->Push(RModuleManager::Instance().UnloadListener());
   // 初始化共享模块管理器
   RSharedModuleManager::Create();
   // 初始化服务管理器
   RServiceManager::Create();
   // 初始化监视器服务
   RMonitorService::Create();
   // 初始化统计服务
   RStatisticsManager::Create();
   // 初始化模板管理器
   RTemplateManager::Create();
   // 初始化功能管理器
   RFeatureManager::Create();
}

//============================================================
// <T>释放核心库。</T>
//============================================================
void MoCoreRelease(){
   // 释放功能管理器
   RFeatureManager::Destroy();
   // 释放统计服务
   RStatisticsManager::Destroy();
   // 释放监视器服务
   RMonitorService::Destroy();
   // 释放服务管理器
   RServiceManager::Destroy();
   // 释放计时管理器
   RTimerManager::Destroy();
   // 释放共享模块管理器
   RSharedModuleManager::Destroy();
   // 释放模块管理器
   RApplication::Instance().InterruptListeners()->Remove(RModuleManager::Instance().InterruptListener());
   RApplication::Instance().ReloadListeners()->Remove(RModuleManager::Instance().ReloadListener());
   RApplication::Instance().UnloadListeners()->Remove(RModuleManager::Instance().UnloadListener());
   RModuleManager::Destroy();
   // 释放配置管理器
   RConfigurationManager::Destroy();
   RXmlContainerManager::Destroy();
}

MO_NAMESPACE_END

#include "MoFeatureGraphic.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoFeatureGraphicsInitialize(){
   MO_STATIC_INFO("Feature Graphic initialize.");
   // 初始化可见管理器
   RVisualManager::Create();
   // 初始化技术管理器
   RTechniqueManager::Create();
}

//============================================================
// <T>启动渲染引擎处理。</T>
//============================================================
void MoFeatureGraphicsStartup(){
   MO_STATIC_INFO("Feature Graphic startup.");
   // 启动可见管理器
   RVisualManager::Instance().Startup();
}

//============================================================
// <T>关闭渲染引擎处理。</T>
//============================================================
void MoFeatureGraphicsShutdown(){
   MO_STATIC_INFO("Feature Graphic shutdown.");
   // 停止可见管理器
   RVisualManager::Instance().Shutdown();
}

//============================================================
// <T>释放渲染引擎处理。</T>
//============================================================
void MoFeatureGraphicsRelease(){
   MO_STATIC_INFO("Feature Graphic release.");
   // 释放技术管理器
   RTechniqueManager::Destroy();
   // 释放可见管理器
   RVisualManager::Destroy();
}

MO_NAMESPACE_END

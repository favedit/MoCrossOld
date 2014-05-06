#include "MoPlatformWindows.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化窗口引擎处理。</T>
//============================================================
void MoPlatformWindowsInitialize(){
   MO_STATIC_INFO("Platform windows initialize.");
   // 初始化窗口管理器
   RRenderWindowManager::Create();
   // 初始化财产管理器
   RAssetManager::Create(MO_CREATE(FEwAssetConsole));
}

//============================================================
// <T>启动窗口引擎处理。</T>
//============================================================
void MoPlatformWindowsStartup(){
   MO_STATIC_INFO("Platform windows startup.");
}

//============================================================
// <T>关闭窗口引擎处理。</T>
//============================================================
void MoPlatformWindowsShutdown(){
   MO_STATIC_INFO("Platform windows shutdown.");
}

//============================================================
// <T>释放窗口引擎处理。</T>
//============================================================
void MoPlatformWindowsRelease(){
   MO_STATIC_INFO("Platform windows release.");
   // 释放财产管理器
   RAssetManager::Destroy();
   // 释放窗口管理器
   RRenderWindowManager::Destroy();
}

MO_NAMESPACE_END

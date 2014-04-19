#include "MoPlatformAndroid.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoPlatformAndroidInitialize(){
   MO_STATIC_INFO("Platform android initialize.");
   // 初始化字体管理器
   RAssetManager::Create(MO_CREATE(FEaAssetConsole));
}

//============================================================
// <T>启动渲染引擎处理。</T>
//============================================================
void MoPlatformAndroidStartup(){
   MO_STATIC_INFO("Platform android startup.");
}

//============================================================
// <T>关闭渲染引擎处理。</T>
//============================================================
void MoPlatformAndroidShutdown(){
   MO_STATIC_INFO("Platform android shutdown.");
}

//============================================================
// <T>释放渲染引擎处理。</T>
//============================================================
void MoPlatformAndroidRelease(){
   MO_STATIC_INFO("Platform android release.");
}

MO_NAMESPACE_END

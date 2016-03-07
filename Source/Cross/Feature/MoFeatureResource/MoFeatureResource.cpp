#include "MoFeatureResource.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化资源功能处理。</T>
//============================================================
void MoFeatureResourceInitialize(){
   MO_STATIC_INFO(TC("Feature resource initialize."));
   RLoaderManager::Create();
   RContentManager::Create();
}

//============================================================
// <T>启动资源功能处理。</T>
//============================================================
void MoFeatureResourceStartup(){
   MO_STATIC_INFO(TC("Feature resource startup."));
   RLoaderManager::Instance().Startup();
}

//============================================================
// <T>关闭资源功能处理。</T>
//============================================================
void MoFeatureResourceShutdown(){
   MO_STATIC_INFO(TC("Feature resource shutdown."));
   RLoaderManager::Instance().Shutdown();
}

//============================================================
// <T>释放资源功能处理。</T>
//============================================================
void MoFeatureResourceRelease(){
   MO_STATIC_INFO(TC("Feature resource release."));
   RLoaderManager::Destroy();
   RContentManager::Destroy();
}

MO_NAMESPACE_END

#include "MoFeatureResource.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化资源功能处理。</T>
//============================================================
void MoFeatureResourceInitialize(){
   MO_STATIC_INFO("Feature resource initialize.");
}

//============================================================
// <T>启动资源功能处理。</T>
//============================================================
void MoFeatureResourceStartup(){
   MO_STATIC_INFO("Feature resource startup.");
}

//============================================================
// <T>关闭资源功能处理。</T>
//============================================================
void MoFeatureResourceShutdown(){
   MO_STATIC_INFO("Feature resource shutdown.");
}

//============================================================
// <T>释放资源功能处理。</T>
//============================================================
void MoFeatureResourceRelease(){
   MO_STATIC_INFO("Feature resource release.");
}

MO_NAMESPACE_END

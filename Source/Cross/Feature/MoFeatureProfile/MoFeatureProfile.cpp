#include "MoFeatureProfile.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化声音功能处理。</T>
//============================================================
void MoFeatureProfileInitialize(){
   MO_STATIC_INFO("Feature profile initialize.");
}

//============================================================
// <T>启动声音功能处理。</T>
//============================================================
void MoFeatureProfileStartup(){
   MO_STATIC_INFO("Feature profile startup.");
}

//============================================================
// <T>关闭声音功能处理。</T>
//============================================================
void MoFeatureProfileShutdown(){
   MO_STATIC_INFO("Feature profile shutdown.");
}

//============================================================
// <T>释放声音功能处理。</T>
//============================================================
void MoFeatureProfileRelease(){
   MO_STATIC_INFO("Feature profile release.");
}

MO_NAMESPACE_END

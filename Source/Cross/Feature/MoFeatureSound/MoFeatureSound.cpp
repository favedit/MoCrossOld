#include "MoFeatureSound.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化声音功能处理。</T>
//============================================================
void MoFeatureSoundInitialize(){
   MO_STATIC_INFO("Feature sound initialize.");
}

//============================================================
// <T>启动声音功能处理。</T>
//============================================================
void MoFeatureSoundStartup(){
   MO_STATIC_INFO("Feature sound startup.");
}

//============================================================
// <T>关闭声音功能处理。</T>
//============================================================
void MoFeatureSoundShutdown(){
   MO_STATIC_INFO("Feature sound shutdown.");
}

//============================================================
// <T>释放声音功能处理。</T>
//============================================================
void MoFeatureSoundRelease(){
   MO_STATIC_INFO("Feature sound release.");
}

MO_NAMESPACE_END

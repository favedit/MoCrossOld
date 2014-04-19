#include "MoFeatureLogger.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化声音功能处理。</T>
//============================================================
void MoFeatureLoggerInitialize(){
   MO_STATIC_INFO("Feature logger initialize.");
   RLoggerFeature::Create();
   RLoggerFeature::Instance().Setup();
}

//============================================================
// <T>启动声音功能处理。</T>
//============================================================
void MoFeatureLoggerStartup(){
   MO_STATIC_INFO("Feature logger startup.");
}

//============================================================
// <T>关闭声音功能处理。</T>
//============================================================
void MoFeatureLoggerShutdown(){
   MO_STATIC_INFO("Feature logger shutdown.");
}

//============================================================
// <T>释放声音功能处理。</T>
//============================================================
void MoFeatureLoggerRelease(){
   MO_STATIC_INFO("Feature logger release.");
   RLoggerFeature::Destroy();
}

MO_NAMESPACE_END

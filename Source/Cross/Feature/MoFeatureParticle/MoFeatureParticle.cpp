#include "MoFeatureParticle.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化粒子功能处理。</T>
//============================================================
void MoFeatureParticleInitialize(){
   MO_STATIC_INFO("Feature particle initialize.");
}

//============================================================
// <T>启动粒子功能处理。</T>
//============================================================
void MoFeatureParticleStartup(){
   MO_STATIC_INFO("Feature particle startup.");
}

//============================================================
// <T>关闭粒子功能处理。</T>
//============================================================
void MoFeatureParticleShutdown(){
   MO_STATIC_INFO("Feature particle shutdown.");
}

//============================================================
// <T>释放粒子功能处理。</T>
//============================================================
void MoFeatureParticleRelease(){
   MO_STATIC_INFO("Feature particle release.");
}

MO_NAMESPACE_END

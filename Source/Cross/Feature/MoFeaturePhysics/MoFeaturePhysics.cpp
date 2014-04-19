#include "MoFeaturePhysics.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化物理功能处理。</T>
//============================================================
void MoFeaturePhysicsInitialize(){
   MO_STATIC_INFO("Feature physics initialize.");
}

//============================================================
// <T>启动物理功能处理。</T>
//============================================================
void MoFeaturePhysicsStartup(){
   MO_STATIC_INFO("Feature physics startup.");
}

//============================================================
// <T>关闭物理功能处理。</T>
//============================================================
void MoFeaturePhysicsShutdown(){
   MO_STATIC_INFO("Feature physics shutdown.");
}

//============================================================
// <T>释放物理功能处理。</T>
//============================================================
void MoFeaturePhysicsRelease(){
   MO_STATIC_INFO("Feature physics release.");
}

MO_NAMESPACE_END

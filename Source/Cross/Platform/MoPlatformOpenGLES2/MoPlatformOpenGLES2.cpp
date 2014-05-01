#include "MoPlatformOpenGLES2.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoEngineOpenGLES2(){
   RFeatureManager::Instance().Register(FPlatformOpenGLES2Feature::Class());
}

MO_NAMESPACE_END

#include "MoPlatformDirectX10.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoEngineDirectX10(){
   RFeatureManager::Instance().Register(FPlatformDirectX10Feature::Class());
}

MO_NAMESPACE_END

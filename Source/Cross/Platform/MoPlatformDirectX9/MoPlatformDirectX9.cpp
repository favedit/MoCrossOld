#include "MoPlatformDirectX9.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoEngineDirectX9(){
   RFeatureManager::Instance().Register(FPlatformDirectX9Feature::Class());
}

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
TResult SetupFeature(){
   RFeatureManager::Instance().Register(FPlatformDirectX9Feature::Class());
   return ESuccess;
}

MO_NAMESPACE_END

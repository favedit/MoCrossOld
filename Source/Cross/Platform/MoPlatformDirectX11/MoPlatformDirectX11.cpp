#include "MoPlatformDirectX11.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoEngineDirectX11(){
   RFeatureManager::Instance().Register(FPlatformDirectX11Feature::Class());
}

MO_NAMESPACE_END

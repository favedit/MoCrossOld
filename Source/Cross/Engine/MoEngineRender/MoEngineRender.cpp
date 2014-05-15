#include "MoEngineRender.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化引擎渲染功能。</T>
//============================================================
void MoEngineRender(){
   RFeatureManager::Instance().Register(FEngineRenderFeature::Class());
}

MO_NAMESPACE_END

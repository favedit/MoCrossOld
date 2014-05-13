#include "MoPluginScriptMono.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>插件脚本Mono功能。</T>
//============================================================
void MoPluginScriptMono(){
   RFeatureManager::Instance().Register(FPluginScriptMonoFeature::Class());
}

MO_NAMESPACE_END

#include "MoPluginScriptMono.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化窗口引擎处理。</T>
//============================================================
void MoPluginScriptMonoInitialize(){
   MO_STATIC_INFO("Plugin script mono initialize.");
}

//============================================================
// <T>启动窗口引擎处理。</T>
//============================================================
void MoPluginScriptMonoStartup(){
   MO_STATIC_INFO("Plugin script mono startup.");
}

//============================================================
// <T>关闭窗口引擎处理。</T>
//============================================================
void MoPluginScriptMonoShutdown(){
   MO_STATIC_INFO("Plugin script mono shutdown.");
}

//============================================================
// <T>释放窗口引擎处理。</T>
//============================================================
void MoPluginScriptMonoRelease(){
   MO_STATIC_INFO("Plugin script mono release.");
}

MO_NAMESPACE_END

#include "MoFeatureScript.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化脚本功能处理。</T>
//============================================================
void MoFeatureScriptInitialize(){
   MO_STATIC_INFO("Feature script initialize.");
}

//============================================================
// <T>启动脚本功能处理。</T>
//============================================================
void MoFeatureScriptStartup(){
   MO_STATIC_INFO("Feature script startup.");
}

//============================================================
// <T>关闭脚本功能处理。</T>
//============================================================
void MoFeatureScriptShutdown(){
   MO_STATIC_INFO("Feature script shutdown.");
}

//============================================================
// <T>释放脚本功能处理。</T>
//============================================================
void MoFeatureScriptRelease(){
   MO_STATIC_INFO("Feature script release.");
}

MO_NAMESPACE_END

#include "MoFeatureInput.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化输入功能处理。</T>
//============================================================
void MoFeatureInputInitialize(){
   MO_STATIC_INFO("Feature input initialize.");
}

//============================================================
// <T>启动输入功能处理。</T>
//============================================================
void MoFeatureInputStartup(){
   MO_STATIC_INFO("Feature input startup.");
}

//============================================================
// <T>关闭输入功能处理。</T>
//============================================================
void MoFeatureInputShutdown(){
   MO_STATIC_INFO("Feature sound shutdown.");
}

//============================================================
// <T>释放输入功能处理。</T>
//============================================================
void MoFeatureInputRelease(){
   MO_STATIC_INFO("Feature input release.");
}

MO_NAMESPACE_END

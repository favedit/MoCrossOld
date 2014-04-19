#include "MoEngineAnimation.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化动画引擎处理。</T>
//============================================================
void MoEngineAnimationInitialize(){
   MO_STATIC_INFO("Engine animation initialize.");
}

//============================================================
// <T>启动动画引擎处理。</T>
//============================================================
void MoEngineAnimationStartup(){
   MO_STATIC_INFO("Engine animation startup.");
}

//============================================================
// <T>关闭动画引擎处理。</T>
//============================================================
void MoEngineAnimationShutdown(){
   MO_STATIC_INFO("Engine animation shutdown.");
}

//============================================================
// <T>释放动画引擎处理。</T>
//============================================================
void MoEngineAnimationRelease(){
   MO_STATIC_INFO("Engine animation release.");
}

MO_NAMESPACE_END

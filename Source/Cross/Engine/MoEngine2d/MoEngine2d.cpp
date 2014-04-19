#include "MoEngine2d.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>构造2D引擎库。</T>
//
// @return 运行库
//============================================================
void MoEngine2dInitialize(){
   MO_STATIC_INFO("Engine 2d initialize.");
   // 初始化粒子管理器
   RDisplay2dManager::Create();
   RDisplay2dManager::Instance().Setup();
}

//============================================================
// <T>启动2D引擎库。</T>
//============================================================
void MoEngine2dStartup(){
   MO_STATIC_INFO("Engine 2d startup.");
}

//============================================================
// <T>停止2D引擎库。</T>
//============================================================
void MoEngine2dShutdown(){
   MO_STATIC_INFO("Engine 2d shutdown.");
}

//============================================================
// <T>释放2D引擎库。</T>
//============================================================
void MoEngine2dRelease(){
   MO_STATIC_INFO("Engine 2d release.");
}

MO_NAMESPACE_END

#include "MoEngine3d.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>构造3D引擎库。</T>
//
// @return 运行库
//============================================================
void MoEngine3dInitialize(){
   MO_STATIC_INFO("Engine 3d initialize.");
   // 初始化3D资源管理器
   RResource3dManager::Create();
   RResource3dManager::Instance().Setup();
   // 初始化3D实体管理器
   RInstance3dManager::Create();
   RInstance3dManager::Instance().Setup();
}

//============================================================
// <T>启动3D引擎库。</T>
//============================================================
void MoEngine3dStartup(){
   MO_STATIC_INFO("Engine 3d startup.");
}

//============================================================
// <T>停止3D引擎库。</T>
//============================================================
void MoEngine3dShutdown(){
   MO_STATIC_INFO("Engine 3d shutdown.");
}

//============================================================
// <T>释放3D引擎库。</T>
//============================================================
void MoEngine3dRelease(){
   MO_STATIC_INFO("Engine 3d release.");
   //............................................................
   // 释放3D实体管理器
   RInstance3dManager::Destroy();
   // 释放3D资源管理器
   RResource3dManager::Destroy();
}

MO_NAMESPACE_END

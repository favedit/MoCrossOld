#include "MoE3Material.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造实体3D材质管理器。</T>
//============================================================
FMaterial3dConsole::FMaterial3dConsole(){
}

//============================================================
// <T>析构实体3D材质管理器。</T>
//============================================================
FMaterial3dConsole::~FMaterial3dConsole(){
}

//============================================================
// <T>根据名称获得材质实例。</T>
//
// @param pName 名称
// @return 材质实例
//============================================================
FMaterial3d* FMaterial3dConsole::Load(TCharC* pName){
   MO_CHECK(pName, return NULL);
   FMaterial3d* pMaterial = _materials.Find(pName);
   if(pMaterial == NULL){
      // 获得资源
      FRs3dMaterialConsole* pRsMaterialConsole = RResource3dManager::Instance().MaterialConsole();
      FRs3dMaterial* pRsMaterial = pRsMaterialConsole->Find(pName);
      MO_CHECK(pRsMaterial, return NULL);
      // 创建实体
      pMaterial = FMaterial3d::InstanceCreate();
      pMaterial->SetName(pName);
      pMaterial->LoadResource(pRsMaterial);
      _materials.Set(pName, pMaterial);
   }
   return pMaterial;
}

MO_NAMESPACE_END

#include "MoFrContent3dMaterial.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dMaterialConsole, FConsole);

//============================================================
// <T>构造资源3D材质控制台。</T>
//============================================================
FRs3dMaterialConsole::FRs3dMaterialConsole(){
}

//============================================================
// <T>析构资源3D材质控制台。</T>
//============================================================
FRs3dMaterialConsole::~FRs3dMaterialConsole(){
}

//============================================================
// <T>根据资源名称查找资源对象。</T>
//
// @param pName 资源名称
// @return 资源对象
//============================================================
FRs3dMaterial* FRs3dMaterialConsole::Find(TCharC* pName){
   return _materials.Find(pName);
}

//============================================================
// <T>根据资源名称获得资源对象。</T>
//
// @param pName 资源名称
// @return 资源对象
//============================================================
FRs3dMaterial* FRs3dMaterialConsole::Get(TCharC* pName){
   return _materials.Get(pName);
}

//============================================================
// <T>清空内容。</T>
//============================================================
void FRs3dMaterialConsole::Clear(){
   _materials.Clear();
}

MO_NAMESPACE_END

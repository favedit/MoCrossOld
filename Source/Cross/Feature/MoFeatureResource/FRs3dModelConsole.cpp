#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dModelConsole, FConsole);

//============================================================
// <T>构造资源3D模型管理器。</T>
//============================================================
FRs3dModelConsole::FRs3dModelConsole(){
}

//============================================================
// <T>析构资源3D模型管理器。</T>
//============================================================
FRs3dModelConsole::~FRs3dModelConsole(){
}

//============================================================
// <T>根据资源代码加载资源对象。</T>
//
// @param resourceId 资源代码
// @return 资源对象
//============================================================
FRs3dModel* FRs3dModelConsole::Load(TCharC* pName){
   FAssetStream* pStream = RAssetManager::Instance().OpenAssetStreamFormat("asset:/model/%s.ser", pName);
   MO_ERROR_CHECK(pStream, return NULL, "Open model stream failure. (resource_id=%s)", pName);
   // 创建纹理
   FRs3dModel* pModel = MO_CREATE(FRs3dModel);
   pModel->Unserialize(pStream);
   // 释放资源
   RAssetManager::Instance().CloseAssetStream(pStream);
   return pModel;
}

//============================================================
// <T>根据资源代码查找资源对象。</T>
//
// @param resourceId 资源代码
// @return 资源对象
//============================================================
FRs3dModel* FRs3dModelConsole::Find(TCharC* pName){
   FRs3dModel* pModel = _models.Find(pName);
   if(pModel == NULL){
      pModel = Load(pName);
      _models.Set(pName, pModel);
   }
   return pModel;
}

//============================================================
// <T>根据资源代码打开资源对象。</T>
//
// @param resourceId 资源代码
// @return 资源对象
//============================================================
FRs3dModel* FRs3dModelConsole::Open(TCharC* pName){
   FRs3dModel* pModel = Find(pName);
   if(pModel != NULL){
      pModel->Open();
   }
   return pModel;
}

//============================================================
// <T>清空内容。</T>
//============================================================
void FRs3dModelConsole::Clear(){
   _models.Clear();
}

MO_NAMESPACE_END

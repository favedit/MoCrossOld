#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源场景控制台。</T>
//============================================================
FRs3dSceneConsole::FRs3dSceneConsole(){
}

//============================================================
// <T>析构资源场景控制台。</T>
//============================================================
FRs3dSceneConsole::~FRs3dSceneConsole(){
}

//============================================================
// <T>根据资源代码加载资源对象。</T>
//
// @param resourceId 资源代码
// @return 资源对象
//============================================================
FRs3dScene* FRs3dSceneConsole::Load(TCharC* pName){
   MO_CHECK(pName, return NULL);
   FAssetStream* pStream = RAssetManager::Instance().OpenAssetStreamFormat(TC("asset:scene/%s.ser"), pName);
   MO_ERROR_CHECK(pStream, return NULL, TC("Open scene stream failure. (resource=%s)"), pName);
   // 创建纹理
   FRs3dScene* pScene = FRs3dScene::InstanceCreate();
   pScene->Unserialize(pStream);
   // 释放资源
   RAssetManager::Instance().CloseAssetStream(pStream);
   return pScene;
}

//============================================================
// <T>根据资源代码查找资源对象。</T>
//
// @param resourceId 资源代码
// @return 资源对象
//============================================================
FRs3dScene* FRs3dSceneConsole::Find(TCharC* pName){
   FRs3dScene* pTexture = _scenes.Find(pName);
   if(pTexture == NULL){
      pTexture = Load(pName);
      _scenes.Set(pName, pTexture);
   }
   return pTexture;
}

//============================================================
// <T>根据资源代码打开资源对象。</T>
//
// @param resourceId 资源代码
// @return 资源对象
//============================================================
FRs3dScene* FRs3dSceneConsole::Open(TCharC* pName){
   FRs3dScene* pTexture = Find(pName);
   if(pTexture != NULL){
      pTexture->Open();
   }
   return pTexture;
}

//============================================================
// <T>清空内容。</T>
//============================================================
void FRs3dSceneConsole::Clear(){
   _scenes.Clear();
}

MO_NAMESPACE_END

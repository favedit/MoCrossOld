#include "MoGeDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FGameSceneConsole, FConsole);

//============================================================
// <T>构造实体3D场景控制台。</T>
//============================================================
FGameSceneConsole::FGameSceneConsole(){
   _pScenes = MO_CREATE(FGameSceneDictionary);
}

//============================================================
// <T>析构实体3D场景控制台。</T>
//============================================================
FGameSceneConsole::~FGameSceneConsole(){
   MO_DELETE(_pScenes);
}

//============================================================
// <T>收集模型实体。</T>
//
// @param pName 名称
// @return 模型实体
//============================================================
FGameScene* FGameSceneConsole::Load(TCharC* pName){
   MO_CHECK(pName, return NULL);
   // 获得缓冲池
   FGameScene* pScene = _pScenes->Find(pName);
   if(pScene == NULL){
      pScene = FGameScene::InstanceCreate();
      pScene->Setup();
      FRs3dScene* pRsScene = RResource3dManager::Instance().SceneConsole()->Load(pName);
      pScene->LoadResource(pRsScene);
      _pScenes->Set(pName, pScene);
   }
   return pScene;
}

//============================================================
// <T>释放模板实体。</T>
//
// @param pTemplate 模板实体
//============================================================
TResult FGameSceneConsole::Free(FGameScene* pScene){
   MO_CHECK(pScene, return ENull);
   //TCharC* pName = pScene->Name();
   //// 获得缓冲池
   //FScene3d* pScene = _pScenes->Find(pName);
   //if(pScene == NULL){
   //   MO_FATAL("Cant' find scene in dictionary. (name=%s)", pName);
   //   return EFailure;
   //}
   //// 获得对象
   //pScene->Release();
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FGameSceneConsole::Suspend(){
   FGameSceneDictionary::TIteratorC iterator = _pScenes->IteratorC();
   while(iterator.Next()){
      FGameScene* pScene = *iterator;
      pScene->Suspend();
   }
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FGameSceneConsole::Resume(){
   FGameSceneDictionary::TIteratorC iterator = _pScenes->IteratorC();
   while(iterator.Next()){
      FGameScene* pScene = *iterator;
      pScene->Resume();
   }
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FGameSceneConsole::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END

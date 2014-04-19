#include "MoE3Scene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScene3dConsole, FConsole);

//============================================================
// <T>构造实体3D场景控制台。</T>
//============================================================
FScene3dConsole::FScene3dConsole(){
   _pScenes = MO_CREATE(FScene3dDictionary);
}

//============================================================
// <T>析构实体3D场景控制台。</T>
//============================================================
FScene3dConsole::~FScene3dConsole(){
   MO_DELETE(_pScenes);
}

//============================================================
// <T>收集模型实体。</T>
//
// @param pName 名称
// @return 模型实体
//============================================================
FScene3d* FScene3dConsole::Load(TCharC* pName){
   MO_CHECK(pName, return NULL);
   // 获得缓冲池
   FScene3d* pScene = _pScenes->Find(pName);
   if(pScene == NULL){
      pScene = FScene3d::InstanceCreate();
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
TResult FScene3dConsole::Free(FScene3d* pScene){
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
TResult FScene3dConsole::Suspend(){
   FScene3dDictionary::TIteratorC iterator = _pScenes->IteratorC();
   while(iterator.Next()){
      FScene3d* pScene = *iterator;
      pScene->Suspend();
   }
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FScene3dConsole::Resume(){
   FScene3dDictionary::TIteratorC iterator = _pScenes->IteratorC();
   while(iterator.Next()){
      FScene3d* pScene = *iterator;
      pScene->Resume();
   }
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FScene3dConsole::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END

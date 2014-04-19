#include "MoE3Model.h"
#include "MoE3Template.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造实体3D模型管理器。</T>
//============================================================
FTemplate3dConsole::FTemplate3dConsole(){
   _pPools = MO_CREATE(FTemplate3dPoolDictionary);
}

//============================================================
// <T>析构实体3D模型管理器。</T>
//============================================================
FTemplate3dConsole::~FTemplate3dConsole(){
   FTemplate3dPoolDictionary::TIteratorC iterator =  _pPools->IteratorC();
   while(iterator.Next()){
      FTemplate3dPool* pPool = *iterator;
      while(pPool->HasFreeItem()){
         FTemplate3d* pTemplate = pPool->AllocFirst();
         MO_DELETE(pTemplate);
      }
      MO_DELETE(pPool);
   }
   MO_DELETE(_pPools);
}

//============================================================
// <T>创建模型实体。</T>
//
// @param pName 名称
// @return 模型实体
//============================================================
FTemplate3d* FTemplate3dConsole::Create(TCharC* pName){
   MO_CHECK(pName, return NULL);
   // 创建对象
   FTemplate3d* pTemplate = FTemplate3d::InstanceCreate();
   pTemplate->SetName(pName);
   // 加载资源
   Load(pTemplate, pName);
   return pTemplate;
}

//============================================================
// <T>收集模型实体。</T>
//
// @param pName 名称
// @return 模型实体
//============================================================
FTemplate3d* FTemplate3dConsole::Alloc(TCharC* pName){
   MO_CHECK(pName, return NULL);
   // 获得缓冲池
   FTemplate3dPool* pPool = _pPools->Find(pName);
   if(pPool == NULL){
      pPool = MO_CREATE(FTemplate3dPool);
      _pPools->Set(pName, pPool);
   }
   // 获得对象
   if(!pPool->HasFreeItem()){
      FTemplate3d* pTemplate = Create(pName);
      pPool->Store(pTemplate);
   }
   return pPool->AllocFirst();
}

//============================================================
// <T>加载模板资源。</T>
//
// @param pTemplate 模板
// @param pName 名称
//============================================================
TResult FTemplate3dConsole::Load(FTemplate3d* pTemplate, TCharC* pName){
   MO_CHECK(pTemplate, return ENull);
   MO_CHECK(pName, return ENull);
   // 获得模板
   FRs3dTemplate* pResource = RResource3dManager::Instance().TemplateConsole()->Find(pName);
   // 加载资源
   pTemplate->SetName(pName);
   pTemplate->LoadResource(pResource);
   RStageManager::Instance().DisplayProcessor()->Register(pTemplate);
   return ESuccess;
}

//============================================================
// <T>释放模板实体。</T>
//
// @param pTemplate 模板实体
//============================================================
TResult FTemplate3dConsole::Free(FTemplate3d* pTemplate){
   MO_CHECK(pTemplate, return ENull);
   TCharC* pName = pTemplate->Name();
   // 获得缓冲池
   FTemplate3dPool* pPool = _pPools->Find(pName);
   if(pPool == NULL){
      MO_FATAL("Cant' find template pool. (name=%s)", pName);
      return EFailure;
   }
   // 获得对象
   pPool->FreeLast(pTemplate);
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FTemplate3dConsole::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FTemplate3dConsole::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FTemplate3dConsole::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END

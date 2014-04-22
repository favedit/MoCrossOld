#include "MoFrContent3dModel.h"
#include "MoFrContent3dTemplate.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源3D模板管理器。</T>
//============================================================
FRs3dTemplateConsole::FRs3dTemplateConsole(){
   _processLimit = 16;
   _trigger = FMonitorTrigger::InstanceCreate();
   _pLooper = MO_CREATE(FRs3dTemplateLooper);
   _pTemplates = MO_CREATE(FRs3dTemplateDictionary);
}

//============================================================
// <T>析构资源3D模板管理器。</T>
//============================================================
FRs3dTemplateConsole::~FRs3dTemplateConsole(){
   MO_DELETE(_pLooper);
   MO_DELETE(_pTemplates);
}

//============================================================
// <T>根据资源代码加载资源对象。</T>
//
// @param resourceId 资源代码
// @return 资源对象
//============================================================
FRs3dTemplate* FRs3dTemplateConsole::Load(TCharC* pName){
   FAssetStream* pStream = RAssetManager::Instance().OpenAssetStreamFormat("asset:/template/%s.ser", pName);
   MO_ERROR_CHECK(pStream, return NULL, "Open template stream failure. (resource=%s)", pName);
   // 创建纹理
   FRs3dTemplate* pTemplate = MO_CREATE(FRs3dTemplate);
   pTemplate->Unserialize(pStream);
   // 释放资源
   RAssetManager::Instance().CloseAssetStream(pStream);
   // 放入循环器
   _pLooper->Push(pTemplate);
   return pTemplate;
}

//============================================================
// <T>根据资源代码查找资源对象。</T>
//
// @param resourceId 资源代码
// @return 资源对象
//============================================================
FRs3dTemplate* FRs3dTemplateConsole::Find(TCharC* pName){
   FRs3dTemplate* pTemplate = _pTemplates->Find(pName);
   if(pTemplate == NULL){
      pTemplate = Load(pName);
      _pTemplates->Set(pName, pTemplate);
   }
   return pTemplate;
}

//============================================================
// <T>触发刷新处理。</T>
//
// @param 当前时刻
// @return 处理结果
//============================================================
TResult FRs3dTemplateConsole::TriggerRefresh(TTimeTick currentTick){
   _pLooper->Record();
   for(TInt n = 0; n < _processLimit; n++){
      FRs3dTemplate* pTemplate = _pLooper->Next();
      if(pTemplate == NULL){
         break;
      }
      TResult processResultCd = pTemplate->Process();
      if(processResultCd == ESuccess){
         _pLooper->RemoveCurrent();
      }
   }
   return ESuccess;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FRs3dTemplateConsole::Startup(){
   RMonitorManager::Instance().Register(_trigger);
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FRs3dTemplateConsole::Shutdown(){
   RMonitorManager::Instance().Unregister(_trigger);
   return ESuccess;
}

//============================================================
// <T>清空内容。</T>
//
// @return 处理结果
//============================================================
TResult FRs3dTemplateConsole::Clear(){
   return ESuccess;
}

MO_NAMESPACE_END

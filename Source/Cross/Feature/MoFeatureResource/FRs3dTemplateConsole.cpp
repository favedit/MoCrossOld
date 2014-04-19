#include "MoFrContent3dModel.h"
#include "MoFrContent3dTemplate.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源3D模板管理器。</T>
//============================================================
FRs3dTemplateConsole::FRs3dTemplateConsole(){
   _pTemplates = MO_CREATE(FRs3dTemplateDictionary);
}

//============================================================
// <T>析构资源3D模板管理器。</T>
//============================================================
FRs3dTemplateConsole::~FRs3dTemplateConsole(){
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
// <T>清空内容。</T>
//============================================================
void FRs3dTemplateConsole::Clear(){
}

MO_NAMESPACE_END

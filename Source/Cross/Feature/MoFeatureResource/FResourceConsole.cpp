#include "MoFrContent2d.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源控制台。</T>
//============================================================
FResourceConsole::FResourceConsole(){
   _pResources = MO_CREATE(FResourceSet);
}

//============================================================
// <T>析构资源控制台。</T>
//============================================================
FResourceConsole::~FResourceConsole(){
   MO_DELETE(_pResources);
}

//============================================================
// <T>根据类型创建资源对象。</T>
//
// @param typeCd 类型
// @return 资源对象
//============================================================
FResource* FResourceConsole::CreateResource(TResourceType typeCd){
   switch(typeCd){
      case EResourceType_Picture:
         return MO_CREATE(FPictureResource);
      case EResourceType_Animation:
         return MO_CREATE(FAnimationResource);
   }
   return NULL;
}

//============================================================
// <T>启动处理。</T>
//============================================================
void FResourceConsole::Startup(){
}

//============================================================
// <T>关闭处理。</T>
//============================================================
void FResourceConsole::Shutdown(){
}

//============================================================
// <T>根据资源编号查找资源对象。</T>
//
// @param resourceId 资源编号
// @return 资源对象
//============================================================
FResource* FResourceConsole::Find(TResourceId resourceId){
   // 查找资源
   FResource* pResource = _pResources->Find(resourceId);
   if(pResource != NULL){
      return pResource;
   }
   //............................................................
   // 打开数据
   FAssetStream* pStream = RAssetManager::Instance().OpenAssetStreamFormat("/rs/%d.ser", resourceId);
   MO_ERROR_CHECK(pStream, return NULL,
      "Open resource asset failure. (resource_id=%d)", resourceId);
   // 创建资源
   TResourceType typeCd = pStream->ReadInt8();
   pResource = CreateResource(typeCd);
   pResource->Unserialize(pStream);
   // 关闭数据
   RAssetManager::Instance().CloseAssetStream(pStream);
   // 存储资源
   _pResources->Set(resourceId, pResource);
   return pResource;
}

MO_NAMESPACE_END

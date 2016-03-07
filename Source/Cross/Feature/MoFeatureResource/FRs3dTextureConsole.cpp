#include "MoFrContent3dTexture.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dTextureConsole, FConsole);

//============================================================
// <T>构造资源3D纹理控制台。</T>
//============================================================
FRs3dTextureConsole::FRs3dTextureConsole(){
}

//============================================================
// <T>析构资源3D纹理控制台。</T>
//============================================================
FRs3dTextureConsole::~FRs3dTextureConsole(){
}

//============================================================
// <T>根据资源代码加载资源对象。</T>
//
// @param resourceId 资源代码
// @return 资源对象
//============================================================
FRs3dTexture* FRs3dTextureConsole::Load(TCharC* pName){
   MO_CHECK(pName, return NULL);
   TFsPath path;
   path.AppendFormat(TC("asset:/texture/%s.ser"), pName);
   // 创建纹理
   FRs3dTexture* pTexture = FRs3dTexture::InstanceCreate();
   FAssetStream* pStream = RAssetManager::Instance().OpenAssetStream(path);
   MO_ERROR_CHECK(pStream, return NULL, TC("Open texture stream failure. (resource=%s)"), pName);
   // 创建纹理
   pTexture->Unserialize(pStream);
   // 释放资源
   RAssetManager::Instance().CloseAssetStream(pStream);
   // 创建加载器
   //FContentLoader* pLoader = FContentLoader::InstanceCreate();
   //pLoader->SetContent(pTexture);
   //RContentManager::Instance().PushLoader(pLoader);
   return pTexture;
}

//============================================================
// <T>根据资源代码查找资源对象。</T>
//
// @param resourceId 资源代码
// @return 资源对象
//============================================================
FRs3dTexture* FRs3dTextureConsole::Find(TCharC* pName){
   FRs3dTexture* pTexture = _textures.Find(pName);
   if(pTexture == NULL){
      pTexture = Load(pName);
      _textures.Set(pName, pTexture);
   }
   return pTexture;
}

//============================================================
// <T>根据资源代码打开资源对象。</T>
//
// @param resourceId 资源代码
// @return 资源对象
//============================================================
FRs3dTexture* FRs3dTextureConsole::Open(TCharC* pName){
   FRs3dTexture* pTexture = Find(pName);
   if(pTexture != NULL){
      pTexture->Open();
   }
   return pTexture;
}

//============================================================
// <T>清空内容。</T>
//============================================================
void FRs3dTextureConsole::Clear(){
   _textures.Clear();
}

MO_NAMESPACE_END

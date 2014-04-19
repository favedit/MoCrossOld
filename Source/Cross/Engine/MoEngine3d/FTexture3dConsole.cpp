#include "MoE3Texture.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造实体3D纹理管理器。</T>
//============================================================
FTexture3dConsole::FTexture3dConsole(){
   _pTextures = MO_CREATE(FTexture3dTextureDictionary);
}

//============================================================
// <T>析构实体3D纹理管理器。</T>
//============================================================
FTexture3dConsole::~FTexture3dConsole(){
   MO_DELETE(_pTextures);
}

//============================================================
// <T>根据名称查找纹理实例。</T>
//
// @param pName 名称
// @return 纹理实例
//============================================================
FTexture3dTexture* FTexture3dConsole::Find(TCharC* pName){
   MO_CHECK(pName, return NULL);
   FTexture3dTexture* pTexture = _pTextures->Find(pName);
   return pTexture;
}

//============================================================
// <T>根据名称加载纹理实例。</T>
//
// @param pName 名称
// @return 纹理实例
//============================================================
FTexture3dTexture* FTexture3dConsole::Load(TCharC* pName){
   MO_CHECK(pName, return NULL);
   FTexture3dTexture* pTexture = _pTextures->Find(pName);
   if(pTexture == NULL){
      // 获得资源
      FRs3dTextureConsole* pRsTextureConsole = RResource3dManager::Instance().TextureConsole();
      FRs3dTexture* pRsTexture = pRsTextureConsole->Find(pName);
      // 创建实体
      pTexture = FTexture3dTexture::InstanceCreate();
      pTexture->LoadResource(pRsTexture);
      pTexture->Open();
      _pTextures->Set(pName, pTexture);
   }
   return pTexture;
}

MO_NAMESPACE_END

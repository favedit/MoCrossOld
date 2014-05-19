#include "MoE3Material.h"
#include "MoE3Instance.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMaterial3dTexture, FRenderMaterialTexture);

//============================================================
// <T>构造实体3D材质纹理。</T>
//============================================================
FMaterial3dTexture::FMaterial3dTexture(){
}

//============================================================
// <T>析构实体3D材质纹理。</T>
//============================================================
FMaterial3dTexture::~FMaterial3dTexture(){
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
//============================================================
TResult FMaterial3dTexture::LoadResource(FRs3dMaterialTexture* pResource){
   MO_CHECK(pResource, return ENull);
   _resource = pResource;
   // 加载纹理
   TCharC* pTextureCode = pResource->Code();
   TCharC* pTextureName = pResource->TextureName();
   // 获得纹理实体
   FTexture3dTexture* pTexture = RInstance3dManager::Instance().TextureConsole()->Load(pTextureName);
   MO_ERROR_CHECK(pTexture, return EFailure,
         "Open instance texture failure. (texture=%s)", pTextureName);
   FTexture3dBitmap* pBitmap = pTexture->FindByPack(pTextureCode);
   MO_ERROR_CHECK(pBitmap, return EFailure,
         "Open resource bitmap is empty. (texture=%s, type=%s)", pTextureName, pTextureCode);
   _code = pTextureCode;
   _renderTexture = pBitmap->RenderTexture();
   MO_ERROR_CHECK(_renderTexture, return EFailure,
         "Open resource bitmap failure. (texture=%s, type=%s)", pTextureName, pTextureCode);
   return ESuccess;
}

MO_NAMESPACE_END

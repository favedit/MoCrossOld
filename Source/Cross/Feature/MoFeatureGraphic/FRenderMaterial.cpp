#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderMaterial, FMaterial);

//============================================================
// <T>构造渲染材质。</T>
//============================================================
FRenderMaterial::FRenderMaterial(){
}

//============================================================
// <T>析构渲染材质。</T>
//============================================================
FRenderMaterial::~FRenderMaterial(){
}

//============================================================
// <T>增加一个渲染材质纹理。</T>
//
// @param pMaterialTexture 渲染材质纹理
// @return 处理结果
//============================================================
TResult FRenderMaterial::TexturePush(FRenderMaterialTexture* pMaterialTexture){
   MO_CHECK(pMaterialTexture, return ENull);
   _textures.Push(pMaterialTexture);
   return ESuccess;
}

//============================================================
// <T>减少一个渲染材质纹理。</T>
//
// @param pMaterialTexture 渲染材质纹理
// @return 处理结果
//============================================================
TResult FRenderMaterial::TextureRemove(FRenderMaterialTexture* pMaterialTexture){
   MO_CHECK(pMaterialTexture, return ENull);
   _textures.Remove(pMaterialTexture);
   return ESuccess;
}

//============================================================
// <T>清空所有渲染材质纹理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderMaterial::TextureClear(){
   _textures.Clear();
   return ESuccess;
}

MO_NAMESPACE_END

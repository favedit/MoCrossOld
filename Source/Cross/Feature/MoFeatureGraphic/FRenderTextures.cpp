#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

//MO_CLASS_IMPLEMENT_INHERITS(FRenderTextures, FInstance);
//
////============================================================
//// <T>构造渲染纹理集合。</T>
////============================================================
//FRenderTextures::FRenderTextures(){
//   _pTextures = MO_CREATE(FRenderTextureCollection);
//}
//
////============================================================
//// <T>析构渲染纹理集合。</T>
////============================================================
//FRenderTextures::~FRenderTextures(){
//   MO_DELETE(_pTextures);
//}
//
////============================================================
//// <T>获得纹理总数。</T>
////
//// @return 纹理总数
////============================================================
//TInt FRenderTextures::Count(){
//   return _pTextures->Count();
//}
//
////============================================================
//// <T>获得指定索引位置的纹理。</T>
////
//// @param index 索引位置
//// @return 纹理
////============================================================
//FRenderTexture* FRenderTextures::Get(TInt index){
//   return _pTextures->Get(index);
//}
//
////============================================================
//// <T>增加一个纹理。</T>
////
//// @param pTexture 纹理
////============================================================
//void FRenderTextures::Push(FRenderTexture* pTexture){
//   _pTextures->Push(pTexture);
//}
//
////============================================================
//// <T>根据差值器类型查找渲染纹理。</T>
////
//// @param samplerCd 差值器类型
//// @return 渲染纹理
////============================================================
//FRenderTexture* FRenderTextures::FindTexture(ERenderSampler samplerCd){
//   TInt count = _pTextures->Count();
//   for(TInt n = 0; n < count; n++){
//      FRenderTexture* pTexture = _pTextures->Get(n);
//      if(pTexture->SamplerCd() == samplerCd){
//         return pTexture;
//      }
//   }
//   return NULL;
//}
//
////============================================================
//// <T>根据差值器类型获得渲染纹理。</T>
////
//// @param samplerCd 差值器类型
//// @return 渲染纹理
////============================================================
//FRenderTexture* FRenderTextures::GetTexture(ERenderSampler samplerCd){
//   FRenderTexture* pTexture = FindTexture(samplerCd);
//   MO_CHECK(pTexture, return NULL);
//   return pTexture;
//}

MO_NAMESPACE_END

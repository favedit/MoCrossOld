#include "MoFgMaterial.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMaterial, FInstance);

//============================================================
// <T>构造材质。</T>
//============================================================
FMaterial::FMaterial(){
   _effectName = "automatic";
   Reset();
   //_level = 0;
   //_optionDepth = EFalse;
   //_optionAlpha = EFalse;
   //_blendSourceMode = ERenderBlendMode_SourceAlpha;
   //_blendTargetMode = ERenderBlendMode_OneMinusSourceAlpha;
   //MO_CLEAR(_pTextures);
}

//============================================================
// <T>析构材质。</T>
//============================================================
FMaterial::~FMaterial(){
   //MO_DELETE(_pTextures);
}

//============================================================
// <T>判断两个材质是否相等。</T>
//
// @param pMaterial 材质
//============================================================
TInt FMaterial::Compare(FMaterial* pMaterial){
   //// 比较技术
   //ERenderTechnique techniqueCd = pMaterial->TechniqueCd();
   //if(_techniqueCd != techniqueCd){
   //   return _techniqueCd - techniqueCd;
   //}
   //// 比较效果
   //ERenderEffect effectCd = pMaterial->EffectCd();
   //if(_effectCd != effectCd){
   //   return _effectCd - effectCd;
   //}
   //// 比较层级
   //TInt level = pMaterial->Level();
   //if(_level != level){
   //   return _level - level;
   //}
   //// 比较纹理
   //TInt count = TextureCount();
   //TInt targetCount = pMaterial->TextureCount();
   //if(targetCount != count){
   //   return count - targetCount;
   //}
   //for(TInt n = 0; n < count; n++){
   //   ITexture* piTexture = _pTextures->Textures()->Get(n);
   //   ITexture* piTargetTexture = pMaterial->Textures()->Textures()->Get(n);
   //   if(piTexture != piTargetTexture){
   //      return piTexture - piTargetTexture;
   //   }
   //}
   return 0;
}

//============================================================
// <T>清空所有纹理。</T>
//
// @param pTexture 纹理
//============================================================
//void FMaterial::TextureClear(){
//   if(_pTextures != NULL){
//      _pTextures->Textures()->Clear();
//   }
//}
//
////============================================================
//// <T>设置一个纹理。</T>
////
//// @param pTexture 纹理
////============================================================
//void FMaterial::TextureSet(FRenderTexture* pTexture){
//   MO_ASSERT(pTexture);
//   if(_pTextures == NULL){
//      _pTextures = MO_CREATE(FRenderTextures);
//   }
//   _pTextures->Textures()->Clear();
//   _pTextures->Textures()->Push(pTexture);
//}
//
////============================================================
//// <T>增加一个纹理。</T>
////
//// @param pTexture 纹理
////============================================================
//void FMaterial::TexturePush(FRenderTexture* pTexture){
//   MO_ASSERT(pTexture);
//   if(_pTextures == NULL){
//      _pTextures = MO_CREATE(FRenderTextures);
//   }
//   _pTextures->Textures()->Push(pTexture);
//}
//
////============================================================
//// <T>减少一个纹理。</T>
////
//// @param pTexture 纹理
////============================================================
//void FMaterial::TextureRemove(FRenderTexture* pTexture){
//   MO_ASSERT(pTexture);
//   if(_pTextures != NULL){
//      _pTextures->Textures()->Remove(pTexture);
//   }
//}

//============================================================
// <T>接收一个材质。</T>
//
// @param pMaterial 材质
// @return 处理结果
//============================================================
TResult FMaterial::AssignOption(FMaterial* pMaterial){
   MO_CHECK(pMaterial, return ENull);
   _name.Assign(pMaterial->Name());
   _effectName.Assign(pMaterial->EffectName());
   // 设置配置
   _optionMerge = pMaterial->OptionMerge();
   _optionSort = pMaterial->OptionSort();
   _sortLevel = pMaterial->SortLevel();
   _optionAlpha = pMaterial->OptionAlpha();
   _optionDepth = pMaterial->OptionDepth();
   _optionCompare.Assign(pMaterial->OptionCompare());
   _optionDouble = pMaterial->OptionDouble();
   _optionShadow = pMaterial->OptionShadow();
   _optionShadowSelf = pMaterial->OptionShadowSelf();
   return ESuccess;
}

//============================================================
// <T>接收一个材质。</T>
//
// @param pMaterial 材质
// @return 处理结果
//============================================================
TResult FMaterial::AssignValue(FMaterial* pMaterial){
   MO_CHECK(pMaterial, return ENull);
   // 设置颜色
   _color = pMaterial->Color();
   _alpha = pMaterial->Alpha();
   // 设置材质
   _ambientColor.Assign(pMaterial->AmbientColor());
   _ambientShadow = pMaterial->AmbientShadow();
   _diffuseColor.Assign(pMaterial->DiffuseColor());
   _diffuseShadow = pMaterial->DiffuseShadow();
   _diffuseViewColor.Assign(pMaterial->DiffuseViewColor());
   _diffuseViewShadow = pMaterial->DiffuseViewShadow();
   _specularColor.Assign(pMaterial->SpecularColor());
   _specularShadow = pMaterial->SpecularShadow();
   _specularInfo = pMaterial->SpecularInfo();
   _specularViewColor.Assign(pMaterial->SpecularViewColor());
   _specularViewShadow = pMaterial->SpecularViewShadow();
   _specularViewInfo = pMaterial->SpecularViewInfo();
   _reflectColor.Assign(pMaterial->ReflectColor());
   return ESuccess;
}

//============================================================
// <T>合并一个材质。</T>
//
// @param pMaterial 材质
// @return 处理结果
//============================================================
TResult FMaterial::Merge(FMaterial* pMaterial){
   MO_CHECK(pMaterial, return ENull);
   // 融合颜色
   _color.Multiply(pMaterial->Color());
   _alpha.Multiply(pMaterial->Alpha());
   // 融合材质
   _ambientColor.AssignPower(pMaterial->AmbientColor());
   _diffuseColor.AssignPower(pMaterial->DiffuseColor());
   _diffuseViewColor.AssignPower(pMaterial->DiffuseViewColor());
   _specularColor.AssignPower(pMaterial->SpecularColor());
   _specularInfo.Multiply(pMaterial->SpecularInfo());
   _specularViewColor.AssignPower(pMaterial->SpecularViewColor());
   _specularViewInfo.Multiply(pMaterial->SpecularViewInfo());
   _reflectColor.AssignPower(pMaterial->ReflectColor());
   return ESuccess;
}

//============================================================
// <T>重置所有参数。</T>
//
// @param pMaterial 材质
// @return 处理结果
//============================================================
TResult FMaterial::Reset(){
   // 设置参数
   _optionMerge = EFalse;
   _optionSort = EFalse;
   _sortLevel = 0;
   _optionAlpha = EFalse;
   _optionDepth = ETrue;
   _optionCompare.Assign("Less");
   _optionDouble = EFalse;
   _optionShadow = ETrue;
   _optionShadowSelf = ETrue;
   // 设置颜色
   _ambientColor.SetAll(1.0f);
   _ambientShadow = 0.0f;
   _diffuseColor.SetAll(1.0f);
   _diffuseShadow = 0.0f;
   _diffuseViewColor.SetAll(1.0f);
   _diffuseViewShadow = 0.0f;
   _specularColor.SetAll(1.0f);
   _specularShadow = 0.0f;
   _specularInfo.Set(1.0, 1.0, 1.0, 1.0);
   _specularViewColor.SetAll(1.0f);
   _specularViewShadow = 0.0f;
   _specularViewInfo.Set(1.0, 1.0, 1.0, 1.0);
   _reflectColor.SetAll(1.0f);
   return ESuccess;
}

MO_NAMESPACE_END

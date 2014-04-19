#include "MoE3Scene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScene3dMaterial, FMaterial);

//============================================================
// <T>构造场景3D材质。</T>
//============================================================
FScene3dMaterial::FScene3dMaterial(){
}

//============================================================
// <T>析构场景3D材质。</T>
//============================================================
FScene3dMaterial::~FScene3dMaterial(){
}

//============================================================
// <T>加载显示资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FScene3dMaterial::LoadSceneResource(FRs3dSceneMaterial* pResource){
   MO_CHECK(pResource, return ENull);
   SMaterialInfo& info = pResource->Info();
   _name = pResource->Name();
   // 设置配置
   _optionMerge = info.optionMerge;
   _optionSort = info.optionSort;
   _sortLevel = info.sortLevel;
   _optionAlpha = info.optionAlpha;
   _optionDepth = info.optionDepth;
   _optionCompare = (TCharC*)info.optionCompare;
   _optionDouble = info.optionDouble;
   _optionShadow = info.optionShadow;
   _optionShadowSelf = info.optionShadowSelf;
   // 设置颜色
   _color.Set(info.colorMin, info.colorMax, info.colorRate, info.colorMerge);
   // 设置透明
   _alpha.Set(info.alphaBase, info.alphaRate, info.alphaLevel, info.alphaMerge);
   // 设置材质
   _ambientColor.Assign(info.ambientColor);
   _ambientShadow = info.ambientShadow;
   _diffuseColor.Assign(info.diffuseColor);
   _diffuseShadow = info.diffuseShadow;
   _diffuseViewColor.Assign(info.diffuseViewColor);
   _diffuseViewShadow = info.diffuseViewShadow;
   _specularColor.Assign(info.specularColor);
   _specularShadow = info.specularShadow;
   _specularInfo.Set(info.specularBase, info.specularRate, info.specularAverage, info.specularShadow);
   _specularViewColor.Assign(info.specularViewColor);
   _specularViewShadow = info.specularViewShadow;
   _specularViewInfo.Set(info.specularViewBase, info.specularViewRate, info.specularViewAverage, info.specularViewShadow);
   _reflectColor.Assign(info.reflectColor);
   return ESuccess;
}

MO_NAMESPACE_END

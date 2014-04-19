#include "MoFrContent3dBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造实体3D材质。</T>
//============================================================
SMaterialInfo::SMaterialInfo(){
   // 设置光源
   optionLight = ETrue;
   // 设置合并
   optionMerge = EFalse;
   // 设置排序
   optionSort = ETrue;
   // 排序级别
   sortLevel = 0;
   // 设置透明
   optionAlpha = EFalse;
   // 设置深度
   optionDepth = ETrue;
   // 设置比较
   // optionCompare = EGeCompare.Less;
   // 设置双面
   optionDouble = EFalse;
   // 设置影子
   optionShadow = ETrue;
   // 设置自阴影
   optionShadowSelf = ETrue;
   // 设置动态
   optionDynamic = EFalse;
   // 设置透射
   optionTransmittance = EFalse;
   // 设置不透明度
   optionOpacity = EFalse;
   // 设置法线缩放 (未确定)
   optionNormalScale = ETrue;
   // 设置边线 (未确定)
   optionBorder = ETrue;
   // 设置高度 (未确定)
   optionHeight = EFalse;
   // 总体强度
   powerRate = 1.0f;
   // 总体衰减
   powerAttenuation = 1.0f;
   // 总体融合
   powerMerge = 1.0f;
   // 总体阴影
   powerShadow = 1.0f;
   // 纹理位置X
   coordX = 0.0f;
   // 纹理位置Y
   coordY = 0.0f;
   // 纹理宽度比率
   coordRateWidth = 1.0f;
   // 纹理高度比率
   coordRateHeight = 1.0f;
   // 颜色最小
   colorMin = 0.0f;
   // 颜色最大
   colorMax = 1.0f;
   // 颜色比率
   colorRate = 1.0f;
   // 颜色融合
   colorMerge = 0.5f;
   // 透明基础
   alphaBase = 0.2f;
   // 透明比率
   alphaRate = 1.0f;
   // 透明级别
   alphaLevel = 1.0f;
   // 透明合并
   alphaMerge = 1.0f;
   // 环境光颜色 (RGB=颜色，A=强度)
   ambientColor.Set(0.0f, 0.0f, 0.0f, 1.0f);
   // 环境光阴影
   ambientShadow = 1.0f;
   // 散射光颜色 (RGB=颜色，A=强度)
   diffuseColor.Set(0.0f, 0.0f, 0.0f, 1.0f);
   // 散射光阴影
   diffuseShadow = 1.0f;
   // 散射光相机颜色 (RGB=颜色，A=强度)
   diffuseViewColor.Set(1.0f, 1.0f, 1.0f, 0.0f);
   // 散射光视角阴影
   diffuseViewShadow = 1.0f;
   // 高光颜色 (RGB=颜色，A=强度)
   specularColor.Set(1.0f, 1.0f, 1.0f, 0.0f);
   // 高光基础
   specularBase = 1.0f;
   // 高光比率
   specularRate = 1.0f;
   // 高光平均
   specularAverage = 1.0f;
   // 高光阴影
   specularShadow = 1.0f;
   // 高光相机颜色 (RGB=颜色，A=强度)
   specularViewColor.Set(1.0f, 1.0f, 1.0f, 0.0f);
   // 视角高光基础
   specularViewBase = 1.0f;
   // 视角高光比率
   specularViewRate = 1.0f;
   // 视角高光平均
   specularViewAverage = 1.0f;
   // 视角高光阴影
   specularViewShadow = 1.0f;
   // 反射颜色 (RGB=颜色，A=强度)
   reflectColor.Set(1.0f, 1.0f, 1.0f, 0.0f);
   // 反射融合
   reflectMerge = 1.0f;
   // 反射阴影
   reflectShadow = 1.0f;
   // 折射正面颜色 (RGB=颜色，A=强度)
   refractFrontColor.Set(1.0f, 1.0f, 1.0f, 0.0f);
   // 折射正面融合
   refractFrontMerge = 1.0f;
   // 折射正面阴影
   refractFrontShadow = 1.0f;
   // 折射背面颜色 (RGB=颜色，A=强度)
   refractBackColor.Set(1.0f, 1.0f, 1.0f, 0.0f);
   // 折射背面融合
   refractBackMerge = 1.0f;
   // 折射背面阴影
   refractBackShadow = 1.0f;
   // 不透明颜色 (RGB=颜色，A=强度)
   opacityColor.Set(1.0f, 1.0f, 1.0f, 0.0f);
   // 不透明比率
   opacityRate = 0.0f;
   // 不透明透明
   opacityAlpha = 0.0f;
   // 不透明深度
   opacityDepth = 0.0f;
   // 不透明透射
   opacityTransmittance = 0.0f;
   // 自发光颜色
   emissiveColor.Set(0.0f, 0.0f, 0.0f, 0.0f);
   // 自发光设置
   emissive.Set(1.0f, 1.0f, 1.0f, 0.0f);
}

//============================================================
// <T>析构实体3D材质。</T>
//============================================================
SMaterialInfo::~SMaterialInfo(){
}

//============================================================
// <T>接收设置。</T>
//
// @param p:material 材质基础
//============================================================
void SMaterialInfo::AssignOption(SMaterialInfo* pMaterial){
   //// 接收属性
   //effectName = p.effectName;
   //transformName = p.transformName;
   //// 接收设置
   //optionLight = p.optionLight;
   //optionMerge = p.optionMerge;
   //optionSort = p.optionSort;
   //sortLevel = p.sortLevel;
   //optionAlpha = p.optionAlpha;
   //optionDepth = p.optionDepth;
   //optionCompare = p.optionCompare;
   //optionDouble = p.optionDouble;
   //optionShadow = p.optionShadow;
   //optionShadowSelf = p.optionShadowSelf;
   //optionDynamic = p.optionDynamic;
   //optionTransmittance = p.optionTransmittance;
   //optionOpacity = p.optionOpacity;
}
      
//============================================================
// <T>接收光照。</T>
//
// @param p:material 材质基础
//============================================================
void SMaterialInfo::AssignLight(SMaterialInfo* pMaterial){
   //// 接受顶点色
   //colorMin = p.colorMin;
   //colorMax = p.colorMax;
   //colorRate = p.colorRate;
   //colorMerge = p.colorMerge;
   //// 加载透明
   //alphaBase = p.alphaBase;
   //alphaRate = p.alphaRate;
   //alphaLevel = p.alphaLevel;
   //alphaMerge = p.alphaMerge;
   //// 加载环境设置
   //ambientColor.assign(p.ambientColor);
   //ambientShadow = p.ambientShadow;
   //diffuseColor.assign(p.diffuseColor);
   //diffuseShadow = p.diffuseShadow;
   //diffuseViewColor.assign(p.diffuseViewColor);
   //diffuseViewShadow = p.diffuseViewShadow;
   //specularColor.assign(p.specularColor);
   //specularBase = p.specularBase;
   //specularRate = p.specularRate;
   //specularAverage = p.specularAverage;
   //specularShadow = p.specularShadow;
   //specularViewColor.assign(p.specularViewColor);
   //specularViewBase = p.specularViewBase;
   //specularViewRate = p.specularViewRate;
   //specularViewAverage = p.specularViewAverage;
   //specularViewShadow = p.specularViewShadow;
   //emissiveColor.assign(p.emissive);
}
      
//============================================================
// <T>反序列化数据信息。</T>
//
// @param p:input 输入数据流
//============================================================
void SMaterialInfo::Unserialize(IDataInput* pInput){
   // 读取设置
   effectName.UnserializeAutomatic(pInput);
   transformName.UnserializeAutomatic(pInput);
   optionLight = RGeomFlag::ToBoolean(pInput->ReadInt8(), optionLight);
   optionMerge = RGeomFlag::ToBoolean(pInput->ReadInt8(), optionMerge);
   optionSort = RGeomFlag::ToBoolean(pInput->ReadInt8(), optionSort);
   sortLevel = pInput->ReadInt32();
   optionAlpha = RGeomFlag::ToBoolean(pInput->ReadInt8(), optionAlpha);
   optionDepth = RGeomFlag::ToBoolean(pInput->ReadInt8(), optionDepth);
   optionCompare.UnserializeAutomatic(pInput);
   optionDouble = RGeomFlag::ToBoolean(pInput->ReadInt8(), optionDouble);
   optionShadow = RGeomFlag::ToBoolean(pInput->ReadInt8(), optionShadow);
   optionShadowSelf = RGeomFlag::ToBoolean(pInput->ReadInt8(), optionShadowSelf);
   optionDynamic = RGeomFlag::ToBoolean(pInput->ReadInt8(), optionDynamic);
   optionTransmittance = RGeomFlag::ToBoolean(pInput->ReadInt8(), optionTransmittance);
   optionOpacity = RGeomFlag::ToBoolean(pInput->ReadInt8(), optionOpacity);
   // 读取纹理
   coordRateWidth = pInput->ReadFloat();
   coordRateHeight = pInput->ReadFloat();
   // 读取颜色
   colorMin = pInput->ReadFloat();
   colorMax = pInput->ReadFloat();
   colorRate = pInput->ReadFloat();
   colorMerge = pInput->ReadFloat();         
   // 读取透明
   alphaBase = pInput->ReadFloat();
   alphaRate = pInput->ReadFloat();
   alphaLevel = pInput->ReadFloat();
   alphaMerge = pInput->ReadFloat();
   // 读取颜色
   ambientColor.Unserialize(pInput);
   ambientShadow = pInput->ReadFloat();
   diffuseColor.Unserialize(pInput);
   diffuseShadow = pInput->ReadFloat();
   diffuseViewColor.Unserialize(pInput);
   diffuseViewShadow = pInput->ReadFloat();
   specularColor.Unserialize(pInput);
   specularBase = pInput->ReadFloat();
   specularRate = pInput->ReadFloat();
   specularAverage = pInput->ReadFloat();
   specularShadow = pInput->ReadFloat();
   specularViewColor.Unserialize(pInput);
   specularViewBase = pInput->ReadFloat();
   specularViewRate = pInput->ReadFloat();
   specularViewAverage = pInput->ReadFloat();
   specularViewShadow = pInput->ReadFloat();
   reflectColor.Unserialize(pInput);
   reflectMerge = pInput->ReadFloat();
   reflectShadow = pInput->ReadFloat();
   refractFrontColor.Unserialize(pInput);
   refractBackColor.Unserialize(pInput);
   opacityColor.Unserialize(pInput);
   opacityRate = pInput->ReadFloat();
   opacityAlpha = pInput->ReadFloat();
   opacityDepth = pInput->ReadFloat();
   opacityTransmittance = pInput->ReadFloat();
   emissive.Unserialize(pInput);
}
      
//============================================================
// <T>重置数据。</T>
//============================================================
void SMaterialInfo::Reset(){
   // 重置纹理
   coordRateWidth = 1.0f;
   coordRateHeight = 1.0f;
   // 重置颜色
   colorMin = 0.0f;
   colorMax = 1.0f;
   colorRate = 1.0f;
   colorMerge = 0.5f;
   // 重置透明
   alphaBase = 0.2f;
   alphaRate = 1.0f;
   alphaLevel = 1.0f;
   alphaMerge = 1.0f;
   // 重置颜色
   ambientColor.SetAll(1.0f);
   ambientShadow = 1.0f;
   diffuseColor.SetAll(1.0f);
   diffuseShadow = 1.0f;
   diffuseViewColor.SetAll(1.0f);
   diffuseViewShadow = 1.0f;
   specularColor.SetAll(1.0f);
   specularBase = 1.0f;
   specularRate = 1.0f;
   specularAverage = 1.0f;
   specularShadow = 1.0f;
   specularViewColor.SetAll(1.0f);
   specularViewBase = 1.0f;
   specularViewRate = 1.0f;
   specularViewAverage = 1.0f;
   specularViewShadow = 1.0f;
   reflectMerge = 1.0f;
   reflectShadow = 1.0f;
   refractFrontColor.SetAll(1.0f);
   refractBackColor.SetAll(1.0f);
   opacityColor.SetAll(1.0f);
   opacityRate = 1.0f;
   opacityAlpha = 1.0f;
   opacityDepth = 1.0f;
   opacityTransmittance = 1.0f;
   emissive.SetAll(1.0f);
}
      
MO_NAMESPACE_END

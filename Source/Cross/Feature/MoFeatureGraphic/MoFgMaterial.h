#ifndef __MO_FG_MATERIAL_H__
#define __MO_FG_MATERIAL_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>纹理对象。</T>
//============================================================
class MO_FG_DECLARE FTexture : public FGraphicObject
{
   MO_CLASS_DECLARE_INHERITS(FTexture, FGraphicObject);
protected:
   TGraphicHandle _graphicHandle;
   SIntSize2 _size;
public:
   FTexture();
   MO_ABSTRACT ~FTexture();
public:
   //------------------------------------------------------------
   // <T>获得句柄。</T>
   MO_INLINE TGraphicHandle& GraphicHandle(){
      return _graphicHandle;
   }
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
public:
   MO_ABSTRACT void Free();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FTexture*> FTextureCollection;

//============================================================
// <T>材质颜色信息。</T>
//============================================================
struct SMaterialColorInfo
{
public:
   // 颜色最小
   TFloat min;
   // 颜色最大
   TFloat max;
   // 颜色比率
   TFloat rate;
   // 颜色融合
   TFloat merge;
public:
   //------------------------------------------------------------
   // <T>构造材质颜色信息。</T>
   SMaterialColorInfo(){
      Reset();
   }
public:
   //------------------------------------------------------------
   // <T>设置处理。</T>
   MO_INLINE void Set(TFloat minValue, TFloat maxValue, TFloat rateValue, TFloat mergeValue){
      min = minValue;
      max = maxValue;
      rate = rateValue;
      merge = mergeValue;
   }
   //------------------------------------------------------------
   // <T>乘以信息。</T>
   MO_INLINE void Multiply(const SMaterialColorInfo& info){
      min *= info.min;
      max *= info.max;
      rate *= info.rate;
      merge *= info.merge;
   }
public:
   //------------------------------------------------------------
   // <T>重置信息。</T>
   MO_INLINE void Reset(){
      min = 0.0f;
      max = 1.0f;
      rate = 1.0f;
      merge = 1.0f;
   }
};
//============================================================
// <T>材质透明信息。</T>
//============================================================
struct SMaterialAlphaInfo
{
public:
   // 透明基础
   TFloat base;
   // 透明比率
   TFloat rate;
   // 透明级别
   TFloat level;
   // 透明合并
   TFloat merge;
public:
   //------------------------------------------------------------
   // <T>构造材质高光信息。</T>
   SMaterialAlphaInfo(){
      Reset();
   }
public:
   //------------------------------------------------------------
   // <T>设置处理。</T>
   MO_INLINE void Set(TFloat baseValue, TFloat rateValue, TFloat levelValue, TFloat mergeValue){
      base = baseValue;
      rate = rateValue;
      level = levelValue;
      merge = mergeValue;
   }
   //------------------------------------------------------------
   // <T>乘以信息。</T>
   MO_INLINE void Multiply(const SMaterialAlphaInfo& info){
      base *= info.base;
      rate *= info.rate;
      level *= info.level;
      merge *= info.merge;
   }
public:
   //------------------------------------------------------------
   // <T>重置信息。</T>
   MO_INLINE void Reset(){
      base = 0.0f;
      rate = 1.0f;
      level = 1.0f;
      merge = 1.0f;
   }
};

//============================================================
// <T>材质高光信息。</T>
//============================================================
struct SMaterialSpecularInfo
{
public:
   // 基础
   TFloat base;
   // 比率
   TFloat rate;
   // 平均
   TFloat average;
   // 阴影
   TFloat shadow;
public:
   //------------------------------------------------------------
   // <T>构造材质高光信息。</T>
   SMaterialSpecularInfo(){
      Reset();
   }
public:
   //------------------------------------------------------------
   // <T>设置处理。</T>
   MO_INLINE void Set(TFloat baseValue, TFloat rateValue, TFloat avarageValue, TFloat shadowValue){
      base = baseValue;
      rate = rateValue;
      average = avarageValue;
      shadow = shadowValue;
   }
   //------------------------------------------------------------
   // <T>乘以信息。</T>
   MO_INLINE void Multiply(const SMaterialSpecularInfo& info){
      base *= info.base;
      rate *= info.rate;
      average *= info.average;
      shadow *= info.shadow;
   }
public:
   //------------------------------------------------------------
   // <T>重置信息。</T>
   MO_INLINE void Reset(){
      base = 0.0f;
      rate = 0.0f;
      average = 0.0f;
      shadow = 0.0f;
   }
};

//============================================================
// <T>材质。</T>
//============================================================
class MO_FG_DECLARE FMaterial : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FMaterial, FInstance);
protected:
   // 名称
   TString _name;
   // 效果名称
   TString _effectName;
   // 变换名称
   TString transformName;
protected:
   // 设置光源
   //TBool _optionLight;
   // 设置合并
   TBool _optionMerge;
   // 设置排序
   TBool _optionSort;
   // 排序级别
   TInt _sortLevel;
   // 设置透明
   TBool _optionAlpha;
   // 设置深度
   TBool _optionDepth;
   // 设置比较
   TString _optionCompare;
   // 设置双面
   TBool _optionDouble;
   // 设置影子
   TBool _optionShadow;
   // 设置自阴影
   TBool _optionShadowSelf;
   //// 设置动态
   //TBool _optionDynamic;
   //// 设置透射
   //TBool _optionTransmittance;
   //// 设置不透明度
   //TBool _optionOpacity;
   //// 设置法线缩放 (未确定)
   //TBool _optionNormalScale;
   //// 设置边线 (未确定)
   //TBool _optionBorder;
   //// 设置高度 (未确定)
   //TBool _optionHeight;
   //// 总体强度
   //TFloat _powerRate;
   //// 总体衰减
   //TFloat _powerAttenuation;
   //// 总体融合
   //TFloat _powerMerge;
   //// 总体阴影
   //TFloat _powerShadow;
   //// 纹理位置X
   //TFloat _coordX;
   //// 纹理位置Y
   //TFloat _coordY;
   //// 纹理宽度比率
   //TFloat _coordRateWidth;
   //// 纹理高度比率
   //TFloat _coordRateHeight;
   // 颜色信息
   SMaterialColorInfo _color;
   // 透明信息
   SMaterialAlphaInfo _alpha;
   // 环境光颜色 (RGB=颜色，A=强度)
   SFloatColor4 _ambientColor;
   // 环境光阴影
   TFloat _ambientShadow;
   // 散射光颜色 (RGB=颜色，A=强度)
   SFloatColor4 _diffuseColor;
   // 散射光阴影
   TFloat _diffuseShadow;
   // 散射光相机颜色 (RGB=颜色，A=强度)
   SFloatColor4 _diffuseViewColor;
   // 散射光视角阴影
   TFloat _diffuseViewShadow;
   // 高光颜色 (RGB=颜色，A=强度)
   SFloatColor4 _specularColor;
   // 高光阴影
   TFloat _specularShadow;
   // 高光信息
   SMaterialSpecularInfo _specularInfo;
   // 高光视角颜色 (RGB=颜色，A=强度)
   SFloatColor4 _specularViewColor;
   // 高光视角信息
   SMaterialSpecularInfo _specularViewInfo;
   // 高光视角阴影
   TFloat _specularViewShadow;
   // 反射颜色 (RGB=颜色，A=强度)
   SFloatColor4 _reflectColor;
   //// 反射融合
   //TFloat _reflectMerge;
   //// 反射阴影
   //TFloat _reflectShadow;
   //// 折射正面颜色 (RGB=颜色，A=强度)
   //SFloatColor4 _refractFrontColor;
   //// 折射正面融合
   //TFloat _refractFrontMerge;
   //// 折射正面阴影
   //TFloat _refractFrontShadow;
   //// 折射背面颜色 (RGB=颜色，A=强度)
   //SFloatColor4 _refractBackColor;
   //// 折射背面融合
   //TFloat _refractBackMerge;
   //// 折射背面阴影
   //TFloat _refractBackShadow;
   //// 不透明颜色 (RGB=颜色，A=强度)
   //SFloatColor4 _opacityColor;
   //// 不透明比率
   //TFloat _opacityRate;
   //// 不透明透明
   //TFloat _opacityAlpha;
   //// 不透明深度
   //TFloat _opacityDepth;
   //// 不透明透射
   //TFloat _opacityTransmittance;
   //// 自发光颜色
   //SFloatColor4 _emissiveColor;
   //// 自发光设置
   //SFloatColor4 _emissive;
   //ERenderBlendMode _blendSourceMode;
   //ERenderBlendMode _blendTargetMode;
   //FRenderTextures* _pTextures;
public:
   FMaterial();
   MO_ABSTRACT ~FMaterial();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得渲染效果名称。</T>
   MO_INLINE TCharC* EffectName(){
      return _effectName;
   }
   //------------------------------------------------------------
   // <T>设置渲染效果名称。</T>
   MO_INLINE void SetEffectName(TCharC* pEffectName){
      _effectName = pEffectName;
   }
   //------------------------------------------------------------
   // <T>获得配置合并。</T>
   MO_INLINE TBool OptionMerge(){
      return _optionMerge;
   }
   //------------------------------------------------------------
   // <T>设置配置合并。</T>
   MO_INLINE void SetOptionMerge(TBool optionMerge){
      _optionMerge = optionMerge;
   }
   //------------------------------------------------------------
   // <T>获得配置合并。</T>
   MO_INLINE TBool OptionSort(){
      return _optionSort;
   }
   //------------------------------------------------------------
   // <T>设置配置排序。</T>
   MO_INLINE void SetOptionSort(TBool optionSort){
      _optionSort = optionSort;
   }
   //------------------------------------------------------------
   // <T>获得排序级别。</T>
   MO_INLINE TInt SortLevel(){
      return _sortLevel;
   }
   //------------------------------------------------------------
   // <T>设置排序级别。</T>
   MO_INLINE void SetSortLevel(TInt sortLevel){
      _sortLevel = sortLevel;
   }
   //------------------------------------------------------------
   // <T>获得配置透明。</T>
   MO_INLINE TBool OptionAlpha(){
      return _optionAlpha;
   }
   //------------------------------------------------------------
   // <T>设置配置透明。</T>
   MO_INLINE void SetOptionAlpha(TBool optionAlpha){
      _optionAlpha = optionAlpha;
   }
   //------------------------------------------------------------
   // <T>获得配置深度。</T>
   MO_INLINE TBool OptionDepth(){
      return _optionDepth;
   }
   //------------------------------------------------------------
   // <T>设置配置深度。</T>
   MO_INLINE void SetOptionDepth(TBool optionDepth){
      _optionDepth = optionDepth;
   }
   //------------------------------------------------------------
   // <T>获得比较方式。</T>
   MO_INLINE TCharC* OptionCompare(){
      return _optionCompare;
   }
   //------------------------------------------------------------
   // <T>设置比较方式。</T>
   MO_INLINE void SetOptionCompare(TCharC* pOptionCompare){
      _optionCompare = pOptionCompare;
   }
   //------------------------------------------------------------
   // <T>获得配置双面。</T>
   MO_INLINE TBool OptionDouble(){
      return _optionDouble;
   }
   //------------------------------------------------------------
   // <T>设置配置双面。</T>
   MO_INLINE void SetOptionDouble(TBool _optionDouble){
      _optionDouble = _optionDouble;
   }
   //------------------------------------------------------------
   // <T>获得配置阴影。</T>
   MO_INLINE TBool OptionShadow(){
      return _optionShadow;
   }
   //------------------------------------------------------------
   // <T>设置配置阴影。</T>
   MO_INLINE void SetOptionShadow(TBool _optionShadow){
      _optionShadow = _optionShadow;
   }
   //------------------------------------------------------------
   // <T>获得配置自阴影。</T>
   MO_INLINE TBool OptionShadowSelf(){
      return _optionShadowSelf;
   }
   //------------------------------------------------------------
   // <T>设置配置自阴影。</T>
   MO_INLINE void SetOptionShadowSelf(TBool _optionShadowSelf){
      _optionShadowSelf = _optionShadowSelf;
   }
   ////------------------------------------------------------------
   //// <T>获得混合来源方式。</T>
   //MO_INLINE ERenderBlendMode BlendSourceMode(){
   //   return _blendSourceMode;
   //}
   ////------------------------------------------------------------
   //// <T>设置混合来源方式。</T>
   //MO_INLINE void SetBlendSourceMode(ERenderBlendMode blendSourceMode){
   //   _blendSourceMode = blendSourceMode;
   //}
   ////------------------------------------------------------------
   //// <T>获得混合目标方式。</T>
   //MO_INLINE ERenderBlendMode BlendTargetMode(){
   //   return _blendTargetMode;
   //}
   ////------------------------------------------------------------
   //// <T>设置混合目标方式。</T>
   //MO_INLINE void SetBlendTargetMode(ERenderBlendMode blendTargetMode){
   //   _blendTargetMode = blendTargetMode;
   //}
   ////------------------------------------------------------------
   //// <T>判断是否含有纹理。</T>
   //MO_INLINE TBool HasTexture(){
   //   return (_pTextures != NULL) ? !_pTextures->Textures()->IsEmpty() : EFalse;
   //}
   ////------------------------------------------------------------
   //// <T>获得纹理个数。</T>
   //MO_INLINE TInt TextureCount(){
   //   return (_pTextures != NULL) ? _pTextures->Textures()->Count() : 0;
   //}
   ////------------------------------------------------------------
   //// <T>获得纹理集合。</T>
   //MO_INLINE FRenderTextures* Textures(){
   //   return _pTextures;
   //}
public:
   //------------------------------------------------------------
   // <T>获得颜色信息。</T>
   MO_INLINE SMaterialColorInfo& Color(){
      return _color;
   }
   //------------------------------------------------------------
   // <T>获得透明信息。</T>
   MO_INLINE SMaterialAlphaInfo& Alpha(){
      return _alpha;
   }
   //------------------------------------------------------------
   // <T>获得环境光颜色。</T>
   MO_INLINE SFloatColor4& AmbientColor(){
      return _ambientColor;
   }
   //------------------------------------------------------------
   // <T>获得环境光阴影。</T>
   MO_INLINE TFloat AmbientShadow(){
      return _ambientShadow;
   }
   //------------------------------------------------------------
   // <T>获得散射光颜色。</T>
   MO_INLINE SFloatColor4& DiffuseColor(){
      return _diffuseColor;
   }
   //------------------------------------------------------------
   // <T>获得散射光阴影。</T>
   MO_INLINE TFloat DiffuseShadow(){
      return _diffuseShadow;
   }
   //------------------------------------------------------------
   // <T>获得散射光相机颜色。</T>
   MO_INLINE SFloatColor4& DiffuseViewColor(){
      return _diffuseViewColor;
   }
   //------------------------------------------------------------
   // <T>获得散射光视角阴影。</T>
   MO_INLINE TFloat DiffuseViewShadow(){
      return _diffuseViewShadow;
   }
   //------------------------------------------------------------
   // <T>获得高光颜色。</T>
   MO_INLINE SFloatColor4& SpecularColor(){
      return _specularColor;
   }
   //------------------------------------------------------------
   // <T>获得高光阴影。</T>
   MO_INLINE TFloat SpecularShadow(){
      return _specularShadow;
   }
   //------------------------------------------------------------
   // <T>获得高光信息。</T>
   MO_INLINE SMaterialSpecularInfo& SpecularInfo(){
      return _specularInfo;
   }
   //------------------------------------------------------------
   // <T>获得高光视角颜色。</T>
   MO_INLINE SFloatColor4& SpecularViewColor(){
      return _specularViewColor;
   }
   //------------------------------------------------------------
   // <T>获得高光视角阴影。</T>
   MO_INLINE TFloat SpecularViewShadow(){
      return _specularViewShadow;
   }
   //------------------------------------------------------------
   // <T>获得高光视角信息。</T>
   MO_INLINE SMaterialSpecularInfo& SpecularViewInfo(){
      return _specularViewInfo;
   }
   //------------------------------------------------------------
   // <T>获得反射颜色。</T>
   MO_INLINE SFloatColor4 ReflectColor(){
      return _reflectColor;
   }
public:
   TInt Compare(FMaterial* pMaterial);
public:
   //void TextureClear();
   //void TextureSet(FRenderTexture* pTexture);
   //void TexturePush(FRenderTexture* pTexture);
   //void TextureRemove(FRenderTexture* pTexture);
public:
   MO_ABSTRACT TResult AssignOption(FMaterial* pMaterial);
   MO_ABSTRACT TResult AssignValue(FMaterial* pMaterial);
   MO_ABSTRACT TResult Merge(FMaterial* pMaterial);
   MO_ABSTRACT TResult Reset();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FMaterial*> FMaterialCollection;
typedef MO_FG_DECLARE FDictionary<FMaterial*> FMaterialDictionary;
typedef MO_FG_DECLARE GPtrDictionary<FMaterial> GMaterialPtrDictionary;

//============================================================
// <T>材质控制台。</T>
//============================================================
class MO_FG_DECLARE FMaterialConsole : public FConsole{
protected:
   FMaterialCollection* _pMaterials;
public:
   FMaterialConsole();
   MO_ABSTRACT ~FMaterialConsole();
public:
   //------------------------------------------------------------
   // <T>获得材质集合。</T>
   MO_INLINE FMaterialCollection* Materials(){
      return _pMaterials;
   }
//public:
   //FMaterial* MaterialFind(FBitmap* pBitmap);
   //FMaterial* MaterialSync(FBitmap* pBitmap);
};

//============================================================
// <T>材质管理器。</T>
//============================================================
class MO_FG_DECLARE RMaterialManager : public RSingleton<FMaterialConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_MATERIAL_H__

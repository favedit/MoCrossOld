using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Common;

namespace MO.Content3d.Resource.Material
{
   //============================================================
   // <T>材质定义。</T>
   //============================================================
   public class FDrMaterialInfo : FObject
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrMaterialInfo));

      // 效果名称
      protected string _effectName;

      // 变换名称
      protected string _transformName = "automatic";

      // 设置光源影响
      protected int _optionLight = EDrFlag.Inherit;

      // 设置合并
      protected int _optionMerge = EDrFlag.Inherit;

      // 设置排序
      protected int _optionSort = EDrFlag.Inherit;

      // 设置排序级别
      protected int _sortLevel = 1;

      // 设置透明
      protected int _optionAlpha = EDrFlag.Inherit;

      // 设置是否写深度 (不深度的话，不写影子区)
      protected int _optionDepth = EDrFlag.Inherit;

      // 设置比较
      protected string _optionCompare = "less";

      // 设置双面
      protected int _optionDouble = EDrFlag.Inherit;

      // 设置阴影
      protected int _optionShadow = EDrFlag.Inherit;

      // 设置自阴影
      protected int _optionShadowSelf = EDrFlag.Inherit;

      // 设置动态
      protected int _optionDynamic = EDrFlag.Inherit;

      // 设置透射
      protected int _optionTransmittance = EDrFlag.Inherit;

      // 设置不透明度
      protected int _optionOpacity = EDrFlag.Inherit;

      // 纹理横向比率
      protected float _coordRateWidth;

      // 纹理纵向比率
      protected float _coordRateHeight;

      // 颜色最小
      protected float _colorMin;

      // 颜色最大
      protected float _colorMax;

      // 颜色比率
      protected float _colorRate;

      // 颜色融合
      protected float _colorMerge;

      // 透明基础
      protected float _alphaBase;

      // 透明比率
      protected float _alphaRate;

      // 透明级别
      protected float _alphaLevel;

      // 透明合并
      protected float _alphaMerge;

      // 环境光颜色
      protected SFloatColor4 _ambientColor = new SFloatColor4();

      // 环境光阴影
      protected float _ambientShadow;

      // 散射光颜色
      protected SFloatColor4 _diffuseColor = new SFloatColor4();

      // 散射光阴影
      protected float _diffuseShadow;

      // 视角散射光颜色
      protected SFloatColor4 _diffuseViewColor = new SFloatColor4();

      // 视角散射光阴影
      protected float _diffuseViewShadow;

      // 高光颜色
      protected SFloatColor4 _specularColor = new SFloatColor4();

      // 高光基础
      protected float _specularBase;

      // 高光比率
      protected float _specularRate;

      // 高光平均
      protected float _specularAverage;

      // 高光阴影
      protected float _specularShadow;
      
      // 视角高光颜色
      protected SFloatColor4 _specularViewColor = new SFloatColor4();

      // 视角高光基础
      protected float _specularViewBase;

      // 视角高光比率
      protected float _specularViewRate;

      // 视角高光平均
      protected float _specularViewAverage;

      // 视角高光阴影
      protected float _specularViewShadow;
      
      // 反射颜色
      protected SFloatColor4 _reflectColor = new SFloatColor4();

      // 反射合并
      protected float _reflectMerge;

      // 反射阴影
      protected float _reflectShadow;

      // 前折射颜色
      protected SFloatColor4 _refractFrontColor = new SFloatColor4();

      // 前折射合并
      protected float _refractFrontMerge;

      // 前折射阴影
      protected float _refractFrontShadow;

      // 后折射颜色
      protected SFloatColor4 _refractBackColor = new SFloatColor4();

      // 后折射合并
      protected float _refractBackMerge;

      // 后折射阴影
      protected float _refractBackShadow;

      // 不透明颜色
      protected SFloatColor4 _opacityColorColor = new SFloatColor4();

      // 不透明比率
      protected float _opacityRate;

      // 不透明透明
      protected float _opacityAlpha;

      // 不透明深度
      protected float _opacityDepth;

      // 不透明透射
      protected float _opacityTransmittance;

      // 自发光颜色
      protected SFloatColor4 _emissiveColor = new SFloatColor4();

      //============================================================
      // <T>构造材质定义。</T>
      //============================================================
      public FDrMaterialInfo() {
         Reset();
      }

      //============================================================
      // <T>获得或设置效果名称。</T>
      //============================================================
      public string EffectName {
         get { return _effectName; }
         set { _effectName = value; }
      }

      //============================================================
      // <T>获得或设置变换名称。</T>
      //============================================================
      public string TransformName {
         get { return _transformName; }
         set { _transformName = value; }
      }

      //============================================================
      // <T>获得或设置光源影响。</T>
      //============================================================
      public int OptionLight {
         get { return _optionLight; }
         set { _optionLight = value; }
      }

      //============================================================
      // <T>获得或设置合并。</T>
      //============================================================
      public int OptionMerge {
         get { return _optionMerge; }
         set { _optionMerge = value; }
      }

      //============================================================
      // <T>获得或设置排序。</T>
      //============================================================
      public int OptionSort {
         get { return _optionSort; }
         set { _optionSort = value; }
      }

      //============================================================
      // <T>获得或设置排序级别。</T>
      //============================================================
      public int SortLevel {
         get { return _sortLevel; }
         set { _sortLevel = value; }
      }

      //============================================================
      // <T>获得或设置透明。</T>
      //============================================================
      public int OptionAlpha {
         get { return _optionAlpha; }
         set { _optionAlpha = value; }
      }

      //============================================================
      // <T>获得或设置深度。</T>
      //============================================================
      public int OptionDepth {
         get { return _optionDepth; }
         set { _optionDepth = value; }
      }

      //============================================================
      // <T>获得或设置比较方式。</T>
      //============================================================
      public string OptionCompare {
         get { return _optionCompare; }
         set { _optionCompare = value; }
      }

      //============================================================
      // <T>获得或设置双面。</T>
      //============================================================
      public int OptionDouble {
         get { return _optionDouble; }
         set { _optionDouble = value; }
      }

      //============================================================
      // <T>获得或设置阴影。</T>
      //============================================================
      public int OptionShadow {
         get { return _optionShadow; }
         set { _optionShadow = value; }
      }

      //============================================================
      // <T>获得或设置自阴影。</T>
      //============================================================
      public int OptionShadowSelf {
         get { return _optionShadowSelf; }
         set { _optionShadowSelf = value; }
      }

      //============================================================
      // <T>获得或设置动态。</T>
      //============================================================
      public int OptionDynamic {
         get { return _optionDynamic; }
         set { _optionDynamic = value; }
      }

      //============================================================
      // <T>获得或设置透射。</T>
      //============================================================
      public int OptionTransmittance {
         get { return _optionTransmittance; }
         set { _optionTransmittance = value; }
      }

      //============================================================
      // <T>获得或设置不透明。</T>
      //============================================================
      public int OptionOpacity {
         get { return _optionOpacity; }
         set { _optionOpacity = value; }
      }

      //============================================================
      // <T>获得或设置纹理横向比率。</T>
      //============================================================
      public float CoordRateWidth {
         get { return _coordRateWidth; }
         set { _coordRateWidth = value; }
      }

      //============================================================
      // <T>获得或设置纹理纵向比率。</T>
      //============================================================
      public float CoordRateHeight {
         get { return _coordRateHeight; }
         set { _coordRateHeight = value; }
      }

      //============================================================
      // <T>获得或设置最小颜色。</T>
      //============================================================
      public float ColorMin {
         get { return _colorMin; }
         set { _colorMin = value; }
      }

      //============================================================
      // <T>获得或设置最大颜色。</T>
      //============================================================
      public float ColorMax {
         get { return _colorMax; }
         set { _colorMax = value; }
      }

      //============================================================
      // <T>获得或设置颜色比率。</T>
      //============================================================
      public float ColorRate {
         get { return _colorRate; }
         set { _colorRate = value; }
      }

      //============================================================
      // <T>获得或设置颜色融合。</T>
      //============================================================
      public float ColorMerge {
         get { return _colorMerge; }
         set { _colorMerge = value; }
      }
      
      //============================================================
      // <T>获得或设置透明基础。</T>
      //============================================================
      public float AlphaBase {
         get { return _alphaBase; }
         set { _alphaBase = value; }
      }

      //============================================================
      // <T>获得或设置透明比率。</T>
      //============================================================
      public float AlphaRate {
         get { return _alphaRate; }
         set { _alphaRate = value; }
      }

      //============================================================
      // <T>获得或设置透明级别。</T>
      //============================================================
      public float AlphaLevel {
         get { return _alphaLevel; }
         set { _alphaLevel = value; }
      }

      //============================================================
      // <T>获得或设置透明合并。</T>
      //============================================================
      public float AlphaMerge {
         get { return _alphaMerge; }
         set { _alphaMerge = value; }
      }

      //============================================================
      // <T>获得环境颜色。</T>
      //============================================================
      public SFloatColor4 AmbientColor {
         get { return _ambientColor; }
      }

      //============================================================
      // <T>获得或设置环境光阴影。</T>
      //============================================================
      public float AmbientShadow {
         get { return _ambientShadow; }
         set { _ambientShadow = value; }
      }

      //============================================================
      // <T>获得散射颜色。</T>
      //============================================================
      public SFloatColor4 DiffuseColor {
         get { return _diffuseColor; }
      }

      //============================================================
      // <T>获得或设置散射光阴影。</T>
      //============================================================
      public float DiffuseShadow {
         get { return _diffuseShadow; }
         set { _diffuseShadow = value; }
      }

      //============================================================
      // <T>获得视角散射光颜色。</T>
      //============================================================
      public SFloatColor4 DiffuseViewColor {
         get { return _diffuseViewColor; }
      }

      //============================================================
      // <T>获得或设置视角散射光阴影。</T>
      //============================================================
      public float DiffuseViewShadow {
         get { return _diffuseViewShadow; }
         set { _diffuseViewShadow = value; }
      }

      //============================================================
      // <T>获得高光颜色。</T>
      //============================================================
      public SFloatColor4 SpecularColor {
         get { return _specularColor; }
      }

      //============================================================
      // <T>获得或设置高光基础。</T>
      //============================================================
      public float SpecularBase {
         get { return _specularBase; }
         set { _specularBase = value; }
      }

      //============================================================
      // <T>获得或设置高光比率。</T>
      //============================================================
      public float SpecularRate {
         get { return _specularRate; }
         set { _specularRate = value; }
      }

      //============================================================
      // <T>获得或设置高光平均。</T>
      //============================================================
      public float SpecularAverage {
         get { return _specularAverage; }
         set { _specularAverage = value; }
      }

      //============================================================
      // <T>获得或设置高光阴影。</T>
      //============================================================
      public float SpecularShadow {
         get { return _specularShadow; }
         set { _specularShadow = value; }
      }

      //============================================================
      // <T>获得视角高光颜色。</T>
      //============================================================
      public SFloatColor4 SpecularViewColor {
         get { return _specularViewColor; }
      }

      //============================================================
      // <T>获得或设置视角高光基础。</T>
      //============================================================
      public float SpecularViewBase {
         get { return _specularViewBase; }
         set { _specularViewBase = value; }
      }

      //============================================================
      // <T>获得或设置视角高光比率。</T>
      //============================================================
      public float SpecularViewRate {
         get { return _specularViewRate; }
         set { _specularViewRate = value; }
      }

      //============================================================
      // <T>获得或设置视角高光平均。</T>
      //============================================================
      public float SpecularViewAverage {
         get { return _specularViewAverage; }
         set { _specularViewAverage = value; }
      }

      //============================================================
      // <T>获得或设置视角高光阴影。</T>
      //============================================================
      public float SpecularViewShadow {
         get { return _specularViewShadow; }
         set { _specularViewShadow = value; }
      }

      //============================================================
      // <T>获得反射颜色。</T>
      //============================================================
      public SFloatColor4 ReflectColor {
         get { return _reflectColor; }
      }

      //============================================================
      // <T>获得或设置反射合并。</T>
      //============================================================
      public float ReflectMerge {
         get { return _reflectMerge; }
         set { _reflectMerge = value; }
      }

      //============================================================
      // <T>获得或设置反射阴影。</T>
      //============================================================
      public float ReflectShadow {
         get { return _reflectShadow; }
         set { _reflectShadow = value; }
      }

      //============================================================
      // <T>获得前折射颜色。</T>
      //============================================================
      public SFloatColor4 RefractFrontColor {
         get { return _refractFrontColor; }
      }

      //============================================================
      // <T>获得或设置前折射合并。</T>
      //============================================================
      public float RefractFrontMerge {
         get { return _refractFrontMerge; }
         set { _refractFrontMerge = value; }
      }

      //============================================================
      // <T>获得或设置前折射阴影。</T>
      //============================================================
      public float RefractFrontShadow {
         get { return _refractFrontShadow; }
         set { _refractFrontShadow = value; }
      }

      //============================================================
      // <T>获得后折射颜色。</T>
      //============================================================
      public SFloatColor4 RefractBackColor {
         get { return _refractBackColor; }
      }

      //============================================================
      // <T>获得或设置后折射合并。</T>
      //============================================================
      public float RefractBackMerge {
         get { return _refractBackMerge; }
         set { _refractBackMerge = value; }
      }

      //============================================================
      // <T>获得或设置后折射阴影。</T>
      //============================================================
      public float RefractBackShadow {
         get { return _refractBackShadow; }
         set { _refractBackShadow = value; }
      }

      //============================================================
      // <T>获得不透明颜色。</T>
      //============================================================
      public SFloatColor4 OpacityColor {
         get { return _opacityColorColor; }
      }

      //============================================================
      // <T>获得或设置不透明比率。</T>
      //============================================================
      public float OpacityRate {
         get { return _opacityRate; }
         set { _opacityRate = value; }
      }

      //============================================================
      // <T>获得或设置不透明透明。</T>
      //============================================================
      public float OpacityAlpha {
         get { return _opacityAlpha; }
         set { _opacityAlpha = value; }
      }

      //============================================================
      // <T>获得或设置不透明深度。</T>
      //============================================================
      public float OpacityDepth {
         get { return _opacityDepth; }
         set { _opacityDepth = value; }
      }

      //============================================================
      // <T>获得或设置不透明透射。</T>
      //============================================================
      public float OpacityTransmittance {
         get { return _opacityTransmittance; }
         set { _opacityTransmittance = value; }
      }
      
      //============================================================
      // <T>获得发光颜色</T>
      //============================================================
      public SFloatColor4 EmissiveColor {
         get { return _emissiveColor; }
      }

      //============================================================
      // <T>接收材质内容。</T>
      //
      // @param material 材质信息
      //============================================================
      public void AssignInfo(FDrMaterialInfo material) {
         // 存储属性
         _effectName = material.EffectName;
         _transformName = material.TransformName;
         // 存储设置
         _optionLight = material.OptionLight;
         _optionMerge = material.OptionMerge;
         _optionDepth = material.OptionDepth;
         _optionCompare = material.OptionCompare;
         _optionAlpha = material.OptionAlpha;
         _optionDouble = material.OptionDouble;
         _optionOpacity = material.OptionOpacity;
         _optionShadow = material.OptionShadow;
         _optionShadowSelf = material.OptionShadowSelf;
         _optionTransmittance = material.OptionTransmittance;
         // 存储信息
         _sortLevel = material.SortLevel;
         // 存储颜色
         _colorMin = material.ColorMin;
         _colorMax = material.ColorMax;
         _colorRate = material.ColorRate;
         _colorMerge = material.ColorMerge;
         // 存储透明
         _alphaBase = material.AlphaBase;
         _alphaRate = material.AlphaRate;
         _alphaLevel = material.AlphaLevel;
         _alphaMerge = material.AlphaMerge;
         // 存储属性
         _ambientColor.Assign(material.AmbientColor);
         _ambientShadow = material.AmbientShadow;
         _diffuseColor.Assign(material.DiffuseColor);
         _diffuseShadow = material.DiffuseShadow;
         _diffuseViewColor.Assign(material.DiffuseViewColor);
         _diffuseViewShadow = material.DiffuseViewShadow;
         _specularColor.Assign(material.SpecularColor);
         _specularBase = material.SpecularBase;
         _specularRate = material.SpecularRate;
         _specularAverage = material.SpecularAverage;
         _specularShadow = material.SpecularShadow;
         _specularViewColor.Assign(material.SpecularViewColor);
         _specularViewBase = material.SpecularViewBase;
         _specularViewRate = material.SpecularViewRate;
         _specularViewAverage = material.SpecularViewAverage;
         _specularViewShadow = material.SpecularViewShadow;
         _reflectColor.Assign(material.ReflectColor);
         _reflectMerge = material.ReflectMerge;
         _reflectShadow = material.ReflectShadow;
         _refractFrontColor.Assign(material.RefractFrontColor);
         _refractFrontMerge = material.RefractFrontMerge;
         _refractFrontShadow = material.RefractFrontShadow;
         _refractBackColor.Assign(material.RefractBackColor);
         _refractBackMerge = material.RefractBackMerge;
         _refractBackShadow = material.RefractBackShadow;
         _opacityColorColor.Assign(material.OpacityColor);
         _opacityRate = material.OpacityRate;
         _opacityAlpha = material.OptionAlpha;
         _opacityDepth = material.OptionDepth;
         _opacityTransmittance = material.OptionTransmittance;
         _emissiveColor.Assign(material.EmissiveColor);
      }
      
      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfigInfo(FXmlNode xconfig) {
         // 读取属性
         _effectName = xconfig.Nvl("effect_name");
         _transformName = xconfig.Nvl("transform_name");
         _optionLight = xconfig.GetInteger("option_light", _optionLight);
         _optionMerge = xconfig.GetInteger("option_merge", _optionMerge);
         _optionSort = xconfig.GetInteger("option_sort", _optionSort);
         _sortLevel = xconfig.GetInteger("sort_level", _sortLevel);
         _optionAlpha = xconfig.GetInteger("option_alpha", _optionAlpha);
         _optionDepth = xconfig.GetInteger("option_depth", _optionDepth);
         _optionCompare = xconfig.Get("option_compare", _optionCompare);
         _optionDouble = xconfig.GetInteger("option_double", _optionDouble);
         _optionShadow = xconfig.GetInteger("option_shadow", _optionShadow);
         _optionShadowSelf = xconfig.GetInteger("option_shadow_self", _optionShadowSelf);
         _optionDynamic = xconfig.GetInteger("option_dynamic", _optionDynamic);
         _optionTransmittance = xconfig.GetInteger("option_transmittance", _optionTransmittance);
         _optionOpacity = xconfig.GetInteger("option_opacity", _optionOpacity);
         // 读取纹理
         FXmlNode xcoord = xconfig.Find("Coord");
         if (null != xcoord) {
            _coordRateWidth = xcoord.GetFloat("rate_width", _coordRateWidth);
            _coordRateHeight = xcoord.GetFloat("rate_height", _coordRateHeight);
         }
         // 读取颜色
         FXmlNode xcolor = xconfig.Find("Color");
         if (null != xcolor) {
            _colorMin = xcolor.GetFloat("min", _colorMin);
            _colorMax = xcolor.GetFloat("max", _colorMax);
            _colorRate = xcolor.GetFloat("rate", _colorRate);
            _colorMerge = xcolor.GetFloat("merge", _colorMerge);
         }
         // 读取透明信息
         FXmlNode xalpha = xconfig.Find("Alpha");
         if (null != xalpha) {
            _alphaBase = xalpha.GetFloat("base", _alphaBase);
            _alphaRate = xalpha.GetFloat("rate", _alphaRate);
            _alphaLevel = xalpha.GetFloat("level", _alphaLevel);
            _alphaMerge = xalpha.GetFloat("merge", _alphaMerge);
         }
         // 读取环境光
         FXmlNode xambient = xconfig.Find("Ambient");
         if (null != xambient) {
            _ambientColor.LoadConfigPower(xambient);
            _ambientShadow = xambient.GetFloat("shadow", _ambientShadow);
         }
         // 读取散射光
         FXmlNode xdiffuse = xconfig.Find("Diffuse");
         if (null != xdiffuse) {
            _diffuseColor.LoadConfigPower(xdiffuse);
            _diffuseShadow = xdiffuse.GetFloat("shadow", _diffuseShadow);
         }
         // 读取视角散射光
         FXmlNode xdiffuseview = xconfig.Find("DiffuseView");
         if(null != xdiffuseview) {
            _diffuseViewColor.LoadConfigPower(xdiffuseview);
            _diffuseViewShadow = xdiffuseview.GetFloat("shadow", _diffuseViewShadow);
         }
         // 读取高光
         FXmlNode xspecular = xconfig.Find("Specular");
         if (null != xspecular) {
            _specularColor.LoadConfigPower(xspecular);
            _specularBase = xspecular.GetFloat("base", _specularBase);
            _specularRate = xspecular.GetFloat("rate", _specularRate);
            _specularAverage = xspecular.GetFloat("average", _specularAverage);
            _specularShadow = xspecular.GetFloat("shadow", _specularShadow);
         }
         // 读取视角高光
         FXmlNode xspecularview = xconfig.Find("SpecularView");
         if (null != xspecularview) {
            _specularViewColor.LoadConfigPower(xspecularview);
            _specularViewBase = xspecularview.GetFloat("base", _specularViewBase);
            _specularViewRate = xspecularview.GetFloat("rate", _specularViewRate);
            _specularViewAverage = xspecularview.GetFloat("average", _specularViewAverage);
            _specularViewShadow = xspecularview.GetFloat("shadow", _specularViewShadow);
         }
         // 读取反射光
         FXmlNode xreflect = xconfig.Find("Reflect");
         if (null != xreflect) {
            _reflectColor.LoadConfigPower(xreflect);
            _reflectMerge = xreflect.GetFloat("merge", _reflectMerge);
            _reflectShadow = xreflect.GetFloat("shadow", _reflectShadow);
         }
         // 读取前折射光
         FXmlNode xrefractFront = xconfig.Find("RefractFront");
         if (null != xrefractFront) {
            _refractFrontColor.LoadConfigPower(xrefractFront);
         }
         // 读取后折射光
         FXmlNode xrefractBack = xconfig.Find("RefractBack");
         if (null != xrefractBack) {
            _refractBackColor.LoadConfigPower(xrefractBack);
         }
         // 读取不透明度
         FXmlNode xopacity = xconfig.Find("Opacity");
         if (null != xopacity) {
            _opacityColorColor.LoadConfigPower(xopacity);
            _opacityRate = xopacity.GetFloat("rate", _opacityRate);
            _opacityAlpha = xopacity.GetFloat("alpha", _opacityAlpha);
            _opacityDepth = xopacity.GetFloat("depth", _opacityDepth);
            _opacityTransmittance = xopacity.GetFloat("transmittance", _opacityTransmittance);
         }
         // 读取自发光
         FXmlNode xemissive = xconfig.Find("Emissive");
         if (null != xemissive) {
            _emissiveColor.LoadConfigPower(xemissive);
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfigInfo(FXmlNode xconfig) {
         // 存储属性
         xconfig.Set("effect_name", _effectName);
         xconfig.Set("transform_name", _transformName);
         xconfig.Set("option_light", _optionLight);
         xconfig.Set("option_merge", _optionMerge);
         xconfig.Set("option_sort", _optionSort);
         xconfig.Set("sort_level", _sortLevel);
         xconfig.Set("option_alpha", _optionAlpha);
         xconfig.Set("option_depth", _optionDepth);
         xconfig.Set("option_compare", _optionCompare);
         xconfig.Set("option_double", _optionDouble);
         xconfig.Set("option_shadow", _optionShadow);
         xconfig.Set("option_shadow_self", _optionShadowSelf);
         xconfig.Set("option_dynamic", _optionDynamic);
         xconfig.Set("option_transmittance", _optionTransmittance);
         xconfig.Set("option_opacity", _optionOpacity);
         // 存储纹理
         FXmlNode xcoord = xconfig.CreateNode("Coord");
         xcoord.Set("rate_width", _coordRateWidth);
         xcoord.Set("rate_height", _coordRateHeight);
         // 存储颜色
         FXmlNode xcolor = xconfig.CreateNode("Color");
         xcolor.Set("min", _colorMin);
         xcolor.Set("max", _colorMax);
         xcolor.Set("rate", _colorRate);
         xcolor.Set("merge", _colorMerge);
         // 读取透明信息
         FXmlNode xalpha = xconfig.CreateNode("Alpha");
         xalpha.Set("base", _alphaBase);
         xalpha.Set("rate", _alphaRate);
         xalpha.Set("level", _alphaLevel);
         xalpha.Set("merge", _alphaMerge);
         // 存储环境光
         FXmlNode xambient = xconfig.CreateNode("Ambient");
         _ambientColor.SaveConfigPower(xambient);
         xambient.Set("shadow", _ambientShadow);
         // 存储散射光
         FXmlNode xdiffuse = xconfig.CreateNode("Diffuse");
         _diffuseColor.SaveConfigPower(xdiffuse);
         xdiffuse.Set("shadow", _diffuseShadow);
         // 存储视角散射光
         FXmlNode xdiffuseview = xconfig.CreateNode("DiffuseView");
         _diffuseViewColor.SaveConfigPower(xdiffuseview);
         xdiffuseview.Set("shadow", _diffuseViewShadow);
         // 存储高光
         FXmlNode xspecular = xconfig.CreateNode("Specular");
         _specularColor.SaveConfigPower(xspecular);
         xspecular.Set("base", _specularBase);
         xspecular.Set("rate", _specularRate);
         xspecular.Set("average", _specularAverage);
         xspecular.Set("shadow", _specularShadow); 
         // 存储视角高光
         FXmlNode xspecularview = xconfig.CreateNode("SpecularView");
         _specularViewColor.SaveConfigPower(xspecularview);
         xspecularview.Set("base", _specularViewBase);
         xspecularview.Set("rate", _specularViewRate);
         xspecularview.Set("average", _specularViewAverage);
         xspecularview.Set("shadow", _specularViewShadow);
         // 存储反射
         FXmlNode xreflect = xconfig.CreateNode("Reflect");
         _reflectColor.SaveConfigPower(xreflect);
         xreflect.Set("merge", _reflectMerge);
         xreflect.Set("shadow", _reflectShadow);
         // 存储前折射
         FXmlNode xrefractFront = xconfig.CreateNode("RefractFront");
         _refractFrontColor.SaveConfigPower(xrefractFront);
         // 存储后折射
         FXmlNode xrefractBack = xconfig.CreateNode("RefractBack");
         _refractBackColor.SaveConfigPower(xrefractBack);
         // 存储不发光度
         FXmlNode xopacity = xconfig.CreateNode("Opacity");
         _opacityColorColor.SaveConfigPower(xopacity);
         xopacity.Set("rate", _opacityRate);
         xopacity.Set("alpha", _opacityAlpha);
         xopacity.Set("depth", _opacityDepth);
         xopacity.Set("transmittance", _opacityTransmittance);
         // 存储自发光
         FXmlNode xemissive = xconfig.CreateNode("Emissive");
         _emissiveColor.SaveConfigPower(xemissive);
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void SerializeInfo(IOutput output) {
         // 修正数据
         if(RString.IsBlank(_effectName)) {
            _effectName = "automatic";
         }else if(_effectName == "skeleton.4") {
            _effectName = "skeleton";
         }
         // 存储属性
         output.WriteString(_effectName);
         output.WriteString(_transformName);
         // 存储设置
         output.WriteInt8((sbyte)_optionLight);
         output.WriteInt8((sbyte)_optionMerge);
         output.WriteInt8((sbyte)_optionSort);
         output.WriteInt32(_sortLevel);
         output.WriteInt8((sbyte)_optionAlpha);
         output.WriteInt8((sbyte)_optionDepth);
         output.WriteString(_optionCompare);
         output.WriteInt8((sbyte)_optionDouble);
         output.WriteInt8((sbyte)_optionShadow);
         output.WriteInt8((sbyte)_optionShadowSelf);
         output.WriteInt8((sbyte)_optionDynamic);
         output.WriteInt8((sbyte)_optionTransmittance);
         output.WriteInt8((sbyte)_optionOpacity);
         // 存储纹理
         output.WriteFloat(_coordRateWidth);
         output.WriteFloat(_coordRateHeight);
         // 存储颜色
         output.WriteFloat(_colorMin);
         output.WriteFloat(_colorMax);
         output.WriteFloat(_colorRate);
         output.WriteFloat(_colorMerge);
         // 存储透明
         output.WriteFloat(_alphaBase);
         output.WriteFloat(_alphaRate);
         output.WriteFloat(_alphaLevel);
         output.WriteFloat(_alphaMerge);
         // 存储属性
         _ambientColor.Serialize(output);
         output.WriteFloat(_ambientShadow);
         _diffuseColor.Serialize(output);
         output.WriteFloat(_diffuseShadow);
         _diffuseViewColor.Serialize(output);
         output.WriteFloat(_diffuseViewShadow);
         _specularColor.Serialize(output);
         output.WriteFloat(_specularBase);
         output.WriteFloat(_specularRate);
         output.WriteFloat(_specularAverage);
         output.WriteFloat(_specularShadow);
         _specularViewColor.Serialize(output);
         output.WriteFloat(_specularViewBase);
         output.WriteFloat(_specularViewRate);
         output.WriteFloat(_specularViewAverage);
         output.WriteFloat(_specularViewShadow);
         // 存储反射
         _reflectColor.Serialize(output);
         output.WriteFloat(_reflectMerge);
         output.WriteFloat(_reflectShadow);
         // 存储折射
         _refractFrontColor.Serialize(output);
         _refractBackColor.Serialize(output);
         // 存储不透明度
         _opacityColorColor.Serialize(output);
         output.WriteFloat(_opacityRate);
         output.WriteFloat(_opacityAlpha);
         output.WriteFloat(_opacityDepth);
         output.WriteFloat(_opacityTransmittance);
         // 存储自发光
         _emissiveColor.Serialize(output);
      }

      //============================================================
      // <T>重置内容。</T>
      //============================================================
      public virtual void Reset() {
         _coordRateWidth = 1.0f;
         _coordRateHeight = 1.0f;
         _colorMin = 0.0f;
         _colorMax = 1.0f;
         _colorRate = 1.0f;
         _colorMerge = 1.0f;
         _alphaBase = 1.0f;
         _alphaRate = 1.0f;
         _alphaLevel = 1.0f;
         _alphaMerge = 1.0f;
         _ambientColor.Set(1.0f, 1.0f, 1.0f, 1.0f);
         _ambientShadow = 1.0f;
         _diffuseColor.Set(1.0f, 1.0f, 1.0f, 1.0f);
         _diffuseShadow = 1.0f;
         _diffuseViewColor.Set(1.0f, 1.0f, 1.0f, 1.0f);
         _diffuseViewShadow = 1.0f;
         _specularColor.Set(1.0f, 1.0f, 1.0f, 1.0f);
         _specularBase = 1.0f;
         _specularRate = 1.0f;
         _specularAverage = 1.0f;
         _specularShadow = 1.0f;
         _specularViewColor.Set(1.0f, 1.0f, 1.0f, 1.0f);
         _specularViewBase = 1.0f;
         _specularViewRate = 1.0f;
         _specularViewAverage = 1.0f;
         _specularViewShadow = 1.0f;
         _reflectColor.Set(1.0f, 1.0f, 1.0f, 1.0f);
         _reflectMerge = 1.0f;
         _reflectShadow = 1.0f;
         _refractFrontColor.Set(1.0f, 1.0f, 1.0f, 1.0f);
         _refractFrontMerge = 1.0f;
         _refractFrontShadow = 1.0f;
         _refractBackColor.Set(1.0f, 1.0f, 1.0f, 1.0f);
         _refractBackMerge = 1.0f;
         _refractBackShadow = 1.0f;
         _opacityColorColor.Set(1.0f, 1.0f, 1.0f, 1.0f);
         _opacityRate = 1.0f;
         _opacityAlpha = 1.0f;
         _opacityDepth = 1.0f;
         _opacityTransmittance = 1.0f;
         _emissiveColor.Set(1.0f, 1.0f, 1.0f, 1.0f);
      }
   }
}

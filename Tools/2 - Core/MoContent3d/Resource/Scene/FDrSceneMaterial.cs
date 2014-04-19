using System;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core;
using MO.Content3d.Common;
using MO.Content3d.Resource.Material;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>材质定义。</T>
   //============================================================
   public class FDrSceneMaterial : FDrMaterialInfo
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrSceneMaterial));

      // 场景
      protected FDrScene _scene;

      // 名称
      protected string _name;

      // 标签
      protected string _label;

      // 材质
      protected FDrMaterial _material;

      // 高度深度
      protected float _heightDepth;

      // 表面比率
      protected float _surfaceRate;

      // 表面反射
      protected float _surfaceReflect;

      // 表面亮度
      protected float _surfaceBright;

      // 表面亮度级别
      protected float _surfaceBrightLevel;

      // 表面粗糙
      protected float _surfaceCoarse;

      // 表面粗糙级别
      protected float _surfaceCoarseLevel;

      // 表面融合
      protected float _surfaceMerge;

      // 表面强度
      protected float _surfacePower;

      //============================================================
      // <T>构造材质定义。</T>
      //============================================================
      public FDrSceneMaterial() {
         Reset();
      }

      //============================================================
      // <T>获得或设置场景。</T>
      //============================================================
      public FDrScene Scene {
         get { return _scene; }
         set { _scene = value; }
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Code {
         get { return RDrUtil.FormatPathToCode(_name); }
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置标签。</T>
      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      // <T>获得或设置材质。</T>
      //============================================================
      public FDrMaterial Material {
         get { return _material; }
         set { _material = value; }
      }

      //============================================================
      // <T>获得或设置高度深度。</T>
      //============================================================
      public float HeightDepth {
         get { return _heightDepth; }
         set { _heightDepth = value; }
      }
      
      //============================================================
      // <T>获得或设置表面比率。</T>
      //============================================================
      public float SurfaceRate {
         get { return _surfaceRate; }
         set { _surfaceRate = value; }
      }

      //============================================================
      // <T>获得或设置表面反射。</T>
      //============================================================
      public float SurfaceReflect {
         get { return _surfaceReflect; }
         set { _surfaceReflect = value; }
      }

      //============================================================
      // <T>获得或设置表面亮度。</T>
      //============================================================
      public float SurfaceBright {
         get { return _surfaceBright; }
         set { _surfaceBright = value; }
      }

      //============================================================
      // <T>获得或设置表面亮度级别。</T>
      //============================================================
      public float SurfaceBrightLevel {
         get { return _surfaceBrightLevel; }
         set { _surfaceBrightLevel = value; }
      }

      //============================================================
      // <T>获得或设置表面粗糙。</T>
      //============================================================
      public float SurfaceCoarse {
         get { return _surfaceCoarse; }
         set { _surfaceCoarse = value; }
      }

      //============================================================
      // <T>获得或设置表面粗糙级别。</T>
      //============================================================
      public float SurfaceCoarseLevel {
         get { return _surfaceCoarseLevel; }
         set { _surfaceCoarseLevel = value; }
      }

      //============================================================
      // <T>获得或设置表面融合。</T>
      //============================================================
      public float SurfaceMerge {
         get { return _surfaceMerge; }
         set { _surfaceMerge = value; }
      }

      //============================================================
      // <T>获得或设置表面强度。</T>
      //============================================================
      public float SurfacePower {
         get { return _surfacePower; }
         set { _surfacePower = value; }
      }
      
      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
         // 读取属性
         _name = xconfig.Nvl("name");
         _effectName = xconfig.Nvl("effect_name");
         if (xconfig.Contains("option_double")) {
            _optionDouble = EDrFlag.FromBoolean(xconfig.GetBoolean("option_double"));
         }
         if (xconfig.Contains("option_opacity")) {
            _optionOpacity = EDrFlag.FromBoolean(xconfig.GetBoolean("option_opacity"));
         }
         if (xconfig.Contains("option_shadow")) {
            _optionShadow = EDrFlag.FromBoolean(xconfig.GetBoolean("option_shadow"));
         }
         if (xconfig.Contains("option_transmittance")) {
            _optionTransmittance = EDrFlag.FromBoolean(xconfig.GetBoolean("option_transmittance"));
         }
         // 读取颜色
         FXmlNode xcolor = xconfig.Find("Color");
         if (null != xcolor) {
            _colorMin = xcolor.GetFloat("min");
            _colorMax = xcolor.GetFloat("max");
            if (xcolor.Contains("merge")) {
               _colorMerge = xcolor.GetFloat("merge");
            }
            if (xcolor.Contains("rate")) {
               _colorRate = xcolor.GetFloat("rate");
            }
         }
         // 读取环境光
         FXmlNode xalpha = xconfig.Find("Alpha");
         if (null != xalpha) {
            _optionAlpha = EDrFlag.FromBoolean(xalpha.GetBoolean("enable"));
            _alphaBase = xalpha.GetFloat("base");
            _alphaRate = xalpha.GetFloat("rate");
            _alphaLevel = xalpha.GetFloat("level");
            _alphaMerge = xalpha.GetFloat("merge");
         }
         // 读取环境光
         FXmlNode xambient = xconfig.Find("Ambient");
         if (null != xambient) {
            _ambientColor.LoadConfig(xambient, "r", "g", "b", "power");
            _ambientShadow = xambient.GetFloat("shadow", _ambientShadow);
         }
         // 读取散射光
         FXmlNode xdiffuse = xconfig.Find("Diffuse");
         if (null != xdiffuse) {
            _diffuseColor.LoadConfig(xdiffuse, "r", "g", "b", "power");
            _diffuseShadow = xdiffuse.GetFloat("shadow", _diffuseShadow);
            _diffuseViewColor.LoadConfig(xdiffuse, "camera_r", "camera_g", "camera_b", "camera_power");
            _diffuseViewShadow = xdiffuse.GetFloat("camera_shadow", _diffuseViewShadow);
         }
         // 读取反射光
         FXmlNode xspecular = xconfig.Find("Specular");
         if (null != xspecular) {
            _specularColor.LoadConfig(xspecular, "r", "g", "b", "power");
            _specularBase = xspecular.GetFloat("base", _specularBase);
            _specularRate = xspecular.GetFloat("rate", _specularRate);
            _specularAverage = xspecular.GetFloat("average", _specularAverage);
            _specularShadow = xspecular.GetFloat("shadow", _specularShadow);
            _specularViewColor.LoadConfig(xspecular, "camera_r", "camera_g", "camera_b", "camera_power");
            _specularViewBase = xspecular.GetFloat("camera_base", _specularViewBase);
            _specularViewRate = xspecular.GetFloat("camera_rate", _specularViewRate);
            _specularViewAverage = xspecular.GetFloat("camera_average", _specularViewAverage);
            _specularViewShadow = xspecular.GetFloat("camera_shadow", _specularViewShadow);
         }
         // 读取自发光
         FXmlNode xreflect = xconfig.Find("Reflect");
         if (null != xreflect) {
            _reflectColor.LoadConfig(xreflect, "r", "g", "b", "power");
            _reflectMerge = xreflect.GetFloat("merge", _reflectMerge);
            _reflectShadow = xreflect.GetFloat("shadow", _reflectShadow);
         }
         // 读取折射
         FXmlNode xrefract = xconfig.Find("Refract");
         if (null != xrefract) {
            _refractFrontColor.LoadConfig(xrefract, "front_r", "front_g", "front_b", "front_power");
            _refractFrontMerge = xrefract.GetFloat("front_merge", _refractFrontMerge);
            _refractFrontShadow = xrefract.GetFloat("front_shadow", _refractFrontShadow);
            _refractBackColor.LoadConfig(xrefract, "back_r", "back_g", "back_b", "back_power");
            _refractBackMerge = xrefract.GetFloat("back_merge", _refractBackMerge);
            _refractBackShadow = xrefract.GetFloat("back_shadow", _refractBackShadow);
         }
         // 读取不透明度
         FXmlNode xopacity = xconfig.Find("Opacity");
         if (null != xopacity) {
            _optionOpacity = EDrFlag.FromBoolean(xopacity.GetBoolean("enable"));
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
         // 读取高度
         FXmlNode xheight = xconfig.Find("Height");
         if (null != xheight) {
            //_optionHeight = EDrFlag.FromBoolean(xheight.GetBoolean("enable"));
            _heightDepth = xheight.GetFloat("depth", _heightDepth);
         }
         // 读取表面
         FXmlNode xsurface = xconfig.Find("Surface");
         if (null != xsurface) {
            _surfaceRate = xsurface.GetFloat("rate", _surfaceRate);
            _surfaceReflect = xsurface.GetFloat("reflect", _surfaceReflect);
            _surfaceBright = xsurface.GetFloat("bright", _surfaceBright);
            _surfaceBrightLevel = xsurface.GetFloat("bright_level", _surfaceBrightLevel);
            _surfaceCoarse = xsurface.GetFloat("coarse", _surfaceCoarse);
            _surfaceCoarseLevel = xsurface.GetFloat("coarse_level", _surfaceCoarseLevel);
            _surfaceMerge = xsurface.GetFloat("merge", _surfaceMerge);
            _surfacePower = xsurface.GetFloat("power", _surfacePower);
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 读取属性
         _name = xconfig.Nvl("name");
         _label = String.Empty;
         // 读取设置
         LoadConfigInfo(xconfig);
         // 读取高度
         FXmlNode xheight = xconfig.Find("Height");
         if (null != xheight) {
            //_optionHeight = xconfig.GetInteger("option_height", _optionHeight);
            _heightDepth = xheight.GetFloat("depth");
         }
         // 读取表面
         FXmlNode xsurface = xconfig.Find("Surface");
         if (null != xsurface) {
            _surfaceRate = xsurface.GetFloat("rate");
            _surfaceReflect = xsurface.GetFloat("reflect");
            _surfaceBright = xsurface.GetFloat("bright");
            _surfaceBrightLevel = xsurface.GetFloat("bright_level");
            _surfaceCoarse = xsurface.GetFloat("coarse");
            _surfaceCoarseLevel = xsurface.GetFloat("coarse_level");
            _surfaceMerge = xsurface.GetFloat("merge");
            _surfacePower = xsurface.GetFloat("power");
         }
         if (!RString.IsEmpty(_name)) {
            _material = RContent3dManager.MaterialConsole.FindDefault(_scene.ThemeName, _name);
            if (null == _material) {
               RMoCore.TrackConsole.Write(this, "LoadConfig", "Scene material is not exists. (scene={0}, material={1})", _scene.Code, Code);
            } else {
               _label = _material.Group.Label;
               _transformName = _material.TransformName;
               _optionLight = _material.OptionLight;
               _optionMerge = _material.OptionMerge;
               _optionSort = _material.OptionSort;
               _sortLevel = _material.SortLevel;
               _optionAlpha = _material.OptionAlpha;
               _optionDepth = _material.OptionDepth;
               _optionCompare = _material.OptionCompare;
               _optionDouble = _material.OptionDouble;
               _optionShadow = _material.OptionShadow;
               _optionShadowSelf = _material.OptionShadowSelf;
               _optionDynamic = _material.OptionDynamic;
               _optionTransmittance = _material.OptionTransmittance;
               _optionOpacity = _material.OptionOpacity;
            }
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 存储属性
         xconfig.Set("name", _name);
         // 存储设置
         SaveConfigInfo(xconfig);
         // 存储高度
         FXmlNode xheight = xconfig.CreateNode("Height");
         xheight.Set("depth", _heightDepth);
         // 存储表面
         FXmlNode xsurface = xconfig.CreateNode("Surface");
         xsurface.Set("rate", _surfaceRate);
         xsurface.Set("reflect", _surfaceReflect);
         xsurface.Set("bright", _surfaceBright);
         xsurface.Set("bright_level", _surfaceBrightLevel);
         xsurface.Set("coarse", _surfaceCoarse);
         xsurface.Set("coarse_level", _surfaceCoarseLevel);
         xsurface.Set("merge", _surfaceMerge);
         xsurface.Set("power", _surfacePower);
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 存储属性
         output.WriteString(Code);
         output.WriteWideString(_label);
         //output.WriteUTFString(_label);
         // 存储设置
         SerializeInfo(output);
         // 存储高度
         output.WriteFloat(_heightDepth);
         // 存储表面
         output.WriteFloat(_surfaceRate);
         output.WriteFloat(_surfaceReflect);
         output.WriteFloat(_surfaceBright);
         output.WriteFloat(_surfaceBrightLevel);
         output.WriteFloat(_surfaceCoarse);
         output.WriteFloat(_surfaceCoarseLevel);
         output.WriteFloat(_surfaceMerge);
         output.WriteFloat(_surfacePower); 
      }

      //============================================================
      // <T>重置内容。</T>
      //============================================================
      public override void Reset() {
         base.Reset();
         _heightDepth = 0.0f;
         _surfaceRate = 1.0f;
         _surfaceReflect = 1.0f;
         _surfaceBright = 1.0f;
         _surfaceBrightLevel = 1.0f;
         _surfaceCoarse = 1.0f;
         _surfaceCoarseLevel = 1.0f;
         _surfaceMerge = 1.0f;
         _surfacePower = 1.0f;
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
      }
   }
}

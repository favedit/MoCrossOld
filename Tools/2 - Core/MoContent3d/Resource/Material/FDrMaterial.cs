using System.Collections.Generic;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Common;
using MO.Content3d.Resource.Theme;
using MO.Common.Collection;

namespace MO.Content3d.Resource.Material
{
   //============================================================
   // <T>材质定义。</T>
   //============================================================
   public class FDrMaterial : FDrMaterialInfo, IComparer<FDrMaterial>
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrMaterial));

      // 主题
      protected FDrTheme _theme;

      // 材质组
      protected FDrMaterialGroup _group;

      // 主题名称
      protected string _themeName;

      //============================================================
      // <T>构造材质定义。</T>
      //============================================================
      public FDrMaterial() {
      }

      //============================================================
      // <T>获得或设置主题。</T>
      //============================================================
      public FDrTheme Theme {
         get { return _theme; }
         set { 
            _theme = value;
            _themeName = value.Name;
         }
      }
      
      //============================================================
      // <T>获得或设置材质组。</T>
      //============================================================
      public FDrMaterialGroup Group{
         get { return _group; }
         set { _group = value; }
      }

      //============================================================
      // <T>获得主题代码。</T>
      //============================================================
      public string ThemeCode {
         get { return RDrUtil.FormatPathToCode(_themeName); }
      }

      //============================================================
      // <T>获得或设置主题名称。</T>
      //============================================================
      public string ThemeName {
         get { return _themeName; }
         set { _themeName = value; }
      }
      
      //============================================================
      // <T>比较材质顺序。</T>
      //============================================================
      public int Compare(FDrMaterial target, FDrMaterial source) {
         return target._themeName.CompareTo(source._themeName);
      }

      //============================================================
      // <T>接收数据。</T>
      //============================================================
      public void Assign(FDrMaterial material) {
         // 存储属性
         _themeName = material.ThemeName;
         // 存储设置
         AssignInfo(material);
      }
      
      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 读取主题
         _theme = RContent3dManager.ThemeConsole.Find(xconfig.Nvl("theme_name"));
         if (_theme == null) {
            _theme = RContent3dManager.ThemeConsole.DefaultTheme;
         }
         _themeName = _theme.Name;
         // 读取属性
         LoadConfigInfo(xconfig);
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 存储主题
         xconfig.Set("theme_name", _themeName);
         // 存储属性
         SaveConfigInfo(xconfig);
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void LoadGroup(FDrMaterialGroup group) {
         // 存储属性
         _effectName = group.EffectName;
         _transformName = group.TransformName;
         // 存储设置
         _optionLight = group.OptionLight;
         _optionMerge = group.OptionMerge;
         _optionSort = group.OptionSort;
         _sortLevel = group.SortLevel;
         _optionAlpha = group.OptionAlpha;
         _optionDepth = group.OptionDepth;
         _optionCompare = group.OptionCompare;
         _optionDouble = group.OptionDouble;
         _optionShadow = group.OptionShadow;
         _optionShadowSelf = group.OptionShadowSelf;
         _optionDynamic = group.OptionDynamic;
         _optionTransmittance = group.OptionTransmittance;
         _optionOpacity = group.OptionOpacity;
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 存储属性
         output.WriteString(ThemeCode);
         output.WriteUTFString(_group.Label);
         // 存储设置
         SerializeInfo(output);
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void SerializeAll(IOutput output) {
         // 存储属性
         output.WriteString(_group.Code);
         //output.WriteUint32(0); // _group.CodeNumber
         //output.WriteInt32(0); // timeout
         //output.WriteString(_group.Code);
         //output.WriteUTFString(_group.Label);
         //output.WriteString(ThemeCode);
         // 存储设置
         SerializeInfo(output);
         // 存储贴图
         FVector<FDrMaterialTexture> textures = _group.Textures;
         output.WriteUint8((byte)textures.Count);
         foreach (FDrMaterialTexture texture in textures) {
            texture.Serialize(output);
         }
      }
   }
}

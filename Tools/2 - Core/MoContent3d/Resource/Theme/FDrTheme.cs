using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d;
using MO.Content2d.Common;
using MO.Content2d.Core;
using MO.Content3d.Resource.Common;
using MO.Content3d.Resource.Material;
using System;

namespace MO.Content3d.Resource.Theme
{
   //============================================================
   // <T>材质定义。</T>
   //============================================================
   public class FDrTheme : FDrResource, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrTheme));

      // 效果名称
      protected string _effectName;

      //============================================================
      // <T>构造材质定义。</T>
      //============================================================
      public FDrTheme() {
         _typeName = EDrResourceType.Theme;
         _typeLabel = "Theme";
      }

      //============================================================
      // <T>获得或设置效果名称。</T>
      //============================================================
      public string EffectName {
         get { return _effectName; }
         set { _effectName = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _effectName = xconfig.Get("effect_name", _effectName);
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 存储属性
         xconfig.Set("name", _name);
         xconfig.Set("label", _label);
         xconfig.SetNvl("effect_name", _effectName);
      }

      //============================================================
      // <T>加载设置文件。</T>
      //============================================================
      public void LoadConfigFile(string fileName) {
         FXmlDocument xdoc = new FXmlDocument(fileName);
         FXmlNode xroot = xdoc.Root;
         FXmlNode xmaterial = xroot.Find("Theme");
         if(xmaterial != null) {
            LoadConfig(xmaterial);
         }
      }

      //============================================================
      // <T>打开材质信息。</T>
      //============================================================
      public override void Open() {
         if(!_opened) {
            base.Open();
            LoadConfigFile(_configFileName);
         }
      }

      //============================================================
      // <T>存储设置文件。</T>
      //============================================================
      public void Store() {
         // 打开内容
         Open();
         // 存储设置
         FXmlDocument xdoc = new FXmlDocument();
         FXmlNode xroot = xdoc.Root;
         SaveConfig(xroot.CreateNode("Theme"));
         xdoc.SaveFile(_configFileName);
         _logger.Debug(this, "Store", "Save material config success. (file_name={0})", _configFileName);
         // 释放内容
         Dispose();
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         // 查找材质
         FVector<FDrMaterial> materials = new FVector<FDrMaterial>();
         foreach (FDrMaterialGroup group in RContent3dManager.MaterialConsole.Materials.Values) {
            FDrMaterial material = group.FindMaterial(_name);
            if (material != null) {
               material = new FDrMaterial();
               material.Assign(group.Materials.First);
               material.Group = group;
               material.ThemeName = _name;
               group.Materials.Push(material);
            }
            if (material != null) {
               materials.Push(material);
            }
         }
         // 输出材质集合
         output.WriteInt32(materials.Count);
         foreach (FDrMaterial material in materials) {
            material.SerializeAll(output);
         }
      }

      //============================================================
      // <T>根据指定模式导出数据。</T>
      //
      // @param modeCd 导出模式
      //============================================================
      public override void Export(ERsExportMode modeCd) {
         // 打开资源
         Open();
         //............................................................
         string exportFileName = RContent3dManager.ThemeConsole.ExportDirectory + "\\" + Code + ".ser";
         //............................................................
         // 序列化数据
         FByteFile file = new FByteFile();
         Serialize(file);
         file.SaveFile(exportFileName);
         //............................................................
         // 释放资源
         Dispose();
         _logger.Debug(this, "Export", "Export theme success. (file_name={0})", exportFileName);
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public override void Dispose() {
         if(_opened) {
            // 释放内容
            base.Dispose();
         }
      }
   }
}

using System;
using System.IO;
using System.Reflection;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Core.Forms.Common;

namespace MO.Core.Forms.Module.Configuration
{
   public class FConfigurationStorage : FObject
   {
      public const string TYPE_EXE = ".exe";

      public const string TYPE_DLL = ".dll";

      public const string TYPE_STOREXML = ".store.xml";

      public const string CFG_RUNTIME = "Runtime";

      protected FDictionary<IConfiguration> _configs = new FDictionary<IConfiguration>();

      //============================================================
      public FConfigurationStorage() {
      }

      //============================================================
      // <T>加载设置文件</T>
      //============================================================
      protected void LoadConfigFile() {
         // 获得配置文件名称
         Assembly assembly = Assembly.GetEntryAssembly();
         string location = assembly.Location;
         string name = assembly.ManifestModule.Name;
         string filename = null;
         if (name.EndsWith(TYPE_EXE)) {
            filename = location.Substring(0, location.Length - TYPE_EXE.Length) + TYPE_STOREXML;
         } else {
            throw new FFatalException("Load config error name={0}", name);
         }
         //// 加载设置
         //if (File.Exists(filename)) {
         //   _document.LoadFile(filename);
         //} else {
         //   _document.FileName = filename;
         //}
      }

      //============================================================
      // 获得控件路径名称
      //============================================================
      public string FindPath(System.Windows.Forms.Control control) {
         // 检查参数
         if (null == control) {
            return null;
         }
         // 获得父链表
         string path = "";
         while (null != control) {
            path = control.Name + "." + path;
            control = control.Parent;
         }
         return path;
      }

      //============================================================
      public IConfiguration Find(System.Windows.Forms.Control control) {
         // 检查参数
         if (null == control) {
            return null;
         }
         string path = FindPath(control);
         // 查找配置节点
         // return new FConfiguration(config);
         return null;
      }

      //============================================================
      public void LoadFormConfig(System.Windows.Forms.Control control) {
         FXmlDocument document = new FXmlDocument();
      }

      //============================================================
      public void SaveFormConfig(System.Windows.Forms.Control control) {
      }
   }
}

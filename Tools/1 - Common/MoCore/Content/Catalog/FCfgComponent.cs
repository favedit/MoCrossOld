using System;
using System.ComponentModel;
using MO.Common.Content;

namespace MO.Core.Content.Catalog
{
   public class FCfgComponent : FCfgObject
   {
      // 所在目录
      protected FCfgFolder _folder;

      // 类型名称
      protected string _typeName;

      // 编号
      protected int _id;

      // 唯一编号
      protected Guid _guid;

      //============================================================
      public FCfgComponent() {
         _guid = Guid.NewGuid();
      }

      //============================================================
      public FCfgFolder Folder {
         get { return _folder; }
         set { _folder = value; }
      }

      //============================================================
      [CategoryAttribute("基本")]
      [DescriptionAttribute("类型名称")]
      public string TypeName {
         get { return _typeName; }
      }

      //============================================================
      [CategoryAttribute("基本")]
      [DescriptionAttribute("编号")]
      public int Id {
         get { return _id; }
         set { _id = value; }
      }
      
      //============================================================
      [CategoryAttribute("基本")]
      [DescriptionAttribute("唯一编号")]
      public Guid Guid {
         get { return _guid; }
      }

      //============================================================
      public string FolderDirectory {
         get { return _folder.Directory; }
      }

      //============================================================
      public override void LoadConfig(FXmlNode config) {
         base.LoadConfig(config);
         // 读取编号
         if (config.Contains("id")) {
            _id = config.GetInteger("id");
         }
         // 读取唯一编号
         if (config.Contains("guid")) {
            _guid = Guid.Parse(config.Get("guid"));
         }
      }

      //============================================================
      public override void SaveConfig(FXmlNode config) {
         base.SaveConfig(config);
         // 设置类型
         config.Set("type", _typeName);
         // 设置编号
         config.Set("id", _id);
         // 设置唯一编号
         config.Set("guid", _guid.ToString());
      }
   }
}

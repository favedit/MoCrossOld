using System;
using System.ComponentModel;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Core.Content.Catalog
{
   //============================================================
   // <T>设置对象。</T>
   //============================================================
   public class FCfgObject : IComparable
   {
      // 名称
      protected string _name;

      // 类型
      protected string _type;

      // 标签
      protected string _label;

      // 索引
      protected int _displayIndex;

      // 附加信息
      protected object _tag;

      //============================================================
      public FCfgObject() {
      }

      //============================================================
      [CategoryAttribute("基本")]
      [DescriptionAttribute("名称")]
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      [CategoryAttribute("类型")]
      [DescriptionAttribute("类型")]
      public string Type {
         get { return _type; }
         set { _type = value; }
      }

      //============================================================
      [CategoryAttribute("基本")]
      [DescriptionAttribute("标签")]
      public string Label {
         get {
            if (RString.IsBlank(_label)) {
               return _name;
            }
            return _label;
         }
         set { _label = value; }
      }

      //============================================================
      [CategoryAttribute("基本")]
      [DescriptionAttribute("索引")]
      public int DisplayIndex {
         get { return _displayIndex; }
         set { _displayIndex = value; }
      }

      //============================================================
      public object Tag {
         get { return _tag; }
         set { _tag = value; }
      }

      //============================================================
      public virtual int CompareTo(object value) {
         FCfgObject cfgObject = value as FCfgObject;
         if (null != cfgObject) {
            if (_displayIndex == cfgObject.DisplayIndex) {
               return _name.CompareTo(cfgObject.Name);
            }
            return _displayIndex - cfgObject.DisplayIndex;
         }
         return 0;
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public virtual void LoadConfig(FXmlNode xconfig) {
         // 检查是否为设置文件
         if (xconfig.Name != "Configuration") {
            return;
         }
         // 读取名称
         if (xconfig.Contains("name")) {
            _name = xconfig.Get("name");
         }
         // 读取标签
         if (xconfig.Contains("label")) {
            _label = xconfig.Get("label");
         }
         // 读取索引
         if (xconfig.Contains("display_index")) {
            _displayIndex = xconfig.GetInteger("display_index");
         }
      }

      //============================================================
      public virtual void SaveConfig(FXmlNode config) {
         // 设置名称
         config.Set("name", _name);
         // 设置标签
         config.Set("label", _label);
         // 设置索引
         config.Set("display_index", _displayIndex);
      }
   }
}

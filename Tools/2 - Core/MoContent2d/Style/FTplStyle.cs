using System.Drawing;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content2d.Style
{
   //============================================================
   // <T>模版样式。</T>
   //============================================================
   public class FTplStyle : FObject
   {
      // 类型名称
      protected string _name;

      // 颜色
      protected Color _color;

      // 字体
      protected string _font;

      // 字体大小
      protected int _size;
      
      // 对齐方式
      protected string _align;

      // 属性集合
      protected FObjects<FTplStyleProperty> _properties = new FObjects<FTplStyleProperty>();

      // 效果器集合
      protected FObjects<FTplFilter> _filters = new FObjects<FTplFilter>();

      //============================================================
      // <T>构造样式。</T>
      //============================================================
      public FTplStyle() {
      }

      //============================================================
      // <T>获得或设置类型名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置颜色。</T>
      //============================================================
      public Color Color {
         get { return _color; }
         set { _color = value; }
      }

      //============================================================
      // <T>获得或设置字体。</T>
      //============================================================
      public string Font {
         get { return _font; }
         set { _font = value; }
      }

      //============================================================
      // <T>获得或设置字体大小。</T>
      //============================================================
      public int Size {
         get { return _size; }
         set { _size = value; }
      }

      //============================================================
      // <T>获得或设置对齐。</T>
      //============================================================
      public string Align {
         get { return _align; }
         set { _align = value; }
      }

      //============================================================
      // <T>获得效果器属性集合。</T>
      //============================================================
      public FObjects<FTplStyleProperty> Properties {
         get { return _properties; }
      }

      //============================================================
      // <T>获得效果器集合。</T>
      //============================================================
      public FObjects<FTplFilter> Filters {
         get { return _filters; }
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public FTplFilter CreateFilter(string typeName) {
         switch(typeName) {
            case "ColorMatrix":
               return new FTplColorMatrixFilter();
            case "Glow":
               return new FTplGlowFilter();
            case "GradientGlow":
               return new FTplGradientGlowFilter();
            case "DropShadow":
               return new FTplDropShadowFilter();
         }
         throw new FFatalException("Unknown type name. (type_name={0})", typeName);
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 加载属性
         _name = xconfig.Get("name");
         if(xconfig.Contains("color")) {
            _color = Color.FromArgb(RInt.ParseHex(xconfig.Get("color")));
         }
         _font = xconfig.Get("font", null);
         _size = xconfig.GetInteger("size", 0);
         _align = xconfig.Get("align", null);
         // 加载效果器集合
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Property")) {
               FTplStyleProperty property = new FTplStyleProperty();
               property.LoadConfig(xnode);
               _properties.Push(property);
            }else if(xnode.IsName("Filter")) {
               string typeName = xnode.Get("type_name");
               FTplFilter filter = CreateFilter(typeName);
               filter.LoadConfig(xnode);
               _filters.Push(filter);
            }
         }
      }

      //============================================================
      // <T>存储设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 设置属性
         xconfig.Set("name", _name);
         xconfig.Set("color", _color.ToArgb().ToString("X"));
         xconfig.Set("font", _font);
         xconfig.Set("size", _size);
         xconfig.Set("align", _align);
         // 存储效果器集合
         foreach(FTplFilter filter in _filters) {
            FXmlNode xnode = xconfig.CreateNode("Style");
            filter.SaveConfig(xnode);
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
         // 写入属性
         output.WriteString(_name);
         output.WriteInt32(_color.ToArgb());
         output.WriteString(_font);
         output.WriteInt8((sbyte)_size);
         output.WriteString(_align);
         // 写入属性集合
         output.WriteUint8((byte)_properties.Count);
         foreach(FTplStyleProperty property in _properties) {
            property.Serialize(output);
         }
         // 写入效果器集合
         output.WriteUint8((byte)_filters.Count);
         foreach(FTplFilter filter in _filters) {
            filter.Serialize(output);
         }
      }
   }
}

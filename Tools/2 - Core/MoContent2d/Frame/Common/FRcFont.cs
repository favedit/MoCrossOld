using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Theme;
using MO.Core.Forms.Common;
using System.ComponentModel;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>字体设置。</T>
   //============================================================
   [TypeConverter(typeof(FRcFontConverter))]
   public class FRcFont
   {
      // 字体名称
      protected string _fontName;

      // 颜色
      protected int _color = 0xFF << 24;

      // 字体大小
      protected int _size;

      // 加粗
      protected bool _bold = false;

      // 倾斜
      protected bool _italic = false;

      // 下划线
      protected bool _underline = false;

      // 废除线
      protected bool _strikeout = false;

      //============================================================
      // <T>构造字体设置。</T>
      //============================================================
      public FRcFont() {
      }

      //============================================================
      // <T>测试是否为空。</T>
      //============================================================
      public bool IsEmpty(){
         return false;
      }

      //============================================================
      // <T>获得或设置字体名称。</T>
      //============================================================
      public string FontName {
         get { return _fontName; }
         set { _fontName = value; }
      }

      //============================================================
      // <T>获得或设置颜色。</T>
      //============================================================
      public int Color {
         get { return _color; }
         set { _color = value; }
      }

      //============================================================
      // <T>获得或设置大小。</T>
      //============================================================
      public int Size {
         get { return _size; }
         set { _size = value; }
      }

      //============================================================
      // <T>获得或设置是否加粗。</T>
      //============================================================
      public bool Bold {
         get { return _bold; }
         set { _bold = value; }
      }

      //============================================================
      // <T>获得或设置是否倾斜。</T>
      //============================================================
      public bool Italic {
         get { return _italic; }
         set { _italic = value; }
      }

      //============================================================
      // <T>获得或设置下划线。</T>
      //============================================================
      public bool Underline {
         get { return _underline; }
         set { _underline = value; }
      }

      //============================================================
      // <T>获得或设置废除线。</T>
      //============================================================
      public bool Strikeout {
         get { return _strikeout; }
         set { _strikeout = value; }
      }

      //============================================================
      // <T>加载主题样式属性。</T>
      //
      // @param property 主题样式属性
      //============================================================
      public bool EqualsStyleProperty(FTplThemeStyleProperty property) {
         if (property == null) {
            return false;
         }
         if (_fontName != property.GetString("font_name")) {
            return false;
         }
         if (_color != property.GetHex("color", _color)) {
            return false;
         }
         if (_size != property.GetInteger("size", _size)) {
            return false;
         }
         if (_bold != property.GetBoolean("bold", _bold)) {
            return false;
         }
         if (_italic != property.GetBoolean("italic", _italic)) {
            return false;
         }
         if (_underline != property.GetBoolean("underline", _underline)) {
            return false;
         }
         if (_strikeout != property.GetBoolean("strikeout", _strikeout)) {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>加载主题样式属性。</T>
      //
      // @param property 主题样式属性
      //============================================================
      public void LoadStyleProperty(FTplThemeStyleProperty property) {
         if (property == null) {
            return;
         }
         _fontName = property.GetString("font_name");
         _color = property.GetHex("color", _color);
         _size = property.GetInteger("size", _size);
         _bold = property.GetBoolean("bold", _bold);
         _italic = property.GetBoolean("italic", _italic);
         _underline = property.GetBoolean("underline", _underline);
         _strikeout = property.GetBoolean("strikeout", _strikeout);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void LoadConfig(FXmlNode xconfig, string name) {
         if (xconfig.Contains(name)) {
            string value = xconfig.Get(name);
            Parse(value);
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void SaveConfig(FXmlNode xconfig, string name) {
         if (_color != 0) {
            string value = ToString();
            xconfig.Set(name, value);
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 写入字体
         output.WriteString(_fontName);
         // 写入字体颜色
         output.WriteInt32(_color);
         // 写入字体大小
         output.WriteInt8((sbyte)_size);
         // 写入字体是否加粗
         output.WriteBool(_bold);
         // 写入字体是否倾斜
         output.WriteBool(_italic);
         // 写入字体是否有废除线
         output.WriteBool(_strikeout);
         // 写入字体是否有下划线
         output.WriteBool(_underline);
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public bool Parse(string value) {
         if (value != null) {
            string[] item = value.Split(',');
            if (7 == item.Length) {
               _fontName = (item[0] == "None") ? "" : item[0];
               _color = RColor.ParseHex(item[1]);
               _size = RInt.Parse(item[2]);
               _bold = RBool.IsTrue(item[3]);
               _italic = RBool.IsTrue(item[4]);
               _strikeout = RBool.IsTrue(item[5]);
               _underline = RBool.IsTrue(item[6]);
            }
         }
         return false;
      }

      //============================================================
      // <T>获得字符串内容。</T>
      //
      // @return 字符串内容
      //============================================================
      public override string ToString() {
         string result = (_fontName == "" || _fontName == null) ? "None" : _fontName;
         result += "," + RColor.FormatHtml(_color);
         result += "," + _size;
         result += "," + _bold;
         result += "," + _italic;
         result += "," + _strikeout;
         result += "," + _underline;
         return result;
      }
   }
}
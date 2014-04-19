using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.IO;
using System.ComponentModel;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>字体。</T>
   //============================================================
   public class FUiFont
   {
      // 字体名称
      protected string _font;

      // 字体颜色
      protected Color _color;

      // 字体大小
      protected int _size;

      // 加粗
      protected bool _bold = false;

      // 倾斜
      protected bool _italic = false;
      
      // 废除线
      protected bool _strikeout = false;

      // 下划线
      protected bool _underline = false;

      //============================================================
      // <T>获得或设置字体名称。</T>
      //============================================================
      public string Font {
         get { return _font; }
         set { _font = value; }
      }

      //============================================================
      // <T>获得或设置字体颜色。</T>
      //============================================================
      public Color Color {
         get { return _color; }
         set { _color = value; }
      }

      //============================================================
      // <T>获得或设置字体大小。</T>
      //============================================================
      public int Size {
         get { return _size; }
         set { _size = value; }
      }

      //============================================================
      // <T>获得或设置字体是否加粗。</T>
      //============================================================
      public bool Bold {
         get { return _bold; }
         set { _bold = value; }
      }

      //============================================================
      // <T>获得或设置字体是否倾斜。</T>
      //============================================================
      public bool Italic {
         get { return _italic; }
         set { _italic = value; }
      }

      //============================================================
      // <T>获得或设置字体废除线。</T>
      //============================================================
      public bool Strikeout {
         get { return _strikeout; }
         set { _strikeout = value; }
      }

      //============================================================
      // <T>获得或设置字体下划线。</T>
      //============================================================
      public bool Underline {
         get { return _underline; }
         set { _underline = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void LoadConfig(FXmlNode xconfig, string name) {
         if (xconfig.Contains(name + "_font")) {
            _font = xconfig.Get(name + "_font");
         }
         if (xconfig.Contains(name + "_color")) {
            _color = ColorTranslator.FromHtml(xconfig.Get(name + "_color"));
         }
         if (xconfig.Contains(name + "_size")) {
            _size = RInt.Parse(xconfig.Get(name + "_size"));
         }
         if (xconfig.Contains(name+"_bold")) {
            _bold = xconfig.GetBoolean(name + "_bold");
         }
         if (xconfig.Contains(name + "_italic")) {
            _italic = xconfig.GetBoolean(name + "_italic");
         }
         if (xconfig.Contains(name + "_strikeout")) {
            _strikeout = xconfig.GetBoolean(name + "_strikeout");
         }
         if (xconfig.Contains(name + "_underline")) {
            _underline = xconfig.GetBoolean(name + "_underline");
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void SaveConfig(FXmlNode xconfig, string name) {
         if ("" != _font) {
            xconfig.Set(name + "_font", _font);
         }
         if (0 != _color.ToArgb()) {
            xconfig.Set(name + "_color", ColorTranslator.ToHtml(Color.FromArgb(_color.ToArgb())));
         }
         if (0 != _size) {
            xconfig.Set(name + "_size", _size);
         }
         if (false != _bold) {
            xconfig.Set(name + "_bold",_bold);
         }
         if (false !=_italic) {
            xconfig.Set(name + "_italic", _italic);
         }
         if (false != _strikeout) {
            xconfig.Set(name + "_strikeout", _strikeout);
         }
         if (false != _underline) {
            xconfig.Set(name + "_underline", _underline);
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 写入字体
         output.WriteUTFString(_font);
         // 写入字体颜色
         output.WriteInt32(_color.ToArgb());
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
         if (null !=value) {
            string[] item = value.Split(',');
            if (7 == item.Length) {
               _font = (item[0] == "None")?"":item[0];
               _color = ColorTranslator.FromHtml(item[1]);
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
         string result = (_font==""|| _font == null)?"None":_font;
         result += ","+ColorTranslator.ToHtml(Color.FromArgb(_color.ToArgb()));
         result += ","+_size;
         result += ","+_bold;
         result += ","+_italic;
         result += ","+_strikeout;
         result += ","+_underline;
         return result;
      }
   }
}
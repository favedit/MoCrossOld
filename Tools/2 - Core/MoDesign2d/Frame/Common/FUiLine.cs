using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using System.ComponentModel;
using MO.Common.Lang;
using MO.Common.Content;
using MO.Common.IO;

namespace MO.Design2d.Frame.Common
{


   [TypeConverter(typeof(FUiLineConverter))]
   public class FUiLine
   {
      // 颜色
      protected Color _color;

      // 宽度
      protected int _width;
      
      // 样式
      protected EUiLineStyle _style;

      public FUiLine() {
      }

      public FUiLine(Color color, int width,EUiLineStyle style) {
         _color = color;
         _width = width;
         _style = style;
      }

      //============================================================
      // <T>获得或设置颜色。</T>
      //============================================================
      public Color Color {
         get { return _color; }
         set { _color = value; }
      }

      //============================================================
      // <T>获得或设置宽度。</T>
      //============================================================
      public int Width {
         get { return _width; }
         set { _width = value; }
      }

      //============================================================
      // <T>获得或设置样式。</T>
      //============================================================
      public EUiLineStyle Style {
         get { return _style; }
         set { _style = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void LoadConfig(FXmlNode xconfig, string name) {
         if (xconfig.Contains(name+"_color")) {
            _color = ColorTranslator.FromHtml(xconfig.Get(name+"_color"));
         }
         if (xconfig.Contains(name+"_width")) {
            _width = RInt.Parse(xconfig.Get(name + "_width"));
         }
         if (xconfig.Contains(name + "_style_cd")) {
            _style = RUiLineStyle.Parse(xconfig.Get(name + "_style_cd"));
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void SaveConfig(FXmlNode xconfig, string name) {
         // 保存颜色
         if (0 != _color.ToArgb()) {
            xconfig.Set(name + "_color", ColorTranslator.ToHtml(Color.FromArgb(_color.ToArgb())));
         }

         // 保存宽度
         if (0 != _width) {
            xconfig.Set(name + "_width", _width.ToString());
         }

         // 保存样式
         if (EUiLineStyle.None != _style) {
            xconfig.Set(name + "_style_cd", _style.ToString());
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         // 写入颜色
         output.WriteUint8(_color.A);
         output.WriteUint8(_color.R);
         output.WriteUint8(_color.G);
         output.WriteUint8(_color.B);
         // 写入宽度
         output.WriteUint32((uint)_width);
         // 写入样式
         output.WriteInt8((sbyte)_style);
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public bool Parse(string value) {
         if (null != value) {
            string[] items = value.Split(',');
            if (3 == items.Length) {
               _color = ColorTranslator.FromHtml(items[0]);
               _width = RInt.Parse(items[1]);
               _style = RUiLineStyle.Parse(items[2]);
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
         string resule = "";
         resule += ColorTranslator.ToHtml(Color.FromArgb(_color.ToArgb()));
         resule += ","+_width;
         resule += ","+_style.ToString();
         return resule;
      }
   }
}

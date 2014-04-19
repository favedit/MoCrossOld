using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core.Forms.Common;
using System.ComponentModel;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>界面线。</T>
   //============================================================
   [TypeConverter(typeof(FRcLineConverter))]
   public class FRcLine
   {
      // 颜色
      protected int _color;

      // 宽度
      protected int _width;

      // 样式
      protected ERcLineStyle _style;

      //============================================================
      // <T>构造直线。</T>
      //============================================================
      public FRcLine() {
      }

      //============================================================
      // <T>获得或设置颜色。</T>
      //============================================================
      public int Color {
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
      public ERcLineStyle Style {
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
         // 写入颜色
         output.WriteInt32(_color);
         // 写入宽度
         output.WriteInt8((sbyte)_width);
         // 写入样式
         output.WriteInt8((sbyte)_style);
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public bool Parse(string value) {
         if (RString.IsEmpty(value)) {
            return false;
         }
         string[] items = value.Split(',');
         if (items.Length == 3) {
            _color = RColor.ParseHex(items[0]);
            _width = RInt.Parse(items[1]);
            _style = RUiLineStyle.Parse(items[2]);
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
         resule += RColor.FormatHtml(_color);
         resule += "," + _width;
         resule += "," + _style.ToString();
         return resule;
      }
   }
}

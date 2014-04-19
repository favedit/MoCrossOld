using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using System;
using System.ComponentModel;
using System.Drawing;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>颜色。</T>
   //============================================================
   [TypeConverter(typeof(FRcColorConverter))]
   public class FRcColor : FObject
   {
      // 有效
      protected bool _valid;

      // 透明色
      public byte _alpha;

      // 红色
      public byte _red;

      // 蓝色
      public byte _green;

      // 绿色
      public byte _blue;

      //============================================================
      // <T>构造颜色。</T>
      //
      // @param red 红色
      // @param green 绿色
      // @param blue 蓝色
      // @param alpha 透明色
      //============================================================
      public FRcColor(byte red = 0, byte green = 0, byte blue = 0, byte alpha = 0, bool valid = false) {
         _alpha = alpha;
         _red = red;
         _green = green;
         _blue = blue;
         _valid = valid;
      }

      //============================================================
      // <T>判定是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsEmpty() {
         return (_alpha == 0) && (_red == 0) && (_green == 0) && (_blue == 0);
      }

      //============================================================
      // <T>获得或设置有效性。</T>
      //============================================================
      [Category("属性")]
      [Browsable(true)]
      [DefaultValue(false)]
      [Description("有效性。")]
      public bool Valid {
         get { return _valid; }
         set { _valid = value; }
      }

      //============================================================
      // <T>获得或设置颜色。</T>
      [Category("属性")]
      [Browsable(true)]
      [DefaultValue(0)]
      [Description("颜色。")]
      //============================================================
      public Color Color {
         get { return Color.FromArgb(_alpha, _red, _green, _blue); }
         set { Set(value.R, value.G, value.B, value.A); }
      }

      //============================================================
      // <T>获得或设置透明度。</T>
      //============================================================
      [Category("属性")]
      [Browsable(true)]
      [DefaultValue(0)]
      [Description("透明度。")]
      public byte Alpha {
         get { return _alpha; }
         set { _alpha = value; }
      }

      //============================================================
      // <T>获得或设置透明。</T>
      //============================================================
      [Browsable(false)]
      public byte Red {
         get { return _red; }
         set { _red = value; }
      }

      //============================================================
      // <T>获得或设置红色。</T>
      //============================================================
      [Browsable(false)]
      public byte Green {
         get { return _green; }
         set { _green = value; }
      }

      //============================================================
      // <T>获得或设置绿色。</T>
      //============================================================
      [Browsable(false)]
      public byte Blue {
         get { return _blue; }
         set { _blue = value; }
      }

      //============================================================
      // <T>获得或设置透明。</T>
      //============================================================
      [Browsable(false)]
      public float ValueA {
         get { return (float)_alpha / 255; }
         set { _alpha = (byte)(value * 255); }
      }

      //============================================================
      // <T>获得或设置红色。</T>
      //============================================================
      [Browsable(false)]
      public float ValueR {
         get { return (float)_red / 255; }
         set { _red = (byte)(value * 255); }
      }

      //============================================================
      // <T>获得或设置绿色。</T>
      //============================================================
      [Browsable(false)]
      public float ValueG {
         get { return (float)_green / 255; }
         set { _green = (byte)(value * 255); }
      }

      //============================================================
      // <T>获得或设置蓝色。</T>
      //============================================================
      [Browsable(false)]
      public float ValueB {
         get { return (float)_blue / 255; }
         set { _blue = (byte)(value * 255); }
      }

      //============================================================
      // <T>设置内容。</T>
      //
      // @param red 红色
      // @param green 绿色
      // @param blue 蓝色
      // @param alpha 透明色
      //============================================================
      public void Set(byte red = 0, byte green = 0, byte blue = 0, byte alpha = 255) {
         _red = red;
         _green = green;
         _blue = blue;
         _alpha = alpha;
      }

      //============================================================
      // <T>接收颜色对象。</T>
      //
      // @param constainsValid 颜色对象
      //============================================================
      public void Assign(FRcColor color, bool constainsValid = true) {
         if (color == null) {
            return;
         }
         if (constainsValid) {
            Valid = color.Valid;
         }
         _alpha = color.Alpha;
         _red = color.Red;
         _green = color.Green;
         _blue = color.Blue;
      }

      //============================================================
      // <T>重置数据。</T>
      //============================================================
      public void Reset() {
         _red = 0;
         _green = 0;
         _blue = 0;
         _alpha = 0;
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void LoadConfig(FXmlNode xconfig, string name) {
         // 加载有效性
         bool containsValid = xconfig.Contains(name + "_valid");
         if (containsValid) {
            _valid = xconfig.GetBoolean(name + "_valid");
         } else {
            _valid = false;
         }
         // 加载颜色
         if (xconfig.Contains(name)) {
            string value = xconfig.Get(name);
            if (!containsValid) {
               _valid = true;
            }
            Parse(value);
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void SaveConfig(FXmlNode xconfig, string name) {
         // 保存有效性
         xconfig.SetNvl(name + "_valid", _valid);
         // 保存颜色
         if (!IsEmpty()) {
            xconfig.SetNvl(name, ToString16());
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteUint8(_alpha);
         output.WriteUint8(_red);
         output.WriteUint8(_green);
         output.WriteUint8(_blue);
      }

      //============================================================
      // <T>解析字符串内容。</T>
      //
      // @param value 字符串内容
      // @param alpha 是否包含透明
      //============================================================
      public void Parse(int value, bool alpha = true) {
         // 设置颜色
         _red = (byte)(value >> 16);
         _green = (byte)(value >> 8);
         _blue = (byte)(value);
         // 设置透明
         if (alpha) {
            _alpha = (byte)(value >> 24);
         } else {
            _alpha = 255;
         }
      }

      //============================================================
      // <T>解析字符串内容。</T>
      //
      // @param value 字符串内容
      //============================================================
      public bool Parse(string value, bool alpha = true) {
         // 重置内容
         Reset();
         // 判断为空
         if (RString.IsEmpty(value)) {
            return false;
         }
         // 去除前置符号#
         if (value.StartsWith("#")) {
            value = value.Substring(1);
         }
         // 去除前置符号0x
         if (value.StartsWith("0x")) {
            value = value.Substring(2);
         }
         // 解析内容
         int result = Convert.ToInt32(value, 16);
         Parse(result, alpha);
         return true;
      }

      //============================================================
      // <T>获得整数内容。</T>
      //
      // @return 整数内容
      //============================================================
      public int ToValue() {
         return (_alpha << 24) | (_red << 16) | (_green << 8) | _blue;
      }

      //============================================================
      // <T>获得字符串内容。</T>
      //
      // @return 字符串内容
      //============================================================
      public override string ToString() {
         if (_valid) {
            int value = ToValue();
            return String.Format("{0:X}", value);
         }
         return null;
      }

      //============================================================
      // <T>获得字符串内容。</T>
      //
      // @return 字符串内容
      //============================================================
      public string ToString16() {
         if (_valid) {
            int value = ToValue();
            return String.Format("0x{0:x}", value);
         }
         return null;
      }
   }
}

using System;
using System.ComponentModel;
using MO.Common.Lang;

namespace MO.Common.Geom
{
   //============================================================
   // <T>颜色。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class SColor
   {
      // 有效
      protected bool _valid;

      // 透明色
      public byte A;

      // 红色
      public byte R;

      // 蓝色
      public byte G;

      // 绿色
      public byte B;

      //============================================================
      // <T>构造颜色。</T>
      //============================================================
      public SColor(byte r = 0, byte g = 0, byte b = 0, byte a = 0, bool valid = false) {
         _valid = valid;
         A = a;
         R = r;
         G = g;
         B = b;
      }

      //============================================================
      // <T>获得或设置是否有效。</T>
      //============================================================
      [Category("属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("是否有效。")]
      public bool Valid {
         get { return _valid; }
         set { _valid = value; }
      }

      //============================================================
      // <T>获得或设置透明度。</T>
      //============================================================
      [Category("属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("透明度。")]
      public byte Alpha {
         get { return A; }
         set { A = value; }
      }

      //============================================================
      // <T>获得或设置透明。</T>
      //============================================================
      [Browsable(false)]
      public float ValueA {
         get { return (float)A / 255; }
         set { A = (byte)(value * 255); }
      }

      //============================================================
      // <T>获得或设置红色。</T>
      //============================================================
      [Browsable(false)]
      public float ValueR {
         get { return (float)R / 255; }
         set { R = (byte)(value * 255); }
      }

      //============================================================
      // <T>获得或设置绿色。</T>
      //============================================================
      [Browsable(false)]
      public float ValueG {
         get { return (float)G / 255; }
         set { G = (byte)(value * 255); }
      }

      //============================================================
      // <T>获得或设置蓝色。</T>
      //============================================================
      [Browsable(false)]
      public float ValueB {
         get { return (float)B / 255; }
         set { B = (byte)(value * 255); }
      }

      //============================================================
      // <T>判定是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsEmpty() {
         return (A == 0) && (R == 0) && (G == 0) && (B == 0);
      }

      //============================================================
      // <T>设置内容。</T>
      //
      // @param r 红色
      // @param g 绿色
      // @param b 蓝色
      // @param a 透明色
      //============================================================
      public void Set(byte r = 0, byte g = 0, byte b = 0, byte a = 255) {
         R = r;
         G = g;
         B = b;
         A = a;
      }

      //============================================================
      // <T>设置内容。</T>
      //
      // @param r 红色
      // @param g 绿色
      // @param b 蓝色
      // @param a 透明色
      //============================================================
      public void SetFloat(float r = 0, float g = 0, float b = 0, float a = 1.0f) {
         R = (byte)(r * 255.0f);
         G = (byte)(g * 255.0f);
         B = (byte)(b * 255.0f);
         A = (byte)(a * 255.0f);
      }
      
      //============================================================
      // <T>接收颜色对象。</T>
      //
      // @param constainsValid 颜色对象
      //============================================================
      public void Assign(SColor color, bool constainsValid = true) {
         if (color == null) {
            return;
         }
         if (constainsValid) {
            Valid = color.Valid;
         }
         A = color.A;
         R = color.R;
         G = color.G;
         B = color.B;
      }
      
      //============================================================
      // <T>解析整数内容。</T>
      //
      // @param value 整数内容
      //============================================================
      public void Parse(int value) {
         A = (byte)(value >> 24);
         R = (byte)(value >> 16);
         G = (byte)(value >> 8);
         B = (byte)(value);
      }

      //============================================================
      // <T>解析字符串内容。</T>
      //
      // @param value 字符串内容
      // @param alpha 是否包含透明
      //============================================================
      public void Parse(int value, bool alpha = true) {
         // 设置颜色
         R = (byte)(value >> 16);
         G = (byte)(value >> 8);
         B = (byte)(value);
         // 设置透明
         if(alpha) {
            A = (byte)(value >> 24);
         } else {
            A = 255;
         }
      }

      //============================================================
      // <T>解析字符串内容。</T>
      //
      // @param value 字符串内容
      //============================================================
      public void Parse(string value, bool alpha = true) {
         // 重置内容
         Reset();
         // 判断为空
         if (RString.IsEmpty(value)) {
            return;
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
      }

      //============================================================
      // <T>解析字符串内容。</T>
      //
      // @param value 字符串内容
      // @param alpha 是否包含透明
      //============================================================
      public bool ParseHex(string value, bool alpha = true) {
         // 判断为空
         if(RString.IsEmpty(value)) {
            Reset();
            return true;
         }
         // 检查有效性
         if (!RInt.IsHex(value)) {
            return false;
         }
         // 消除标志
         if(value.StartsWith("#")) {
            value = value.Substring(1);
            if(value.Length == 6) {
               alpha = false;
            }
         }
         // 消除标志
         if (value.StartsWith("0x")) {
            value = value.Substring(2);
            if(value.Length == 6) {
               alpha = false;
            }
         }
         // 解析内容
         int result = RInt.ParseHex(value); ;
         Parse(result, alpha);
         return true;
      }

      //============================================================
      // <T>重置数据。</T>
      //============================================================
      public void Reset() {
         A = 0;
         R = 0;
         G = 0;
         B = 0;
      }

      //============================================================
      // <T>获得整数内容。</T>
      //
      // @return 整数内容
      //============================================================
      public int ToValue() {
         return (A << 24) | (R << 16) | (G << 8) | (B);
      }

      //============================================================
      // <T>获得字符串内容。</T>
      //
      // @return 字符串内容
      //============================================================
      public override string ToString() {
         if(_valid){
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

using MO.Common.Lang;
using System;
using System.Drawing;

namespace MO.Core.Forms.Common
{
   //============================================================
   // <T>颜色工具类。</T>
   //============================================================
   public class RColor
   {
      //============================================================
      // <T>解析颜色字符串为数字。</T>
      //
      // @param source 字符串
      // @return 整数
      //============================================================
      public static int ConvertRevert(int value) {
         Color color = Color.FromArgb(value);
         Color targetColor = Color.FromArgb(color.A, color.B, color.G, color.R);
         return targetColor.ToArgb();
      }

      //============================================================
      // <T>解析颜色字符串为数字。</T>
      //
      // @param source 字符串
      // @return 整数
      //============================================================
      public static int ParseHex(string source) {
         // 解析内容
         if (RString.IsEmpty(source)) {
            return 0;
         }
         if (!RInt.IsHex(source)) {
            return 0;
         }
         string value = source.Trim();
         if (value.Length == 0) {
            return 0;
         }
         // 去除前缀
         if (value.StartsWith("#")) {
            value = value.Substring(1);
         }
         if (value.StartsWith("0x")) {
            value = value.Substring(2);
         }
         // 转换为数字
         int result = Convert.ToInt32(value, 16);
         if (value.Length <= 6) {
            result |= 0xFF << 24;
         }
         return result;
      }

      //============================================================
      // <T>解析颜色字符串为颜色。</T>
      //
      // @param source 字符串
      // @return 颜色
      //============================================================
      public static Color ParseHexColor(string source) {
         int value = ParseHex(source);
         return Color.FromArgb(value);
      }

      //============================================================
      // <T>格式化数字为颜色字符串。</T>
      //
      // @param value 数字
      // @param length 长度
      // @return 字符串
      //============================================================
      public static string FormatHex(int value, int length = 8) {
         string source = String.Format("{0:X}", value);
         source = RString.PadLeft(source, length, '0');
         return source;
      }

      //============================================================
      // <T>格式化数字为颜色字符串。</T>
      //
      // @param value 数字
      // @param length 长度
      // @return 字符串
      //============================================================
      public static string FormatHtml(int value, int length = 8) {
         string source = String.Format("{0:X}", value);
         source = RString.PadLeft(source, length, '0');
         return "#" + source;
      }
   }
}

using System;
using System.Drawing;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>界面工具。</T>
   //============================================================
   public class RRcUtil
   {
      //============================================================
      // <T>格式化颜色为一个字符串。</T>
      //
      // @param value 颜色
      // @return 字符串
      //============================================================
      public static string FormatColorToString(Color color) {
         int value = color.ToArgb();
         return String.Format("#{0:X}", value);
      }

      //============================================================
      // <T>从字符串中解析一个颜色。</T>
      //
      // @param value 字符串
      // @return 颜色
      //============================================================
      public static Color ParseColorFromString(string value) {
         FRcColor color = new FRcColor();
         color.Parse(value);
         return Color.FromArgb(color.Alpha, color.Red, color.Green, color.Blue);
      }
   }
}

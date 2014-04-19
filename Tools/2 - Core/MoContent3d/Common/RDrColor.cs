using System;
using System.Drawing;
using MO.Common.Geom;

namespace MO.Content3d.Common
{
   //============================================================
   // <T>颜色工具类。</T>
   //============================================================
   public static class RDrColor
   {
      //============================================================
      // <T>根据浮点颜色获得颜色。</T>
      //
      // @param color 浮点颜色
      // @reutrn 颜色
      //============================================================
      public static Color FromFloatColor(SFloatColor4 color) {
         return Color.FromArgb((int)(color.A * 255) > 255 ? 255 : (int)(color.A * 255), (int)(color.R * 255), (int)(color.G * 255), (int)(color.B * 255));
      }

      //============================================================
      // <T>获得浮点颜色。</T>
      //
      // @param color 颜色
      // @reutrn 浮点颜色
      //============================================================
      public static SFloatColor4 ToFloatColor(Color color) {
         SFloatColor4 result = new SFloatColor4();
         result.R = (float)(color.R / 255);
         result.G = (float)(color.G / 255);
         result.B = (float)(color.B / 255);
         result.A = (float)(color.A / 255);
         return result;
      }

      //============================================================
      // <T>获得颜色字符串。</T>
      //
      // @param color 浮点颜色
      // @reutrn 字符串
      //============================================================
      public static string ToColorString(SFloatColor4 color) {
         Color result = Color.FromArgb((int)(color.A * 255) > 255 ? 255 : (int)(color.A * 255), (int)(color.R * 255), (int)(color.G * 255), (int)(color.B * 255));
         string text = Convert.ToString(result.ToArgb(), 16);
         return text.ToUpper();
      }
   }
}

using System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>浮点功能类。</T>
   //============================================================
   public class RFloat
   {
      // 三角比率
      public const float PI = (float)Math.PI;

      // 弧度转换
      public const float RadianRate = (float)(180 / Math.PI);

      // 度转换
      public const float DegreeRate = (float)(Math.PI / 180);

      //============================================================
      // <T>解析字符串为内容。</T>
      //
      // @param value 字符串
      // @param defaultValue 默认内容
      //============================================================
      public static float Parse(string value, float defaultValue = 0) {
         if(value != null) {
            if(value.Length > 0) {
               if(-1 == value.IndexOf("#")) {
                  if(value != "NaN") {
                     return float.Parse(value);
                  }
               }
            }
         }
         return defaultValue;
      }
   }
}

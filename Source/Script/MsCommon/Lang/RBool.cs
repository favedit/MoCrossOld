﻿namespace MS.Common.Lang
{
   //============================================================
   // <T>布尔工具类。</T>
   //
   // @history MAOCY 140414
   //============================================================
   public class RBool : RTypes<bool>
   {
      // 真字符
      public const char TrueChar = 'Y';

      // 真字符串
      public const string TrueString = "Y";

      // 假字符
      public const char FalseChar = 'N';

      // 假字符串
      public const string FalseString = "N";

      //============================================================
      // <T>测试一个对象内容是否为真。</T>
      //
      // @param value 对象
      // @return 是否为真
      //============================================================
      public static bool IsTrue(object value) {
         return IsTrue(RString.Nvl(value));
      }

      //============================================================
      // <T>测试一个字符串内容是否为真。</T>
      //
      // @param value 字符串
      // @return 是否为真
      //============================================================
      public static bool IsTrue(string value) {
         if(null != value) {
            value = value.ToLower();
            return ("1" == value || "y" == value || "yes" == value || "t" == value || "true" == value || "o" == value);
         }
         return false;
      }

      //============================================================
      // <T>格式化一个布尔值为字符串。</T>
      //
      // @param value 布尔值
      // @return 字符串
      //============================================================
      public static string ToString(bool value) {
         return value ? "Y" : "N";
      }

      //============================================================
      // <T>格式化一个布尔值为字符串。</T>
      //
      // @param value 布尔值
      // @param trueString 真字符串
      // @param falseString 假字符串
      // @return 字符串
      //============================================================
      public static string ToString(bool value, string trueString, string falseString) {
         return value ? trueString : falseString;
      }
   }
}

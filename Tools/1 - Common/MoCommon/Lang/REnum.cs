using System;
using SysEnum = System.Enum;

namespace MO.Common.Lang
{
   //============================================================
   // <T>枚举工具类。</T>
   //============================================================
   public class REnum
   {
      //============================================================
      // <T>获得枚举对象中值对应的名称。</T>
      //
      // @param type 枚举对象
      // @param value 值
      // @return 名称
      //============================================================
      public static string ToString(Type type, object value) {
         return SysEnum.GetName(type, value);
      }

      //============================================================
      // <T>获得枚举字符串行集合。</T>
      //
      // @param type 枚举对象
      // @return 名称
      //============================================================
      public static string[] ToStringLines(Type type) {
         return SysEnum.GetNames(type);
      }

      //============================================================
      // <T>获得枚举对象中值对应的名称。</T>
      //
      // @template T 枚举对象
      // @param value 值
      // @return 名称
      //============================================================
      public static string[] ToStringLines<T>() {
         return SysEnum.GetNames(typeof(T));
      }

      //============================================================
      // <T>获得枚举字符串行集合。</T>
      //
      // @param type 枚举对象
      // @return 名称
      //============================================================
      public static string ToStringLine(Type type) {
         string[] names = SysEnum.GetNames(type);
         FString result = new FString();
         int count = names.Length;
         for (int n = 0; n < count; n++) {
            if(n > 0){
               result.AppendLine();
            }
            result.Append(names[n]);
         }
         return result.ToString();
      }

      //============================================================
      // <T>获得枚举对象中值对应的名称。</T>
      //
      // @template T 枚举对象
      // @param value 值
      // @return 名称
      //============================================================
      public static string ToString<T>(object value) {
         return SysEnum.GetName(typeof(T), value);
      }

      //============================================================
      // <T>获得枚举对象中名称对应的值。</T>
      //
      // @param type 枚举对象
      // @param value 字符串
      // @return 值
      //============================================================
      public static object ToValue(Type type, string value) {
         return SysEnum.Parse(type, value, true);
      }

      //============================================================
      // <T>获得枚举对象中名称对应的值。</T>
      //
      // @template T 枚举对象
      // @param value 字符串
      // @return 值
      //============================================================
      public static T ToValue<T>(string value) {
         return (T)SysEnum.Parse(typeof(T), value, true);
      }
   }
}

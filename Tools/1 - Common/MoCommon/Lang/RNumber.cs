using System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>数字工具类。</T>
   //
   // @history 110209 MAOCY 创建
   //============================================================
   public class RNumber
   {
      // 数字类型列表
      public static Type[] TYPES = new Type[] {
         typeof(byte), typeof(Byte),
         typeof(short), typeof(int), typeof(long), 
         typeof(Int16), typeof(Int32), typeof(Int64),
         typeof(ushort), typeof(uint), typeof(ulong), 
         typeof(UInt16), typeof(UInt32), typeof(UInt64)
      };

      //============================================================
      // <T>是否为数字类型。</T>
      //
      // @param type 类型
      // @return 是否为数字类型
      //============================================================
      public static bool IsNumberType(Type type) {
         foreach (Type stype in TYPES) {
            if (type == stype) {
               return true;
            }
         }
         return false;
      }

      //============================================================
      // <T>格式化为显示字符串。</T>
      //
      // @param type 类型
      // @param value 数字值
      // @param numberType 数字类型
      // @return 字符串
      //============================================================
      public static string FormatDisplay(Type type, object value, int numberType) {
         if (null == value) {
            return null;
         }
         // 格式化字符串
         string format = null;
         if ((type == typeof(byte)) || (type == typeof(Byte))) {
            if (16 == numberType) {
               format = ((byte)value).ToString("X2");
            } else {
               format = Convert.ToString((byte)value, numberType);
            }
         } else if ((type == typeof(short)) || (type == typeof(Int16))) {
            if (16 == numberType) {
               format = ((short)value).ToString("X4");
            } else {
               format = Convert.ToString((short)value, numberType);
            }
         } else if ((type == typeof(int)) || (type == typeof(Int32))) {
            if (16 == numberType) {
               format = ((int)value).ToString("X8");
            } else {
               format = Convert.ToString((int)value, numberType);
            }
         } else if ((type == typeof(long)) || (type == typeof(Int64))) {
            if (16 == numberType) {
               format = ((long)value).ToString("X16");
            } else {
               format = Convert.ToString((long)value, numberType);
            }
         } else if ((type == typeof(ushort)) || (type == typeof(UInt16))) {
            if (16 == numberType) {
               format = ((ushort)value).ToString("X4");
            } else {
               format = Convert.ToString((ushort)value, numberType);
            }
         } else if ((type == typeof(uint)) || (type == typeof(UInt32))) {
            if (16 == numberType) {
               format = ((uint)value).ToString("X8");
            } else {
               format = Convert.ToString((uint)value, numberType);
            }
         } else if ((type == typeof(ulong)) || (type == typeof(UInt64))) {
            if (16 == numberType) {
               format = ((long)value).ToString("X16");
            } else {
               format = Convert.ToString((long)value, numberType);
            }
         }
         return format;
      }
   }
}

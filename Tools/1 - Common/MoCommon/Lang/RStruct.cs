using System;
using System.Runtime.InteropServices;

namespace MO.Common.Lang
{
   //============================================================
   // <T>结构体工具类。</T>
   //
   // @history 110125 MAOCY 创建
   //============================================================
   public class RStruct
   {
      //============================================================
      // <T>将一个结构体转换为字节数组。</T>
      //
      // @param value 结构体对象
      // @return 字节数组
      //============================================================
      public static byte[] StructToBytes(object value) {
         int size = Marshal.SizeOf(value);
         IntPtr buffer = Marshal.AllocHGlobal(size);
         try {
            Marshal.StructureToPtr(value, buffer, false);
            byte[] bytes = new byte[size];
            Marshal.Copy(buffer, bytes, 0, size);
            return bytes;
         } finally {
            Marshal.FreeHGlobal(buffer);
         }
      }

      //============================================================
      // <T>将一个指定开始位置的字节数组转换为结构体。</T>
      //
      // @param bytes 字节数组
      // @param offset 开始位置
      // @param type 结构体类型
      // @return 结构体对象
      //============================================================
      public static object BytesToStruct(byte[] bytes, int offset, Type type) {
         int size = Marshal.SizeOf(type);
         IntPtr buffer = Marshal.AllocHGlobal(size);
         try {
            Marshal.Copy(bytes, offset, buffer, size);
            return Marshal.PtrToStructure(buffer, type);
         } finally {
            Marshal.FreeHGlobal(buffer);
         }
      }

      //============================================================
      // <T>将一个字节数组转换为结构体。</T>
      //
      // @param bytes 字节数组
      // @param type 结构体类型
      // @return 结构体对象
      //============================================================
      public static object BytesToStruct(byte[] bytes, Type type) {
         return BytesToStruct(bytes, 0, type);
      }
   }
}

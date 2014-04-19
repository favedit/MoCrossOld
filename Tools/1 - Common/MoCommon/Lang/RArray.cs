using System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>数组工具类。</T>
   //============================================================
   public class RArray<T>
   {
      //============================================================
      // <T>判断一个数组中是否含有指定内容。</T>
      //
      // @param values 数组
      // @param value 内容
      // @return 是否含有
      //============================================================
      public static bool Contains(T[] values, T value) {
         return (-1 != Array.IndexOf<T>(values, value));
      }

      //============================================================
      // <T>判断一个数组中是否含有指定内容。</T>
      //
      // @param values 数组
      // @param value 内容
      // @param offset 位置
      // @param length 长度
      // @return 是否含有
      //============================================================
      public static bool Contains(T[] values, T value, int offset, int length) {
         return (-1 != Array.IndexOf<T>(values, value, offset, length));
      }

      //============================================================
      // <T>从字符数组中查找指定字符数组。</T>
      //
      // @param source 数组
      // @param offset 数组开始位置
      // @param length 数组数据长度
      // @param find 查找数组
      // @param findOffset 查找数组开始位置
      // @param findLength 查找数组数据长度
      // @return 查找到数组的索引位置<br>不存在时返回-1
      //============================================================
      public static int Find(T[] source, int offset, int length,
                             T[] find, int findOffset, int findLength) {
         T first = find[findOffset];
         int m = offset - 1;
         while(++m < length) {
            if(source[m].Equals(first)) {
               int n = findOffset;
               while(++n < findLength) {
                  if(!source[m + n].Equals(find[n])) {
                     break;
                  }
               }
               if(n == findLength - 1) {
                  return m;
               }
            }
         }
         return -1;
      }

      //============================================================
      // <T>从一个数组中截取一部分做为另一个数组返回。</T>
      //
      // @param values 数组
      // @param offset 位置
      // @param length 长度
      // @return 截取数组
      //============================================================
      public static T[] Cut(T[] values, int offset, int length) {
         T[] result = new T[length];
         Array.Copy(values, offset, result, 0, length);
         return result;
      }

      //============================================================
      // <T>清空数组。</T>
      //
      // @param values 数组
      //============================================================
      public static void Clear(T[] values) {
         Array.Clear(values, 0, values.Length);
      }
   }
}

using System;

namespace MO.Common.Collection
{
   //============================================================
   // <T>哈希工具类。</T>
   //============================================================
   public static class RHash
   {
      public const int FACTOR = 16;

      //============================================================
      // <T>哈希入口对象。</T>
      //============================================================
      public class FEntry
      {
         // 哈希值
         internal int _hash;

         // 索引未知
         internal int _index;

         // 下一个入口对象
         internal FEntry _next;

         //============================================================
         // <T>构造哈希入口对象。</T>
         //
         // @param hash 哈希值
         // @param index 索引位置
         // @param next 下一个对象
         //============================================================
         public FEntry(int hash, int index, FEntry next) {
            _hash = hash;
            _index = index;
            _next = next;
         }

         //============================================================
         // <T>设置内容。</T>
         //
         // @param index 索引位置
         // @param next 下一个对象
         //============================================================
         public void Set(int index, FEntry next) {
            _index = index;
            _next = next;
         }
      }

      //============================================================
      // <T>计算对象的哈希代码。</T>
      //
      // @param item 对象
      // @return 代码
      //============================================================
      public static int Code(object item) {
         return Math.Abs(item.GetHashCode());
      }

      //============================================================
      // <T>修正删除元素的哈希表。</T>
      //
      // @param entries 入口对象列表
      // @param index 删除的索引位置
      //============================================================
      public static void Remove(FEntry[] entries, int index) {
         int length = entries.Length;
         for (int n = 0; n < length; n++) {
            FEntry entry = entries[n];
            while (null != entry) {
               if (entry._index > index) {
                  entry._index--;
               }
               entry = entry._next;
            }
         }
      }
      
      //============================================================
      // <T>调整哈希表大小。</T>
      //
      // @param entries 入口对象列表
      // @param count 总数
      // @return 调整后的入口对象列表
      //============================================================
      public static FEntry[] Resize(FEntry[] entries, int count) {
         FEntry[] alloc = new FEntry[count];
         if (null != entries) {
            int total = entries.Length;
            for (int n = 0; n < total; n++) {
               FEntry entry = entries[n];
               while (null != entry) {
                  FEntry next = entry._next;
                  // 设置对象
                  int index = entry._hash % count;
                  entry._next = alloc[index];
                  alloc[index] = entry;
                  entry = next;
               }
            }
         }
         return alloc;
      }
   }
}

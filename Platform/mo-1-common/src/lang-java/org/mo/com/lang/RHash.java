package org.mo.com.lang;

import org.mo.com.lang.generic.SHashIntEntry;
import org.mo.com.lang.generic.SHashLongEntry;

//============================================================
// <T>哈希工具。</T>
//============================================================
public class RHash
{
   //============================================================
   // <T>计算哈希值。</T>
   //
   // @param hash 哈希值
   // @return 哈希值
   //============================================================
   public static int hash(int hash){
      hash += ~(hash << 9);
      hash ^= (hash >>> 14);
      hash += (hash << 4);
      hash ^= (hash >>> 10);
      return Math.abs(hash);
   }

   //============================================================
   // <T>计算哈希值。</T>
   //
   // @param hash 哈希值
   // @return 哈希值
   //============================================================
   public static long hash(long hash){
      hash += ~(hash << 9);
      hash ^= (hash >>> 14);
      hash += (hash << 4);
      hash ^= (hash >>> 10);
      return Math.abs((long)hash);
   }

   //============================================================
   // <T>计算哈希值。</T>
   //
   // @param item 对象
   // @return 哈希值
   //============================================================
   public static int hash(Object item){
      return hash(item.hashCode());
   }

   //============================================================
   // <T>更改哈希入口数组大小。</T>
   //
   // @param table 哈希入口数组
   // @param size 尺寸
   // @return 哈希值
   //============================================================
   public static SHashIntEntry[] resize(SHashIntEntry[] table,
                                        int size){
      int position = 0;
      int length = table.length;
      SHashIntEntry[] memory = new SHashIntEntry[size];
      for(int n = 0; n < length; n++){
         SHashIntEntry entry = table[n];
         while(null != entry){
            position = entry.hash % size;
            SHashIntEntry next = entry.next;
            entry.next = memory[position];
            memory[position] = entry;
            entry = next;
         }
      }
      return memory;
   }

   //============================================================
   // <T>更改哈希入口数组大小。</T>
   //
   // @param table 哈希入口数组
   // @param size 尺寸
   // @return 哈希值
   //============================================================
   public static SHashLongEntry[] resize(SHashLongEntry[] table,
                                         int size){
      int position = 0;
      int length = table.length;
      SHashLongEntry[] memory = new SHashLongEntry[size];
      for(int n = 0; n < length; n++){
         SHashLongEntry entry = table[n];
         while(null != entry){
            position = (int)entry.hash % size;
            SHashLongEntry next = entry.next;
            entry.next = memory[position];
            memory[position] = entry;
            entry = next;
         }
      }
      return memory;
   }

   //============================================================
   // <T>修正大于索引的所有索引位置。</T>
   //
   // @param table 哈希入口数组
   // @param index 索引位置
   //============================================================
   public static void removeFix(SHashIntEntry[] table,
                                int index){
      int length = table.length;
      for(int n = 0; n < length; n++){
         SHashIntEntry entry = table[n];
         while(null != entry){
            if(entry.index > index){
               entry.index--;
            }
            entry = entry.next;
         }
      }
   }

   //============================================================
   // <T>修正大于索引的所有索引位置。</T>
   //
   // @param table 哈希入口数组
   // @param index 索引位置
   //============================================================
   public static void removeFix(SHashLongEntry[] table,
                                int index){
      int length = table.length;
      for(int n = 0; n < length; n++){
         SHashLongEntry entry = table[n];
         while(null != entry){
            if(entry.index > index){
               entry.index--;
            }
            entry = entry.next;
         }
      }
   }
}

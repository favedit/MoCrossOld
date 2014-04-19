package org.mo.com.lang.generic;

//============================================================
//<T>哈希入口。</T>
//============================================================
public class SHashLongEntry
{
   // 哈希值
   public long hash;

   // 索引
   public int index;

   // 下个入口
   public SHashLongEntry next;

   //============================================================
   //<T>构造哈希入口。</T>
   //
   // @param hash 哈希值
   // @param index 索引
   // @param next 下个入口
   //============================================================
   public SHashLongEntry(long hashValue,
                         int indexValue,
                         SHashLongEntry nextValue){
      hash = hashValue;
      index = indexValue;
      next = nextValue;
   }
}

namespace MO.Common.Collection
{
   //============================================================
   // <T>哈希入口对象。</T>
   //============================================================
   public class FHashEntry
   {
      // 哈希值
      public int Hash;

      // 索引未知
      public int Index;

      // 下一个入口对象
      public FHashEntry Next;

      //============================================================
      // <T>构造哈希入口对象。</T>
      //
      // @param hash 哈希值
      // @param index 索引位置
      // @param next 下一个对象
      //============================================================
      public FHashEntry(int hash, int index, FHashEntry next) {
         Hash = hash;
         Index = index;
         Next = next;
      }

      //============================================================
      // <T>设置内容。</T>
      //
      // @param index 索引位置
      // @param next 下一个对象
      //============================================================
      public void Set(int index, FHashEntry next) {
         Index = index;
         Next = next;
      }
   }
}

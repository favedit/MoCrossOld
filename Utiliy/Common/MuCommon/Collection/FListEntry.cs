namespace MO.Common.Collection
{
   //============================================================
   // <T>链表入口对象。</T>
   //
   // @history MAOCY 140414
   //============================================================
   public class FListEntry<T>
   {
      // 前一个位置
      public FListEntry<T> prior;

      // 下一个位置
      public FListEntry<T> next;

      // 内容
      public T value;
   }
}

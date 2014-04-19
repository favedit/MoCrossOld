namespace MO.Common.Collection
{
   //============================================================
   // <T>字符串名称和内容的成对对象的递归器。</T>
   //============================================================
   public class FNamePairEnumerator<V> : FPairEnumerator<string, V>
   {
      //============================================================
      // <T>构造递归器。</T>
      //
      // @param names 名称列表
      // @param values 内容列表
      // @param offset 开始位置
      // @param count 总数
      //============================================================
      public FNamePairEnumerator(string[] names, V[] values, int offset, int count)
         : base(names, values, offset, count) {
      }

      //============================================================
      // <T>创建名称和内容的成对对象。</T>
      //
      // @return 成对对象
      //============================================================
      protected override FPair<string, V> InnerCreatePair() {
         return new FNamePair<V>();
      }
   }
}

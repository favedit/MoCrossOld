using System.Collections;
namespace MO.Common.Collection
{
   //============================================================
   // <T>整数字典。</T>
   //============================================================
   public class FIntDictionary<V> : FMap<int, V>
   {
      //============================================================
      // <T>构造整数哈希集合。</T>
      //============================================================
      public FIntDictionary() {
      }

      //============================================================
      // <T>构造整数哈希集合。</T>
      //
      // @param size 初始化大小
      //============================================================
      public FIntDictionary(int size)
         : base(size) {
      }

      #region IEnumerable implements

      //============================================================
      // <T>获得枚举器。</T>
      //============================================================
      public override IEnumerator GetEnumerator() {
         return new FIntPairEnumerator<V>(_names, _values, 0, _count);
      }

      #endregion
   }
}

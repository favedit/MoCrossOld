using System;
using MO.Common.Lang;

namespace MO.Common.Collection
{
   //============================================================
   // <T>对象集合。</T>
   //============================================================
   public class FObjectCollection<T> : FObjects<T>, IObjectCollection<T>
   {
      public bool _readonly;

      //============================================================
      // <T>获得是否只读。</T>
      //
      // @return 是否只读
      //============================================================
      public bool IsReadOnly {
         get { return _readonly; }
      }

      //============================================================
      // <T>获得是否只读。</T>
      //
      // @param items 对象集合
      // @param offset 索引位置
      //============================================================
      public void CopyTo(T[] items, int offset) {
         int length = Math.Min(items.Length - offset, _items.Length);
         Array.Copy(_items, 0, items, offset, length);
      }

      //============================================================
      // <T>追加对象到尾位置。</T>
      //
      // @param value 对象
      //============================================================
      public new void Add(T value) {
         Push(value);
      }
   }
}

using System.Collections;

namespace MO.Common.Collection
{
   //============================================================
   // <T>类型数组枚举器。</T>
   //============================================================
   public class FListEnumerator<T> : IEnumerator
   {
      // 首对象
      protected FListEntry<T> _first;

      // 当前对象
      protected FListEntry<T> _current;

      //============================================================
      // <T>构造类型数组枚举器。</T>
      //
      // @param items 数据内存
      // @param offset 开始位置
      // @param count 数据长度
      //============================================================
      public FListEnumerator(FListEntry<T> first) {
         _first = first;
         _current = null;
      }

      //============================================================
      // <T>移动到下一个位置。</T>
      //
      // @return 是否移动成功
      //============================================================
      public bool MoveNext() {
         if(null == _current) {
            _current = _first;
         } else {
            _current = _current.next;
         }
         return (null != _current);
      }

      //============================================================
      // <T>获得当前位置的数据内容。</T>
      //
      // @return 数据内容
      //============================================================
      public object Current {
         get { return _current.value; }
      }

      //============================================================
      // <T>重置枚举器。</T>
      //============================================================
      public void Reset() {
         _current = null;
      }
   }
}

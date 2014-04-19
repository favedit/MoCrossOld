using System.Collections.Generic;
using Collections = System.Collections;

namespace MO.Common.Lang
{
   //============================================================
   // <T>对象集合枚举器。</T>
   //============================================================
   public class FObjectsEnumerator<T> : IEnumerator<T>
   {
      // 数据内存
      protected T[] _items;

      // 开始位置
      protected int _offset;

      // 数据长度
      protected int _count;

      // 当前位置
      protected int _position;

      //============================================================
      // <T>构造对象数组枚举器。</T>
      //
      // @param items 对象集合
      // @param offset 开始位置
      // @param count 数据长度
      //============================================================
      public FObjectsEnumerator(T[] items, int offset, int count) {
         _items = items;
         _offset = offset - 1;
         _count = count;
         _position = _offset;
      }

      //============================================================
      // <T>获得当前位置的对象。</T>
      //
      // @return 对象
      //============================================================
      public T Current {
         get { return _items[_position]; }
      }

      //============================================================
      // <T>获得当前位置的对象。</T>
      //
      // @return 对象
      //============================================================
      object Collections.IEnumerator.Current {
         get { return _items[_position]; }
      }

      //============================================================
      // <T>移动到下一个位置。</T>
      //
      // @return 是否移动成功
      //============================================================
      public bool MoveNext() {
         if(_position < _count - 1) {
            _position++;
            return true;
         }
         return false;
      }

      //============================================================
      // <T>重置枚举器。</T>
      //============================================================
      public void Reset() {
         _position = _offset;
      }

      //============================================================
      // <T>释放对象。</T>
      //============================================================
      public void Dispose() {
      }
   }
}

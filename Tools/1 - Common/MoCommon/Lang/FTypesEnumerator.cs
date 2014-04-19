using System.Collections.Generic;
using Collections = System.Collections;

namespace MO.Common.Lang
{
   //============================================================
   // <T>类型集合枚举器。</T>
   //============================================================
   public class FTypesEnumerator<T> : IEnumerator<T>
   {
      // 数据内存
      protected T[] _memory;

      // 开始位置
      protected int _offset;

      // 数据长度
      protected int _count;

      // 当前位置
      protected int _position;

      //============================================================
      // <T>构造类型数组枚举器。</T>
      //
      // @param memory 类型集合
      // @param offset 开始位置
      // @param count 数据长度
      //============================================================
      public FTypesEnumerator(T[] memory, int offset, int count) {
         _memory = memory;
         _offset = offset - 1;
         _count = count;
         _position = _offset;
      }

      //============================================================
      // <T>获得当前位置的类型。</T>
      //
      // @return 对象
      //============================================================
      public T Current {
         get { return _memory[_position]; }
      }

      //============================================================
      // <T>获得当前位置的类型。</T>
      //
      // @return 对象
      //============================================================
      object Collections.IEnumerator.Current {
         get { return _memory[_position]; }
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

using System.Collections;

namespace MO.Common.Collection
{
   //============================================================
   // <T>名称和内容的成对对象的递归器。</T>
   //============================================================
   public class FPairEnumerator<N, V> : IEnumerator
   {
      // 值对对象
      protected FPair<N, V> _pair;

      // 开始位置
      protected int _offset;

      // 当前位置
      protected int _position;

      // 总数
      protected int _count;

      // 名称列表
      protected N[] _names;

      // 内容列表
      protected V[] _values;

      //============================================================
      // <T>构造递归器。</T>
      //
      // @param names 名称列表
      // @param values 内容列表
      // @param offset 开始位置
      // @param count 总数
      //============================================================
      public FPairEnumerator(N[] names, V[] values, int offset, int count) {
         _names = names;
         _values = values;
         _position = _offset = offset - 1;
         _count = count;
         _pair = InnerCreatePair();
         _pair._position = 0;
         _pair._count = count;
      }

      //============================================================
      // <T>创建名称和内容的成对对象。</T>
      //
      // @return 成对对象
      //============================================================
      protected virtual FPair<N, V> InnerCreatePair() {
         return new FPair<N, V>();
      }

      //============================================================
      // <T>移动到下一个位置。</T>
      //
      // @return 是否能够移动
      //============================================================
      public bool MoveNext() {
         if(_position < _count - 1) {
            _position++;
            return true;
         }
         return false;
      }

      //============================================================
      // <T>获得当前的值对对象。</T>
      //
      // @return 值对对象
      //============================================================
      public object Current {
         get {
            _pair.Set(_names[_position], _values[_position], _position);
            return _pair;
         }
      }

      //============================================================
      // <T>移动到下一个位置。</T>
      //
      // @return 是否能够移动
      //============================================================
      public void Reset() {
         _position = _offset;
      }
   }
}

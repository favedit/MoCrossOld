using MO.Common.Lang;

namespace MO.Common.IO
{
   //============================================================
   // <T>数组路径类型。</T>
   //============================================================
   public class FArrayInput<T> : FTypes<T>
   {
      private int _position = 0;

      private T[] _skips;

      public FArrayInput() {
      }

      public FArrayInput(T[] data) {
         base.Append(data);
      }

      public FArrayInput(T[] data, int offset, int length) {
         base.Append(data, offset, length);
      }

      public T[] Skips {
         get { return _skips; }
         set { _skips = value; }
      }

      public bool NextIsSkip() {
         if (_skips != null) {
            T next = Get(_position + 1);
            foreach (T skip in _skips) {
               if (skip.Equals(next)) {
                  return true;
               }
            }
         }
         return false;
      }

      public void DoSkips() {
         if (_skips != null) {
            while (NextIsSkip()) {
               _position++;
            }
         }
      }

      public bool HasNext {
         get {
            DoSkips();
            return _position < _length;
         }
      }

      public T Read() {
         DoSkips();
         return (_position < _length) ? _memory[_position++] : default(T);
      }

      public T Test(int index) {
         return Get(_position + index);
      }

      public void Skip(int index) {
         _position += index;
      }

      public void SetSkips(params T[] skips) {
         _skips = skips;
      }

      public void Close() {
         Clear();
      }

      public override void Dispose() {
         Close();
      }
   }
}

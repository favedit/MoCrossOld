using MO.Common.Collection;
using System;

namespace MO.Common.IO
{
   public class FBytePipe : FBytes
   {
      // 管道头
      protected int _first = 0;

      // 管道尾
      protected int _last = 0;

      //============================================================
      // <T>构造字节管道。</T>
      //============================================================
      public FBytePipe() {
      }

      //============================================================
      // <T>构造字节管道。</T>
      //
      // @param capacity 初始化大小
      //============================================================
      public FBytePipe(int capacity)
         : base(capacity) {
      }

      //============================================================
      // <T>获得管道中数据长度。</T>
      //============================================================
      public int DataLength {
         get {
            if (_first < _last) {
               return _last - _first;
            } else if (_first > _last) {
               return _memory.Length - _first + _last;
            }
            return 0;
         }
      }

      //============================================================
      // <T>将指定长度的数据保存到指定数组的指定位置。</T>
      //
      // @param memory 目标数组 
      // @param offset 偏移量
      // @param length 要保存的字节数
      // @return 实际保存的数组长度
      //============================================================
      public int Test(byte[] memory, int offset, int length) {
         if (_first < _last) {
            if (length <= _last - _first) {
               Array.Copy(_memory, _first, memory, offset, length);
               return length;
            } else {
               Array.Copy(_memory, _first, memory, offset, _last - _first);
               return _last - _first;
            }
         } else if (_first > _last) {
            if (length <= _memory.Length - _first + _last) {
               if (length <= _memory.Length - _first) {
                  Array.Copy(_memory, _first, memory, offset, length);
                  return length;
               } else {
                  Array.Copy(_memory, _first, memory, offset, _memory.Length - _first);
                  Array.Copy(_memory, 0, memory, offset + _memory.Length - _first, length - (_memory.Length - _first));
                  return length;
               }
            } else {
               Array.Copy(_memory, _first, memory, offset, _memory.Length - _first);
               Array.Copy(_memory, 0, memory, offset + _memory.Length - _first, _last);
               return _memory.Length - _first + _last;
            }
         }
         return 0;
      }

      //============================================================
      // <T>将指定长度的数据读取到指定数组的指定位置，并将管道头移到
      // 已读取数据的后面。</T>
      //
      // @param memory 目标数组 
      // @param offset 偏移量
      // @param length 要保存的字节数
      //============================================================
      public bool Read(byte[] memory, int offset, int length) {
         if (_first < _last) {
            if (length <= _last - _first) {
               Array.Copy(_memory, _first, memory, offset, length);
               if (null != _memory) {
                  _first += length;
               }
               return true;
            } else {
               return false;
            }
         } else if (_first > _last) {
            if (length <= _memory.Length - _first + _last) {
               if (length <= _memory.Length - _first) {
                  Array.Copy(_memory, _first, memory, offset, length);
                  _first += length;
                  return true;
               } else {
                  Array.Copy(_memory, _first, memory, offset, _memory.Length - _first);
                  Array.Copy(_memory, 0, memory, offset + _memory.Length - _first, length - (_memory.Length - _first));
                  _first = length - (_memory.Length - _first);
                  return true;
               }
            }
         }
         return false;
      }

      //============================================================
      // <T>将指定数组中指定位置和长度的数据写入管道，并把管道尾移到
      // 写入数据的后面。</T>
      //
      // @param memory 目标数组 
      // @param offset 偏移量
      // @param length 要保存的字节数
      //============================================================
      public bool Write(byte[] memory, int offset, int length) {
         if (0 == memory.Length || 0 == length) {
            return false;
         }
         if (_first < _last) {
            if (length >= _memory.Length - _last + _first) {
               return false;
            } else {
               if (length <= _memory.Length - _last) {
                  Array.Copy(memory, offset, _memory, _last, length);
                  _last += length;
                  return true;
               } else {
                  Array.Copy(memory, offset, _memory, _last, _memory.Length - _last);
                  Array.Copy(memory, offset + _memory.Length - _last, _memory, 0, length - (_memory.Length - _last));
                  _last = length - (_memory.Length - _last);
                  return true;
               }
            }
         } else if (_first > _last) {
            if (length >= _first - _last) {
               return false;
            } else {
               Array.Copy(memory, offset, _memory, _last, length);
               _last += length;
               return true;
            }
         } else {
            if (length >= _memory.Length) {
               return false;
            } else {
               if (length <= _memory.Length - _last) {
                  Array.Copy(memory, offset, _memory, _last, length);
                  _last += length;
                  return true;
               } else {
                  Array.Copy(memory, offset, _memory, _last, _memory.Length - _last);
                  Array.Copy(memory, offset + _memory.Length - _last, _memory, 0, length - (_memory.Length - _last));
                  _last = length - (_memory.Length - _last);
                  return true;
               }
            }
         }
      }

      //============================================================
      // <T>清除所有数据内容。</T>
      // <P>不清空数据，只是重设长度和位置。</P>
      //============================================================
      public override void Clear() {
         base.Clear();
         _first = 0;
         _last = 0;
      }

      //============================================================
      // <T>清除所有数据内容。</T>
      //============================================================
      public override void Reset() {
         base.Reset();
         _first = 0;
         _last = 0;
      }
   }
}

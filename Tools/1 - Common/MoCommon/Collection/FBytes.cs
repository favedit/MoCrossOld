using System;
using System.Text;
using MO.Common.Lang;

namespace MO.Common.Collection
{
   //============================================================
   // <T>字节数组。</T>
   //============================================================
   public class FBytes : FTypes<byte>
   {
      //============================================================
      // <T>构造字节数组。</T>
      //============================================================
      public FBytes() {
      }

      //============================================================
      // <T>构造字节数组。</T>
      //
      // @param capacity 初始化大小
      //============================================================
      public FBytes(int capacity)
         : base(capacity) {
      }

      //============================================================
      // <T>获得指定位置的一个8位数字。</T>
      //
      // @param offset 开始位置
      // @return 8位数字
      //============================================================
      public sbyte GetInt8(int offset) {
         return (sbyte)_memory[offset];
      }

      //============================================================
      // <T>获得指定位置的一个16位数字。</T>
      //
      // @param offset 开始位置
      // @return 16位数字
      //============================================================
      public short GetInt16(int offset) {
         return BitConverter.ToInt16(_memory, offset);
      }

      //============================================================
      // <T>获得指定位置的一个32位数字。</T>
      //
      // @param offset 开始位置
      // @return 16位数字
      //============================================================
      public int GetInt32(int offset) {
         return BitConverter.ToInt32(_memory, offset);
      }

      //============================================================
      // <T>获得指定位置的一个64位数字。</T>
      //
      // @param offset 开始位置
      // @return 64位数字
      //============================================================
      public long GetInt64(int offset) {
         return BitConverter.ToInt64(_memory, offset);
      }

      //============================================================
      // <T>获得指定位置的一个8位非负数字。</T>
      //
      // @param offset 开始位置
      // @return 8位非负数字
      //============================================================
      public byte GetUint8(int offset) {
         return _memory[offset];
      }

      //============================================================
      // <T>获得指定位置的一个16位非负数字。</T>
      //
      // @param offset 开始位置
      // @return 16位非负数字
      //============================================================
      public ushort GetUint16(int offset) {
         return BitConverter.ToUInt16(_memory, offset);
      }

      //============================================================
      // <T>获得指定位置的一个32位非负数字。</T>
      //
      // @param offset 开始位置
      // @return 32位非负数字
      //============================================================
      public uint GetUint32(int offset) {
         return BitConverter.ToUInt32(_memory, offset);
      }

      //============================================================
      // <T>获得指定位置的一个64位非负数字。</T>
      //
      // @param offset 开始位置
      // @return 64位非负数字
      //============================================================
      public ulong GetUint64(int offset) {
          return BitConverter.ToUInt64(_memory, offset);
      }

      //============================================================
      // <T>获得指定位置的一个结构体。</T>
      //
      // @param offset 开始位置
      // @param strcutType 结构体类型
      // @return 结构体
      //============================================================
      public object GetStruct(int offset, Type strcutType) {
         return RStruct.BytesToStruct(_memory, offset, strcutType);
      }

      //============================================================
      // <T>获得指定位置的一个ASCII字符串。</T>
      //
      // @param offset 开始位置
      // @return ASCII字符串
      //============================================================
      public string GetString(int offset) {
         for(int n = offset; n < _length; n++) {
            if(0 == _memory[n]) {
               return Encoding.ASCII.GetString(_memory, offset, n - offset);
            }
         }
         return null;
      }

      //============================================================
      // <T>转换为可以显示的字符串。</T>
      // <P>非可见字符全部用[.]替换。</P>
      //
      // @return 字符串
      //============================================================
      public virtual string ToDisplay() {
         StringBuilder result = new StringBuilder();
         for(int n = 0; n < _length; n++) {
            char value = (char)_memory[n];
            if((value >= ' ') && (value <= 'z')) {
               result.Append(value);
            } else {
               result.Append('.');
            }
         }
         return result.ToString();
      }

      //============================================================
      // <T>转换为字符串。</T>
      //
      // @return 字符串
      //============================================================
      public override string ToString() {
         StringBuilder result = new StringBuilder();
         for(int n = 0; n < _length; n++) {
            if(n > 0) {
               result.Append(' ');
            }
            result.Append(_memory[n].ToString("X2"));
         }
         return result.ToString();
      }
   }
}

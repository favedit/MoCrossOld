using System;
using System.Text;
using MO.Common.Collection;

namespace MO.Common.IO
{
   //============================================================
   // <T>字节数据流。</T>
   //
   // @class FByteStream
   //============================================================
   public class FByteStream : FBytes, IInput, IOutput
   {
      // 指定位置
      protected int _position = 0;

      //============================================================
      // <T>构造字节数据流。</T>
      //============================================================
      public FByteStream() {
      }

      //============================================================
      // <T>构造字节数据流。</T>
      //
      // @param capacity 初始化大小
      //============================================================
      public FByteStream(int capacity)
         : base(capacity) {
      }

      //============================================================
      // <T>设置和获取指定位置的数值。</T>
      //============================================================
      public int Position {
         get { return _position; }
         set { _position = value; }
      }

      //============================================================
      // <T>读取一个布尔值。</T>
      //
      // @return 布尔值
      //============================================================
      public bool ReadBool() {
         return (_memory[_position++] > 0) ? true : false;
      }

      //============================================================
      // <T>读取一个有符号8位整数。</T>
      //
      // @return 有符号8位整数
      //============================================================
      public sbyte ReadInt8() {
         return (sbyte)_memory[_position++];
      }

      //============================================================
      // <T>读取一个有符号16位整数。</T>
      //
      // @return 有符号16位整数
      //============================================================
      public short ReadInt16() {
         short result = BitConverter.ToInt16(_memory, _position);
         _position += sizeof(short);
         return result;
      }

      //============================================================
      // <T>读取一个有符号32位整数。</T>
      //
      // @return 有符号32位整数
      //============================================================
      public int ReadInt32() {
         int result = BitConverter.ToInt32(_memory, _position);
         _position += sizeof(int);
         return result;
      }

      //============================================================
      // <T>读取一个有符号64位整数。</T>
      //
      // @return 有符号64位整数
      //============================================================
      public long ReadInt64() {
         long result = BitConverter.ToInt64(_memory, _position);
         _position += sizeof(long);
         return result;
      }

      //============================================================
      // <T>读取一个无符号8位整数。</T>
      //
      // @return 无符号8位整数
      //============================================================
      public byte ReadUint8() {
         return _memory[_position++];
      }

      //============================================================
      // <T>读取一个无符号16位整数。</T>
      //
      // @return 无符号16位整数
      //============================================================
      public ushort ReadUint16() {
         ushort result = BitConverter.ToUInt16(_memory, _position);
         _position += sizeof(ushort);
         return result;
      }

      //============================================================
      // <T>读取一个无符号32位整数。</T>
      //
      // @return 无符号32位整数
      //============================================================
      public uint ReadUint32() {
         uint result = BitConverter.ToUInt32(_memory, _position);
         _position += sizeof(uint);
         return result;
      }

      //============================================================
      // <T>读取一个无符号64位整数。</T>
      //
      // @return 无符号64位整数
      //============================================================
      public ulong ReadUint64() {
         ulong result = BitConverter.ToUInt64(_memory, _position);
         _position += sizeof(ulong);
         return result;
      }

      //============================================================
      // <T>读取一个单精度浮点数。</T>
      //
      // @return 单精度浮点数
      //============================================================
      public float ReadFloat() {
         float result = BitConverter.ToSingle(_memory, _position);
         _position += sizeof(float);
         return result;
      }

      //============================================================
      // <T>读取一个双精度浮点数。</T>
      //
      // @return 双精度浮点数
      //============================================================
      public double ReadDouble() {
         double result = BitConverter.ToDouble(_memory, _position);
         _position += sizeof(double);
         return result;
      }

      //============================================================
      // <T>读取一个字符串。</T>
      //
      // @return 字符串
      //============================================================
      public string ReadString() {
         string value = string.Empty;
         int length = (int)ReadUint16();
         if (length > 0) {
            value = Encoding.UTF8.GetString(_memory, _position, length);
         }
         _position += length;
         return value;
      }

      //============================================================
      // <T>读取一个串字符。</T>
      //
      // @return 字符串
      //============================================================
      public string ReadStringA16() {
         string value = string.Empty;
         int length = (int)ReadUint16();
         if (length > 0) {
            value = Encoding.ASCII.GetString(_memory, _position, length);
         }
         _position += length;
         return value;
      }

      //============================================================
      // <T>读取一个串字符。</T>
      //
      // @return 字符串
      //============================================================
      public string ReadStringA32() {
         string value = string.Empty;
         int length = (int)ReadUint32();
         if (length > 0) {
            value = Encoding.ASCII.GetString(_memory, _position, length);
         }
         _position += length;
         return value;
      }

      //============================================================
      // <T>读取一个UTF-8的字符串。</T>
      //
      // @return UTF-8字符串
      //============================================================
      public string ReadUTFString() {
         string value = string.Empty;
         int length = (int)ReadUint16();
         if (length > 0) {
            value = Encoding.UTF8.GetString(_memory, _position, length);
         }
         _position += length;
         return value;
      }

      //============================================================
      // <T>读取一个宽字符串。</T>
      //
      // @return 字符串
      //============================================================
      public string ReadWideString() {
         string value = string.Empty;
         int length = (int)ReadUint16();
         if (length > 0) {
            char[] chars = new char[length];
            for (int n = 0; n < length; n++) {
               chars[n] = (char)ReadUint16();
            }
            value = new string(chars);
         }
         return value;
      }

      //============================================================
      // <T>读取一段数据。</T>
      //
      // @params bytes 目标字节流
      // @params offset 目标字节流的写入位置
      // @params length 读取的长度
      //============================================================
      public int ReadBytes(byte[] bytes, int offset, int length) {
         Array.Copy(_memory, _position, bytes, offset, length);
         return length;
      }

      //============================================================
      // <T>读取流中所有数据。</T>
      //
      // @return 字节流
      //============================================================
      public byte[] ReadAll() {
         int length = _length - _position;
         byte[] bytes = new byte[length];
         Array.Copy(_memory, _position, bytes, 0, length);
         return bytes;
      }

      //============================================================
      // <T>写入一个布尔值。</T>
      //
      // @params value 布尔值
      //============================================================
      public void WriteBool(bool value) {
         EnsureSize(_position + 1);
         _memory[_position] = (byte)(value ? 1 : 0);
         _position++;
         if (_position > _length) {
            _length = _position;
         }
      }

      //============================================================
      // <T>写入一个有符号8位整数。</T>
      //
      // @params value 有符号8位整数
      //============================================================
      public void WriteInt8(sbyte value) {
         EnsureSize(_position + 1);
         Byte[] bytes = BitConverter.GetBytes(value);
         _memory[_position] = bytes[0];
         _position++;
         if (_position > _length) {
            _length = _position;
         }
      }

      //============================================================
      // <T>写入一个有符号16位整数。</T>
      //
      // @params value 有符号16位整数
      //============================================================
      public void WriteInt16(short value) {
         EnsureSize(_position + 2);
         Byte[] bytes = BitConverter.GetBytes(value);
         _memory[_position] = bytes[0];
         _memory[_position + 1] = bytes[1];
         _position += 2;
         if (_position > _length) {
            _length = _position;
         }
      }

      //============================================================
      // <T>写入一个有符号32位整数。</T>
      //
      // @params value 有符号32位整数
      //============================================================
      public void WriteInt32(int value) {
         EnsureSize(_position + 4);
         Byte[] bytes = BitConverter.GetBytes(value);
         _memory[_position] = bytes[0];
         _memory[_position + 1] = bytes[1];
         _memory[_position + 2] = bytes[2];
         _memory[_position + 3] = bytes[3];
         _position += 4;
         if (_position > _length) {
            _length = _position;
         }
      }

      //============================================================
      // <T>写入一个有符号64位整数。</T>
      //
      // @params value 有符号64位整数
      //============================================================
      public void WriteInt64(long value) {
         EnsureSize(_position + 8);
         Byte[] bytes = BitConverter.GetBytes(value);
         Array.Copy(bytes, 0, _memory, _position, bytes.Length);
         _position += 8;
         if (_position > _length) {
            _length = _position;
         }
      }

      //============================================================
      // <T>写入一个无符号8位整数。</T>
      //
      // @params value 无符号8位整数
      //============================================================
      public void WriteUint8(byte value) {
         EnsureSize(_position + 1);
         _memory[_position] = value;
         _position++;
         if (_position > _length) {
            _length = _position;
         }
      }

      //============================================================
      // <T>写入一个无符号16位整数。</T>
      //
      // @params value 无符号16位整数
      //============================================================
      public void WriteUint16(ushort value) {
         EnsureSize(_position + 2);
         Byte[] bytes = BitConverter.GetBytes(value);
         _memory[_position] = bytes[0];
         _memory[_position + 1] = bytes[1];
         _position += 2;
         if (_position > _length) {
            _length = _position;
         }
      }

      //============================================================
      // <T>写入一个无符号32位整数。</T>
      //
      // @params value 无符号32位整数
      //============================================================
      public void WriteUint32(uint value) {
         EnsureSize(_position + 4);
         Byte[] bytes = BitConverter.GetBytes(value);
         _memory[_position] = bytes[0];
         _memory[_position + 1] = bytes[1];
         _memory[_position + 2] = bytes[2];
         _memory[_position + 3] = bytes[3];
         _position += 4;
         if (_position > _length) {
            _length = _position;
         }
      }

      //============================================================
      // <T>写入一个无符号64位整数。</T>
      //
      // @params value 无符号64位整数
      //============================================================
      public void WriteUint64(ulong value) {
         EnsureSize(_position + 8);
         Byte[] bytes = BitConverter.GetBytes(value);
         Array.Copy(bytes, 0, _memory, _position, bytes.Length);
         _position += 8;
         if (_position > _length) {
            _length = _position;
         }
      }

      //============================================================
      // <T>写入一个单精度浮点数。</T>
      //
      // @params value 单精度浮点数
      //============================================================
      public void WriteFloat(float value) {
         EnsureSize(_position + 4);
         Byte[] bytes = BitConverter.GetBytes(value);
         _memory[_position] = bytes[0];
         _memory[_position + 1] = bytes[1];
         _memory[_position + 2] = bytes[2];
         _memory[_position + 3] = bytes[3];
         _position += 4;
         if (_position > _length) {
            _length = _position;
         }
      }

      //============================================================
      // <T>写入一个双精度浮点数。</T>
      //
      // @params value 双精度浮点数
      //============================================================
      public void WriteDouble(double value) {
         EnsureSize(_position + 8);
         Byte[] bytes = BitConverter.GetBytes(value);
         Array.Copy(bytes, 0, _memory, _position, bytes.Length);
         _position += 8;
         if (_position > _length) {
            _length = _position;
         }
      }

      //============================================================
      // <T>写入一个字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public void WriteString(string value) {
         WriteUint8(1);
         if (value == null) {
            WriteUint16(0);
         } else if (value.Length == 0) {
            WriteUint16(0);
         } else {
            Byte[] bytes = Encoding.ASCII.GetBytes(value);
            int length = bytes.Length;
            WriteUint16((ushort)length);
            EnsureSize(_position + length);
            Array.Copy(bytes, 0, _memory, _position, bytes.Length);
            _position += bytes.Length;
            if (_position > _length) {
               _length = _position;
            }
         }
      }

      //============================================================
      // <T>写入一个字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public void WriteAnsiString(string value) {
         if(value == null) {
            WriteUint16(0);
         } else if(value.Length == 0) {
            WriteUint16(0);
         } else {
            Byte[] bytes = Encoding.ASCII.GetBytes(value);
            int length = bytes.Length;
            WriteUint16((ushort)length);
            EnsureSize(_position + length);
            Array.Copy(bytes, 0, _memory, _position, bytes.Length);
            _position += bytes.Length;
            if(_position > _length) {
               _length = _position;
            }
         }
      }

      //============================================================
      // <T>写入一个UTF-8字符串。</T>
      //
      // @param value UTF-8字符串
      //============================================================
      public void WriteUTFString(string value) {
         WriteUint8(2);
         if (value == null) {
            WriteUint16(0);
         } else if (value.Length == 0) {
            WriteUint16(0);
         } else {
            Byte[] bytes = Encoding.UTF8.GetBytes(value);
            int length = bytes.Length;
            WriteUint16((ushort)length);
            EnsureSize(_position + length);
            Array.Copy(bytes, 0, _memory, _position, bytes.Length);
            _position += bytes.Length;
            if (_position > _length) {
               _length = _position;
            }
         }
      }

      //============================================================
      // <T>写入一个宽字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public void WriteWideString(string value) {
         WriteUint8(3);
         if (value == null) {
            WriteUint16(0);
         } else if (value.Length == 0) {
            WriteUint16(0);
         } else {
            char[] chars = value.ToCharArray();
            int byteLength = 4 * chars.Length;
            WriteUint16((ushort)chars.Length);
            EnsureSize(_position + byteLength);
            foreach (char ch in chars) {
               WriteUint32(ch);
            }
            if (_position > _length) {
               _length = _position;
            }
         }
      }

      //============================================================
      // <T>写入一段数据。</T>
      //
      // @params bytes 字节流
      // @params offset 偏移量
      // @params length 写入长度
      //============================================================
      public int WriteBytes(byte[] bytes, int offset, int length) {
         EnsureSize(_position + length);
         Array.Copy(bytes, offset, _memory, _position, length);
         _position += length;
         if (_position > _length) {
            _length = _position;
         }
         return length;
      }

      //============================================================
      // <T>重设数据开始位置和长度。</T>
      //============================================================
      public void Flip() {
         _length = _position;
         _position = 0;
      }

      //============================================================
      // <T>清除所有数据内容。</T>
      //============================================================
      public override void Reset() {
         base.Reset();
         _position = 0;
      }

      //============================================================
      // <T>清除所有数据内容。</T>
      // <P>不清空数据，只是重设长度和位置。</P>
      //============================================================
      public override void Clear() {
         base.Clear();
         _position = 0;
      }
   }
}

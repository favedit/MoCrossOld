using System;
using System.IO;
using MO.Common.Lang;

namespace MO.Common.IO
{
   //============================================================
   // <T>字符读取器。</T>
   //============================================================
   public class FCharReader : IDisposable
   {
      protected StreamReader _reader;

      protected int _position = 0;

      protected int _count = 0;

      protected string _skips = "\r";

      protected char[] _buffer;

      public const int DEFAULT_SIZE = 16;

      public FCharReader(StreamReader reader)
         : this(reader, DEFAULT_SIZE) {
      }

      public FCharReader(StreamReader reader, int length) {
         _reader = reader;
         _buffer = new char[length];
         ReadBuffer();
      }

      public StreamReader Reader {
         get { return _reader; }
      }

      protected void ReadBuffer() {
         _count -= _position;
         if (_count > 0) {
            Array.Copy(_buffer, _position, _buffer, 0, _count);
         }
         _count += _reader.Read(_buffer, _count, _buffer.Length - _count);
         int offset = 0;
         for (int n = 0; n < _count; n++) {
            if (_skips.IndexOf(_buffer[n]) != -1) {
               continue;
            }
            _buffer[offset++] = _buffer[n];
         }
         _count = offset;
         _position = 0;
      }

      public bool HasNext() {
         if (_position >= _count) {
            ReadBuffer();
         }
         return _position < _count;
      }

      public char Read() {
         if (_position >= _count) {
            ReadBuffer();
         }
         return (_position < _count) ? _buffer[_position++] : default(char);
      }

      public string ReadLine() {
         FString line = new FString();
         while (HasNext()) {
            char ch = Read();
            if (ch == '\r') {
               continue;
            } else if (ch == '\r') {
               break;
            }
            line.Append(ch);
         }
         return line.ToString();
      }

      public char Get(int index) {
         if (_position + index >= _count) {
            ReadBuffer();
         }
         if (_position < _count) {
            return _buffer[_position + index - 1];
         }
         return default(char);
      }

      public void Skip(int index) {
         if (_position + index >= _count) {
            ReadBuffer();
         }
         if (_position < _count) {
            _position++;
         }
      }

      public void Close() {
         if (_reader != null) {
            _reader.Close();
         }
      }

      public void Dispose() {
         Close();
      }
   }
}

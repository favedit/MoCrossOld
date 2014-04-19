using System;
using System.Net.Sockets;
using System.Text;
using MO.Common.IO;

namespace MO.Common.Net.Sockets
{
   //============================================================
   // <T>Socket输出数据流。</T>
   //============================================================
   public class FSocketOutput
   {
      private FSocket _socket;

      //============================================================
      // <T>构造输出数据流。</T>
      //============================================================
      public FSocketOutput() {
      }

      //============================================================
      // <T>构造输出数据流并绑定Socket。</T>
      //
      // @param socket 指定的Socket
      //============================================================
      public FSocketOutput(FSocket socket) {
         _socket = socket;
      }

      //============================================================
      public FSocket Socket {
         get { return _socket; }
         set { _socket = value; }
      }

      //============================================================
      // <T>将一个整数写入输出流中。</T>
      //
      // @param value 写入的数据
      // @return 已发送到Socket的字节数
      //============================================================
      public int Write(int value) {
         byte[] bytes = BitConverter.GetBytes(value);
         return _socket.NativeSocket.Send(bytes, SocketFlags.None);
      }

      //============================================================
      // <T>将一个字符串写入输出流中。</T>
      //
      // @param value 写入的数据
      // @return 已发送到Socket的字节数
      //============================================================
      public int Write(string value) {
         return Write(value, Encoding.ASCII);
      }

      //============================================================
      // <T>将一个字符串按照指定字符编码格式写入输出流中。</T>
      //
      // @param value 写入的数据
      // @param encoding 字符编码
      // @return 已发送到Socket的字节数
      //============================================================
      public int Write(string value, Encoding encoding) {
         byte[] bytes = encoding.GetBytes(value);
         return _socket.NativeSocket.Send(bytes, SocketFlags.None);
      }

      //============================================================
      // <T>将一个字符串按照指定字符编码格式写入输出流中，
      // 并且在末尾加换行符。</T>
      //
      // @param value 写入的数据
      // @param encoding 字符编码
      // @return 已发送到Socket的字节数
      //============================================================
      public int WriteLine(string line, Encoding encoding) {
         byte[] bytes = encoding.GetBytes(line + RFile.LINE);
         return _socket.NativeSocket.Send(bytes, SocketFlags.None);
      }

      //============================================================
      // <T>将一个byte数组写入输出流中。</T>
      //
      // @param bytes 写入的数据
      // @return 已发送到Socket的字节数
      //============================================================
      public int Write(byte[] bytes) {
         return _socket.NativeSocket.Send(bytes, SocketFlags.None);
      }

      //============================================================
      // <T>将一个byte数组的指定部分写入输出流中。</T>
      //
      // @param bytes 写入的数据
      // @param offset 指定偏移量
      // @param count 要写入的字节数
      // @return 已发送到Socket的字节数
      //============================================================
      public int Write(byte[] bytes, int offset, int count) {
         return _socket.NativeSocket.Send(bytes, offset, count, SocketFlags.None);
      }
   }
}

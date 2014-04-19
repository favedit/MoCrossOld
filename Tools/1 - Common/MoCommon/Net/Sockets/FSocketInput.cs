using System;
using System.Net.Sockets;
using System.Text;
using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Common.Net.Sockets
{
   //============================================================
   // <T>Socket输入数据流。</T>
   //============================================================
   public class FSocketInput
   {
      private FSocket _socket;

      //============================================================
      // <T>构造输入数据流。</T>
      //============================================================
      public FSocketInput() {
      }

      //============================================================
      // <T>构造输入数据流并绑定Socket。</T>
      //
      // @param socket 指定的Socket
      //============================================================
      public FSocketInput(FSocket socket) {
         _socket = socket;
      }

      //============================================================
      public FSocket Socket {
         get { return _socket; }
         set { _socket = value; }
      }

      //============================================================
      // <T>从字节流中读取所有数据。</T>
      //
      // @return 保存所有数据的数组
      //============================================================
      public FBytes ReadAll() {
         FBytes bytes = new FBytes();
         int read = 0;
         do {
            bytes.EnsureSize(bytes.Length + RInt.SIZE_1K);
            read = _socket.NativeSocket.Receive(bytes.Memory, bytes.Length, RInt.SIZE_1K, SocketFlags.None);
            //bytes.Length += read;
         } while (read == RInt.SIZE_1K);
         return bytes;
      }

      //============================================================
      // <T>从字节流中读取指定长度的数据。</T>
      //
      // @param lenth 读取的字节数
      // @return 保存所有数据的数组
      //============================================================
      public FBytes ReadLength(int length) {
         FBytes bytes = new FBytes();
         ReadLength(bytes, length);
         return bytes;
      }

      //============================================================
      // <T>从字节流中读取指定长度的数据保存在指定数组中。</T>
      //
      // @param bytes 保存数据的数组
      // @param lenth 读取的字节数
      //============================================================
      public void ReadLength(FBytes bytes, int length) {
         int read = 0;
         int remain = length;
         int block = RInt.SIZE_4K;
         byte[] memory = new byte[block];
         while (remain > 0) {
            block = (remain > block) ? block : remain;
            read = _socket.NativeSocket.Receive(memory, 0, block, SocketFlags.None);
            bytes.Append(memory, 0, read);
            remain -= read;
         }
      }

      //============================================================
      // <T>读取一行数据，并转换为字符串返回。</T>
      //
      // @param code 字符编码
      // @return 转换后的字符串
      //============================================================
      public String ReadLine(Encoding code) {
         byte[] buf = new byte[1];
         FBytes bytes = new FBytes();
         while (true) {
            int read = _socket.NativeSocket.Receive(buf);
            if (read != 1) {
               break;
            }
            if (buf[0] == '\r') {
               continue;
            } else if (buf[0] == '\n') {
               break;
            }
            //bytes.Append(buf[0]);
         }
         return code.GetString(bytes.Memory, 0, bytes.Length);
      }

      //============================================================
      // <T>将数据读入指定字节数组，并返回数据长度。</T>
      //
      // @param bytes 目标数组
      // @return 数据长度
      //============================================================
      public int Read(byte[] bytes) {
         int readed = 0;
         int available = _socket.NativeSocket.Available;
         if (available > 0) {
            readed = _socket.NativeSocket.Receive(bytes);
         }
         return readed;
      }
   }
}

using System;
using System.Net.Sockets;
using System.Text;
using MS.Common.Collection;
using MS.Common.Lang;
using MS.Common.System;

namespace MS.Common.Net.Sockets
{
   //============================================================
   // <T>网络输入数据流。</T>
   //============================================================
   public class FSocketInput
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FSocketInput));

      // 读取限制
      protected int _readLimit = 4096;

      // 网络端口
      protected FSocket _socket;

      //============================================================
      // <T>构造网络输入数据流。</T>
      //============================================================
      public FSocketInput() {
      }

      //============================================================
      // <T>构造网络输入数据流。</T>
      //
      // @param socket 网络端口
      //============================================================
      public FSocketInput(FSocket socket) {
         _socket = socket;
      }

      //============================================================
      // <T>获得或设置网络端口。</T>
      //============================================================
      public FSocket Socket {
         get { return _socket; }
         set { _socket = value; }
      }

      //============================================================
      // <T>读取所有数据。</T>
      //
      // @return 保存所有数据的数组
      //============================================================
      public FBytes ReadAll() {
         FBytes bytes = new FBytes();
         byte[] buffer = new byte[_readLimit];
         while(true){
            int length = _socket.NativeSocket.Receive(buffer, 0, _readLimit, SocketFlags.None);
            if(length > 0) {
               bytes.Append(buffer, 0, length);
            }
            // 检查是否完成
            if(length != _readLimit) {
               break;
            }
         }
         return bytes;
      }

      //============================================================
      // <T>从字节流中读取所有数据。</T>
      //
      // @param data 输出
      // @return 读取长度
      //============================================================
      public int ReadAll(FBytes data) {
         int total = 0;
         byte[] buffer = new byte[_readLimit];
         try {
            while(true) {
               // 接收数据
               int length = _socket.NativeSocket.Receive(buffer, 0, _readLimit, SocketFlags.None);
               data.Append(buffer, 0, length);
               total += length;
               // 检查是否完成
               if(length != _readLimit) {
                  break;
               }
            }
         } catch(SocketException exception) {
             Console.WriteLine(exception.Message);
            total = -1;
         }
         return total;
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
         while(remain > 0) {
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
         while(true) {
            int read = _socket.NativeSocket.Receive(buf);
            if(read != 1) {
               break;
            }
            if(buf[0] == '\r') {
               continue;
            } else if(buf[0] == '\n') {
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
         if(available > 0) {
            readed = _socket.NativeSocket.Receive(bytes);
         }
         return readed;
      }
   }
}

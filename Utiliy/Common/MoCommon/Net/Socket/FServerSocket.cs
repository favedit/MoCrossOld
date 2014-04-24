using System.Net;
using System.Net.Sockets;
using System;

namespace MO.Common.Net.Sockets
{
   //============================================================
   // <T>端口服务。</T>
   //============================================================
   public class FServerSocket : FSocket
   {
      protected int _backlog = 64;

      //============================================================
      // <T>构造端口服务的实例。</T>
      //============================================================
      public FServerSocket() {
         _host = "127.0.0.1";
      }

      //============================================================
      // <T>构造端口服务的实例。</T>
      //
      // @param port 端口
      //============================================================
      public FServerSocket(int port) {
         _host = "127.0.0.1";
         _port = port;
      }

      //============================================================
      // <T>启动端口服务。</T>
      //============================================================
      public void Startup() {
         _socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
         //IPAddress ipAddress = IPAddress.Parse(_host);
         IPAddress ipAddress = IPAddress.Any;
         IPEndPoint ipPoint = new IPEndPoint(ipAddress, _port);
         _socket.Bind(ipPoint);
         // _socket.Blocking = false;
         _socket.Listen(_backlog);
         //BeginAccept(_socket, null);
      }

      //============================================================
      // <T>接收一个网络链接。</T>
      //============================================================
      public FSocket Accept() {
         FSocket socket = null;
         Socket nativeSocket = _socket.Accept();
         if (null != nativeSocket) {
            socket = new FSocket(nativeSocket);
         }
         return socket;
      }

      //============================================================
      private void BeginAccept(Socket listener, SocketAsyncEventArgs args) {
         if (null == args) {
            args = new SocketAsyncEventArgs();
            args.Completed += new EventHandler<SocketAsyncEventArgs>(EndAccept);
         }
         args.AcceptSocket = null;
         if (!listener.AcceptAsync(args)) {
            EndAccept(listener, args);
         }
      }

      //============================================================
      private void EndAccept(object sender, SocketAsyncEventArgs e) {
         if (e.SocketError == SocketError.OperationAborted) {
            e.Dispose();
            return;
         }
         Socket listener = (Socket)sender;
         Socket client = e.AcceptSocket;
         BeginAccept(listener, e);
         client.Close();
      }

      //============================================================
      // <T>关闭网络服务。</T>
      //============================================================
      public void Shutdown() {
         Disconnect();
      }
   }
}

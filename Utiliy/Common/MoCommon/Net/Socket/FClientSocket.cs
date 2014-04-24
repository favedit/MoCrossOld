using System.Net.Sockets;

namespace MO.Common.Net.Sockets
{
   public class FClientSocket : FSocket
   {
      public FClientSocket() {
         _socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
      }

      public FClientSocket(Socket socket) {
         _socket = socket;
      }

      public void Connect() {
         _socket.Connect(_host, _port);        
      }

      public void Connect(string host, int port) {
         _host = host;
         _port = port;
         Connect();
      }
   }
}

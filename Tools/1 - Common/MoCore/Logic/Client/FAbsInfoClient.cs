using MO.Common.Net.Sockets;

namespace MO.Core.Logic.Client {

   public abstract class FAbsInfoClient : IInfoClient {

      internal string _host;

      internal int _port;

      internal FSocket _socket = new FSocket();

      public FSocket Socket {
         get { return _socket; }
      }

      protected abstract void SendProtocol();

      public virtual void Connect(string host, int port) {
         _host = host;
         _port = port;
         //_socket.Connect(_host, _port);
         SendProtocol();
      }

      public FSocketInput Input {
         get { return _socket.Input; }
      }

      public FSocketOutput Output {
         get { return _socket.Output; }
      }

      public void Disconnect() {
         _socket.Disconnect();
      }

   }

}

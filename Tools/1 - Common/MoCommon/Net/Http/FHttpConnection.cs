using System;
using MO.Common.Net.Sockets;

namespace MObj.Net.Http {

   public class FHttpConnection : IDisposable {

      internal FUrl _url = new FUrl();

      private FHttpRequest _request;

      private FHttpResponse _response;

      private bool _useProxy = false;

      internal FClientSocket _socket = new FClientSocket();

      public FHttpConnection() {
         Construct();
      }

      public FHttpConnection(FUrl url) {
         _url = url;
         Construct();
         Connect();
      }

      protected void Construct() {
         _request = new FHttpRequest(this);
         _response = new FHttpResponse(this);
      }

      public FUrl Url {
         get { return _url; }
      }

      public bool UseProxy {
         get { return _useProxy; }
         set { _useProxy = value; }
      }

      public FHttpRequest Request {
         get { return _request; }
      }

      public FHttpResponse Response {
         get { return _response; }
      }

      public void Connect() {
         _socket.Connect(_url.Host, _url.Port);
      }

      public void Send() {
         _response.Send();
      }

      public void Receive() {
         _request.Receive();
      }

      public void SendReceive() {
         _response.Send();
         _request.Receive();
      }

      public void Disconnect() {
         _socket.Disconnect();
      }

      public void Dispose() {
         Disconnect();
      }
   }

}

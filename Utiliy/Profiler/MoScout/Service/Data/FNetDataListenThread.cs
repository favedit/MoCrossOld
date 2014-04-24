using MO.Common.Net.Sockets;
using MO.Common.System;

namespace MO.Scout.Service.Data
{
   public class FNetDataListenThread : FThread
   {
      internal FNetDataService _service;

      protected FServerSocket _serverSocket = new FServerSocket();

      protected int _interval = 10;

      public FNetDataListenThread() {
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public override void OnProcess() {
         _serverSocket.Port = 9999;
         _serverSocket.Startup();
         while (IsRunning) {
            FSocket socket = _serverSocket.Accept();
            if (socket != null) {
               _service.SocketPush(socket);
            }
            Sleep(_interval);
         }
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public void Shutdown() {
         _serverSocket.Disconnect();
         _thread.Abort();
      }
   }
}

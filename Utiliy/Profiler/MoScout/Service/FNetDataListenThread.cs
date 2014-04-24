using MO.Common.Net.Sockets;
using MO.Common.System;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MoScout.Service
{
   public class FNetDataListenThread : FThread
   {
      internal FNetDataService _service;

      protected FServerSocket _serverSocket = new FServerSocket();

      public FNetDataListenThread() { 
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public override void OnProcess() {
         _serverSocket.Port = 9999;
         _serverSocket.Startup();
         while (!IsStop) {
            FSocket socket = _serverSocket.Accept();
            if (socket != null) {
               _service.SocketPush(socket);
            }
            Sleep(10);
         }
      }
   }
}

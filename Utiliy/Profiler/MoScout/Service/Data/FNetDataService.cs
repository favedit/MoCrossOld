using MO.Common.Lang;
using MO.Common.Net.Sockets;
using MO.Common.System;
using MoScout.Core;

namespace MO.Scout.Service.Data
{
   //============================================================
   // <T>网络数据服务。</T>
   //============================================================
   public class FNetDataService : FConsole
   {
      // 服务线程
      internal FNetDataServiceThread _serviceThread = new FNetDataServiceThread();

      // 监听线程
      internal FNetDataListenThread _listenThread = new FNetDataListenThread();

      // 端口线程
      internal FObjects<FNetDataThread> _socketThreads = new FObjects<FNetDataThread>();

      //============================================================
      // <T>构造网络数据服务。</T>
      //============================================================
      public FNetDataService() {
      }

      //============================================================
      // <T>启动处理。</T>
      //============================================================
      public void Startup() {
         // 启动服务线程
         _serviceThread._service = this;
         _serviceThread.Start();
         // 启动监听线程
         _listenThread._service = this;
         _listenThread.Start();
      }

      //============================================================
      // <T>关闭处理。</T>
      //============================================================
      public void Shutdown() {
         // 关闭服务线程
         _serviceThread.Abort();
         // 关闭监听线程
         _listenThread.Shutdown();
      }

      //============================================================
      // <T>增加网络端口。</T>
      //============================================================
      public void SocketPush(FSocket socket) {
         FApplicationInfo info = RScoutManager.InfoConsole.CreateInfo();
         FNetDataThread socketThread = new FNetDataThread();
         socketThread._service = this;
         socketThread._applicationInfo = info;
         socketThread.Socket = socket;
         socketThread.Start();
         _socketThreads.Push(socketThread);
      }

      //============================================================
      // <T>移除端口线程。</T>
      //============================================================
      public void SocketThreadRemove(FNetDataThread thread) {
         _socketThreads.Remove(thread);
      }
   }
}

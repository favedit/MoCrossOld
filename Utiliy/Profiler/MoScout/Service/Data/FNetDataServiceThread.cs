using MO.Common.System;

namespace MO.Scout.Service.Data
{
   //============================================================
   // <T>网络数据服务线程。</T>
   //============================================================
   public class FNetDataServiceThread : FThread
   {
      // 服务对象
      internal FNetDataService _service;

      // 处理间隔
      internal int _interval = 10;

      //============================================================
      // <T>构造网络数据服务线程。</T>
      //============================================================
      public FNetDataServiceThread() { 
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public override void OnProcess() {
         while(IsRunning){
            foreach(FNetDataThread thread in _service._socketThreads) {
               thread.ProcessMessages();
            }
            Sleep(_interval);
         }
      }
   }
}

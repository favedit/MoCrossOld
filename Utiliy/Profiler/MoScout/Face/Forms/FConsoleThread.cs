using MO.Common.System;

namespace MO.Scout.Face.Forms
{
   //============================================================
   // <T>控制台线程。</T>
   //
   // @history MAOCY 140424
   //============================================================
   public class FConsoleThread : FThread
   {
      // 控制台
      internal WConsole _console;

      // 处理间隔
      protected int _interval = 1000;

      //============================================================
      // <T>构造控制台线程。</T>
      //============================================================
      public FConsoleThread() { 
      }

      //============================================================
      // <T>逻辑处理。</T>
      //============================================================
      public override void OnProcess() {
         while(IsRunning){
            _console.DataRefresh();
            Sleep(_interval);
         }
      }
   }
}

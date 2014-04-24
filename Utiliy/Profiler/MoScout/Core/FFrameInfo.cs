using MO.Common.Lang;
using MoScout.Core.Invoker;
using MoScout.Core.Logger;

namespace MoScout.Core
{
   //============================================================
   // <T>帧信息。</T>
   //
   // @history MAOCY 140410
   //============================================================
   public class FFrameInfo : FObject
   {
      protected long _tick;

      // 日志集合
      protected FObjects<FLoggerInfo> _loggers = new FObjects<FLoggerInfo>();

      // 日志集合
      protected FObjects<FInvokerInfo> _invokers = new FObjects<FInvokerInfo>();

      //============================================================
      // <T>构造帧信息。</T>
      //============================================================
      public FFrameInfo() {
      }

      //============================================================
      // <T>获得时刻。</T>
      //============================================================
      public long Tick {
         get { return _tick; }
         set { _tick = value; }
      }

      //============================================================
      // <T>获得日志信息。</T>
      //============================================================
      public FObjects<FLoggerInfo> Loggers {
         get { return _loggers; }
      }

      //============================================================
      // <T>获得调用信息。</T>
      //============================================================
      public FObjects<FInvokerInfo> Invokers {
         get { return _invokers; }
      }
   }
}

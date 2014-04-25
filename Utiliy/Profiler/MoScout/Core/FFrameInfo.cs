using MO.Common.IO;
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
      // 索引
      protected int _index;

      // 时刻
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
      // <T>获得索引。</T>
      //============================================================
      public int Index {
         get { return _index; }
         set { _index = value; }
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

      //============================================================
      // <T>序列化数据到输出流。</T>
      //
      // @param output 输出流
      // @return 处理结果
      //============================================================
      public EResult Serialize(IDataOutput output) {
         int count = _loggers.Count;
         output.WriteInt32(count);
         for(int n = 0; n < count; n++) {
            FLoggerInfo loggerInfo = _loggers.Get(n);
            loggerInfo.Serialize(output);
         }
         return EResult.Success;
      }

      //============================================================
      // <T>从输入流中反序列化数据。</T>
      //
      // @param input 输入流
      // @return 处理结果
      //============================================================
      public EResult Unserialize(IDataInput input) {
         int count = input.ReadInt32();
         for(int n = 0; n < count; n++) {
            FLoggerInfo loggerInfo = new FLoggerInfo();
            loggerInfo.Unserialize(input);
            _loggers.Push(loggerInfo);
         }
         return EResult.Success;
      }
   }
}

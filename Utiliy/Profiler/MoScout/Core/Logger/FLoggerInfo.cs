using MO.Common.IO;
using MO.Common.Lang;

namespace MoScout.Core.Logger
{
   //============================================================
   // <T>日志信息。</T>
   //============================================================
   public class FLoggerInfo : FObject
   {
      // 时刻
      protected long _tick;

      // 级别
      protected int _level;

      // 函数
      protected string _method;

      // 消息
      protected string _message;

      //============================================================
      // <T>构造日志信息。</T>
      //============================================================
      public FLoggerInfo() {
      }

      //============================================================
      // <T>设置或获得时刻。</T>
      //============================================================
      public long Tick {
         get { return _tick; }
         set { _tick = value; }
      }

      //============================================================
      // <T>设置或获得级别。</T>
      //============================================================
      public int Level {
         get { return _level; }
         set { _level = value; }
      }

      //============================================================
      // <T>设置或获得函数。</T>
      //============================================================
      public string Method {
         get { return _method; }
         set { _method = value; }
      }

      //============================================================
      // <T>设置或获得消息。</T>
      //============================================================
      public string Message {
         get { return _message; }
         set { _message = value; }
      }

      //============================================================
      // <T>序列化数据到输出流。</T>
      //
      // @param output 输出流
      // @return 处理结果
      //============================================================
      public EResult Serialize(IDataOutput output) {
         output.WriteInt64(_tick);
         output.WriteInt32(_level);
         output.WriteString(_method);
         output.WriteString(_message);
         return EResult.Success;
      }

      //============================================================
      // <T>从输入流中反序列化数据。</T>
      //
      // @param input 输入流
      // @return 处理结果
      //============================================================
      public EResult Unserialize(IDataInput input) {
         _tick = input.ReadInt64();
         _level = input.ReadInt32();
         _method = input.ReadString();
         _message = input.ReadString();
         return EResult.Success;
      }
   }
}

using System;
using MO.Common.Lang;

namespace MO.Core.Logic.Track
{
   //============================================================
   // <T>获得或设置时间。</T>
   //============================================================
   public class FTrack : FObject
   {
      // 时间
      protected DateTime _datetime;

      // 产生者
      protected string _sender;

      // 消息
      protected string _message;

      //============================================================
      // <T>获得或设置时间。</T>
      //============================================================
      public DateTime Datetime {
         get { return _datetime; }
         set { _datetime = value; }
      }

      //============================================================
      // <T>获得或设置产生者。</T>
      //============================================================
      public string Sender {
         get { return _sender; }
         set { _sender = value; }
      }

      //============================================================
      // <T>获得或设置消息。</T>
      //============================================================
      public string Message {
         get { return _message; }
         set { _message = value; }
      }
   }
}

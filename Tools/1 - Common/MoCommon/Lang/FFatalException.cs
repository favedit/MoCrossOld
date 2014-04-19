using System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>致命例外。</T>
   //============================================================
   public class FFatalException : FException
   {
      //============================================================
      // <T>构造致命例外。</T>
      //============================================================
      public FFatalException() {
      }

      //============================================================
      // <T>构造致命例外。</T>
      //
      // @param description 消息对象
      // @param args 参数集合
      //============================================================
      public FFatalException(object description, params object[] args)
         : base(description, args) {
      }

      //============================================================
      // <T>构造致命例外。</T>
      //
      // @param exception 例外对象
      //============================================================
      public FFatalException(Exception exception)
         : base(exception) {
      }

      //============================================================
      // <T>构造致命例外。</T>
      //
      // @param exception 例外对象
      // @param description 消息对象
      // @param args 参数集合
      //============================================================
      public FFatalException(Exception exception, object description, params object[] args)
         : base(exception, description, args) {
      }
   }
}

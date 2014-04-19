using System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>例外工具类。</T>
   //============================================================
   public class RException
   {
      //============================================================
      // <T>产生校验例外。</T>
      //
      // @param type 类型
      // @param name 名称
      // @param parameters 参数列表
      //============================================================
      public static void RaiseValid(Type type, string name, params object[] parameters) {
         throw new FValidException(name, parameters);
      }

      //============================================================
      // <T>产生没有找到项目例外。</T>
      //============================================================
      public static void RaiseNotFound() {
         throw new FFatalException("Can't find object.");
      }

      //============================================================
      // <T>产生非法范围范围例外。</T>
      //
      // @param begin 开始位置
      // @param end 结束位置
      // @param index 索引位置
      //============================================================
      public static void RaiseOutRange(int begin, int end, int index) {
         if(index < begin || index >= end) {
            throw new FFatalException("Index range error. (begin={1}, end={2}, index={3})", begin, end, index);
         }
      }

      //============================================================
      // <T>产生非法属于范围例外。</T>
      //
      // @param begin 开始位置
      // @param end 结束位置
      // @param index 索引位置
      //============================================================
      public static void RaiseOutBetween(int begin, int end, int index) {
         if(index < begin || index > end) {
            throw new FFatalException("Index range error. (begin={1}, end={2}, index={3})", begin, end, index);
         }
      }

      //============================================================
      // <T>获得例外对象的消息内容。</T>
      //
      // @param exception 例外对象
      // @return 消息内容
      //============================================================
      public static FString MakeMessage(Exception exception) {
         FString message = new FString();
         MakeMessage(message, exception);
         return message;
      }

      //============================================================
      // <T>获得例外对象的消息内容。</T>
      //
      // @param message 消息内容
      // @param exception 例外对象
      //============================================================
      public static void MakeMessage(FString message, Exception exception) {
         // 建立例外堆栈
         FObjects<Exception> stack = new FObjects<Exception>();
         Exception find = exception;
         while(find != null) {
            stack.Push(find);
            if(find is FException) {
               find = ((FException)find).Reason;
            } else {
               find = find.InnerException;
            }
         }
         // 加入消息内容
         stack.Reverse();
         message.AppendLine("Exception has raised. (@.@)");
         foreach(Exception exp in stack) {
            message.AppendLine(RDump.LINE_L2);
            string msg = exp.Message;
            if(!RString.IsBlank(msg)) {
               message.AppendLine(msg);
            }
            message.AppendLine(exp.StackTrace);
         }
      }
   }
}

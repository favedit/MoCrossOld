package org.mo.com.lang;

//============================================================
// <T>致命错误。</T>
//============================================================
public class FFatalError
      extends FError
{
   // 序列化标识
   private final static long serialVersionUID = 1L;

   //============================================================
   // <T>构造致命错误。</T>
   //
   // @param message 消息
   //============================================================
   public FFatalError(String message){
      super(message);
   }

   //============================================================
   // <T>构造致命错误。</T>
   //
   // @param message 消息
   // @param params 参数
   //============================================================
   public FFatalError(String message,
                      Object... params){
      super(message, params);
   }

   //============================================================
   // <T>构造致命错误。</T>
   //
   // @param throwable 例外
   //============================================================
   public FFatalError(Throwable throwable){
      super(throwable);
   }

   //============================================================
   // <T>构造致命错误。</T>
   //
   // @param throwable 例外
   // @param message 消息
   //============================================================
   public FFatalError(Throwable throwable,
                      String message){
      super(throwable, message);
   }

   //============================================================
   // <T>构造致命错误。</T>
   //
   // @param throwable 例外
   // @param message 消息
   // @param params 参数
   //============================================================
   public FFatalError(Throwable throwable,
                      String message,
                      Object... params){
      super(throwable, message, params);
   }
}

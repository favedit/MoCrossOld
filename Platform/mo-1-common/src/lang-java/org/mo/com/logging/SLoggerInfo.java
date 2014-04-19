package org.mo.com.logging;

//============================================================
// <T>日志信息。</T>
//============================================================
public class SLoggerInfo
{
   // 级别
   public ELoggerLevel levelCd;

   // 引用
   public Object reference;

   // 函数
   public String method;

   // 时间间隔
   public long timeSpan = -1;

   // 例外
   public Throwable throwable;

   // 全信息
   public boolean full;

   // 消息
   public String message;

   // 参数集合
   public Object[] parameters;

   //============================================================
   // <T>构造日志信息。</T>
   //============================================================
   public SLoggerInfo(){
   }

   //============================================================
   // <T>构造日志信息。</T>
   //
   // @param levelCd 级别
   // @param reference 引用
   // @param method 函数
   // @param timeSpan 时间
   // @param throwable 例外
   // @param message 消息
   // @param parameters 参数
   //============================================================
   public SLoggerInfo(ELoggerLevel levelCd,
                      Object reference,
                      String method,
                      long timeSpan,
                      Throwable throwable,
                      boolean full,
                      String message,
                      Object[] parameters){
      this.levelCd = levelCd;
      this.reference = reference;
      this.method = method;
      this.timeSpan = timeSpan;
      this.throwable = throwable;
      this.full = full;
      this.message = message;
      this.parameters = parameters;
   }
}

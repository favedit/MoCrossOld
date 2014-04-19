package org.mo.com.logging;

import org.mo.com.lang.FString;
import org.mo.com.lang.RDateTime;

//============================================================
// <T>日志文件输出监听器。</T>
//============================================================
public class FLoggerFileListener
      extends MLoggerListener
{
   //============================================================
   // <T>构造日志文件输出监听器。</T>
   //============================================================
   public FLoggerFileListener(Object owner){
      super(owner);
   }

   //============================================================
   // <T>输出日志处理。</T>
   //
   // @param sender 发出者
   // @param level 级别
   // @param message 日志信息
   //============================================================
   protected void output(Object sender,
                         int command,
                         FString message){
      System.out.println(RDateTime.format("YYMMDD-HH24MISS.MS") + "|" + message);
   }
}

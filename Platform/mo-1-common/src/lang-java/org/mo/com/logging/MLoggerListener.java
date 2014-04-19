package org.mo.com.logging;

import org.mo.com.lang.FString;
import org.mo.com.lang.FThrowables;
import org.mo.com.lang.RString;
import org.mo.com.lang.RThrowable;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.system.MListener;

//============================================================
// <T>日志监听器基础类。</T>
//============================================================
public abstract class MLoggerListener
      extends MListener
      implements
         ILoggerListener
{
   //============================================================
   // <T>构造日志监听器基础类。</T>
   //============================================================
   public MLoggerListener(){
   }

   //============================================================
   // <T>构造日志监听器基础类。</T>
   //
   // @param owner 拥有者
   //============================================================
   public MLoggerListener(Object owner){
      super(owner);
   }

   //============================================================
   // <T>初始化处理。</T>
   //============================================================
   @Override
   public void initialize(){
   }

   //============================================================
   // <T>建立消息信息字符串。</T>
   //
   // @param result 结果
   // @param info 信息
   //============================================================
   protected void buildMessage(FString result,
                               SLoggerInfo info){
      // 建立显示信息
      switch(info.levelCd){
         case NONE:
            result.append('P');
            break;
         case PRINT:
            result.append('P');
            break;
         case DEBUG:
            result.append('D');
            break;
         case INFO:
            result.append('I');
            break;
         case WARN:
            result.append('W');
            break;
         case ERROR:
            result.append('E');
            break;
         case FATAL:
            result.append('F');
            break;
         case NO_DEBUG:
            result.append('F');
            break;
      }
      result.append('.');
      // 建立线程信息
      String threadName = Thread.currentThread().getName();
      if(threadName.equalsIgnoreCase("main")){
         result.append("<MA>");
      }else if(threadName.startsWith("Thread-")){
         result.append("T" + RString.leftPad(threadName.substring(7), 3, "0"));
      }else if(threadName.startsWith("http-")){
         result.append("H" + RString.leftPad(threadName.substring(threadName.indexOf("Processor") + 9), 3, "0"));
      }else if(threadName.startsWith("TP-Processor")){
         result.append("P" + RString.leftPad(threadName.substring(12), 3, "0"));
      }else if(threadName.equals("JavaFX Application Thread")){
         result.append("<JF>");
      }else if(threadName.equals("ContainerBackgroundProcessor[StandardEngine[Catalina]]")){
         result.append("<CL>");
      }else{
         result.append(threadName);
      }
      // 获得引用对象信息
      String referInfo = null;
      Object reference = info.reference;
      if(reference instanceof String){
         referInfo = "(" + (String)reference + ")";
      }else{
         referInfo = RClass.shortName(reference);
         if(null != reference){
            referInfo += "@" + Integer.toHexString(reference.hashCode()).toUpperCase();
         }
      }
      if(null != info.method){
         referInfo += "." + info.method;
      }
      result.append(" [ ");
      if(info.timeSpan > 0){
         String timeSpan = Long.toString(info.timeSpan);
         result.append(RString.rightPad(referInfo, 50 - timeSpan.length() - 2));
         result.append(timeSpan + "ms");
      }else{
         result.append(RString.rightPad(referInfo, 50));
      }
      result.append(" ] ");
      // 建立信息
      String message = info.message;
      if(message != null){
         Object[] params = info.parameters;
         if(params != null){
            message = RString.format(message, params);
         }
         //if(!args.full() && message.length() > 2000){
         //message = message.substring(0, 2000);
         //result.append(message).append("...");
         //}else{
         result.append(message);
         //}
      }
      if(null != info.throwable){
         // 建立例外堆栈
         FThrowables stack = RThrowable.buildStack(info.throwable);
         // 建立例外消息
         RThrowable.buildMessage(result, stack);
      }
   }

   //============================================================
   // <T>输出日志处理。</T>
   //
   // @param sender 发出者
   // @param level 级别
   // @param message 日志信息
   //============================================================
   protected abstract void output(Object sender,
                                  int command,
                                  FString message);

   //============================================================
   // <T>日志处理。</T>
   //
   // @param sender 发出者
   // @param level 级别
   // @param info 信息
   //============================================================
   public boolean processLogger(Object sender,
                                int level,
                                SLoggerInfo info){
      FString message = new FString();
      buildMessage(message, info);
      output(sender, level, message);
      return true;
   }

   //============================================================
   // <T>处理日志。</T>
   //
   // @param sender 发出者
   // @param level 级别
   // @param params 参数
   //============================================================
   public boolean process(Object sender,
                          int command,
                          Object params){
      return processLogger(sender, command, (SLoggerInfo)params);
   }

   //============================================================
   // <T>刷新处理。</T>
   //============================================================
   @Override
   public void refresh(){
   }

   //============================================================
   // <T>释放处理。</T>
   //============================================================
   @Override
   public void release(){
   }
}

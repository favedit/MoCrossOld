package org.mo.com.logging;

import org.mo.com.lang.RObject;

//============================================================
// <T>日志。</T>
//============================================================
public class FLogger
      implements
         ILogger
{
   // 标志
   protected int _flags;

   // 引用
   protected Class<?> _reference;

   //============================================================
   // <T>构造日志。</T>
   //============================================================
   protected FLogger(){
   }

   //============================================================
   // <T>关联类对象。</T>
   //
   // @param clazz 类对象
   //============================================================
   public void link(Class<?> clazz){
      _reference = clazz;
   }

   //============================================================
   // <T>设置标志。</T>
   //
   // @param flags 标志
   //============================================================
   public void setFlags(int flags){
      _flags = flags;
   }

   //============================================================
   // <T>输出日志信息。</T>
   //
   // @param args 日志参数
   //============================================================
   public void output(SLoggerInfo args){
      RLogger.listeners().process(this, args.levelCd.value(), args);
   }

   //============================================================
   // <T>测试是否可以输出打印日志。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   @Override
   public boolean printAble(){
      return (ELoggerLevel.PRINT.value() == (ELoggerLevel.PRINT.value() & _flags));
   }

   //============================================================
   // <T>输出一条打印日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   @Override
   public void print(Object instance,
                     String method,
                     String message,
                     Object... parameters){
      if(printAble()){
         Object reference = RObject.nvl(instance, _reference);
         output(new SLoggerInfo(ELoggerLevel.PRINT, reference, method, 0, null, false, message, parameters));
      }
   }

   //============================================================
   // <T>测试是否可以输出调试日志。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   @Override
   public boolean debugAble(){
      return (ELoggerLevel.DEBUG.value() == (ELoggerLevel.DEBUG.value() & _flags));
   }

   //============================================================
   // <T>输出一条调试日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   @Override
   public void debug(Object instance,
                     String method,
                     String message,
                     Object... params){
      if(debugAble()){
         Object reference = RObject.nvl(instance, _reference);
         output(new SLoggerInfo(ELoggerLevel.DEBUG, reference, method, 0, null, false, message, params));
      }
   }

   //============================================================
   // <T>输出一条调试日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param timeSpan 时间
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   @Override
   public void debug(Object instance,
                     String method,
                     long timeSpan,
                     String message,
                     Object... parameters){
      if(debugAble()){
         Object reference = RObject.nvl(instance, _reference);
         output(new SLoggerInfo(ELoggerLevel.DEBUG, reference, method, timeSpan, null, false, message, parameters));
      }
   }

   //============================================================
   // <T>输出一条完成调试日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   @Override
   public void debugFull(Object instance,
                         String method,
                         String message,
                         Object... parameters){
      if(debugAble()){
         Object reference = RObject.nvl(instance, _reference);
         output(new SLoggerInfo(ELoggerLevel.DEBUG, reference, method, 0, null, true, message, parameters));
      }
   }

   //============================================================
   // <T>输出一条完成调试日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param timeSpan 时间
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   @Override
   public void debugFull(Object instance,
                         String method,
                         long timeSpan,
                         String message,
                         Object... parameters){
      if(debugAble()){
         Object reference = RObject.nvl(instance, _reference);
         output(new SLoggerInfo(ELoggerLevel.DEBUG, reference, method, timeSpan, null, true, message, parameters));
      }
   }

   //============================================================
   // <T>测试是否可以输出信息日志。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   @Override
   public boolean infoAble(){
      return (ELoggerLevel.INFO.value() == (ELoggerLevel.INFO.value() & _flags));
   }

   //============================================================
   // <T>输出一条信息日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param timeSpan 时间
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   @Override
   public void info(Object instance,
                    String method,
                    String message,
                    Object... parameters){
      if(infoAble()){
         Object reference = RObject.nvl(instance, _reference);
         output(new SLoggerInfo(ELoggerLevel.INFO, reference, method, 0, null, false, message, parameters));
      }
   }

   //============================================================
   // <T>测试是否可以输出警告日志。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   @Override
   public boolean warnAble(){
      return (ELoggerLevel.WARN.value() == (ELoggerLevel.WARN.value() & _flags));
   }

   //============================================================
   // <T>输出一条警告日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   @Override
   public void warn(Object instance,
                    String method,
                    String message,
                    Object... parameters){
      if(warnAble()){
         Object reference = RObject.nvl(instance, _reference);
         output(new SLoggerInfo(ELoggerLevel.WARN, reference, method, 0, null, false, message, parameters));
      }
   }

   //============================================================
   // <T>输出一条错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param throwable 例外信息
   //============================================================
   @Override
   public void error(Object instance,
                     String method,
                     Throwable throwable){
      Object reference = RObject.nvl(instance, _reference);
      output(new SLoggerInfo(ELoggerLevel.ERROR, reference, method, 0, throwable, false, null, null));
   }

   //============================================================
   // <T>输出一条错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   @Override
   public void error(Object instance,
                     String method,
                     String message,
                     Object... parameters){
      Object reference = RObject.nvl(instance, _reference);
      output(new SLoggerInfo(ELoggerLevel.ERROR, reference, method, 0, null, false, message, parameters));
   }

   //============================================================
   // <T>输出一条错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param throwable 例外信息
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   @Override
   public void error(Object instance,
                     String method,
                     Throwable throwable,
                     String message,
                     Object... parameters){
      Object reference = RObject.nvl(instance, _reference);
      output(new SLoggerInfo(ELoggerLevel.ERROR, reference, method, 0, throwable, false, message, parameters));
   }

   //============================================================
   // <T>输出一条致命错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param throwable 例外信息
   //============================================================
   @Override
   public void fatal(Object instance,
                     String method,
                     Throwable throwable){
      Object reference = RObject.nvl(instance, _reference);
      output(new SLoggerInfo(ELoggerLevel.FATAL, reference, method, 0, throwable, false, null, null));
   }

   //============================================================
   // <T>输出一条致命错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   @Override
   public void fatal(Object instance,
                     String method,
                     String message,
                     Object... params){
      Object reference = RObject.nvl(instance, _reference);
      output(new SLoggerInfo(ELoggerLevel.FATAL, reference, method, 0, null, false, message, params));
   }

   //============================================================
   // <T>输出一条致命错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param throwable 例外信息
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   @Override
   public void fatal(Object instance,
                     String method,
                     Throwable throwable,
                     String message,
                     Object... params){
      Object reference = RObject.nvl(instance, _reference);
      output(new SLoggerInfo(ELoggerLevel.FATAL, reference, method, 0, throwable, false, message, params));
   }
}

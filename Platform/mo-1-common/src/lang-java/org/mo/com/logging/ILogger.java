package org.mo.com.logging;

//============================================================
//<T>日志接口。</T>
//============================================================
public interface ILogger
{
   //============================================================
   // <T>设置标志。</T>
   //
   // @param flags 标志
   //============================================================
   void setFlags(int flags);

   //============================================================
   // <T>测试是否可以输出打印日志。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   boolean printAble();

   //============================================================
   // <T>输出一条打印日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   void print(Object instance,
              String method,
              String message,
              Object... parameters);

   //============================================================
   // <T>测试是否可以输出调试日志。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   boolean debugAble();

   //============================================================
   // <T>输出一条调试日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   void debug(Object instance,
              String method,
              String message,
              Object... params);

   //============================================================
   // <T>输出一条调试日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param timeSpan 时间
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   void debug(Object instance,
              String method,
              long timeSpan,
              String message,
              Object... parameters);

   //============================================================
   // <T>输出一条完成调试日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   void debugFull(Object instance,
                  String method,
                  String message,
                  Object... parameters);

   //============================================================
   // <T>输出一条完成调试日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param timeSpan 时间
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   void debugFull(Object instance,
                  String method,
                  long timeSpan,
                  String message,
                  Object... parameters);

   //============================================================
   // <T>测试是否可以输出信息日志。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   boolean infoAble();

   //============================================================
   // <T>输出一条信息日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param timeSpan 时间
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   void info(Object instance,
             String method,
             String message,
             Object... parameters);

   //============================================================
   // <T>测试是否可以输出警告日志。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   boolean warnAble();

   //============================================================
   // <T>输出一条警告日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   void warn(Object instance,
             String method,
             String message,
             Object... parameters);

   //============================================================
   // <T>输出一条错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param throwable 例外信息
   //============================================================
   void error(Object instance,
              String method,
              Throwable throwable);

   //============================================================
   // <T>输出一条错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   void error(Object instance,
              String method,
              String message,
              Object... parameters);

   //============================================================
   // <T>输出一条错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param throwable 例外信息
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   void error(Object instance,
              String method,
              Throwable throwable,
              String message,
              Object... parameters);

   //============================================================
   // <T>输出一条致命错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param throwable 例外信息
   //============================================================
   void fatal(Object instance,
              String method,
              Throwable throwable);

   //============================================================
   // <T>输出一条致命错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   void fatal(Object instance,
              String method,
              String message,
              Object... params);

   //============================================================
   // <T>输出一条致命错误日志。</T>
   //
   // @param instance 对象实体
   // @param method 函数名称
   // @param throwable 例外信息
   // @param message 消息
   // @param parameters 参数集合 
   //============================================================
   void fatal(Object instance,
              String method,
              Throwable throwable,
              String message,
              Object... params);
}

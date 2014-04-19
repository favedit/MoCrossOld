package org.mo.com.logging;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.system.FListeners;

//============================================================
// <T>日志管理器。</T>
//============================================================
public class RLogger
{
   // 标志
   private static int _flags = 0xFF;

   // 监听器集合
   private static FListeners _listeners = new FListeners();

   // 日志字典
   private static FLoggerDictionary _loggers = new FLoggerDictionary();
   //============================================================
   // <T>静态初始化。</T>
   //============================================================
   static{
      _listeners.push(new FLoggerConsoleListener(null));
   }

   //============================================================
   // <T>根据类获得日志接口对象。</T>
   //
   // @param clazz 类对象
   // @return 日志接口
   //============================================================
   public static void setFlags(int ableFlag){
      _flags = ableFlag;
      int count = _loggers.count();
      for(int n = 0; n < count; n++){
         _loggers.value(n).setFlags(_flags);
      }
   }

   //============================================================
   // <T>获得监听器集合。</T>
   //
   // @return 监听器集合
   //============================================================
   public static FListeners listeners(){
      return _listeners;
   }

   //============================================================
   // <T>获得日志集合。</T>
   //
   // @return 日志集合
   //============================================================
   public static FLoggerDictionary loggers(){
      return _loggers;
   }

   //============================================================
   // <T>根据类获得日志接口对象。</T>
   //
   // @param clazz 类对象
   // @return 日志接口
   //============================================================
   public static ILogger find(Class<?> clazz){
      String name = clazz.getName();
      ILogger face = _loggers.find(name);
      if(null == face){
         FLogger logger = new FLogger();
         logger.link(clazz);
         logger.setFlags(_flags);
         face = logger;
         _loggers.set(name, logger);
      }
      return face;
   }

   //============================================================
   // <T>根据类获得日志接口对象。</T>
   //
   // @param clazz 类对象
   // @return 日志接口
   //============================================================
   public static ILogger find(String name){
      ILogger logger = _loggers.get(name);
      if(null == logger){
         Class<?> clazz = RClass.findClass(name);
         if(null == clazz){
            throw new FFatalError("Can't found class {0}", name);
         }
         return find(clazz);
      }
      return logger;
   }
}

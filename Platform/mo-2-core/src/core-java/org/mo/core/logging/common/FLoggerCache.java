package org.mo.core.logging.common;

import org.mo.com.lang.FError;
import org.mo.com.lang.FString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

public class FLoggerCache
{
   private static ILogger _logger = RLogger.find(FLoggerCache.class);

   // 日志文件句柄
   private final FLogHandle _handle;

   // 日志内容
   private final FString _message;

   // 输出时间
   private long _time = 0;

   /**
    * <p>创建日志对象</p>
    *
    * @param handle 日志文件句柄
    * @param time 输出时间
    * @param message 日志内容
    */
   public FLoggerCache(FLogHandle handle,
                       long time,
                       FString message){
      _handle = handle;
      _time = time;
      _message = message;
   }

   /**
    * <p>执行写日志操作</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    */
   public void execute(){
      try{
         _handle.writeLine(_time, _message);
      }catch(FError e){
         _logger.error(this, "execute", e);
      }
   }
}

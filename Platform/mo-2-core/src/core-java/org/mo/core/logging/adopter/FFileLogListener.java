package org.mo.core.logging.adopter;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.logging.MLoggerListener;
import org.mo.core.logging.ILoggerConsole;
import org.mo.core.logging.common.ILogHandle;

public class FFileLogListener
      extends MLoggerListener
{
   private ILoggerConsole _console;

   private static int INDEX_DEBUG = 0;

   private static int INDEX_ERROR = 4;

   private static int INDEX_FATAL = 3;

   private static int INDEX_INFO = 1;

   private static int INDEX_WARN = 2;

   private static int TOTAL = 5;

   private static String TYPE_DEBUG = "Debug";

   private static String TYPE_ERROR = "Error";

   private static String TYPE_FATAL = "Fatal";

   private static String TYPE_INFO = "Info";

   private static String TYPE_WARN = "Warn";

   private final ILogHandle[] _handles = new ILogHandle[TOTAL];

   @Override
   public void initialize(){
      for(ILogHandle handle : _handles){
         if(null != handle){
            // 如果句柄中没有指定时间格式，则设置时间格式
            if(RString.isEmpty(handle.dateFormat())){
               handle.setDateFormat(_console.dateFormat());
            }
            // 如果句柄中没有指定存储路径，则设置存储路径
            if(RString.isEmpty(handle.storePath())){
               handle.setStorePath(_console.storePath());
            }
         }
      }
   }

   @Override
   protected void output(Object sender,
                         int command,
                         FString message){
      //      ILogHandle handle = null;
      //      //      switch(command){
      //      //         case ILogger.DEBUG:
      //      //            handle = _handles[INDEX_DEBUG];
      //      //            break;
      //      //         case ILogger.INFO:
      //      //            handle = _handles[INDEX_INFO];
      //      //            break;
      //      //         case ILogger.WARN:
      //      //            handle = _handles[INDEX_WARN];
      //      //            break;
      //      //         case ILogger.ERROR:
      //      //            handle = _handles[INDEX_ERROR];
      //      //            break;
      //      //         case ILogger.FATAL:
      //      //            handle = _handles[INDEX_FATAL];
      //      //            break;
      //      //      }
      //      if(null != handle){
      //         handle.writeLine(System.currentTimeMillis(), message);
      //      }
   }

   @Override
   public void refresh(){
      for(ILogHandle handle : _handles){
         if(null != handle){
            handle.flush(false);
         }
      }
   }

   /**
    * <T>释放后操作</T>
    */
   @Override
   public void release(){
      for(ILogHandle handle : _handles){
         if(null != handle){
            handle.close();
         }
      }
   }

   public void setHandle(String type,
                         ILogHandle handle){
      // 根据类型分配句柄
      if(TYPE_DEBUG.equalsIgnoreCase(type)){
         _handles[INDEX_DEBUG] = handle;
      }else if(TYPE_INFO.equalsIgnoreCase(type)){
         _handles[INDEX_INFO] = handle;
      }else if(TYPE_WARN.equalsIgnoreCase(type)){
         _handles[INDEX_WARN] = handle;
      }else if(TYPE_ERROR.equalsIgnoreCase(type)){
         _handles[INDEX_ERROR] = handle;
      }else if(TYPE_FATAL.equalsIgnoreCase(type)){
         _handles[INDEX_FATAL] = handle;
      }else{
         throw new FFatalError("Unknown handle type (type={0}, handle={1})", type, handle);
      }
   }

   public void setConsole(ILoggerConsole console){
      _console = console;
   }
}

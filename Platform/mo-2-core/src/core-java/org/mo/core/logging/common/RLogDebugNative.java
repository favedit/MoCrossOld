package org.mo.core.logging.common;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

public class RLogDebugNative
{
   private static RLogDebugNative _instance = new RLogDebugNative();

   private static ILogger _logger = RLogger.find(RLogDebugNative.class);
   static{
      try{
         System.loadLibrary("RLogDebugNative");
      }catch(Throwable t){
         _logger.error(null, "static", t);
      }
   }

   public static void println(String source){
      _instance.OutputDebugString(source);
   }

   public native void OutputDebugString(String source);
}

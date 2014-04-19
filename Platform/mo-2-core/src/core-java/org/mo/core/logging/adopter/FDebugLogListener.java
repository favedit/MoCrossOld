package org.mo.core.logging.adopter;

import org.mo.com.lang.FString;
import org.mo.com.logging.MLoggerListener;
import org.mo.core.aop.face.AProperty;

public class FDebugLogListener
      extends MLoggerListener
{
   @AProperty
   protected boolean _debugAble = true;

   @AProperty
   protected boolean _errorAble = true;

   @AProperty
   protected boolean _fatalAble = true;

   @AProperty
   protected boolean _infoAble = true;

   @AProperty
   protected boolean _warnAble = true;

   public FDebugLogListener(){
      super(null);
   }

   @Override
   protected void output(Object sender,
                         int command,
                         FString message){
      //      boolean able = false;
      //      switch(command){
      //         case ELoggerLevel.DEBUG.value():
      //            able = _debugAble;
      //            break;
      //         case ELoggerLevel.INFO.value():
      //            able = _infoAble;
      //            break;
      //         case ELoggerLevel.WARN.value():
      //            able = _warnAble;
      //            break;
      //         case ELoggerLevel.ERROR.value():
      //            able = _errorAble;
      //            break;
      //         case ELoggerLevel.FATAL.value():
      //            able = _fatalAble;
      //            break;
      //      }
      //      if(able){
      //         RLogDebugNative.println(message.toString());
      //      }
   }
}

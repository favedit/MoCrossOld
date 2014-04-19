package org.mo.com.logging;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>日志字典。</T>
//============================================================
public class FLoggerDictionary
      extends FDictionary<ILogger>
{
   //============================================================
   // <T>构造日志字典。</T>
   //============================================================
   public FLoggerDictionary(){
      super(ILogger.class);
   }
}

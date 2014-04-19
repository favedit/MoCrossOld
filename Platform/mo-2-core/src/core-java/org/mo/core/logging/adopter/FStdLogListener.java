package org.mo.core.logging.adopter;

import org.mo.com.lang.FString;
import org.mo.com.lang.RDateTime;
import org.mo.com.logging.MLoggerListener;

public class FStdLogListener
      extends MLoggerListener
{
   protected void output(Object sender,
                         int command,
                         FString message){
      System.out.println(RDateTime.format("YYMMDD-HH24MISS.MS") + "|" + message);
   }
}

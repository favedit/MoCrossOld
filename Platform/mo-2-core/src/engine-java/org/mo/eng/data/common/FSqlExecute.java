package org.mo.eng.data.common;

import org.mo.com.data.ISqlConnect;
import org.mo.com.lang.FString;

public class FSqlExecute
      extends FSqlCommand
{
   public FSqlExecute(ISqlConnect connect){
      super(connect);
   }

   public FSqlExecute(ISqlConnect connect,
                      FString command){
      super(connect, command);
   }

   @Override
   public void execute(){
      activeConnection().executeSql(makeCommand());
   }
}

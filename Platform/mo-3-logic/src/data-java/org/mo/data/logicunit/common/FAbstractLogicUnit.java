package org.mo.data.logicunit.common;

import org.mo.com.data.ISqlConnect;
import org.mo.com.data.MSqlConnect;

public class FAbstractLogicUnit
      extends MSqlConnect
      implements
         ILogicUnit
{
   public FAbstractLogicUnit(){
   }

   public FAbstractLogicUnit(ISqlConnect connect){
      super(connect);
   }
}

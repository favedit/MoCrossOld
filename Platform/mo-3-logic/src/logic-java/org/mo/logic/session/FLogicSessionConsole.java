package org.mo.logic.session;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IAttributes;

public class FLogicSessionConsole
      implements
         ILogicSessionConsole
{
   public IAttributes makeLogic(){
      return new FAttributes();
   }
}

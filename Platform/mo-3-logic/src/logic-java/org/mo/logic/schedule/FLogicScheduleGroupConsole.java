package org.mo.logic.schedule;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.FXmlConfigConsole;

public class FLogicScheduleGroupConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         ILogicScheduleGroupConsole
{
   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}

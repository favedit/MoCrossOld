package org.mo.logic.event;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.FXmlConfigConsole;

public class FLogicEventTypeConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         ILogicEventTypeConsole
{
   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}

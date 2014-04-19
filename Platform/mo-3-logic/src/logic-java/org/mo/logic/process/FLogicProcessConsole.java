package org.mo.logic.process;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.FXmlConfigConsole;

public class FLogicProcessConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         ILogicProcessConsole
{
   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}

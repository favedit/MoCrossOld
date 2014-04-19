package org.mo.logic.property;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.FXmlConfigConsole;

public class FLogicPropertyConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         ILogicPropertyConsole
{
   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}

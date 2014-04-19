package org.mo.logic.project;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.FXmlConfigConsole;

public class FLogicProjectConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         ILogicProjectConsole
{
   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}

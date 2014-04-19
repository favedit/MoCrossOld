package org.mo.eng.translate;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.FXmlConfigConsole;

public class FTranslateConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         ITranslateConsole
{
   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}

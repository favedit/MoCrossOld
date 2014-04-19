package org.mo.jfa.core.webtag;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.FXmlConfigConsole;

public class FWebTagConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         IWebTagConsole
{

   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}

package org.mo.eng.help;

import org.mo.com.xml.IXmlObject;

public class FHelpBuilderArgs
{
   private IXmlObject _instance;

   public IXmlObject getInstance(){
      return _instance;
   }

   public void setInstance(IXmlObject instance){
      _instance = instance;
   }
}

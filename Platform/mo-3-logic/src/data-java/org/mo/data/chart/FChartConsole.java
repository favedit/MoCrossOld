package org.mo.data.chart;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.FXmlConfigConsole;

public class FChartConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         IChartConsole
{
   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}

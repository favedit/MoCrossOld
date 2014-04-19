package org.mo.data.logicunit;

import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.data.codelist.common.XCodeList;
import org.mo.eng.store.IXmlConfigConsole;

public interface ILogicUnitConsole
      extends
         IXmlConfigConsole<IXmlObject>
{
   XCodeList build(String name);

   FXmlNodes buildItemNodes(String name);
}

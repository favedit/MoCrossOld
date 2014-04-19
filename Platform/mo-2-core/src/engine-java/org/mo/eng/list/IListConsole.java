package org.mo.eng.list;

import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.eng.list.common.XList;
import org.mo.eng.store.IXmlConfigConsole;

public interface IListConsole
      extends
         IXmlConfigConsole<XList>
{
   XList build(String name);

   FXmlNodes buildItemNodes(String name);

   FXmlNodes buildItemNodes(TListArgs args);

   FXmlNode buildListConfig(TListArgs args);
}

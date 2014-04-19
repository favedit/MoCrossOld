package org.mo.data.codelist;

import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.IXmlConfigConsole;

public interface ICodeListConsole
      extends
         IXmlConfigConsole<IXmlObject>
{
   void build(FCodeListBuilderArgs args);
}

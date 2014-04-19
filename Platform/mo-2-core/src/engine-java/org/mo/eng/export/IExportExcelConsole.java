package org.mo.eng.export;

import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.IXmlConfigConsole;

public interface IExportExcelConsole
      extends
         IXmlConfigConsole<IXmlObject>
{

   String getPath();

   String makeStorePath(String name);

   String makeTempPath(String name);
}

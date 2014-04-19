package org.mo.web.core.webtools;

import org.mo.com.xml.FXmlNode;
import org.mo.eng.store.IXmlConfigConsole;
import org.mo.eng.store.IXmlConfigConvert;
import org.mo.web.core.webtools.common.XToolBar;

public interface IWebToolsConsole
      extends
         IXmlConfigConsole<XToolBar>,
         IXmlConfigConvert
{

   FXmlNode buildSimple(String tree);

}

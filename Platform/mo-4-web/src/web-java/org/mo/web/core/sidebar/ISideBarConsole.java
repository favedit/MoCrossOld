package org.mo.web.core.sidebar;

import org.mo.com.xml.FXmlNode;
import org.mo.eng.store.IXmlConfigConsole;
import org.mo.eng.store.IXmlConfigConvert;
import org.mo.web.core.sidebar.common.XSideBar;

public interface ISideBarConsole
      extends
         IXmlConfigConsole<XSideBar>,
         IXmlConfigConvert
{

   FXmlNode buildSimple(String tree);

}

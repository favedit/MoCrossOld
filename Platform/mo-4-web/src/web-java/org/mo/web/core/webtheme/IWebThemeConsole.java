package org.mo.web.core.webtheme;

import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.IXmlConfigConsole;
import org.mo.eng.store.IXmlConfigConvert;
import org.mo.web.core.webtheme.common.XWebTheme;

public interface IWebThemeConsole
      extends
         IXmlConfigConsole<XWebTheme>,
         IXmlConfigConvert
{

   FXmlNode buildConfig(String themeName);

   void copy(IXmlObject xsource,
             IXmlObject xtarget);

}

package org.mo.web.core.webtree;

import org.mo.com.xml.FXmlNode;
import org.mo.eng.store.IXmlConfigConsole;
import org.mo.eng.store.IXmlConfigConvert;
import org.mo.web.core.webtree.common.XTreeView;

public interface IWebTreeConsole
      extends
         IXmlConfigConsole<XTreeView>,
         IXmlConfigConvert
{

   FXmlNode buildSimple(String tree);

   FXmlNode buildSimpleWithPermission(String treeName,
                                      String userId);

}

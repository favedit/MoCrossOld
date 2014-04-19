package org.mo.web.core.webtools;

import org.mo.com.lang.FObjects;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.persistence.common.FXmlPersistence;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.web.core.webtools.common.XToolBar;
import org.mo.web.core.webtree.common.XTreeNode;

public class FWebToolsConsole
      extends FXmlConfigConsole<XToolBar>
      implements
         IWebToolsConsole
{

   @Override
   public FXmlNode buildSimple(String tree){
      XToolBar xtools = get(tree);
      FXmlPersistence persistence = _persistenceConsole.find(FXmlPersistence.class, _persistence);
      FXmlNode config = persistence.saveObject(xtools, EXmlConfig.Simple);
      for(FXmlNode node : config.allNodes()){
         if(node.isName(XTreeNode.NAME)){
            String attributes = node.get("attributes");
            if(RString.isNotEmpty(attributes)){
               node.removeAttribute("attributes");
               node.attributes().split(attributes, "=", "\n", true);
            }
         }
      }
      return config;
   }

   @Override
   protected FObjects<XToolBar> createCollection(){
      return new FObjects<XToolBar>(XToolBar.class);
   }
}

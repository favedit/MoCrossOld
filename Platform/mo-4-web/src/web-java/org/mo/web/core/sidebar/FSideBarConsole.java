package org.mo.web.core.sidebar;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.web.core.sidebar.common.XSideBar;

public class FSideBarConsole
      extends FXmlConfigConsole<XSideBar>
      implements
         ISideBarConsole
{

   @Override
   public FXmlNode buildSimple(String tree){
      return null;
   }

   @Override
   protected FObjects<XSideBar> createCollection(){
      return new FObjects<XSideBar>(XSideBar.class);
   }

}

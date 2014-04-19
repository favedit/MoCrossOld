package org.mo.jfa.face.design.sidebar;

import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.core.sidebar.ISideBarConsole;
import org.mo.web.core.sidebar.common.XSideBar;
import org.mo.web.protocol.context.IWebContext;

public class FSideBarAction
      extends FAbsXmlObjectAction<XSideBar>
      implements
         ISideBarAction
{

   public final String PAGE_CATALOG = "Catalog";

   public final String PAGE_LIST = "List";

   public final String PAGE_TOOLBAR = "ToolBar";

   @ALink
   protected ISideBarConsole _sidebarConsole;

   @Override
   public String catalog(IWebContext context,
                         FSideBarPage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FSideBarPage page){
      return delete(_sidebarConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FSideBarPage page){
      return insert(_sidebarConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FSideBarPage page){
      return list(_sidebarConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FSideBarPage page){
      return sort(_sidebarConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FSideBarPage page){
      return update(_sidebarConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}

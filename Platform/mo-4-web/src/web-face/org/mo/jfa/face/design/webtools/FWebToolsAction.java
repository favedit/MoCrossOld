package org.mo.jfa.face.design.webtools;

import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.core.webtools.IWebToolsConsole;
import org.mo.web.core.webtools.common.XToolBar;
import org.mo.web.protocol.context.IWebContext;

public class FWebToolsAction
      extends FAbsXmlObjectAction<XToolBar>
      implements
         IWebToolsAction
{

   public final String PAGE_CATALOG = "Catalog";

   public final String PAGE_LIST = "List";

   public final String PAGE_TOOLBAR = "ToolBar";

   @ALink
   protected IWebToolsConsole _toolsConsole;

   @Override
   public String catalog(IWebContext context,
                         FWebToolsPage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FWebToolsPage page){
      return delete(_toolsConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FWebToolsPage page){
      return insert(_toolsConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FWebToolsPage page){
      return list(_toolsConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FWebToolsPage page){
      return sort(_toolsConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FWebToolsPage page){
      return update(_toolsConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}

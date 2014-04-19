package org.mo.jfa.face.system.help;

import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.help.FHelpBuilderArgs;
import org.mo.eng.help.IHelpConsole;
import org.mo.eng.help.common.XHelp;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FHelpAction
      extends FAbsXmlObjectAction<XHelp>
      implements
         IHelpAction
{

   public final String PAGE_CATALOG = "Catalog";

   public final String PAGE_LIST = "List";

   @ALink
   protected IHelpConsole _helpConsole;

   @Override
   public String build(IWebContext context,
                       FHelpPage page){
      page.appachContext(context);
      IXmlObject xhelp = getSelectCollection(_helpConsole, page);
      FHelpBuilderArgs args = new FHelpBuilderArgs();
      args.setInstance(xhelp);
      _helpConsole.build(args);
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String catalog(IWebContext context,
                         FHelpPage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FHelpPage page){
      return delete(_helpConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FHelpPage page){
      return insert(_helpConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FHelpPage page){
      return list(_helpConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FHelpPage page){
      return sort(_helpConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FHelpPage page){
      return update(_helpConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}

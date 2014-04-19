package org.mo.jfa.face.system.config;

import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.config.IConfigConsole;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FConfigAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IConfigAction
{

   public final String PAGE_CATALOG = "Catalog";

   public final String PAGE_LIST = "List";

   @ALink
   protected IConfigConsole _configConsole;

   @Override
   public String catalog(IWebContext context,
                         FConfigPage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FConfigPage page){
      return delete(_configConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FConfigPage page){
      return insert(_configConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FConfigPage page){
      return list(_configConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FConfigPage page){
      return sort(_configConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FConfigPage page){
      return update(_configConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}

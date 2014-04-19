package org.mo.jfa.face.database.connector;

import org.mo.data.connector.common.XDatabase;

import org.mo.data.connector.IConnectorConsole;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FConnectorAction
      extends FAbsXmlObjectAction<XDatabase>
      implements
         IConnectorAction
{
   public final static String PAGE_CATALOG = "Catalog";

   @ALink
   protected IConnectorConsole _connectorConsole;

   @Override
   public String catalog(IWebContext context,
                         FConnectorPage page){
      return catalog(_connectorConsole, context, page, PAGE_CATALOG);
   }

   @Override
   public String delete(IWebContext context,
                        FConnectorPage page){
      return delete(_connectorConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FConnectorPage page){
      return insert(_connectorConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FConnectorPage page){
      return list(_connectorConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FConnectorPage page){
      return sort(_connectorConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FConnectorPage page){
      return update(_connectorConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}

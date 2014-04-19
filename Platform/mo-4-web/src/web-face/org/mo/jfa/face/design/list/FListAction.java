package org.mo.jfa.face.design.list;

import org.mo.core.aop.face.ALink;
import org.mo.eng.list.IListConsole;
import org.mo.eng.list.common.XList;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FListAction
      extends FAbsXmlObjectAction<XList>
      implements
         IListAction
{

   @ALink
   protected IListConsole _listConsole;

   public final static String PAGE_CATALOG = "Catalog";

   @Override
   public String catalog(IWebContext context,
                         FListPage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FListPage page){
      return delete(_listConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FListPage page){
      return insert(_listConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FListPage page){
      return list(_listConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FListPage page){
      return sort(_listConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FListPage page){
      return update(_listConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}

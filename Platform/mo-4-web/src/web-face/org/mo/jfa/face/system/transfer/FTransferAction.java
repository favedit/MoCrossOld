package org.mo.jfa.face.system.transfer;

import org.mo.core.aop.face.ALink;
import org.mo.eng.transfer.ITransferConsole;
import org.mo.eng.transfer.common.XCsvImport;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FTransferAction
      extends FAbsXmlObjectAction<XCsvImport>
      implements
         ITransferAction
{

   @ALink
   protected ITransferConsole _transferConsole;

   public final static String PAGE_CATALOG = "Catalog";

   @Override
   public String catalog(IWebContext context,
                         FTransferPage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FTransferPage page){
      return delete(_transferConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FTransferPage page){
      return insert(_transferConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FTransferPage page){
      return list(_transferConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FTransferPage page){
      return sort(_transferConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FTransferPage page){
      return update(_transferConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}

package org.mo.jfa.face.design.report;

import org.mo.core.aop.face.ALink;
import org.mo.eng.report.IReportConsole;
import org.mo.eng.report.common.XReport;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FReportAction
      extends FAbsXmlObjectAction<XReport>
      implements
         IReportAction
{

   public final String PAGE_CATALOG = "Catalog";

   @ALink
   protected IReportConsole _reportConsole;

   @Override
   public String catalog(IWebContext context,
                         FReportPage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FReportPage page){
      return delete(_reportConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FReportPage page){
      return insert(_reportConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FReportPage page){
      return list(_reportConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FReportPage page){
      return sort(_reportConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FReportPage page){
      return update(_reportConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}

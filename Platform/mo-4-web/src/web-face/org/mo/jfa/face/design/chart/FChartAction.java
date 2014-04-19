package org.mo.jfa.face.design.chart;

import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.chart.IChartConsole;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FChartAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IChartAction
{

   public final String PAGE_CATALOG = "Catalog";

   public final String PAGE_LIST = "List";

   public final String PAGE_Chart = "Chart";

   @ALink
   protected IChartConsole _chartConsole;

   @Override
   public String catalog(IWebContext context,
                         FChartPage page){
      return catalog(_formConsole, context, page, PAGE_CATALOG);
   }

   @Override
   public String delete(IWebContext context,
                        FChartPage page){
      return delete(_chartConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FChartPage page){
      return insert(_chartConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FChartPage page){
      return list(_chartConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FChartPage page){
      return sort(_chartConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FChartPage page){
      return update(_chartConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}

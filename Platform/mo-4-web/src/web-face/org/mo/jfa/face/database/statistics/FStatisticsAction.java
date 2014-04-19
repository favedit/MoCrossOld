package org.mo.jfa.face.database.statistics;

import org.mo.core.aop.face.ALink;
import org.mo.data.statistics.IStatisticsConsole;
import org.mo.data.statistics.common.XStatistics;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FStatisticsAction
      extends FAbsXmlObjectAction<XStatistics>
      implements
         IStatisticsAction
{
   public final static String PAGE_CATALOG = "Catalog";

   @ALink
   protected IStatisticsConsole _statisticsConsole;

   @Override
   public String catalog(IWebContext context,
                         FStatisticsPage page){
      return catalog(_statisticsConsole, context, page, PAGE_CATALOG);
   }

   @Override
   public String delete(IWebContext context,
                        FStatisticsPage page){
      return delete(_statisticsConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FStatisticsPage page){
      return insert(_statisticsConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FStatisticsPage page){
      return list(_statisticsConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FStatisticsPage page){
      return sort(_statisticsConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FStatisticsPage page){
      return update(_statisticsConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}

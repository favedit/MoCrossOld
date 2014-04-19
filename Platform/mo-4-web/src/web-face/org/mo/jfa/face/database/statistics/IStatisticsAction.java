package org.mo.jfa.face.database.statistics;

import org.mo.web.core.container.AContainer;
import org.mo.web.core.webform.IFormPage;
import org.mo.web.protocol.context.IWebContext;

public interface IStatisticsAction
{
   String catalog(IWebContext context,
                  @AContainer(name = IFormPage.Page) FStatisticsPage page);

   String list(IWebContext context,
               @AContainer(name = IFormPage.Page) FStatisticsPage page);

   String sort(IWebContext context,
               @AContainer(name = IFormPage.Page) FStatisticsPage page);

   String insert(IWebContext context,
                 @AContainer(name = IFormPage.Page) FStatisticsPage page);

   String update(IWebContext context,
                 @AContainer(name = IFormPage.Page) FStatisticsPage page);

   String delete(IWebContext context,
                 @AContainer(name = IFormPage.Page) FStatisticsPage page);
}

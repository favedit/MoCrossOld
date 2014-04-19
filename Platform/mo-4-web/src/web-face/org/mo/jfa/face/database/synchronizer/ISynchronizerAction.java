package org.mo.jfa.face.database.synchronizer;

import org.mo.web.core.container.AContainer;
import org.mo.web.core.webform.IFormPage;
import org.mo.web.protocol.context.IWebContext;

public interface ISynchronizerAction
{
   String catalog(IWebContext context,
                  @AContainer(name = IFormPage.Page) FSynchronizerPage page);

   String list(IWebContext context,
               @AContainer(name = IFormPage.Page) FSynchronizerPage page);

   String sort(IWebContext context,
               @AContainer(name = IFormPage.Page) FSynchronizerPage page);

   String insert(IWebContext context,
                 @AContainer(name = IFormPage.Page) FSynchronizerPage page);

   String update(IWebContext context,
                 @AContainer(name = IFormPage.Page) FSynchronizerPage page);

   String delete(IWebContext context,
                 @AContainer(name = IFormPage.Page) FSynchronizerPage page);
}

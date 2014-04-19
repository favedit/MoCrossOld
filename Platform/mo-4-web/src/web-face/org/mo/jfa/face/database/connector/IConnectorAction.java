package org.mo.jfa.face.database.connector;

import org.mo.web.core.container.AContainer;
import org.mo.web.core.webform.IFormPage;
import org.mo.web.protocol.context.IWebContext;

public interface IConnectorAction
{
   String catalog(IWebContext context,
                  @AContainer(name = IFormPage.Page) FConnectorPage page);

   String list(IWebContext context,
               @AContainer(name = IFormPage.Page) FConnectorPage page);

   String sort(IWebContext context,
               @AContainer(name = IFormPage.Page) FConnectorPage page);

   String insert(IWebContext context,
                 @AContainer(name = IFormPage.Page) FConnectorPage page);

   String update(IWebContext context,
                 @AContainer(name = IFormPage.Page) FConnectorPage page);

   String delete(IWebContext context,
                 @AContainer(name = IFormPage.Page) FConnectorPage page);
}

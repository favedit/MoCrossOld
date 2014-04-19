package org.mo.jfa.face.database.logicunit;

import org.mo.web.core.container.AContainer;

import org.mo.web.core.webform.IFormPage;
import org.mo.web.protocol.context.IWebContext;

public interface ILogicUnitAction
{

   String catalog(IWebContext context,
                  @AContainer(name = IFormPage.Page) FLogicUnitPage page);

   String delete(IWebContext context,
                 @AContainer(name = IFormPage.Page) FLogicUnitPage page);

   String insert(IWebContext context,
                 @AContainer(name = IFormPage.Page) FLogicUnitPage page);

   String list(IWebContext context,
               @AContainer(name = IFormPage.Page) FLogicUnitPage page);

   String sort(IWebContext context,
               @AContainer(name = IFormPage.Page) FLogicUnitPage page);

   String update(IWebContext context,
                 @AContainer(name = IFormPage.Page) FLogicUnitPage page);

}

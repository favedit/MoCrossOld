package org.mo.jfa.face.design.property;

import org.mo.web.core.container.AContainer;
import org.mo.web.core.container.EContainerScope;

import org.mo.jfa.face.inc.form.FFormPage;
import org.mo.jfa.face.inc.form.IFormAble;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.protocol.context.IWebContext;

public interface IPropertyAction
      extends
         IFormAble
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FPropertyPage page,
                  IWebFormConsole wfConsole);

   String construct(IWebContext context,
                    @AContainer(name = "page", clear = true) FPropertyPage page);

   String extendForm(IWebContext context,
                     @AContainer(name = "page") FPropertyPage page,
                     IWebFormConsole wfConsole);

   String showField(IWebContext context,
                    @AContainer(name = "page") FPropertyPage page,
                    IWebFormConsole wfConsole);

   String showForm(IWebContext context,
                   @AContainer(name = "page") FPropertyPage page,
                   IWebFormConsole wfConsole);

   String update(IWebContext context,
                 @AContainer(name = "page", scope = EContainerScope.Session) FPropertyPage page,
                 @AContainer(name = "webform", scope = EContainerScope.Session) FFormPage formPage,
                 IWebFormConsole wfConsole);

   String updateSave(IWebContext context,
                     @AContainer(name = "page", scope = EContainerScope.Session) FPropertyPage page,
                     @AContainer(name = "webform", scope = EContainerScope.Session) FFormPage formPage,
                     IWebFormConsole wfConsole);

}

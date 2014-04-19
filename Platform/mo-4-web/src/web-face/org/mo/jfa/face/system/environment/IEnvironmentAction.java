package org.mo.jfa.face.system.environment;

import org.mo.web.core.container.AContainer;

import org.mo.jfa.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IEnvironmentAction
      extends
         IFormAble
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FEnvironmentPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FEnvironmentPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FEnvironmentPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FEnvironmentPage page);

   String refresh(IWebContext context,
                  @AContainer(name = "page") FEnvironmentPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FEnvironmentPage page);
}

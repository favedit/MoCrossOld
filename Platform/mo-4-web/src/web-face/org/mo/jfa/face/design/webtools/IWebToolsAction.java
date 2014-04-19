package org.mo.jfa.face.design.webtools;

import org.mo.web.core.container.AContainer;

import org.mo.jfa.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IWebToolsAction
      extends
         IFormAble
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FWebToolsPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FWebToolsPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FWebToolsPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FWebToolsPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FWebToolsPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FWebToolsPage page);

}

package org.mo.jfa.face.system.config;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

public interface IConfigAction
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FConfigPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FConfigPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FConfigPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FConfigPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FConfigPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FConfigPage page);
}

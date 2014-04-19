package org.mo.jfa.face.system.help;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

public interface IHelpAction
{

   String build(IWebContext context,
                @AContainer(name = "page") FHelpPage page);

   String catalog(IWebContext context,
                  @AContainer(name = "page") FHelpPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FHelpPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FHelpPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FHelpPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FHelpPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FHelpPage page);

}

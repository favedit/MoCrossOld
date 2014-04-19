package org.mo.jfa.face.design.persistence;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

public interface IPersistenceAction
{

   String build(IWebContext context,
                @AContainer(name = "page") FPersistencePage page);

   String buildAll(IWebContext context,
                   @AContainer(name = "page") FPersistencePage page);

   String catalog(IWebContext context,
                  @AContainer(name = "page") FPersistencePage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FPersistencePage page);

   String execute(IWebContext context,
                  @AContainer(name = "page") FPersistencePage page);

   String help(IWebContext context,
               @AContainer(name = "page") FPersistencePage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FPersistencePage page);

   String list(IWebContext context,
               @AContainer(name = "page") FPersistencePage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FPersistencePage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FPersistencePage page);

}

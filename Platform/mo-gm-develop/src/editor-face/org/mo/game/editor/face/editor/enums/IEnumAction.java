package org.mo.game.editor.face.editor.enums;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

public interface IEnumAction{

   String buildAllSource(IWebContext context,
                         @AContainer(name = "page") FEnumPage page);

   String catalog(IWebContext context,
                  @AContainer(name = "page") FEnumPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FEnumPage page);

   String fetch(IWebContext context,
                @AContainer(name = "page") FEnumPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FEnumPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FEnumPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FEnumPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FEnumPage page);
}

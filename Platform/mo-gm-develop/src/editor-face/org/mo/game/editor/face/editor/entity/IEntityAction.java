package org.mo.game.editor.face.editor.entity;

import org.mo.web.core.container.AContainer;

import org.mo.game.editor.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IEntityAction
      extends
         IFormAble{

   String buildAllSource(IWebContext context,
                         @AContainer(name = "page") FEntityPage page);

   String catalog(IWebContext context,
                  @AContainer(name = "page") FEntityPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FEntityPage page);

   String fetchProperty(IWebContext context,
                        @AContainer(name = "page") FEntityPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FEntityPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FEntityPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FEntityPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FEntityPage page);
}

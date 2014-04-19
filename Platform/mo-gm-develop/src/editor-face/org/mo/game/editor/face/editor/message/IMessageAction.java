package org.mo.game.editor.face.editor.message;

import org.mo.web.core.container.AContainer;

import org.mo.game.editor.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IMessageAction
      extends
         IFormAble{

   String buildAllSource(IWebContext context,
                         @AContainer(name = "page") FMessagePage page);

   String catalog(IWebContext context,
                  @AContainer(name = "page") FMessagePage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FMessagePage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FMessagePage page);

   String list(IWebContext context,
               @AContainer(name = "page") FMessagePage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FMessagePage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FMessagePage page);
}

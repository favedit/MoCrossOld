package org.mo.game.editor.face.editor.logic;

import org.mo.web.core.container.AContainer;
import org.mo.web.protocol.context.IWebContext;

public interface ILogicAction
{
   String catalog(IWebContext context,
                  @AContainer(name = "page") FLogicPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FLogicPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FLogicPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FLogicPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FLogicPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FLogicPage page);

   String buildAllSource(IWebContext context,
                         @AContainer(name = "page") FLogicPage page);
}

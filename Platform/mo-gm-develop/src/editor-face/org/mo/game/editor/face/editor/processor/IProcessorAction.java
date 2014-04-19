package org.mo.game.editor.face.editor.processor;

import org.mo.web.core.container.AContainer;

import org.mo.game.editor.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IProcessorAction
      extends
         IFormAble{

   String build(IWebContext context,
                @AContainer(name = "page") FProcessorPage page);

   String buildAllSource(IWebContext context,
                         @AContainer(name = "page") FProcessorPage page);

   String buildSource(IWebContext context,
                      @AContainer(name = "page") FProcessorPage page);

   String catalog(IWebContext context,
                  @AContainer(name = "page") FProcessorPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FProcessorPage page);

   String execute(IWebContext context,
                  @AContainer(name = "page") FProcessorPage page);

   String fetch(IWebContext context,
                @AContainer(name = "page") FProcessorPage page);

   String fetchAll(IWebContext context,
                   @AContainer(name = "page") FProcessorPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FProcessorPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FProcessorPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FProcessorPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FProcessorPage page);
}

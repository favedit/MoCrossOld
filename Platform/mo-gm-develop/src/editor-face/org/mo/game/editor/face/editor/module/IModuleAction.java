package org.mo.game.editor.face.editor.module;

import org.mo.web.core.container.AContainer;

import org.mo.game.editor.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IModuleAction
      extends
         IFormAble{

   String buildAllSource(IWebContext context,
                         @AContainer(name = "page") FModulePage page);

   String buildSource(IWebContext context,
                      @AContainer(name = "page") FModulePage page);

   String buildView(IWebContext context,
                    @AContainer(name = "page") FModulePage page);

   String catalog(IWebContext context,
                  @AContainer(name = "page") FModulePage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FModulePage page);

   String execute(IWebContext context,
                  @AContainer(name = "page") FModulePage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FModulePage page);

   String list(IWebContext context,
               @AContainer(name = "page") FModulePage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FModulePage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FModulePage page);
}

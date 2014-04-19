package org.mo.game.editor.face.editor.storage;

import org.mo.web.core.container.AContainer;

import org.mo.game.editor.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IStorageAction
      extends
         IFormAble{

   String buildAllSource(IWebContext context,
                         @AContainer(name = "page") FStoragePage page);

   String buildSource(IWebContext context,
                      @AContainer(name = "page") FStoragePage page);

   String catalog(IWebContext context,
                  @AContainer(name = "page") FStoragePage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FStoragePage page);

   String execute(IWebContext context,
                  @AContainer(name = "page") FStoragePage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FStoragePage page);

   String list(IWebContext context,
               @AContainer(name = "page") FStoragePage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FStoragePage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FStoragePage page);
}

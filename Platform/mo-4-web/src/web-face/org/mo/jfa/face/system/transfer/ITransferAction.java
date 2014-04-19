package org.mo.jfa.face.system.transfer;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

public interface ITransferAction
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FTransferPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FTransferPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FTransferPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FTransferPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FTransferPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FTransferPage page);
}

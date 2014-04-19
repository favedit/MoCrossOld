package org.mo.jfa.face.design.list;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

public interface IListAction
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FListPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FListPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FListPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FListPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FListPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FListPage page);
}

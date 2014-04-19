package org.mo.jfa.face.design.webtree;

import org.mo.web.core.container.AContainer;

import org.mo.logic.data.ISyCatalogDi;
import org.mo.logic.data.ISyCatalogNodeDi;
import org.mo.web.protocol.context.IWebContext;

public interface IWebTreeAction
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FWebTreePage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FWebTreePage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FWebTreePage page);

   String list(IWebContext context,
               @AContainer(name = "page") FWebTreePage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FWebTreePage page);

   String syncCatalog(IWebContext context,
                      ISyCatalogDi syCatalogDi,
                      ISyCatalogNodeDi syCatalogNodeDi,
                      @AContainer(name = "page") FWebTreePage page);

   String syncCatalogAll(IWebContext context,
                         ISyCatalogDi syCatalogDi,
                         ISyCatalogNodeDi syCatalogNodeDi,
                         @AContainer(name = "page") FWebTreePage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FWebTreePage page);
}

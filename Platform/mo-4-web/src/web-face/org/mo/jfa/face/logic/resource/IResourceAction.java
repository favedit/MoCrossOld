package org.mo.jfa.face.logic.resource;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

public interface IResourceAction
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FResourcePage page);

}

package org.mo.jfa.face.monitor.objects;

import org.mo.web.core.container.AContainer;
import org.mo.web.core.container.EContainerScope;

import org.mo.web.protocol.context.IWebContext;

public interface IObjectsAction
{

   String catalog(IWebContext context,
                  @AContainer(name = "page", scope = EContainerScope.Session) FObjectsPage page);

   String showObject(IWebContext context,
                     @AContainer(name = "page", scope = EContainerScope.Session) FObjectsPage page);

}

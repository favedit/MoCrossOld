package org.mo.jfa.face.monitor.objects;

import org.mo.web.core.container.AContainer;
import org.mo.web.core.container.EContainerScope;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IObjectsService
{

   public void process(IWebContext context,
                       @AContainer(name = "page", scope = EContainerScope.Session) FObjectsPage page,
                       IWebInput input,
                       IWebOutput output);

}

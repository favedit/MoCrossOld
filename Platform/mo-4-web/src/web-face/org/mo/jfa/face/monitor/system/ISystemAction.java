package org.mo.jfa.face.monitor.system;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

public interface ISystemAction
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") TSystemPage page);

   String showMemory(IWebContext context,
                     @AContainer(name = "page") TSystemPage page);

}

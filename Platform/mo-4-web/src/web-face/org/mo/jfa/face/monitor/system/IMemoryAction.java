package org.mo.jfa.face.monitor.system;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

public interface IMemoryAction
{

   String show(IWebContext context,
               @AContainer(name = "page") TMemoryPage page);

}

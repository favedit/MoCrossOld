package org.mo.web.core.servlet;

import org.mo.web.core.servlet.common.IWebServletResponse;
import org.mo.web.protocol.context.IWebContext;

public interface IWebServletConsole
{

   void execute(String name,
                IWebContext context,
                IWebServletResponse response);

   Object findInstance(String name);
}

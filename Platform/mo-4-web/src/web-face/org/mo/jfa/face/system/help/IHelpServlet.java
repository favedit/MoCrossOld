package org.mo.jfa.face.system.help;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.servlet.common.IWebServletResponse;
import org.mo.web.protocol.context.IWebContext;

public interface IHelpServlet
{

   void download(IWebContext context,
                 ISqlContext sqlContext,
                 IWebServletResponse response);

}

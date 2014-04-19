package org.mo.jfa.face.apl.page.process;

import org.mo.web.protocol.context.IWebContext;

public interface IEndProcessAction
{

   String construct(IWebContext context);

   String delete(IWebContext context);

   String insert(IWebContext context);

   String update(IWebContext context);

}

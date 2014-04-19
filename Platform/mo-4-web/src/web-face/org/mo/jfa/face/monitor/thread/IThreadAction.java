package org.mo.jfa.face.monitor.thread;

import org.mo.web.core.container.AContainer;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public interface IThreadAction
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FThreadPage page);

   String construct(IWebContext context,
                    @AContainer(name = "page", clear = true) FThreadPage page);

   String info(ISqlContext sqlContext,
               @AContainer(name = "page") FThreadPage page);

}

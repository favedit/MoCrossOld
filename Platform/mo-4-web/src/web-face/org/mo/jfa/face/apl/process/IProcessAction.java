package org.mo.jfa.face.apl.process;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public interface IProcessAction
{

   String construct(IWebContext context,
                    ISqlContext sqlContext);

}

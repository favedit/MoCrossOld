package org.mo.game.editor.face.editor.apl.process;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public interface IProcessAction{

   String construct(IWebContext context,
                    ISqlContext sqlContext);

}

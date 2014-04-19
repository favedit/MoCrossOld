package org.mo.game.editor.face.apl.process;

import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.protocol.context.IWebContext;

public interface IProcessAction{

   String construct(IWebContext context,
                    ISqlSessionContext sqlContext);

}

package org.mo.game.editor.web.session;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IUserSessionService{

   void list(IWebContext webContext,
             ISqlContext sqlContext,
             IWebInput input,
             IWebOutput output);

}

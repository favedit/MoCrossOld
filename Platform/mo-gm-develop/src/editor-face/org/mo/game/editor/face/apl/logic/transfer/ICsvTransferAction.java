package org.mo.game.editor.face.apl.logic.transfer;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.servlet.common.IWebServletResponse;
import org.mo.web.protocol.context.IWebContext;

public interface ICsvTransferAction{

   void process(IWebContext context,
                ISqlContext sqlContext,
                IWebServletResponse response);

}

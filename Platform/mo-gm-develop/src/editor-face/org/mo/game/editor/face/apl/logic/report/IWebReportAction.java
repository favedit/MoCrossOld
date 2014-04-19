package org.mo.game.editor.face.apl.logic.report;

import org.mo.web.core.container.AContainer;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.action.AWebLogin;
import org.mo.web.protocol.context.IWebContext;

@AWebLogin
public interface IWebReportAction{

   String construct(IWebContext context,
                    ISqlContext sqlContext,
                    @AContainer(name = "page") FWebReportPage page);

}

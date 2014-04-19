package org.mo.game.editor.face.inc.page;

import org.mo.web.protocol.context.IWebContext;

public interface IEndDataAction{

   String construct(IWebContext context);

   String delete(IWebContext context);

   String insert(IWebContext context);

   String update(IWebContext context);

}

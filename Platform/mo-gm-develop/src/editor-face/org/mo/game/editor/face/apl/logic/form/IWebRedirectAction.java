package org.mo.game.editor.face.apl.logic.form;

import org.mo.web.core.container.AContainer;

import org.mo.eng.data.common.ISqlContext;
import org.mo.game.editor.face.inc.form.IFormAble;
import org.mo.game.editor.web.session.FUserSessionPage;
import org.mo.web.protocol.context.IWebContext;

/**
 * 显示数据操作用的表单
 * 
 * @author maocy
 */
public interface IWebRedirectAction
      extends
         IFormAble{

   String construct(IWebContext context,
                    ISqlContext sqlContext,
                    @AContainer(name = "session") FUserSessionPage sessionPage,
                    @AContainer(name = "page") FWebFormPage formPage);

}

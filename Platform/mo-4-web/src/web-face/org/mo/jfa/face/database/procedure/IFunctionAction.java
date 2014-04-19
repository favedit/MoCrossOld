package org.mo.jfa.face.database.procedure;

import org.mo.web.core.container.AContainer;

import org.mo.eng.data.common.ISqlContext;
import org.mo.jfa.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IFunctionAction
      extends
         IFormAble
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FProcedurePage page);

   String update(IWebContext context,
                 ISqlContext sqlContext,
                 @AContainer(name = "page") FProcedurePage page);

}

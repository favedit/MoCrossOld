package org.mo.jfa.face.database.procedure;

import org.mo.web.core.container.AContainer;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public interface IPackageAction
{

   public String build(ISqlContext sqlContext,
                       IWebContext webContext,
                       @AContainer(name = "page") FProcedurePage page);

   public String buildAll(ISqlContext sqlContext,
                          IWebContext webContext,
                          @AContainer(name = "page") FProcedurePage page);

   String catalog(IWebContext context,
                  @AContainer(name = "page") FProcedurePage page);

   public String execute(IWebContext context,
                         ISqlContext sqlContext,
                         @AContainer(name = "page") FProcedurePage page);

   String list(IWebContext context,
               @AContainer(name = "page") FProcedurePage page);

   String update(IWebContext context,
                 ISqlContext sqlContext,
                 @AContainer(name = "page") FProcedurePage page);
}

package org.mo.jfa.face.monitor.database;

import org.mo.web.core.container.AContainer;

import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public interface IDatabaseAction
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FDatabasePage page,
                  IDatabaseConsole dbConsole);

   String construct(IWebContext context,
                    @AContainer(name = "page", clear = true) FDatabasePage page);

   String info(ISqlContext sqlContext,
               @AContainer(name = "page") FDatabasePage page);

   String showConsole(IWebContext context,
                      @AContainer(name = "page") FDatabasePage page,
                      IDatabaseConsole dbConsole);

}

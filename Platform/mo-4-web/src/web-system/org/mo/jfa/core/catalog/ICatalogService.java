package org.mo.jfa.core.catalog;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface ICatalogService
{

   void loadGroups(IWebContext webContext,
                   ISqlContext sqlContext,
                   IWebInput input,
                   IWebOutput output);

   void loadNodes(IWebContext webContext,
                  ISqlContext sqlContext,
                  IWebInput input,
                  IWebOutput output);

}

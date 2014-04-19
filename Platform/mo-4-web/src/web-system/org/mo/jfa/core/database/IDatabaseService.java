package org.mo.jfa.core.database;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IDatabaseService
{

   void list(IWebContext webContext,
             ISqlContext sqlContext,
             IWebInput input,
             IWebOutput output);

   void listTables(IWebContext webContext,
                   ISqlContext sqlContext,
                   IWebInput input,
                   IWebOutput output);

   void listViews(IWebContext webContext,
                  ISqlContext sqlContext,
                  IWebInput input,
                  IWebOutput output);

   void listFields(IWebContext webContext,
                   ISqlContext sqlContext,
                   IWebInput input,
                   IWebOutput output);

}

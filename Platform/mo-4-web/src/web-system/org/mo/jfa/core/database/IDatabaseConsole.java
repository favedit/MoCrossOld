package org.mo.jfa.core.database;

import org.mo.com.xml.FXmlNodes;
import org.mo.eng.data.common.ISqlContext;

public interface IDatabaseConsole
{

   FTableNode findTable(ISqlContext context,
                        String name);

   FViewNode findView(ISqlContext context,
                      String name);

   FXmlNodes list(ISqlContext context);

   FXmlNodes listFields(ISqlContext context,
                        String name);

   FTableNodes listTables(ISqlContext context);

   FViewNodes listViews(ISqlContext context);

}

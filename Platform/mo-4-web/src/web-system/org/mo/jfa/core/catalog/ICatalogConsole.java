package org.mo.jfa.core.catalog;

import org.mo.eng.data.common.ISqlContext;

public interface ICatalogConsole
{

   FCatalogGroups loadGroups(ISqlContext sqlContext);

   FCatalogNodes loadNodes(ISqlContext sqlContext);

}

package org.mo.jfa.core.page;

import org.mo.com.xml.FXmlNodes;
import org.mo.eng.data.common.ISqlContext;

public interface IPageConsole
{

   public FXmlNodes list(String path);

   public FXmlNodes list(ISqlContext context,
                         String type,
                         String name);

}

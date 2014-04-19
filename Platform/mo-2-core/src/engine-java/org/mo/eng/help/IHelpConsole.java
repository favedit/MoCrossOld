package org.mo.eng.help;

import org.mo.eng.help.common.XHelp;
import org.mo.eng.store.IXmlConfigConsole;

public interface IHelpConsole
      extends
         IXmlConfigConsole<XHelp>
{
   void build(FHelpBuilderArgs args);
}

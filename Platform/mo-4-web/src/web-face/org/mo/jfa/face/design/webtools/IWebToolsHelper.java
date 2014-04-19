package org.mo.jfa.face.design.webtools;

import org.mo.eng.help.IBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;

public interface IWebToolsHelper
      extends
         IBuildHelper
{

   void buildWebToolsAll(XHelp xhelp,
                         XAction xaction);

   void buildItemdAll(XHelp xhelp,
                      XAction xaction);

   void buildList(XHelp xhelp,
                  XAction xaction);

}

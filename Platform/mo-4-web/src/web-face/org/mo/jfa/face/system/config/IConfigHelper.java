package org.mo.jfa.face.system.config;

import org.mo.eng.help.IBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;

public interface IConfigHelper
      extends
         IBuildHelper
{

   void buildConfigAll(XHelp xhelp,
                       XAction xaction);

   void buildItemdAll(XHelp xhelp,
                      XAction xaction);

   void buildList(XHelp xhelp,
                  XAction xaction);

}

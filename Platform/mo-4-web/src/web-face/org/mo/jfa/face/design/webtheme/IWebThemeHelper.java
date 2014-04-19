package org.mo.jfa.face.design.webtheme;

import org.mo.eng.help.IBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;

public interface IWebThemeHelper
      extends
         IBuildHelper
{

   void list(XHelp xhelp,
             XAction xaction);

   void bulid(XHelp xhelp,
              XAction xaction);

}

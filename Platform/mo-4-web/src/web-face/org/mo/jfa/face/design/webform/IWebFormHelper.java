package org.mo.jfa.face.design.webform;

import org.mo.eng.help.IBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;

public interface IWebFormHelper
      extends
         IBuildHelper
{

   void bulidAllList(XHelp xhelp,
                     XAction xaction);

   void buildWebFormList(XHelp xhelp,
                         XAction xaction);

   void buildWebTableList(XHelp xhelp,
                          XAction xaction);

   void buildAttribute(XHelp xhelp,
                       XAction xaction);

   void list(XHelp xhelp,
             XAction xaction);
}

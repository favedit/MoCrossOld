package org.mo.game.editor.face.editor.module;

import org.mo.eng.help.IBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;

public interface IModuleHelper
      extends
         IBuildHelper{

   void buildRightDetail(XHelp xhelp,
                         XAction xaction);

   void bulidRightGroupList(XHelp xhelp,
                            XAction xaction);
}

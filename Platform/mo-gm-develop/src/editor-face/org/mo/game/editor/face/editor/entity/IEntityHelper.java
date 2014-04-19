package org.mo.game.editor.face.editor.entity;

import org.mo.eng.help.IBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;

public interface IEntityHelper
      extends
         IBuildHelper{

   void buildRightDetail(XHelp xhelp,
                         XAction xaction);

   void bulidRightGroupList(XHelp xhelp,
                            XAction xaction);
}

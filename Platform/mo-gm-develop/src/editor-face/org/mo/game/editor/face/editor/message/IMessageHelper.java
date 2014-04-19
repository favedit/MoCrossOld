package org.mo.game.editor.face.editor.message;

import org.mo.eng.help.IBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;

public interface IMessageHelper
      extends
         IBuildHelper{

   void buildRightDetail(XHelp xhelp,
                         XAction xaction);

   void bulidRightGroupList(XHelp xhelp,
                            XAction xaction);
}

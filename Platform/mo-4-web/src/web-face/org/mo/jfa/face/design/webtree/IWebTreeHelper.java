package org.mo.jfa.face.design.webtree;

import org.mo.eng.help.IBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;

public interface IWebTreeHelper
      extends
         IBuildHelper
{

   void buildTreeViewAll(XHelp xhelp,
                         XAction xaction);

   void buildTreeNodeAll(XHelp xhelp,
                         XAction xaction);

   void buildList(XHelp xhelp,
                  XAction xaction);

}

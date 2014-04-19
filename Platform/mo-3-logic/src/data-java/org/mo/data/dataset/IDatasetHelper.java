package org.mo.data.dataset;

import org.mo.eng.help.IBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;

public interface IDatasetHelper
      extends
         IBuildHelper
{
   void buildDatasetAll(XHelp xhelp,
                        XAction xaction);

   void buildFieldAll(XHelp xhelp,
                      XAction xaction);

   void buildList(XHelp xhelp,
                  XAction xaction);
}

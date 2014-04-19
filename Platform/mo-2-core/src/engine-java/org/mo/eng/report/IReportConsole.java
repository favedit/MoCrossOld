package org.mo.eng.report;

import org.mo.eng.report.common.XReport;
import org.mo.eng.store.IXmlConfigConsole;

public interface IReportConsole
      extends
         IXmlConfigConsole<XReport>
{
   String getPath();

   String makeStorePath(String name);

   String makeTempPath(String uuid);
}

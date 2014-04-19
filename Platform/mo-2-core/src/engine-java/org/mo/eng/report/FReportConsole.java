package org.mo.eng.report;

import org.mo.com.io.RFile;
import org.mo.com.lang.FObjects;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.report.common.XReport;
import org.mo.eng.store.FXmlConfigConsole;

public class FReportConsole
      extends FXmlConfigConsole<XReport>
      implements
         IReportConsole
{
   public final static String DESIGN_REPORT = "design.report";

   @ALink
   protected IDatabaseConsole _databaseConsole;

   @AProperty
   protected String _storePath;

   @AProperty
   protected String _tempPath;

   @Override
   protected FObjects<XReport> createCollection(){
      return new FObjects<XReport>(XReport.class);
   }

   @Override
   public String getPath(){
      return _storePath;
   }

   @Override
   public String makeStorePath(String name){
      return RFile.makeFilename(_storePath, name);
   }

   @Override
   public String makeTempPath(String uuid){
      return RFile.makeFilename(_tempPath, uuid);
   }
}

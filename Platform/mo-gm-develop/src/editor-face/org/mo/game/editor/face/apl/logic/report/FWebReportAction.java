package org.mo.game.editor.face.apl.logic.report;

import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperRunManager;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.lang.RUuid;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.report.IReportConsole;
import org.mo.eng.report.common.XReport;
import org.mo.logic.store.IStoreConsole;
import org.mo.web.protocol.context.IWebContext;

public class FWebReportAction
      implements
         IWebReportAction
{

   private static ILogger _logger = RLogger.find(FWebReportAction.class);

   public static final String PAGE_DOWNLOAD = "Download";

   public final static String PTY_FORM_PACK = "form_pack";

   public final static String PTY_REPORT = "report";

   @ALink
   protected IReportConsole _reportConsole;

   @ALink
   protected IStoreConsole _storeConsole;

   public String construct(IWebContext context,
                           ISqlContext sqlContext,
                           FWebReportPage page){
      // 获得报表配置名称
      String reportName = context.parameter(PTY_REPORT);
      if(RString.isEmpty(reportName)){
         throw new FFatalError("The report name is null. (reportName={0})", reportName);
      }
      // 获得报表的存储名称
      IXmlObject xreport = _reportConsole.get(reportName);
      String storeName = xreport.innerGet(XReport.PTY_REPORT_NAME);
      if(RString.isEmpty(storeName)){
         storeName = "ExportPDF{YYMMDD-HH24MI}";
      }
      if(!RString.contains(storeName, ".pdf")){
         storeName += ".pdf";
      }
      String exportNameDate = RString.mid(storeName, "{", "}");
      String newDateName = RDateTime.format(exportNameDate);
      storeName = RString.replace(storeName, exportNameDate, newDateName);
      storeName = RString.removeString(storeName, "{");
      storeName = RString.removeString(storeName, "}");
      // 获得报表的存储模板
      // 获得报表的引用模板
      String sourceName = _reportConsole.makeStorePath(xreport.innerGet(XReport.PTY_REFERENCE));
      if(RString.isEmpty(sourceName)){
         throw new FFatalError("The report template file is empty.");
      }
      // 输出前，需要判断是否要输出。
      if(_logger.debugAble()){
         _logger.debug(this, "construct", "Begin build pdf report. (report={0}, source={1})", reportName, sourceName);
      }
      // 设置参数，从form_pack中获得
      // 格式为“$P!{变量}”就可以替换SQL语句中对应的变量(较为复杂的替换)
      // 简单的替换数字变量可使用“$P{变量替换}”
      HashMap<Object, Object> properties = new HashMap<Object, Object>();
      IAttributes attributes = new FAttributes();
      attributes.unpack(context.parameter(PTY_FORM_PACK));
      if(!attributes.isEmpty()){
         int attributeCount = attributes.count();
         for(int n = 0; n < attributeCount; n++){
            properties.put(attributes.name(n), attributes.value(n));
         }
      }
      String subReportDir = _reportConsole.getPath() + "/";
      properties.put("SUBREPORT_DIR", subReportDir);
      properties.put("REPORT_NAME", storeName);
      // 获得临时存储文件名称
      String tempName = RUuid.simpleUuid() + "/" + storeName;
      page.setTempName(tempName);
      String tempFile = _reportConsole.makeTempPath(tempName);
      // 生成PDF文件
      try{
         RFile.makeFileDirectory(tempFile);
         Connection connection = sqlContext.activeConnection().sqlConnection();
         JasperRunManager.runReportToPdfFile(sourceName, tempFile, properties, connection);
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return PAGE_DOWNLOAD;
   }

}

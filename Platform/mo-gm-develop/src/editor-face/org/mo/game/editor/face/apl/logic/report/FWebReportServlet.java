package org.mo.game.editor.face.apl.logic.report;

import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperRunManager;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.report.IReportConsole;
import org.mo.eng.report.common.XReport;
import org.mo.web.core.servlet.common.IWebServletResponse;
import org.mo.web.protocol.context.IWebContext;

public class FWebReportServlet
      implements
         IWebReportServlet
{

   private static ILogger _logger = RLogger.find(FWebReportServlet.class);

   public final static String REPORT_NAME = "report";

   public final static String FORM_PACK = "form_pack";

   @ALink
   protected IReportConsole _reportConsole;

   @Override
   @SuppressWarnings({"unchecked", "rawtypes"})
   public void process(IWebContext context,
                       ISqlContext sqlContext,
                       IWebServletResponse response){
      try{
         // 获得报表配置名称
         String reportName = context.parameter(REPORT_NAME);
         if(RString.isNotEmpty(reportName)){
            _logger.debug(this, "build", "Get report name. (reportName={0})", reportName);
            // 根据名称获得xml对象
            IXmlObject xreport = _reportConsole.get(reportName);
            String saveName = xreport.innerGet(XReport.PTY_REPORT_NAME);
            if(RString.isEmpty(saveName)){
               saveName = "ExportPDF{YYMMDD-HH24MI}";
            }
            if(!RString.contains(saveName, ".pdf")){
               saveName = saveName + ".pdf";
            }
            String exportNameDate = RString.mid(saveName, "{", "}");
            String newDateName = RDateTime.format(exportNameDate);
            saveName = RString.replace(saveName, exportNameDate, newDateName);
            saveName = RString.removeString(saveName, "{");
            saveName = RString.removeString(saveName, "}");
            // 获得报表的引用模板
            String configName = xreport.innerGet(XReport.PTY_REFERENCE);
            configName = _reportConsole.makeStorePath(configName);
            if(RString.isNotEmpty(configName)){
               _logger.debug(this, "build", "Get pdf template name. (configName={0})", configName);
               // 获得数据库连接
               Connection connection = sqlContext.activeConnection().sqlConnection();
               if(connection != null){
                  // 设置参数，从form_pack中获得
                  // 格式为“$P!{变量}”就可以替换SQL语句中对应的变量(较为复杂的替换)
                  // 简单的替换数字变量可使用“$P{变量替换}”
                  HashMap properties = new HashMap();
                  IAttributes attributes = new FAttributes();
                  attributes.unpack(context.parameter(FORM_PACK));
                  if(!attributes.isEmpty()){
                     int attributeCount = attributes.count();
                     for(int n = 0; n < attributeCount; n++){
                        properties.put(attributes.name(n), attributes.value(n));
                     }
                  }
                  byte[] bytes = JasperRunManager.runReportToPdf(configName, properties, connection);
                  if(bytes.length != 0){
                     _logger.debug(this, "build", "Build pdf file. (size={0})", bytes.length);
                     response.setContentType("application/pdf");
                     response.addHeader("Content-Disposition", "attachement;filename=" + saveName);
                     response.setCharacterEncoding("GBK");
                     response.setContentLength(bytes.length);
                     response.write(bytes);
                  }
               }else{
                  _logger.debug(this, "build", "The connection data is null. (connection={0})", connection);
               }
            }
         }else{
            _logger.debug(this, "build", "The report name is null. (reportName={0})", reportName);
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }finally{

      }
   }
}

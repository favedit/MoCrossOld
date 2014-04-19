package org.mo.game.editor.face.apl.logic.transfer;

import java.net.URLEncoder;
import org.mo.com.collections.FRow;
import org.mo.com.data.FSql;
import org.mo.com.data.ISqlDatasetReader;
import org.mo.com.io.FByteFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.lang.RUuid;
import org.mo.com.lang.temp.RPack;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.transfer.ITransferConsole;
import org.mo.eng.transfer.common.XColumn;
import org.mo.eng.transfer.common.XExport;
import org.mo.mime.csv.FCsvColumn;
import org.mo.mime.csv.FCsvWriter;
import org.mo.mime.csv.ICsvLine;
import org.mo.mime.csv.RCsvCommand;
import org.mo.web.core.servlet.common.IWebServletResponse;
import org.mo.web.protocol.context.IWebContext;

public class FCsvTransferAction
      implements
         ICsvTransferAction
{

   private static ILogger _logger = RLogger.find(FCsvExportServlet.class);

   public final static String SQL_NAME = "SQL";

   public final static String TRANSFER_NAME = "transfer";

   private final String _encode = "GB18030";

   @ALink
   protected ITransferConsole _transferConsole;

   protected IAttributes executeParameters(IWebContext context,
                                           ISqlContext sqlContext,
                                           IXmlObject sqlObject){
      FRow row = null;
      try{
         // 获得要执行的数据参数
         String datasetParameters = sqlObject.innerGet(XExport.PTY_DATASET_PARAMETERS);
         if(RString.isNotEmpty(datasetParameters)){
            // 获得前台的信息
            String fromPack = context.parameter("form_pack");
            IAttributes attributes = new FAttributes();
            attributes.unpack(fromPack);
            FSql parametersCommand = new FSql();
            // 替换从页面传过来的变量格式为${变量}
            RPack.replaceLink(parametersCommand, datasetParameters, attributes);
            if(_logger.debugAble()){
               _logger.debug(this, "executeParameters", "Excute dataset parameters={0}", parametersCommand);
            }
            row = sqlContext.activeConnection().find(parametersCommand);
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return row;
   }

   @Override
   public void process(IWebContext context,
                       ISqlContext sqlContext,
                       IWebServletResponse response){
      try{
         // 调用格式datasetName@transferName
         String transferConfig = context.parameter(TRANSFER_NAME);
         // 数据名称
         String datasetName = RString.left(transferConfig, "@");
         // 传输名称
         String transferName = RString.right(transferConfig, "@");
         if(RString.isNotEmpty(datasetName) && RString.isNotEmpty(transferName)){
            if(_logger.debugAble()){
               _logger.debug(this, "build", "Get transfer name. (transferName={0}, datasetName={1})", transferName, datasetName);
            }
            IXmlObject transfer = _transferConsole.get(transferName);
            // 根据数据名称查找数据IXmlObject对象
            IXmlObject dataset = transfer.search("name", datasetName);
            // 写入csv
            String csvName = writeCsv(context, sqlContext, response, dataset, transfer);
            if("Export".equals(dataset.name())){
               // 读出csv
               readCsv(context, sqlContext, csvName, dataset, response);
            }
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   protected String readCsv(IWebContext context,
                            ISqlContext sqlContext,
                            String csvName,
                            IXmlObject sqlObject,
                            IWebServletResponse response){

      // 读取数据并传入到客户端
      try{
         FByteFile bytesFile = new FByteFile(csvName);
         if(!bytesFile.isEmpty()){
            // 设置内容类型
            String exportName = sqlObject.innerGet(XExport.PTY_EXPORT_NAME);
            String exportCharset = sqlObject.innerGet(XExport.PTY_EXPORT_CHARSET);
            if(RString.isEmpty(exportName)){
               exportName = "ExportCSVFile{YYMMDD-HH24MI}";
            }
            if(!RString.contains(exportName, ".csv")){
               exportName = exportName + ".csv";
            }
            // 如果导出格式为空，则设为UTF8
            if(RString.isEmpty(exportCharset)){
               exportCharset = "GB18030";
            }
            String fromPack = context.parameter("form_pack");
            IAttributes attributes = new FAttributes();
            attributes.unpack(fromPack);
            FString fExportName = new FString();
            // 替换从页面传过来的变量格式为${变量}
            RPack.replaceParameters(fExportName, exportName, attributes);
            // 执行后的获得数据
            IAttributes parameters = executeParameters(context, sqlContext, sqlObject);
            // 替换文件名中的数据参数
            FString newExportName = new FString();
            RPack.replaceParameters(newExportName, fExportName.toString(), parameters);
            exportName = newExportName.toString();
            response.setContentLength(bytesFile.length());
            // 替换时间，如果字符串中含有{YYMMDD-HH24MI}则替换{YYMMDD-HH24MI}为当前时间
            response.setContentLength(bytesFile.length());
            if(RString.contains(exportName, "{") && RString.contains(exportName, "}")){
               String exportNameDate = RString.mid(exportName, "{", "}");
               String newDateName = RDateTime.format(exportNameDate);
               exportName = RString.replace(exportName, exportNameDate, newDateName);
               exportName = RString.removeString(exportName, "{");
               exportName = RString.removeString(exportName, "}");
            }
            // 根据设置项设置输入文件名称
            response.setContentLength(bytesFile.length());
            if(RBoolean.parse(sqlObject.innerGet(XExport.PTY_IS_OPEN))){
               response.setContentType("application/csv-msdownload");
               response.setCharacterEncoding(exportCharset);
               response.addHeader("Content-Disposition", "attachement;filename=" + URLEncoder.encode(exportName, "UTF-8"));
            }else{
               response.setContentLength(bytesFile.length());
               response.setContentType("application/csv");
               response.setCharacterEncoding(exportCharset);
               response.addHeader("Content-Disposition", "attachement;filename=" + URLEncoder.encode(exportName, "UTF-8"));
            }
            response.write(bytesFile.toArray());
         }
         RFile.delete(csvName);
      }catch(Exception e){
         throw new FFatalError(e, "Csv read failure. (path={0})", csvName);
      }
      return null;
   }

   protected String writeCsv(IWebContext context,
                             ISqlContext sqlContext,
                             IWebServletResponse response,
                             IXmlObject dataset,
                             IXmlObject transfer){
      FCsvWriter writer = new FCsvWriter();
      String pathTemporary = null;
      try{
         if(null != dataset){
            // 根据数据名称获得要执行的SQL文
            String sql = dataset.innerGet(XExport.PTY_SQL);
            FSql fsql = new FSql();
            String fromPack = context.parameter("form_pack");
            IAttributes attributes = new FAttributes();
            attributes.unpack(fromPack);
            // 替换变量
            RPack.replaceParameters(fsql, sql, attributes);
            IAttributes parameters = executeParameters(context, sqlContext, dataset);
            FString newSql = new FString();
            RPack.replaceParameters(newSql, fsql.toString(), parameters);
            // 随机产生保存的csv路径
            pathTemporary = _transferConsole.makeStorePath(RUuid.simpleUuid() + ".csv");
            RFile.makeFileDirectory(pathTemporary);
            _logger.debug(this, "excute", "Excute sql={0}", newSql);
            // 打开路径格式为GB18030
            writer.openFile(pathTemporary, _encode);
            // 获得列集
            IXmlObject columns = transfer.find("Columns");
            // 写入字段name和label
            if(null != columns && columns.hasChild()){
               for(IXmlObject columnXml : columns.children()){
                  FCsvColumn column = new FCsvColumn();
                  String dataName = columnXml.innerGet(XColumn.PTY_DATA_NAME);
                  column.setName(dataName);
                  column.setLabel(columnXml.innerGet(XColumn.PTY_LABEL));
                  writer.columns().set(dataName, column);
               }
               if(RBoolean.parse(dataset.innerGet(XExport.PTY_DISPLAY_HEAD))){
                  writer.doCommand(RCsvCommand.Head, RBoolean.parse(dataset.innerGet(XExport.PTY_DISPLAY_HEAD_TAG)));
               }
               if(RBoolean.parse(dataset.innerGet(XExport.PTY_DISPLAY_LABEL))){
                  writer.doCommand(RCsvCommand.Label, RBoolean.parse(dataset.innerGet(XExport.PTY_DISPLAY_LABEL_TAG)));
               }
               writer.doCommand(RCsvCommand.Data, RBoolean.parse(dataset.innerGet(XExport.PTY_DISP_DATA_START_TAG)));
               int attributesCount = writer.columns().count();
               ICsvLine line = writer.createLine();
               // 保存数据
               if(!newSql.isEmpty()){
                  // 从数据库中读取数据
                  ISqlDatasetReader datasetReader = sqlContext.activeConnection().fetchReader(newSql);
                  while(datasetReader.hasNext()){
                     line.clear();
                     FRow row = datasetReader.next();
                     for(int n = 0; n < attributesCount; n++){
                        String fileName = writer.columns().name(n);
                        String rowValue = row.get(fileName);
                        if(RString.isNotEmpty(rowValue)){
                           line.set(fileName, row.get(fileName));
                        }
                     }
                     writer.writeLine(line);
                  }
               }
               writer.doCommand(RCsvCommand.End, RBoolean.parse(dataset.innerGet(XExport.PTY_DISP_DATA_END_TAG)));
               if(RBoolean.parse(dataset.innerGet(XExport.PTY_DISPLAY_VALID))){
                  writer.doCommand(RCsvCommand.ValidLines, RBoolean.parse(dataset.innerGet(XExport.PTY_DISPLAY_VALID_TAG)));
               }
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Csv store failure. (path={0})", pathTemporary);
      }finally{
         if(null != writer){
            writer.Close();
            writer = null;
         }
      }
      return pathTemporary;
   }

}

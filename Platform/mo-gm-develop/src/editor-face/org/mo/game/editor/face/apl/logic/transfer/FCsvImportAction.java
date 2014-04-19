package org.mo.game.editor.face.apl.logic.transfer;

import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.lang.RUuid;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.eng.transfer.FCsvImport;
import org.mo.eng.transfer.ITransferConsole;
import org.mo.game.editor.face.apl.page.IPublicPage;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.mime.csv.FCsvColumns;
import org.mo.mime.csv.FCsvLine;
import org.mo.mime.csv.FCsvReader;
import org.mo.web.protocol.context.IWebContext;

public class FCsvImportAction
      implements
         ICsvImportAction
{

   public final static ILogger _logger = RLogger.find(FCsvImportAction.class);

   public final static String PTY_CONFIG = "config_";

   public final static String PTY_FILE_NAME_PATH = "file_path_name";

   public final static String PTY_LOGIC = "logic_";

   public final static String PTY_PARAMETER = "params_";

   public final static String PTY_CSV_LINE = "csv_line";

   @ALink
   protected ITransferConsole _console;

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.logic.transfer.ICsvImportAction#upload(org.mo.web.protocol.context.IWebContext, org.mo.logic.session.ISqlSessionContext, com.linekong.euis.face.apl.logic.transfer.FCsvImportPage)
    */
   @Override
   public String upload(IWebContext context,
                        ISqlSessionContext sqlContext,
                        FCsvImportPage page){
      page.setFile(null);
      return "CsvImport";
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.logic.transfer.ICsvImportAction#uploadSave(org.mo.web.protocol.context.IWebContext, org.mo.logic.session.ISqlSessionContext, org.mo.logic.data.IPmUserDi, com.linekong.euis.face.apl.logic.transfer.FCsvImportPage)
    */
   @Override
   public String uploadSave(IWebContext context,
                            ISqlSessionContext sqlContext,
                            FCsvImportPage page){
      // 读取服务器上文件
      if(!context.files().isEmpty()){
         String fileName = context.files().get(0).uploadName();
         _logger.debug(this, "uploadSave", "Upload file (filename={0})", fileName);
         if(!RString.isEmpty(fileName)){
            // 保存上传文件
            String uploadFile = _console.makeUploadPath(RDateTime.format("YYYYMMDD-HH24MISS") + "[" + RUuid.simpleUuid() + "].csv");
            _logger.debug(this, "uploadSave", "Store upload file (filename={0})", uploadFile);
            RFile.copyFile(fileName, uploadFile);
            // 读取上传文件
            String charset = RString.nvl(page.getCharset(), "GB18030");
            FCsvReader reader = new FCsvReader(fileName, charset);
            if(reader.hasNext()){
               FCsvLine csvLine = reader.returnHead();
               FCsvImport file = _console.findCsvImport(context.parameter(PTY_FILE_NAME_PATH), csvLine.toObjects(), null);
               file.loadFileWithoutHead(fileName, charset);
               // 创建配置集合
               IAttributes config = new FAttributes();
               // 获得预处理过程
               String executeBeforeString = file.executePlsqlBefore();
               if(!RString.isEmpty(executeBeforeString)){
                  FSqlProcedure procedure = new FSqlProcedure(executeBeforeString);
                  procedure.createParameter(PTY_LOGIC, null, ESqlDataType.String, ESqlDataDirection.InOut);
                  procedure.createParameter(PTY_CONFIG, config.pack(), ESqlDataType.String, ESqlDataDirection.InOut);
                  sqlContext.activeConnection().execute(procedure);
               }
               // 每一行处理
               String executeString = file.executePlsql();
               if(!RString.isEmpty(executeString)){
                  // 得到执行字符串
                  FSqlProcedure procedure = new FSqlProcedure(executeString);
                  procedure.createParameter(PTY_LOGIC, null, ESqlDataType.String, ESqlDataDirection.InOut);
                  procedure.createParameter(PTY_CONFIG, null, ESqlDataType.String, ESqlDataDirection.InOut);
                  procedure.createParameter(PTY_PARAMETER, null, ESqlDataType.String, ESqlDataDirection.InOut);
                  // 读取行对象
                  int length = file.lines().count();
                  // 得到第一行数据
                  FCsvLine firstLine = file.lines().get(0);
                  // 读取配置文件列
                  FCsvColumns columns = file.columns();
                  int count = columns.count();
                  // 循环列
                  for(int n = 0; n < count; n++){
                     boolean isRequire = columns.value(n).isRequire();
                     if(isRequire){
                        columns.value(n).setIsValid(true);
                     }else{
                        String is_valid = firstLine.value(n);
                        columns.value(n).setIsValid(RBoolean.parse(is_valid));
                     }
                  }
                  // 循环取出数据 
                  for(int n = 1; n < length; n++){
                     FCsvLine line = file.lines().get(n);
                     String linePack = line.pack();
                     /// 调用数据包操作数据
                     if(RString.isNotBlank(linePack)){
                        config.set(PTY_CSV_LINE, Integer.toString(n));
                        procedure.parameter(PTY_CONFIG).set(config.pack());
                        procedure.parameter(PTY_PARAMETER).set(linePack);
                        sqlContext.activeConnection().execute(procedure);
                     }
                  }
               }
               // 获得结束处理过程
               String executeAfterString = file.executePlsqlAfter();
               if(!RString.isEmpty(executeAfterString)){
                  FSqlProcedure procedure = new FSqlProcedure(executeAfterString);
                  procedure.createParameter(PTY_LOGIC, null, ESqlDataType.String, ESqlDataDirection.InOut);
                  procedure.createParameter(PTY_CONFIG, config.pack(), ESqlDataType.String, ESqlDataDirection.InOut);
                  sqlContext.activeConnection().execute(procedure);
               }
            }
         }
      }
      return IPublicPage.PROCESS_SUCCESS;

   }
}

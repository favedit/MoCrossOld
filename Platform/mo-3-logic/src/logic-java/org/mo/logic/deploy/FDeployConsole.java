package org.mo.logic.deploy;

import org.mo.com.data.ISqlConnection;
import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.mime.csv.FCsvReader;

public class FDeployConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         IDeployConsole
{
   // CSV文件
   private final String _csv = ".csv";

   @ALink
   protected IDatasetConsole _datasetConsole;

   @AProperty
   protected String _datasetPath;

   @ALink
   protected IDatabaseConsole _deployConsole;

   // 编码类型
   private final String _encode = "UTF-8";

   @AProperty
   protected String _logicUnit;

   @AProperty
   protected String _enum;

   @AProperty
   protected String _installSqlViewUser;

   @AProperty
   protected String _uninstallSqlViewUser;

   @AProperty
   protected String _installSqlSystem;

   @AProperty
   protected String _uninstallSqlSystem;

   @AProperty
   protected String _installDataTable;

   @AProperty
   protected String _uninstallDataTable;

   @AProperty
   protected String _installSqlType;

   @AProperty
   protected String _uninstallSqlType;

   // 日志
   private final ILogger _logger = RLogger.find(FDeployConsole.class);

   // 普通包头与系统包头的文件扩展名
   private final String _pkh = ".pkh";

   // 普通包体与系统包体的文件扩展名
   private final String _pky = ".pky";

   // SQL文件的扩展名
   private final String _sql = ".sql";

   // 类型包头的文件扩展名
   private final String _tph = ".tph";

   // 类型包体的文件扩展名
   private final String _tpy = ".tpy";

   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }

   public void deployInstallData(FStrings files){
      FCsvReader reader = null;
      files.sort();
      String datasetName = "";
      try{
         _datasetConsole.tableForeignKeyDisableAll();
         for(String fileName : files){
            if(fileName.endsWith(_csv)){
               reader = new FCsvReader();
               reader.openFile(fileName);
               reader.openSegment();
               datasetName = reader.properties().get("dataset_name").value();
               reader.close();
               IXmlObject xdataset = _datasetConsole.get(datasetName);
               _datasetConsole.dataRestore(xdataset, false);
            }
         }
         _datasetConsole.tableForeignKeyEnableAll();
      }catch(Exception e){
         _logger.error("", "deployInstallData", datasetName);
      }
   }

   /**
    * <T>执行包反应到数据库</T>
    * 
    * @param files
    * @param fl
    */
   public void deployPackage(FStrings files){
      ISqlConnection connection = null;
      files.sort();
      try{
         // 建立数据库连接
         connection = _deployConsole.alloc();
         for(String file : files){
            if(file.endsWith(_pkh) || file.endsWith(_pky) || file.endsWith(_tph) || file.endsWith(_tpy)){
               connection.executeSql(loadFile(file).toString());
            }
         }
      }finally{
         if(null != connection){
            _deployConsole.free(connection);
         }
      }
   }

   /**
    * <T>执行SQL</T>
    * 
    * @param files
    * @param fl
    */
   public void deploySql(FStrings files){
      ISqlConnection connection = null;
      files.sort();
      try{
         // 建立数据库连接
         connection = _deployConsole.alloc();
         for(String fileName : files){
            if(fileName.endsWith(_sql)){
               FStringFile file = new FStringFile(fileName, _encode);
               connection.executeSqls(file.toString());
            }
         }
      }finally{
         if(null != connection){
            _deployConsole.free(connection);
         }
      }
   }

   public void deployUnInstallData(FStrings files){
      String datasetName = "";
      try{
         _datasetConsole.tableForeignKeyDisableAll();
         for(String file : files){
            if(file.endsWith(_sql)){
               int finded = file.lastIndexOf('\\');
               int findPonit = file.lastIndexOf('.');
               if(-1 != finded && -1 != findPonit){
                  datasetName = file.substring(finded + 1, findPonit);
               }
               IXmlObject xdataset = _datasetConsole.get(datasetName);
               _datasetConsole.dataDelete(xdataset, false);
            }
         }
         _datasetConsole.tableForeignKeyEnableAll();
      }catch(Exception e){
         _logger.error("", "deployUnInstallData", datasetName);
      }
   }

   //   protected void excute(String pack,
   //                         String produrce){
   //      ISqlConnection connection = null;
   //      try{
   //         // 建立数据库连接
   //         connection = _deployConsole.alloc();
   //         FSqlProcedure procedure = new FSqlProcedure(produrce);
   //         procedure.setLogicName(pack);
   //         procedure.createParameter("logic_", null, ESqlDataType.String, ESqlDataDirection.InOut);
   //         connection.activeConnection().execute(procedure);
   //      }finally{
   //         if(null != connection){
   //            _deployConsole.free(connection);
   //         }
   //      }
   //   }
   public String getWorkpath(){
      return _workpath;
   }

   @Override
   public void install(EDeploySource type){
      if(type.equals(EDeploySource.SqlTable)){
         // 建立所有表
         FStrings files = RFile.searchFiles(_logicUnit, "install_table.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlView)){
         // 建立所有视图
         FStrings files = RFile.searchFiles(_logicUnit, "install_view.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlViewUser)){
         // 建立所有用户视图
         FStrings files = RFile.searchFiles(_installSqlViewUser, null, _sql);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlSequence)){
         // 建立所有序列
         FStrings files = RFile.searchFiles(_logicUnit, "install_sequence.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlSystemHead)){
         // 建立系统包头
         FStrings files = RFile.searchFiles(_installSqlSystem, null, _pkh);
         deployPackage(files);
      }else if(type.equals(EDeploySource.SqlTypeHead)){
         // 建立类型包头
         FStrings files = RFile.searchFiles(_installSqlType, null, _tph);
         deployPackage(files);
      }else if(type.equals(EDeploySource.SqlPackageHead)){
         // 建立普通包头
         FStrings files = RFile.searchFiles(_logicUnit, "install_package.pkh", null);
         deployPackage(files);
      }else if(type.equals(EDeploySource.SqlSystemBody)){
         // 建立系统包体
         FStrings files = RFile.searchFiles(_installSqlSystem, null, _pky);
         deployPackage(files);
      }else if(type.equals(EDeploySource.SqlTypeBody)){
         // 建立类型包体
         FStrings files = RFile.searchFiles(_installSqlType, null, _tpy);
         deployPackage(files);
      }else if(type.equals(EDeploySource.SqlPackageBody)){
         // 建立普通包体
         FStrings files = RFile.searchFiles(_logicUnit, "install_package.pky", null);
         deployPackage(files);
      }else if(type.equals(EDeploySource.SqlHistoryHead)){
         // 建立历史包头
         FStrings files = RFile.searchFiles(_logicUnit, "install_history_package.pkh", null);
         deployPackage(files);
      }else if(type.equals(EDeploySource.SqlHistoryBody)){
         // 建立历史包体
         FStrings files = RFile.searchFiles(_logicUnit, "install_history_package.pky", null);
         deployPackage(files);
      }else if(type.equals(EDeploySource.SqlHistoryTable)){
         // 建立历史表
         FStrings files = RFile.searchFiles(_logicUnit, "install_history_table.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlHistoryView)){
         // 建立历史视图
         FStrings files = RFile.searchFiles(_logicUnit, "install_history_view.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlHistorySequence)){
         // 建立历史序列
         FStrings files = RFile.searchFiles(_logicUnit, "install_history_sequence.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlForeignKey)){
         // 建立外键
         FStrings files = RFile.searchFiles(_logicUnit, "install_foreign.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.InitializeData)){
         // 初始化数据
         FStrings files = RFile.searchFiles(_installDataTable, null, _csv);
         deployInstallData(files);
      }else if(type.equals(EDeploySource.SqlEnumHead)){
         // 建立列表包头
         FStrings files = RFile.searchFiles(_enum, "install_package_head.pkh", null);
         deployPackage(files);
      }else if(type.equals(EDeploySource.SqlEnumBody)){
         // 建立列表包体
         FStrings files = RFile.searchFiles(_enum, "install_package_body.pky", null);
         deployPackage(files);
      }
   }

   @Override
   public void installAll(EDeploySource type){
      install(type);
   }

   public FString loadFile(String fileName){
      FStringFile file = new FStringFile(fileName, _encode);
      int findEnd = file.lastIndexOf(';');
      int finded = file.lastIndexOf('/');
      if(-1 != finded && -1 != findEnd){
         if(finded > findEnd){
            file.setLength(finded - 1);
         }
      }
      return file;
   }

   public void setWorkpath(String workpath){
      _workpath = workpath;
   }

   @Override
   public void uninstall(EDeploySource type){
      if(type.equals(EDeploySource.SqlForeignKey)){
         // 卸载外键
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_foreign.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlHistorySequence)){
         // 卸载历史序列
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_history_sequence.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlHistoryView)){
         // 卸载历史视图
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_history_view.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlHistoryTable)){
         // 卸载历史表
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_history_table.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlHistoryHead)){
         // 卸载历史包头
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_history_package_head.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlHistoryBody)){
         // 卸载历史包体
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_history_package_body.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlPackageBody)){
         // 卸载普通包体
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_package_body.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlSystemBody)){
         // 卸载系统包体
         FStrings files = RFile.searchFiles(_uninstallSqlSystem, null, "body.sql");
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlPackageHead)){
         // 卸载普通包头
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_package_head.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlTypeHead)){
         // 卸载类型包头
         FStrings files = RFile.searchFiles(_uninstallSqlType, null, _sql);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlSystemHead)){
         // 卸载系统包头
         FStrings files = RFile.searchFiles(_uninstallSqlSystem, null, "head.sql");
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlSequence)){
         // 卸载序列
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_sequence.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlView)){
         // 卸载视图
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_view.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlViewUser)){
         // 卸载用户视图
         FStrings files = RFile.searchFiles(_uninstallSqlViewUser, null, _sql);
         deploySql(files);
      }else if(type.equals(EDeploySource.SqlTable)){
         // 卸载表
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_table.sql", null);
         deploySql(files);
      }else if(type.equals(EDeploySource.InitializeData)){
         // 卸载CSV表数据
         FStrings files = RFile.searchFiles(_logicUnit, "uninstall_clear_table.sql", null);
         deployUnInstallData(files);
      }else if(type.equals(EDeploySource.SqlEnumHead)){
         // 卸载列表包
         FStrings files = RFile.searchFiles(_enum, "uninstall_package_head.sql", null);
         deploySql(files);
      }
   }

   @Override
   public void uninstallAll(EDeploySource type){
      uninstall(type);
   }
}

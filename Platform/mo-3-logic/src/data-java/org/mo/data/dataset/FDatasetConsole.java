package org.mo.data.dataset;

import java.io.File;
import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.FSql;
import org.mo.com.data.ISqlConnection;
import org.mo.com.data.ISqlDatasetReader;
import org.mo.com.data.RSql;
import org.mo.com.io.FLinesFile;
import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.system.ESystemMode;
import org.mo.com.system.RSystem;
import org.mo.com.text.RDatabaseFormat;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.data.dataset.base.XBaseDataProperty;
import org.mo.data.dataset.base.XBaseDataStore;
import org.mo.data.dataset.common.FDatasetSourceBuilder;
import org.mo.data.dataset.common.RDatasetSqlBuilder;
import org.mo.data.dataset.common.XDataStore;
import org.mo.data.dataset.common.XDataWork;
import org.mo.data.procedure.common.FSqlPackageParser;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.FSqlSearchFields;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.eng.template.ITemplateConsole;
import org.mo.mime.csv.FCsvColumn;
import org.mo.mime.csv.FCsvColumns;
import org.mo.mime.csv.FCsvCommandProperty;
import org.mo.mime.csv.FCsvLine;
import org.mo.mime.csv.FCsvReader;
import org.mo.mime.csv.FCsvWriter;
import org.mo.mime.csv.ICsvLine;
import org.mo.mime.csv.RCsvCommand;

public class FDatasetConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         IDatasetConsole
{
   private static ILogger _logger = RLogger.find(FDatasetConsole.class);

   private static IResource _resource = RResource.find(FDatasetConsole.class);

   private final String _csv = ".csv";

   @ALink
   protected IDatabaseConsole _databaseConsole;

   private final String _encode = "GB18030";

   @AProperty
   protected String _installDataTable;

   @AProperty
   protected String _installEnum;

   @AProperty
   protected String _installSqlSystem;

   @AProperty
   protected String _installSqlType;

   @AProperty
   protected String _installSqlViewUser;

   @AProperty
   protected String _logicUnit;

   @AProperty
   protected String _sourceHistoryLogic;

   @AProperty
   protected String _sourceLogic;

   @AProperty
   protected String _sourceLogicData;

   @AProperty
   protected String _sourcepath;

   @AProperty
   protected String _storepath;

   @ALink
   private ITemplateConsole _templateConsole;

   @AProperty
   protected String _uninstallDataTable;

   @AProperty
   protected String _uninstallSqlSystem;

   @AProperty
   protected String _uninstallSqlType;

   @AProperty
   protected String _uninstallSqlViewUser;

   @AProperty
   protected String _viewUnit;

   @Override
   public void build(FDatasetBuilderArgs builderArgs){
      // 获取传过来的类型（FXmlNode,XDataset类型）
      EDatasetSourceType type = builderArgs.type();
      EDatasetBuildAction action = builderArgs.action();
      FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
      if(_logger.debugAble()){
         _logger.debug(this, "build", "Build java source. (type={1}, action={2}, name={3})", type, action, builderArgs.name());
      }
      // Build java interface package
      if(EDatasetSourceType.All == type || EDatasetSourceType.JavaPackageFace == type){
         String packageName = builderArgs.name();
         FSqlPackageParser parser = new FSqlPackageParser(builderArgs.sqlConnect(), packageName);
         FXmlNode config = parser.makeXmlNode();
         builderArgs.setSource(source.makeJavaPackageFace(config));
         // 保存为文件时的处理
         if(EDatasetBuildAction.Store == action){
            String name = RDatabaseFormat.toJavaClassName(packageName);
            name = "org/mobj/logic/data/I" + name + ".java";
            String filename = RFile.makeFilename(_sourceLogicData, name);
            _logger.info(this, "----------------------------------------------", "----");
            _logger.info(this, "build", "Build java source. (file={1})", filename);
            _logger.info(this, "----------------------------------------------", "----");
            RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
         }
         return;
      }
      // Build java package
      if(EDatasetSourceType.All == type || EDatasetSourceType.JavaPackage == type){
         String packageName = builderArgs.name();
         FSqlPackageParser parser = new FSqlPackageParser(builderArgs.sqlConnect(), packageName);
         if(packageName.endsWith("_DI")){
            packageName = packageName.substring(0, packageName.length() - 3);
         }
         String className = RDatabaseFormat.toJavaClassName(packageName);
         FXmlNode config = parser.makeXmlNode();
         config.set("class_name", className);
         builderArgs.setSource(source.makeJavaPackage(config));
         // 保存为文件时的处理
         if(EDatasetBuildAction.Store == action){
            className = "org/mobj/logic/data/impl/F" + className + "Impl.java";
            String filename = RFile.makeFilename(_sourceLogicData, className);
            _logger.info(this, "----------------------------------------------", "----");
            _logger.info(this, "build", "Build java source. (file={1})", filename);
            _logger.info(this, "----------------------------------------------", "----");
            RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
         }
         return;
      }
      // 获得设置信息
      IXmlObject xdataset = builderArgs.instance();
      String dataName = xdataset.innerGet(XDataStore.PTY_NAME);
      String tableName = xdataset.innerGet(XDataStore.PTY_DATA_NAME);
      // ESqlDatabaseType databaseType = ESqlDatabaseType.Oracle;
      // 建立XML节点信息
      FXmlNode dataset = convertInstanceToNode(xdataset, EXmlConfig.Simple);
      // 建立模板控制台
      String logicPackage = xdataset.innerGet(XDataStore.PTY_LOGIC_NAME);
      String path = RFile.makeFilename(_sourcepath, RString.replace(logicPackage, '.', '/'));
      String name = RString.replace(xdataset.innerGet(XDataStore.PTY_NAME), '.', '_');
      name = RDatabaseFormat.toJavaClassName(name);
      dataset.set("class_name", name);
      // Build entity
      if(EDatasetSourceType.All == type || EDatasetSourceType.Entity == type){
         builderArgs.setSource(source.makeEntity(dataset));
         if(EDatasetBuildAction.Store == action){
            String filename = RFile.format(path + "/base/F" + name + "Entity.java");
            RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
            _logger.info(this, "build", "Build entity source. (file={1})", filename);
         }
      }
      // Build base interface
      if(EDatasetSourceType.All == type || EDatasetSourceType.IBase == type){
         builderArgs.setSource(source.makeIBase(dataset));
         if(EDatasetBuildAction.Store == action){
            String filename = RFile.format(path + "/base/I" + name + "Base.java");
            RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
            _logger.info(this, "build", "Build interface base source. (file={1})", filename);
         }
      }
      // Build dao
      if(EDatasetSourceType.All == type || EDatasetSourceType.Dao == type){
         builderArgs.setSource(source.makeDao(dataset));
         if(EDatasetBuildAction.Store == action){
            String filename = RFile.format(path + "/base/F" + name + "Dao.java");
            RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
            _logger.info(this, "build", "Build dao source. (file={1})", filename);
         }
      }
      // Build row
      if(EDatasetSourceType.All == type || EDatasetSourceType.Row == type){
         builderArgs.setSource(source.makeRow(dataset));
         if(EDatasetBuildAction.Store == action){
            String filename = RFile.format(path + "/F" + name + ".java");
            if(!RFile.isFile(filename)){
               RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
               _logger.info(this, "build", "Build row source. (file={1})", filename);
            }
         }
      }
      // Build logic interface
      if(EDatasetSourceType.All == type || EDatasetSourceType.ILogic == type){
         builderArgs.setSource(source.makeIFace(dataset));
         if(EDatasetBuildAction.Store == action){
            String filename = RFile.format(path + "/I" + name + "Logic.java");
            if(!RFile.isFile(filename)){
               RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
               _logger.info(this, "build", "Build interface face source. (file={1})", filename);
            }
         }
      }
      // Build logic
      if(EDatasetSourceType.All == type || EDatasetSourceType.Logic == type){
         builderArgs.setSource(source.makeLogic(dataset));
         if(EDatasetBuildAction.Store == action){
            String filename = RFile.format(path + "/F" + name + "Logic.java");
            if(!RFile.isFile(filename)){
               RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
               _logger.info(this, "build", "Build logic source. (file={1})", filename);
            }
         }
      }
      // 建立数据集的删除数据表代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.SqlTableDelete == type){
         builderArgs.setSource(source.makeSqlTableDelete(dataset));
      }
      // 建立c++生命代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.Define == type){
         // 生成SQL代码
         FXmlNode config = buildConfig(dataName);
         builderArgs.setSource(source.makeDefineC(config));
      }
      // 建立c++实现代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.Implement == type){
         FXmlNode config = buildConfig(dataName);
         builderArgs.setSource(source.makeImplementC(config));
      }
      // 建立及卸载数据集的数据表代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.SqlTable == type){
         // 生成SQL代码
         FXmlNode config = buildConfig(dataName);
         if(ESystemMode.Test.equals(RSystem.modeCd())){
            config.set("config", "Test");
         }else if(ESystemMode.Logic.equals(RSystem.modeCd())){
            config.set("config", "Logic");
         }else if(ESystemMode.Develop.equals(RSystem.modeCd())){
            config.set("config", "Develop");
         }
         builderArgs.setSource(source.makeSqlTable(config));
         // 如果是存储模式的话，保存生成内容到文件
         if(EDatasetBuildAction.Store == action){
            String fileName = RFile.format(_logicUnit + dataName + "/install_table.sql").toLowerCase();
            RFile.saveToFile(fileName, builderArgs.source(), REncoding.UTF8);
            _logger.info(this, "build", "Build table sql. (file={1})", fileName);
            // 卸载数据集的数据表代码
            String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_table.sql").toLowerCase();
            RFile.saveToFile(uninstallfilename, source.makeSqlTableDelete(dataset), REncoding.UTF8);
            _logger.info(this, "build", "Delete table sql. (file={1})", uninstallfilename);
            // 保存卸载数据的文件
            FString dataSource = new FString();
            //dataSource.append("TRUNCATE TABLE ", tableName + "_DS");
            dataSource.append("DELETE FROM ", tableName + "_DS");
            String filename = RFile.format(_logicUnit + dataName + "/uninstall_clear_table.sql");
            RFile.saveToFile(filename, dataSource, REncoding.UTF8);
         }
      }
      // 建立及卸载数据工作生成数据表---YJHUA
      if(EDatasetSourceType.AllWork == type || EDatasetSourceType.SqlTableWork == type){
         // 生成SQL代码
         FXmlNode config = buildConfig(dataName);
         if("DataWork".equals(config.name())){
            if(ESystemMode.Test.equals(RSystem.modeCd())){
               config.set("config", "Test");
            }else if(ESystemMode.Logic.equals(RSystem.modeCd())){
               config.set("config", "Logic");
            }else if(ESystemMode.Develop.equals(RSystem.modeCd())){
               config.set("config", "Develop");
            }
            // 获得数据类型
            String dataType = config.get(XDataWork.PTY_DATA_TYPE);
            // 判断数据类型为工作数据
            if("Work".equalsIgnoreCase(dataType)){
               builderArgs.setSource(source.makeSqlTableWork(config));
            }else if("Memory".equalsIgnoreCase(dataType)){
               // 判断数据类型为内存数据
               builderArgs.setSource(source.makeSqlTableMemory(config));
            }else{
            }
            // 如果是存储模式的话，保存生成内容到文件
            if(EDatasetBuildAction.Store == action){
               String fileName = RFile.format(_logicUnit + dataName + "/install_table.sql").toLowerCase();
               RFile.saveToFile(fileName, builderArgs.source(), REncoding.UTF8);
               _logger.info(this, "build", "Build table sql. (file={1})", fileName);
               // 卸载数据集的数据表代码
               String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_table.sql").toLowerCase();
               RFile.saveToFile(uninstallfilename, source.makeSqlTableDelete(dataset), REncoding.UTF8);
               _logger.info(this, "build", "Delete table sql. (file={1})", uninstallfilename);
               // 保存卸载数据的文件
               FString dataSource = new FString();
               //dataSource.append("TRUNCATE TABLE ", tableName + "_DS");
               dataSource.append("DELETE FROM ", tableName + "_DS");
               String filename = RFile.format(_logicUnit + dataName + "/uninstall_clear_table.sql");
               RFile.saveToFile(filename, dataSource, REncoding.UTF8);
            }
         }
      }
      // 建立及卸载数据工作生成数据表---YJHUA
      if(EDatasetSourceType.AllCache == type || EDatasetSourceType.SqlTableCache == type){
         // 生成SQL代码
         FXmlNode config = buildConfig(dataName);
         if("DataCache".equals(config.name())){
            if(ESystemMode.Test.equals(RSystem.modeCd())){
               config.set("config", "Test");
            }else if(ESystemMode.Logic.equals(RSystem.modeCd())){
               config.set("config", "Logic");
            }else if(ESystemMode.Develop.equals(RSystem.modeCd())){
               config.set("config", "Develop");
            }
            builderArgs.setSource(source.makeSqlTableCache(config));
            // 如果是存储模式的话，保存生成内容到文件
            if(EDatasetBuildAction.Store == action){
               String fileName = RFile.format(_logicUnit + dataName + "/install_table_work.sql").toLowerCase();
               RFile.saveToFile(fileName, builderArgs.source(), REncoding.UTF8);
               _logger.info(this, "build", "Build table sql. (file={1})", fileName);
               // 卸载数据集的数据表代码
               String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_table_work.sql").toLowerCase();
               RFile.saveToFile(uninstallfilename, source.makeSqlTableDelete(dataset), REncoding.UTF8);
               _logger.info(this, "build", "Delete table sql. (file={1})", uninstallfilename);
               // 保存卸载数据的文件
               FString dataSource = new FString();
               //dataSource.append("TRUNCATE TABLE ", tableName + "_DS");
               dataSource.append("DELETE FROM ", tableName + "_DS");
               String filename = RFile.format(_logicUnit + dataName + "/uninstall_clear_table.sql");
               RFile.saveToFile(filename, dataSource, REncoding.UTF8);
            }
         }
      }
      // 建立及卸载数据集的视图代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.SqlView == type){
         FXmlNode config = buildConfig(dataName);
         builderArgs.setSource(source.makeSqlView(config));
         //String packageName = RString.replace(dataName, '.', '_');
         String fileName = RFile.format(_logicUnit + dataName + "/install_view.sql").toLowerCase();
         RFile.saveToFile(fileName, builderArgs.source(), REncoding.UTF8);
         _logger.info(this, "build", "Build plsql view. (file={1})", fileName);
         // 卸载数据集的视图代码
         String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_view.sql").toLowerCase();
         RFile.saveToFile(uninstallfilename, source.makeSqlViewDelete(dataset), REncoding.UTF8);
         _logger.info(this, "build", "Delete plsql view. (file={1})", uninstallfilename);
      }
      // 建立及卸载工作数据的视图代码---YJHUA
      if(EDatasetSourceType.AllWork == type || EDatasetSourceType.SqlViewWork == type){
         FXmlNode config = buildConfig(dataName);
         builderArgs.setSource(source.makeSqlView(config));
         //String packageName = RString.replace(dataName, '.', '_');
         String fileName = RFile.format(_logicUnit + dataName + "/install_view.sql").toLowerCase();
         RFile.saveToFile(fileName, builderArgs.source(), REncoding.UTF8);
         _logger.info(this, "build", "Build plsql view. (file={1})", fileName);
         // 卸载数据集的视图代码
         String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_view.sql").toLowerCase();
         RFile.saveToFile(uninstallfilename, source.makeSqlViewDelete(dataset), REncoding.UTF8);
         _logger.info(this, "build", "Delete plsql view. (file={1})", uninstallfilename);
      }
      // 建立及卸载数据视图的视图代码---YJHUA
      if(EDatasetSourceType.All == type || EDatasetSourceType.SqlViewView == type){
         String viewSql = xdataset.innerText();
         if(RString.isNotEmpty(viewSql)){
            FStringFile file = new FStringFile();
            file.append(viewSql);
            builderArgs.setSource(file);
         }else{
            FXmlNode config = buildConfig(dataName);
            builderArgs.setSource(source.makeSqlViewView(config));
         }
         //String packageName = RString.replace(dataName, '.', '_');
         String fileName = RFile.format(_viewUnit + dataName + "/install_view.sql").toLowerCase();
         RFile.saveToFile(fileName, builderArgs.source(), REncoding.UTF8);
         _logger.info(this, "build", "Build plsql view. (file={1})", fileName);
         // 卸载数据集的视图代码
         String uninstallfilename = RFile.format(_viewUnit + dataName + "/uninstall_view.sql").toLowerCase();
         RFile.saveToFile(uninstallfilename, source.makeSqlViewDelete(dataset), REncoding.UTF8);
         _logger.info(this, "build", "Delete plsql view. (file={1})", uninstallfilename);
      }
      // 建立及卸载数据集的属性视图代码---YJHUA
      if(EDatasetSourceType.AllProperty == type || EDatasetSourceType.SqlViewProperty == type){
         FXmlNode config = buildConfig(dataName);
         // 判断是否是属性视图类型的xml节点，如果是则生成视图
         if("DataProperty".equals(config.name())){
            builderArgs.setSource(source.makeSqlViewProperty(config));
            //String packageName = RString.replace(dataName, '.', '_');
            String fileName = RFile.format(_logicUnit + dataName + "/install_view.sql").toLowerCase();
            RFile.saveToFile(fileName, builderArgs.source(), REncoding.UTF8);
            _logger.info(this, "build", "Build plsql view. (file={1})", fileName);
            // 卸载数据集的视图代码
            String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_view.sql").toLowerCase();
            RFile.saveToFile(uninstallfilename, source.makeSqlViewDelete(dataset), REncoding.UTF8);
            _logger.info(this, "build", "Delete plsql view. (file={1})", uninstallfilename);
         }
      }
      // 建立数据集的删除序列代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.SqlSequenceDelete == type){
         builderArgs.setSource(source.makeSqlSequenceDelete(dataset));
      }
      // 建立,删除数据集的序列代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.SqlSequence == type){
         builderArgs.setSource(source.makeSqlSequence(dataset));
         //String packageName = RString.replace(dataName, '.', '_');
         String installfilename = RFile.format(_logicUnit + dataName + "/install_sequence.sql").toLowerCase();
         RFile.saveToFile(installfilename, builderArgs.source(), REncoding.UTF8);
         _logger.info(this, "build", "Build plsql sequence. (file={1})", installfilename);
         // 卸载序列的代码
         String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_sequence.sql").toLowerCase();
         RFile.saveToFile(uninstallfilename, source.makeSqlSequenceDelete(dataset), REncoding.UTF8);
         _logger.info(this, "build", "Delete plsql sequence. (file={1})", uninstallfilename);
      }
      // 建立数据集的删除序列代码---YJHUA
      if(EDatasetSourceType.AllWork == type || EDatasetSourceType.SqlSequenceDeleteWork == type){
         builderArgs.setSource(source.makeSqlSequenceDelete(dataset));
      }
      // 建立,删除数据集的序列代码---YJHUA
      if(EDatasetSourceType.AllWork == type || EDatasetSourceType.SqlSequenceWork == type){
         builderArgs.setSource(source.makeSqlSequence(dataset));
         //String packageName = RString.replace(dataName, '.', '_');
         String installfilename = RFile.format(_logicUnit + dataName + "/install_sequence.sql").toLowerCase();
         RFile.saveToFile(installfilename, builderArgs.source(), REncoding.UTF8);
         _logger.info(this, "build", "Build plsql sequence. (file={1})", installfilename);
         // 卸载序列的代码
         String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_sequence.sql").toLowerCase();
         RFile.saveToFile(uninstallfilename, source.makeSqlSequenceDelete(dataset), REncoding.UTF8);
         _logger.info(this, "build", "Delete plsql sequence. (file={1})", uninstallfilename);
      }
      // 建立及卸载数据集的包头代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.PackageHead == type){
         FXmlNode config = buildConfig(dataName);
         FString define = (FString)builderArgs.attributes().get(FSqlPackageParser.USER_DEFINE);
         config.set("old_define", define.toString());
         if(EDatasetBuildAction.Query == action){
            builderArgs.setSource(source.makePackageHead(config));
         }else if(EDatasetBuildAction.Execute == action){
            builderArgs.setSource(source.makePackageHeadSql(config));
         }else if(EDatasetBuildAction.Store == action){
            //String packageName = RString.replace(dataName, '.', '_');
            FString argsSource = builderArgs.source();
            // 建立数据集的普通包头
            String installFileName = RFile.format(_logicUnit + dataName + "/install_package.pkh");
            RFile.saveToFile(installFileName, argsSource, REncoding.UTF8);
            _logger.info(this, "build", "Build plsql package head. (file={1})", installFileName);
            // 卸载数据集的包头代码
            String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_package_head.sql");
            RFile.saveToFile(uninstallfilename, source.makePackageHeadDelete(dataset), REncoding.UTF8);
            _logger.info(this, "build", "Delete plsql package head. (file={1})", uninstallfilename);
            builderArgs.setSource(source.makePackageHead(config));
            // 建立数据集的普通包头
            //String filename = RFile.format(_sourceLogic + packageName + ".pkh");
            //RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
         }
      }
      // 建立及卸载数据集的包体
      if(EDatasetSourceType.All == type || EDatasetSourceType.PackageBody == type){
         FXmlNode config = buildConfig(dataName);
         FString define = (FString)builderArgs.attributes().get(FSqlPackageParser.USER_DEFINE);
         config.set("old_define", define.toString());
         if(EDatasetBuildAction.Query == action){
            builderArgs.setSource(source.makePackageBody(config));
         }else if(EDatasetBuildAction.Execute == action){
            builderArgs.setSource(source.makePackageBodySql(config));
         }else if(EDatasetBuildAction.Store == action){
            //String packageName = RString.replace(dataName, '.', '_').toLowerCase();
            FString argsSource = builderArgs.source();
            // 建立数据集的普通包体
            String installFileName = RFile.format(_logicUnit + dataName + "/install_package.pky");
            RFile.saveToFile(installFileName, argsSource, REncoding.UTF8);
            _logger.info(this, "build", "Build plsql package body. (file={1})", installFileName);
            // 卸载数据集的普通包体
            String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_package_body.sql");
            RFile.saveToFile(uninstallfilename, source.makePackageBodyDelete(dataset), REncoding.UTF8);
            _logger.info(this, "build", "Delete plsql system package sql. (file={1})", uninstallfilename);
            // 建立数据集的普通包体
            //builderArgs.setSource(source.makePackageBody(config));
            //String filename = RFile.format(_sourceLogic + packageName + ".pky");
            //RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
            //_logger.info(this, "build", "Build plsql package body. (file={1})", filename);
         }
      }
      // 建立及卸载数据工作的包头代码---YJHUA
      if(EDatasetSourceType.AllWork == type || EDatasetSourceType.PackageHeadWork == type){
         FXmlNode config = buildConfig(dataName);
         FString define = (FString)builderArgs.attributes().get(FSqlPackageParser.USER_DEFINE);
         config.set("old_define", define.toString());
         if(EDatasetBuildAction.Query == action){
            builderArgs.setSource(source.makePackageHead(config));
         }else if(EDatasetBuildAction.Execute == action){
            builderArgs.setSource(source.makePackageHeadSql(config));
         }else if(EDatasetBuildAction.Store == action){
            //String packageName = RString.replace(dataName, '.', '_');
            FString argsSource = builderArgs.source();
            // 建立数据集的普通包头
            String installFileName = RFile.format(_logicUnit + dataName + "/install_package.pkh");
            RFile.saveToFile(installFileName, argsSource, REncoding.UTF8);
            _logger.info(this, "build", "Build plsql package head. (file={1})", installFileName);
            // 卸载数据集的包头代码
            String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_package_head.sql");
            RFile.saveToFile(uninstallfilename, source.makePackageHeadDelete(dataset), REncoding.UTF8);
            _logger.info(this, "build", "Delete plsql package head. (file={1})", uninstallfilename);
            builderArgs.setSource(source.makePackageHead(config));
            // 建立数据集的普通包头
            //String filename = RFile.format(_sourceLogic + packageName + ".pkh");
            //RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
         }
      }
      // 建立及卸载数据工作的包体---YJHUA
      if(EDatasetSourceType.AllWork == type || EDatasetSourceType.PackageBodyWork == type){
         FXmlNode config = buildConfig(dataName);
         FString define = (FString)builderArgs.attributes().get(FSqlPackageParser.USER_DEFINE);
         config.set("old_define", define.toString());
         if(EDatasetBuildAction.Query == action){
            builderArgs.setSource(source.makePackageBody(config));
         }else if(EDatasetBuildAction.Execute == action){
            builderArgs.setSource(source.makePackageBodySql(config));
         }else if(EDatasetBuildAction.Store == action){
            //String packageName = RString.replace(dataName, '.', '_').toLowerCase();
            FString argsSource = builderArgs.source();
            // 建立数据集的普通包体
            String installFileName = RFile.format(_logicUnit + dataName + "/install_package.pky");
            RFile.saveToFile(installFileName, argsSource, REncoding.UTF8);
            _logger.info(this, "build", "Build plsql package body. (file={1})", installFileName);
            // 卸载数据集的普通包体
            String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_package_body.sql");
            RFile.saveToFile(uninstallfilename, source.makePackageBodyDelete(dataset), REncoding.UTF8);
            _logger.info(this, "build", "Delete plsql system package sql. (file={1})", uninstallfilename);
            // 建立数据集的普通包体
            //builderArgs.setSource(source.makePackageBody(config));
            //String filename = RFile.format(_sourceLogic + packageName + ".pky");
            //RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
            //_logger.info(this, "build", "Build plsql package body. (file={1})", filename);
         }
      }
      // 建立及卸载属性视图的包头代码---YJHUA
      if(EDatasetSourceType.AllProperty == type || EDatasetSourceType.PackagePropertyHead == type){
         FXmlNode config = buildConfig(dataName);
         // 判断是否是属性视图类型的xml节点，如果是则生成视图
         if("DataProperty".equals(config.name())){
            FString define = (FString)builderArgs.attributes().get(FSqlPackageParser.USER_DEFINE);
            config.set("old_define", define.toString());
            if(EDatasetBuildAction.Query == action){
               builderArgs.setSource(source.makePackagePropertyHead(config));
            }else if(EDatasetBuildAction.Execute == action){
               builderArgs.setSource(source.makePackagePropertyHeadSql(config));
            }else if(EDatasetBuildAction.Store == action){
               //String packageName = RString.replace(dataName, '.', '_');
               FString argsSource = builderArgs.source();
               // 建立数据集的普通包头
               String installFileName = RFile.format(_logicUnit + dataName + "/install_package.pkh");
               RFile.saveToFile(installFileName, argsSource, REncoding.UTF8);
               _logger.info(this, "build", "Build plsql package head. (file={1})", installFileName);
               // 卸载数据集的包头代码
               String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_package.sql");
               RFile.saveToFile(uninstallfilename, source.makePackagePropertyHeadDelete(dataset), REncoding.UTF8);
               _logger.info(this, "build", "Delete plsql package head. (file={1})", uninstallfilename);
               builderArgs.setSource(source.makePackagePropertyHead(config));
               // 建立数据集的普通包头
               //String filename = RFile.format(_sourceLogic + packageName + ".pkh");
               //RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
            }
         }
      }
      // 建立及卸载属性视图的包体---YJHUA
      if(EDatasetSourceType.AllProperty == type || EDatasetSourceType.PackagePropertyBody == type){
         FXmlNode config = buildConfig(dataName);
         // 判断是否是属性视图类型的xml节点，如果是则生成视图
         if("DataProperty".equals(config.name())){
            FString define = (FString)builderArgs.attributes().get(FSqlPackageParser.USER_DEFINE);
            config.set("old_define", define.toString());
            if(EDatasetBuildAction.Query == action){
               builderArgs.setSource(source.makePackagePropertyBody(config));
            }else if(EDatasetBuildAction.Execute == action){
               builderArgs.setSource(source.makePackagePropertyBodySql(config));
            }else if(EDatasetBuildAction.Store == action){
               //String packageName = RString.replace(dataName, '.', '_').toLowerCase();
               FString argsSource = builderArgs.source();
               // 建立数据集的普通包体
               String installFileName = RFile.format(_logicUnit + dataName + "/install_package.pky");
               RFile.saveToFile(installFileName, argsSource, REncoding.UTF8);
               _logger.info(this, "build", "Build plsql package body. (file={1})", installFileName);
               // 卸载数据集的普通包体
               String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_package.sql");
               RFile.saveToFile(uninstallfilename, source.makePackagePropertyBodyDelete(dataset), REncoding.UTF8);
               _logger.info(this, "build", "Delete plsql system package sql. (file={1})", uninstallfilename);
               // 建立数据集的普通包体
               //builderArgs.setSource(source.makePackagePropertyBody(config));
               //String filename = RFile.format(_sourceLogic + packageName + ".pky");
               //RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
               //_logger.info(this, "build", "Build plsql package body. (file={1})", filename);
            }
         }
      }
      // 建立历史数据集的删除数据表代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.HisSqlTableDelete == type){
         builderArgs.setSource(source.makeHsSqlTableDelete(dataset));
      }
      // 建立及卸载历史数据集的数据表
      if(EDatasetSourceType.All == type || EDatasetSourceType.HisSqlTable == type){
         // 生成SQL代码
         FXmlNode config = buildConfig(dataName);
         if(ESystemMode.Test.equals(RSystem.modeCd())){
            config.set("config", "Test");
         }else if(ESystemMode.Logic.equals(RSystem.modeCd())){
            config.set("config", "Logic");
         }else if(ESystemMode.Develop.equals(RSystem.modeCd())){
            config.set("config", "Develop");
         }
         builderArgs.setSource(source.makeHsSqlTable(config));
         // 如果是存储模式的话，保存生成内容到文件
         if(EDatasetBuildAction.Store == action){
            //String storeName = RString.replace(dataName, '.', '_').toLowerCase();
            String fileName = RFile.format(_logicUnit + dataName + "/install_history_table.sql");
            RFile.saveToFile(fileName, builderArgs.source(), REncoding.UTF8);
            _logger.info(this, "build", "Build table sql. (file={1})", fileName);
            // 卸载历史数据集的数据表
            String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_history_table.sql");
            RFile.saveToFile(uninstallfilename, source.makeHsSqlTableDelete(dataset), REncoding.UTF8);
            _logger.info(this, "build", "Delete table sql. (file={1})", uninstallfilename);
         }
      }
      // 建立及卸载历史数据集的视图
      if(EDatasetSourceType.All == type || EDatasetSourceType.HisSqlView == type){
         FXmlNode config = buildConfig(dataName);
         builderArgs.setSource(source.makeHsSqlView(config));
         //String packageName = RString.replace(dataName, '.', '_').toLowerCase();
         String filename = RFile.format(_logicUnit + dataName + "/install_history_view.sql");
         RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
         _logger.info(this, "build", "Build plsql history view. (file={1})", filename);
         // 卸载历史数据集的视图
         String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_history_view.sql");
         RFile.saveToFile(uninstallfilename, source.makeHsSqlViewDelete(dataset), REncoding.UTF8);
         _logger.info(this, "build", "Delete plsql history view. (file={1})", uninstallfilename);
      }
      // 建立历史数据集的删除序列代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.HisSqlSequenceDelete == type){
         builderArgs.setSource(source.makeHsSqlSequenceDelete(dataset));
      }
      // 建立及卸载历史数据集的序列
      if(EDatasetSourceType.All == type || EDatasetSourceType.HisSqlSequence == type){
         builderArgs.setSource(source.makeHsSqlSequence(dataset));
         //String packageName = RString.replace(dataName, '.', '_').toLowerCase();
         String filename = RFile.format(_logicUnit + dataName + "/install_history_sequence.sql");
         RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
         _logger.info(this, "build", "Build plsql history sequence. (file={1})", filename);
         // 卸载历史数据集的序列
         String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_history_sequence.sql");
         RFile.saveToFile(uninstallfilename, source.makeHsSqlSequenceDelete(dataset), REncoding.UTF8);
         _logger.info(this, "build", "Delete plsql history sequence. (file={1})", uninstallfilename);
      }
      // 建立及卸载历史数据集的包头
      if(EDatasetSourceType.All == type || EDatasetSourceType.HisPackageHead == type){
         FXmlNode config = buildConfig(dataName);
         if(EDatasetBuildAction.Query == action){
            builderArgs.setSource(source.makeHsPackageHead(config));
         }else if(EDatasetBuildAction.Execute == action){
            builderArgs.setSource(source.makeHsPackageHeadSql(config));
         }else if(EDatasetBuildAction.Store == action){
            // 建立数据集的历史包头
            //String packageName = RString.replace(dataName, '.', '_').toLowerCase();
            String installFileName = RFile.format(_logicUnit + dataName + "/install_history_package.pkh");
            RFile.saveToFile(installFileName, builderArgs.source(), REncoding.UTF8);
            _logger.info(this, "build", "Build plsql history package head. (file={1})", installFileName);
            // 卸载历史数据集的包头
            String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_history_package_head.sql");
            RFile.saveToFile(uninstallfilename, source.makeHsPackageHeadDelete(dataset), REncoding.UTF8);
            _logger.info(this, "build", "Delete plsql history package head. (file={1})", uninstallfilename);
            // 引用模板生成的历史包头
            //builderArgs.setSource(source.makeHsPackageHead(config));
            //String filename = RFile.format(_sourceHistoryLogic + packageName + "_hs.pkh");
            //RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
         }
      }
      // 建立及卸载历史数据集的包体
      if(EDatasetSourceType.All == type || EDatasetSourceType.HisPackageBody == type){
         FXmlNode config = buildConfig(dataName);
         if(EDatasetBuildAction.Query == action){
            builderArgs.setSource(source.makeHsPackageBody(config));
         }else if(EDatasetBuildAction.Execute == action){
            builderArgs.setSource(source.makeHsPackageBodySql(config));
         }else if(EDatasetBuildAction.Store == action){
            // 建立数据集的历史包体
            //String packageName = RString.replace(dataName, '.', '_').toLowerCase();
            String installFileName = RFile.format(_logicUnit + dataName + "/install_history_package.pky");
            RFile.saveToFile(installFileName, builderArgs.source(), REncoding.UTF8);
            _logger.info(this, "build", "Build plsql history package body. (file={1})", installFileName);
            // 卸载数据集的历史包体
            String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_history_package_body.sql");
            RFile.saveToFile(uninstallfilename, source.makeHsPackageBodyDelete(dataset), REncoding.UTF8);
            _logger.info(this, "build", "Delete plsql history package body. (file={1})", uninstallfilename);
            // 引用模板生成的历史包体
            //builderArgs.setSource(source.makeHsPackageBody(config));
            //String filename = RFile.format(_sourceHistoryLogic + packageName + "_hs.pky");
            //RFile.saveToFile(filename, builderArgs.source(), REncoding.UTF8);
         }else{
         }
      }
   }

   @Override
   public void build(String packageName,
                     FString lines,
                     EDatasetSourceType type){
      if(null != packageName){
         if(EDatasetSourceType.TypeHead == type){
            String filename = RFile.format(_installSqlType + packageName.toLowerCase() + ".tph");
            RFile.saveToFile(filename, lines, REncoding.UTF8);
         }else if(EDatasetSourceType.TypeBody == type){
            String filename = RFile.format(_installSqlType + packageName.toLowerCase() + ".tpy");
            RFile.saveToFile(filename, lines, REncoding.UTF8);
         }else if(EDatasetSourceType.SystemHead == type){
            String filename = RFile.format(_installSqlSystem + packageName.toLowerCase() + ".pkh");
            RFile.saveToFile(filename, lines, REncoding.UTF8);
            // 卸载
            FString sqlLine = new FString();
            sqlLine.append("DROP PACKAGE ", packageName);
            String uninStallFilename = RFile.format(_uninstallSqlSystem + packageName.toLowerCase() + "_head.sql");
            RFile.saveToFile(uninStallFilename, sqlLine, REncoding.UTF8);
         }else if(EDatasetSourceType.SystemBody == type){
            String filename = RFile.format(_installSqlSystem + packageName.toLowerCase() + ".pky");
            RFile.saveToFile(filename, lines, REncoding.UTF8);
            // 卸载
            FString sqlLine = new FString();
            sqlLine.append("DROP PACKAGE BODY ", packageName);
            String uninStallFilename = RFile.format(_uninstallSqlSystem + packageName.toLowerCase() + "_body.sql");
            RFile.saveToFile(uninStallFilename, sqlLine, REncoding.UTF8);
         }
      }
   }

   @Override
   public FXmlNode buildConfig(IXmlObject xdataset){
      return buildConfig(xdataset, EXmlConfig.Simple);
   }

   @Override
   public FXmlNode buildConfig(IXmlObject xdataset,
                               EXmlConfig type){
      try{
         FXmlNode config = new FXmlNode();
         xdataset.saveConfig(config, EXmlConfig.Simple);
         // 解析字段列表
         FXmlNode fieldsNode = config.createNode("Fields");
         // 如果有父名称，则将父节点复制过来
         buildConfigFields(xdataset, fieldsNode, type);
         // 获得父表数据的信息
         String parentName = xdataset.innerGet(XBaseDataProperty.PTY_PARENT_NAME);
         if(RString.isNotEmpty(parentName)){
            IXmlObject xParentName = get(parentName);
            String parentDataName = xParentName.innerGet(XBaseDataStore.PTY_DATA_NAME);
            config.set("parent_data_name", parentDataName);
            String parentDataLogic = xParentName.innerGet(XBaseDataStore.PTY_DATA_LOGIC);
            config.set("parent_data_logic", parentDataLogic);
         }
         // 获得父属性表数据的信息
         String parentValue = xdataset.innerGet(XBaseDataProperty.PTY_PARENT_VALUE);
         if(RString.isNotEmpty(parentValue)){
            IXmlObject xParentValue = get(parentValue);
            String parentDataValue = xParentValue.innerGet(XBaseDataStore.PTY_DATA_NAME);
            config.set("value_data_name", parentDataValue);
            String parentDataLogic = xParentValue.innerGet(XBaseDataStore.PTY_DATA_LOGIC);
            config.set("value_data_logic", parentDataLogic);
         }
         for(FXmlNode fieldNode : fieldsNode){
            // 查找数据定义中的引用其它数据集的名称
            String dataRefer = fieldNode.get("data_refer", null);
            if(RString.isNotEmpty(dataRefer)){
               IXmlObject xdatasetRefer = get(dataRefer);
               String referName = xdatasetRefer.innerGet(XDataStore.PTY_DATA_NAME);
               fieldNode.set("refer_name", referName);
            }
            // 设置数据集内容
            String fieldType = fieldNode.get("type");
            if("Boolean".equals(fieldType)){
               fieldType = "CHAR(1)";
            }else if("Integer".equals(fieldType)){
               fieldType = "INTEGER";
            }else if("Float".equals(fieldType)){
               fieldType = "FLOAT";
            }else if("Char".equals(fieldType)){
               fieldType = "CHAR(" + fieldNode.get("data_size") + ")";
            }else if("String".equals(fieldType)){
               fieldType = "VARCHAR2(" + fieldNode.get("data_size") + ")";
            }else if("Content".equals(fieldType)){
               fieldType = "VARCHAR2(2000)";
            }else if("Date".equals(fieldType)){
               fieldType = "DATE";
            }else if("TimeSpan".equals(fieldType)){
               fieldType = "INTEGER";
            }else if("Enum".equals(fieldType)){
               fieldType = "CHAR(" + fieldNode.get("data_size") + ")";
            }else if("Set".equals(fieldType)){
               fieldType = "VARCHAR2(" + fieldNode.get("data_size") + ")";
            }
            fieldNode.set("data_type", fieldType);
            if(RString.endsWith(fieldType, "Property")){
               fieldNode.set("is_property", RBoolean.TRUE_STR);
               fieldType = fieldType.substring(0, fieldType.length() - 8);
               fieldNode.set("type", fieldType);
               if(RString.hasChars(fieldType, "Integer")){
                  fieldNode.set("data_type", "Integer");
               }else if(RString.hasChars(fieldType, "Float")){
                  fieldNode.set("data_type", "Float");
               }else if(RString.hasChars(fieldType, "Boolean")){
                  fieldNode.set("data_type", "Boolean");
               }else if(RString.hasChars(fieldType, "Date")){
                  fieldNode.set("data_type", "Date");
               }else if(RString.hasChars(fieldType, "TimeSpan")){
                  fieldNode.set("data_type", "Integer");
               }else{
                  fieldNode.set("data_type", "String");
               }
            }else{
               fieldNode.set("is_property", RBoolean.FALSE_STR);
            }
         }
         // 解析键列表
         FXmlNode keysNode = config.createNode("Keys");
         // 添加主键
         FXmlNode keyNode = keysNode.createNode("Key");
         keyNode.set("key_type", "Primary");
         // 添加唯一键
         FAttributes uniqueNames = new FAttributes();
         for(FXmlNode fieldNode : fieldsNode){
            String isUnique = fieldNode.get("is_unique", null);
            if(RBoolean.parse(isUnique)){
               keyNode = keysNode.createNode("Key");
               keyNode.set("key_type", "Unique");
               keyNode.set("unique_names", fieldNode.get("data_name"));
               keyNode.attributes().append(fieldNode.attributes());
               // 增加唯一键关联
               FXmlNode keyFieldNode = keyNode.createNode();
               keyFieldNode.attributes().append(fieldNode.attributes());
            }
            // 建立关联唯一键
            String dataUniques = fieldNode.get("data_uniques", null);
            if(RString.isNotEmpty(dataUniques)){
               String dataName = RString.toUpper(fieldNode.get("data_name"));
               for(String unique : RString.split(dataUniques, ',')){
                  if(RString.isNotBlank(unique)){
                     unique = unique.trim().toUpperCase();
                     String value = uniqueNames.get(unique);
                     if(RString.isNotEmpty(value)){
                        uniqueNames.set(unique, value + ", " + dataName);
                     }else{
                        uniqueNames.set(unique, dataName);
                     }
                  }
               }
            }
         }
         // 添加关联唯一键
         if(!uniqueNames.isEmpty()){
            for(IStringPair pair : uniqueNames){
               keyNode = keysNode.createNode("Key");
               keyNode.set("key_type", "Unique");
               keyNode.set("data_name", pair.name());
               keyNode.set("unique_names", pair.value());
               // 增加唯一键关联
               for(String unique : RString.split(pair.value(), ',')){
                  FXmlNode linkNode = fieldsNode.findNode("data_name", unique.trim());
                  FXmlNode keyFieldNode = keyNode.createNode();
                  keyFieldNode.attributes().append(linkNode.attributes());
               }
            }
         }
         // 添加外键
         for(FXmlNode fieldNode : fieldsNode){
            String dataRefer = fieldNode.get("data_refer", null);
            if(RString.isNotEmpty(dataRefer)){
               keyNode = keysNode.createNode("Key");
               keyNode.set("key_type", "Foreign");
               keyNode.attributes().append(fieldNode.attributes());
               // 检查外键名称是否以(_ID)结尾
               String dataName = RString.nvl(fieldNode.get("data_alias", null), fieldNode.get("data_name"));
               if(dataName.toLowerCase().endsWith("_id")){
                  dataName = dataName.substring(0, dataName.length() - "_id".length());
               }
               keyNode.set("key_name", dataName);
            }
         }
         // 添加索引列表
         FXmlNode indexsNode = config.createNode("indexs");
         String dataName = RString.nvl(config.get("data_alias", null), config.get("data_name"));
         for(FXmlNode fieldNode : fieldsNode){
            // 建立索引
            String indexNames = fieldNode.get("index_names", null);
            if(RString.isNotEmpty(indexNames)){
               for(String indexName : RString.split(indexNames, ',')){
                  boolean hasName = false;
                  for(FXmlNode indexNameNode : indexsNode.nodes()){
                     if(indexNameNode.get("index_name").equals(RString.toUpper(indexName))){
                        hasName = true;
                        break;
                     }
                  }
                  if(!hasName){
                     FXmlNode indexNameNode = indexsNode.createNode("index_name");
                     indexNameNode.set("index_name", RString.toUpper(indexName));
                     indexNameNode.set("data_name", RString.toUpper(dataName));
                  }
               }
               for(FXmlNode indexNameNode : indexsNode.nodes()){
                  for(String indexName : RString.split(indexNames, ',')){
                     if(indexNameNode.get("index_name").equals(RString.toUpper(indexName))){
                        FXmlNode indexNode = indexNameNode.createNode("index");
                        indexNode.set("index_params", fieldNode.get("data_name"));
                        indexNode.attributes().append(fieldNode.attributes());
                     }
                  }
               }
            }
         }
         return config;
      }catch(Exception e){
         throw new FFatalError(e, "Build config failure. (dataset={1}, type={2})", xdataset.innerGet("name"), type);
      }
   }

   @Override
   public FXmlNode buildConfig(String name){
      IXmlObject xdataset = get(name);
      return buildConfig(xdataset, EXmlConfig.Simple);
   }

   @Override
   public FXmlNode buildConfig(String name,
                               EXmlConfig type){
      IXmlObject xdataset = get(name);
      return buildConfig(xdataset, type);
   }

   public void buildConfigFields(IXmlObject xdataset,
                                 FXmlNode fieldsNode,
                                 EXmlConfig type){
      // 如果有父名称，则将父节点复制过来
      String parentName = xdataset.innerGet("parent_name");
      if(RString.isNotEmpty(parentName)){
         buildConfigFields(get(parentName), fieldsNode, type);
      }
      // 将父数据集的内容全部复制过来
      for(IXmlObject xfield : xdataset.children()){
         // 创建节点
         String fieldType = xfield.name();
         // 设置类型信息
         if(fieldType.startsWith("Field")){
            fieldType = fieldType.substring("Field".length());
         }
         // 处理模板的情况
         if("Template".equals(fieldType)){
            String templateName = xfield.innerGet("template_name");
            buildConfigFields(get(templateName), fieldsNode, type);
            continue;
         }
         // 创建字段节点
         FXmlNode fieldNode = fieldsNode.createNode();
         xfield.saveConfig(fieldNode, type);
         fieldNode.set("type", fieldType);
         fieldNode.setName("Field");
      }
   }

   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }

   @Override
   public void dataDelete(IXmlObject xdataset){
      dataDelete(xdataset, true);
   }

   @Override
   public void dataDelete(IXmlObject xdataset,
                          boolean disableKey){
      ISqlConnection sqlCnn = null;
      try{
         sqlCnn = _databaseConsole.alloc();
         String dataName = xdataset.innerGet("data_name");
         if(disableKey){
            tableForeignKeyDisable(sqlCnn, dataName);
         }
         // 执行删除表中所有记录的SQL语句
         FSql sql = new FSql("DELETE FROM " + dataName + "_DS");
         sqlCnn.executeSql(sql.toString());
      }finally{
         if(null != sqlCnn){
            _databaseConsole.free(sqlCnn);
         }
      }
   }

   @Override
   public void dataRestore(IXmlObject xdataset){
      dataRestore(xdataset, true);
   }

   @Override
   public void dataRestore(IXmlObject xdataset,
                           boolean disableKey){
      // Execute
      ISqlConnection sqlCnn = null;
      FCsvReader reader = null;
      try{
         if(disableKey){
            dataDelete(xdataset, true);
         }
         FXmlNode config = buildConfig(xdataset);
         String data_name = config.get("data_name");
         String datasetName = xdataset.innerGet("name");
         String fileName = _installDataTable + datasetName + _csv;
         FLinesFile file = new FLinesFile(fileName, _encode);
         if(file.length() > 0){
            sqlCnn = _databaseConsole.alloc();
            reader = new FCsvReader();
            reader.openFile(fileName, _encode);
            reader.openSegment();
            //
            FXmlNode node = config.findNode("Fields");
            FCsvColumns columns = reader.columns();
            FString fieldsStr = new FString();
            FXmlNodes fields = new FXmlNodes();
            for(FXmlNode sub : node){
               if(columns.contains(sub.get("name"))){
                  fields.push(sub);
                  if(!fieldsStr.isEmpty()){
                     fieldsStr.append(",");
                  }
                  fieldsStr.append(sub.get("data_name"));
               }
            }
            //
            FString sql = new FString();
            while(reader.hasNextData()){
               FCsvLine csvline = reader.readLine();
               sql.clear();
               sql.append("INSERT INTO ", data_name, "_DS", "(", fieldsStr, ") VALUES(");
               boolean isFirst = true;
               for(FXmlNode field : fields){
                  String dataType = field.get("type");
                  String value = csvline.get(field.get("name"));
                  value = RString.replace(value, "\\n", "\n");
                  if(value.startsWith(":")){
                     value = value.substring(1, value.length());
                  }
                  value = RSql.formatValue(value);
                  String dataName = field.get("data_name");
                  if(isFirst){
                     isFirst = false;
                  }else{
                     sql.append(",");
                  }
                  if("OGID".equals(dataName)){
                     if(RString.isEmpty(value)){
                        sql.append("SYS_GUID()");
                     }else{
                        sql.append('\'', value, '\'');
                     }
                     continue;
                  }
                  if(RString.isEmpty(value)){
                     sql.append(RSql.NULL);
                  }else if(dataType.equals("Date")){
                     sql.append("TO_DATE(", '\'', value, '\'', ",'YYYYMMDDHH24MISS')");
                  }else if(dataType.equals("Integer")){
                     sql.append(value);
                  }else{
                     sql.append('\'', value, '\'');
                  }
               }
               sql.append(")");
               sqlCnn.executeSql(sql.toString());
            }
            if(disableKey){
               try{
                  tableForeignKeyEnable(sqlCnn, data_name);
               }catch(Exception e){
                  _logger.error(this, "dataRestore", e, "Enable forkey failure.");
               }
            }
            maxSequence(sqlCnn, data_name);
            reader.close();
         }
      }finally{
         if(null != sqlCnn){
            _databaseConsole.free(sqlCnn);
         }
      }
   }

   @Override
   public void dataRestoreAll(IXmlObject xdataset){
      ISqlConnection sqlCnn = null;
      try{
         sqlCnn = _databaseConsole.alloc();
         FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlCnn);
         args.setInstance(xdataset);
         // 建立数据表
         args.setAction(EDatasetBuildAction.Query);
         args.setType(EDatasetSourceType.SqlTable);
         build(args);
         sqlCnn.executeSqls(args.source().toString());
         args.setAction(EDatasetBuildAction.Store);
         build(args);
         // 建立数据视图
         args.setAction(EDatasetBuildAction.Query);
         args.setType(EDatasetSourceType.SqlView);
         build(args);
         sqlCnn.executeSqls(args.source().toString());
         // 建立数据视图
         args.setAction(EDatasetBuildAction.Query);
         args.setType(EDatasetSourceType.SqlSequence);
         build(args);
         sqlCnn.executeSqls(args.source().toString());
         // 建立数据包头
         String dataLogic = xdataset.innerGet(XDataStore.PTY_DATA_LOGIC);
         FSqlPackageParser parser = new FSqlPackageParser(sqlCnn, dataLogic);
         FString define = parser.fetchUserDefine(EDatasetSourceType.PackageHead);
         args.attributes().set(FSqlPackageParser.USER_DEFINE, define);
         args.setAction(EDatasetBuildAction.Execute);
         args.setType(EDatasetSourceType.PackageHead);
         build(args);
         sqlCnn.executeSql(args.source().toString());
         args.setAction(EDatasetBuildAction.Store);
         build(args);
         // 建立数据包体
         define = parser.fetchUserDefine(EDatasetSourceType.PackageBody);
         args.attributes().set(FSqlPackageParser.USER_DEFINE, define);
         args.setAction(EDatasetBuildAction.Execute);
         args.setType(EDatasetSourceType.PackageBody);
         build(args);
         sqlCnn.executeSql(args.source().toString());
         args.setAction(EDatasetBuildAction.Store);
         build(args);
      }finally{
         _databaseConsole.free(sqlCnn);
      }
   }

   @Override
   public void dataStore(IXmlObject xdataset){
      FXmlNode config = buildConfig(xdataset);
      ISqlConnection sqlCnn = null;
      ISqlDatasetReader reader = null;
      sqlCnn = _databaseConsole.alloc();
      String datasetName = xdataset.innerGet("name").toLowerCase();
      String data_name = xdataset.innerGet("data_name");
      String sqlCountSource = "SELECT COUNT(*) COUNT FROM " + data_name;
      FSqlQuery countSource = new FSqlQuery(sqlCnn, sqlCountSource);
      FDataset countDataset = countSource.fetchDataset();
      int recordCount = 0;
      for(FRow row : countDataset){
         recordCount = Integer.parseInt(row.get("count"));
      }
      String tableName = data_name + "_DS";
      FCsvWriter csvWriter = new FCsvWriter();
      File f = new File(_installDataTable);
      if(!f.exists()){
         f.mkdirs();
      }
      String installDataPath = RFile.format(_installDataTable + datasetName + _csv);
      try{
         if(recordCount > 0){
            // 保存CSV
            _logger.debug(this, "dataStore", "Store dataset. (count={1}, file_name={2})", recordCount, installDataPath);
            FXmlNode node = config.findNode("Fields");
            int count = node.nodes().count();
            String sqlSource = _resource.findString("data.table.column");
            sqlSource = RString.parse(sqlSource, "table_name", tableName);
            FSqlQuery columnSource = new FSqlQuery(sqlCnn, sqlSource);
            FDataset columnDataset = columnSource.fetchDataset();
            FAttributes existsColumns = new FAttributes();
            FAttributes columns = new FAttributes();
            for(FRow row : columnDataset){
               columns.set(row.get("column_name"), null);
            }
            for(int n = 0; n < count; n++){
               FXmlNode fieldNode = node.node(n);
               String fieldName = fieldNode.get("data_name");
               boolean exists = columns.contains(fieldName);
               if(exists){
                  FCsvColumn column = new FCsvColumn();
                  String name = fieldNode.get("name");
                  column.setName(name);
                  String label = fieldNode.get("label");
                  column.setLabel(label);
                  csvWriter.columns().set(name, column);
                  existsColumns.set(fieldName, name);
               }
               fieldNode.set("is_exists", RBoolean.toString(exists));
            }
            String[] names = existsColumns.values();
            csvWriter.openFile(installDataPath, _encode);
            csvWriter.doCommand(RCsvCommand.Head);
            csvWriter.doCommand(RCsvCommand.Label);
            // 版本
            FCsvCommandProperty versionPropert = new FCsvCommandProperty();
            versionPropert.setName("version");
            versionPropert.setValue("1.0.0");
            csvWriter.doCommand(versionPropert);
            // 作者
            FCsvCommandProperty authorProperty = new FCsvCommandProperty();
            authorProperty.setName("author");
            authorProperty.setValue("eUIS-system");
            csvWriter.doCommand(authorProperty);
            // 生成时间
            //FCsvCommandProperty dateProperty = new FCsvCommandProperty();
            //dateProperty.setName("create_date");
            //dateProperty.setValue(RDate.format());
            //csvWriter.doCommand(dateProperty);
            // 数据集
            FCsvCommandProperty property = new FCsvCommandProperty();
            property.setName("dataset_name");
            property.setValue(datasetName);
            csvWriter.doCommand(property);
            csvWriter.doCommand(RCsvCommand.Data);
            // Execute
            FSql sql = RDatasetSqlBuilder.makeFetchSql(config);
            reader = sqlCnn.activeConnection().fetchReader(sql);
            ICsvLine line = csvWriter.createLine();
            for(FRow row : reader){
               line.clear();
               for(String name : names){
                  String rowValue = row.get(name);
                  if(null != rowValue && !rowValue.isEmpty()){
                     if(rowValue.contains("\n")){
                        rowValue = RString.replace(rowValue, "\n", "\\n");
                     }
                     if(rowValue.contains("\t")){
                        rowValue = RString.replace(rowValue, "\t", "\\t");
                     }
                     rowValue = ":" + rowValue;
                  }
                  line.set(name, rowValue);
               }
               csvWriter.writeLine(line);
            }
            csvWriter.doCommand(RCsvCommand.End);
            csvWriter.doCommand(RCsvCommand.ValidLines);
         }
      }catch(Exception e){
         throw new FFatalError(e, "Data store failure. (dataset={1})", datasetName);
      }finally{
         if(null != reader){
            reader.close();
         }
         csvWriter.Close();
         _databaseConsole.free(sqlCnn);
      }
   }

   @Override
   public void dsDelete(String name,
                        IAttributes row){
      //      IXmlObject xdataset = find(name);
      //      if(_logger.debugAble()){
      //         _logger.debug(this, "dsDelete", "Delete row. (name={1}, dataset={2}, row={3})", name, xdataset, row.dump());
      //      }
      //      if(null != xdataset){
      //         IEntityDao dao = xdataset.entityDao();
      //         if(null != dao){
      //            IEntity entity = dao.createEntity(null, row, EEntityTransfer.Value);
      //            dao.doDelete(null, entity);
      //         }else{
      //            sqlExecute(xdataset.makeDeleteSql(row));
      //         }
      //      }
   }

   @Override
   public FDataset dsFetch(String name,
                           FStrings fields,
                           FSqlSearchFields searchs,
                           int page,
                           int pageSize){
      //      IXmlObject xdataset = find(name);
      //      if(null != xdataset){
      //         IEntityDao dao = xdataset.entityDao();
      //         FDataset dataset = null;
      //         if(null != dao){
      //            IEntity[] entities = dao.fetch(null, searchs);
      //            if(null != entities){
      //               dataset = new FDataset();
      //               for(IEntity entity : entities){
      //                  FRow row = new FRow();
      //                  if(null != fields){
      //                     for(@SuppressWarnings("unused")
      //                     String field : fields){
      //                        Object value = entity.values().get(name);
      //                        if(value instanceof FMultiString){
      //                           // type new_name = (type) ;
      //                        }
      //                        // row.set(field, entity.values().get(name)
      //                        // .innerGet(field));
      //                     }
      //                  }else{
      //                     // entity.innerSaveValue(row);
      //                  }
      //                  dataset.push(row);
      //               }
      //            }
      //         }else{
      //            FSql sql = xdataset.makeFetchSql(null);
      //            // Execute
      //            ISqlConnection sqlCnn = null;
      //            try{
      //               sqlCnn = _databaseConsole.alloc();
      //               dataset = sqlCnn.fetchDataset(sql);
      //            }finally{
      //               _databaseConsole.free(sqlCnn);
      //            }
      //         }
      //         return dataset;
      //      }
      return null;
   }

   @Override
   public void dsInsert(String name,
                        IAttributes row){
      //      IXmlObject xdataset = find(name);
      //      if(_logger.debugAble()){
      //         _logger.debug(this, "dsInsert", "Insert row. (name={1}, dataset={2}, row={3})", name, xdataset, row.dump());
      //      }
      //      if(null != xdataset){
      //         IEntityDao dao = xdataset.entityDao();
      //         if(null != dao){
      //            IEntity entity = dao.createEntity(null, row, EEntityTransfer.Value);
      //            dao.doInsert(null, entity);
      //         }else{
      //            sqlExecute(xdataset.makeInsertSql(row));
      //         }
      //      }
   }

   @Override
   public void dsUpdate(String name,
                        IAttributes row){
      //      IXmlObject xdataset = find(name);
      //      if(_logger.debugAble()){
      //         _logger.debug(this, "dsUpdate", "Update row. (name={1}, dataset={2}, row={3})", name, xdataset, row.dump());
      //      }
      //      if(null != xdataset){
      //         IEntityDao<IEntity> dao = xdataset.entityDao();
      //         if(null != dao){
      //            IEntity entity = dao.createEntity(null, row, EEntityTransfer.Value);
      //            dao.doUpdate(null, entity);
      //         }else{
      //            sqlExecute(xdataset.makeUpdateSql(row));
      //         }
      //      }
   }

   //   @Override
   //   public void dataRestore(IXmlObject xdataset){
   //      // Execute
   //      ISqlConnection sqlCnn = null;
   //      try{
   //         FXmlNode config = buildConfig(xdataset);
   //         String dsName = xdataset.innerGet("name");
   //         String filename = RFile.makeFilename(_storepath, dsName + ".txt");
   //         _logger.debug(this, "dataRestore", "Restore dataset. (name={1}, file={2})", dsName, filename);
   //         FLinesFile file = new FLinesFile(filename, "UTF-8");
   //         if(file.length() > 0){
   //            // Clear
   //            sqlCnn = _databaseConsole.alloc();
   //            sqlCnn.executeSql(RDatasetSqlBuilder.makeClearSql(xdataset));
   //            // Execute
   //            for(int n = 0; n < file.count(); n++){
   //               String line = file.line(n);
   //               if(RString.isNotEmpty(line)){
   //                  FRow row = new FRow();
   //                  row.unpack(line);
   //                  if(RString.isEmpty(row.get("object_global_id"))){
   //                     row.set("object_global_id", RUuid.simpleUuid());
   //                  }
   //                  if(RString.isEmpty(row.get("object_valid"))){
   //                     row.set("object_valid", RBoolean.TRUE_STR);
   //                  }
   //                  FSql sql = RDatasetSqlBuilder.makeInsertSql(config, row);
   //                  sqlCnn.executeSql(sql);
   //               }
   //            }
   //         }
   //      }finally{
   //         if(null != sqlCnn){
   //            _databaseConsole.free(sqlCnn);
   //         }
   //      }
   //   }
   @Override
   public String getLogicUnit(){
      return _logicUnit;
   }

   public void insertTableData(IXmlObject dataset,
                               FXmlNode rowNode){
      //      FSql sql = dataset.makeInsertSql(null);
      //      ISqlConnection sqlCnn = null;
      //      try{
      //         sqlCnn = _databaseConsole.alloc();
      //         sqlCnn.executeSql(sql);
      //      }finally{
      //         _databaseConsole.free(sqlCnn);
      //      }
   }

   //   @Override
   //   public void dataStore(IXmlObject xdataset){
   //      FXmlNode config = buildConfig(xdataset);
   //      String dsName = xdataset.innerGet("name");
   //      FSql sql = RDatasetSqlBuilder.makeFetchSql(config);
   //      // Execute
   //      ISqlConnection sqlCnn = null;
   //      try{
   //         sqlCnn = _databaseConsole.alloc();
   //         FDataset dataset = sqlCnn.fetchDataset(sql);
   //         FStringFile file = new FStringFile();
   //         for(FRow row : dataset){
   //            file.appendLine(row.pack(IPack.NORMAL));
   //         }
   //         String filename = RFile.makeFilename(_storepath, dsName + ".txt");
   //         _logger.debug(this, "dataRestore", "Store dataset. (name={1}, file={2})", dsName, filename);
   //         file.saveToFile(filename, "UTF-8");
   //      }catch(Exception e){
   //         throw new FFatalError(e, "Data store failure. (dataset={1})", dsName);
   //      }finally{
   //         _databaseConsole.free(sqlCnn);
   //      }
   //   }
   public void maxSequence(ISqlConnection sqlCnn,
                           String dataName){
      //      // 建立数据库连接
      //      String tableName = dataName + "_DS";
      //      String sequenceName = dataName + "_SQ";
      //      FSqlProcedure procedure = new FSqlProcedure("Max_Sequence");
      //      procedure.setLogicName("CP_SEQUENCE");
      //      procedure.createParameter("table_name_", tableName, ESqlDataType.String, ESqlDataDirection.In);
      //      procedure.createParameter("sequence_name_", sequenceName, ESqlDataType.String, ESqlDataDirection.In);
      //      sqlCnn.activeConnection().execute(procedure);
   }

   @Override
   public void rebuild(){
      //      ISqlConnection cnn = null;
      //      try{
      //         cnn = _databaseConsole.alloc();
      //         ISqlConnectionMeta meta = cnn.meta();
      //         FSqlTable[] tables = meta.listTables();
      //         for(FSqlTable table : tables){
      //            @SuppressWarnings("unused")
      //            FSqlTable tableInfo = meta.findTable(table.name(), true);
      //         }
      //      }finally{
      //         _databaseConsole.free(cnn);
      //      }
   }

   protected void sqlExecute(FSql sql){
      //      // Execute
      //      ISqlConnection sqlCnn = null;
      //      try{
      //         sqlCnn = _databaseConsole.alloc();
      //         sqlCnn.executeSql(sql);
      //      }finally{
      //         _databaseConsole.free(sqlCnn);
      //      }
   }

   @Override
   public void syncTable(IXmlObject xdataset){
      FXmlNode config = buildConfig(xdataset);
      /// 获取数据集中的所有字段处理
      String tableName = xdataset.innerGet("data_name") + "_DS";
      FXmlNode node = config.findNode("Fields");
      int count = node.nodes().count();
      /// 获取数据库中表的所有字段处理
      ISqlConnection sqlCnn = null;
      sqlCnn = _databaseConsole.alloc();
      String sqlSource = _resource.findString("data.table.column");
      sqlSource = RString.parse(sqlSource, "table_name", tableName);
      FSqlQuery columnSource = new FSqlQuery(sqlCnn, sqlSource);
      FDataset columnDataset = columnSource.fetchDataset();
      FAttributes columns = new FAttributes();
      for(FRow row : columnDataset){
         columns.set(row.get("column_name"), null);
      }
      /// 检查字段处理
      for(int n = 0; n < count; n++){
         FXmlNode fieldNode = node.node(n);
         String fieldName = fieldNode.get("data_name");
         /// 检查字段是否存在
         boolean exists = columns.contains(fieldName);
         /// 存在处理
         if(exists){
            /// 获取字段名称
            //            String name = fieldNode.get("name");
            /// 检查字段类型
            /// 检查字段大小
            /// 检查字段缺省值
            /// 检查字段非空处理
            /// 检查唯一键
         }else{
            /// 增加字段（包含：名称，数据类型，大小，是否有默认值）
         }
      }
      /// 检查索引
   }

   public void tableForeignKeyDisable(ISqlConnection sqlCnn,
                                      String tableName){
      //      // 设置禁止外键使用
      //      FSqlProcedure disableProcedure = new FSqlProcedure("Table_Foreign_Key_Disable");
      //      disableProcedure.setLogicName("CP_SQL");
      //      disableProcedure.createParameter("table_name_", tableName, ESqlDataType.String, ESqlDataDirection.In);
      //      sqlCnn.activeConnection().execute(disableProcedure);
   }

   @Override
   public void tableForeignKeyDisableAll(){
      //      ISqlConnection sqlCnn = null;
      //      try{
      //         sqlCnn = _databaseConsole.alloc();
      //         // 设置禁止所有外键使用
      //         FSqlProcedure disableProcedure = new FSqlProcedure("Table_Foreign_Key_Disable_All");
      //         disableProcedure.setLogicName("CP_SQL");
      //         sqlCnn.activeConnection().execute(disableProcedure);
      //      }finally{
      //         if(null != sqlCnn){
      //            _databaseConsole.free(sqlCnn);
      //         }
      //      }
   }

   public void tableForeignKeyEnable(ISqlConnection sqlCnn,
                                     String tableName){
      // 设置恢复外键使用
      //      FSqlProcedure enableProcedure = new FSqlProcedure("Table_Foreign_Key_Enable");
      //      enableProcedure.setLogicName("CP_SQL");
      //      enableProcedure.createParameter("table_name_", tableName, ESqlDataType.String, ESqlDataDirection.In);
      //      sqlCnn.activeConnection().execute(enableProcedure);
   }

   @Override
   public void tableForeignKeyEnableAll(){
      //      ISqlConnection sqlCnn = null;
      //      // 设置恢复所有外键使用
      //      try{
      //         sqlCnn = _databaseConsole.alloc();
      //         FSqlProcedure enableProcedure = new FSqlProcedure("Table_Foreign_Key_Enable_All");
      //         enableProcedure.setLogicName("CP_SQL");
      //         sqlCnn.activeConnection().execute(enableProcedure);
      //      }finally{
      //         if(null != sqlCnn){
      //            _databaseConsole.free(sqlCnn);
      //         }
      //      }
   }
}

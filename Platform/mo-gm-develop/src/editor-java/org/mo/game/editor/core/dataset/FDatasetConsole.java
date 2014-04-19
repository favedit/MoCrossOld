package org.mo.game.editor.core.dataset;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSql;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.FSqlTable;
import org.mo.com.data.ISqlConnection;
import org.mo.com.data.ISqlConnectionMeta;
import org.mo.com.data.ISqlDatasetReader;
import org.mo.com.data.RSql;
import org.mo.com.io.FLinesFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.text.RDatabaseFormat;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.data.dataset.base.XBaseDataProperty;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.eng.template.ITemplateConsole;
import org.mo.game.editor.core.dataset.base.XBaseDataStore;
import org.mo.game.editor.core.dataset.common.FDatasetSourceBuilder;
import org.mo.game.editor.core.dataset.common.RDatasetSqlBuilder;
import org.mo.game.editor.core.dataset.common.XDataStore;
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
   protected String _sourceJavaDomainPath;

   @AProperty
   protected String _sourceJavaDataPath;

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
   protected String _viewUnit;

   @AProperty
   protected String _sourceServerPath;

   @Override
   public void build(FDatasetBuilderArgs builderArgs){
      // 获取传过来的类型（FXmlNode,XDataset类型）
      EDatasetSourceType type = builderArgs.type();
      EDatasetBuildAction action = builderArgs.action();
      FDatasetSourceBuilder sourceBuilder = new FDatasetSourceBuilder(_templateConsole);
      if(_logger.debugAble()){
         _logger.debug(this, "build", "Build source (type={1}, action={2}, name={3})", type, action, builderArgs.name());
      }
      // 获得设置信息
      IXmlObject xdataset = builderArgs.instance();
      String dataName = xdataset.innerGet(XDataStore.PTY_NAME);
      String tableName = xdataset.innerGet(XDataStore.PTY_DATA_NAME);
      // 建立XML节点信息
      FXmlNode dataset = convertInstanceToNode(xdataset, EXmlConfig.Simple);
      // 建立模板控制台
      String name = RString.replace(xdataset.innerGet(XDataStore.PTY_NAME), '.', '_');
      name = RDatabaseFormat.toJavaClassName(name);
      dataset.set("class_name", name);
      // 建立及卸载数据集的数据表代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.SqlTable == type){
         // 生成SQL代码
         FXmlNode config = buildConfig(dataName);
         builderArgs.setSource(sourceBuilder.makeSqlTable(config));
         // 如果是存储模式的话，保存生成内容到文件
         if(EDatasetBuildAction.Store == action){
            String fileName = RFile.format(_logicUnit + dataName + "/install_table.sql").toLowerCase();
            RFile.saveToFile(fileName, builderArgs.source(), REncoding.UTF8);
            _logger.info(this, "build", "Build table sql (file={1})", fileName);
            // 卸载数据集的数据表代码
            String uninstallfilename = RFile.format(_logicUnit + dataName + "/uninstall_table.sql").toLowerCase();
            RFile.saveToFile(uninstallfilename, sourceBuilder.makeSqlTableDelete(dataset), REncoding.UTF8);
            _logger.info(this, "build", "Delete table sql (file={1})", uninstallfilename);
            // 保存卸载数据的文件
            FString dataSource = new FString();
            dataSource.append("DELETE FROM ", tableName + "_DS");
            String filename = RFile.format(_logicUnit + dataName + "/uninstall_clear_table.sql");
            RFile.saveToFile(filename, dataSource, REncoding.UTF8);
         }
      }
      // 建立数据集的删除数据表代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.SqlTableDelete == type){
         builderArgs.setSource(sourceBuilder.makeSqlTableDelete(dataset));
      }
      // 建立CPP定义代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.CppDefine == type){
         FString source = sourceBuilder.makeCppDefine(builderArgs.config(), builderArgs.dataGroup(), builderArgs.source());
         builderArgs.setSource(source);
      }
      // 建立CPP头代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.CppHead == type){
         // 生成SQL代码
         FXmlNode config = buildConfig(dataName);
         builderArgs.setConfig(config);
         builderArgs.setSource(sourceBuilder.makeCppHead(config));
      }
      // 建立CPP体代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.CppBody == type){
         FXmlNode config = buildConfig(dataName);
         builderArgs.setSource(sourceBuilder.makeCppBody(config));
      }
      // 建立Java代码
      if(EDatasetSourceType.All == type || EDatasetSourceType.JavaAll == type){
         String packageName = null;
         String groupName = null;
         String groupPath = null;
         if(dataName.startsWith("domain.")){
            groupName = RString.left(dataName, ".");
            groupPath = _sourceJavaDomainPath;
            packageName = "org.mo.data.face";
         }else{
            groupName = RString.left(dataName, ".");
            groupPath = _sourceJavaDataPath + groupName;
            packageName = "com.zqinet.logic.data." + groupName.toLowerCase();
         }
         FXmlNode config = buildConfig(dataName);
         String className = "F" + RDatabaseFormat.toJavaClassName(dataName.replace('.', '_'));
         config.set("source_package_name", packageName);
         config.set("source_class_name", className);
         // 创建单元
         String unitFileName = groupPath + "/" + className + "Unit.java";
         FString source = sourceBuilder.makeJavaUnit(config);
         RFile.saveToFile(unitFileName, source.toString(), "utf-8");
         _logger.debug(this, "build", "Build java source. (file_name={1})", unitFileName);
         // 创建逻辑
         String logicFileName = groupPath + "/" + className + "Logic.java";
         source = sourceBuilder.makeJavaLogic(config);
         RFile.saveToFile(logicFileName, source.toString(), "utf-8");
         _logger.debug(this, "build", "Build java source. (file_name={1})", logicFileName);
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
            if("RecordId".equals(fieldType)){
               fieldType = "BIGINT";
               fieldNode.set("java_type", "long");
            }else if("Boolean".equals(fieldType)){
               fieldType = "TINYINT";
               fieldNode.set("java_type", "boolean");
            }else if("Integer".equals(fieldType)){
               fieldType = "INTEGER";
               fieldNode.set("java_type", "int");
            }else if("Float".equals(fieldType)){
               fieldType = "FLOAT";
               fieldNode.set("java_type", "float");
            }else if("Char".equals(fieldType)){
               fieldType = "CHAR(" + fieldNode.get("data_size") + ")";
               fieldNode.set("java_type", "String");
            }else if("String".equals(fieldType)){
               fieldType = "VARCHAR(" + fieldNode.get("data_size") + ")";
               fieldNode.set("java_type", "String");
            }else if("Content".equals(fieldType)){
               fieldType = "VARCHAR(2000)";
               fieldNode.set("java_type", "String");
            }else if("Date".equals(fieldType)){
               fieldType = "DATE";
               fieldNode.set("java_type", "TDateTime");
            }else if("DateTime".equals(fieldType)){
               fieldType = "DATETIME";
               fieldNode.set("java_type", "TDateTime");
            }else if("TimeSpan".equals(fieldType)){
               fieldType = "INTEGER";
               fieldNode.set("java_type", "TDateTime");
            }else if("Enum".equals(fieldType)){
               fieldType = "CHAR(" + fieldNode.get("data_size") + ")";
               fieldNode.set("java_type", "int");
            }else if("Set".equals(fieldType)){
               fieldType = "CHAR(" + fieldNode.get("data_size") + ")";
               fieldNode.set("java_type", "int");
            }
            fieldNode.set("data_type", fieldType);
            if(RString.endsWith(fieldType, "Property")){
               fieldNode.set("is_property", RBoolean.TRUE_STR);
               fieldType = fieldType.substring(0, fieldType.length() - 8);
               fieldNode.set("type", fieldType);
               if(RString.hasChars(fieldType, "RecordId")){
                  fieldNode.set("data_type", "Long");
               }else if(RString.hasChars(fieldType, "Integer")){
                  fieldNode.set("data_type", "Integer");
               }else if(RString.hasChars(fieldType, "Float")){
                  fieldNode.set("data_type", "Float");
               }else if(RString.hasChars(fieldType, "Double")){
                  fieldNode.set("data_type", "Double");
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
               sqlCnn.executeSql(sql);
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
         //         sqlCnn = _databaseConsole.alloc();
         //         FDatasetBuilderArgs args = new FDatasetBuilderArgs(sqlCnn);
         //         args.setInstance(xdataset);
         //         // 建立数据表
         //         args.setAction(EDatasetBuildAction.Query);
         //         args.setType(EDatasetSourceType.SqlTable);
         //         build(args);
         //         sqlCnn.executeSqls(args.source());
         //         args.setAction(EDatasetBuildAction.Store);
         //         build(args);
         //         // 建立数据视图
         //         args.setAction(EDatasetBuildAction.Query);
         //         args.setType(EDatasetSourceType.SqlView);
         //         build(args);
         //         sqlCnn.executeSqls(args.source());
         //         // 建立数据视图
         //         args.setAction(EDatasetBuildAction.Query);
         //         args.setType(EDatasetSourceType.SqlSequence);
         //         build(args);
         //         sqlCnn.executeSqls(args.source());
         //         // 建立数据包头
         //         String dataLogic = xdataset.innerGet(XDataStore.PTY_DATA_LOGIC);
         //         FSqlPackageParser parser = new FSqlPackageParser(sqlCnn, dataLogic);
         //         FString define = parser.fetchUserDefine(EDatasetSourceType.PackageHead);
         //         args.attributes().set(FSqlPackageParser.USER_DEFINE, define);
         //         args.setAction(EDatasetBuildAction.Execute);
         //         args.setType(EDatasetSourceType.PackageHead);
         //         build(args);
         //         sqlCnn.executeSql(args.source());
         //         args.setAction(EDatasetBuildAction.Store);
         //         build(args);
         //         // 建立数据包体
         //         define = parser.fetchUserDefine(EDatasetSourceType.PackageBody);
         //         args.attributes().set(FSqlPackageParser.USER_DEFINE, define);
         //         args.setAction(EDatasetBuildAction.Execute);
         //         args.setType(EDatasetSourceType.PackageBody);
         //         build(args);
         //         sqlCnn.executeSql(args.source());
         //         args.setAction(EDatasetBuildAction.Store);
         //         build(args);
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
         recordCount = Integer.parseInt(row.get("COUNT"));
      }
      String tableName = data_name + "_DS";
      FCsvWriter csvWriter = new FCsvWriter();
      String installDataPath = RFile.format(_installDataTable + datasetName + _csv);
      try{
         if(0 < recordCount){
            // 保存CSV
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
            FSql sql = RDatasetSqlBuilder.makeFetchSql(config);
            // Execute
            //      try{
            reader = sqlCnn.activeConnection().fetchReader(sql);
            ICsvLine line = csvWriter.createLine();
            while(reader.hasNext()){
               line.clear();
               FRow row = reader.next();
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

   public void maxSequence(ISqlConnection sqlCnn,
                           String dataName){
      // 建立数据库连接
      String tableName = dataName + "_DS";
      String sequenceName = dataName + "_SQ";
      FSqlProcedure procedure = new FSqlProcedure("Max_Sequence");
      procedure.setLogicName("CP_SEQUENCE");
      procedure.createParameter("table_name_", tableName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("sequence_name_", sequenceName, ESqlDataType.String, ESqlDataDirection.In);
      sqlCnn.activeConnection().execute(procedure);
   }

   @Override
   public void rebuild(){
      ISqlConnection cnn = null;
      try{
         cnn = _databaseConsole.alloc();
         ISqlConnectionMeta meta = cnn.meta();
         FSqlTable[] tables = meta.listTables();
         for(FSqlTable table : tables){
            @SuppressWarnings("unused") FSqlTable tableInfo = meta.findTable(table.name(), true);
         }
      }finally{
         _databaseConsole.free(cnn);
      }
   }

   @Override
   public String sourceServerPath(){
      return _sourceServerPath;
   }

   protected void sqlExecute(FSql sql){
      // Execute
      ISqlConnection sqlCnn = null;
      try{
         sqlCnn = _databaseConsole.alloc();
         sqlCnn.executeSql(sql);
      }finally{
         _databaseConsole.free(sqlCnn);
      }
   }
}

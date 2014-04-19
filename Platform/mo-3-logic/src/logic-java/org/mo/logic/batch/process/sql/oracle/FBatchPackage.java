package org.mo.logic.batch.process.sql.oracle;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnection;
import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.codelist.ICodeListConsole;
import org.mo.data.codelist.common.FCodeListSourceBuilder;
import org.mo.data.codelist.common.XCodeList;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.data.dataset.common.FDatasetSourceBuilder;
import org.mo.data.dataset.common.XDataProperty;
import org.mo.data.dataset.common.XDataStore;
import org.mo.eng.data.common.FSqlPackageParser;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.template.ITemplateConsole;
import org.mo.logic.batch.common.FBatchProcess;
import org.mo.logic.batch.process.FBatchSqlCommand;

public class FBatchPackage
      extends FBatchSqlCommand
      implements
         IBatchPackage
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FBatchProcess.class);

   // 获得xml中的sql文
   private static IResource _resource = RResource.find(FBatchPackage.class);

   private final static int MAX_FETCH = 60000;

   private final static String CREATE_PACKAGE = "CREATE OR REPLACE ";

   // 输出路径
   private String _outputFile = "D:/euis-dataset";

   // 输出路径
   @SuppressWarnings("unused")
   private final String _zipFile = "";

   // 数据库名称
   private final static String SYN_DATABASE_NAME = "syn_name";

   // 数据库名称
   private final static String DATABASE_NAME = "data_name";

   // 文件路径
   private final static String FILE_PATH = "path";

   // 获得数据集控制台
   @ALink
   protected IDatasetConsole _datasetConsole;

   // 获得数据集控制台
   @ALink
   protected ICodeListConsole _codeListConsole;

   @ALink
   private ITemplateConsole _templateConsole;

   @Override
   public void backupListPackage(FXmlNode config,
                                 FStrings allLog,
                                 FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      String path = config.get(FILE_PATH);
      setOutPutPath(path);
      for(String item : items){
         String dataset = item;
         if(RString.isNotEmpty(dataset)){
            if(TYPE_ALL.equalsIgnoreCase(dataset)){
               for(IXmlObject xcodeList : _codeListConsole.list()){
                  if(!RBoolean.parse(xcodeList.innerGet(XCodeList.PTY_IS_VALID))){
                     continue;
                  }
                  FXmlNode codeListNode = _codeListConsole.convertInstanceToNode(xcodeList);
                  String packageName = codeListNode.get(XCodeList.PTY_DATA_NAME);
                  String filePath = packageName + "_EI";
                  if(RString.isNotEmpty(packageName)){
                     //根据数据集模板创建包头sql
                     FString sqlHead = headSourceDataset(null, codeListNode);
                     // 写入包头文件
                     if(!sqlHead.isEmpty()){
                        writeFile((filePath + "/head.txt"), sqlHead);
                     }else{
                        failLog.push("定义列表包包头：" + filePath + "\n" + "出错原因为：包头sql文为空");
                     }
                     //根据数据集模板创建包体sql
                     FString sqlBody = bodySourceDataset(null, codeListNode);
                     // 写入包体文件
                     if(!sqlBody.isEmpty()){
                        writeFile((filePath + "/body.txt"), sqlBody);
                     }else{
                        failLog.push("定义列表包包体：" + filePath + "\n" + "出错原因为：包体sql文为空");
                     }
                     allLog.push("定义列表包：" + filePath);
                  }else{
                     failLog.push("定义列表：" + dataset + "\n" + "出错原因为：数据名称为空。");
                  }
               }
            }else{
               IXmlObject xcodeList = _codeListConsole.find(dataset);
               if(RBoolean.parse(xcodeList.innerGet(XCodeList.PTY_IS_VALID))){
                  FXmlNode codeListNode = _codeListConsole.convertInstanceToNode(xcodeList);
                  String packageName = codeListNode.get(XCodeList.PTY_DATA_NAME);
                  String filePath = packageName + "_EI";
                  if(RString.isNotEmpty(packageName)){
                     //根据数据集模板创建包头sql
                     FString sqlHead = headSourceDataset(null, codeListNode);
                     // 写入包头文件
                     if(!sqlHead.isEmpty()){
                        writeFile((filePath + "/head.txt"), sqlHead);
                     }else{
                        failLog.push("定义列表包包头：" + filePath + "\n" + "出错原因为：包头sql文为空");
                     }
                     //根据数据集模板创建包体sql
                     FString sqlBody = bodySourceDataset(null, codeListNode);
                     // 写入包体文件
                     if(!sqlBody.isEmpty()){
                        writeFile((filePath + "/body.txt"), sqlBody);
                     }else{
                        failLog.push("定义列表包包体：" + filePath + "\n" + "出错原因为：包体sql文为空");
                     }
                     allLog.push("定义列表包：" + filePath);
                  }else{
                     failLog.push("定义列表：" + dataset + "\n" + "出错原因为：数据名称为空。");
                  }
               }
            }
         }
      }
   }

   @Override
   public void backupPackage(FXmlNode config,
                             FStrings allLog,
                             FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      // 数据库连接
      ISqlConnection synConnection = null;
      String synDatabaseName = config.get(DATABASE_NAME);
      try{
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synDatabaseName).activeConnection();
      }catch(Exception e){
         failLog.push("备份包时数据库连接为空" + "\n" + "出错原因为：" + e.toString());
         if(null != synConnection){
            _databaseConsole.free(synConnection);
         }
         return;
      }
      if(null != synConnection){
         String path = config.get(FILE_PATH);
         setOutPutPath(path);
         for(String item : items){
            if(RString.isNotEmpty(item)){
               //获得包头sql
               FString sqlHead = headSource(synConnection, item);
               // 写入包头文件
               if(null != sqlHead && !sqlHead.isEmpty()){
                  writeFile((item + "/head.txt"), sqlHead);
               }else{
                  failLog.push("数据包：item" + "\n" + "出错原因为：数据包头Sql文为空。");
               }
               FString sqlbody = bodySource(synConnection, item);
               // 写入包头文件
               if(null != sqlbody && !sqlbody.isEmpty()){
                  writeFile((item + "/body.txt"), sqlbody);
               }else{
                  failLog.push("数据包：item" + "\n" + "出错原因为：数据包体Sql文为空。");
               }
               allLog.push("数据包：" + item);
            }
         }
      }
      if(null != synConnection){
         _databaseConsole.free(synConnection);
      }
   }

   @Override
   public void backupPackageDataset(FXmlNode config,
                                    FStrings allLog,
                                    FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      String path = config.get(FILE_PATH);
      String databaseName = config.get(DATABASE_NAME);
      setOutPutPath(path);
      // 数据库连接
      ISqlConnection connection = null;
      try{
         // 获得被同步的数据库连接
         connection = _databaseConsole.alloc(databaseName).activeConnection();
      }catch(Exception e){
         failLog.push("通过数据集备份包时数据库连接为空" + "\n" + "出错原因为：" + e.toString());
         // 如果数据库连接不是空的话则释放被同步的数据库连接
         if(null != connection){
            _databaseConsole.free(connection);
         }
         return;
      }
      for(String item : items){
         String dataset = item;
         if(RString.isNotEmpty(dataset)){
            if(TYPE_ALL.equalsIgnoreCase(dataset)){
               for(IXmlObject xdataset : _datasetConsole.list()){
                  if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                     continue;
                  }
                  FXmlNode datasetNode = _datasetConsole.buildConfig(xdataset);
                  String packageName = datasetNode.get(XDataStore.PTY_DATA_LOGIC);
                  //根据数据集模板创建包头sql
                  FString sqlHead = headSourceDataset(connection, datasetNode);
                  // 写入包头文件
                  if(null != sqlHead && !sqlHead.isEmpty()){
                     writeFile((packageName + "/head.txt"), sqlHead);
                  }else{
                     failLog.push("数据包备份：" + packageName + "\n" + "出错原因为：包头Sql文为空。");
                  }
                  //根据数据集模板创建包体sql
                  FString sqlBody = bodySourceDataset(connection, datasetNode);
                  // 写入包头文件
                  if(null != sqlBody && !sqlBody.isEmpty()){
                     writeFile((packageName + "/body.txt"), sqlBody);
                  }else{
                     failLog.push("数据包备份：" + packageName + "\n" + "出错原因为：包体Sql文为空。");
                  }
                  if("DataStore".equalsIgnoreCase(xdataset.name())){
                     String packageNameHis = packageName.substring(0, packageName.length() - 2) + "HI";
                     //根据数据集模板创建历史包头sql
                     FString sqlHeadHis = headSourceHisDataset(connection, datasetNode);
                     // 写入包头文件
                     if(null != sqlHeadHis && !sqlHeadHis.isEmpty()){
                        writeFile((packageNameHis + "/head.txt"), sqlHeadHis);
                     }else{
                        failLog.push("数据包备份：" + packageNameHis + "\n" + "出错原因为：包头Sql文为空。");
                     }
                     //根据数据集模板创建历史包体sql
                     FString sqlBodyHis = bodySourceHisDataset(connection, datasetNode);
                     // 写入包头文件
                     if(null != sqlBodyHis && !sqlBodyHis.isEmpty()){
                        writeFile((packageNameHis + "/body.txt"), sqlBodyHis);
                     }else{
                        failLog.push("数据包备份：" + packageNameHis + "\n" + "出错原因为：包体Sql文为空。");
                     }
                  }
                  allLog.push("备份数据包：" + packageName);
               }
            }else{
               IXmlObject xdataset = _datasetConsole.find(dataset);
               if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                  FXmlNode datasetNode = _datasetConsole.buildConfig(xdataset);
                  String packageName = datasetNode.get(XDataStore.PTY_DATA_LOGIC);
                  //根据数据集模板创建包头sql
                  FString sqlHead = headSourceDataset(connection, datasetNode);
                  // 写入包头文件
                  if(null != sqlHead && !sqlHead.isEmpty()){
                     writeFile((packageName + "/head.txt"), sqlHead);
                  }else{
                     failLog.push("数据包备份：" + packageName + "\n" + "出错原因为：包头Sql文为空。");
                  }
                  //根据数据集模板创建包体sql
                  FString sqlBody = bodySourceDataset(connection, datasetNode);
                  // 写入包头文件
                  if(null != sqlBody && !sqlBody.isEmpty()){
                     writeFile((packageName + "/body.txt"), sqlBody);
                  }else{
                     failLog.push("数据包备份：" + packageName + "\n" + "出错原因为：包体Sql文为空。");
                  }
                  if("DataStore".equalsIgnoreCase(xdataset.name())){
                     String packageNameHis = packageName.substring(0, packageName.length() - 1) + "HI";
                     //根据数据集模板创建历史包头sql
                     FString sqlHeadHis = headSourceHisDataset(connection, datasetNode);
                     // 写入包头文件
                     if(null != sqlHeadHis && !sqlHeadHis.isEmpty()){
                        writeFile((packageNameHis + "/head.txt"), sqlHeadHis);
                     }else{
                        failLog.push("数据包备份：" + packageNameHis + "\n" + "出错原因为：包头Sql文为空。");
                     }
                     //根据数据集模板创建历史包体sql
                     FString sqlBodyHis = bodySourceHisDataset(connection, datasetNode);
                     // 写入包头文件
                     if(null != sqlBodyHis && !sqlBodyHis.isEmpty()){
                        writeFile((packageNameHis + "/body.txt"), sqlBodyHis);
                     }else{
                        failLog.push("数据包备份：" + packageNameHis + "\n" + "出错原因为：包体Sql文为空。");
                     }
                  }
                  allLog.push("备份数据包：" + packageName);
               }
            }
         }
      }
      // 如果数据库连接不是空的话则释放被同步的数据库连接
      if(null != connection){
         _databaseConsole.free(connection);
      }
   }

   @Override
   public void backupPropertyPackageDataset(FXmlNode config,
                                            FStrings allLog,
                                            FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      String path = config.get(FILE_PATH);
      String databaseName = config.get(DATABASE_NAME);
      setOutPutPath(path);
      // 数据库连接
      ISqlConnection connection = null;
      try{
         // 获得被同步的数据库连接
         connection = _databaseConsole.alloc(databaseName).activeConnection();
      }catch(Exception e){
         failLog.push("属性包备份：数据库连接出错" + "\n" + "出错原因为：" + e.toString());
         // 如果数据库连接不是空的话则释放被同步的数据库连接
         if(null != connection){
            _databaseConsole.free(connection);
         }
         return;
      }
      for(String item : items){
         String dataset = item;
         if(RString.isNotEmpty(dataset)){
            if(TYPE_ALL.equalsIgnoreCase(dataset)){
               for(IXmlObject xdataset : _datasetConsole.list()){
                  if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name())){
                     if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                        continue;
                     }
                     FXmlNode datasetNode = _datasetConsole.buildConfig(xdataset);
                     String packageName = datasetNode.get(XDataStore.PTY_DATA_LOGIC);
                     //根据数据集模板创建包头sql
                     FString sqlHead = headSourceDataset(connection, datasetNode);
                     // 写入包头文件
                     if(null != sqlHead && !sqlHead.isEmpty()){
                        writeFile((packageName + "/head.txt"), sqlHead);
                     }else{
                        failLog.push("属性包备份：" + packageName + "\n" + "出错原因为：包头Sql文为空。");
                     }
                     //根据数据集模板创建包体sql
                     FString sqlBody = bodySourceDataset(connection, datasetNode);
                     // 写入包头文件
                     if(null != sqlBody && !sqlBody.isEmpty()){
                        writeFile((packageName + "/body.txt"), sqlBody);
                     }else{
                        failLog.push("属性包备份：" + packageName + "\n" + "出错原因为：包体Sql文为空。");
                     }
                     allLog.push("备份属性包：" + packageName);
                  }
               }
            }else{
               IXmlObject xdataset = _datasetConsole.find(dataset);
               if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name())){
                  if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                     FXmlNode datasetNode = _datasetConsole.buildConfig(xdataset);
                     String packageName = datasetNode.get(XDataStore.PTY_DATA_LOGIC);
                     //根据数据集模板创建包头sql
                     FString sqlHead = headSourceDataset(connection, datasetNode);
                     // 写入包头文件
                     if(null != sqlHead && !sqlHead.isEmpty()){
                        writeFile((packageName + "/head.txt"), sqlHead);
                     }else{
                        failLog.push("属性包备份：" + packageName + "\n" + "出错原因为：包头Sql文为空。");
                     }
                     //根据数据集模板创建包体sql
                     FString sqlBody = bodySourceDataset(connection, datasetNode);
                     // 写入包头文件
                     if(null != sqlBody && !sqlBody.isEmpty()){
                        writeFile((packageName + "/body.txt"), sqlBody);
                     }else{
                        failLog.push("属性包备份：" + packageName + "\n" + "出错原因为：包体Sql文为空。");
                     }
                     allLog.push("备份属性包：" + packageName);
                  }
               }
            }
         }
      }
   }

   /**
    * <T>获得包体代码</T>
    * <P>去掉尾空格</P>
    * 
    */
   protected FString bodySource(ISqlConnection connection,
                                String packageName){
      if(RString.isNotEmpty(packageName)){
         connection.setMaxFetch(MAX_FETCH);
         FString sqlPackage = new FString();
         FSqlQuery query = new FSqlQuery(connection, _resource.findString("sql.package.body"));
         query.bindString("package_name", packageName);
         FDataset dataset = query.fetchDataset();
         if(null != dataset){
            for(FRow row : dataset){
               String sql = RString.trimRight(row.get("TEXT"));
               sqlPackage.append(sql);
               if(!(RString.endsWith(sql, "\n")) && !(RString.startsWith(sql, ("END " + packageName)))){
                  sqlPackage.append("\n");
               }
            }
         }
         sqlPackage.trim();
         return sqlPackage;
      }
      return null;
   }

   /**
    * <T>根据数据集模板获得包体代码</T>
    * 
    */
   protected FString bodySourceDataset(ISqlConnection connection,
                                       FXmlNode xmlConfig){
      String packageName = xmlConfig.get(XDataStore.PTY_DATA_LOGIC);
      try{
         // 获取包内用户自定义的内容
         FString packageSource = null;
         if(xmlConfig.isName("DataStore")){
            FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
            FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
            FString define = parser.fetchUserBodyDefine();
            xmlConfig.set("old_define", define.toString());
            packageSource = source.makePackageBodySql(xmlConfig);
         }
         // 属性视图视图
         if(xmlConfig.isName("DataProperty")){
            FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
            FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
            FString define = parser.fetchUserBodyDefine();
            xmlConfig.set("old_define", define.toString());
            packageSource = source.makePackagePropertyBodySql(xmlConfig);
         }
         // 数据缓冲视图
         if(xmlConfig.isName("DataCache")){
            FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
            FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
            FString define = parser.fetchUserBodyDefine();
            xmlConfig.set("old_define", define.toString());
            packageSource = source.makePackageBodySql(xmlConfig);
         }
         // 工作数据视图
         if(xmlConfig.isName("DataWork")){
            FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
            FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
            FString define = parser.fetchUserBodyDefine();
            xmlConfig.set("old_define", define.toString());
            packageSource = source.makePackageBodySql(xmlConfig);
         }
         // 创建枚举包
         if(xmlConfig.isName(XCodeList.NAME)){
            FCodeListSourceBuilder sourceBuilder = new FCodeListSourceBuilder(_templateConsole);
            packageSource = sourceBuilder.makePackageBodySql(xmlConfig);
         }
         if(null != packageSource){
            packageSource.trim();
            if(RString.startsWith(packageSource.toString(), CREATE_PACKAGE)){
               return packageSource.subString(18);
            }
         }
         return packageSource;
      }catch(Exception e){
         System.out.println(e);
      }
      return null;
   }

   /**
    * <T>根据数据集模板获得包体代码</T>
    * 
    */
   protected FString bodySourceHisDataset(ISqlConnection connection,
                                          FXmlNode dataset){
      FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
      FString packageSource = null;
      if(dataset.isName("DataStore")){
         packageSource = source.makeHsPackageBodySql(dataset);
      }
      if(null != packageSource){
         packageSource.trim();
         if(RString.startsWith(packageSource.toString(), CREATE_PACKAGE)){
            return packageSource.subString(18);
         }
      }
      return packageSource;
   }

   /**
    * <T>构建创建包体Sql文并执行sql创建包体</T>
    * 
    */
   protected void createBody(ISqlConnection synConnection,
                             String packageName,
                             FString sqlPackage){
      System.out.println("----------------------------------------");
      System.out.println("CREATE PACKAGE BODY " + packageName);
      System.out.println("========================================");
      System.out.println("");
      System.out.println("");
      System.out.println("");
      if(null == sqlPackage || sqlPackage.isEmpty()){
         System.out.println("Create package body sql is null.");
         System.out.println("创建失败！");
         System.out.println("========================================");
         return;
      }
      // 执行包的sql文创建表
      try{
         if(RString.startsWith(sqlPackage.toString(), CREATE_PACKAGE)){
            sqlPackage.trim();
            synConnection.executeSql(sqlPackage.toString());
         }else{
            FString sql = new FString(CREATE_PACKAGE + sqlPackage.toString());
            sql.trim();
            synConnection.executeSql(sql.toString());
         }
         System.out.println("创建成功！");
      }catch(Exception e){
         System.out.println(e);
         System.out.println("创建失败！");
      }finally{
         System.out.println("========================================");
      }
   }

   /**
    * <T>构建创建包头Sql文并执行sql创建包</T>
    * 
    */
   protected void createHead(ISqlConnection synConnection,
                             String packageName,
                             FString sqlPackage){
      System.out.println("----------------------------------------");
      System.out.println("CREATE PACKAGE HEAD " + packageName);
      System.out.println("========================================");
      System.out.println("");
      System.out.println("");
      System.out.println("");
      if(null == sqlPackage || sqlPackage.isEmpty()){
         System.out.println("Create package head sql is null.");
         System.out.println("创建失败！");
         System.out.println("========================================");
         return;
      }
      // 执行包的sql文创建表
      try{
         if(RString.startsWith(sqlPackage.toString(), CREATE_PACKAGE)){
            sqlPackage.trim();
            synConnection.executeSql(sqlPackage.toString());
         }else{
            FString sql = new FString(CREATE_PACKAGE + sqlPackage.toString());
            sql.trim();
            synConnection.executeSql(sql.toString());
         }
         System.out.println("创建成功！");
      }catch(Exception e){
         System.out.println(e);
         System.out.println("创建失败！");
      }finally{
         System.out.println("========================================");
      }
   }

   @Override
   public void createPackage(FXmlNode config,
                             FStrings allLog,
                             FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      // 数据库连接
      ISqlConnection synConnection = null;
      String synDatabaseName = config.get(SYN_DATABASE_NAME);
      // 获得zip包对象
      ZipFile zipFile = null;
      String filePath = config.get(FILE_PATH);
      try{
         zipFile = getZipObject(filePath);
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synDatabaseName).activeConnection();
      }catch(Exception e){
         System.out.println("----------------------------------------");
         System.out.println("CREATE PACKAGE");
         System.out.println("========================================");
         System.out.println("[" + synDatabaseName + "]数据库连接失败！");
         System.out.println("========================================");
         System.out.println(e);
         System.out.println("========================================");
         return;
      }
      if(null != zipFile && null != synConnection){
         for(String item : items){
            if(RString.isNotEmpty(item)){
               //获得包头sql
               FString sqlHead = readFile(zipFile, item + "/head.txt");
               // 创建包头
               if(null != sqlHead && !sqlHead.isEmpty()){
                  createHead(synConnection, item, sqlHead);
               }
               //获得包体sql
               FString sqlBody = readFile(zipFile, item + "/body.txt");
               if(null != sqlBody && !sqlBody.isEmpty()){
                  createBody(synConnection, item, sqlBody);
               }
            }
         }
      }
      if(null != synConnection){
         _databaseConsole.free(synConnection);
      }
      // 关闭zip文件
      if(null != zipFile){
         try{
            zipFile.close();
         }catch(Exception e){
            System.out.println("========================================");
            System.out.println(e);
            System.out.println("关闭zip文件对象失败");
            System.out.println("========================================");
         }
      }
   }

   @Override
   public void createPackageDataset(FXmlNode config,
                                    FStrings allLog,
                                    FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      // 数据库连接
      ISqlConnection synConnection = null;
      String synDatabaseName = config.get(SYN_DATABASE_NAME);
      // 获得zip包对象
      ZipFile zipFile = null;
      String filePath = config.get(FILE_PATH);
      try{
         zipFile = getZipObject(filePath);
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synDatabaseName).activeConnection();
      }catch(Exception e){
         failLog.push("通过数据集创建数据包：失败！" + "\n" + "出错原因：" + e.toString());
         System.out.println("----------------------------------------");
         System.out.println("CREATE PACKAGE");
         System.out.println("========================================");
         System.out.println("[" + synDatabaseName + "]数据库连接失败！");
         System.out.println("========================================");
         System.out.println(e);
         System.out.println("========================================");
         return;
      }
      if(null != filePath && null != synConnection){
         for(String item : items){
            String datasetName = item;
            if(RString.isNotEmpty(datasetName)){
               if(TYPE_ALL.equalsIgnoreCase(datasetName)){
                  for(IXmlObject xdataset : _datasetConsole.list()){
                     // 判断不是属性视图的数据集继续操作
                     if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name())){
                        continue;
                     }
                     // 有效的数据集进行同步操作
                     if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                        continue;
                     }
                     FXmlNode datasetNode = _datasetConsole.buildConfig(xdataset);
                     String packageName = datasetNode.get(XDataStore.PTY_DATA_LOGIC);
                     if(RString.isNotEmpty(packageName)){
                        //获得包头sql
                        FString sqlHead = readFile(zipFile, packageName + "/head.txt");
                        // 创建包头
                        if(null != sqlHead && !sqlHead.isEmpty()){
                           createHead(synConnection, packageName, sqlHead);
                        }else{
                           failLog.push("通过数据集创建数据包：！" + packageName + "\n" + "出错原因：包头Sql文为空");
                        }
                        //获得包体sql
                        FString sqlBody = readFile(zipFile, packageName + "/body.txt");
                        // 创建包头
                        if(null != sqlBody && !sqlBody.isEmpty()){
                           createBody(synConnection, packageName, sqlBody);
                        }else{
                           failLog.push("通过数据集创建数据包：！" + packageName + "\n" + "出错原因：包体Sql文为空");
                        }
                        if("DataStore".equalsIgnoreCase(xdataset.name())){
                           String packageNameHis = packageName.substring(0, packageName.length() - 2) + "HI";
                           //获得包头sql
                           FString sqlHisHead = readFile(zipFile, packageNameHis + "/head.txt");
                           // 创建包头
                           if(null != sqlHisHead && !sqlHisHead.isEmpty()){
                              createHead(synConnection, packageNameHis, sqlHisHead);
                           }else{
                              failLog.push("通过数据集创建数据包：！" + packageNameHis + "\n" + "出错原因：包头Sql文为空");
                           }
                           //获得包体sql
                           FString sqlHisBody = readFile(zipFile, packageNameHis + "/body.txt");
                           // 创建包头
                           if(null != sqlHisBody && !sqlHisBody.isEmpty()){
                              createBody(synConnection, packageNameHis, sqlHisBody);
                           }else{
                              failLog.push("通过数据集创建数据包：！" + packageNameHis + "\n" + "出错原因：包体Sql文为空");
                           }
                        }
                     }
                  }
               }else{
                  IXmlObject xdataset = _datasetConsole.find(datasetName);
                  // 判断不是属性视图的数据集继续操作
                  if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name())){
                     continue;
                  }
                  // 有效的数据集进行同步操作
                  if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                     continue;
                  }
                  FXmlNode datasetNode = _datasetConsole.buildConfig(xdataset);
                  String packageName = datasetNode.get(XDataStore.PTY_DATA_LOGIC);
                  if(RString.isNotEmpty(packageName)){
                     //获得包头sql
                     FString sqlHead = readFile(zipFile, packageName + "/head.txt");
                     // 创建包头
                     if(null != sqlHead && !sqlHead.isEmpty()){
                        createHead(synConnection, packageName, sqlHead);
                     }else{
                        failLog.push("通过数据集创建数据包：！" + packageName + "\n" + "出错原因：包头Sql文为空");
                     }
                     //获得包体sql
                     FString sqlBody = readFile(zipFile, packageName + "/body.txt");
                     // 创建包头
                     if(null != sqlBody && !sqlBody.isEmpty()){
                        createBody(synConnection, packageName, sqlBody);
                     }else{
                        failLog.push("通过数据集创建数据包：！" + packageName + "\n" + "出错原因：包体Sql文为空");
                     }
                     if("DataStore".equalsIgnoreCase(xdataset.name())){
                        String packageNameHis = packageName.substring(0, packageName.length() - 2) + "HI";
                        //获得包头sql
                        FString sqlHisHead = readFile(zipFile, packageNameHis + "/head.txt");
                        // 创建包头
                        if(null != sqlHisHead && !sqlHisHead.isEmpty()){
                           createHead(synConnection, packageNameHis, sqlHisHead);
                        }else{
                           failLog.push("通过数据集创建数据包：！" + packageNameHis + "\n" + "出错原因：包头Sql文为空");
                        }
                        //获得包体sql
                        FString sqlHisBody = readFile(zipFile, packageNameHis + "/body.txt");
                        // 创建包头
                        if(null != sqlHisBody && !sqlHisBody.isEmpty()){
                           createBody(synConnection, packageNameHis, sqlHisBody);
                        }else{
                           failLog.push("通过数据集创建数据包：！" + packageNameHis + "\n" + "出错原因：包体Sql文为空");
                        }
                     }
                  }
               }
            }
         }
      }
      // 如果数据库连接不是空的话则释放被同步的数据库连接
      if(null != synConnection){
         _databaseConsole.free(synConnection);
      }
      // 关闭zip文件
      if(null != zipFile){
         try{
            zipFile.close();
         }catch(Exception e){
            System.out.println("========================================");
            System.out.println(e);
            System.out.println("关闭zip文件对象失败");
            System.out.println("========================================");
         }
      }
   }

   @Override
   public void dropPackage(FXmlNode config,
                           FStrings allLog,
                           FStrings failLog){
      // 解析xml获得要同步的数据集名称
      // 数据库连接
      ISqlConnection synConnection = null;
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      for(String item : items){
         if(RString.isNotEmpty(item)){
            // 删除包
            dropPackage(synConnection, item);
         }
      }
   }

   protected void dropPackage(ISqlConnection connection,
                              String packageName){
      if(RString.isNotEmpty(packageName)){
         System.out.println("----------------------------------------");
         System.out.println("DROP PACKAGE HEAD " + packageName);
         System.out.println("========================================");
         System.out.println("");
         System.out.println("");
         System.out.println("");
         try{
            FString sql = new FString("DROP PACKAGE " + packageName);
            sql.trim();
            connection.executeSql(sql.toString());
            System.out.println("删除成功！");
         }catch(Exception e){
            System.out.println(e);
            System.out.println("删除失败！");
         }finally{
            System.out.println("========================================");
         }
      }
   }

   @Override
   public void dropPackageDataset(FXmlNode config,
                                  FStrings allLog,
                                  FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      // 数据库连接
      ISqlConnection synConnection = null;
      for(String item : items){
         String dataset = item;
         if(RString.isNotEmpty(dataset)){
            if(TYPE_ALL.equalsIgnoreCase(dataset)){
               for(IXmlObject xdataset : _datasetConsole.list()){
                  if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                     continue;
                  }
                  String packageName = xdataset.innerGet(XDataStore.PTY_DATA_LOGIC);
                  // 删除包
                  dropPackage(synConnection, packageName);
                  if("DataStore".equalsIgnoreCase(xdataset.name())){
                     // 如果是数据存储类型数据集则删除历史包
                     String packageNameHis = packageName.substring(0, packageName.length() - 2) + "HI";
                     dropPackage(synConnection, packageNameHis);
                  }
               }
            }else{
               IXmlObject xdataset = _datasetConsole.find(dataset);
               if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                  String packageName = xdataset.innerGet(XDataStore.PTY_DATA_LOGIC);
                  // 删除包
                  dropPackage(synConnection, packageName);
                  if("DataStore".equalsIgnoreCase(xdataset.name())){
                     // 如果是数据存储类型数据集则删除历史包
                     String packageNameHis = packageName.substring(0, packageName.length() - 2) + "HI";
                     dropPackage(synConnection, packageNameHis);
                  }
               }
            }
         }
      }
   }

   /**
    * <T>比较value1和value2是否相同</T>
    * 
    */
   protected boolean equalSource(FString value1,
                                 FString value2){
      if(null == value1){
         if(null != value2){
            return false;
         }
      }
      if(null == value2){
         if(null != value1){
            return false;
         }
      }
      if(null == value1 && null == value2){
         return true;
      }
      // 同时为空返回真
      if(value1.isEmpty() && value2.isEmpty()){
         return true;
      }
      // value1和value2长度不相同时返回假
      if(value1.length() != value2.length()){
         return false;
      }
      // 查看是否相同
      return value1.equals(value2);
   }

   /**
    * <T>获得指定目录下的zip文件目录</T>
    * <P>如果没有指定zip文件，则查找最后版本的zip文件目录</p>
    * 
    */
   protected ZipFile getZipObject(String zipPath){
      if(RString.isEmpty(zipPath)){
         zipPath = "D:/eUIS-Store";
      }
      try{
         File file = new File(zipPath);
         // 判断指定的目录是否为zip文件包
         if(file.isFile()){
            System.out.println("**************************");
            System.out.println("查找版本(" + zipPath + ")成功！");
            System.out.println("**************************");
            return new ZipFile(zipPath);
         }
         // 查找最后一个导入文件
         String newPath = "";
         for(File zipFile : file.listFiles()){
            String path = zipFile.getAbsolutePath();
            if(path.endsWith(".zip")){
               if(path.compareTo(zipPath) > 0){
                  newPath = path;
               }
            }
         }
         System.out.println("**************************");
         System.out.println("查找版本(" + newPath + ")成功！");
         System.out.println("**************************");
         return new ZipFile(newPath);
      }catch(Exception e){
         System.out.println("========================================");
         System.out.println("同步数据包时出错！原因如下：");
         System.out.println(e);
         System.out.println("查找zip对象失败！");
         System.out.println("========================================");
      }
      return null;
   }

   /**
    * <T>获得包头代码</T>
    * <P>去掉尾空格</P>
    * 
    */
   protected FString headSource(ISqlConnection connection,
                                String packageName){
      if(RString.isNotEmpty(packageName)){
         connection.setMaxFetch(MAX_FETCH);
         FString sqlPackage = new FString();
         FSqlQuery query = new FSqlQuery(connection, _resource.findString("sql.package"));
         query.bindString("package_name", packageName);
         FDataset dataset = query.fetchDataset();
         if(null != dataset){
            for(FRow row : dataset){
               String sql = RString.trimRight(row.get("TEXT"));
               sqlPackage.append(sql);
               if(!(RString.endsWith(sql, "\n")) && !(RString.startsWith(sql, ("END " + packageName)))){
                  sqlPackage.append("\n");
               }
            }
         }
         sqlPackage.trim();
         return sqlPackage;
      }
      return null;
   }

   /**
    * <T>根据数据集模板获得包体代码</T>
    * 
    */
   protected FString headSourceDataset(ISqlConnection connection,
                                       FXmlNode xmlConfig){
      String packageName = xmlConfig.get(XDataStore.PTY_DATA_LOGIC);
      try{
         // 获取包内用户自定义的内容
         FString packageSource = null;
         if(xmlConfig.isName("DataStore")){
            FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
            FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
            FString define = parser.fetchUserHeadDefine();
            xmlConfig.set("old_define", define.toString());
            packageSource = source.makePackageHeadSql(xmlConfig);
         }
         // 属性视图视图
         if(xmlConfig.isName("DataProperty")){
            FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
            FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
            FString define = parser.fetchUserHeadDefine();
            xmlConfig.set("old_define", define.toString());
            packageSource = source.makePackagePropertyHeadSql(xmlConfig);
         }
         // 数据缓冲视图
         if(xmlConfig.isName("DataCache")){
            FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
            FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
            FString define = parser.fetchUserHeadDefine();
            xmlConfig.set("old_define", define.toString());
            packageSource = source.makePackageHeadSql(xmlConfig);
         }
         // 工作数据视图
         if(xmlConfig.isName("DataWork")){
            FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
            FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
            FString define = parser.fetchUserHeadDefine();
            xmlConfig.set("old_define", define.toString());
            packageSource = source.makePackageHeadSql(xmlConfig);
         }
         // 创建枚举包
         if(xmlConfig.isName(XCodeList.NAME)){
            FCodeListSourceBuilder sourceBuilder = new FCodeListSourceBuilder(_templateConsole);
            packageSource = sourceBuilder.makePackageHeadSql(xmlConfig);
         }
         if(null != packageSource){
            packageSource.trim();
            if(RString.startsWith(packageSource.toString(), CREATE_PACKAGE)){
               return packageSource.subString(18);
            }
         }
         return packageSource;
      }catch(Exception e){
         System.out.println(e);
      }
      return null;
   }

   /**
    * <T>根据数据集模板获得历史包体代码</T>
    * 
    */
   protected FString headSourceHisDataset(ISqlConnection connection,
                                          FXmlNode dataset){
      FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
      FString packageSource = null;
      if(dataset.isName("DataStore")){
         packageSource = source.makeHsPackageHeadSql(dataset);
      }
      if(null != packageSource){
         packageSource.trim();
         if(RString.startsWith(packageSource.toString(), CREATE_PACKAGE)){
            return packageSource.subString(18);
         }
      }
      return packageSource;
   }

   /**
    * <T>从指定文件中读取文件</T>
    * 
    */
   protected FString readFile(ZipFile zipfile,
                              String filePath){
      if(null != zipfile){
         try{
            filePath = "database/" + filePath;
            filePath = RString.replace(filePath, "\\", "/");
            ZipEntry entry = zipfile.getEntry(filePath);
            if(null != entry){
               InputStreamReader stream = new InputStreamReader(zipfile.getInputStream(entry), REncoding.UTF8.toString());
               BufferedReader read = new BufferedReader(stream);
               String line = read.readLine();
               FString source = new FString();
               while(null != line){
                  source.appendLine(line);
                  line = read.readLine();
               }
               source.trim();
               if(null != stream){
                  stream.close();
               }
               if(null != read){
                  read.close();
               }
               return source;
            }
         }catch(Exception e){
            System.out.println("========================================");
            System.out.println(e);
            System.out.println("读取filePath文件失败！");
            System.out.println("========================================");
         }
      }
      return null;
   }

   /**
    * <T>设置输出根目录</T>
    * 
    */
   protected void setOutPutPath(String path){
      if(RString.isNotEmpty(path)){
         _outputFile = path;
      }
   }

   /**
    * <T>同步包体</T>
    * 
    * @param connection 被同步的数据库连接
    * @param synConnection 要同步的数据库连接
    */
   protected void synBody(ISqlConnection synConnection,
                          String packageName,
                          FString sql){
      // 获得被同步的包头sql
      FString synSql = bodySource(synConnection, packageName);
      // 如果被同步的包头为空则，删除要同步的包头
      if(null == sql || sql.isEmpty()){
         if(null != synSql && !synSql.isEmpty()){
            // 删除要同步的包头
            dropPackage(synConnection, packageName);
         }
      }
      // 如果要同步的包体和被同步的包体不一样则更新被同步的包头
      if(!equalSource(sql, synSql)){
         createBody(synConnection, packageName, sql);
      }
   }

   /**
    * <T>同步包头</T>
    * 
    * @param connection 被同步的数据库连接
    * @param synConnection 要同步的数据库连接
    */
   protected void synHead(ISqlConnection synConnection,
                          String packageName,
                          FString sql){
      // 获得被同步的包头sql
      FString synSql = headSource(synConnection, packageName);
      // 如果被同步的包头为空则，删除要同步的包头
      if(null == sql || sql.isEmpty()){
         if(null != synSql && !synSql.isEmpty()){
            // 删除要同步的包头
            dropPackage(synConnection, packageName);
         }
      }
      // 如果要同步的包体和被同步的包体不一样则更新被同步的包头
      if(!equalSource(sql, synSql)){
         createHead(synConnection, packageName, sql);
      }
   }

   @Override
   public void updatePackage(FXmlNode config,
                             FStrings allLog,
                             FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      // 数据库连接
      ISqlConnection synConnection = null;
      String synDatabaseName = config.get(SYN_DATABASE_NAME);
      // 获得zip包对象
      ZipFile zipFile = null;
      String filePath = config.get(FILE_PATH);
      try{
         zipFile = getZipObject(filePath);
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synDatabaseName).activeConnection();
      }catch(Exception e){
         failLog.push("同步数据包：出错" + "\n" + "出错原因为：" + e.toString());
         return;
      }
      if(null != synConnection){
         for(String item : items){
            if(RString.isNotEmpty(item)){
               //获得包头sql
               FString sqlHead = readFile(zipFile, item + "/head.txt");
               // 创建包头
               if(null != sqlHead && !sqlHead.isEmpty()){
                  synHead(synConnection, item, sqlHead);
               }else{
                  failLog.push("同步数据包：" + item + "\n" + "出错原因为：包头Sql文为空。");
               }
               //获得包体sql
               FString sqlBody = readFile(zipFile, item + "/body.txt");
               if(null != sqlBody && !sqlBody.isEmpty()){
                  synBody(synConnection, item, sqlBody);
               }else{
                  failLog.push("同步数据包：" + item + "\n" + "出错原因为：包体Sql文为空。");
               }
               allLog.push("同步数据包：" + item);
            }
         }
      }
      if(null != synConnection){
         _databaseConsole.free(synConnection);
      }
      // 关闭zip文件
      if(null != zipFile){
         try{
            zipFile.close();
         }catch(Exception e){
            failLog.push("同步数据包时关闭版本流失败" + "\n" + "出错原因为：" + e.toString());
         }
      }
   }

   @Override
   public void updatePackageDataset(FXmlNode config,
                                    FStrings allLog,
                                    FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      // 数据库连接
      ISqlConnection synConnection = null;
      String synDatabaseName = config.get(SYN_DATABASE_NAME);
      // 获得zip包对象
      ZipFile zipFile = null;
      String filePath = config.get(FILE_PATH);
      try{
         zipFile = getZipObject(filePath);
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synDatabaseName).activeConnection();
      }catch(Exception e){
         failLog.push("通过数据集同步数据包：失败" + "\n" + "出错原因为：" + e.toString());
         return;
      }
      if(null != zipFile && null != synConnection){
         for(String item : items){
            String datasetName = item;
            if(RString.isNotEmpty(datasetName)){
               if(TYPE_ALL.equalsIgnoreCase(datasetName)){
                  for(IXmlObject xdataset : _datasetConsole.list()){
                     // 判断不是属性视图的数据集继续操作
                     if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name())){
                        continue;
                     }
                     // 有效的数据集进行同步操作
                     if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                        continue;
                     }
                     FXmlNode datasetNode = _datasetConsole.buildConfig(xdataset);
                     String packageName = datasetNode.get(XDataStore.PTY_DATA_LOGIC);
                     if(RString.isNotEmpty(packageName)){
                        //获得包头sql
                        FString sqlHead = readFile(zipFile, packageName + "/head.txt");
                        // 创建包头
                        if(null != sqlHead && !sqlHead.isEmpty()){
                           synHead(synConnection, packageName, sqlHead);
                        }else{
                           failLog.push("通过数据集同步数据包：" + packageName + "\n" + "出错原因为：包头Sql文为空。");
                        }
                        //获得包体sql
                        FString sqlBody = readFile(zipFile, packageName + "/body.txt");
                        // 创建包头
                        if(null != sqlBody && !sqlBody.isEmpty()){
                           synBody(synConnection, packageName, sqlBody);
                        }else{
                           failLog.push("通过数据集同步数据包：" + packageName + "出错原因为：包体Sql文为空。");
                        }
                        allLog.push("通过数据集同步数据包：" + packageName);
                        if("DataStore".equalsIgnoreCase(xdataset.name())){
                           String packageNameHis = packageName.substring(0, packageName.length() - 2) + "HI";
                           //获得包头sql
                           FString sqlHisHead = readFile(zipFile, packageNameHis + "/head.txt");
                           // 创建包头
                           if(null != sqlHisHead && !sqlHisHead.isEmpty()){
                              synHead(synConnection, packageNameHis, sqlHisHead);
                           }else{
                              failLog.push("通过数据集同步数据包：" + packageNameHis + "\n" + "出错原因为：包头Sql文为空。");
                           }
                           //获得包体sql
                           FString sqlHisBody = readFile(zipFile, packageNameHis + "/body.txt");
                           // 创建包头
                           if(null != sqlHisBody && !sqlHisBody.isEmpty()){
                              synBody(synConnection, packageNameHis, sqlHisBody);
                           }else{
                              failLog.push("通过数据集同步数据包：" + packageNameHis + "\n" + "出错原因为：包体Sql文为空。");
                           }
                           allLog.push("通过数据集同步数据包：" + packageNameHis);
                        }
                     }
                  }
               }else{
                  IXmlObject xdataset = _datasetConsole.find(datasetName);
                  // 判断不是属性视图的数据集继续操作
                  if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name())){
                     continue;
                  }
                  // 有效的数据集进行同步操作
                  if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                     continue;
                  }
                  FXmlNode datasetNode = _datasetConsole.buildConfig(xdataset);
                  String packageName = datasetNode.get(XDataStore.PTY_DATA_LOGIC);
                  if(RString.isNotEmpty(packageName)){
                     //获得包头sql
                     FString sqlHead = readFile(zipFile, packageName + "/head.txt");
                     // 创建包头
                     if(null != sqlHead && !sqlHead.isEmpty()){
                        synHead(synConnection, packageName, sqlHead);
                     }else{
                        failLog.push("通过数据集同步数据包：" + packageName + "\n" + "出错原因为：包头Sql文为空。");
                     }
                     //获得包体sql
                     FString sqlBody = readFile(zipFile, packageName + "/body.txt");
                     // 创建包头
                     if(null != sqlBody && !sqlBody.isEmpty()){
                        synBody(synConnection, packageName, sqlBody);
                     }else{
                        failLog.push("通过数据集同步数据包：" + packageName + "\n" + "出错原因为：包体Sql文为空。");
                     }
                     allLog.push("通过数据集同步数据包：" + packageName);
                     if("DataStore".equalsIgnoreCase(xdataset.name())){
                        String packageNameHis = packageName.substring(0, packageName.length() - 2) + "HI";
                        //获得包头sql
                        FString sqlHisHead = readFile(zipFile, packageNameHis + "/head.txt");
                        // 创建包头
                        if(null != sqlHisHead && !sqlHisHead.isEmpty()){
                           synHead(synConnection, packageNameHis, sqlHisHead);
                        }else{
                           failLog.push("通过数据集同步数据包：" + packageNameHis + "\n" + "出错原因为：包头Sql文为空。");
                        }
                        //获得包体sql
                        FString sqlHisBody = readFile(zipFile, packageNameHis + "/body.txt");
                        // 创建包头
                        if(null != sqlHisBody && !sqlHisBody.isEmpty()){
                           synBody(synConnection, packageNameHis, sqlHisBody);
                        }else{
                           failLog.push("通过数据集同步数据包：" + packageNameHis + "\n" + "出错原因为：包体Sql文为空。");
                        }
                        allLog.push("通过数据集同步数据包：" + packageNameHis);
                     }
                  }
               }
            }
         }
      }
      // 如果数据库连接不是空的话则释放被同步的数据库连接
      if(null != synConnection){
         _databaseConsole.free(synConnection);
      }
      // 关闭zip文件
      if(null != zipFile){
         try{
            zipFile.close();
         }catch(Exception e){
            failLog.push("通过数据集同步数据包时关闭版本流失败" + "\n" + "出错原因为：" + e.toString());
         }
      }
   }

   @Override
   public void updatePackageList(FXmlNode config,
                                 FStrings allLog,
                                 FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      // 数据库连接
      ISqlConnection synConnection = null;
      String synDatabaseName = config.get(SYN_DATABASE_NAME);
      // 获得zip包对象
      ZipFile zipFile = null;
      String filePath = config.get(FILE_PATH);
      try{
         zipFile = getZipObject(filePath);
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synDatabaseName).activeConnection();
      }catch(Exception e){
         failLog.push("通过定义列表同步定义列表包：失败" + "\n" + "出错原因为：" + e.toString());
         return;
      }
      if(null != synConnection){
         for(String item : items){
            String listName = item;
            if(RString.isNotEmpty(listName)){
               if(TYPE_ALL.equalsIgnoreCase(listName)){
                  for(IXmlObject xcodeList : _codeListConsole.list()){
                     if(!RBoolean.parse(xcodeList.innerGet(XCodeList.PTY_IS_VALID))){
                        continue;
                     }
                     FXmlNode codeListNode = _codeListConsole.convertInstanceToNode(xcodeList);
                     String packageName = codeListNode.get(XCodeList.PTY_DATA_NAME);
                     if(RString.isNotEmpty(packageName)){
                        packageName = packageName + "_EI";
                        //获得包头sql
                        FString sqlHead = readFile(zipFile, packageName + "/head.txt");
                        // 创建包头
                        if(null != sqlHead && !sqlHead.isEmpty()){
                           synHead(synConnection, packageName, sqlHead);
                        }else{
                           failLog.push("通过定义列表同步定义列表包：" + packageName + "\n" + "出错原因为：包头Sql文为空。");
                        }
                        //获得包体sql
                        FString sqlBody = readFile(zipFile, packageName + "/body.txt");
                        // 创建包头
                        if(null != sqlBody && !sqlBody.isEmpty()){
                           synBody(synConnection, packageName, sqlBody);
                        }else{
                           failLog.push("通过定义列表同步定义列表包：" + packageName + "\n" + "出错原因为：包体Sql文为空。");
                        }
                        allLog.push("通过定义列表同步定义列表包：" + packageName);
                     }
                  }
               }
            }else{
               IXmlObject xcodeList = _codeListConsole.find(listName);
               FXmlNode codeListNode = _codeListConsole.convertInstanceToNode(xcodeList);
               String packageName = codeListNode.get(XCodeList.PTY_DATA_NAME);
               if(RString.isNotEmpty(packageName)){
                  packageName = packageName + "_EI";
                  //获得包头sql
                  FString sqlHead = readFile(zipFile, packageName + "/head.txt");
                  // 创建包头
                  if(null != sqlHead && !sqlHead.isEmpty()){
                     synHead(synConnection, packageName, sqlHead);
                  }else{
                     failLog.push("通过定义列表同步定义列表包：" + packageName + "\n" + "出错原因为：包头Sql文为空。");
                  }
                  //获得包体sql
                  FString sqlBody = readFile(zipFile, packageName + "/body.txt");
                  // 创建包头
                  if(null != sqlBody && !sqlBody.isEmpty()){
                     synBody(synConnection, packageName, sqlBody);
                  }else{
                     failLog.push("通过定义列表同步定义列表包：" + packageName + "\n" + "出错原因为：包体Sql文为空。");
                  }
                  allLog.push("通过定义列表同步定义列表包：" + packageName);
               }
            }
         }
      }
      // 如果数据库连接不是空的话则释放被同步的数据库连接
      if(null != synConnection){
         _databaseConsole.free(synConnection);
      }
      // 关闭zip文件
      if(null != zipFile){
         try{
            zipFile.close();
         }catch(Exception e){
            failLog.push("通过定义列表同步定义列表包：关闭版本流报错" + "\n" + "出错原因为：" + e.toString());
         }
      }
   }

   @Override
   public void updatePackagePropertyDataset(FXmlNode config,
                                            FStrings allLog,
                                            FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      // 数据库连接
      ISqlConnection synConnection = null;
      String synDatabaseName = config.get(SYN_DATABASE_NAME);
      // 获得zip包对象
      ZipFile zipFile = null;
      String filePath = config.get(FILE_PATH);
      try{
         zipFile = getZipObject(filePath);
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synDatabaseName).activeConnection();
      }catch(Exception e){
         failLog.push("通过数据集同步属性包：失败" + "\n" + "出错原因为：" + e.toString());
         return;
      }
      if(null != synConnection){
         for(String item : items){
            String datasetName = item;
            if(RString.isNotEmpty(datasetName)){
               if(TYPE_ALL.equalsIgnoreCase(datasetName)){
                  for(IXmlObject xdataset : _datasetConsole.list()){
                     // 判断不是属性视图的数据集继续操作
                     if(!XDataProperty.NAME.equalsIgnoreCase(xdataset.name())){
                        continue;
                     }
                     // 有效的数据集进行同步操作
                     if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                        continue;
                     }
                     FXmlNode datasetNode = _datasetConsole.buildConfig(xdataset);
                     String packageName = datasetNode.get(XDataStore.PTY_DATA_LOGIC);
                     if(RString.isNotEmpty(packageName)){
                        //获得包头sql
                        FString sqlHead = readFile(zipFile, packageName + "/head.txt");
                        // 创建包头
                        if(null != sqlHead && !sqlHead.isEmpty()){
                           synHead(synConnection, packageName, sqlHead);
                        }else{
                           failLog.push("通过数据集同步属性包：" + packageName + "\n" + "出错原因为：包头为空。");
                        }
                        //获得包体sql
                        FString sqlBody = readFile(zipFile, packageName + "/body.txt");
                        // 创建包头
                        if(null != sqlBody && !sqlBody.isEmpty()){
                           synBody(synConnection, packageName, sqlBody);
                        }else{
                           failLog.push("通过数据集同步属性包：" + packageName + "\n" + "出错原因为：包体为空。");
                        }
                        allLog.push("通过数据集同步属性包：" + packageName);
                     }
                  }
               }else{
                  IXmlObject xdataset = _datasetConsole.find(datasetName);
                  // 判断不是属性视图的数据集继续操作
                  if(!XDataProperty.NAME.equalsIgnoreCase(xdataset.name())){
                     continue;
                  }
                  // 有效的数据集进行同步操作
                  if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                     continue;
                  }
                  FXmlNode datasetNode = _datasetConsole.buildConfig(xdataset);
                  String packageName = datasetNode.get(XDataStore.PTY_DATA_LOGIC);
                  if(RString.isNotEmpty(packageName)){
                     //获得包头sql
                     FString sqlHead = readFile(zipFile, packageName + "/head.txt");
                     // 创建包头
                     if(null != sqlHead && !sqlHead.isEmpty()){
                        synHead(synConnection, packageName, sqlHead);
                     }else{
                        failLog.push("通过数据集同步属性包：" + packageName + "\n" + "出错原因为：包头为空。");
                     }
                     //获得包体sql
                     FString sqlBody = readFile(zipFile, packageName + "/body.txt");
                     // 创建包头
                     if(null != sqlBody && !sqlBody.isEmpty()){
                        synBody(synConnection, packageName, sqlBody);
                     }else{
                        failLog.push("通过数据集同步属性包：" + packageName + "\n" + "出错原因为：包体为空。");
                     }
                     allLog.push("通过数据集同步属性包：" + packageName);
                  }
               }
            }
         }
      }
      // 如果数据库连接不是空的话则释放被同步的数据库连接
      if(null != synConnection){
         _databaseConsole.free(synConnection);
      }
      // 关闭zip文件
      if(null != zipFile){
         try{
            zipFile.close();
         }catch(Exception e){
            failLog.push("通过数据集同步属性包：关闭版本流报错" + "\n" + "出错原因为：" + e.toString());
         }
      }
   }

   /**
    * <T>写文件</T>
    * 
    */
   protected void writeFile(String filePath,
                            FString source){
      filePath = _outputFile + "/database/" + filePath;
      String fullName = RFile.makeFilename(filePath);
      RFile.saveToFile(fullName, source, REncoding.UTF8);
   }
}

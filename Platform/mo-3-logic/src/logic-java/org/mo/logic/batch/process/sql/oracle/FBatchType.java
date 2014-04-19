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
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.logic.batch.process.FBatchSqlCommand;

public class FBatchType
      extends FBatchSqlCommand
      implements
         IBatchType
{
   private static ILogger _logger = RLogger.find(FBatchType.class);

   // 获得xml中的sql文
   private static IResource _resource = RResource.find(FBatchType.class);

   private final static int MAX_FETCH = 60000;

   private FXmlNode _config;

   // 输出路径
   private String _outputFile = "D:/euis-dataset";

   // 数据库名称
   private final static String SYN_DATABASE_NAME = "syn_name";

   // 数据库名称
   private final static String DATABASE_NAME = "data_name";

   // 文件路径
   private final static String FILE_PATH = "path";

   @Override
   public void backupType(FXmlNode config,
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
         failLog.push("备份Type包：失败" + "\n" + "出错原因为：" + e.toString());
         // 如果数据库连接不是空的话则释放被同步的数据库连接
         if(null != connection){
            _databaseConsole.free(connection);
         }
         return;
      }
      for(String item : items){
         if(RString.isNotEmpty(item)){
            //获得类包头sql
            FString sqlHead = headSource(connection, item);
            // 写入包头文件
            try{
               if(!sqlHead.isEmpty()){
                  writeFile((item + "/head.txt"), sqlHead);
               }else{
                  failLog.push("备份Type包：" + item + "\n" + "出错原因为：包头Sql为空。");
               }
               //获得类包体sql
               FString sqlBody = bodySource(connection, item);
               // 写入包头文件
               if(!sqlBody.isEmpty()){
                  writeFile((item + "/body.txt"), sqlBody);
               }else{
               }
            }catch(Exception e){
               failLog.push("备份Type包：" + item + "\n" + "出错原因为：" + e.toString());
            }
            allLog.push("备份Type包：" + item);
         }
      }
      if(null != connection){
         _databaseConsole.free(connection);
      }
   }

   /**
    * <T>获得包体代码</T>
    * <P>去掉尾空格</P>
    * 
    */
   public FString bodySource(ISqlConnection connection,
                             String typeName){
      if(RString.isNotEmpty(typeName)){
         connection.setMaxFetch(MAX_FETCH);
         FString sqlType = new FString();
         FSqlQuery query = new FSqlQuery(connection, _resource.findString("sql.type.body"));
         query.bindString("type_name", typeName);
         FDataset dataset = query.fetchDataset();
         if(null != dataset){
            int last = 0;
            int length = dataset.count();
            for(FRow row : dataset){
               String sql = RString.trimRight(row.get("TEXT"));
               sqlType.append(sql);
               last++;
               if(!(RString.endsWith(sql, "\n")) && (last != length)){
                  sqlType.append("\n");
               }
            }
         }
         return sqlType;
      }
      return null;
   }

   /**
    * <T>执行sql创建类包</T>
    * 
    */
   public void createOrReplace(ISqlConnection synConnection,
                               FString sqlType){
      FString sql = new FString("CREATE OR REPLACE " + sqlType.toString());
      // 执行包的sql文创建表
      try{
         sql.trim();
         synConnection.executeSql(sql.toString());
         _logger.debug(this, "createOrReplace", "Create or replace type is  success[sql={0}].", sqlType.toString());
      }catch(Exception e){
         _logger.error(null, "fail", e);
         _logger.debug(this, "createOrReplace", "Create or replace type is fail[sql={0}].", sqlType.toString());
      }finally{
      }
   }

   @Override
   public void createType(FXmlNode config,
                          FStrings allLog,
                          FStrings failLog){
      _config = config;
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      String databaseName = config.get(SYN_DATABASE_NAME);
      // 数据库连接
      ISqlConnection connection = null;
      // 获得zip包对象
      ZipFile zipFile = null;
      String filePath = config.get(FILE_PATH);
      try{
         zipFile = getZipObject(filePath);
         // 获得被同步的数据库连接
         connection = _databaseConsole.alloc(databaseName).activeConnection();
      }catch(Exception e){
         failLog.push("创建Type包：失败" + "\n" + "出错原因为：" + e.toString());
         return;
      }
      if(null != connection){
         for(String item : items){
            if(RString.isNotEmpty(item)){
               //获得包头sql
               FString sqlHead = readFile(zipFile, item + "/head.txt");
               // 创建包头
               if(null == sqlHead || sqlHead.isEmpty()){
                  failLog.push("创建Type包：" + item + "\n" + "出错原因为：包头Sql文为空。");
                  continue;
               }
               // 获得引用列表
               FStrings references = new FStrings();
               getReference(item, references);
               dropReference(connection, references);
               createOrReplace(connection, sqlHead);
               //获得包体sql
               FString sqlBody = readFile(zipFile, item + "/body.txt");
               // 创建包头
               if(null != sqlBody && !sqlBody.isEmpty()){
                  createOrReplace(connection, sqlBody);
               }else{
                  failLog.push("创建Type包：" + item + "\n" + "出错原因为：包体Sql文为空。");
               }
               allLog.push("创建Type包：" + item);
            }
         }
      }
      // 如果数据库连接不是空则释放被同步的数据库连接
      if(null != connection){
         _databaseConsole.free(connection);
      }
      // 关闭zip文件
      if(null != zipFile){
         try{
            zipFile.close();
         }catch(Exception e){
            failLog.push("创建Type包：" + "\n" + "出错原因为：关闭zip文件对象失败");
         }
      }
   }

   /**
    * <T>删除引用包</T>
    * 
    */
   public void dropReference(ISqlConnection connection,
                             FStrings references){
      if(!references.isEmpty()){
         try{
            for(String reference : references){
               dropType(connection, reference);
            }
            _logger.debug(this, "dropReference", "Drop type is success.");
         }catch(Exception e){
            _logger.debug(this, "dropPackage", "Drop type is fail.");
         }finally{
         }
      }
   }

   @Override
   public void dropType(FXmlNode config,
                        FStrings allLog,
                        FStrings failLog){
      _config = config;
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      try{
         for(String item : items){
            if(RString.isNotEmpty(item)){
               // 删除包
               //dropType(synConnection, item);
            }
         }
      }catch(Exception e){
         _logger.debug(this, "dropPackageDataset", "Drop package is fail[packageName={0}].");
      }finally{
      }
   }

   /**
    * <T>删除包</T>
    * 
    */
   public void dropType(ISqlConnection connection,
                        String typeName){
      if(RString.isNotEmpty(typeName)){
         try{
            if(connection.executeExist("SELECT 1 FROM USER_OBJECTS WHERE OBJECT_NAME='" + typeName + "'")){
               FString sql = new FString("DROP TYPE " + typeName);
               sql.trim();
               connection.executeSql(sql.toString());
            }
            _logger.debug(this, "dropPackage", "Drop type is success[typeName={0}].", typeName);
         }catch(Exception e){
            _logger.debug(this, "dropPackage", "Drop type is fail[typeName={0}].", typeName);
         }finally{
         }
      }
   }

   /**
    * <T>比较value1和value2是否相同</T>
    * 
    */
   public boolean equalSource(FString value1,
                              FString value2){
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
    * <T>获得引用列表，递归实现</T>
    * 
    * @param typeName 被引用类包名称
    */
   public void getReference(String typeName,
                            FStrings references){
      if(null != _config && _config.allNodes().containsNode(FBatchSqlCommand.NAME_REFERENCE)){
         for(FXmlNode range : _config.nodes(FBatchSqlCommand.NAME_RANGE)){
            for(FXmlNode item : range.nodes(FBatchSqlCommand.NAME_ITEM)){
               String referenceType = item.text();
               if(!typeName.equalsIgnoreCase(item.text())){
                  for(FXmlNode reference : item.nodes(FBatchSqlCommand.NAME_REFERENCE)){
                     if((typeName.equalsIgnoreCase(reference.text())) && (!references.contains(referenceType))){
                        references.push(referenceType);
                        getReference(typeName, references);
                     }
                  }
               }
            }
         }
      }
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
         return new ZipFile(newPath);
      }catch(Exception e){
         System.out.println("========================================");
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
   public FString headSource(ISqlConnection connection,
                             String typeName){
      if(RString.isNotEmpty(typeName)){
         connection.setMaxFetch(MAX_FETCH);
         FString sqlPackage = new FString();
         FSqlQuery query = new FSqlQuery(connection, _resource.findString("sql.type"));
         query.bindString("type_name", typeName);
         FDataset dataset = query.fetchDataset();
         if(null != dataset){
            int last = 0;
            int length = dataset.count();
            for(FRow row : dataset){
               String sql = RString.trimRight(row.get("TEXT"));
               sqlPackage.append(sql);
               last++;
               if(!(RString.endsWith(sql, "\n")) && (last != length)){
                  sqlPackage.append("\n");
               }
            }
         }
         return sqlPackage;
      }
      return null;
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
   public void synBody(ISqlConnection synConnection,
                       String typeName,
                       FString sql){
      // 获得要同步的包头sql
      FString synSql = bodySource(synConnection, typeName);
      // 如果被同步的包头为空则，删除要同步的包头
      if(sql.isEmpty()){
         if(!synSql.isEmpty()){
            // 删除要同步的包头
            dropType(synConnection, typeName);
         }
      }
      // 如果要同步的包头为空则创建包头
      if(!equalSource(sql, synSql)){
         createOrReplace(synConnection, sql);
      }
   }

   /**
    * <T>同步包头</T>
    * 
    * @param connection 被同步的数据库连接
    * @param synConnection 要同步的数据库连接
    */
   public void synHead(ISqlConnection synConnection,
                       String typeName,
                       FString sql){
      // 获得要同步的包头sql
      FString synSql = headSource(synConnection, typeName);
      // 如果被同步的包头为空则，删除要同步的包头
      if(sql.isEmpty()){
         if(!synSql.isEmpty()){
            // 删除要同步的包头
            dropType(synConnection, typeName);
         }
      }
      // 如果要同步的包头为空则创建包头
      if(!equalSource(sql, synSql)){
         // 获得引用列表
         FStrings references = new FStrings();
         getReference(typeName, references);
         // 删除引用
         dropReference(synConnection, references);
         createOrReplace(synConnection, sql);
      }
   }

   @Override
   public void updateType(FXmlNode config,
                          FStrings allLog,
                          FStrings failLog){
      _config = config;
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      String databaseName = config.get(SYN_DATABASE_NAME);
      // 数据库连接
      ISqlConnection connection = null;
      // 获得zip包对象
      ZipFile zipFile = null;
      String filePath = config.get(FILE_PATH);
      try{
         zipFile = getZipObject(filePath);
         // 获得被同步的数据库连接
         connection = _databaseConsole.alloc(databaseName).activeConnection();
      }catch(Exception e){
         failLog.push("*******************************");
         failLog.push("更新Type包：失败");
         failLog.push("出错原因为：" + e.toString());
         failLog.push("*******************************");
         return;
      }
      if(null != connection){
         for(String item : items){
            if(RString.isNotEmpty(item)){
               //获得包头sql
               FString sqlHead = readFile(zipFile, item + "/head.txt");
               // 创建包头
               if(null == sqlHead || sqlHead.isEmpty()){
                  failLog.push("*******************************");
                  failLog.push("更新Type包：" + item);
                  failLog.push("出错原因为：包头Sql文为空。");
                  failLog.push("*******************************");
                  continue;
               }
               synHead(connection, item, sqlHead);
               //获得包体sql
               FString sqlBody = readFile(zipFile, item + "/body.txt");
               // 创建包头
               if(null != sqlBody && !sqlBody.isEmpty()){
                  synBody(connection, item, sqlBody);
               }else{
                  failLog.push("*******************************");
                  failLog.push("更新Type包：" + item);
                  failLog.push("出错原因为：包体Sql文为空。");
                  failLog.push("*******************************");
               }
               allLog.push("*******************************");
               allLog.push("更新Type包：" + item);
               allLog.push("*******************************");
            }
         }
      }
      if(null != connection){
         _databaseConsole.free(connection);
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

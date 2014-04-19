package org.mo.logic.batch.process.sql.oracle;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.data.dataset.common.FDatasetSourceBuilder;
import org.mo.data.dataset.common.XDataProperty;
import org.mo.data.dataset.common.XDataStore;
import org.mo.data.dataset.common.XDataView;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.template.ITemplateConsole;
import org.mo.logic.batch.process.FBatchSqlCommand;

public class FBatchSequence
      extends FBatchSqlCommand
      implements
         IBatchSequence
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FBatchSequence.class);

   // 获得xml中的sql文
   private static IResource _resource = RResource.find(FBatchSequence.class);

   @SuppressWarnings("unused")
   private final static int MAX_FETCH = 60000;

   // 要同步的数据库名称
   private final static String SYN_DATABASE_NAME = "syn_name";

   // 本地的数据库名称
   @SuppressWarnings("unused")
   private final static String DATABASE_NAME = "from_name";

   @ALink
   private ITemplateConsole _templateConsole;

   // 获得数据集控制台
   @ALink
   protected IDatasetConsole _datasetConsole;

   /**
    * <T>根据数据集XML对象的接口创建数据集Xml节点</T>
    * 
    */
   public FXmlNode buildConfig(IXmlObject xconfig){
      // 通过数据集控制台把Xml对象转换成XML节点对象
      if(null != xconfig){
         FXmlNode config = _datasetConsole.buildConfig(xconfig);
         return config;
      }
      return null;
   }

   /**
    * <T>获得数据存储的历史视图sql文</T>
    * 
    */
   public FString buildHisSource(FXmlNode config){
      if(null != config){
         // 创建生成视图的sql文
         FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
         // 数据存储视图
         if(config.isName("DataStore")){
            FString viewSource = source.makeHsSqlSequence(config);
            return viewSource;
         }
      }
      return null;
   }

   /**
    * <T>根据数据集xml节点获得创建序列sql文</T>
    * 
    */
   public FString buildSource(FXmlNode config){
      if(null != config){
         // 创建生成视图的sql文
         FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
         FString viewSource = null;
         // 数据存储视图
         if(config.isName("DataStore")){
            viewSource = source.makeSqlSequence(config);
         }
         // 数据缓冲视图
         if(config.isName("DataCache")){
            viewSource = source.makeSqlSequence(config);
         }
         // 工作数据视图
         if(config.isName("DataWork")){
            viewSource = source.makeSqlSequence(config);
         }
         return viewSource;
      }
      return null;
   }

   @Override
   public void createSequence(FXmlNode config,
                              FStrings allLog,
                              FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      String synName = config.get(SYN_DATABASE_NAME);
      // 获得被同步的数据库连接
      ISqlConnection synConnection = null;
      try{
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synName).activeConnection();
      }catch(Exception e){
         failLog.push("创建序列(Sequence)：失败" + "\n" + "出错原因为：" + e.toString());
         if(null != synConnection){
            // 关闭被同步的数据库连接
            _databaseConsole.free(synConnection);
         }
         return;
      }
      for(String item : items){
         if(RString.isNotEmpty(item)){
            // 不管是否存在序列，都创建序列
            createSequence(synConnection, item, failLog);
            allLog.push("创建序列(Sequence)：");
         }
      }
      if(null != synConnection){
         // 关闭被同步的数据库连接
         _databaseConsole.free(synConnection);
      }
   }

   /**
    * <T>根据数据集XML节点对象创建序列</T>
    * 
    */
   public void createSequence(ISqlConnection synConnection,
                              String sequenceName,
                              FString sql,
                              FStrings failLog){
      if(null != sql && !sql.isEmpty()){
         try{
            System.out.println("----------------------------------------");
            System.out.println("CREATE SEQUENCE " + sequenceName);
            System.out.println("========================================");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            // 执行sql文创建序列
            synConnection.executeSql(sql.toString());
            System.out.println("创建成功！");
         }catch(Exception e){
            failLog.push("创建序列(Sequence)：失败" + "\n" + "出错原因为：" + e.toString());
            System.out.println(e);
            System.out.println("创建失败！");
         }finally{
            System.out.println("========================================");
         }
      }
   }

   /**
    * <T>执行sql创建序列</T>
    * 
    */
   public void createSequence(ISqlConnection synConnection,
                              String sequenceName,
                              FStrings failLog){
      if(RString.isNotEmpty(sequenceName)){
         FSqlQuery query = null;
         try{
            System.out.println("----------------------------------------");
            System.out.println("CREATE SEQUENCE " + sequenceName);
            System.out.println("========================================");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            // 获得创建序列sql文
            query = new FSqlQuery(synConnection, _resource.findString("create.sequence"));
            query.bindSql("sequence_name", sequenceName);
            // 执行sql文创建序列
            query.execute();
            System.out.println("创建成功！");
         }catch(Exception e){
            failLog.push("创建序列(Sequence)：失败" + "\n" + "出错原因为：" + e.toString());
         }finally{
            System.out.println("========================================");
         }
      }
   }

   @Override
   public void createSequenceDataset(FXmlNode config,
                                     FStrings allLog,
                                     FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      String synName = config.get(SYN_DATABASE_NAME);
      // 获得被同步的数据库连接
      ISqlConnection synConnection = null;
      try{
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synName).activeConnection();
      }catch(Exception e){
         failLog.push("创建序列(Sequence)：失败" + "\n" + "出错原因为：" + e.toString());
         if(null != synConnection){
            // 关闭被同步的数据库连接
            _databaseConsole.free(synConnection);
         }
         return;
      }
      for(String item : items){
         String dataset = item;
         if(RString.isNotEmpty(dataset)){
            if(TYPE_ALL.equalsIgnoreCase(dataset)){
               for(IXmlObject xdataset : _datasetConsole.list()){
                  // 如果不是属性视图和数据视图则创建序列
                  if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name()) || XDataView.NAME.equalsIgnoreCase(xdataset.name())){
                     continue;
                  }
                  FXmlNode datasetNode = buildConfig(xdataset);
                  if(!RBoolean.parse(datasetNode.get(XDataStore.PTY_IS_VALID))){
                     continue;
                  }
                  String dataName = datasetNode.get(XDataStore.PTY_DATA_NAME);
                  String sequenceName = dataName + "_SQ";
                  FString sql = buildSource(datasetNode);
                  createSequence(synConnection, sequenceName, sql, failLog);
                  allLog.push("创建序列(Sequence)：" + sequenceName);
                  // 如果是数据存储数据集则创建历史序列
                  if(XDataStore.NAME.equalsIgnoreCase(xdataset.name())){
                     String sequenceNameHis = dataName + "_HQ";
                     FString sqlHis = buildHisSource(datasetNode);
                     createSequence(synConnection, sequenceNameHis, sqlHis, failLog);
                     allLog.push("创建序列(Sequence)：" + sequenceNameHis);
                  }
               }
            }else{
               IXmlObject xdataset = _datasetConsole.find(dataset);
               // 如果不是属性视图和数据视图则创建序列
               if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name()) || XDataView.NAME.equalsIgnoreCase(xdataset.name())){
                  continue;
               }
               FXmlNode datasetNode = buildConfig(xdataset);
               if(RBoolean.parse(datasetNode.get(XDataStore.PTY_IS_VALID))){
                  String dataName = datasetNode.get(XDataStore.PTY_DATA_NAME);
                  String sequenceName = dataName + "_SQ";
                  FString sql = buildSource(datasetNode);
                  createSequence(synConnection, sequenceName, sql, failLog);
                  allLog.push("创建序列(Sequence)：" + sequenceName);
                  // 如果是数据存储数据集则创建历史序列
                  if(XDataStore.NAME.equalsIgnoreCase(xdataset.name())){
                     String sequenceNameHis = dataName + "_HQ";
                     FString sqlHis = buildHisSource(datasetNode);
                     createSequence(synConnection, sequenceNameHis, sqlHis, failLog);
                     allLog.push("创建序列(Sequence)：" + sequenceNameHis);
                  }
               }
            }
         }
      }
      if(null != synConnection){
         // 关闭被同步的数据库连接
         _databaseConsole.free(synConnection);
      }
   }

   @Override
   public void dropSequence(FXmlNode config,
                            FStrings allLog,
                            FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      String synName = config.get(SYN_DATABASE_NAME);
      // 获得被同步的数据库连接
      ISqlConnection synConnection = null;
      try{
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synName).activeConnection();
      }catch(Exception e){
         failLog.push("删除序列(Sequence)：失败" + "\n" + "出错原因为：" + e.toString());
         if(null != synConnection){
            // 关闭被同步的数据库连接
            _databaseConsole.free(synConnection);
         }
         return;
      }
      for(String item : items){
         if(RString.isNotEmpty(item)){
            // 不管是否存在序列，都删除序列
            dropSequence(synConnection, item, failLog);
            allLog.push("删除序列(Sequence)：" + item);
         }
      }
      if(null != synConnection){
         // 关闭被同步的数据库连接
         _databaseConsole.free(synConnection);
      }
   }

   /**
    * <T>执行sql删除序列</T>
    * 
    */
   public void dropSequence(ISqlConnection synConnection,
                            String sequenceName,
                            FStrings failLog){
      if(RString.isNotEmpty(sequenceName)){
         try{
            System.out.println("----------------------------------------");
            System.out.println("DROP SEQUENCE " + sequenceName);
            System.out.println("========================================");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            String dropSql = "DROP SEQUENCE " + sequenceName;
            // 获得创建序列sql文
            FSqlQuery query = new FSqlQuery(synConnection, dropSql);
            // 执行sql文删除序列
            query.execute();
            System.out.println("删除成功！");
         }catch(Exception e){
            failLog.push("删除序列(Sequence)：" + sequenceName + "\n" + "出错原因为：" + e.toString());
            System.out.println(e);
            System.out.println("删除失败！");
         }finally{
            System.out.println("========================================");
         }
      }
   }

   @Override
   public void dropSequenceDataset(FXmlNode config,
                                   FStrings allLog,
                                   FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      String synName = config.get(SYN_DATABASE_NAME);
      // 获得被同步的数据库连接
      ISqlConnection synConnection = null;
      try{
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synName).activeConnection();
      }catch(Exception e){
         if(null != synConnection){
            // 关闭被同步的数据库连接
            _databaseConsole.free(synConnection);
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
                  // 如果不是属性视图和数据视图则创建序列
                  if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name()) || XDataView.NAME.equalsIgnoreCase(xdataset.name())){
                     continue;
                  }
                  String dataName = xdataset.innerGet(XDataStore.PTY_DATA_NAME);
                  String sequenceName = dataName + "_SQ";
                  dropSequence(synConnection, sequenceName, failLog);
                  allLog.push("删除序列(Sequence)：" + sequenceName);
                  // 如果是数据存储数据集则创建历史序列
                  if(XDataStore.NAME.equalsIgnoreCase(xdataset.name())){
                     String sequenceNameHis = dataName + "_HQ";
                     dropSequence(synConnection, sequenceNameHis, failLog);
                     allLog.push("删除序列(Sequence)：" + sequenceNameHis);
                  }
               }
            }else{
               IXmlObject xdataset = _datasetConsole.find(dataset);
               if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                  // 如果不是属性视图和数据视图则创建序列
                  if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name()) || XDataView.NAME.equalsIgnoreCase(xdataset.name())){
                     continue;
                  }
                  String dataName = xdataset.innerGet(XDataStore.PTY_DATA_NAME);
                  String sequenceName = dataName + "_SQ";
                  dropSequence(synConnection, sequenceName, failLog);
                  allLog.push("删除序列(Sequence)：" + sequenceName);
                  // 如果是数据存储数据集则创建历史序列
                  if(XDataStore.NAME.equalsIgnoreCase(xdataset.name())){
                     String sequenceNameHis = dataName + "_HQ";
                     dropSequence(synConnection, sequenceNameHis, failLog);
                     allLog.push("删除序列(Sequence)：" + sequenceNameHis);
                  }
               }
            }
         }
      }
      if(null != synConnection){
         // 关闭被同步的数据库连接
         _databaseConsole.free(synConnection);
      }
   }

   /**
    * <T>执行sql判断序列是否存在</T>
    * 
    */
   public boolean hasSequence(ISqlConnection synConnection,
                              String sequenceName){
      if(RString.isNotEmpty(sequenceName)){
         try{
            // 获得创建序列sql文
            FSqlQuery query = new FSqlQuery(synConnection, _resource.findString("has.sequence"));
            query.bindString("sequence_name", sequenceName);
            // 执行sql文创建序列
            return query.hasDataset();
         }catch(Exception e){
         }finally{
         }
      }
      return false;
   }

   @Override
   public void updateSequence(FXmlNode config,
                              FStrings allLog,
                              FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      String synName = config.get(SYN_DATABASE_NAME);
      // 获得被同步的数据库连接
      ISqlConnection synConnection = null;
      try{
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synName).activeConnection();
      }catch(Exception e){
         failLog.push("更新序列(Sequence)：失败！" + "\n" + "出错原因：" + e.toString());
         if(null != synConnection){
            // 关闭被同步的数据库连接
            _databaseConsole.free(synConnection);
         }
         return;
      }
      for(String item : items){
         if(RString.isNotEmpty(item)){
            // 判断是否存在序列，如果不存在则创建序列
            if(hasSequence(synConnection, item)){
               createSequence(synConnection, item, failLog);
               allLog.push("更新序列(Sequence)：" + item);
            }
         }
      }
      if(null != synConnection){
         // 关闭被同步的数据库连接
         _databaseConsole.free(synConnection);
      }
   }

   @Override
   public void updateSequenceDataset(FXmlNode config,
                                     FStrings allLog,
                                     FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      String synName = config.get(SYN_DATABASE_NAME);
      // 获得被同步的数据库连接
      ISqlConnection synConnection = null;
      try{
         // 获得同步的数据库连接
         synConnection = _databaseConsole.alloc(synName).activeConnection();
      }catch(Exception e){
         failLog.push("通过数据集更新序列(Sequence)：失败！" + "\n" + "出错原因：" + e.toString());
         if(null != synConnection){
            // 关闭被同步的数据库连接
            _databaseConsole.free(synConnection);
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
                  // 如果不是属性视图和数据视图则创建序列
                  if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name()) || XDataView.NAME.equalsIgnoreCase(xdataset.name())){
                     continue;
                  }
                  String dataName = xdataset.innerGet(XDataStore.PTY_DATA_NAME);
                  FXmlNode datasetNode = null;
                  String sequenceName = dataName + "_SQ";
                  if(!hasSequence(synConnection, sequenceName)){
                     datasetNode = buildConfig(xdataset);
                     FString sql = buildSource(datasetNode);
                     createSequence(synConnection, sequenceName, sql, failLog);
                     allLog.push("更新序列(Sequence)：" + sequenceName);
                  }
                  // 如果是数据存储数据集则创建历史序列
                  if(XDataStore.NAME.equalsIgnoreCase(xdataset.name())){
                     String sequenceNameHis = dataName + "_HQ";
                     if(!hasSequence(synConnection, sequenceNameHis)){
                        if(null == datasetNode){
                           datasetNode = buildConfig(xdataset);
                        }
                        FString sqlHis = buildHisSource(datasetNode);
                        createSequence(synConnection, sequenceNameHis, sqlHis, failLog);
                        allLog.push("更新序列(Sequence)：" + sequenceNameHis);
                     }
                  }
               }
            }else{
               IXmlObject xdataset = _datasetConsole.find(dataset);
               if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                  // 如果不是属性视图和数据视图则创建序列
                  if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name()) || XDataView.NAME.equalsIgnoreCase(xdataset.name())){
                     continue;
                  }
                  String dataName = xdataset.innerGet(XDataStore.PTY_DATA_NAME);
                  FXmlNode datasetNode = null;
                  String sequenceName = dataName + "_SQ";
                  if(!hasSequence(synConnection, sequenceName)){
                     datasetNode = buildConfig(xdataset);
                     FString sql = buildSource(datasetNode);
                     createSequence(synConnection, sequenceName, sql, failLog);
                     allLog.push("更新序列(Sequence)：" + sequenceName);
                  }
                  // 如果是数据存储数据集则创建历史序列
                  if(XDataStore.NAME.equalsIgnoreCase(xdataset.name())){
                     String sequenceNameHis = dataName + "_HQ";
                     if(!hasSequence(synConnection, sequenceNameHis)){
                        if(null == datasetNode){
                           datasetNode = buildConfig(xdataset);
                        }
                        FString sqlHis = buildHisSource(datasetNode);
                        createSequence(synConnection, sequenceNameHis, sqlHis, failLog);
                        allLog.push("更新序列(Sequence)：" + sequenceNameHis);
                     }
                  }
               }
            }
         }
      }
      if(null != synConnection){
         // 关闭被同步的数据库连接
         _databaseConsole.free(synConnection);
      }
   }
}

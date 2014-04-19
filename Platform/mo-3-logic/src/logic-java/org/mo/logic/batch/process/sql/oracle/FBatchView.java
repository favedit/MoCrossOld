package org.mo.logic.batch.process.sql.oracle;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
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
import org.mo.data.dataset.common.XDataTemplate;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.template.ITemplateConsole;
import org.mo.logic.batch.process.FBatchSqlCommand;

public class FBatchView
      extends FBatchSqlCommand
      implements
         IBatchView
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FBatchView.class);

   // 获得xml中的sql文
   private static IResource _resource = RResource.find(FBatchView.class);

   // 要同步的数据库名称
   private final static String SYN_DATABASE_NAME = "syn_name";

   // 本地的数据库名称
   private final static String DATABASE_NAME = "from_name";

   private final static int MAX_FETCH = 60000;

   private final IAttributes attributes = new FAttributes();

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
    * <T>根据数据集名称创建数据集Xml节点</T>
    * 
    */
   public FXmlNode buildConfig(String datasetName){
      // 通过数据集控制台获得xml节点对象
      if(RString.isNotEmpty(datasetName)){
         FXmlNode config = _datasetConsole.buildConfig(datasetName);
         return config;
      }
      return null;
   }

   /**
    * <T>获得数据存储的历史视图sql文</T>
    * 
    */
   public FString buildHisViewSource(FXmlNode config){
      if(null != config){
         // 创建生成视图的sql文
         FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
         // 数据存储视图
         if(config.isName("DataStore")){
            FString viewSource = source.makeHsSqlView(config);
            return viewSource;
         }
      }
      return null;
   }

   /**
    * <T>根据数据集xml节点获得视图sql文</T>
    * 
    */
   public FString buildViewSource(FXmlNode config){
      if(null != config){
         // 创建生成视图的sql文
         FDatasetSourceBuilder source = new FDatasetSourceBuilder(_templateConsole);
         FString viewSource = null;
         // 数据存储视图
         if(config.isName("DataStore")){
            viewSource = source.makeSqlView(config);
         }
         // 数据视图视图
         if(config.isName("DataView")){
            viewSource = source.makeSqlViewView(config);
         }
         // 属性视图视图
         if(config.isName("DataProperty")){
            viewSource = source.makeSqlViewProperty(config);
         }
         // 数据缓冲视图
         if(config.isName("DataCache")){
            viewSource = source.makeSqlView(config);
         }
         // 工作数据视图
         if(config.isName("DataWork")){
            viewSource = source.makeSqlView(config);
         }
         return viewSource;
      }
      return null;
   }

   /**
    * <T>执行sql创建视图</T>
    * 
    */
   public void createOrReplace(ISqlConnection synConnection,
                               String viewName,
                               String sqlView,
                               FStrings failLog){
      FString sql = new FString("CREATE OR REPLACE VIEW " + viewName + " AS " + sqlView);
      try{
         System.out.println("----------------------------------------");
         System.out.println("CREATE VIEW " + viewName);
         System.out.println("========================================");
         System.out.println("");
         System.out.println("");
         System.out.println("");
         // 执行包的sql文创建表
         synConnection.executeSql(sql.toString());
         System.out.println("创建成功！");
      }catch(Exception e){
         System.out.println(e);
         System.out.println("创建失败！");
         attributes.set(viewName, e.toString());
         failLog.push("创建视图(View)：" + viewName + "失败！" + "\n" + "出错原因为：" + e.toString());
      }finally{
         System.out.println("========================================");
      }
   }

   /**
    * <T>直接执行sql创建视图</T>
    * 
    */
   public void createOrReplaceSql(ISqlConnection synConnection,
                                  String viewName,
                                  FString sqlView,
                                  FStrings failLog){
      try{
         System.out.println("----------------------------------------");
         System.out.println("CREATE VIEW " + viewName);
         System.out.println("========================================");
         System.out.println("");
         System.out.println("");
         System.out.println("");
         // 执行包的sql文创建表
         synConnection.executeSql(sqlView.toString());
         System.out.println("创建成功！");
      }catch(Exception e){
         System.out.println(e);
         System.out.println("创建失败！");
         if(null != viewName){
            attributes.set(viewName, e.toString());
         }
         failLog.push("创建视图(View)：" + viewName + "失败！" + "\n" + "出错原因为：" + e.toString());
      }finally{
         System.out.println("========================================");
      }
   }

   @Override
   public void createView(FXmlNode config,
                          FStrings allLog,
                          FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      // 获得同步的数据库名称
      String synName = config.get(SYN_DATABASE_NAME);
      String dataName = config.get(DATABASE_NAME);
      // 获得被同步的数据库连接
      ISqlConnection connection = null;
      // 获得被同步的数据库连接
      ISqlConnection synConnection = null;
      try{
         // 获得本地的数据库连接
         connection = _databaseConsole.alloc(dataName).activeConnection();
         synConnection = _databaseConsole.alloc(synName).activeConnection();
         if(null != synConnection && null != connection){
            for(String item : items){
               if(RString.isNotEmpty(item)){
                  String sqlView = viewSource(connection, item);
                  createOrReplace(synConnection, item, sqlView, failLog);
                  allLog.push("创建视图(View)：" + item);
               }
            }
         }
      }catch(Exception e){
         failLog.push("创建视图(View)：失败" + "\n" + "出错原因为：" + e.toString());
      }
      // 关闭被同步的数据库连接
      if(null != synConnection){
         _databaseConsole.free(synConnection);
      }
   }

   @Override
   public void createViewDataset(FXmlNode config,
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
         failLog.push("通过数据集创建视图(View)：失败" + "\n" + "出错原因为：" + e.toString());
         if(null != synConnection){
            // 关闭被同步的数据库连接
            _databaseConsole.free(synConnection);
         }
         return;
      }
      // 获得被同步的数据库连接
      for(String item : items){
         String dataset = item;
         if(RString.isNotEmpty(dataset)){
            if(TYPE_ALL.equalsIgnoreCase(dataset)){
               for(IXmlObject xdataset : _datasetConsole.list()){
                  FXmlNode datasetNode = buildConfig(xdataset);
                  if(!RBoolean.parse(datasetNode.get(XDataStore.PTY_IS_VALID))){
                     continue;
                  }
                  FString sqlView = buildViewSource(datasetNode);
                  String viewName = datasetNode.get(XDataStore.PTY_DATA_NAME);
                  // 创建视图
                  createOrReplaceSql(synConnection, viewName, sqlView, failLog);
                  allLog.push("通过数据集创建视图(View)：" + viewName);
                  // 如果是数据存储则需要创建历史视图
                  if(datasetNode.isName(XDataStore.NAME)){
                     String viewNameHis = viewName + "_HV";
                     FString sqlViewHis = buildHisViewSource(datasetNode);
                     createOrReplaceSql(synConnection, viewNameHis, sqlViewHis, failLog);
                     allLog.push("通过数据集创建视图(View)：" + viewNameHis);
                  }
               }
            }else{
               FXmlNode datasetNode = buildConfig(dataset);
               if(RBoolean.parse(datasetNode.get(XDataStore.PTY_IS_VALID))){
                  FString sqlView = buildViewSource(datasetNode);
                  String viewName = datasetNode.get(XDataStore.PTY_DATA_NAME);
                  // 创建视图
                  createOrReplaceSql(synConnection, viewName, sqlView, failLog);
                  allLog.push("通过数据集创建视图(View)：" + viewName);
                  // 如果是数据存储则需要创建历史视图
                  if(datasetNode.isName(XDataStore.NAME)){
                     String viewNameHis = viewName + "_HV";
                     FString sqlViewHis = buildHisViewSource(datasetNode);
                     createOrReplaceSql(synConnection, viewNameHis, sqlViewHis, failLog);
                     allLog.push("通过数据集创建视图(View)：" + viewNameHis);
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
   public void dropView(FXmlNode config,
                        FStrings allLog,
                        FStrings failLog){
      FStrings items = fetchRangeItem(config);
      if(!items.isEmpty()){
         String synName = config.get(SYN_DATABASE_NAME);
         // 获得被同步的数据库连接
         ISqlConnection synConnection = null;
         try{
            // 获得同步的数据库连接
            synConnection = _databaseConsole.alloc(synName).activeConnection();
         }catch(Exception e){
            failLog.push("删除视图(View)：失败！" + "\n" + "出错原因：" + e.toString());
            if(null != synConnection){
               // 关闭被同步的数据库连接
               _databaseConsole.free(synConnection);
            }
            return;
         }
         for(String item : items){
            if(RString.isNotEmpty(item)){
               dropView(synConnection, item, failLog);
               allLog.push("删除视图(View)：" + item);
            }
         }
         if(null != synConnection){
            // 关闭被同步的数据库连接
            _databaseConsole.free(synConnection);
         }
      }
   }

   /**
    * <T>执行sql创建视图</T>
    * 
    */
   public void dropView(ISqlConnection synConnection,
                        String viewName,
                        FStrings failLog){
      FString sql = new FString("DROP VIEW " + viewName);
      // 执行视图的sql文删除视图
      try{
         System.out.println("----------------------------------------");
         System.out.println("DROP VIEW " + viewName);
         System.out.println("========================================");
         System.out.println("");
         System.out.println("");
         System.out.println("");
         synConnection.executeSql(sql.toString());
         System.out.println("删除成功！");
      }catch(Exception e){
         System.out.println(e);
         System.out.println("删除失败！");
         failLog.push("删除视图(View)：" + viewName + "失败！" + "\n" + "出错原因：" + e.toString());
      }finally{
         System.out.println("========================================");
      }
   }

   @Override
   public void dropViewDataset(FXmlNode config,
                               FStrings allLog,
                               FStrings failLog){
      FStrings items = fetchRangeItem(config);
      if(!items.isEmpty()){
         String synName = config.get(SYN_DATABASE_NAME);
         // 获得被同步的数据库连接
         ISqlConnection synConnection = null;
         try{
            // 获得同步的数据库连接
            synConnection = _databaseConsole.alloc(synName).activeConnection();
         }catch(Exception e){
            failLog.push("通过数据集删除视图(View)：失败！" + "\n" + "出错原因：" + e.toString());
            if(null != synConnection){
               // 关闭被同步的数据库连接
               _databaseConsole.free(synConnection);
            }
            return;
         }
         for(String item : items){
            if(RString.isNotEmpty(item)){
               String dataset = item;
               if(TYPE_ALL.equalsIgnoreCase(dataset)){
                  for(IXmlObject xdataset : _datasetConsole.list()){
                     if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                        continue;
                     }
                     String viewName = xdataset.innerGet(XDataStore.PTY_DATA_NAME);
                     dropView(synConnection, viewName, failLog);
                     allLog.push("通过删除视图(View)：" + viewName);
                     // 如果是数据存储则需要删除历史视图
                     if("DataStore".equalsIgnoreCase(xdataset.name())){
                        String viewNameHis = viewName + "_HV";
                        dropView(synConnection, viewNameHis, failLog);
                        allLog.push("通过删除视图(View)：" + viewNameHis);
                     }
                  }
               }else{
                  IXmlObject xdataset = _datasetConsole.find(dataset);
                  if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                     String viewName = xdataset.innerGet(XDataStore.PTY_DATA_NAME);
                     dropView(synConnection, viewName, failLog);
                     allLog.push("通过删除视图(View)：" + viewName);
                     // 如果是数据存储则需要删除历史视图
                     if("DataStore".equalsIgnoreCase(xdataset.name())){
                        String viewNameHis = viewName + "_HV";
                        dropView(synConnection, viewNameHis, failLog);
                        allLog.push("通过删除视图(View)：" + viewNameHis);
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

   /**
    * <T>更新视图</T>
    * 
    */
   public void synView(ISqlConnection synConnection,
                       String viewName,
                       FStrings failLog){
      // 数据库连接
      ISqlConnection connection = null;
      try{
         // 获得被同步的数据库连接
         connection = _databaseConsole.alloc().activeConnection();
         // 获得被同步的视图sql
         String sqlView = viewSource(connection, viewName);
         // 比较如果被同步的为空，则删除要同步的视图
         // 如果是被同步的视图和要同步的视图不相同则更新要同步的视图
         if(RString.isEmpty(sqlView)){
            dropView(synConnection, viewName, failLog);
            return;
         }else{
            // 获得要同步的视图sql
            String sqlViewSyn = viewSource(synConnection, viewName);
            if(!sqlView.equalsIgnoreCase(sqlViewSyn)){
               createOrReplace(synConnection, viewName, sqlView, failLog);
               return;
            }
         }
      }catch(Exception e){
         failLog.push("跟新视图(View)：" + viewName + "失败！" + "\n" + "出错原因：" + e.toString());
      }finally{
      }
   }

   /**
    * <T>更新视图通过数据集</T>
    * 
    */
   public void synViewDataset(ISqlConnection synConnection,
                              FString sqlView,
                              String viewName,
                              FStrings failLog){
      // 比较如果被同步的为空，则删除要同步的视图
      if(null == sqlView){
         if(RString.isNotEmpty(viewName)){
            dropView(synConnection, viewName, failLog);
         }
         return;
      }
      // 如果是被同步的视图和要同步的视图不相同则更新要同步的视图
      // 获得要同步的视图sql
      String sqlViewSyn = viewSource(synConnection, viewName);
      if(!sqlView.equals(sqlViewSyn)){
         createOrReplaceSql(synConnection, viewName, sqlView, failLog);
         return;
      }
   }

   @Override
   public void updateView(FXmlNode config,
                          FStrings allLog,
                          FStrings failLog){
      FStrings items = fetchRangeItem(config);
      if(!items.isEmpty()){
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
            failLog.push("更新视图(View)：失败！" + "\n" + "出错原因：" + e.toString());
            return;
         }
         for(String item : items){
            if(RString.isNotEmpty(item)){
               synView(synConnection, item, failLog);
               allLog.push("更新视图(View)：" + item);
            }
         }
         if(null != synConnection){
            // 关闭被同步的数据库连接
            _databaseConsole.free(synConnection);
         }
      }
   }

   @Override
   public void updateViewDataset(FXmlNode config,
                                 FStrings allLog,
                                 FStrings failLog){
      FStrings items = fetchRangeItem(config);
      if(!items.isEmpty()){
         String synName = config.get(SYN_DATABASE_NAME);
         // 获得被同步的数据库连接
         ISqlConnection synConnection = null;
         try{
            // 获得同步的数据库连接
            synConnection = _databaseConsole.alloc(synName).activeConnection();
         }catch(Exception e){
            failLog.push("通过数据集更新视图(View)：失败！" + "\n" + "出错原因：" + e.toString());
            if(null != synConnection){
               // 关闭被同步的数据库连接
               _databaseConsole.free(synConnection);
            }
            return;
         }
         // 获得被同步的数据库连接
         for(String item : items){
            String dataset = item;
            if(RString.isNotEmpty(dataset)){
               if(TYPE_ALL.equalsIgnoreCase(dataset)){
                  for(IXmlObject xdataset : _datasetConsole.list()){
                     if(XDataProperty.NAME.equalsIgnoreCase(xdataset.name()) || XDataTemplate.NAME.equalsIgnoreCase(xdataset.name())){
                        continue;
                     }
                     if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                        continue;
                     }
                     FXmlNode datasetNode = buildConfig(xdataset);
                     FString sqlView = buildViewSource(datasetNode);
                     String viewName = datasetNode.get(XDataStore.PTY_DATA_NAME);
                     // 同步视图
                     synViewDataset(synConnection, sqlView, viewName, failLog);
                     allLog.push("更新视图(View)：" + viewName);
                     // 如果是数据存储则需要创建历史视图
                     if(datasetNode.isName(XDataStore.NAME)){
                        String viewNameHis = viewName + "_HV";
                        FString sqlViewHis = buildHisViewSource(datasetNode);
                        // 同步视图
                        synViewDataset(synConnection, sqlViewHis, viewNameHis, failLog);
                        allLog.push("更新视图(View)：" + viewNameHis);
                     }
                  }
               }else{
                  FXmlNode datasetNode = buildConfig(dataset);
                  if(datasetNode.isName(XDataProperty.NAME)){
                     continue;
                  }
                  if(RBoolean.parse(datasetNode.get(XDataStore.PTY_IS_VALID))){
                     FString sqlView = buildViewSource(datasetNode);
                     String viewName = datasetNode.get(XDataStore.PTY_DATA_NAME);
                     // 同步视图
                     synViewDataset(synConnection, sqlView, viewName, failLog);
                     allLog.push("更新视图(View)：" + viewName);
                     // 如果是数据存储则需要创建历史视图
                     if(datasetNode.isName(XDataStore.NAME)){
                        String viewNameHis = viewName + "_HV";
                        FString sqlViewHis = buildHisViewSource(datasetNode);
                        // 同步视图
                        synViewDataset(synConnection, sqlViewHis, viewNameHis, failLog);
                        allLog.push("更新视图(View)：" + viewNameHis);
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

   @Override
   public void updateViewPropertyDataset(FXmlNode config,
                                         FStrings allLog,
                                         FStrings failLog){
      FStrings items = fetchRangeItem(config);
      if(!items.isEmpty()){
         String synName = config.get(SYN_DATABASE_NAME);
         // 获得被同步的数据库连接
         ISqlConnection synConnection = null;
         try{
            // 获得同步的数据库连接
            synConnection = _databaseConsole.alloc(synName).activeConnection();
         }catch(Exception e){
            failLog.push("更新视图(View)：失败！" + "\n" + "出错原因：" + e.toString());
            if(null != synConnection){
               // 关闭被同步的数据库连接
               _databaseConsole.free(synConnection);
            }
            return;
         }
         // 获得被同步的数据库连接
         for(String item : items){
            String dataset = item;
            if(RString.isNotEmpty(dataset)){
               if(TYPE_ALL.equalsIgnoreCase(dataset)){
                  for(IXmlObject xdataset : _datasetConsole.list()){
                     if(!XDataProperty.NAME.equalsIgnoreCase(xdataset.name())){
                        continue;
                     }
                     if(!RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
                        continue;
                     }
                     FXmlNode datasetNode = buildConfig(xdataset);
                     FString sqlView = buildViewSource(datasetNode);
                     String viewName = datasetNode.get(XDataStore.PTY_DATA_NAME);
                     // 同步视图
                     synViewDataset(synConnection, sqlView, viewName, failLog);
                     allLog.push("更新视图(View)：" + viewName);
                     // 如果是数据存储则需要创建历史视图
                     if(datasetNode.isName(XDataStore.NAME)){
                        String viewNameHis = viewName + "_HV";
                        FString sqlViewHis = buildHisViewSource(datasetNode);
                        // 同步视图
                        synViewDataset(synConnection, sqlViewHis, viewNameHis, failLog);
                        allLog.push("更新视图(View)：" + viewNameHis);
                     }
                  }
               }else{
                  FXmlNode datasetNode = buildConfig(dataset);
                  if(!datasetNode.isName(XDataProperty.NAME)){
                     continue;
                  }
                  if(RBoolean.parse(datasetNode.get(XDataStore.PTY_IS_VALID))){
                     FString sqlView = buildViewSource(datasetNode);
                     String viewName = datasetNode.get(XDataStore.PTY_DATA_NAME);
                     // 同步视图
                     synViewDataset(synConnection, sqlView, viewName, failLog);
                     allLog.push("更新视图(View)：" + viewName);
                     // 如果是数据存储则需要创建历史视图
                     if(datasetNode.isName(XDataStore.NAME)){
                        String viewNameHis = viewName + "_HV";
                        FString sqlViewHis = buildHisViewSource(datasetNode);
                        // 同步视图
                        synViewDataset(synConnection, sqlViewHis, viewNameHis, failLog);
                        allLog.push("更新视图(View)：" + viewNameHis);
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

   /**
    * <T>获得包体代码</T>
    * <P>去掉尾空格</P>
    * 
    */
   public String viewSource(ISqlConnection connection,
                            String viewName){
      if(RString.isNotEmpty(viewName)){
         connection.setMaxFetch(MAX_FETCH);
         String sqlView = "";
         FSqlQuery query = new FSqlQuery(connection, _resource.findString("sql.view"));
         query.bindString("view_name", viewName);
         if(null != query){
            FDataset dataset = query.fetchDataset();
            if(null != dataset){
               for(FRow row : dataset){
                  sqlView = sqlView + row.get("TEXT");
               }
            }
         }
         return sqlView;
      }
      return null;
   }
}

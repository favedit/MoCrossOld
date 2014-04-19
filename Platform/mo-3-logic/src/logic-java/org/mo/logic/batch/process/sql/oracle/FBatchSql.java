package org.mo.logic.batch.process.sql.oracle;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.logic.batch.process.FBatchSqlCommand;

public class FBatchSql
      extends FBatchSqlCommand
      implements
         IBatchSql
{
   private static ILogger _logger = RLogger.find(FBatchSql.class);

   // 要同步的数据库名称
   private final static String SYN_DATABASE_NAME = "syn_name";

   // 本地的数据库名称
   private final static String DATABASE_NAME = "from_name";

   private final static int MAX_FETCH = 60000;

   // 获得xml中的sql文
   private static IResource _resource = RResource.find(FBatchSql.class);

   @Override
   public void compileInvalid(FXmlNode config,
                              FStrings allLog,
                              FStrings failLog){
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
      FSqlQuery query = new FSqlQuery(synConnection, _resource.findString("sql.invlaid"));
      FDataset dataset = query.fetchDataset();
      if(dataset.isEmpty()){
         return;
      }
      FSqlProcedure procedure = new FSqlProcedure("ALTER_COMPILE");
      procedure.setLogicName("DBMS_DDL");
      for(FRow row : dataset){
         String name = row.get("OBJECT_NAME");
         String type = row.get("OBJECT_TYPE");
         System.out.println("----------------------------------------");
         System.out.println("COMPILE INVALID " + type + " :" + name);
         System.out.println("========================================");
         procedure.createParameter("type", type, ESqlDataType.String, ESqlDataDirection.In);
         procedure.createParameter("schema", null, ESqlDataType.String, ESqlDataDirection.In);
         procedure.createParameter("name", name, ESqlDataType.String, ESqlDataDirection.In);
         // 执行编译无效对象逻辑过程
         executeProcedure(procedure, synConnection);
      }
      if(null != synConnection){
         // 关闭被同步的数据库连接
         _databaseConsole.free(synConnection);
      }
   }

   @Override
   public void execute(FXmlNode config,
                       FStrings allLog,
                       FStrings failLog){
      int executeCount = 0;
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
      while(true){
         // 解析xml获得要同步的数据集名称
         FStrings items = fetchRangeItem(config);
         if(items.isEmpty()){
            return;
         }
         int itemCount = items.count();
         // 判断执行次数
         if(0 == itemCount || executeCount == itemCount){
            return;
         }
         String sql = "";
         try{
            for(String item : items){
               if(RString.isNotEmpty(item)){
                  sql = item;
                  System.out.println("----------------------------------------");
                  System.out.println("EXECUTE SQL:" + item);
                  System.out.println("========================================");
                  System.out.println("");
                  System.out.println("");
                  System.out.println("");
                  synConnection.executeSql(sql);
                  System.out.println("执行成功！");
               }
            }
         }catch(Exception e){
            System.out.println(e);
            System.out.println("执行失败！");
            if(null != synConnection){
               // 关闭被同步的数据库连接
               _databaseConsole.free(synConnection);
            }
         }finally{
            executeCount = itemCount;
            System.out.println("========================================");
         }
      }
   }

   @Override
   public void executeCompare(FXmlNode config,
                              FStrings allLog,
                              FStrings failLog){
      FDataset dataset1 = null;
      FDataset dataset2 = null;
      // 获得要同步的数据库名称
      String synName = config.get(SYN_DATABASE_NAME);
      // 获得本地的数据库名称
      String dataName = config.get(DATABASE_NAME);
      // 获得被同步的数据库连接
      ISqlConnection synConnection = null;
      ISqlConnection connection = null;
      try{
         connection = _databaseConsole.alloc(dataName).activeConnection();
         synConnection = _databaseConsole.alloc(synName).activeConnection();
         String sql = "SELECT ALO.OBJECT_TYPE, ALO.OBJECT_NAME FROM ALL_OBJECTS ALO WHERE ALO.OWNER = USER AND ALO.OWNER NOT IN ('SYS','SYSTEM') AND ALO.OBJECT_NAME NOT IN ('PAK_ALL_COMPILE') AND ALO.OBJECT_TYPE IN ('VIEW', 'SEQUENCE', 'PACKAGE', 'PACKAGE BODY', 'TYPE', 'TYPE BODY') ORDER BY ALO.OBJECT_TYPE";
         connection.setMaxFetch(100000);
         synConnection.setMaxFetch(100000);
         dataset1 = connection.fetchDataset(sql);
         dataset2 = synConnection.fetchDataset(sql);
      }catch(Exception e){
         System.out.println("*********************************");
         System.out.println(e);
         System.out.println("*********************************");
      }finally{
         _databaseConsole.free(connection);
      }
      FString result = new FString();
      for(FRow row1 : dataset1){
         String type1 = row1.get("OBJECT_TYPE");
         String value1 = row1.get("OBJECT_NAME");
         boolean yes = false;
         for(FRow row2 : dataset2){
            String type2 = row2.get("OBJECT_TYPE");
            String value2 = row2.get("OBJECT_NAME");
            if(type1.equalsIgnoreCase(type2) && value1.equalsIgnoreCase(value2)){
               yes = true;
               break;
            }
         }
         if(!yes){
            result.append(type1 + "   :");
            result.append(value1);
            result.append("\n");
         }
      }
      System.out.println("*****************************************");
      System.out.println(result.toString());
      System.out.println("*****************************************");
   }

   @Override
   public void executeDisplay(FXmlNode config,
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
      String sql = "";
      try{
         for(String item : items){
            if(RString.isNotEmpty(item)){
               sql = item;
               synConnection.setMaxFetch(MAX_FETCH);
               FDataset dataset = synConnection.fetchDataset(sql);
               _logger.debug(this, "execute", "Execute sql is success[sql={0}].", sql);
               if(!dataset.isEmpty()){
                  System.out.println("结果显示****************************************");
                  for(FRow row : dataset){
                     String values[] = row.values();
                     String value = "";
                     for(String valueChild : values){
                        value = value + "   " + valueChild;
                     }
                     System.out.println(value);
                  }
                  System.out.println("************************************************");
               }
            }
         }
      }catch(Exception e){
         _logger.debug(this, "execute", "Execute sql is fail[sql={0}].", sql);
      }finally{
      }
      if(null != synConnection){
         // 关闭被同步的数据库连接
         _databaseConsole.free(synConnection);
      }
   }

   public void executeFunction(FSqlFunction function,
                               ISqlConnection synConnection){
      try{
         System.out.println("----------------------------------------");
         System.out.println("EXECUTE FUNCTION :" + function.fullName());
         System.out.println("========================================");
         System.out.println("");
         System.out.println("");
         System.out.println("");
         synConnection.execute(function);
         System.out.println("执行成功！");
      }catch(Exception e){
         System.out.println(e);
         System.out.println("执行失败！");
      }finally{
         System.out.println("========================================");
      }
   }

   public void executeProcedure(FSqlProcedure procedure,
                                ISqlConnection synConnection){
      try{
         System.out.println("----------------------------------------");
         System.out.println("EXECUTE PROCEDURE :" + procedure.fullName());
         System.out.println("========================================");
         System.out.println("");
         System.out.println("");
         System.out.println("");
         synConnection.execute(procedure);
         System.out.println("执行成功！");
      }catch(Exception e){
         System.out.println(e);
         System.out.println("执行失败！");
      }finally{
         System.out.println("========================================");
      }
   }

   @Override
   public void executeScript(FXmlNode config,
                             FStrings allLog,
                             FStrings failLog){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      FString sqlScript = new FString();
      try{
         for(String item : items){
            if(RString.isNotEmpty(item)){
               System.out.println("----------------------------------------");
               System.out.println("EXECUTE SCRIPT:" + item);
               System.out.println("========================================");
               System.out.println("");
               System.out.println("");
               System.out.println("");
               sqlScript.clear();
               sqlScript.append(item);
               // 执行脚本
               // synConnection.executeScript(sqlScript);
               System.out.println("执行成功！");
            }
         }
      }catch(Exception e){
         System.out.println(e);
         System.out.println("执行失败！");
      }finally{
         System.out.println("========================================");
      }
   }
}

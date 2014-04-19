package org.mo.util.data;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;
import org.mo.com.system.RThread;
import org.mo.core.aop.RAop;
import org.mo.eng.data.IDatabaseConsole;

public class FDataTargetWriteThread
      extends FThread
{

   private final static ILogger _logger = RLogger.find(FDataTargetWriteThread.class);

   private ISqlConnection _connection;

   private FDataSourceItems _items;

   public FDataTargetWriteThread(FDataSourceItems items,
                                 ISqlConnection connection){
      _items = items;
      _connection = connection;
   }

   @Override
   public boolean execute(){
      while(true){
         boolean has = false;
         // 获得数据集管理器
         for(FDataSourceItem item : _items.toObjects()){
            if(item.test()){
               has = true;
               synchronized(_items){
                  //_items.delete(item.dataLogic());
               }
               // 更新包头和包体
               try{
                  if(!item.sourceHead().equals(item.targetHead())){
                     _logger.debug(null, "syncPackage", "Transfer package body: {0}", item.dataLogic());
                     _connection.executeSql(new FString("CREATE OR REPLACE " + item.sourceHead().toString()));
                  }
                  if(!item.sourceBody().equals(item.targetBody())){
                     _logger.debug(null, "syncPackage", "Transfer package body: {0}", item.dataLogic());
                     _connection.executeSql(new FString("CREATE OR REPLACE " + item.sourceBody().toString()));
                  }
               }catch(Exception e){
                  _logger.error(this, "execute", e);
               }
            }
         }
         //
         if(has){
            //_items.compress();
            if(_items.isEmpty()){
               break;
            }
         }
         //
         RThread.sleep(1000);
      }
      IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
      databaseConsole.free(_connection);
      // 释放环境
      RAop.release();
      return true;
   }
}

package org.mo.util.data;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FString;
import org.mo.com.system.FThread;
import org.mo.core.aop.RAop;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlPackageParser;

public class FDataTargetReadThread
      extends FThread{

   private ISqlConnection _connection;

   private FDataSourceItems _items;

   public FDataTargetReadThread(FDataSourceItems items,
                                ISqlConnection connection){
      _items = items;
      _connection = connection;
   }

   @Override
   public boolean execute(){
      // 获得数据集管理器
      for(FDataSourceItem item : _items.toObjects()){
         // 读取包头
         FSqlPackageParser parserSource = new FSqlPackageParser(_connection, item.dataLogic());
         FString headSource = parserSource.fetchHeadString();
         FString bodySource = parserSource.fetchBodyString();
         // 设置
         synchronized(item){
            item.setTargetHead(headSource);
            item.setTargetBody(bodySource);
         }
      }
      IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
      databaseConsole.free(_connection);
      return true;
   }

}

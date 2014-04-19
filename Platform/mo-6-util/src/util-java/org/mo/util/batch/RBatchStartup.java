package org.mo.util.batch;

import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;
import org.mo.logic.batch.IBatchConsole;

public class RBatchStartup{

   private final static ILogger _logger = RLogger.find(RBatchStartup.class);

   public static void main(String[] args){
      // 加载设置
      String config = args[0];
      String batchPath = args[1];
      String synDataset = args[2];
      String batchConfig = args[3];
      if(RString.isEmpty(config)){
         _logger.debug(null, "main", "加载配置为空。【name={0}】", config);
      }
      if(RString.isEmpty(synDataset)){
         _logger.debug(null, "main", "要同步的数据库位空。【name={0}】", config);
      }
      RAop.configConsole().loadFile(config);
      // 收集数据库链接
      IBatchConsole batchConsole = RAop.find(IBatchConsole.class);
      batchConsole.loadConfig(batchPath, batchConfig);
      batchConsole.setSynDatabaseName(synDataset);
      batchConsole.process(null);
      // 释放资源
      RAop.release();
   }
}

package org.mo.logic.batch.common;

import org.mo.com.lang.FObjects;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.logic.batch.IBatchConsole;

/**
 * <T>批处理处理列表。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public class FBatchProcesses
      extends FObjects<FBatchProcess>
{
   protected IBatchConsole _console;

   private static ILogger _logger = RLogger.find(FBatchDefine.class);

   /**
    * <T>构造函数</T>
    * 
    * @param console 批处理控制台
    */
   public FBatchProcesses(IBatchConsole console){
      _console = console;
   }

   /**
    * <T>执行处理列表中的处理</T>
    * 
    */
   public void process(String dataset){
      if(RString.isNotEmpty(dataset)){
         _logger.debug(this, "process", "This processes dataset's process process Begin[dataset name={0}].", dataset);
      }
      _logger.debug(this, "process", "This processess  process process Begin.");
      _logger.debug(this, "process", "---------------------------------------------------");
      if(!isEmpty()){
         for(FBatchProcess process : this){
            process.process(dataset);
         }
      }
      _logger.debug(this, "process", "---------------------------------------------------");
      _logger.debug(this, "process", "This processess  process process End.");
   }

   /**
    * <T>往处理列表添加处理</T>
    * 
    */
   @Override
   public void push(FBatchProcess process){
      super.push(process);
   }
}

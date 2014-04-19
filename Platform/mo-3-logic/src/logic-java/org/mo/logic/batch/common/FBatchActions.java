package org.mo.logic.batch.common;

import org.mo.com.lang.FObjects;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.logic.batch.IBatchConsole;

/**
 * <T>批处理的动作列表。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public class FBatchActions
      extends FObjects<FBatchAction>
{
   protected IBatchConsole _console;

   private static ILogger _logger = RLogger.find(FBatchDefine.class);

   /**
    * <T>构造函数</T>
    * 
    * @param console 批处理控制台
    */
   public FBatchActions(IBatchConsole console){
      _console = console;
   }

   /**
    * <T>执行动作列表中的动作</T>
    * 
    */
   public void process(String dataset){
      if(RString.isNotEmpty(dataset)){
         _logger.debug(this, "process", "This actions dataset's process action Begin[dataset name={0}].", dataset);
      }
      _logger.debug(this, "process", "This actions  process action Begin.");
      _logger.debug(this, "process", "---------------------------------------------------");
      for(FBatchAction action : this){
         action.process(dataset);
      }
      _logger.debug(this, "process", "This actions  process action End.");
      _logger.debug(this, "process", "---------------------------------------------------");
   }

   /**
    * <T>往执行动作列表添加执行动作</T>
    * 
    */
   @Override
   public void push(FBatchAction action){
      super.push(action);
      _logger.debug(this, "push", "This actions push action Success[action name={0}].", action.name());
   }
}

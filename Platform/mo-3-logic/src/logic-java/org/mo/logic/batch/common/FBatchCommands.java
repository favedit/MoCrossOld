package org.mo.logic.batch.common;

import org.mo.com.lang.FObjects;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.logic.batch.IBatchConsole;

/**
 * <T>批处理处理列表。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public class FBatchCommands
      extends FObjects<FBatchCommand>
{
   protected IBatchConsole _console;

   private static ILogger _logger = RLogger.find(FBatchDefine.class);

   /**
    * <T>构造函数</T>
    * 
    * @param console 批处理控制台
    */
   public FBatchCommands(IBatchConsole console){
      _console = console;
   }

   /**
    * <T>根据命令名称获得命令对象</T>
    * 
    * @param name 定义名称
    */
   public FBatchCommand getCommand(String name){
      // 根据命令名称查找命令
      if(!isEmpty()){
         for(FBatchCommand command : this){
            if(name.equals(command.name())){
               _logger.debug(this, "getCommand", "Found command Success.[name={0}]", name);
               return command;
            }
         }
      }
      _logger.debug(this, "getCommand", "Found command Fail.[name={0}]", name);
      return null;
   }

   /**
    * <T>根据命令名称获得命令的接口</T>
    * 
    * @param name 定义名称
    */
   public String getCommandFace(String name){
      // 根据命令名称查找命令
      if(!isEmpty()){
         for(FBatchCommand command : this){
            if(name.equals(command.name())){
               _logger.debug(this, "getCommandFace", "Found command's face Success.[name={0}]", name);
               return command.face();
            }
         }
      }
      //_logger.debug(this, "getCommandFace", "Found command's face Fail.[name={0}]", name);
      return null;
   }

   /**
    * <T>根据命令名称获得命令处理的方法</T>
    * 
    * @param name 定义名称
    */
   public String getCommandMethod(String name){
      // 根据命令名称查找命令
      if(!isEmpty()){
         for(FBatchCommand command : this){
            if(name.equals(command.name())){
               _logger.debug(this, "getCommandMethod", "Found command's method Success.[name={0}]", name);
               return command.method();
            }
         }
      }
      _logger.debug(this, "getCommandFace", "Found command's method Fail.[name={0}]", name);
      return null;
   }

   /**
    * <T>往命令列表中插入新命令</T>
    * <P>根据定义名称查找定义列表中是否存在，如果存在更新，否则插入</P>
    * 
    * @param define 定义对象
    */
   @Override
   public void push(FBatchCommand command){
      // 根据命令名称获得命令
      FBatchCommand oldCommand = getCommand(command.name());
      if(null != oldCommand){
         oldCommand.setFace(command.face());
         oldCommand.setMethod(command.method());
         return;
      }
      super.push(command);
   }

   /**
    * <T>根据命令名称设置命令的接口</T>
    * 
    * @param name 命令名称
    * @param face 命令接口
    */
   public void setCommandFace(String name,
                              String face){
      // 根据命令名称查找命令
      if(!isEmpty()){
         for(FBatchCommand command : this){
            if(name.equals(command.name())){
               command.setFace(face);
               _logger.debug(this, "setCommandFace", "Set command's face Success.[name={0}]", name);
               return;
            }
         }
      }
      _logger.debug(this, "setCommandFace", "Set command's face Fail,not found command.[name={0}]", name);
   }

   /**
    * <T>根据命令名称设置命令处理的方法</T>
    * 
    * @param name 命令名称
    * @param method 命令处理的方法
    */
   public void setCommandMethod(String name,
                                String method){
      // 根据命令名称查找命令
      if(!isEmpty()){
         for(FBatchCommand command : this){
            if(name.equals(command.name())){
               command.setMethod(method);
               _logger.debug(this, "setCommandMethod", "Set command's method Success.[name={0}]", name);
               return;
            }
         }
      }
      _logger.debug(this, "setCommandMethod", "Set command's method Fail,not found command.[name={0}]", name);
   }
}

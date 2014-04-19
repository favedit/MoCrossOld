package org.mo.logic.batch.common;

import org.mo.com.lang.FStrings;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.logic.batch.IBatchConsole;

/**
 * <T>批处理变量定义。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public class FBatchProcess
      extends FAbstractBatchBase
{
   private static ILogger _logger = RLogger.find(FBatchProcess.class);

   public static String NAME = "Process";

   private String _name;

   // 存储流程名称
   private final FStrings _processName = new FStrings();

   private FXmlNode _config;

   // 处理的方法列表
   private final FBatchActions _actions = new FBatchActions(_console);

   /**
    * <T>构造函数</T>
    * 
    */
   public FBatchProcess(IBatchConsole console){
      super(console);
   }

   /**
    * <T>根据处理流程名称判断该流程是否在子流程中是否被加载</T>
    * 
    */
   public boolean existProcess(String name){
      if(null != _processName){
         if(_processName.contains(name)){
            return true;
         }
      }
      return false;
   }

   /**
    * <T>导入流程配置文件</T>
    * 
    */
   public void loadConfig(FXmlNode config){
      if(null == config){
         return;
      }
      _config = config;
      _name = _config.get("name");
      loadConfigAction(config);
   }

   /**
    * <T>导入配置文件</T>
    * 
    */
   public void loadConfigAction(FXmlNode config){
      if(null == config){
         return;
      }
      if(config.hasNode()){
         for(FXmlNode node : config.nodes()){
            // 处理Process，导入配置好的Process导入Action
            if(node.isName(NAME)){
               _processName.push(_name);
               String nodeName = node.get("name");
               if(existProcess(nodeName)){
                  continue;
               }
               FXmlNode detailConfig = _console.getProcessConfig(nodeName);
               if((null == detailConfig) || (!detailConfig.hasNode())){
                  continue;
               }
               loadConfigAction(detailConfig);
            }else if(node.isName(FBatchAction.NAME)){
               // 处理Action
               FBatchAction action = new FBatchAction(_console);
               action.loadConfig(node);
               _actions.push(action);
            }
         }
      }
   }

   /**
    * <T>获得处理的名称</T>
    * 
    */
   @Override
   public String name(){
      return _name;
   }

   public void process(String dataset){
      long start = System.currentTimeMillis();
      _logger.debug(this, "process", "Process**********************************************");
      _logger.debug(this, "process", "This process process begin[process name={0}].", _name);
      if(!_actions.isEmpty()){
         for(FBatchAction action : _actions){
            action.process(dataset);
         }
      }else{
         _logger.debug(this, "process", "This process process end,because not has action[process name={0},time:{1}].", _name, System.currentTimeMillis() - start);
      }
      _logger.debug(this, "process", "This process process end[process name={0},time:{1}].", _name, System.currentTimeMillis() - start);
      _logger.debug(this, "process", "Process**********************************************");
   }

   /**
    * <T>设置处理的名称</T>
    * 
    */
   @Override
   public void setName(String name){
      _name = name;
   }
}

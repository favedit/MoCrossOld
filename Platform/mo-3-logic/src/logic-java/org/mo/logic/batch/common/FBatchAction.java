package org.mo.logic.batch.common;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.logic.batch.IBatchConsole;

/**
 * <T>批处理的动作。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public class FBatchAction
      extends FAbstractBatchBase
{
   private static ILogger _logger = RLogger.find(FBatchAction.class);

   public static String NAME = "Action";

   private String _name;

   private String _face;

   private String _method;

   private FXmlNode _config;

   /**
    * <T>构造函数</T>
    * 
    */
   public FBatchAction(IBatchConsole console){
      super(console);
   }

   /**
    * <T>获得执行动作的接口</T>
    * 
    */
   public String face(){
      if(RString.isNotEmpty(_face)){
         return _face;
      }
      if(RString.isEmpty(_name)){
         _name = _config.get("name");
      }
      FBatchCommand command = _console.getCommand(_name);
      return command.face();
   }

   /**
    * <T>导入动作的配置文件</T>
    * 
    */
   public void loadConfig(FXmlNode config){
      _config = config;
      _name = config.get("name");
      // 获得动作的执行命令
      FBatchCommand command = _console.getCommand(_name);
      _face = command.face();
      _method = command.method();
   }

   /**
    * <T>获得执行动作的方法</T>
    * 
    */
   public String method(){
      if(RString.isNotEmpty(_method)){
         return _method;
      }
      if(RString.isEmpty(_name)){
         _name = _config.get("name");
      }
      FBatchCommand command = _console.getCommand(_name);
      return command.method();
   }

   /**
    * <T>获得执行动作的名称</T>
    * 
    */
   @Override
   public String name(){
      return _name;
   }

   /**
    * <T>执行动作</T>
    * 
    */
   public void process(String dataset){
      long start = System.currentTimeMillis();
      _logger.debug(this, "process", "Process action begin[action name={0}.", _name);
      if(null != _config){
         if(RString.isEmpty(_name)){
            _name = _config.get("name");
         }
         if(RString.isEmpty(_face)){
            FBatchCommand command = _console.getCommand(_name);
            _face = command.face();
         }
         if(RString.isEmpty(_method)){
            FBatchCommand command = _console.getCommand(_name);
            _method = command.method();
         }
         // 接口、方法和配置文件都不为空则动作执行
         if(RString.isNotEmpty(_face) && RString.isNotEmpty(_method) && null != _config){
            try{
               // 执行方法
               _console.execute(_face, _method, _config.copy(), new FAttributes());
            }catch(Exception e){
               throw new FFatalError(e);
            }
         }else{
            _logger.debug(this, "process", "Process action is fail, because command or config is null[name={0}]", _name);
         }
      }else{
         _logger.debug(this, "process", "Process action is fail, because config is null[name={0}]", _name);
      }
      _logger.debug(this, "process", "Process action end[action name={0},time:{1}].", _name, System.currentTimeMillis() - start);
   }

   /**
    * <T>设置执行动作的接口</T>
    * 
    */
   public void setFace(String face){
      _face = face;
      if(RString.isEmpty(_name)){
         _name = _config.get("name");
      }
      FBatchCommand command = _console.getCommand(_name);
      if(null != command){
         command.setFace(face);
         _logger.debug(this, "setFace", "Set action's face is Success[name={0}].", _name);
      }else{
         _logger.debug(this, "setFace", "Set action's face is fail,because command is null[name={0}].", _name);
      }
   }

   /**
    * <T>设置执行动作的方法</T>
    * 
    */
   public void setMethod(String method){
      _method = method;
      if(RString.isEmpty(_name)){
         _name = _config.get("name");
      }
      // 获得动作的执行命令
      FBatchCommand command = _console.getCommand(_name);
      if(null != command){
         command.setMethod(method);
         _logger.debug(this, "setMethod", "Set action's method is Success[name={0}].", _name);
      }else{
         _logger.debug(this, "setFace", "Set action's method is fail,because command is null[name={0}].", _name);
      }
   }

   /**
    * <T>设置执行动作的名称</T>
    * 
    */
   @Override
   public void setName(String name){
      _name = name;
      _logger.debug(this, "setName", "Set action's name is Success[name={0}].", _name);
   }
}

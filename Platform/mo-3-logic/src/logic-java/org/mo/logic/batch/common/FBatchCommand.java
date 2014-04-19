package org.mo.logic.batch.common;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.logic.batch.IBatchConsole;

public class FBatchCommand
      extends FAbstractBatchBase
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FBatchCommand.class);

   @SuppressWarnings("unused")
   private FXmlNode _config;

   public static String NAME = "Command";

   private String _face;

   private String _name;

   private String _method;

   public FBatchCommand(IBatchConsole console){
      super(console);
   }

   /**
    * <T>获得该命令的接口</T>
    * 
    */
   public String face(){
      return _face;
   }

   public void loadConfig(FXmlNode config){
      _name = config.get("name");
      setFace(config.get("face"));
      _method = config.get("method");
      _config = config;
   }

   /**
    * <T>获得命令处理的方法</T>
    * 
    */
   public String method(){
      return _method;
   }

   /**
    * <T>获得该命令名称</T>
    * 
    */
   @Override
   public String name(){
      return _name;
   }

   public void process(String dataset){
   }

   /**
    * <T>设置命令接口</T>
    * 
    * @param face 命令接口
    */
   public void setFace(String face){
      _face = face;
   }

   /**
    * <T>设置命令要处理的的方法</T>
    * 
    * @param method 处理方法名称
    */
   public void setMethod(String method){
      _method = method;
   }

   /**
    * <T>设置命令名称</T>
    * 
    * @param name 命令接口
    */
   @Override
   public void setName(String name){
      _name = name;
   }
}

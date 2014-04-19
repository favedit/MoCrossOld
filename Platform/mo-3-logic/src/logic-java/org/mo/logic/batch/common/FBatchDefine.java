package org.mo.logic.batch.common;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.logic.batch.IBatchConsole;

/**
 * <T>批处理变量定义。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public class FBatchDefine
      extends FAbstractBatchBase
{
   private static ILogger _logger = RLogger.find(FBatchDefine.class);

   public static String NAME = "Define";

   protected String _name;

   @SuppressWarnings("unused")
   private FXmlNode _config;

   protected String _value;

   public FBatchDefine(IBatchConsole console){
      super(console);
   }

   public void loadConfig(FXmlNode config){
      _config = config.copy();
      _name = config.get("name");
      _value = config.get("value");
      _logger.debug(this, "loadConfig", "This Define loadConfig Success![name={0},value={1}]", _name, _value);
   }

   @Override
   public String name(){
      return _name;
   }

   @Override
   public void setName(String name){
      _name = name;
      _logger.debug(this, "setName", "This Define set name Success![name={0}]", _name);
   }

   public void setValue(String value){
      _value = value;
      _logger.debug(this, "setValue", "This Define set value Success![value={0}]", _value);
   }

   public String value(){
      return _value;
   }
}

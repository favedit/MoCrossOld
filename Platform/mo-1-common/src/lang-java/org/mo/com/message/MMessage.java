package org.mo.com.message;

import org.mo.com.lang.FError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.RThrowable;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.xml.FXmlNode;

public abstract class MMessage
      implements
         IMessage
{
   private String _code;

   private String _description;

   private String _message;

   private Object[] _params;

   private String _type;

   public MMessage(){
   }

   public MMessage(String message){
      _message = message;
   }

   public MMessage(String message,
                   Object[] params){
      _message = message;
      _params = params;
   }

   public MMessage(Throwable throwable){
      // 获得根例外
      Throwable rootThrowable = RThrowable.rootThrowable(throwable);
      // 设置例外信息
      _type = RClass.shortName(rootThrowable);
      _code = rootThrowable.getMessage();
      if(rootThrowable instanceof FError){
         _message = ((FError)rootThrowable).description();
      }else{
         _message = RThrowable.buildMessage(throwable).toString();
      }
      _description = RThrowable.buildDescription(throwable).toString();
   }

   public MMessage(Throwable throwable,
                   String message,
                   Object... params){
      // 获得根例外
      Throwable rootThrowable = RThrowable.rootThrowable(throwable);
      // 设置例外信息
      _type = RClass.shortName(rootThrowable);
      _code = rootThrowable.getMessage();
      if(rootThrowable instanceof FError){
         _message = ((FError)rootThrowable).description();
      }else{
         _message = RThrowable.buildMessage(throwable).toString();
      }
      _description = RThrowable.buildDescription(throwable).toString();
      _params = params;
   }

   public String code(){
      return _code;
   }

   @Override
   public FXmlNode convertToNode(){
      FXmlNode config = new FXmlNode();
      saveConfig(config);
      return config;
   }

   @Override
   public String description(){
      return _description;
   }

   public TDumpInfo dump(){
      return dump(new TDumpInfo(this));
   }

   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append("[");
      info.append("Message:");
      info.append(_message);
      info.append(" Description:");
      info.append(_description);
      info.append(" Params:");
      info.append(_params);
      info.append(" Type:");
      info.append(_type);
      info.append("]");
      return info;
   }

   @Override
   public void loadConfig(FXmlNode config){
      _message = config.get("message");
      _description = config.get("description");
   }

   @Override
   public String message(){
      return RString.format(_message, _params);
   }

   @Override
   public abstract String name();

   @Override
   public Object[] params(){
      return _params;
   }

   @Override
   public void saveConfig(FXmlNode config){
      config.setName(name());
      config.set("message", message());
      config.set("description", description());
   }

   public void setCode(String code){
      _code = code;
   }

   @Override
   public void setDescription(String sValue){
      _description = sValue;
   }

   public void setMessage(String message){
      _message = message;
   }

   @Override
   public String type(){
      return _type;
   }
}

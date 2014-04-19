package org.mo.eng.translate.common;

import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;

public abstract class XBaseTranslateContent
      extends FXmlObject
{
   public static final String NAME = "TranslateContent";

   @Override
   public String name(){
      return NAME;
   }

   @AName("name")
   protected String _name;

   public String getName(){
      return _name;
   }

   public void setName(String value){
      _name = value;
   }

   @Override
   public void loadConfig(FXmlNode config,
                          EXmlConfig type){
      super.loadConfig(config, type);
      if(EXmlConfig.Full == type){
         if(config.contains("name")){
            setName(config.get("name"));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get("name"));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get("name"));
         }
      }
   }

   @Override
   public void saveConfig(FXmlNode config,
                          EXmlConfig type){
      config.setName(NAME);
      super.saveConfig(config, type);
      if(EXmlConfig.Full == type){
         if(!RString.isEmpty(getName())){
            config.set("name", getName());
         }
      }else if(EXmlConfig.Simple == type){
         if(!RString.isEmpty(getName())){
            config.set("name", getName());
         }
      }else if(EXmlConfig.Value == type){
         String sName = getName();
         if(!RString.isEmpty(sName)){
            config.set("name", sName);
         }
      }
   }

   @Override
   public String innerGet(String name){
      if(RString.isEmpty(name)){
         return null;
      }else if("name".equalsIgnoreCase(name)){
         return getName();
      }
      return null;
   }

   @Override
   public void innerSet(String name,
                        String value){
      if(RString.isEmpty(name)){
         return;
      }else if("name".equalsIgnoreCase(name)){
         setName(value);
      }
   }
}

package org.mo.eng.template;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.template.tag.ITplTag;

public class FTplTagConfig
{
   private final Class<?> _class;

   private String _name;

   public FTplTagConfig(FXmlNode config){
      _name = config.get("name");
      _class = RClass.findClass(config.get("class"));
   }

   public String name(){
      return _name;
   }

   public ITplTag newInstance(){
      try{
         return (ITplTag)_class.newInstance();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void setName(String name){
      _name = name;
   }
}

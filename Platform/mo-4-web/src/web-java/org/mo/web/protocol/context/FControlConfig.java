package org.mo.web.protocol.context;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

public class FControlConfig
{

   private FAttributes _attributes = new FAttributes();

   public FControlConfig(FXmlNode config){
      build(config);
   }

   public String attribute(String name){
      return _attributes.get(name);
   }

   protected void build(FXmlNode config){
      for(FXmlNode node : config.nodes()){
         if(node.isName("Property")){
            String name = node.get("name");
            if(!RString.isBlank(name)){
               _attributes.set(name.trim().toLowerCase(), node.text());
            }
         }
      }
   }

}

package org.mo.web.protocol.context;

import org.mo.com.lang.FMap;
import org.mo.com.xml.FXmlNode;

public class FPageConfig
{

   private FMap<String, FControlConfig> _controls = new FMap<String, FControlConfig>(String.class, FControlConfig.class);

   public FPageConfig(FXmlNode config){
      build(config);
   }

   protected void build(FXmlNode config){
      for(FXmlNode node : config.nodes()){
         if(node.isName("Control")){
            buildControl(node);
         }
      }
   }

   protected void buildControl(FXmlNode config){
      String name = config.get("name").toLowerCase();
      FControlConfig ctlConfig = new FControlConfig(config);
      _controls.set(name, ctlConfig);
   }

   public FControlConfig control(String name){
      return _controls.get(name.toLowerCase());
   }

}

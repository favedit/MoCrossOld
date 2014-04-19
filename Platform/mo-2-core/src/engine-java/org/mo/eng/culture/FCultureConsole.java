package org.mo.eng.culture;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.cultrue.FCulture;
import org.mo.com.xml.FXmlNode;

public class FCultureConsole
      implements
         ICultureConsole
{
   // 序列化版本号
   private final FCulture _culture = new FCulture();

   private final FDictionary<FCulture> _cultures = new FDictionary<FCulture>(FCulture.class);

   @Override
   public FCulture culture(){
      return _culture;
   }

   @Override
   public FCulture find(){
      String threadId = Long.toString(Thread.currentThread().getId());
      FCulture culture = _cultures.get(threadId);
      if(culture == null){
         culture = new FCulture();
         _cultures.set(threadId, culture);
      }
      return culture;
   }

   public void initializeConfig(FXmlNode config){
      //      _culture.setEncoding(config.nodeText("charset"));
      //      for(FXmlNode node : config.nodes()){
      //         String name = node.name().toLowerCase();
      //         if(name.equals("calender")){
      //            _culture.calender().construct(node);
      //         }else if(name.equals("country")){
      //            _culture.country().construct(node);
      //         }else if(name.equals("currency")){
      //            _culture.curency().construct(node);
      //         }else if(name.equals("number")){
      //            _culture.number().construct(node);
      //         }
      //      }
   }

   @Override
   public void release(FCulture culture){
   }
}

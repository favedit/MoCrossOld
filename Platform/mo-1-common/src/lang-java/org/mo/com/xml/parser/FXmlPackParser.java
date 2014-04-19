package org.mo.com.xml.parser;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

public class FXmlPackParser
{
   public final static String PTY_CHILDREN_PACK = "children_pack";

   private final String _childName;

   public FXmlPackParser(){
      _childName = PTY_CHILDREN_PACK;
   }

   public FXmlPackParser(String childName){
      _childName = childName;
   }

   public void parse(FXmlNode config,
                     String pack){
      try{
         if(!RString.isEmpty(pack)){
            FStrings top = new FStrings();
            top.unpack(pack);
            int count = top.count();
            for(int j = 0; j < count; j++){
               // 解析最顶层节点
               FXmlNode topNode = config.createNode();
               topNode.attributes().unpack(top.get(j));
               String childrenPack = topNode.removeAttribute(_childName);
               if(!RString.isEmpty(childrenPack)){
                  // 解析下一层节点
                  parse(topNode, childrenPack);
               }
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Parse error. (pack={0})", pack);
      }
   }

   public FXmlNode parse(String pack){
      FXmlNode config = new FXmlNode();
      if(!RString.isEmpty(pack)){
         parse(config, pack);
      }
      return config;
   }
}

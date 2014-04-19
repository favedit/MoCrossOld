package org.mt.core.util;

import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodeDictionary;

public class FComponentMatch2
{
   private static ILogger _logger = RLogger.find(FComponentMatch2.class);

   @SuppressWarnings("null")
   public FXmlNode match(String type,
                         String face,
                         String field){
      //FXmlNodeMap map = _nodes.value(type.toLowerCase());
      FXmlNodeDictionary map = null;
      //if(map != null){
      int n = -1;
      String match = null;
      FXmlNode node = null;
      String nodeFace = null;
      int count = map.count();
      while(++n < count){
         match = RString.replace(map.name(n), "*", "\\w+");
         if(face.matches(match)){
            node = map.value(n);
            nodeFace = node.get("face");
            break;
         }
      }
      if(node != null){
         if(node.hasNode("Proxy")){
            node.set("face", face);
            node.set("class", face + "Proxy");
            return node;
         }
         // TODO: Match
         String[] names = nodeFace.split("\\*");
         String[] values = node.get(field).split("\\*");
         if(names.length == values.length){
            if(names.length == 3){
               int find = face.lastIndexOf(names[1]);
               String first = face.substring(names[0].length(), find);
               String end = face.substring(find + names[1].length(), face.length() - names[2].length());
               match = values[0] + first + values[1] + end + values[2];
               _logger.debug(this, "match", "Match face [{1}]->[{2}]", face, match);
               FXmlNode result = node.copy();
               result.setName(type);
               result.set("face", face);
               result.set(field, match);
               return result;
            }else if(names.length == 2){
               int find = face.lastIndexOf(names[1]);
               String sub = face.substring(names[0].length(), find);
               match = values[0] + sub + values[1];
               _logger.debug(this, "match", "Match face [{1}]->[{2}]", face, match);
               FXmlNode result = node.copy();
               result.setName(type);
               result.set("face", face);
               result.set(field, match);
               return result;
            }
         }
      }
      //}
      return null;
   }
}

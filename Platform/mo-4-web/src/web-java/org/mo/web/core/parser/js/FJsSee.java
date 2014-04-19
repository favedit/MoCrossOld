package org.mo.web.core.parser.js;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

public class FJsSee
      extends FJsParameter
{

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.append("[name=", _name);
      info.append(",alias=", _alias);
      info.append(",type=", _type);
      info.append(",data_type=", "See");
      info.append(",description=", _description, "]");
      return info;
   }

   public FXmlNode makeSeeNode(){
      FXmlNode node = new FXmlNode("See");
      node.set("name", _name);
      node.set("type", _type.toString());
      node.set("data_type", "See");
      node.set("description", _description);
      return node;
   }

   @Override
   public void parse(String line){
      try{
         String[] items = RString.splitTwo(line, ".", true);
         // 没有写注释的情况
         if(null == items){
            _type = line;
            _name = "see";
         }else if(items.length == 2){
            _type = items[0];
            _description = items[1];
            _name = "see";
         }else{
            throw new FFatalError("Parse parameter error. (line={0})", line);
         }
      }catch(Exception e){
         throw new FFatalError(e, "Parse parameter error. (line={0})", line);
      }
   }

}

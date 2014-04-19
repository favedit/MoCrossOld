package org.mo.web.core.parser.js;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;

public class FJsMethodEvent
      extends FJsParameter
{

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.append("[name=", _name);
      info.append(",alias=", _alias);
      info.append(",type=", _type);
      info.append(",description=", _description, "]");
      return info;
   }

   @Override
   public void parse(String line){
      try{
         String[] items = RString.splitTwo(line, " ", true);
         // 没有写注释的情况
         if(null == items){
            _type = line;
            _name = "event";
         }else if(items.length == 1){
            _type = items[0];
            _name = "event";
         }else if(items.length == 2){
            _type = items[0];
            _description = items[1];
            _name = "event";
         }else{
            throw new FFatalError("Parse event error. (line={0})", line);
         }
      }catch(Exception e){
         throw new FFatalError(e, "Parse event error. (line={0})", line);
      }
   }

}

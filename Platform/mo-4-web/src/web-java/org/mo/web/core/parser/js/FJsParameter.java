package org.mo.web.core.parser.js;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

public class FJsParameter
      extends FJsObject
{

   protected String _name;

   protected String _type;

   protected String _description;

   protected String _alias;

   protected String _dataType;

   public String alias(){
      return _alias;
   }

   public String description(){
      return _description;
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.append("[name=", _name);
      info.append(",alias=", _alias);
      info.append(",type=", _type);
      info.append(",data_type=", _dataType);
      info.append(",description=", _description, "]");
      return info;
   }

   public FXmlNode makeNode(){
      FXmlNode classNode = new FXmlNode("Parameter");
      classNode.set("name", _name);
      classNode.set("alias", _alias);
      classNode.set("data_type", "Parameter");
      classNode.set("type", _type);
      classNode.set("description", _description);
      return classNode;
   }

   public String name(){
      return _name;
   }

   public void parse(String line){
      try{
         String[] items = RString.splitTwo(line, " ", true);
         // 没有写注释的情况
         if(null == items){
            items = new String[]{line};
         }
         String[] subs = RString.split(items[0], ':');
         if(subs.length == 1){
            // 返回函数的情况
            _name = subs[0];
         }else if(subs.length == 2){
            // 参数里：名称:类型
            _name = subs[0];
            _type = subs[1];
         }else if(subs.length == 3){
            // 参数里：别称:名称:类型
            _alias = subs[0];
            _name = subs[1];
            _type = subs[2];
         }else{
            throw new FFatalError("Parse parameter error. (line={0})", line);
         }
         // 设置注释信息
         if(items.length > 1){
            _description = items[1];
         }
      }catch(Exception e){
         throw new FFatalError(e, "Parse parameter error. (line={0})", line);
      }
   }

   public void setAlias(String alias){
      _alias = alias;
   }

   public void setDescription(String description){
      _description = description;
   }

   public void setName(String name){
      _name = name;
   }

   public void setType(String type){
      _type = type;
   }

   public String type(){
      return _type;
   }

}

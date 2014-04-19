package org.mo.web.tag.util;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

public class FTagAttributeInfo
{

   private String _name;

   private String _required;

   private String _rtexprvalue;

   public void build(FString xml){
      if(RString.isEmpty(_name)){
         throw new FFatalError("Name is null.");
      }
      if(RString.isEmpty(_required)){
         _required = "false";
      }
      if(RString.isEmpty(_rtexprvalue)){
         _rtexprvalue = "false";
      }
      xml.appendLine("      <attribute>");
      xml.appendLine("         <name>", _name, "</name>");
      xml.appendLine("         <required>", _required, "</required>");
      xml.appendLine("         <rtexprvalue>", _rtexprvalue, "</rtexprvalue>");
      xml.appendLine("      </attribute>");
   }

   public void fromNode(FXmlNode config){
      _name = config.get("name");
      _required = config.get("required");
      _rtexprvalue = config.get("rtexprvalue");
   }

   public String name(){
      return _name;
   }

   public String required(){
      return _required;
   }

   public String rtexprvalue(){
      return _rtexprvalue;
   }

   public void setName(String value){
      _name = value;
   }

   public void setRequired(String value){
      _required = value;
   }

   public void setRtexprvalue(String value){
      _rtexprvalue = value;
   }

}

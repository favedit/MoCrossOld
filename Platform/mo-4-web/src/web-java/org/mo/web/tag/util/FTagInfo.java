package org.mo.web.tag.util;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

public class FTagInfo
{

   private FObjects<FTagAttributeInfo> _attributes = new FObjects<FTagAttributeInfo>(FTagAttributeInfo.class);

   private String _name;

   private String _tagclass;

   public FTagAttributeInfo append(String name){
      FTagAttributeInfo attribute = new FTagAttributeInfo();
      attribute.setName(name);
      _attributes.push(attribute);
      return attribute;
   }

   public void build(FString xml){
      if(RString.isEmpty(_name)){
         throw new FFatalError("Name is null.");
      }
      if(RString.isEmpty(_tagclass)){
         throw new FFatalError("Tagclass is null.");
      }
      xml.appendLine("   <tag>");
      xml.appendLine("      <name>", _name, "</name>");
      xml.appendLine("      <tagclass>", _tagclass, "</tagclass>");
      for(FTagAttributeInfo attrInfo : _attributes){
         attrInfo.build(xml);
      }
      xml.appendLine("   </tag>");
   }

   public void fromNode(FXmlNode config){
      _name = config.get("name");
      _tagclass = config.get("class");
      for(FXmlNode node : config.nodes()){
         if(node.isName("attribute")){
            FTagAttributeInfo attribute = new FTagAttributeInfo();
            attribute.fromNode(node);
            _attributes.push(attribute);
         }
      }
   }

   public String name(){
      return _name;
   }

   public void setName(String value){
      _name = value;
   }

   public void setTagclass(String value){
      _tagclass = value;
   }

   public String tagclass(){
      return _tagclass;
   }

}

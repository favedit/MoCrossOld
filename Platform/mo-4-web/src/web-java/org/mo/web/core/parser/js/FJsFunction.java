package org.mo.web.core.parser.js;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

public class FJsFunction
      extends FJsObject
{

   protected String _name;

   protected String _context;

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.append("[name=", _name);
      info.append(",context=", _context, "]");
      return info;
   }

   public FXmlNode makeNode(){
      FXmlNode node = new FXmlNode("Function");
      node.set("name", _name);
      node.set("context", _context);
      return node;
   }

   public String name(){
      return _name;
   }

   public void parse(FString line){
      try{
         _context = line.toString();
         String item[] = _context.split("\n");
         String itemLine = item[0];
         _name = itemLine.substring(itemLine.indexOf(" "), itemLine.indexOf("(")).trim();
      }catch(Exception e){
         throw new FFatalError(e, "Parse function error. (line={0})", line.toString());
      }
   }

   public void setName(String name){
      _name = name;
   }
}

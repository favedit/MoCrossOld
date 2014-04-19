package org.mo.eng.template.tag;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.template.FTemplateContext;

public class FTplUniqueNamesTag
      extends FAbstractTplTag
{
   public static String NAME = "UniqueNames";

   protected String _source;

   @Override
   public void construct(FTemplateContext context){
      _nowrap = "r";
      super.construct(context);
   }

   @Override
   public void onDump(MString dump){
      dump.append(NAME);
      dump.append("[ source=", _source);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      // 获得数据集名称
      FXmlNode formNode = (FXmlNode)_context.parse(_source);
      String name = formNode.get("data_name");
      String alias = formNode.get("data_alias");
      if(!RString.isEmpty(alias)){
         name = alias;
      }
      name = RString.toUpper(name);
      // 获得字段名称
      FAttributes uniqueRs = new FAttributes();
      for(FXmlNode fieldNode : formNode.nodes()){
         String dataName = fieldNode.get("data_name");
         // 获得唯一名称集
         String dataUniques = fieldNode.get("data_uniques");
         if(!RString.isEmpty(dataUniques)){
            dataName = RString.toUpper(dataName);
            for(String unique : RString.split(dataUniques, ',')){
               if(!RString.isBlank(unique)){
                  unique = unique.trim().toUpperCase();
                  String value = uniqueRs.get(unique);
                  if(!RString.isEmpty(value)){
                     uniqueRs.set(unique, value + ", " + dataName);
                  }else{
                     uniqueRs.set(unique, dataName);
                  }
               }
            }
         }
      }
      if(!uniqueRs.isEmpty()){
         String sql = "\n\nALTER TABLE BS_CATALOG_DS\n   ADD CONSTRAINT ";
         for(IStringPair pair : uniqueRs){
            _context.append(sql);
            _context.append(name);
            _context.append("_UK_");
            _context.append(pair.name());
            _context.append("  UNIQUE ( ");
            _context.append(pair.value());
            _context.append(" );");
         }
      }
      return STOP;
   }

   public void setSource(String value){
      _source = value;
   }
}

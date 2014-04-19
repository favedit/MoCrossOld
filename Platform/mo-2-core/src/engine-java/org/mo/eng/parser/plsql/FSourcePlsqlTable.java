package org.mo.eng.parser.plsql;

import org.mo.com.lang.IAttributes;

public class FSourcePlsqlTable
{
   private String _name;

   private FSourcePlsqlTableFields _fields;

   public FSourcePlsqlTableFields fields(){
      if(null == _fields){
         _fields = new FSourcePlsqlTableFields();
      }
      return _fields;
   }

   public void loadConfig(IAttributes attributes){
      _name = attributes.get("table_name");
   }

   public String name(){
      return _name;
   }

   public void setName(String name){
      _name = name;
   }
}

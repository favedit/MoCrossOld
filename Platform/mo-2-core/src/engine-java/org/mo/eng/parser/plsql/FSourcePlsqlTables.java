package org.mo.eng.parser.plsql;

import org.mo.com.lang.FDictionary;

public class FSourcePlsqlTables
      extends FDictionary<FSourcePlsqlTable>
{
   public FSourcePlsqlTables(){
      super(FSourcePlsqlTable.class);
   }

   private String _name;

   private FSourcePlsqlTableFields _fields;

   public FSourcePlsqlTableFields fields(){
      if(null == _fields){
         _fields = new FSourcePlsqlTableFields();
      }
      return _fields;
   }

   public String name(){
      return _name;
   }

   public void setName(String name){
      _name = name;
   }
}

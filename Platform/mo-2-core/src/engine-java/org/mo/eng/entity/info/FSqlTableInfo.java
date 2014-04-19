package org.mo.eng.entity.info;

import org.mo.com.lang.FString;

public class FSqlTableInfo
      implements
         ISqlTableInfo
{
   private String _name = null;

   public FSqlTableInfo(){
   }

   public FString dump(){
      FString dump = new FString();
      dump.append("TableInfo ");
      dump.append("[ name=", _name);
      dump.append(" ]");
      return dump;
   }

   @Override
   public String name(){
      return _name;
   }

   public void setName(String name){
      _name = name;
   }

   @Override
   public String toString(){
      return _name;
   }
}

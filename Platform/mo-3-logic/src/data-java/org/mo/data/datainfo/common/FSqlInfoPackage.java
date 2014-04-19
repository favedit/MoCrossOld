package org.mo.data.datainfo.common;

import org.mo.com.lang.generic.IStringNamed;

public class FSqlInfoPackage
      implements
         IStringNamed
{
   private String _name;

   public FSqlInfoPackage(){
   }

   public String name(){
      return _name;
   }

   public void setName(String name){
      _name = name;
   }
}

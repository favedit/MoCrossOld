package org.mo.data.datainfo.common;

import org.mo.com.lang.generic.IStringNamed;

public class FSqlInfoView
      implements
         IStringNamed
{
   private String _name;

   public FSqlInfoView(){
   }

   public String name(){
      return _name;
   }

   public void setName(String name){
      _name = name;
   }
}

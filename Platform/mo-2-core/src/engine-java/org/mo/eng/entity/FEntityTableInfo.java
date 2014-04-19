package org.mo.eng.entity;

import org.mo.eng.entity.face.ATable;

public class FEntityTableInfo
{
   private final String _name;

   public FEntityTableInfo(ATable table){
      _name = table.name();
   }

   public String name(){
      return _name;
   }
}

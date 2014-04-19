package org.mo.data.datainfo.common;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.IStringNamed;
import org.mo.com.lang.generic.TDumpInfo;

public class FSqlInfoTable
      extends FObject
      implements
         IStringNamed
{
   private String _name;

   public FSqlInfoTable(){
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info);
      info.append(" [name:");
      info.append(_name);
      info.append(" ]");
      return info;
   }

   public String name(){
      return _name;
   }

   public void setName(String name){
      _name = name;
   }
}

package org.mo.eng.list;

import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.eng.data.common.ISqlContext;

public class TListArgs
{
   private String _name;

   private ISqlContext _sqlContext;

   public TListArgs(){
   }

   public TListArgs(String name){
      _name = name;
   }

   public TListArgs(String name,
                    ISqlContext sqlContext){
      _name = name;
      _sqlContext = sqlContext;
   }

   public String dump(){
      TDumpInfo info = new TDumpInfo();
      RDump.dump(info, this);
      info.append("[name=", _name);
      info.append(",sqlContext=", _sqlContext);
      info.append("]");
      return info.toString();
   }

   public String name(){
      return _name;
   }

   public void setName(String name){
      _name = name;
   }

   public void setSqlContext(ISqlContext sqlContext){
      _sqlContext = sqlContext;
   }

   public ISqlContext sqlContext(){
      return _sqlContext;
   }
}

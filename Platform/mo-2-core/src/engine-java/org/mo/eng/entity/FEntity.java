package org.mo.eng.entity;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;

public class FEntity
      extends FObject
      implements
         IEntity
{
   protected FAttributes _attributes;

   protected FObjectDictionary _values = new FObjectDictionary();

   @Override
   public FAttributes attributes(){
      if(_attributes == null){
         _attributes = new FAttributes();
      }
      return _attributes;
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info);
      info.append("Values[");
      _values.dump(info);
      info.append("]");
      if(null != _attributes){
         info.append(" Attributes[");
         _values.dump(info);
         info.append("]");
      }
      return info;
   }

   @Override
   public int ouid(){
      String ouid = (String)_values.get("OUID");
      if(!RString.isEmpty(ouid)){
         return Integer.parseInt(ouid);
      }
      return -1;
   }

   @Override
   public String over(){
      return (String)_values.get("OUID");
   }

   @Override
   public void setOuid(int ouid){
      _values.set("OUID", Integer.toString(ouid));
   }

   @Override
   public String toString(){
      return dump().toString();
   }

   @Override
   public FObjectDictionary values(){
      return _values;
   }
}

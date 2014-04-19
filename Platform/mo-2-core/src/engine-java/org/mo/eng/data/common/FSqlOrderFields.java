package org.mo.eng.data.common;

import org.mo.com.data.FSql;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.IStringPair;

public class FSqlOrderFields
      extends FObjects<FSqlOrderField>
{
   public FSqlOrderFields(){
      super(FSqlOrderField.class);
   }

   public void appendOrder(FAttributes attributes){
      if(null != attributes){
         for(IStringPair pair : attributes){
            push(new FSqlOrderField(pair.name(), pair.value()));
         }
      }
   }

   public void buildOrderSql(FSql sql){
      boolean first = true;
      for(int n = 0; n < _count; n++){
         if(ESqlOrderType.None != _items[n].order()){
            if(first){
               sql.append(" ORDER BY ");
               first = false;
            }else{
               sql.append(",");
            }
            sql.append(_items[n].orderSql());
         }
      }
   }

   public boolean hasOrder(){
      for(int n = 0; n < _count; n++){
         if(ESqlOrderType.None != _items[n].order()){
            return true;
         }
      }
      return false;
   }
}

package org.mo.eng.data.common;

import org.mo.com.data.FSql;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.IAttributes;

public class FSqlSearchFields
      extends FObjects<FSqlSearchField>
{
   public FSqlSearchFields(){
      super(FSqlSearchField.class);
   }

   public void appendSearch(IAttributes attributes){
      //      if(null != attributes){
      //         for(IStringPair pair : attributes){
      //            push(new FSqlSearchField(pair.name(), pair.value()));
      //         }
      //      }
   }

   public void buildSearchSql(FSql sql){
      boolean first = true;
      for(int n = 0; n < _count; n++){
         if(ESqlSearchType.None != _items[n].type()){
            if(first){
               sql.append(" WHERE ");
               first = false;
            }else{
               sql.append(",");
            }
            sql.append(_items[n].searchSql());
         }
      }
   }

   public boolean hasSearch(){
      for(int n = 0; n < _count; n++){
         if(ESqlSearchType.None != _items[n].type()){
            return true;
         }
      }
      return false;
   }
}

package org.mo.com.data;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.generic.MStringNamedObjects;

//============================================================
// <T>数据字段信息列表。</T>
//============================================================
public class FSqlFields
      extends MStringNamedObjects<FSqlField>
{
   //============================================================
   // <T>构造数据字段信息列表。</T>
   //============================================================
   public FSqlFields(){
      super(FSqlField.class);
   }

   //============================================================
   // <T>根据名称获得命名对象。</T>
   //
   // @param name 名称
   // @return 命名对象
   //============================================================
   public FSqlField get(String name){
      for(int n = 0; n < _count; n++){
         FSqlField item = _items[n];
         if(null != item){
            if(item.name().equalsIgnoreCase(name)){
               return item;
            }
         }
      }
      throw new FFatalError("Can't find item by name. (name={1})", name);
   }
}

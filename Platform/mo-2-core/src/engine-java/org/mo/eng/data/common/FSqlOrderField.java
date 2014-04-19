package org.mo.eng.data.common;

import org.mo.com.lang.FString;

public class FSqlOrderField
{
   private String _name;

   private ESqlOrderType _order = ESqlOrderType.None;

   public FSqlOrderField(){
   }

   public FSqlOrderField(String name,
                         ESqlOrderType order){
      _name = name;
      _order = order;
   }

   public FSqlOrderField(String name,
                         String order){
      _name = name;
      setOrder(order);
   }

   public void makeSql(FString sql){
      // 追加字段名称
      sql.append(_name.toUpperCase());
      // 追加条件
      if(ESqlOrderType.Desc == _order){
         sql.append(" DESC");
      }
   }

   public String name(){
      return _name;
   }

   public ESqlOrderType order(){
      return _order;
   }

   public String orderSql(){
      if(ESqlOrderType.Asc == _order){
         return _name + " ASC";
      }else if(ESqlOrderType.Desc == _order){
         return _name + " DESC";
      }else if(ESqlOrderType.Source == _order){
         return order().toString();
      }
      return null;
   }

   public void setName(String name){
      _name = name;
   }

   public void setOrder(ESqlOrderType order){
      _order = order;
   }

   public void setOrder(String order){
      if("N".equals(order)){
         _order = ESqlOrderType.None;
      }else if("A".equals(order)){
         _order = ESqlOrderType.Asc;
      }else if("D".equals(order)){
         _order = ESqlOrderType.Desc;
      }else if("C".equals(order)){
         _order = ESqlOrderType.Source;
      }
   }
}

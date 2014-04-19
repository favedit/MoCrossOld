package org.mo.com.collections;

import java.util.Date;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MAttributes;

//============================================================
// <T>行对象。</T>
//============================================================
@SuppressWarnings("unchecked")
public class FRow
      extends MAttributes
{
   public final static String NAME = "Row";

   //============================================================
   // <T>构造行对象。</T>
   //============================================================
   public FRow(){
   }

   //============================================================
   // <T>构造行对象。</T>
   //
   // @param capacity 容量
   //============================================================
   public FRow(int capacity){
      super(capacity);
   }

   //============================================================
   // <T>构造行对象。</T>
   //
   // @param pack 打包字符串
   //============================================================
   public FRow(String pack){
      super(pack);
   }

   //============================================================
   // <T>根据名称获得整数对象。</T>
   //
   // @param name 名称
   // @return 整数对象
   //============================================================
   public Integer getInteger(String name){
      String value = get(name);
      return RInteger.parse(value);
   }

   //============================================================
   // <T>根据名称设置整数对象。</T>
   //
   // @param name 名称
   // @param value 整数对象
   //============================================================
   public void setInteger(String name,
                          Integer value){
      if(null == value){
         set(name, RString.EMPTY);
      }else{
         set(name, value.toString());
      }
   }

   //============================================================
   // <T>根据名称获得时间对象。</T>
   //
   // @param name 名称
   // @return 时间对象
   //============================================================
   public Date getDate(String name){
      String value = get(name);
      if(null != value){
         return RDateTime.parse(value);
      }
      return null;
   }

   //============================================================
   // <T>根据名称设置时间对象。</T>
   //
   // @param name 名称
   // @param value 时间对象
   //============================================================
   public void setDate(String name,
                       Date value){
      if(null == value){
         set(name, RString.EMPTY);
      }else{
         set(name, RDateTime.format(value));
      }
   }
}

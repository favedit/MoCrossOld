package org.mo.core.aop.descriptor;

import java.lang.reflect.Field;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RConvert;

//============================================================
// <T>AOP关联器。</T>
//============================================================
public class FAopLinker
      extends FObject
{
   // 名称
   private String _name;

   // 类对象
   private Class<?> _type;

   // 字段对象
   private Field _field;

   //============================================================
   // <T>构造AOP关联器。</T>
   //============================================================
   public FAopLinker(){
   }

   //============================================================
   // <T>构造AOP关联器。</T>
   //
   // @param field 字段
   //============================================================
   public FAopLinker(Field field){
      link(field);
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      return _name;
   }

   //============================================================
   // <T>设置名称。</T>
   //
   // @param name 名称
   //============================================================
   public void setName(String name){
      _name = name;
   }

   //============================================================
   // <T>获得类型。</T>
   //
   // @return 类型
   //============================================================
   public Class<?> type(){
      return _type;
   }

   //============================================================
   // <T>获得字段。</T>
   //
   // @return 字段
   //============================================================
   public Field field(){
      return _field;
   }

   //============================================================
   // <T>获得字段名称。</T>
   //
   // @return 字段名称
   //============================================================
   public String fieldName(){
      return _field.getName();
   }

   //============================================================
   // <T>关联字段。</T>
   //
   // @param field 字段
   //============================================================
   public void link(Field field){
      _field = field;
      _type = field.getType();
   }

   //============================================================
   // <T>设置对象和内容。</T>
   //
   // @param item 对象
   // @param value 内容
   //============================================================
   public void set(Object item,
                   Object value){
      if((null != item) && (null != _field)){
         nativeSet(item, RConvert.convert(_type, value));
      }
   }

   //============================================================
   // <T>设置本地对象和内容。</T>
   //
   // @param item 对象
   // @param value 内容
   //============================================================
   public void nativeSet(Object item,
                         Object value){
      if((null != item) && (null != _field)){
         try{
            boolean accessible = _field.isAccessible();
            if(!accessible){
               _field.setAccessible(true);
            }
            _field.set(item, value);
            if(!accessible){
               _field.setAccessible(false);
            }
         }catch(Exception e){
            throw new FFatalError(e, "Set value error.({1}={2})", _name, value);
         }
      }
   }
}

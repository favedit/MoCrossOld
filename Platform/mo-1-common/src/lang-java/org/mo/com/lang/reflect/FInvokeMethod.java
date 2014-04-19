package org.mo.com.lang.reflect;

import java.lang.reflect.Method;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RConvert;

//============================================================
// <T>调用函数对象。</T>
//============================================================
public class FInvokeMethod
      extends FObject
{
   // 名称
   protected String _name;

   // 类型
   protected Class<?> _type;

   // 获得函数对象
   protected Method _getter;

   // 设置函数对象
   protected Method _setter;

   //============================================================
   // <T>构造调用函数对象。</T>
   //============================================================
   public FInvokeMethod(){
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
   // <T>设置类型。</T>
   //
   // @param type 类型
   //============================================================
   public void setType(Class<?> type){
      _type = type;
   }

   //============================================================
   // <T>获得获得函数。</T>
   //
   // @return 获得函数
   //============================================================
   public Method getter(){
      return _getter;
   }

   //============================================================
   // <T>设置获得函数。</T>
   //
   // @param getter 获得函数
   //============================================================
   public void setGetter(Method getter){
      _getter = getter;
   }

   //============================================================
   // <T>获得设置函数。</T>
   //
   // @return 设置函数
   //============================================================
   public Method setter(){
      return _setter;
   }

   //============================================================
   // <T>设置设置函数。</T>
   //
   // @return 设置函数
   //============================================================
   public void setSetter(Method setter){
      _setter = setter;
   }

   //============================================================
   // <T>获得内容。</T>
   //
   // @param item 对象
   // @return 设置函数
   //============================================================
   public Object get(Object item){
      if((null != item) && (null != _getter)){
         try{
            Object value = _getter.invoke(item);
            if(null != value){
               return RConvert.convert(_type, value);
            }
         }catch(Exception e){
            throw new FFatalError(e, "Get value error. (type={1}, method={2}, item={3})", _type, _getter, item);
         }
      }
      return null;
   }

   //============================================================
   // <T>设置内容。</T>
   //
   // @param item 对象
   // @param value 内容
   //============================================================
   public void set(Object item,
                   Object value){
      if((null != item) && (null != _setter)){
         try{
            Object param = null;
            if(null != value){
               param = RConvert.convert(_type, value);
            }
            _setter.invoke(item, param);
         }catch(Exception e){
            throw new FFatalError(e, "Set value error.(name={1}, value={2})", _name, value);
         }
      }
   }

   //============================================================
   // <T>直接获得内容。</T>
   //
   // @param item 对象
   // @return 设置函数
   //============================================================
   public Object directGet(Object item){
      if((null != item) && (null != _getter)){
         try{
            return _getter.invoke(item);
         }catch(Exception e){
            throw new FFatalError(e);
         }
      }
      return null;
   }

   //============================================================
   // <T>直接设置内容。</T>
   //
   // @param item 对象
   // @param value 内容
   //============================================================
   public void directSet(Object item,
                         Object value){
      if((null != item) && (null != _setter)){
         try{
            _setter.invoke(item, value);
         }catch(Exception e){
            throw new FFatalError(e, "Set value error.(name={1}, value={2})", _name, value);
         }
      }
   }
}

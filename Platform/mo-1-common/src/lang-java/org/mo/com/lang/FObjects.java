package org.mo.com.lang;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.mo.com.lang.generic.MObjects;

//============================================================
// <T>对象集合。</T>
//============================================================
public class FObjects<V>
      extends MObjects<V>
{
   //============================================================
   // <T>构造对象集合。</T>
   //============================================================
   @SuppressWarnings("unchecked")
   public FObjects(){
      ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
      Type[] argumentsTypes = type.getActualTypeArguments();
      if(1 != argumentsTypes.length){
         throw new FFatalError("Invalid arguments types (count={0})", argumentsTypes.length);
      }
      _clazz = (Class<V>)argumentsTypes[0];
      ensureSize(CAPACITY);
   }

   //============================================================
   // <T>构造对象集合。</T>
   //
   // @param clazz 类对象
   //============================================================
   public FObjects(Class<V> clazz){
      super(clazz);
   }

   //============================================================
   // <T>构造对象集合。</T>
   //
   // @param clazz 类对象
   // @param capacity 容量
   //============================================================
   public FObjects(Class<V> clazz,
                   int capacity){
      super(clazz, capacity);
   }
}

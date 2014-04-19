package org.mo.com.lang;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.mo.com.lang.generic.MDictionary;

//============================================================
// <T>字典。</T>
// <P>名称为字符串，内容为任意对象。</P>
//============================================================
public class FDictionary<V>
      extends MDictionary<INamePair<V>, V>
{
   //============================================================
   // <T>构造字典。</T>
   //============================================================
   @SuppressWarnings("unchecked")
   public FDictionary(){
      ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
      Type[] argumentsTypes = type.getActualTypeArguments();
      if(1 == argumentsTypes.length){
         innerConstruct(String.class, (Class<V>)argumentsTypes[0], RObject.CAPACITY);
      }
   }

   //============================================================
   // <T>构造字典。</T>
   //
   // @param clazz 类对象
   //============================================================
   public FDictionary(Class<V> clazz){
      super(clazz);
   }

   //============================================================
   // <T>构造字典。</T>
   //
   // @param clazz 类对象
   // @param capacity 容量
   //============================================================
   public FDictionary(Class<V> clazz,
                      int capacity){
      super(clazz, capacity);
   }
}

/*
 * @(#)FMap.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.com.lang;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.mo.com.lang.generic.IPair;
import org.mo.com.lang.generic.MMap;

//============================================================
// <T>哈希集合。</T>
//
// @type N 名称类型
// @type V 内容类型
// @history 050218 创建
//============================================================
public class FMap<N, V>
      extends MMap<IPair<N, V>, N, V>
{
   //============================================================
   // <T>构造哈希集合。</T>
   //============================================================
   @SuppressWarnings("unchecked")
   public FMap(){
      ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
      Type[] argumentsTypes = type.getActualTypeArguments();
      if(2 == argumentsTypes.length){
         innerConstruct((Class<N>)argumentsTypes[0], (Class<V>)argumentsTypes[1], RObject.CAPACITY);
      }
   }

   //============================================================
   // <T>构造哈希集合。</T>
   //
   // @param nameClass 名称类对象
   // @param valueClass 内容类对象
   //============================================================
   public FMap(Class<N> nameClass,
               Class<V> valueClass){
      super(nameClass, valueClass);
   }

   //============================================================
   // <T>构造哈希集合。</T>
   //
   // @param nameClass 名称类对象
   // @param valueClass 内容类对象
   // @param capacity 容量
   //============================================================
   public FMap(Class<N> nameClass,
               Class<V> valueClass,
               int capacity){
      super(nameClass, valueClass, capacity);
   }
}

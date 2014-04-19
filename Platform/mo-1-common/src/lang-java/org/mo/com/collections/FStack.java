package org.mo.com.collections;

import org.mo.com.lang.generic.MObjects;

//============================================================
// <T>堆栈集合。</T>
//
// @history MAOCY 051118 创建 
//============================================================
public abstract class FStack<V>
      extends MObjects<V>
{
   //============================================================
   // <T>创建对象堆栈的实例。</T>
   //
   // @param clazz 类对象 
   //============================================================
   public FStack(Class<V> clazz){
      super(clazz);
   }

   //============================================================
   // <T>创建对象堆栈的实例。</T>
   //
   // @param clazz 类对象 
   // @param capacity 容量
   //============================================================
   public FStack(Class<V> clazz,
                 int capacity){
      super(clazz, capacity);
   }

   //============================================================
   // <T>压入首对象。</T>
   //
   // @param clazz 类对象 
   // @param capacity 容量
   //============================================================
   @SuppressWarnings("unchecked")
   public void unshift(V... items){
      for(int n = items.length - 1; n >= 0; n--){
         insert(items[n]);
      }
   }

   //============================================================
   // <T>弹出首对象。</T>
   //
   // @param clazz 类对象 
   // @param capacity 容量
   //============================================================
   public V shift(){
      return erase(0);
   }
}

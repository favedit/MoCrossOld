package org.mo.com.lang.generic;

import org.mo.com.lang.FFatalError;

//============================================================
// <T>命名对象集合。</T>
//============================================================
public abstract class MNamedObjects<T extends INamed<N>, N>
      extends MObjects<T>
{
   //============================================================
   // <T>构造命名对象集合。</T>
   //============================================================
   public MNamedObjects(Class<T> clazz){
      super(clazz);
   }

   //============================================================
   // <T>根据名称获得命名对象。</T>
   //
   // @param name 名称
   // @return 命名对象
   //============================================================
   public T find(N name){
      for(int n = 0; n < _count; n++){
         T item = _items[n];
         if(null != item){
            if(item.name().equals(name)){
               return item;
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>根据名称获得命名对象。</T>
   //
   // @param name 名称
   // @return 命名对象
   //============================================================
   public T get(N name){
      for(int n = 0; n < _count; n++){
         T item = _items[n];
         if(null != item){
            if(item.name().equals(name)){
               return item;
            }
         }
      }
      throw new FFatalError("Can't find item by name. (name={1})", name);
   }
}

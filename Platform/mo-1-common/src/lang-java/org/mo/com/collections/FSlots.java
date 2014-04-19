package org.mo.com.collections;

import org.mo.com.lang.generic.MObjects;

//============================================================
// <T>插槽集合。</T>
//============================================================
public class FSlots<V>
      extends MObjects<V>
{
   //============================================================
   // <T>构造插槽集合。</T>
   //
   // @param clazz 类对象
   //============================================================
   public FSlots(Class<V> clazz){
      super(clazz);
   }

   //============================================================
   // <T>构造插槽集合。</T>
   //
   // @param clazz 类对象
   // @param capacity 容量
   //============================================================
   public FSlots(Class<V> clazz,
                 int capacity){
      super(clazz, capacity);
   }

   //============================================================
   // <T>放入插槽。</T>
   //
   // @param value 内容
   //============================================================
   public void pushSlot(V value){
      for(int n = 0; n < _count; n++){
         if(null == _items[n]){
            _items[n] = value;
            return;
         }
      }
      push(value);
   }

   //============================================================
   // <T>从插槽中移除第一个非空内容。</T>
   //
   // @return 非空内容
   //============================================================
   public V removeFirstNotNull(){
      V item = null;
      for(int n = 0; n < _count; n++){
         item = _items[n];
         if(null != item){
            _items[n] = null;
            break;
         }
      }
      return item;
   }
}

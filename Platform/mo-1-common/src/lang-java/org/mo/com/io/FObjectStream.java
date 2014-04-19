package org.mo.com.io;

import org.mo.com.lang.FObject;

public class FObjectStream<V>
      extends FObject
{
   /**
    * <p>创建对象内存管理类实例</p>
    * 
    * @param clazz 子项类的类型
    */
   public FObjectStream(Class<V> clazz){
   }

   /**
    * <p>创建对象内存管理类实例</p>
    *
    * @param clazz 子项类的类型
    * @param capacity 最小收集内存大小
    */
   public FObjectStream(Class<V> clazz,
                        int capacity){
   }
}

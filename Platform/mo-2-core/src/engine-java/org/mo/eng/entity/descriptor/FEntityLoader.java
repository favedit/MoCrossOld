package org.mo.eng.entity.descriptor;

import org.mo.com.collections.FObjectDictionary;

public class FEntityLoader
      extends ClassLoader
{
   private final FObjectDictionary _classes = new FObjectDictionary();

   public FEntityLoader(Object parent){
      super(parent.getClass().getClassLoader());
   }

   public synchronized Class<?> buildClass(String name,
                                           byte[] data){
      Class<?> clazz = (Class<?>)_classes.get(name);
      if(null == clazz){
         clazz = defineClass(name, data, 0, data.length);
         _classes.set(name, clazz);
      }
      return clazz;
   }
}

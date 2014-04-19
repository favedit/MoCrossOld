package org.mo.eng.entity;

import org.mo.com.lang.FObjects;

public class FEntities<V>
      extends FObjects<V>
      implements
         IEntities<V>
{
   public FEntities(Class<V> clazz){
      super(clazz);
   }
}

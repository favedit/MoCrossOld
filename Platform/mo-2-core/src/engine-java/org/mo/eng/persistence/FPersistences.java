package org.mo.eng.persistence;

import org.mo.com.lang.FDictionary;

public class FPersistences
      extends FDictionary<IPersistence>
{
   public FPersistences(){
      super(IPersistence.class);
   }
}

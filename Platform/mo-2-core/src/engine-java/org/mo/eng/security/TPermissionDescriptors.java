package org.mo.eng.security;

import org.mo.com.lang.FDictionary;

public class TPermissionDescriptors
      extends FDictionary<TPermissionDescriptor>
{
   private int _invalidDescriptorCount;

   public int invalidDescriptorCount(){
      return _invalidDescriptorCount;
   }

   public void setInvalidDescriptorCount(int _invalidDescriptorCount){
      this._invalidDescriptorCount = _invalidDescriptorCount;
   }
}

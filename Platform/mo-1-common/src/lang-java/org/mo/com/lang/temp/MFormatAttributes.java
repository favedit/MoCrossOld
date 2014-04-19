package org.mo.com.lang.temp;

import org.mo.com.lang.IDisplayFormator;
import org.mo.com.lang.generic.MAttributes;

@SuppressWarnings("unchecked")
public class MFormatAttributes
      extends MAttributes
{
   private IDisplayFormator _formator;

   public IDisplayFormator formator(){
      return _formator;
   }

   public void setFormator(IDisplayFormator formator){
      _formator = formator;
   }

   @Override
   public void setValue(int index,
                        String value){
      if(value != null && _formator != null){
         value = _formator.formatValue(value);
      }
      super.setValue(index, value);
   }

   @Override
   public void set(String name,
                   String value){
      if(value != null && _formator != null){
         value = _formator.formatValue(value);
      }
      super.set(name, value);
   }

   @Override
   public String get(String name){
      String value = super.get(name);
      if(value != null && _formator != null){
         return _formator.formatDisplay(value);
      }
      return value;
   }
}

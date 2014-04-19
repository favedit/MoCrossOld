package org.mo.com.xml.persistent;

import java.lang.reflect.Field;
import org.mo.com.lang.FFatalError;

public class FXmlField
{
   @SuppressWarnings("unused")
   private FXmlClass _class;

   private Field _field;

   private String _name;

   public FXmlField(FXmlClass clazz,
                    Field field){
      _class = clazz;
      load(field);
   }

   public Object get(Object item){
      try{
         return _field.get(item);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void load(Field field){
      _name = field.getName();
      _field = field;
      _field.setAccessible(true);
   }

   public String name(){
      return _name;
   }

   public void set(Object item,
                   Object value){
      try{
         _field.set(item, value);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void setBase(String base){
      _name += "@" + base;
   }
}

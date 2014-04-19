package org.mt.core.util;

import java.lang.reflect.Field;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.face.AName;
import org.mo.com.lang.reflect.RClass;

public class FObjectAttributes2
      extends FAttributes
{
   public FObjectAttributes2(){
   }

   public FObjectAttributes2(Object item){
      loadObject(item);
   }

   public void loadObject(Object item){
      Class<?> clazz = item.getClass();
      Field[] fields = clazz.getDeclaredFields();
      for(Field field : fields){
         AName annoName = field.getAnnotation(AName.class);
         if(annoName != null){
            String name = annoName.value();
            String value = (String)RClass.invokeGet(item, name);
            set(name, value);
         }
      }
   }
}

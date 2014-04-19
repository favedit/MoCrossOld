package org.mo.core.convert;

import java.util.Date;
import org.mo.com.lang.FString;
import org.mo.com.lang.INameValue;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.FGetDescriptor;
import org.mo.com.lang.reflect.FPropertyDescriptor;
import org.mo.com.lang.reflect.RDescriptor;

public class RProperty
{

   public static final Class<?>[] CON_CLASSES = {Boolean.class, Character.class, Short.class, Integer.class, Float.class, Date.class, String.class, FString.class};

   public static boolean isDeep(Object container){
      if(null != container){
         Class<?> clazz = container.getClass();
         for(Class<?> cls : CON_CLASSES){
            if(clazz == cls){
               return false;
            }
         }
         return true;
      }
      return false;
   }

   @SuppressWarnings({"rawtypes", "unchecked"})
   public static void setValue(Object container,
                               String name,
                               Object value){
      if(null != container){
         if(container instanceof INameValue){
            ((INameValue)container).set(name, value);
         }else{
            if(isDeep(container)){
               FPropertyDescriptor dsp = RDescriptor.findProperty(container.getClass());
               if(null != dsp){
                  dsp.set(container, name, value);
               }
            }
         }
      }
   }

   @SuppressWarnings({"rawtypes", "unchecked"})
   public static Object value(Object container,
                              String name){
      Object result = null;
      if(null != container){
         if(container instanceof INameValue){
            result = ((INameValue)container).get(name);
         }else{
            if(isDeep(container)){
               FGetDescriptor dsp = RDescriptor.find(FGetDescriptor.class, container.getClass());
               if(null != dsp){
                  result = dsp.get(container, name);
               }
            }
         }
      }
      return result;
   }

   public static String valueAsString(Object container,
                                      String name){
      return RString.toString(value(container, name));
   }

}

package org.mo.core.convert;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IObjects;
import org.mo.com.lang.reflect.IDescriptor;

public class FConvertConsole
      implements
         IConvertConsole
{

   public Object[] convetToArray(Object item){
      if(item instanceof List){
         return ((List<?>)item).toArray();
      }else if(item instanceof Set){
         return ((Set<?>)item).toArray();
      }else if(item instanceof IObjects<?>){
         return ((IObjects<?>)item).toObjects();
      }
      return null;
   }

   public IDescriptor findDescriptor(Object item){
      //return _descriptorConsole.findDescriptor(item);
      return null;
   }

   public Object proxy(Class<?> clazz,
                       Method method,
                       FAttributes attributes,
                       String[] names,
                       Object[] values){
      try{
         Object result = method.getReturnType().newInstance();
         //System.out.println(clazz + " " + method);
         return result;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

}

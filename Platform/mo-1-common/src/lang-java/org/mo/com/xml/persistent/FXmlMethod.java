package org.mo.com.xml.persistent;

import java.lang.reflect.Method;
import org.mo.com.lang.FFatalError;
import org.mo.com.xml.FXmlNode;

public class FXmlMethod
{
   @SuppressWarnings("unused")
   private FXmlClass _class;

   private Method _method;

   private String _name;

   public FXmlMethod(FXmlClass clazz,
                     Method method){
      _class = clazz;
      load(method);
   }

   public Object invoke(Object item,
                        FXmlNode config){
      try{
         return _method.invoke(item, config);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void load(Method method){
      _name = method.getName();
      _method = method;
      _method.setAccessible(true);
   }

   public String name(){
      return _name;
   }
}

package org.mo.web.core.servlet.common;

import org.mo.web.core.container.AContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.mo.com.lang.FFatalError;

public class FServletMethodDescriptor
{

   private AContainer[] _forms;

   private Method _method;

   private Class<?>[] _types;

   public FServletMethodDescriptor(Method method){
      _method = method;
      build();
   }

   protected void build(){
      _types = _method.getParameterTypes();
      _forms = new AContainer[_types.length];
      Annotation[][] annos = _method.getParameterAnnotations();
      for(int n = 0; n < _types.length; n++){
         for(Annotation anno : annos[n]){
            if(anno instanceof AContainer){
               _forms[n] = (AContainer)anno;
               break;
            }
         }
      }
   }

   public AContainer[] forms(){
      return _forms;
   }

   public String invoke(Object action,
                        Object[] params){
      try{
         return (String)_method.invoke(action, params);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public Class<?>[] types(){
      return _types;
   }

}

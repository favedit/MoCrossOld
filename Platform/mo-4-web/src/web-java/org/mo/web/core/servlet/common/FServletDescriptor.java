package org.mo.web.core.servlet.common;

import org.mo.com.lang.FDictionary;

public class FServletDescriptor
{

   private FDictionary<FServletMethodDescriptor> _methods = new FDictionary<FServletMethodDescriptor>(FServletMethodDescriptor.class);

   public boolean contains(String name){
      return _methods.contains(name);
   }

   public FServletMethodDescriptor find(String method){
      return _methods.get(method);
   }

   public void push(String method,
                    FServletMethodDescriptor methodDsp){
      _methods.set(method, methodDsp);
   }

}

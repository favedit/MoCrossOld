package org.mo.web.core.parser.js;

import org.mo.com.lang.FDictionary;

public class FJsMethods
      extends FDictionary<FJsMethod>
{

   public void push(FJsMethod method){
      set(method.name(), method);
   }
}

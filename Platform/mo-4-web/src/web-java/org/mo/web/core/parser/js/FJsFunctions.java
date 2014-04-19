package org.mo.web.core.parser.js;

import org.mo.com.lang.FDictionary;

public class FJsFunctions
      extends FDictionary<FJsFunction>
{

   public void push(FJsFunction fun){
      set(fun.name(), fun);
   }
}

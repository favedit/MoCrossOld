package org.mo.web.core.parser.js;

import org.mo.com.lang.FDictionary;

public class FJsClasses
      extends FDictionary<FJsClass>
{

   public void push(FJsClass clazz){
      set(clazz.name(), clazz);
   }

}

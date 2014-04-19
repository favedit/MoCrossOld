package org.mo.web.core.parser.js;

import org.mo.com.lang.FDictionary;

public class FJsXmlClasses
      extends FDictionary<FJsXmlClass>
{

   public void push(FJsXmlClass clazz){
      set(clazz.name(), clazz);
   }
}

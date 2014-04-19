package org.mo.web.core.parser.js;

import org.mo.com.lang.FDictionary;

public class FJsAttributes
      extends FDictionary<FJsAttribute>
{

   public void push(FJsAttribute attribute){
      set(attribute.name(), attribute);
   }
}

package org.mo.web.core.parser.js;

import org.mo.com.lang.FDictionary;

public class FJsProperties
      extends FDictionary<FJsProperty>
{

   public void push(FJsProperty property){
      set(property.name(), property);
   }
}

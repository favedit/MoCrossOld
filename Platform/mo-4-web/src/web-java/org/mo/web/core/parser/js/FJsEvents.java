package org.mo.web.core.parser.js;

import org.mo.com.lang.FDictionary;

public class FJsEvents
      extends FDictionary<FJsEvent>
{

   public void push(FJsEvent event){
      set(event.name(), event);
   }
}

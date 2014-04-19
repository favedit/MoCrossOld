package org.mo.web.core.parser.js;

import org.mo.com.lang.FDictionary;

public class FJsEmpties
      extends FDictionary<FJsEmpty>
{

   public void push(FJsEmpty empty){
      set(empty.name(), empty);
   }
}

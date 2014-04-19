package org.mo.web.core.parser.js;

import org.mo.com.lang.FDictionary;

public class FJsStyles
      extends FDictionary<FJsStyle>
{

   public void push(FJsStyle style){
      set(style.name(), style);
   }
}

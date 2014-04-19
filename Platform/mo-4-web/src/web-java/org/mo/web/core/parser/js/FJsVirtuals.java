package org.mo.web.core.parser.js;

import org.mo.com.lang.FDictionary;

public class FJsVirtuals
      extends FDictionary<FJsVirtual>
{

   public void push(FJsVirtual virtual){
      set(virtual.name(), virtual);
   }
}

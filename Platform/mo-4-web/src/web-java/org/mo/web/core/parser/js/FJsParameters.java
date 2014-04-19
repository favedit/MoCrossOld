package org.mo.web.core.parser.js;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;

public class FJsParameters
      extends FDictionary<FJsParameter>
{

   public void push(FJsParameter parameter){
      String name = parameter.name();
      if(RString.isEmpty(name)){
         throw new FFatalError("Parameter name is null. (parameter={0})", parameter.dump());
      }
      set(name, parameter);
   }
}

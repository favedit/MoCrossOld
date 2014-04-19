package org.mo.eng.validator.common;

import org.mo.com.lang.FError;
import org.mo.com.lang.RFloat;
import org.mo.com.lang.RString;
import org.mo.core.context.IContext;

public class FFloatValidator
{
   public static void checkFloat(IContext iContext,
                                 String sValue,
                                 String sDisplay) throws FError{
      boolean bResult = true;
      if(!RString.isEmpty(sValue)){
         if(!RFloat.isFloat(sValue)){
            bResult = false;
         }
      }
      if(!bResult){
         //         String sMessage = FTranslateManager.console().translate(iContext,
         //               FStringValidator.class,
         //               "trs:this|check.float|{0} is not float({1}).", sDisplay, sValue);
         //         iContext.messages().push(new FValidMessage(sMessage));
      }
   }
}

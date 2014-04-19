package org.mo.eng.validator.common;

import org.mo.com.lang.FError;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.core.context.IContext;

public class FDateValidator
{
   public static void checkDate(IContext iContext,
                                String sValue,
                                String sFormat,
                                String sDisplay) throws FError{
      boolean bResult = true;
      if(!RString.isEmpty(sValue)){
         if(!RDateTime.isDate(sValue, sFormat)){
            bResult = false;
         }
      }
      if(!bResult){
         //         String sMessage = FTranslateManager.console().translate(iContext,
         //               FStringValidator.class,
         //               "trs:this|check.ansi|Item{0} is not date({1}).", sDisplay,
         //               sFormat);
         //         iContext.messages().push(new FValidMessage(sMessage));
      }
   }
}

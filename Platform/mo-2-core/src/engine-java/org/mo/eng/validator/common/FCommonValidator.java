package org.mo.eng.validator.common;

import org.mo.com.lang.FError;
import org.mo.com.lang.FString;
import org.mo.core.context.IContext;

public class FCommonValidator
{
   public static void checkNotNull(IContext iContext,
                                   Object oValue,
                                   String sDisplay) throws FError{
      boolean bResult = false;
      if(oValue == null){
         bResult = true;
      }else if(oValue instanceof String){
         if(((String)oValue).trim().length() == 0){
            bResult = true;
         }
      }else if(oValue instanceof FString){
         if(((FString)oValue).length() == 0){
            bResult = true;
         }
      }
      if(bResult){
         //         String sMessage = FTranslateManager.console().translate(iContext,
         //               FCommonValidator.class,
         //               "trs:this|notnull|Item{0} can't be null.", sDisplay);
         //         iContext.messages().push(new FValidMessage(sMessage));
      }
   }
}

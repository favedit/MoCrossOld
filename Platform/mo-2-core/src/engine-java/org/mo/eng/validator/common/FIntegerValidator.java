package org.mo.eng.validator.common;

import org.mo.com.lang.FError;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.core.context.IContext;

public class FIntegerValidator
{
   public static void checkInteger(IContext iContext,
                                   String sValue,
                                   String sDisplay) throws FError{
      boolean bResult = true;
      if(!RString.isEmpty(sValue)){
         if(!RInteger.isInteger(sValue)){
            bResult = false;
         }
      }
      if(!bResult){
         //         String sMessage = FTranslateManager.console().translate(iContext,
         //               FStringValidator.class,
         //               "trs:this|check.integer|{0} is not integer({1}).", sDisplay,
         //               sValue);
         //         iContext.messages().push(new FValidMessage(sMessage));
      }
   }

   public static void checkLength(IContext iContext,
                                  String sValue,
                                  String sDisplay,
                                  int nMinLength,
                                  boolean bMinLengthEq,
                                  int nMaxLength,
                                  boolean bMaxLengthEq) throws FError{
      //      String sMessage = null;
      //      if (!RString.isEmpty(sValue)) {
      //         int nLength = sValue.length();
      //         if (bMinLengthEq && (nLength < nMinLength)) {
      //            sMessage = FTranslateManager
      //                  .console()
      //                  .translate(
      //                        iContext,
      //                        FStringValidator.class,
      //                        "trs:this|check.length|{0}'s length is small or eq than {1}.",
      //                        sDisplay, Integer.toString(nMinLength));
      //         } else if (!bMinLengthEq && (nLength <= nMinLength)) {
      //            sMessage = FTranslateManager.console().translate(iContext,
      //                  FStringValidator.class,
      //                  "trs:this|check.length|{0}'s length is small than {1}.",
      //                  sDisplay, Integer.toString(nMinLength));
      //         }
      //         if (bMaxLengthEq && (nLength > nMaxLength)) {
      //            sMessage = FTranslateManager
      //                  .console()
      //                  .translate(
      //                        iContext,
      //                        FStringValidator.class,
      //                        "trs:this|check.length|{0}'s length is larger or eq than {1}.",
      //                        sDisplay, Integer.toString(nMinLength));
      //         } else if (!bMaxLengthEq && (nLength >= nMaxLength)) {
      //            sMessage = FTranslateManager.console().translate(iContext,
      //                  FStringValidator.class,
      //                  "trs:this|check.length|{0}'s length is larger than {1}.",
      //                  sDisplay, Integer.toString(nMinLength));
      //         }
      //      }
      //      if (sMessage != null) {
      //         iContext.messages().push(new FValidMessage(sMessage));
      //      }
   }

   public static void checkLength(IContext iContext,
                                  String sValue,
                                  String sMinLength,
                                  String sMinLengthEq,
                                  String sMaxLength,
                                  String sMaxLengthEq,
                                  String sDisplay) throws FError{
      checkLength(iContext, sValue, sDisplay, RInteger.parse(sMinLength), RBoolean.parse(sMinLengthEq), RInteger.parse(sMaxLength), RBoolean.parse(sMaxLengthEq));
   }

   public static void checkValue(IContext iContext,
                                 String sValue,
                                 String sDisplay,
                                 int nMinValue,
                                 boolean bMinValueEq,
                                 int nMaxValue,
                                 boolean bMaxValueEq) throws FError{
      //      String sMessage = null;
      //      if (!RString.isEmpty(sValue)) {
      //         int nValue = RInteger.parse(sValue);
      //         if (bMinValueEq && (nValue < nMinValue)) {
      //            sMessage = FTranslateManager.console().translate(iContext,
      //                  FStringValidator.class,
      //                  "trs:this|check.value|{0}'s value is small or eq than {1}.",
      //                  sDisplay, Integer.toString(nMinValue));
      //         } else if (!bMinValueEq && (nValue <= nMinValue)) {
      //            sMessage = FTranslateManager.console().translate(iContext,
      //                  FStringValidator.class,
      //                  "trs:this|check.value|{0}'s value is small than {1}.",
      //                  sDisplay, Integer.toString(nMinValue));
      //         }
      //         if (bMaxValueEq && (nValue > nMinValue)) {
      //            sMessage = FTranslateManager.console().translate(iContext,
      //                  FStringValidator.class,
      //                  "trs:this|check.value|{0}'s value is larger or eq than {1}.",
      //                  sDisplay, Integer.toString(nMaxValue));
      //         } else if (!bMaxValueEq && (nValue >= nMaxValue)) {
      //            sMessage = FTranslateManager.console().translate(iContext,
      //                  FStringValidator.class,
      //                  "trs:this|check.value|{0}'s value is larger than {1}.",
      //                  sDisplay, Integer.toString(nMaxValue));
      //         }
      //      }
      //      if (sMessage != null) {
      //         iContext.messages().push(new FValidMessage(sMessage));
      //      }
   }

   public static void checkValue(IContext iContext,
                                 String sValue,
                                 String sMinLength,
                                 String sMinLengthEq,
                                 String sMaxLength,
                                 String sMaxLengthEq,
                                 String sDisplay) throws FError{
      checkValue(iContext, sValue, sDisplay, RInteger.parse(sMinLength), RBoolean.parse(sMinLengthEq), RInteger.parse(sMaxLength), RBoolean.parse(sMaxLengthEq));
   }
}

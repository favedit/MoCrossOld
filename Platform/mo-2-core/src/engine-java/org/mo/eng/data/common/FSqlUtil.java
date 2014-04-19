package org.mo.eng.data.common;

public class FSqlUtil
{
   //   public static void parseException(FMessages oMessages,
   //                                     SQLException oException)
   //         throws FException{
   //      if(oMessages == null){
   //         return;
   //      }
   //      String sMessage = oException.getMessage();
   //      if(sMessage.indexOf("BEGIN[") != -1 && sMessage.indexOf("]END") != -1){
   //         FStringList oParams = new FStringList();
   //         oParams.unpack(new FString(FStringUtil.subString(sMessage, "BEGIN[",
   //               "]END")));
   //         if(!FStringUtil.isEmpty(oParams.value("M"))){
   //            FErrorMessage oError = new FErrorMessage(oParams.value("M"));
   //            oMessages.push(oError);
   //         }else{
   //            FErrorMessage oError = new FErrorMessage(oParams.value("C"),
   //                  oParams.value("1"), oParams.value("2"), oParams.value("3"),
   //                  oParams.value("4"), oParams.value("5"));
   //            oMessages.push(oError);
   //         }
   //      }else{
   //         oMessages.push(new FErrorMessage(sMessage));
   //      }
   //   }
   //
   //   public static FMessage parseException(SQLException oException)
   //         throws FException{
   //      FMessage oMessage = null;
   //      String sMessage = oException.getMessage();
   //      if(sMessage.indexOf("BEGIN[") != -1 && sMessage.indexOf("]END") != -1){
   //         FStringList oParams = new FStringList();
   //         oParams.unpack(new FString(FStringUtil.subString(sMessage, "BEGIN[",
   //               "]END")));
   //         if(!FStringUtil.isEmpty(oParams.value("M"))){
   //            oMessage = new FErrorMessage(oParams.value("M"));
   //         }else{
   //            oMessage = new FErrorMessage(oParams.value("C"),
   //                  oParams.value("1"), oParams.value("2"), oParams.value("3"),
   //                  oParams.value("4"), oParams.value("5"));
   //         }
   //      }else{
   //         oMessage = new FFatalMessage(sMessage);
   //      }
   //      oMessage.setDescription(FMessageUtil.buildDescription(oException)
   //            .toString());
   //      return oMessage;
   //   }
   //   public static FMessage parseException(String sMessage)
   //         throws FException{
   //      FMessage oMessage = null;
   //      if(sMessage.indexOf("BEGIN[") != -1 && sMessage.indexOf("]END") != -1){
   //         FStringList oParams = new FStringList();
   //         oParams.unpack(new FString(FStringUtil.subString(sMessage, "BEGIN[", "]END")));
   //         if(!FStringUtil.isEmpty(oParams.value("M"))){
   //            oMessage = new FErrorMessage(oParams.value("M"));
   //         }else{
   //            oMessage = new FErrorMessage(oParams.value("C"), oParams.value("1"),
   //                  oParams.value("2"), oParams.value("3"), oParams.value("4"), oParams.value("5"));
   //         }
   //      }else{
   //         oMessage = new FFatalMessage(sMessage);
   //      }
   //      return oMessage;
   //   }
}

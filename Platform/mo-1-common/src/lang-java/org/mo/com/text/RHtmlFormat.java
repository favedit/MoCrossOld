package org.mo.com.text;

import org.mo.com.lang.FString;
import org.mo.com.lang.RString;

public class RHtmlFormat
{
   // ============================================================
   // CY.MAO      20030215        Created
   public final static FString textToHtml(FString sValue){
      FString sHtml = new FString();
      if(sValue != null){
         char ch = 0;
         int nLength = sValue.length();
         char[] arMemory = sValue.memory();
         for(int n = 0; n < nLength; n++){
            ch = arMemory[n];
            if(ch == '&'){
               sHtml.append("&amp;");
            }else if(ch == '"'){
               sHtml.append("&quot;");
            }else if(ch == '<'){
               sHtml.append("&lt;");
            }else if(ch == '>'){
               sHtml.append("&gt;");
            }else if(ch == '\n'){
               sHtml.append("<br>");
            }else if(ch == '\t'){
               sHtml.append("&nbsp;&nbsp;&nbsp;");
            }else if(ch == ' '){
               sHtml.append("&nbsp;");
            }else{
               sHtml.append(ch);
            }
         }
      }
      return sHtml;
   }

   public final static FString textToHtml(String sValue){
      return textToHtml(new FString(sValue));
   }

   // ============================================================
   // CY.MAO      20030215        Created
   public final static FString textToInput(FString sValue){
      FString sHtml = new FString();
      if(sValue != null){
         char ch = 0;
         int nLength = sValue.length();
         char[] arMemory = sValue.memory();
         for(int n = 0; n < nLength; n++){
            ch = arMemory[n];
            if(ch == '"'){
               sHtml.append("&quot;");
            }else{
               sHtml.append(ch);
            }
         }
      }
      return sHtml;
   }

   public final static String textToInput(String sValue){
      return textToInput(new FString(sValue)).toString();
   }

   // ============================================================
   // CY.MAO      20030223        Created
   public final static String textToScript(String sValue){
      sValue = RString.replace(sValue, "\"", "\\\"");
      return sValue;
   }
}

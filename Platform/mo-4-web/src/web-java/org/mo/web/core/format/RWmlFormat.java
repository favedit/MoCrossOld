package org.mo.web.core.format;

import org.mo.com.lang.FString;

public class RWmlFormat
{

   public static String WML_BEGIN = "<?xml version='1.0'?>\n" + "<!DOCTYPE wml PUBLIC '-//WAPFORUM//DTD WML 1.1//EN' 'http://www.wapforum.org/DTD/wml_1.1.xml'>\n" + "<wml>\n";

   public static String WML_END = "</wml>";

   // CY.MAO 20040621 Created
   public final static FString formatWml(String sValue){
      char ch = 0;
      int nLength = sValue.length();
      FString sWml = new FString(nLength);
      char[] chBuf = sValue.toCharArray();
      for(int n = 0; n < nLength; n++){
         ch = chBuf[n];
         if(ch == '&'){
            sWml.append("&amp;");
         }else if(ch == '"'){
            sWml.append("&quot;");
         }else if(ch == '<'){
            sWml.append("&lt;");
         }else if(ch == '>'){
            sWml.append("&gt;");
         }else if(ch == '$'){
            sWml.append("$$");
         }else if(ch == ' '){
            sWml.append("&nbsp;");
         }else{
            sWml.append(ch);
         }
      }
      return sWml;
   }
}

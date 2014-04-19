package org.mo.eng.validator.common;

import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.RCulture;
import org.mo.com.message.FMessages;
import org.mo.com.message.FValidMessage;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;

public class FStringValidator
      extends FAbstractValidator
{
   private static IResource _resource = RResource.find(FStringValidator.class);

   public boolean checkAnsi(FMessages messages,
                            String value,
                            String display){
      FValidMessage message = null;
      if(!RString.isEmpty(value)){
         if(!RString.isAnsi(value)){
            String text = _resource.findString("checkAnsi", RCulture.currentLanguage());
            message = new FValidMessage(text, display);
            messages.push(message);
            return false;
         }
      }
      return true;
   }

   public boolean checkDbcs(FMessages messages,
                            String value,
                            String display){
      FValidMessage message = null;
      if(!RString.isEmpty(value)){
         if(!RString.isDbcs(value)){
            String text = _resource.findString("checkDbcs", RCulture.currentLanguage());
            message = new FValidMessage(text, display);
            messages.push(message);
            return false;
         }
      }
      return true;
   }

   public boolean checkEmpty(FMessages messages,
                             String value,
                             String display){
      FValidMessage message = null;
      if(RString.isBlank(value)){
         String text = _resource.findString("checkEmpty", RCulture.currentLanguage());
         message = new FValidMessage(text, display);
         messages.push(message);
         return false;
      }
      return true;
   }

   public boolean checkLength(FMessages messages,
                              String value,
                              String display,
                              int min,
                              boolean minEquals,
                              int max,
                              boolean maxEquals){
      FValidMessage message = null;
      if(!RString.isEmpty(value)){
         int length = value.length();
         if(minEquals && (length < min)){
            String text = _resource.findString("checkLength..minEq", RCulture.currentLanguage());
            message = new FValidMessage(text, display);
            messages.push(message);
            return false;
         }else if(!minEquals && (length <= min)){
            String text = _resource.findString("checkLength..min", RCulture.currentLanguage());
            message = new FValidMessage(text, display);
            messages.push(message);
            return false;
         }
         if(maxEquals && (length > max)){
            String text = _resource.findString("checkLength..maxEq", RCulture.currentLanguage());
            message = new FValidMessage(text, display);
            messages.push(message);
            return false;
         }else if(!maxEquals && (length >= max)){
            String text = _resource.findString("checkLength..max", RCulture.currentLanguage());
            message = new FValidMessage(text, display);
            messages.push(message);
            return false;
         }
      }
      return true;
   }

   public boolean checkLength(FMessages messages,
                              String value,
                              String display,
                              String min,
                              String minEquals,
                              String max,
                              String maxEquals){
      int minVal = RInteger.parse(min);
      boolean minEq = RBoolean.parse(minEquals);
      int maxVal = RInteger.parse(max);
      boolean maxEq = RBoolean.parse(maxEquals);
      return checkLength(messages, value, display, minVal, minEq, maxVal, maxEq);
   }

   public boolean checkPartten(FMessages messages,
                               String value,
                               String partten,
                               String display){
      FValidMessage message = null;
      if(!RString.isEmpty(value)){
         if(!RString.isPartten(value, partten)){
            String text = _resource.findString("checkPartten", RCulture.currentLanguage());
            message = new FValidMessage(text, display);
            messages.push(message);
            return false;
         }
      }
      return true;
   }
}

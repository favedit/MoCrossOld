package org.mo.core.logging.common;

import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;

public class FLogSubscribe
{
   private final int _maxCount = 100;

   private final FStrings _messages = new FStrings();

   public FLogSubscribe(){
   }

   public String message(){
      synchronized(_messages){
         FString buffer = new FString();
         int count = _messages.count();
         for(int n = 0; n < count; n++){
            buffer.append(_messages.get(n) + "\n");
         }
         _messages.clear();
         return buffer.toString();
      }
   }

   public boolean pushMessage(String message){
      synchronized(_messages){
         if(_messages.count() > _maxCount){
            //_messages.remove(0);
         }
         _messages.push(message);
      }
      return true;
   }
}

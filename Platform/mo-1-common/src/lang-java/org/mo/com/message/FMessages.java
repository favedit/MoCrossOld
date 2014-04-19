package org.mo.com.message;

import org.mo.com.lang.FObjects;
import org.mo.com.lang.FValidError;

public class FMessages
      extends FObjects<IMessage>
{
   public FMessages(){
      super(IMessage.class);
   }

   public void check(){
      if(!isEmpty()){
         throw new FValidError();
      }
   }

   public int count(Class<?> clazz){
      int n = -1;
      int count = 0;
      while(++n < _count){
         if(_items[n].getClass().isAssignableFrom(clazz)){
            count++;
         }
      }
      return count;
   }

   public boolean hasError(){
      return hasMessage(FFatalMessage.class) || hasMessage(FErrorMessage.class);
   }

   public boolean hasMessage(Class<?> clazz){
      int n = -1;
      while(++n < _count){
         if(_items[n].getClass().isAssignableFrom(clazz)){
            return true;
         }
      }
      return false;
   }

   @SuppressWarnings("unchecked")
   public <V extends IMessage> V message(Class<V> clazz){
      int n = -1;
      while(++n < _count){
         if(_items[n].getClass().isAssignableFrom(clazz)){
            return (V)_items[n];
         }
      }
      return null;
   }

   public FMessages messages(Class<?> clazz){
      int n = -1;
      FMessages messages = new FMessages();
      while(++n < _count){
         if(_items[n].getClass().isAssignableFrom(clazz)){
            messages.push(_items[n]);
         }
      }
      return messages;
   }
}

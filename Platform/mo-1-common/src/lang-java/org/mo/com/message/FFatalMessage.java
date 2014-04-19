package org.mo.com.message;

public class FFatalMessage
      extends MMessage
{
   private static String NAME = "Fatal";

   public FFatalMessage(){
   }

   public FFatalMessage(String message){
      super(message);
   }

   public FFatalMessage(String message,
                        String... params){
      super(message, params);
   }

   public FFatalMessage(Throwable throwable){
      super(throwable);
   }

   @Override
   public String name(){
      return NAME;
   }
}

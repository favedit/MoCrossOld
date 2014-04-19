package org.mo.com.message;

public class FErrorMessage
      extends MMessage
{
   private static String NAME = "Error";

   public FErrorMessage(){
   }

   public FErrorMessage(String message){
      super(message);
   }

   public FErrorMessage(String message,
                        String... params){
      super(message, params);
   }

   public FErrorMessage(Throwable throwable){
      super(throwable);
   }

   @Override
   public String name(){
      return NAME;
   }
}

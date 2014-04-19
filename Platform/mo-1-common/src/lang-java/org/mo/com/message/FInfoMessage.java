package org.mo.com.message;

public class FInfoMessage
      extends MMessage
{
   private static String NAME = "Info";

   public FInfoMessage(){
   }

   public FInfoMessage(String message){
      super(message);
   }

   public FInfoMessage(String message,
                       String... params){
      super(message, params);
   }

   @Override
   public String name(){
      return NAME;
   }
}

package org.mo.web.protocol.context;

import org.mo.com.lang.FAttributes;

public class FWebResponse
      implements
         IWebResponse
{

   private FAttributes _heads = new FAttributes();

   private FAttributes _values = new FAttributes();

   public FWebResponse(){
   }

   public String head(String name){
      return _heads.get(name);
   }

   public FAttributes heads(){
      return _heads;
   }

   public void setHead(String name,
                       String value){
      _heads.set(name, value);
   }

   public void setValue(String name,
                        String value){
      _values.set(name, value);
   }

   public String value(String name){
      return _values.get(name);
   }

   public FAttributes values(){
      return _values;
   }

}

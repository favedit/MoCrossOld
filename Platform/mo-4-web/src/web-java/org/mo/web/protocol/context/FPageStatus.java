package org.mo.web.protocol.context;

import org.mo.com.lang.RString;

public class FPageStatus
      implements
         IPageStatus
{

   private String _status;

   public String parse(String status,
                       String def){
      String[] items = RString.split(status, ';');
      for(String item : items){
         String[] params = RString.splitTwo(item, ':', true);
         if(params != null){
            if(params[0].equalsIgnoreCase(_status)){
               return params[1];
            }
         }
      }
      return def;
   }

   public void setStatus(String status){
      _status = status;
   }

   public String status(){
      return _status;
   }
}

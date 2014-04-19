package org.mo.web.core.action.common;

import org.mo.com.lang.RString;

public class TAction
{

   private String _action;

   private String _page;

   private String _uri;

   public String action(){
      return _action;
   }

   public boolean hasAction(){
      return RString.isNotEmpty(_action);
   }

   public String page(){
      return _page;
   }

   public void setAction(String action){
      _action = action;
   }

   public void setPage(String page){
      _page = page;
   }

   public void setUri(String uri){
      _uri = uri;
   }

   public String uri(){
      return _uri;
   }

}

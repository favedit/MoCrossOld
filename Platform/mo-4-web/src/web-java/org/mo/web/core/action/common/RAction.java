package org.mo.web.core.action.common;

import org.mo.com.lang.RString;
import org.mo.web.core.action.servlet.IActionConstant;

public class RAction
{

   public static TAction parse(String uri){
      TAction action = null;
      if(RString.isNotEmpty(uri)){
         action = new TAction();
         if(uri.indexOf("@") != -1){
            String[] items = RString.splitTwo(uri, '@', false);
            action.setAction(items[0]);
            action.setUri(items[1]);
         }else{
            action.setAction(IActionConstant.DEFAULT_METHOD);
            action.setUri(uri);
         }
         // Page
         String page = action.uri();
         if(page.endsWith(IActionConstant.WEB_ACTION)){
            page = page.substring(0, page.length() - IActionConstant.WEB_ACTION_LEN);
            page += IActionConstant.JSP_PAGE;
         }else if(page.endsWith(IActionConstant.WEB_PAGE)){
            page = page.substring(0, page.length() - IActionConstant.WEB_PAGE_LEN);
            page += IActionConstant.JSP_PAGE;
         }
         action.setPage(page);
      }
      return action;
   }

}

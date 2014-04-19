package org.mo.web.tag.control;

import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.core.aop.RAop;
import org.mo.web.core.cache.ICacheConsole;

public class FIncludeTag
      extends FBaseIncludeTag
{

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      String uri = _context.parseString(_uri);
      if(RString.isNotEmpty(uri)){
         String contextPath = _context.contextPath();
         uri = _context.contextPath(uri);
         if(uri.startsWith(contextPath)){
            uri = uri.substring(contextPath.length());
         }
         ICacheConsole console = RAop.find(ICacheConsole.class);
         FString data = console.find(uri);
         if(null != data){
            _writer.append(data);
         }
         _writer.flush();
      }
      return SKIP_BODY;
   }

}

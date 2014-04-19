package org.mo.web.core.message;

import org.mo.core.aop.face.AProperty;

public class FWebMessageConsole
      implements
         IWebMessageConsole
{

   @AProperty
   protected String _pageError;

   @AProperty
   protected String _pageFatal;

   @AProperty
   protected String _pageInfo;

   @AProperty
   protected String _pageTimeout;

   @AProperty
   protected String _pageWarn;

   @Override
   public String errorMessage(){
      return _pageError;
   }

   @Override
   public String fatalMessage(){
      return _pageFatal;
   }

   @Override
   public String infoMessage(){
      return _pageInfo;
   }

   @Override
   public String loginTimeout(){
      return _pageTimeout;
   }

   @Override
   public String loginWithout(){
      return _pageTimeout;
   }

   @Override
   public String warnMessage(){
      return _pageWarn;
   }

}

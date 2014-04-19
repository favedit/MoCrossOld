package org.mo.logic.resource;

import org.mo.core.aop.face.AProperty;

public class FLogicResourceConsole
      implements
         ILogicResourceConsole
{
   @AProperty
   private String _directory;

   @AProperty
   private String _workpath;

   @Override
   public ILogicResource find(String id){
      return null;
   }

   @Override
   public void update(ILogicResource resource){
   }
}

package org.mo.jfa.face.logic.resource;

import org.mo.core.aop.face.ALink;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.protocol.context.IWebContext;

public class FResourceAction
      implements
         IResourceAction
{

   @ALink
   protected IFormatConsole _formatConsole;

   @Override
   public String catalog(IWebContext context,
                         FResourcePage page){
      return "Catalog";
   }

}

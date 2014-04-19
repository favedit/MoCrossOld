package org.mo.jfa.face.monitor.system;

import org.mo.web.protocol.context.IWebContext;

public class FSystemAction
      implements
         ISystemAction
{

   public final static String PAGE_CATALOG = "Catalog";

   public final static String PAGE_MEMORY = "Memory";

   //private static ILogger _logger = RLogger.find(FSystemAction.class);

   public String catalog(IWebContext context,
                         TSystemPage page){
      return PAGE_CATALOG;
   }

   public String showMemory(IWebContext context,
                            TSystemPage page){
      return PAGE_MEMORY;
   }

}

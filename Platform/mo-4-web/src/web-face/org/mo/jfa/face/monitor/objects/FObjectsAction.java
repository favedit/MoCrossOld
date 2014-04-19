package org.mo.jfa.face.monitor.objects;

import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;
import org.mo.core.aop.container.FAopComponent;
import org.mo.core.aop.face.ALink;
import org.mo.eng.tracker.ITrackerConsole;
import org.mo.web.protocol.context.IWebContext;

public class FObjectsAction
      implements
         IObjectsAction
{

   private static ILogger _logger = RLogger.find(FObjectsAction.class);

   @ALink
   protected ITrackerConsole _trackerConsole;

   public String catalog(IWebContext context,
                         FObjectsPage page){
      return "Catalog";
   }

   public String showObject(IWebContext context,
                            FObjectsPage page){
      String type = RString.nvl(context.parameter("type")).toLowerCase();
      String name = RString.nvl(context.parameter("name"));
      System.out.println(type + " " + name);
      Object instance = null;
      if(type.equals("face")){
         RAop.find(name);
         FAopComponent comp = RAop.findComponent(name);
         instance = comp.nativeInstance();
         System.out.println(instance);
      }else if(type.equals("name")){
         if(name.equals("core.config")){
            instance = RAop.configConsole();
         }else if(name.equals("core.component")){
            instance = RAop.componentConsole();
         }
      }
      _logger.debug(this, "FObjectsAction", "Set monitor object {0}={1}", name, instance);
      if(null != instance){
         page.setObjectName(name);
         page.setObject(instance);
      }
      //page.getObjects().clear();
      return "Object";
   }
}

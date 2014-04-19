package org.mo.jfa.face.monitor.system;

import org.mo.com.lang.RInteger;
import org.mo.web.protocol.context.IWebContext;

public class FMemoryAction
      implements
         IMemoryAction
{

   public final static String PAGE_MEMORY = "Memory";

   public String show(IWebContext context,
                      TMemoryPage page){
      Runtime runtime = Runtime.getRuntime();
      long totalMemory = runtime.totalMemory();
      long freeMemory = runtime.freeMemory();
      long maxMemory = runtime.maxMemory();
      //
      page.setTotalMemory(RInteger.formatGroup(Long.toString(totalMemory)));
      page.setTotalMemoryM(Integer.toString((int)(totalMemory / 1024 / 1024)));
      page.setFreeMemory(RInteger.formatGroup(Long.toString(freeMemory)));
      page.setFreeMemoryM(Integer.toString((int)(freeMemory / 1024 / 1024)));
      page.setMaxMemory(RInteger.formatGroup(Long.toString(maxMemory)));
      page.setMaxMemoryM(Integer.toString((int)(maxMemory / 1024 / 1024)));
      float userPercent = ((float)totalMemory - (float)freeMemory) / totalMemory;
      String userPercentString = Float.toString(userPercent * 100);
      if(userPercentString.length() > 4){
         userPercentString = userPercentString.substring(0, 4);
      }
      page.setUsePercent(userPercentString);
      page.setAvailableProcessors(Integer.toString(runtime.availableProcessors()));
      return PAGE_MEMORY;
   }

}

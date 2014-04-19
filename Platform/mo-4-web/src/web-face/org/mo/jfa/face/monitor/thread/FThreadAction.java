package org.mo.jfa.face.monitor.thread;

import org.mo.com.lang.FObjects;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public class FThreadAction
      implements
         IThreadAction
{

   @Override
   public String catalog(IWebContext context,
                         FThreadPage page){
      System.out.println("------------ " + page);
      FObjects<FThreadInfo> infos = page.getInfos();
      infos.clear();
      int count = Thread.activeCount();
      System.out.println(count);
      Thread[] threads = new Thread[count];
      Thread.enumerate(threads);
      for(Thread thread : threads){
         FThreadInfo info = new FThreadInfo();
         info.setName(Long.toString(thread.getId()));
         info.setName(thread.getName());
         infos.push(info);
      }
      return "Catalog";
   }

   @Override
   public String construct(IWebContext context,
                           FThreadPage page){
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String info(ISqlContext sqlContext,
                      FThreadPage page){
      // TODO Auto-generated method stub
      return null;
   }

}

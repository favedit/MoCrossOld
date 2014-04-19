package org.mo.jfa.face.monitor.database;

import org.mo.com.collections.FAttributesList;
import org.mo.com.lang.FAttributes;
import org.mo.eng.data.IConnectionConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public class FDatabaseAction
      implements
         IDatabaseAction
{

   @Override
   public String catalog(IWebContext context,
                         FDatabasePage page,
                         IDatabaseConsole dbConsole){
      FAttributesList cnnConsoleInfos = page.getConnectionConsoles();
      cnnConsoleInfos.clear();

      IConnectionConsole[] consoles = dbConsole.connectionConsoles();
      System.out.println(consoles.length);
      for(IConnectionConsole console : consoles){
         FAttributes cnnInfo = new FAttributes();
         cnnInfo.set("name", console.name());
         cnnConsoleInfos.push(cnnInfo);
      }
      System.out.println(page.getConnectionConsoles().get(0).dump());
      return "Catalog";
   }

   @Override
   public String construct(IWebContext context,
                           FDatabasePage page){
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String info(ISqlContext sqlContext,
                      FDatabasePage page){
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String showConsole(IWebContext context,
                             FDatabasePage page,
                             IDatabaseConsole dbConsole){
      String name = context.parameter("name");
      IConnectionConsole console = dbConsole.connectionConsole(name);
      page.getConnectionConsole().assign(console.config());
      page.getConnections().assign(console.connectionInfos());
      System.out.println(page.getConnectionConsole().dump());
      System.out.println(page.getConnections().dump());
      return "Console";
   }

}

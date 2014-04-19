package org.mo.jfa.core.catalog;

import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FCatalogService
      implements
         ICatalogService
{

   @ALink
   private ICatalogConsole _catalogConsole;

   public void loadGroups(IWebContext webContext,
                          ISqlContext sqlContext,
                          IWebInput input,
                          IWebOutput output){
      FCatalogGroups groups = _catalogConsole.loadGroups(sqlContext);
      output.config().nodes().append(groups.toObjects());
   }

   public void loadNodes(IWebContext webContext,
                         ISqlContext sqlContext,
                         IWebInput input,
                         IWebOutput output){
      System.out.println(input.config().dump());
      String sql = "SELECT * FROM COM_NAV_NODE";
      FSqlQuery query = new FSqlQuery(sqlContext, sql);
      System.out.println(query.fetchDataset().dump());
      System.out.println("----------------------");
      System.out.println(input.config().dump());
      System.out.println("----------------------");
      System.out.println(this);
   }

}

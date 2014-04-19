package org.mo.jfa.face.database.view;

import java.util.Iterator;
import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.FSqlTable;
import org.mo.com.data.ISqlConnectionMeta;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.data.datainfo.IDataInfoConsole;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public class FViewAction
      extends FAbstractCommon
      implements
         IViewAction
{

   @ALink
   protected IDataInfoConsole _dataInfoConsole;

   @Override
   public String catalog(IWebContext context,
                         FViewPage page){
      return "Catalog";
   }

   @Override
   @SuppressWarnings("rawtypes")
   public String update(IWebContext context,
                        ISqlContext sqlContext,
                        FViewPage page){
      // Environment
      setEnvironment(context, page);
      // Attribute
      String viewName = page.getSelectView();
      //System.out.println("viewName = "+viewName);
      if(viewName != null){
         ISqlConnectionMeta meta = sqlContext.activeConnection().meta();
         FSqlTable view = meta.findTable(viewName, true);
         FSqlQuery TSql = new FSqlQuery(sqlContext, FViewService.class, "view.value");
         TSql.bindString("view_name", viewName);
         FDataset dataset = TSql.fetchDataset();
         FXmlNode config;
         for(Iterator iterator = dataset.iterator(); iterator.hasNext(); page.setFormValue(config.xml())){
            FRow noteName = (FRow)iterator.next();
            config = new FXmlNode();
            config.attributes().append(noteName);
            view.saveConfig(config);
         }

      }
      return "View";
   }

}

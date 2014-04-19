package org.mo.jfa.face.database.table;

import java.util.Iterator;
import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.FSqlTable;
import org.mo.com.data.ISqlConnectionMeta;
import org.mo.com.xml.FXmlNode;
import org.mo.data.datainfo.IDataInfoConsole;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

// Referenced classes of package org.mo.jfa.face.database.table:
//            FAbstractCommon, ITableAction, FTablePage, FTableService

public class FTableAction
      extends FAbstractCommon
      implements
         ITableAction
{

   protected IDataInfoConsole _dataInfoConsole;

   public FTableAction(){
   }

   @Override
   public String catalog(IWebContext context,
                         FTablePage page){
      return "Catalog";
   }

   @Override
   @SuppressWarnings("rawtypes")
   public String update(IWebContext context,
                        ISqlContext sqlContext,
                        FTablePage page){
      setEnvironment(context, page);
      String tableName = page.getSelectTable();
      if(tableName != null){
         ISqlConnectionMeta meta = sqlContext.activeConnection().meta();
         FSqlTable table = meta.findTable(tableName, true);
         FSqlQuery TSql = new FSqlQuery(sqlContext, FTableService.class, "table.value");
         TSql.bindString("table_name", tableName);
         FDataset dataset = TSql.fetchDataset();
         FXmlNode config;
         for(Iterator iterator = dataset.iterator(); iterator.hasNext(); page.setFormValue(config.xml())){
            FRow noteName = (FRow)iterator.next();
            config = new FXmlNode();
            config.attributes().append(noteName);
            table.saveConfig(config);
         }

      }
      return null;
   }
}

package org.mo.jfa.face.database.view;

import org.mo.com.data.FSqlField;
import org.mo.com.data.FSqlTable;
import org.mo.com.data.ISqlConnectionMeta;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.data.datainfo.IDataInfoConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public class FFieldAction
      extends FAbstractCommon
      implements
         IFieldAction
{

   @ALink
   protected IDataInfoConsole _dataInfoConsole;

   @Override
   public String catalog(IWebContext context,
                         FViewPage page){
      return "Catalog";
   }

   public String update(IWebContext context,
                        ISqlContext sqlContext,
                        FViewPage page){
      // Environment
      setEnvironment(context, page);
      // Attribute
      String tableName = page.getSelectView();
      String fieldName = page.getSelectField();
      ISqlConnectionMeta meta = sqlContext.activeConnection().meta();
      FSqlTable view = meta.findTable(tableName, true);
      if(null != view){
         FSqlField field = view.fields().get(fieldName);
         if(null != field){
            FXmlNode config = new FXmlNode();
            field.saveConfig(config);
            if(!config.contains("data_name")){
               config.set("data_name", field.name().toLowerCase());
            }
            page.setFormValue(config.xml());
         }
      }
      return null;
   }

}

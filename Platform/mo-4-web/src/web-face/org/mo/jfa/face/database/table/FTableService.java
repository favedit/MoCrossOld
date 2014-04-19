package org.mo.jfa.face.database.table;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.FSqlField;
import org.mo.com.data.FSqlTable;
import org.mo.com.data.ISqlConnectionMeta;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.core.aop.face.ALink;
import org.mo.data.datainfo.IDataInfoConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FTableService
      extends FAbstractCommon
      implements
         ITableService
{

   // 创建services
   private static IResource _resource = RResource.find(FTableService.class);

   @ALink
   protected IDataInfoConsole _dataInfoConsole;

   @ALink
   protected IWebFormConsole _formConsole;

   @Override
   public void catalog(ISqlContext context,
                       IWebInput input,
                       IWebOutput output){
      String sql = _resource.findString("table.list");
      FDataset dataset = context.activeConnection().fetchDataset(sql);
      FXmlNodes outputNodes = output.config().nodes();
      for(FRow noteName : dataset){
         String tableName = noteName.get("TABLE_NAME");
         if(!"BIN".equals(tableName.substring(0, 3))){
            // 建立树节点
            XTreeNode xnode = new XTreeNode();
            xnode.setType("table");
            xnode.setLabel(tableName);
            xnode.setChild(true);
            outputNodes.push(xnode.toSimpleNode());
         }
      }
   }

   @SuppressWarnings("unused")
   @Override
   public void doSave(ISqlContext sqlContext,
                      IWebInput input,
                      IWebOutput output){
      FXmlNode env = input.config().findNode("Environment");
      FXmlNode data = input.config().findNode("Data");
      if(null != env && null != data){
         String type = env.get("select_type");
         String tableName = env.get("select_table");
         String fieldName = env.get("select_field");
         if("table".equals(type)){
            //            XTable xTable = _dataInfoConsole.syncTable(tableName);
            //            xTable.loadConfig(data, EXmlConfig.Value);
            //            _dataInfoConsole.store(xTable);
         }else if("field".equals(type)){
            //            XTable xTable = _dataInfoConsole.syncTable(tableName);
            //            XField xfield = (XField) xTable.find("Field", "name", fieldName);
            //            if(null == xfield){
            //               xfield = new XField();
            //               xTable.children().push(xfield);
            //            }
            //            xfield.loadConfig(data, EXmlConfig.Value);
            //            _dataInfoConsole.store(xTable);
         }
      }
   }

   @Override
   public void listField(ISqlContext context,
                         IWebInput input,
                         IWebOutput output){
      FXmlNode select = input.config().node("Node");
      String pageName = select.get("caption");
      FXmlNodes outputNodes = output.config().nodes();
      ISqlConnectionMeta meta = context.activeConnection().meta();
      FSqlTable dataset = meta.findTable(pageName, true);
      if(null != dataset){
         for(FSqlField field : dataset.fields()){
            // 建立树节点
            XTreeNode xnode = new XTreeNode();
            xnode.setType("field");
            xnode.setLabel(field.name());
            xnode.setChild(false);
            outputNodes.push(xnode.toSimpleNode());
         }
      }

   }
}

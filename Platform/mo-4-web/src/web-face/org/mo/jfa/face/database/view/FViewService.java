package org.mo.jfa.face.database.view;

import org.mo.com.data.FSqlField;
import org.mo.com.data.FSqlTable;
import org.mo.com.data.FSqlView;
import org.mo.com.data.ISqlConnectionMeta;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.core.aop.face.ALink;
import org.mo.data.datainfo.IDataInfoConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FViewService
      implements
         IViewService
{

   @ALink
   protected IDataInfoConsole _dataInfoConsole;

   @ALink
   protected IWebFormConsole _formConsole;

   @Override
   public void catalog(ISqlContext context,
                       IWebInput input,
                       IWebOutput output){
      ISqlConnectionMeta meta = context.activeConnection().meta();
      FXmlNodes outputNodes = output.config().nodes();
      for(FSqlView view : meta.listViews()){
         // 建立树节点
         XTreeNode xnode = new XTreeNode();
         xnode.setType("view");
         xnode.setLabel(view.name());
         xnode.setChild(true);
         outputNodes.push(xnode.toSimpleNode());
      }
   }

   @SuppressWarnings("unused")
   @Override
   public void doSave(ISqlContext sqlContext,
                      IWebInput input,
                      IWebOutput output){
      FXmlNode env = input.config().findNode("Environment");
      FXmlNode data = input.config().findNode("Data");
      //_formConsole.find(data);
      if(null != env && null != data){
         String type = env.get("select_type");
         String viewName = env.get("select_view");
         String fieldName = env.get("select_field");
         if("view".equals(type)){
            //            XTable xTable = _dataInfoConsole.syncTable(viewName);
            //            xTable.loadConfig(data, EXmlConfig.Value);
            //            _dataInfoConsole.store(xTable);
         }else if("field".equals(type)){
            //            XTable xTable = _dataInfoConsole.syncTable(viewName);
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
      if(null != select){
         String viewName = select.get("caption");
         ISqlConnectionMeta meta = context.activeConnection().meta();
         FSqlTable view = meta.findTable(viewName, true);
         FXmlNodes outputNodes = output.config().nodes();
         if(null != view){
            for(FSqlField field : view.fields()){
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

}

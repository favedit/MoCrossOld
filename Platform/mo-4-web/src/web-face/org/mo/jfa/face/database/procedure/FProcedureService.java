package org.mo.jfa.face.database.procedure;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.lang.RString;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.core.aop.face.ALink;
import org.mo.data.datainfo.IDataInfoConsole;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FProcedureService
      implements
         IProcedureService
{

   @SuppressWarnings("unused")
   private static IResource _resource = RResource.find(FProcedureService.class);

   @ALink
   protected IDataInfoConsole _dataInfoConsole;

   @ALink
   protected IWebFormConsole _formConsole;

   @Override
   public void catalog(ISqlContext context,
                       IWebInput input,
                       IWebOutput output){
      FSqlQuery query = new FSqlQuery(context, getClass(), "package.list");
      FDataset dataset = query.fetchDataset();
      FXmlNodes outputNodes = output.config().nodes();
      for(FRow row : dataset){
         String packageName = row.get("package_name");
         if(RString.endsWith(packageName, "_DI")){
            // 建立树节点
            XTreeNode xnode = new XTreeNode();
            xnode.setType("package");
            xnode.setLabel(packageName);
            xnode.set("package_name", packageName);
            xnode.setChild(true);
            outputNodes.push(xnode.toSimpleNode());
         }
      }
      outputNodes.sortByAttribute("label");
   }

   @Override
   public void doSave(ISqlContext sqlContext,
                      IWebInput input,
                      IWebOutput output){
      FXmlNode env = input.config().findNode("Environment");
      FXmlNode data = input.config().findNode("Data");
      //_formConsole.find(data);
      if(null != env && null != data){
         String type = env.get("select_type");
         //         String tableName = env.get("select_table");
         //         String fieldName = env.get("select_field");
         if("table".equals(type)){
            //IXmlObject xTable = _dataInfoConsole.syncTable(tableName);
            //xTable.loadConfig(data, EXmlConfig.Value);
            //_dataInfoConsole.store(xTable);
         }else if("field".equals(type)){
            //XTable xTable = _dataInfoConsole.syncTable(tableName);
            //XField xfield = (XField) xTable.find("Field", "name", fieldName);
            //if(null == xfield){
            //   xfield = new XField();
            //   xTable.children().push(xfield);
            //}
            //xfield.loadConfig(data, EXmlConfig.Value);
            //_dataInfoConsole.store(xTable);
         }
      }
   }

   @Override
   public void listField(ISqlContext context,
                         IWebInput input,
                         IWebOutput output){
      //      FXmlNode selectNode = input.config().node("Node");
      //      FXmlNodes outputNodes = output.config().nodes();
      //      if(null != selectNode){
      //         FAttributes attrs = new FAttributes();
      //         attrs.unpack(selectNode.get("attributes"));
      //         String pageName = attrs.get("package_name");
      //         FSqlPackageParser parser = new FSqlPackageParser(context, pageName);
      //         if(parser.parseHead()){
      //            FSqlFunctions funName = parser.functions();
      //            for(int i = 0; i < funName.count(); i++){
      //               String sFunName = funName.name(i);
      //               String funcName = _resource.findString("funcName.list");
      //               // 建立树节点
      //               XTreeNode xnode = new XTreeNode();
      //               xnode.setType("function");
      //               xnode.setLabel(sFunName);
      //               xnode.setNote(funcName);
      //               xnode.setChild(false);
      //               outputNodes.push(xnode.toSimpleNode());
      //            }
      //            FSqlProcedures procName = parser.procedures();
      //            String Name = _resource.findString("procName.list");
      //            for(int i = 0; i < procName.count(); i++){
      //               String sProcName = procName.name(i);
      //               // 建立树节点
      //               XTreeNode xnode = new XTreeNode();
      //               xnode.setType("procedure");
      //               xnode.setLabel(sProcName);
      //               xnode.setNote(Name);
      //               xnode.setChild(false);
      //               outputNodes.push(xnode.toSimpleNode());
      //            }
      //         }
      //      }
   }
}

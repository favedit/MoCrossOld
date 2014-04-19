package org.mo.jfa.face.logic.resource;

import org.mo.com.collections.FRow;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FResourceService
      implements
         IResourceService
{

   // private static IResource _resource =
   // RResource.find(FModuleService.class);

   // private static ILogger _logger = RLogger.find(FModuleService.class);

   public final static String NODE_LIST = "list";

   /*
    * (non-Javadoc)
    * 
    * @see
    * com.linekong.euis.face.company.system.module.IModuleService#catalog(org
    * .mobj.eng.data.common.ISqlContext,
    * org.mo.web.protocol.context.IWebInput,
    * org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void catalog(ISqlContext context,
                       IWebInput input,
                       IWebOutput output){
      int viewId = 1;
      FXmlNode treeNode = input.config().findNode("Tree");
      if(null != treeNode){
         viewId = treeNode.getInt("view_id");
      }
      // Make output nodes
      FXmlNodes outputNodes = output.config().nodes();
      makeOutputNodes(context, outputNodes, Integer.toString(viewId), "0");
   }

   /*
    * (non-Javadoc)
    * 
    * @see
    * com.linekong.euis.face.company.system.module.IModuleService#listNode(
    * org.mo.eng.data.common.ISqlContext,
    * org.mo.web.protocol.context.IWebInput,
    * org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void listNode(ISqlContext context,
                        IWebInput input,
                        IWebOutput output){
      FXmlNode select = input.config().node("Node");
      if(null != select){
         FAttributes pack = new FAttributes();
         pack.unpack(select.get("attribute"));
         String viewId = pack.get("view_id");
         String parentId = pack.get("ouid");
         // Make output nodes
         FXmlNodes outputNodes = output.config().nodes();
         makeOutputNodes(context, outputNodes, viewId, parentId);
      }
   }

   private void makeOutputNodes(ISqlContext context,
                                FXmlNodes outputNodes,
                                String viewId,
                                String parentId){
      FSqlQuery query = new FSqlQuery(context, getClass(), "resource.catalog");
      query.bindSql("view_id", viewId);
      query.bindSql("catalog_id", parentId);
      for(FRow row : query.fetchDataset()){
         // 建立树节点
         XTreeNode xnode = new XTreeNode();
         xnode.setType(RString.nvl(row.get("module_type"), "node"));
         xnode.setLabel(row.get("label"));
         xnode.setNote(row.get("name"));
         xnode.setChild(row.getInteger("child_count") > 0);
         xnode.set("view_id", viewId);
         xnode.set("ouid", row.get("ouid"));
         outputNodes.push(xnode.toSimpleNode());
      }
   }

}

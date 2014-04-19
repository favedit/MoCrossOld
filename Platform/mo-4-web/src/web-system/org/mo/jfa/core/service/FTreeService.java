package org.mo.jfa.core.service;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.eng.translate.ITranslateConsole;
import org.mo.jfa.core.catalog.ICatalogConsole;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.control.FWebTreeNode;

public class FTreeService
      implements
         ITreeService
{

   @ALink
   private ITranslateConsole _translateConsole;

   @ALink
   private ICatalogConsole _treeConsole;

   @SuppressWarnings("unused")
   private String _treename;

   @Override
   public void process(IWebContext context,
                       FXmlNode input,
                       FXmlNode output){
      String action = input.nodeText("Action");
      if(RString.isBlank(action)){
         throw new FFatalError("Action is null.");
      }
      if(action.equalsIgnoreCase("tree.node")){
         if(input.nodes().count() == 2){
            serviceTree(context, input.node(1), output);
         }else{
            serviceTree(context, input, output);
         }
      }
   }

   @SuppressWarnings("null")
   public void serviceTree(IWebContext context,
                           FXmlNode input,
                           FXmlNode output){
      String extension = ".ws";
      // 获得入口参数
      String sNodeType = input.get("type");
      String sConfig = "system";
      // 初始化变量
      // String sExtension = FWebServiceManager.console().extension();
      // 生成树节点
      //FXmlNode root = _treeConsole.findTree(sConfig);
      FXmlNode root = null;
      FXmlNode oTvNodes = root.findNode("Nodes");
      FXmlNode oTypeNodes = root.findNode("Types");
      if(!RString.isEmpty(sNodeType)){
         oTvNodes = oTvNodes.allNodes().findNode("Node", "type", sNodeType);
      }
      if(oTvNodes == null){
         throw new FFatalError("Not find any node.[" + sConfig + "]");
      }
      oTvNodes = oTvNodes.copy();
      String type;
      String treeService;
      String pageService;
      boolean hasChild;
      FXmlNode typeNode;
      FWebTreeNode treeNode;
      String label;
      for(FXmlNode oNode : oTvNodes.nodes()){
         // Type Check
         type = oNode.get("type");
         typeNode = oTypeNodes.findNode("Type", "name", type);
         if(typeNode != null){
            treeService = typeNode.get("tree_svc");
            pageService = typeNode.get("page_svc");
         }else{
            treeService = null;
            pageService = null;
         }
         if(RString.isEmpty(treeService)){
            treeService = oTvNodes.get("tree_svc");
         }
         if(RString.isEmpty(pageService)){
            pageService = oTvNodes.get("page_svc");
         }
         // Table List
         hasChild = RBoolean.parse(oNode.get("child"));
         if(oNode.hasNode()){
            hasChild = true;
         }
         label = oNode.get("label");
         // label = _translateConsole.translate(ETranslateType.CONFIG, "tree.system", label);
         treeNode = new FWebTreeNode(type, label, hasChild, oNode.get("icon"));
         if(!RString.isBlank(treeService)){
            treeNode.set("tree_url", context.contextPath() + "/" + treeService + extension);
         }
         if(!RString.isBlank(pageService)){
            treeNode.set("page_url", context.contextPath() + "/" + pageService + extension);
         }
         //FStringListUtil.appendEmpty(oTvNode.attributes(), oNode.attributes());
         output.nodes().push(treeNode);
      }
   }

}

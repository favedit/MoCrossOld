package org.mo.jfa.core.service;

import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.eng.translate.ITranslateConsole;
import org.mo.jfa.core.catalog.ICatalogConsole;
import org.mo.web.protocol.context.IWebContext;

public class FPageService
      implements
         IPageService
{

   @ALink
   private ITranslateConsole _translateConsole;

   @ALink
   private ICatalogConsole _treeConsole;

   @Override
   public void process(IWebContext context,
                       FXmlNode input,
                       FXmlNode output){
      //      String sNodeType = input.attribute("type");
      //      if(RString.isEmpty(sNodeType)){
      //         sNodeType = "default";
      //      }
      //      String sConfig = "system";
      //      FXmlNode root = _treeConsole.findTree(sConfig);
      //      FXmlNode typeNodes = root.findNode("Types");
      //      FXmlNode typeNode = null;
      //
      //      if(RString.isEmpty(sConfig)){
      //         typeNode = typeNodes.findNode("Type", "name", "default");
      //         FXmlNode oSheetNode = typeNode.findNode("Sheet");
      //         String sAction = context.contextPath() + oSheetNode.attribute("action");
      //         String sLabel = oSheetNode.attribute("label");
      //         FWebSheet oSheet = new FWebSheet(oSheetNode.attribute("name"), sLabel, oSheetNode
      //               .attribute("icon"), sAction, oSheetNode.attribute("hint"));
      //         output.nodes().push(oSheet);
      //      }else{
      //         if(typeNodes != null){
      //            typeNode = typeNodes.findNode("Type", "name", sNodeType);
      //         }
      //         if(typeNode == null){
      //            typeNode = typeNodes.findNode("Type", "name", "default");
      //            if(typeNode == null){
      //               throw new FFatalError("Catalog type({0}) is not found.", sNodeType);
      //            }
      //         }
      //         String sAction = null;
      //         FWebSheet oSheet = null;
      //         for(FXmlNode oSheetNode : typeNode.nodes()){
      //            sAction = context.contextPath() + oSheetNode.attribute("action");
      //            String sLabel = oSheetNode.attribute("label");
      //            sLabel = _translateConsole.translate(ETranslateType.CONFIG, "tree.system", sLabel);
      //            oSheet = new FWebSheet(oSheetNode.attribute("name"), sLabel, oSheetNode
      //                  .attribute("icon"), sAction, oSheetNode.attribute("hint"));
      //            output.nodes().push(oSheet);
      //         }
      //      }
   }

}

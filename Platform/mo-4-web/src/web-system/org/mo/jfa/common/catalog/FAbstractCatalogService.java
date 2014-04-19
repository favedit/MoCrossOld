package org.mo.jfa.common.catalog;

import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlFunction;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNodes;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.validator.IValidatorConsole;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FAbstractCatalogService
      extends FAbstractCatalogCommon
{

   @ALink
   protected IValidatorConsole _validatorConsole;

   protected void buildChildren(ISqlContext sqlContext,
                                String logicName,
                                IAttributes params,
                                IWebInput input,
                                IWebOutput output){
      FXmlNodes outputNodes = output.config().nodes();
      FSqlFunction function = new FSqlFunction(logicName);
      function.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.In);
      sqlContext.activeConnection().execute(function);
      String pack = function.returnAsString();
      if(RString.isNotEmpty(pack)){
         FStrings lines = new FStrings();
         FRow row = new FRow();
         lines.unpack(pack);
         for(String line : lines){
            row.unpack(line);
            // 建立树节点
            XTreeNode xnode = new XTreeNode();
            xnode.setType(row.get("node_type"));
            xnode.setLabel(row.get("label"));
            xnode.setNote(row.get("name"));
            xnode.setChild(row.getInteger("child_count") > 0);
            xnode.set("view_id", row.get("view_id"));
            xnode.set("ouid", row.get("ouid"));
            outputNodes.push(xnode.toSimpleNode());
         }
      }
   }

   protected void list(ISqlContext sqlContext,
                       String logicName,
                       IAttributes params,
                       IWebInput input,
                       IWebOutput output){
      list(sqlContext, logicName, "Get_Children_Pack", params, input, output);
   }

   protected void list(ISqlContext sqlContext,
                       String logicName,
                       String methodName,
                       IAttributes params,
                       IWebInput input,
                       IWebOutput output){
      FXmlNodes outputNodes = output.config().nodes();
      FSqlFunction function = new FSqlFunction(methodName);
      function.setLogicName(logicName);
      function.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.In);
      sqlContext.activeConnection().execute(function);
      String pack = function.returnAsString();
      if(RString.isNotEmpty(pack)){
         FStrings lines = new FStrings();
         FRow row = new FRow();
         lines.unpack(pack);
         for(String line : lines){
            row.unpack(line);
            // 建立树节点
            XTreeNode xnode = new XTreeNode();
            xnode.setType(row.get("node_type"));
            xnode.setLabel(row.get("label"));
            xnode.setNote(row.get("name"));
            xnode.setChild(row.getInteger("child_count") > 0);
            xnode.set("ouid", row.get("ouid"));
            xnode.set("checked", row.get("checked"));
            onBuildTreeNode(xnode, row);
            outputNodes.push(xnode.toSimpleNode());
         }
      }
   }

   protected void onBuildTreeNode(XTreeNode xnode,
                                  FRow row){
   }
}

package org.mo.jfa.face.design.chart;

import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.core.aop.face.ALink;
import org.mo.data.chart.IChartConsole;
import org.mo.eng.store.FXmlConfigMeta;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FChartService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IChartService
{

   @ALink
   protected IChartConsole _chartConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#catalog(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      String nodeType = null;
      String nodeSort = null;
      FXmlNode config = input.config().findNode("Attributes");
      if(null != config){
         nodeType = config.get(PTY_NODE_TYPE);
         nodeSort = config.get(PTY_NODE_SORT);
      }
      FXmlNode node = input.config().findNode("Node");
      if(null != node){
         String updateType = node.get("type");
         if("package".equals(updateType)){
            FAttributes nodeAttrs = new FAttributes();
            nodeAttrs.unpack(node.get("attributes"));
            String uuid = nodeAttrs.get("uuid");
            // 统计节点包
            FXmlNodes outputNodes = output.config().nodes();
            for(FXmlConfigMeta meta : _chartConsole.listMetas()){
               if(RFile.removeExtension(meta.name()).equals(uuid)){
                  String name = meta.name();
                  IXmlObject xcollection = _chartConsole.find(name);
                  String type = xcollection.name();
                  // 过滤节点
                  if(!RString.isEmpty(nodeType) && !name.endsWith(nodeType)){
                     continue;
                  }
                  // 新建树节点
                  XTreeNode xnode = new XTreeNode();
                  xnode.setType(type);
                  xnode.setUuid(xcollection.objectId());
                  xnode.setLabel(name.substring(uuid.length() + 1));
                  xnode.setNote(xcollection.innerGet("label"));
                  xnode.setChild(xcollection.hasChild());
                  xnode.set("form_name", name);
                  onBuildTreeNode(xnode, xcollection);
                  FXmlNode treeNode = xnode.toSimpleNode();
                  treeNode.set("is_valid", xcollection.innerGet("is_valid"));
                  outputNodes.push(treeNode);
               }
            }
            // 控制排序
            if(null != nodeSort){
               outputNodes.sortByAttribute("asc".equals(nodeSort), "label");
            }else{
               outputNodes.sortByAttribute("label");
            }
            return;
         }
      }
      // 统计节点包
      FAttributes nameMap = new FAttributes();
      FXmlNodes outputNodes = output.config().nodes();
      for(FXmlConfigMeta meta : _chartConsole.listMetas()){
         String metaName = meta.name();
         String name = RFile.removeExtension(metaName);
         // 过滤节点
         if(!RString.isEmpty(nodeType) && !metaName.endsWith(nodeType)){
            continue;
         }
         // 检查是否已经添加过
         if(nameMap.contains(name)){
            int count = RInteger.parse(nameMap.get(name));
            nameMap.set(name, Integer.toString(count + 1));
            continue;
         }
         nameMap.set(name, "1");
      }
      // 建立树节点
      for(IStringPair pair : nameMap){
         String type = "package";
         String name = pair.name();
         int count = RInteger.parse(pair.value());
         // 新建树节点
         XTreeNode xnode = new XTreeNode();
         xnode.setType(type);
         xnode.setUuid(name);
         xnode.setLabel(name + " (" + Integer.toString(count) + ")");
         xnode.setChild(count > 0);
         //onBuildTreeNode(xnode, xcollection);
         FXmlNode treeNode = xnode.toSimpleNode();
         //node.set("is_valid", xcollection.innerGet("is_valid"));
         outputNodes.push(treeNode);
      }
      // 控制排序
      if(null != nodeSort){
         outputNodes.sortByAttribute("asc".equals(nodeSort), "label");
      }else{
         outputNodes.sortByAttribute("label");
      }
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_chartConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_chartConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      // 获得选中的内容
      FXmlNode selectNode = getSelectNode(input);
      FAttributes nodeAttrs = new FAttributes();
      // 获得容器节点
      String collection = null;
      String typeType = selectNode.get("type_type");
      if(TYPE_COLLECTION.equals(typeType)){
         nodeAttrs.unpack(selectNode.get("attributes"));
         collection = nodeAttrs.get("form_name");
      }else if(TYPE_COMPONENT.equals(typeType)){
         FXmlNode topNode = selectNode.findDeep("Node", "type_type", TYPE_COLLECTION);
         nodeAttrs.unpack(topNode.get("attributes"));
         collection = nodeAttrs.get("form_name");
      }
      IXmlObject xcollection = _chartConsole.get(collection);
      // 获得控件对象
      IXmlObjects xcontrols = null;
      if(TYPE_COLLECTION.equals(typeType)){
         xcontrols = xcollection.children();
      }else{
         String objectId = selectNode.get("uuid");
         IXmlObject xcontrol = xcollection.children().search(objectId);
         if(null != xcontrol){
            xcontrols = xcontrol.children();
         }
      }
      if(null == xcontrols){
         throw new FFatalError("Control node is null.");
      }
      // 新建所有子节点
      FXmlNodes outputNodes = output.config().nodes();
      for(int n = 0; n < xcontrols.count(); n++){
         IXmlObject xcomponent = xcontrols.get(n);
         // 建立树节点
         XTreeNode xnode = new XTreeNode();
         xnode.setType(xcomponent.name());
         xnode.setUuid(xcomponent.objectId());
         xnode.setLabel(RString.nvl(xcomponent.innerGet(PTY_NAME), xcomponent.name()));
         xnode.setNote(xcomponent.innerGet(PTY_LABEL));
         xnode.setChild(xcomponent.hasChild());
         onBuildTreeNode(xnode, xcomponent);
         FXmlNode node = xnode.toSimpleNode();
         node.set("is_valid", xcomponent.innerGet("is_valid"));
         outputNodes.push(node);
      }
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_chartConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_chartConsole, context, input, output);
   }
}

package org.mo.jfa.face.design.webform;

import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.core.aop.face.ALink;
import org.mo.data.dataset.FDatasetConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.store.FXmlConfigMeta;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webform.FWebFormDatasetArgs;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webform.IWebFormDatasetConsole;
import org.mo.web.core.webform.control.XWebTable;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FWebFormService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IWebFormService
{

   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FDatasetConsole.class);

   @ALink
   protected IWebFormDatasetConsole _datasetConsole;

   @ALink
   protected IWebFormConsole _formConsole;

   @Override
   public void assign(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      //order(_formConsole, context, input, output);
   }

   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      // 获得上传的参数（节点过滤，节点排序）
      String nodeType = null;
      String nodeSort = null;
      FXmlNode xconfig = input.config().findNode("Attributes");
      if(null != xconfig){
         nodeType = xconfig.get(PTY_NODE_TYPE);
         nodeSort = xconfig.get(PTY_NODE_SORT, null);
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
            for(FXmlConfigMeta meta : _formConsole.listMetas()){
               if(RFile.removeExtension(meta.name()).equals(uuid)){
                  String name = meta.name();
                  IXmlObject xcollection = _formConsole.find(name);
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
      for(FXmlConfigMeta meta : _formConsole.listMetas()){
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
      delete(_formConsole, context, input, output);
   }

   @Override
   public void design(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      design(_formConsole, context, input, output);
   }

   @Override
   public void fetchLov(IWebContext context,
                        ISqlContext sqlContext,
                        IWebInput input,
                        IWebOutput output){
      FXmlNode inputNode = input.config().findNode("Control");
      FXmlNode outputNode = output.config();
      // 建立表单
      String formName = inputNode.get("lov_refer");
      FXmlNode formNode = _formConsole.build(formName, EXmlConfig.Simple);
      formNode.set("width", "100%");
      formNode.set("height", "100%");
      formNode.setName(XWebTable.NAME);
      for(FXmlNode node : formNode.nodes()){
         node.setName("ColumnEdit");
      }
      // 建立数据参数
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.setFormName(formName);
      FXmlNode dsNode = _datasetConsole.fetchNode(args);
      // 设置返回内容
      outputNode.push(formNode);
      outputNode.push(dsNode);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_formConsole, context, input, output);
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
      IXmlObject xcollection = _formConsole.get(collection);
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
   public void search(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      // 获得选中的内容
      FXmlNode config = input.config().findNode("Attributes");
      if(null == config){
         throw new FFatalError("Search node is null.");
      }
      String search = RString.toNvlLower(config.get("search"));
      String[] serachs = RString.split(search, ' ');
      serachs = RStrings.filterNotBlank(serachs);
      // 获得容器节点
      for(FXmlConfigMeta meta : _formConsole.listMetas()){
         String name = meta.name();
         // 过滤节点
         boolean searched = true;
         for(String searchItem : serachs){
            if(!name.toLowerCase().contains(searchItem)){
               searched = false;
               break;
            }
         }
         if(!searched){
            continue;
         }
         // 获得节点定义
         IXmlObject xcollection = _formConsole.get(name);
         String type = xcollection.name();
         // 新建树节点
         XTreeNode xnode = new XTreeNode();
         xnode.setType(type);
         xnode.setUuid(xcollection.objectId());
         xnode.setLabel(name);
         xnode.setNote(xcollection.innerGet(PTY_LABEL));
         xnode.setChild(xcollection.hasChild());
         xnode.set("form_name", name);
         // 生成树节点
         FXmlNode treeNode = xnode.toSimpleNode();
         treeNode.set("is_valid", xcollection.innerGet("is_valid"));
         output.config().push(treeNode);
      }
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_formConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_formConsole, context, input, output);
   }
}

package org.mo.game.editor.face.editor.dataset;

import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.core.aop.face.ALink;
import org.mo.core.bind.IBindConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.store.FXmlConfigMeta;
import org.mo.game.editor.core.dataset.IDatasetConsole;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FDatasetService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IDatasetService
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FDatasetService.class);

   public final static String NODE_LIST = "list";

   @ALink
   private IBindConsole _bindConsole;

   @ALink
   private IDatabaseConsole _databaseConsole;

   @ALink
   private IDatasetConsole _datasetConsole;

   @ALink
   private IWebFormConsole _formConsole;

   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      // 获得上传的参数（节点过滤，节点排序）
      String findType = null;
      String findSort = null;
      FXmlNode config = input.config().findNode("Attributes");
      if(null != config){
         findType = config.get(PTY_NODE_TYPE);
         findSort = config.get(PTY_NODE_SORT);
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
            for(FXmlConfigMeta meta : _datasetConsole.listMetas()){
               if(RFile.removeExtension(meta.name()).equals(uuid)){
                  String name = meta.name();
                  IXmlObject xcollection = _datasetConsole.find(name);
                  String type = xcollection.name();
                  // 过滤节点
                  if(!RString.isEmpty(findType) && !type.equals(findType)){
                     continue;
                  }
                  // 新建树节点
                  XTreeNode xnode = new XTreeNode();
                  xnode.setType(type);
                  xnode.setUuid(xcollection.objectId());
                  xnode.setLabel(name.substring(uuid.length() + 1));
                  xnode.setNote(xcollection.innerGet(PTY_LABEL));
                  xnode.setChild(xcollection.hasChild());
                  xnode.set("dataset_name", name);
                  FXmlNode treeNode = xnode.toSimpleNode();
                  treeNode.set("is_valid", xcollection.innerGet("is_valid"));
                  outputNodes.push(treeNode);
               }
            }
            // 控制排序
            if(null != findSort){
               outputNodes.sortByAttribute("asc".equals(findSort), PTY_LABEL);
            }else{
               outputNodes.sortByAttribute(PTY_LABEL);
            }
            return;
         }
      }
      // 统计节点包
      FAttributes nameMap = new FAttributes();
      FXmlNodes outputNodes = output.config().nodes();
      for(IXmlObject xobject : _datasetConsole.list()){
         String type = xobject.name();
         String fullName = xobject.innerGet("name");
         String path = RFile.removeExtension(fullName);
         // 过滤节点
         if(!RString.isEmpty(findType) && !type.equals(findType)){
            continue;
         }
         // 检查是否已经添加过
         if(nameMap.contains(path)){
            int count = RInteger.parse(nameMap.get(path));
            nameMap.set(path, Integer.toString(count + 1));
            continue;
         }
         nameMap.set(path, "1");
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
      if(null != findSort){
         outputNodes.sortByAttribute("asc".equals(findSort), "label");
      }else{
         outputNodes.sortByAttribute("label");
      }
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_datasetConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_datasetConsole, context, input, output);
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
         collection = nodeAttrs.get("dataset_name");
      }else if(TYPE_COMPONENT.equals(typeType)){
         FXmlNode topNode = selectNode.findDeep("Node", "type_type", TYPE_COLLECTION);
         nodeAttrs.unpack(topNode.get("attributes"));
         collection = nodeAttrs.get("dataset_name");
      }
      IXmlObject xcollection = _datasetConsole.get(collection);
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
      String search = config.get("search").toLowerCase();
      String[] serachs = RString.split(search, ' ');
      serachs = RStrings.filterNotBlank(serachs);
      // 获得容器节点
      for(IXmlObject xcollection : _datasetConsole.list()){
         String name = xcollection.innerGet(PTY_NAME);
         String type = xcollection.name();
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
         // 新建树节点
         XTreeNode xnode = new XTreeNode();
         xnode.setType(type);
         xnode.setUuid(xcollection.objectId());
         xnode.setLabel(name);
         xnode.setNote(xcollection.innerGet(PTY_LABEL));
         xnode.setChild(xcollection.hasChild());
         xnode.set("dataset_name", name);
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
      sort(_datasetConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_datasetConsole, context, input, output);
   }

}

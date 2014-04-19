package org.mo.jfa.face.database.dataset;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FStrings;
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
import org.mo.core.bind.IBindConsole;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlSearchField;
import org.mo.eng.data.common.FSqlSearchFields;
import org.mo.eng.entity.FEntityContext;
import org.mo.eng.entity.IEntityContext;
import org.mo.eng.store.FXmlConfigMeta;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>数据集合服务。</T>
//============================================================
public class FDatasetService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IDatasetService
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FDatasetService.class);

   // 排序字段定义
   public final static String NODE_LIST = "list";

   // 绑定控制台接口
   @ALink
   private IBindConsole _bindConsole;

   // 数据库控制台接口
   @ALink
   private IDatabaseConsole _databaseConsole;

   // 数据集合控制台接口
   @ALink
   private IDatasetConsole _datasetConsole;

   // 表单控制台接口
   @ALink
   private IWebFormConsole _formConsole;

   //============================================================
   // <T>获得数据集合目录。</T>
   //
   // @param context 网页环境
   // @param input 输入信息
   // @param output 输出信息
   //============================================================
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
         findSort = config.get(PTY_NODE_SORT, null);
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
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_datasetConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_datasetConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_datasetConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_datasetConsole, context, input, output);
   }

   @Override
   public void dsFetch(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      // Bind database
      FEntityContext entityContext = new FEntityContext(_databaseConsole);
      _bindConsole.bind(IEntityContext.class, entityContext);
      // Fetch
      for(FXmlNode node : input.config().nodes()){
         if(node.isName("Dataset")){
            String name = node.get("name");
            int page = RInteger.parse(node.get("page"));
            int pageSize = RInteger.parse(node.get("page_size"));
            // Build search fields
            FXmlNode searchNode = node.node("Search");
            FSqlSearchFields searchs = null;
            if(null != searchNode){
               searchs = new FSqlSearchFields();
               for(FXmlNode siNode : searchNode.nodes()){
                  if(siNode.isName("Item")){
                     FSqlSearchField field = new FSqlSearchField();
                     field.setName(siNode.get("data_name"));
                     field.setType(siNode.get("search_type"));
                     //field.setOrder(siNode.get("search_order"));
                     field.setValue(siNode.get("data_value"));
                     searchs.push(field);
                  }
               }
            }
            // Build fields
            FStrings fields = null;
            String formName = node.get("form_name");
            IXmlObject xform = _formConsole.find(formName);
            if(null != xform){
               fields = new FStrings();
               IXmlObjects children = xform.children();
               for(int n = 0; n < children.count(); n++){
                  IXmlObject xcontrol = children.get(n);
                  String dataName = xcontrol.innerGet("data_name");
                  if(RString.isNotEmpty(dataName)){
                     fields.push(dataName);
                  }
               }
            }
            // Config
            FXmlNode dsNode = output.config().nodes().create("Dataset");
            dsNode.set("name", name);
            FDataset dataset = _datasetConsole.dsFetch(name, fields, searchs, page, pageSize);
            for(FRow row : dataset){
               dsNode.createNode("Row").attributes().append(row);
            }
         }
      }
   }

   @Override
   public void dsPicker(IWebContext context,
                        IWebInput input,
                        IWebOutput output){
      // Bind database
      FEntityContext entityContext = new FEntityContext(_databaseConsole);
      _bindConsole.bind(IEntityContext.class, entityContext);
      // Picker
      FXmlNode ctlNode = input.config().node("Control");
      if(null != ctlNode){
         String lovRefer = ctlNode.get("lov_refer");
         IXmlObject xform = _formConsole.find(lovRefer);
         if(null != xform){
            // Build form
            output.setName("WebTable");
            output.set("name", "tbwPicker");
            int count = xform.children().count();
            for(int n = 0; n < count; n++){
               IXmlObject xcontrol = xform.children().get(n);
               FXmlNode ncontrol = output.config().nodes().create();
               xcontrol.saveConfig(ncontrol, EXmlConfig.Simple);
               ncontrol.setName("Column");
            }
            // Build dataset
            String dsName = xform.innerGet("dataset");
            FDataset dataset = _datasetConsole.dsFetch(dsName, null, null, 0, 100);
            if(null != dataset){
               for(int n = 0; n < dataset.count(); n++){
                  FRow row = dataset.get(n);
                  FXmlNode nrow = output.config().nodes().create("Row");
                  nrow.attributes().append(row);
               }
            }
         }
      }
   }

   @Override
   public void dsUpdate(IWebContext context,
                        IWebInput input,
                        IWebOutput output){
      // Bind database
      FEntityContext entityContext = new FEntityContext(_databaseConsole);
      _bindConsole.bind(IEntityContext.class, entityContext);
      // Update
      for(FXmlNode dsNode : input.config().nodes()){
         if(dsNode.isName("Dataset")){
            String dsName = dsNode.get("name");
            FXmlNode odsNode = output.config().nodes().create("Dataset");
            odsNode.set("name", dsName);
            int page = RInteger.parse(odsNode.get("page"));
            int pageSize = RInteger.parse(odsNode.get("page_size"));
            for(FXmlNode rowNode : dsNode.nodes()){
               if(rowNode.isName("Row")){
                  String flag = rowNode.getNvl("_flag_");
                  if(_logger.debugAble()){
                     _logger.debug(this, "dsUpdate", "flag={0}, row={1}", flag, rowNode.dump());
                  }
                  // Execute
                  if("I".equals(flag)){
                     _datasetConsole.dsInsert(dsName, rowNode.attributes());
                  }else if("U".equals(flag)){
                     _datasetConsole.dsUpdate(dsName, rowNode.attributes());
                  }else if("D".equals(flag)){
                     _datasetConsole.dsDelete(dsName, rowNode.attributes());
                  }
               }
            }
            // Fix data
            FDataset dataset = _datasetConsole.dsFetch(dsName, null, null, page, pageSize);
            for(FRow row : dataset){
               odsNode.createNode("Row").attributes().append(row);
            }
         }
      }
   }
}

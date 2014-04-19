package org.mo.jfa.common.xmlobjects;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.core.aop.face.ALink;
import org.mo.eng.store.IXmlConfigConsole;
import org.mo.eng.validator.IValidatorConsole;
import org.mo.eng.validator.common.FStringValidator;
import org.mo.jfa.common.page.RXmlObjects;
import org.mo.jfa.common.service.RServiceResult;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>配置对象服务。</T>
//============================================================
public class FAbsXmlObjectService<X extends IXmlObject>
      extends FAbsXmlObjectCommon
{
   // 校验控制台接口
   @ALink
   protected IValidatorConsole _validatorConsole;

   //============================================================
   // console从配置文件中加载树目录
   protected void catalog(IXmlConfigConsole<X> console,
                          IWebContext context,
                          IWebInput input,
                          IWebOutput output){
      // 获得上传的参数（节点过滤，节点排序）
      String nodeType = null;
      String nodeFilter = null;
      String nodeSort = null;
      FXmlNode config = input.config().findNode(NODE_ENVIRONMENT);
      if(null != config){
         nodeType = config.get(PTY_NODE_TYPE);
         nodeFilter = config.get(PTY_NODE_FILTER);
         nodeSort = config.get(PTY_NODE_SORT);
      }
      // 建立树节点，生成xml文件
      FXmlNodes outputNodes = output.config().nodes();
      for(IXmlObject xcollection : console.list()){
         String type = xcollection.name();
         String name = xcollection.innerGet(PTY_NAME);
         // 过滤节点
         if(RString.isNotEmpty(nodeType) && !nodeType.equalsIgnoreCase(type)){
            continue;
         }
         if(RString.isNotEmpty(nodeFilter) && !RString.matchString(name, nodeFilter)){
            continue;
         }
         // 新建树节点
         XTreeNode xnode = new XTreeNode();
         xnode.setType(type);
         xnode.setUuid(xcollection.objectId());
         xnode.setLabel(RString.nvl(name, xcollection.name()));
         xnode.setNote(xcollection.innerGet("label"));
         xnode.setChild(xcollection.hasChild());
         xnode.set("linker_name", xcollection.innerGet("name"));
         onBuildTreeNode(xnode, xcollection);
         FXmlNode node = xnode.toSimpleNode();
         node.set("is_valid", xcollection.innerGet("is_valid"));
         outputNodes.push(node);
      }
      // 控制排序
      if(null != nodeSort){
         outputNodes.sortByAttribute("asc".equals(nodeSort), "label");
      }else{
         outputNodes.sortByAttribute("label");
      }
   }

   //============================================================
   @SuppressWarnings("unchecked")
   protected void delete(IXmlConfigConsole<X> console,
                         IWebContext context,
                         IWebInput input,
                         IWebOutput output){
      // 获得上传数据
      FXmlNode envNode = getEnvironmentNode(input);
      // 判断操作类型
      String type = envNode.get(PTY_SEL_TYPE);
      String collection = envNode.get(PTY_SEL_COLLECTION);
      IXmlObject xcollection = console.get(collection);
      if(TYPE_COLLECTION.equals(type)){
         // 删除选中的XML集合对象
         console.remove((X)xcollection);
      }else if(TYPE_COMPONENT.equals(type)){
         // 删除选中的XML对象
         String component = envNode.get(PTY_SEL_COMPONENT);
         IXmlObject xcomponent = xcollection.children().search(component);
         if(null == xcomponent){
            throw new FFatalError("Xml component is not found. (collection={1},component={2})", collection, component);
         }
         // 查找要删除XML对象的父节点对象
         IXmlObject xparent = xcollection.searchParent(component);
         if(null == xparent){
            throw new FFatalError("Xml parent is not found. (collection={1},component={2})", collection, component);
         }
         // 删除XML对象
         xparent.children().remove(xcomponent.objectId());
         console.store((X)xcollection);
      }else{
         throw new FFatalError("Unknown select type. (type={0})", type);
      }
      // 刷新树目录
      RServiceResult.setTreeParentRefresh(output);
      RServiceResult.setPageRedirect(output, IPublicPage.PROCESS_END_INSERT);
   }

   //============================================================
   protected void design(IXmlConfigConsole<X> console,
                         IWebContext context,
                         IWebInput input,
                         IWebOutput output){
      // 获得环境对象
      FXmlNode envNode = getEnvironmentNode(input);
      String type = envNode.get(PTY_SEL_TYPE);
      String collection = envNode.get(PTY_SEL_COLLECTION);
      // 查找XML集合对象
      X xcollection = console.get(collection);
      String name = xcollection.innerGet("name");
      FXmlNode formNode = input.config().findNode("name", name);
      if(null == formNode){
         throw new FFatalError("Design form config is null. (name={1})", name);
      }
      // 根据类型来选择操作
      IXmlObject xsort = null;
      if(TYPE_COLLECTION.equals(type)){
         xsort = xcollection;
      }else if(TYPE_COMPONENT.equals(type)){
         String component = envNode.get(PTY_SEL_COMPONENT);
         IXmlObject xcomponent = xcollection.children().search(component);
         if(null == xcomponent){
            throw new FFatalError("Component is null. (collection={0}, component={1})", collection, component);
         }
         xsort = xcomponent;
      }else{
         throw new FFatalError("Unknown select type. (type={0})", type);
      }
      // 对集合中对象进行重新排序
      if(RXmlObjects.sortNameByNode(xsort, formNode)){
         console.store(xcollection);
      }
      // 刷新树目录节点
      RServiceResult.setTreeRefresh(output);
      RServiceResult.setPageRedirect(output, IPublicPage.PROCESS_SUCCESS);
   }

   //============================================================
   @SuppressWarnings("unchecked")
   protected void insert(IXmlConfigConsole<X> console,
                         IWebContext context,
                         IWebInput input,
                         IWebOutput output){
      // 获得上传数据
      FXmlNode envNode = getEnvironmentNode(input);
      FXmlNode dataNode = getDataNode(input);
      // 判断操作类型
      String selectType = envNode.get(PTY_SEL_TYPE);
      String uuid = null;
      if(TYPE_COLLECTION.equals(selectType)){
         // 基本数据检查
         String type = dataNode.get(PTY_TYPE);
         String name = dataNode.get(PTY_NAME);
         FStringValidator sv = _validatorConsole.find(FStringValidator.class);
         sv.checkEmpty(context.messages(), type, PTY_TYPE);
         sv.checkEmpty(context.messages(), name, PTY_NAME);
         context.messages().check();
         dataNode.setName(type);
         // 新建XML数据集
         IXmlObject xcollection = console.createElement(dataNode, EXmlConfig.Value);
         console.persist((X)xcollection);
         uuid = xcollection.objectId();
         // 刷新树目录
         RServiceResult.setTreeReload(output);
      }else if(TYPE_COMPONENT.equals(selectType)){
         // 查找选中的表单和控件
         String collection = envNode.get(PTY_SEL_COLLECTION);
         IXmlObject xcollection = console.get(collection);
         // 查找父控件，当是顶层控件时，父控件为空
         IXmlObject xparent = null;
         String parentControl = envNode.get(PTY_SEL_COMPONENT);
         if(RString.isNotEmpty(parentControl)){
            xparent = xcollection.children().search(parentControl);
            if(null == xparent){
               throw new FFatalError("Parent control is null. (tree={0},control={1})", xcollection, parentControl);
            }
         }
         // 检查控件的名称和类型有效性
         String type = dataNode.get(PTY_TYPE);
         String name = dataNode.get(PTY_NAME);
         FStringValidator sv = _validatorConsole.find(FStringValidator.class);
         sv.checkEmpty(context.messages(), type, PTY_TYPE);
         sv.checkEmpty(context.messages(), name, PTY_NAME);
         context.messages().check();
         dataNode.setName(type);
         // 新建控件
         IXmlObject xcontrol = console.createElement(dataNode, EXmlConfig.Value);
         if(null != xparent){
            xparent.children().push(xcontrol);
            uuid = xparent.objectId();
         }else{
            xcollection.children().push(xcontrol);
            uuid = xcollection.objectId();
         }
         console.store((X)xcollection);
         // 刷新树目录
         RServiceResult.setTreeNodeRefresh(output, uuid);
      }else{
         throw new FFatalError("Unknown select type. (type={0})", selectType);
      }
      // 设置页面转向
      RServiceResult.setPageRedirect(output, IPublicPage.PROCESS_END_INSERT);
   }

   //============================================================
   @SuppressWarnings("unchecked")
   protected void insertCollection(IXmlConfigConsole<X> console,
                                   IWebContext context,
                                   IWebInput input,
                                   IWebOutput output){
      // 获得上传数据
      FXmlNode data = input.config().findNode("Data");
      if(null == data){
         throw new FFatalError("Collection config is null.");
      }
      // 基本数据检查
      String type = data.get(PTY_TYPE);
      String name = data.get(PTY_NAME);
      FStringValidator sv = _validatorConsole.find(FStringValidator.class);
      sv.checkEmpty(context.messages(), type, PTY_TYPE);
      sv.checkEmpty(context.messages(), name, PTY_NAME);
      context.messages().check();
      data.setName(type);
      // 新建XML数据集
      IXmlObject xcollection = console.createElement(data, EXmlConfig.Value);
      console.persist((X)xcollection);
   }

   //============================================================
   @SuppressWarnings("unchecked")
   protected void insertComponent(IXmlConfigConsole<X> console,
                                  IWebContext context,
                                  IWebInput input,
                                  IWebOutput output){
      // 获得上传数据
      FXmlNode data = input.config().findNode("Data");
      if(null == data){
         throw new FFatalError("Component config is null.");
      }
      FXmlNode envNode = input.config().findNode("Environment");
      if(null == envNode){
         throw new FFatalError("Can't find environment node.");
      }
      // 查找选中的表单和控件
      String collection = envNode.get(PTY_SEL_COLLECTION);
      IXmlObject xcollection = console.get(collection);
      String parentControl = envNode.get(PTY_SEL_COMPONENT);
      IXmlObject xparent = null;
      if(RString.isNotEmpty(parentControl)){
         xparent = xcollection.children().search(parentControl);
         if(null == xparent){
            throw new FFatalError("Parent control is null. (tree={0},control={1})", xcollection, parentControl);
         }
      }
      // 检查控件的名称和类型有效性
      String type = data.get(PTY_TYPE);
      String name = data.get(PTY_NAME);
      FStringValidator sv = _validatorConsole.find(FStringValidator.class);
      sv.checkEmpty(context.messages(), type, PTY_TYPE);
      sv.checkEmpty(context.messages(), name, PTY_NAME);
      context.messages().check();
      data.setName(type);
      // 新建控件
      IXmlObject xcontrol = console.createElement(data, EXmlConfig.Value);
      if(null != xparent){
         xparent.children().push(xcontrol);
      }else{
         xcollection.children().push(xcontrol);
      }
      console.store((X)xcollection);
   }

   //============================================================
   protected String makeCollectionName(String name){
      return name;
   }

   //============================================================
   protected void list(IXmlConfigConsole<X> console,
                       IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      // 获得选中的内容
      FXmlNode selectNode = getSelectNode(input);
      FAttributes nodeAttrs = new FAttributes();
      // 获得容器节点
      String collection = null;
      String typeType = selectNode.get("type_type");
      if(TYPE_COLLECTION.equals(typeType)){
         collection = selectNode.get("label");
         collection = makeCollectionName(collection);
      }else if(TYPE_COMPONENT.equals(typeType)){
         FXmlNode topNode = selectNode.findDeep("Node", "type_type", TYPE_COLLECTION);
         nodeAttrs.unpack(topNode.get("attributes"));
         if(nodeAttrs.contains("linker_name")){
            collection = nodeAttrs.get("linker_name");
         }else{
            collection = topNode.get("label");
         }
         collection = makeCollectionName(collection);
      }
      IXmlObject xcollection = console.get(collection);
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
         String port = null;
         if(xcomponent.innerGet("port") != null){
            port = xcomponent.innerGet("port");
            xnode.setLabel(RString.nvl(xcomponent.innerGet(PTY_NAME), xcomponent.name())+"."+port);
         }else{
            xnode.setLabel(RString.nvl(xcomponent.innerGet(PTY_NAME), xcomponent.name()));
         }
         
         xnode.setNote(xcomponent.innerGet(PTY_LABEL));
         xnode.setChild(xcomponent.hasChild());
         onBuildTreeNode(xnode, xcomponent);
         FXmlNode node = xnode.toSimpleNode();
         node.set("is_valid", xcomponent.innerGet("is_valid"));
         outputNodes.push(node);
      }
   }

   //============================================================
   protected void onBuildTreeNode(XTreeNode xnode,
                                  IXmlObject xobject){
   }

   //============================================================
   protected void saveCollection(IXmlConfigConsole<X> console,
                                 IWebContext context,
                                 IWebInput input,
                                 IWebOutput output){
      // 查找上传的控件数据信息
      FXmlNode data = input.config().findNode("Data");
      if(null == data){
         throw new FFatalError("Collection config is null.");
      }
      // 查找目录树和控件定义对象
      FXmlNode envNode = input.config().findNode("Environment");
      String collection = envNode.get(PTY_SEL_COLLECTION);
      if(RString.isEmpty(collection)){
         throw new FFatalError("Can't find selected collection.");
      }
      X xcollection = console.get(collection);
      // 读取目录树设置
      xcollection.loadConfig(data, EXmlConfig.Value);
      // 存储表单
      console.store(xcollection);
   }

   //============================================================
   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webform.IWebFormService#saveControl(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput, org.mo.jfa.face.design.webform.FWebFormPage)
    */
   @SuppressWarnings("unchecked")
   protected void saveComponent(IXmlConfigConsole<X> console,
                                IWebContext context,
                                IWebInput input,
                                IWebOutput output){
      // 获得上传数据
      FXmlNode envNode = getEnvironmentNode(input);
      FXmlNode dataNode = getDataNode(input);
      // 查找表单和控件定义对象
      String type = envNode.get(PTY_SEL_TYPE);
      String collection = envNode.get(PTY_SEL_COLLECTION);
      String component = envNode.get(PTY_SEL_COMPONENT);
      // 查找XML集合对象
      IXmlObject xcollection = console.get(collection);
      // 根据类型来选择操作
      if(TYPE_COLLECTION.equals(type)){
         // 存储XML集合对象
         xcollection.loadConfig(dataNode, EXmlConfig.Value);
      }else if(TYPE_COMPONENT.equals(type)){
         // 存储XML对象
         IXmlObject xcomponent = xcollection.children().search(component);
         if(null == xcomponent){
            throw new FFatalError("Component is null. (collection={0}, component={1})", collection, component);
         }
         // 更新操作
         String componentType = dataNode.get(PTY_TYPE);
         if(RString.equalsIgnoreCase(componentType, xcomponent.name())){
            // 读取控件设置
            xcomponent.loadConfig(dataNode, EXmlConfig.Value);
         }else{
            // 查找当前控件的父控件
            IXmlObject xparent = xcollection.searchParent(xcomponent.objectId());
            if(null == xparent){
               throw new FFatalError("Control parent is null. (collection={0}, component={1})", collection, component);
            }
            // 创建指定类型的新控件
            dataNode.setName(componentType);
            IXmlObject xcreate = console.createElement(dataNode, EXmlConfig.Value);
            if(null == xcreate){
               throw new FFatalError("Create component failure. (collection={0}, config={1})", collection, dataNode.xml());
            }
            int index = xparent.children().indexOf(xcomponent);
            if(-1 == index){
               throw new FFatalError("Find component index failure. (collection={0}, component={1})", collection, component);
            }
            xparent.children().set(index, xcreate);
            // 刷新树内容
            RServiceResult.setTreeParentRefresh(output);
         }
      }else{
         throw new FFatalError("Unknown select type. (type={0})", type);
      }
      // 存储表单
      console.store((X)xcollection);
   }

   //============================================================
   protected void sort(IXmlConfigConsole<X> console,
                       IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      // 获得环境对象
      FXmlNode envNode = input.config().findNode("Environment");
      if(null == envNode){
         throw new FFatalError("Environment config is null.");
      }
      // 查找XML集合对象
      String collection = envNode.get(PTY_SEL_COLLECTION);
      X xcollection = console.get(collection);
      FXmlNode sortForm = input.config().findNode("FWebForm");
      FXmlNode sortNode = sortForm.findNode("FListBox", "name", NAME_SORT);
      if(null == sortNode){
         throw new FFatalError("Sort config is null.");
      }
      // 根据类型来选择操作
      String type = envNode.get(PTY_SEL_TYPE);
      IXmlObject xsort = null;
      if(TYPE_COLLECTION.equals(type)){
         xsort = xcollection;
      }else if(TYPE_COMPONENT.equals(type)){
         String component = envNode.get(PTY_SEL_COMPONENT);
         IXmlObject xcomponent = xcollection.children().search(component);
         if(null == xcomponent){
            throw new FFatalError("Component is null. (collection={0}, component={1})", collection, component);
         }
         xsort = xcomponent;
      }else{
         throw new FFatalError("Unknown select type. (type={0})", type);
      }
      // 对集合中对象进行重新排序
      if(RXmlObjects.sortUuidByNode(xsort, sortNode, false)){
         console.store(xcollection);
      }
      // 刷新树目录节点
      RServiceResult.setTreeRefresh(output);
      RServiceResult.setPageRedirect(output, IPublicPage.PROCESS_SUCCESS);
   }

   //============================================================
   protected void update(IXmlConfigConsole<X> console,
                         IWebContext context,
                         IWebInput input,
                         IWebOutput output){
      // 获得上传数据
      FXmlNode envNode = getEnvironmentNode(input);
      // 判断操作类型
      String type = envNode.get(PTY_SEL_TYPE);
      if(TYPE_COLLECTION.equals(type)){
         saveCollection(console, context, input, output);
      }else if(TYPE_COMPONENT.equals(type)){
         saveComponent(console, context, input, output);
      }else{
         throw new FFatalError("Unknown select type. (type={0})", type);
      }
   }

}

package org.mo.jfa.common.catalog;

import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;

public class FAbstractCatalogAction
      extends FAbstractCatalogCommon
{

   protected FXmlNode parseBusinessNode(){
      return null;
   }

   protected FXmlNodes parseBusinessNodes(){
      return null;
   }

   //   public final static String TYPE_COLLECTION = "collection";
   //
   //   public final static String TYPE_COMPONENT = "component";
   //
   //   @ALink
   //   protected IWebFormConsole _formConsole;
   //
   //   @ALink
   //   protected IValidatorConsole _validatorConsole;
   //
   //   @ALink
   //   protected ITemplateConsole _templateConsole;
   //
   //   public String catalog(IXmlConfigConsole<X> console,
   //                         IWebContext context,
   //                         FAbsCatalogPage<X> page,
   //                         String redirect){
   //      // 获得上传的数据
   //      page.appachContext(context);
   //      return redirect;
   //   }
   //
   //   public String delete(IXmlConfigConsole<X> console,
   //                        IWebContext context,
   //                        FAbsCatalogPage<X> page,
   //                        String redirect){
   //      // 获得上传的数据
   //      page.appachContext(context);
   //      // 查找选中的XML集合对象和XML对象
   //      String collection = page.getSelectCollection();
   //      X xcollection = console.get(collection);
   //      page.setCollection(xcollection);
   //      // 判断操作类型
   //      String type = page.getSelectType();
   //      if(TYPE_COLLECTION.equals(type)){
   //         // 删除选中的XML集合对象
   //         console.remove(xcollection);
   //      }else if(TYPE_COMPONENT.equals(type)){
   //         // 删除选中的XML对象
   //         String component = page.getSelectComponent();
   //         IXmlObject xcomponent = xcollection.children().search(component);
   //         if(null == xcomponent){
   //            throw new FFatalError("Xml component is not found. (collection={0},component={1})", collection, component);
   //         }
   //         // 查找要删除XML对象的父节点对象
   //         IXmlObject xparent = xcollection.searchParent(component);
   //         if(null == xcomponent){
   //            throw new FFatalError("Xml parent is not found. (collection={0},component={1})", collection, component);
   //         }
   //         // 删除XML对象
   //         xparent.children().remove(xcomponent.objectId());
   //         console.store(xcollection);
   //      }
   //      // 刷新树目录
   //      page.resetCommands();
   //      page.setTreeParentRefresh();
   //      return IPublicPage.PROCESS_END_DELETE;
   //   }
   //
   //   public String design(IXmlConfigConsole<X> console,
   //                        IWebContext context,
   //                        FAbsCatalogPage<X> page,
   //                        String redirect){
   //      // 获得上传的数据
   //      page.appachContext(context);
   //      // 查找选中的XML集合对象和XML对象
   //      String collection = page.getSelectCollection();
   //      X xcollection = console.get(collection);
   //      page.setCollection(xcollection);
   //      // 判断操作类型
   //      String type = page.getSelectType();
   //      if(TYPE_COLLECTION.equals(type)){
   //         // 存储选中的XML集合对象
   //         page.setFormValue(RXmlObject.convertToXml(xcollection, EXmlConfig.Value));
   //         page.setFormConfig(RXmlObject.convertDeepToXml(xcollection, EXmlConfig.Value));
   //      }else if(TYPE_COMPONENT.equals(type)){
   //         // 存储选中的XML对象
   //         String component = page.getSelectComponent();
   //         IXmlObject xcomponent = xcollection.children().search(component);
   //         if(null == xcomponent){
   //            throw new FFatalError("Xml component is not found. (collection={0},component={1})", collection, component);
   //         }
   //         page.setComponent(xcomponent);
   //         page.setFormValue(RXmlObject.convertToXml(xcomponent, EXmlConfig.Value));
   //         page.setFormConfig(RXmlObject.convertDeepToXml(xcomponent, EXmlConfig.Value));
   //      }else{
   //         throw new FFatalError("Unknown select type. (type={0})", type);
   //      }
   //      return redirect;
   //   }
   //
   //   public IAttributes findEnvPack(IWebContext context){
   //      String pack = context.parameter("env_pack");
   //      if(RString.isNotEmpty(pack)){
   //         FAttributes attributes = new FAttributes();
   //         attributes.unpack(pack);
   //         return attributes;
   //      }
   //      return null;
   //   }
   //
   //   /**
   //    * 获得系统上传的数据设置
   //    * 
   //    * @param input 上传数据
   //    * @return 数据设置
   //    */
   //   public X getSelectCollection(IXmlConfigConsole<X> console,
   //                                FAbsCatalogPage<X> page){
   //      String collection = page.getSelectCollection();
   //      return console.get(collection);
   //   }
   //
   //   public String help(IXmlConfigConsole<X> console,
   //                      IWebContext context,
   //                      FAbsCatalogPage<X> page,
   //                      ITemplateParser parser,
   //                      String redirect){
   //      // 获得上传的数据
   //      page.appachContext(context);
   //      // 查找选中的XML集合对象和XML对象
   //      String collection = page.getSelectCollection();
   //      X xcollection = console.get(collection);
   //      page.setCollection(xcollection);
   //      FXmlNode config = RXmlObject.convertDeepToNode(xcollection, EXmlConfig.Value);
   //      parser.define("collection", config);
   //      // 判断操作类型
   //      String type = page.getSelectType();
   //      if(TYPE_COLLECTION.equals(type)){
   //         // 存储选中的XML集合对象
   //         parser.define("config", config);
   //      }else if(TYPE_COMPONENT.equals(type)){
   //         // 存储选中的XML对象
   //         String component = page.getSelectComponent();
   //         IXmlObject xcomponent = xcollection.children().search(component);
   //         if(null == xcomponent){
   //            throw new FFatalError("Xml component is not found. (collection={0},component={1})", collection, component);
   //         }
   //         config = RXmlObject.convertDeepToNode(xcomponent, EXmlConfig.Value);
   //         page.setComponent(xcomponent);
   //         parser.define("component", config);
   //         parser.define("config", config);
   //      }else{
   //         throw new FFatalError("Unknown select type. (type={0})", type);
   //      }
   //      FString source = parser.parse();
   //      page.setHelp(source.toString());
   //      return redirect;
   //   }
   //
   //   public String insert(IXmlConfigConsole<X> console,
   //                        IWebContext context,
   //                        FAbsCatalogPage<X> page,
   //                        String redirect){
   //      // 获得上传的数据
   //      page.appachContext(context);
   //      // 重置表单数据
   //      FXmlNode config = new FXmlNode("Config");
   //      config.set(PTY_TYPE, context.parameter(PTY_TYPE));
   //      page.setFormValue(config.xml());
   //      return redirect;
   //   }
   //
   //   public String list(IXmlConfigConsole<X> console,
   //                      IWebContext context,
   //                      FAbsCatalogPage<X> page,
   //                      String redirect){
   //      // 获得上传的数据
   //      page.appachContext(context);
   //      // 获得列表数据
   //      FXmlNode config = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
   //      FXmlNode dsNode = config.createNode(FDataset.NAME);
   //      dsNode.set("name", page.getFormName());
   //      for(X item : console.list()){
   //         FXmlNode rowNode = dsNode.createNode();
   //         item.saveConfig(rowNode, EXmlConfig.Simple);
   //         rowNode.setName(FRow.NAME);
   //      }
   //      page.setFormValue(config.xml());
   //      return redirect;
   //   }
   //
   //   protected void setFormValue(FAbsCatalogPage<X> page,
   //                               IXmlObject xobject){
   //      FXmlNode config = RXmlObject.convertToNode(xobject, EXmlConfig.Value);
   //      config.setText(null);
   //      page.setFormValue(config.xml());
   //   }
   //
   //   public String sort(IXmlConfigConsole<X> console,
   //                      IWebContext context,
   //                      FAbsCatalogPage<X> page,
   //                      String redirect){
   //      // 获得上传的数据
   //      page.appachContext(context);
   //      String formName = page.getFormName();
   //      FXmlNode buildNode = _formConsole.build(formName, EXmlConfig.Value);
   //      FXmlNode orderActionNode = buildNode.findNode("name", ACTION_SORT);
   //      if(null == orderActionNode){
   //         throw new FFatalError("Can't find sort action in form config. (form={0})", formName);
   //      }
   //      orderActionNode.setName("ConfigAction");
   //      // 查找选中的XML集合对象
   //      String collection = page.getSelectCollection();
   //      X xcollection = console.get(collection);
   //      page.setCollection(xcollection);
   //      // 判断操作类型
   //      String type = page.getSelectType();
   //      IXmlObjects xcomponents = null;
   //      if(TYPE_COLLECTION.equals(type)){
   //         xcomponents = xcollection.children();
   //      }else if(TYPE_COMPONENT.equals(type)){
   //         // 存储选中的XML对象
   //         String component = page.getSelectComponent();
   //         IXmlObject xcomponent = xcollection.children().search(component);
   //         if(null == xcomponent){
   //            throw new FFatalError("Xml component is not found. (collection={0},component={1})", collection, component);
   //         }
   //         xcomponents = xcomponent.children();
   //         page.setComponent(xcomponent);
   //      }else{
   //         throw new FFatalError("Unknown select type. (type={0})", type);
   //      }
   //      // 建立ListBox对象
   //      FXmlNode formNode = new FXmlNode(XWebForm.NAME);
   //      formNode.set("name", "sortForm");
   //      formNode.set("width", "100%");
   //      formNode.set("height", "100%");
   //      formNode.push(orderActionNode);
   //      FXmlNode listNode = formNode.createNode("ListBox");
   //      listNode.set("name", "sortList");
   //      for(int n = 0; n < xcomponents.count(); n++){
   //         IXmlObject xchild = xcomponents.get(n);
   //         String label = RString.nvl(xchild.innerGet("label"), xchild.innerGet("note"));
   //         // 建立节点
   //         FXmlNode itemNode = listNode.createNode("ListItem");
   //         itemNode.set("name", xchild.objectId());
   //         itemNode.set("label", xchild.innerGet("name") + "(" + label + ")");
   //      }
   //      page.setFormConfig(formNode.xml());
   //      return redirect;
   //   }
   //
   //   public String update(IXmlConfigConsole<X> console,
   //                        IWebContext context,
   //                        FAbsCatalogPage<X> page,
   //                        String redirect){
   //      // 获得上传的数据
   //      page.appachContext(context);
   //      // 查找选中的XML集合对象和XML对象
   //      String collection = page.getSelectCollection();
   //      X xcollection = console.get(collection);
   //      page.setCollection(xcollection);
   //      // 判断操作类型
   //      String type = page.getSelectType();
   //      if(TYPE_COLLECTION.equals(type)){
   //         // 存储选中的XML集合对象
   //         page.setFormValue(RXmlObject.convertToXml(xcollection, EXmlConfig.Value));
   //      }else if(TYPE_COMPONENT.equals(type)){
   //         // 存储选中的XML对象
   //         String component = page.getSelectComponent();
   //         IXmlObject xcomponent = xcollection.children().search(component);
   //         if(null == xcomponent){
   //            throw new FFatalError("Xml component is not found. (collection={0},component={1})", collection, component);
   //         }
   //         setFormValue(page, xcomponent);
   //         page.setComponent(xcomponent);
   //      }else{
   //         throw new FFatalError("Unknown select type. (type={0})", type);
   //      }
   //      return redirect;
   //   }
}

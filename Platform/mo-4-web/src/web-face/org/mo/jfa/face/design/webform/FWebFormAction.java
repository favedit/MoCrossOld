package org.mo.jfa.face.design.webform;

import org.mo.com.data.FSqlParameter;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.store.IXmlConfigConsole;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.logic.data.ISyModuleDi;
import org.mo.logic.data.ISyTranslateDi;
import org.mo.logic.session.ILogicSessionConsole;
import org.mo.logic.subscribe.FLogicSubscribeDeployThread;
import org.mo.web.core.webform.control.XListViewFace;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>表单命令。</T>
//============================================================
public class FWebFormAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IWebFormAction
{
   // 日志输出接口
   @SuppressWarnings("unused")
   private final static ILogger _logger = RLogger.find(FLogicSubscribeDeployThread.class);

   // 页面目录定义
   public final static String PAGE_CATALOG = "Catalog";

   // 业务控制台接口
   @ALink
   protected ILogicSessionConsole _sessionConsole;

   //============================================================
   // <T>生成目录处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   @Override
   public String catalog(IWebContext context,
                         FWebFormPage page){
      return catalog(_formConsole, context, page, PAGE_CATALOG);
   }

   //============================================================
   // <T>列表处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   @Override
   public String list(IWebContext context,
                      FWebFormPage page){
      return list(_formConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   // <T>新建处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   @Override
   public String insert(IWebContext context,
                        FWebFormPage page){
      return insert(_formConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   // <T>更新处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   @Override
   public String update(IWebContext context,
                        FWebFormPage page){
      return update(_formConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   // <T>删除处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   @Override
   public String delete(IWebContext context,
                        FWebFormPage page){
      return delete(_formConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   // <T>排序处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   @Override
   public String sort(IWebContext context,
                      FWebFormPage page){
      return sort(_formConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   //============================================================
   // <T>设计处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   @Override
   public String design(IWebContext context,
                        FWebFormPage page){
      page.setFormService("design.webform");
      return design(_formConsole, context, page, IPublicPage.XOBJECT_DESIGN);
   }

   @Override
   public String allCheck(IWebContext context,
                          FWebFormPage page){
      for(IXmlObject xds : _formConsole.list()){
         if(RBoolean.parse(xds.innerGet("is_valid"))){
            if(xds.hasChild()){
               checkChild(xds);
            }
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webform.IWebFormAction#allSyncModule(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.design.webform.FWebFormPage)
    */
   @Override
   public String allSyncModule(IWebContext context,
                               ISyModuleDi syModuleDi,
                               FWebFormPage page){
      // 循环创建表单或表格
      for(IXmlObject xds : _formConsole.list()){
         if(("WebTable".equals(xds.name())) || ("WebForm".equals(xds.name()))){
            FXmlNode node = _formConsole.build(xds.innerGet("name"), EXmlConfig.Simple);
            FAttributes attributes = new FAttributes();
            attributes = node.attributes();
            attributes.set("type_name", node.name());
            if("WebTable".equals(node.name())){
               attributes.set("type_label", "表格");
            }else{
               attributes.set("type_label", "表单");
            }
            attributes.set("parent_id", "0");
            attributes.set("top_id", "0");
            attributes.set("name_path", node.get("name"));
            attributes.set("label_path", node.get("label"));
            attributes.set("name", node.get("name"));
            attributes.set("label", node.get("label"));
            attributes.set("icon_id", null);
            attributes.set("do_format", null);
            attributes.set("do_redirect", node.get("name"));
            attributes.set("do_invoke", null);
            attributes.set("note", node.get("note"));
            attributes.pack();
            FSqlProcedure module = syModuleDi.syncModule(_sessionConsole.makeLogic(), attributes);
            FSqlParameter parameter = module.parameter("parameters_");
            String sParameter = parameter.asString();
            attributes.unpack(sParameter);
            node.set("parent_id", attributes.get("parent_id"));
            node.set("top_id", attributes.get("top_id"));
            if(node.hasNode()){
               syncChildModule(syModuleDi, node, node);
            }
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String allSyncTranslate(IWebContext context,
                                  ISyTranslateDi syTranslateDi,
                                  FWebFormPage page){
      // 同步翻译组
      IAttributes attributes = new FAttributes();
      attributes.set("name", "design.webform");
      attributes.set("code", "webform");
      attributes.set("label", "设计-表单");
      attributes.set("type_cd", "F");
      syTranslateDi.doSyncGroup(_sessionConsole.makeLogic(), attributes);
      for(IXmlObject xds : _formConsole.list()){
         FXmlNode formNode = _formConsole.build(xds.innerGet("name"), EXmlConfig.Simple);
         if(("WebTable".equals(formNode.name())) || ("WebForm".equals(formNode.name()))){
            // 同步翻译l列表
            String formName = formNode.get("name");
            attributes.clear();
            //formName = "F|" + formName;
            attributes.set("group_name", "design.webform");
            attributes.set("name", formName);
            attributes.set("label", formNode.get("label"));
            attributes.set("code", formName);
            attributes.set("type_cd", "F");
            syTranslateDi.doSyncList(_sessionConsole.makeLogic(), attributes);
            // 同步翻译
            attributes.set("list_name", attributes.get("name"));
            attributes.set("code", "F|" + formName);
            attributes.set("content", formName);
            attributes.set("status_cd", "A");
            syTranslateDi.doSyncTranslate(_sessionConsole.makeLogic(), attributes);
            attributes.set("language_name", "SC");
            attributes.set("translate_code", attributes.get("code"));
            attributes.set("content", formNode.get("label"));
            // 同步翻译的内容例如翻译成中文
            syTranslateDi.doSyncContent(_sessionConsole.makeLogic(), attributes);
            syncTranslateProperty(syTranslateDi, formNode, attributes);
            if(formNode.hasNode()){
               syncChildTranslate(formNode, syTranslateDi, attributes);
            }
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String buildSqlSource(IWebContext context,
                                FWebFormPage page){
      // 获得上传的数据
      page.appachContext(context);
      // 查找选中的XML集合对象和XML对象
      String collection = page.getSelectCollection();
      IXmlObject xcollection = _formConsole.get(collection);
      String component = page.getSelectComponent();
      IXmlObject xcomponent = xcollection.children().search(component);
      if(null == xcomponent){
         throw new FFatalError("Xml view is not found. (collection={1},component={2})", collection);
      }
      page.setComponent(xcomponent);
      // 返回数据
      page.setSqlSource(xcomponent.innerText());
      return "SqlSource";
   }

   protected void checkChild(IXmlObject parentForm){
      for(IXmlObject child : parentForm.children()){
         String typeName = child.name();
         // 检查模板
         if("Template".equals(typeName)){
            _formConsole.buildTemplate(child.innerGet("template_name"), EXmlConfig.Default);
         }
         String lovName = child.innerGet(XListViewFace.PTY_LOV_REFER);
         if(RString.isNotEmpty(lovName)){
            _formConsole.buildLov(lovName);
         }
         if(child.hasChild()){
            checkChild(child);
         }
      }
   }

   @Override
   public String design(IXmlConfigConsole<IXmlObject> console,
                        IWebContext context,
                        FAbsXmlObjectPage<IXmlObject> page,
                        String redirect){
      // 获得上传的数据

      page.appachContext(context);
      // 查找选中的XML集合对象和XML对象
      String collection = page.getSelectCollection();
      IXmlObject xcollection = console.get(collection);
      page.setCollection(xcollection);
      // 判断操作类型
      String type = page.getSelectType();
      if(TYPE_COLLECTION.equals(type)){
         // 存储选中的XML集合对象
         String formName = xcollection.innerGet("name");
         FXmlNode config = _formConsole.buildDesign(formName, EXmlConfig.Value);
         page.setFormConfig(config.xml());
         page.setFormValue(RXmlObject.convertToXml(xcollection, EXmlConfig.Value));
      }else if(TYPE_COMPONENT.equals(type)){
         // 存储选中的XML对象
         String component = page.getSelectComponent();
         IXmlObject xcomponent = xcollection.children().search(component);
         if(null == xcomponent){
            throw new FFatalError("Xml component is not found. (collection={1},component={2})", collection, component);
         }
         page.setComponent(xcomponent);
         page.setFormValue(RXmlObject.convertToXml(xcomponent, EXmlConfig.Value));
         page.setFormConfig(RXmlObject.convertDeepToXml(xcomponent, EXmlConfig.Value));
      }else{
         throw new FFatalError("Unknown select type. (type={1})", type);
      }
      return redirect;
   }

   @Override
   public String sqlSourceSave(IWebContext context,
                               FWebFormPage page){
      // 获得上传的数据
      page.appachContext(context);
      // 查找选中的XML集合对象和XML对象
      IXmlObject xcomponent = page.getComponent();
      IXmlObject xcollection = page.getCollection();
      if(null == xcomponent){
         throw new FFatalError("Xml component is not found. (collection={1},component={2})", xcollection, xcomponent);
      }
      // 返回数据
      String SqlSource = page.getSqlSource();
      xcomponent.setInnerText(SqlSource);
      _formConsole.store(xcollection);
      return IPublicPage.PROCESS_SUCCESS;
   }

   private void syncChildModule(ISyModuleDi syModuleDi,
                                FXmlNode child,
                                FXmlNode parentNode){
      for(FXmlNode node : child.nodes()){
         if(("List".equals(node.name())) || ("WebStyle".equals(node.name())) || ("WebForm").equals(node.name()) || ("WebTable".equals(node.name())) || ("Item".equals(node.name()))){
            continue;
         }
         FAttributes attributes = new FAttributes();
         attributes.set("type_name", (parentNode.name() + "." + node.name()));
         if("WebTable".equals(parentNode.name())){
            attributes.set("type_label", "表格-" + node.get("label"));
         }else{
            attributes.set("type_label", "表单-" + node.get("label"));
         }
         attributes.set("parent_id", parentNode.get("parent_id"));
         attributes.set("top_id", parentNode.get("top_id"));
         attributes.set("name", node.get("name"));
         attributes.set("label", node.get("label"));
         attributes.set("name_path", parentNode.get("name") + "|" + node.get("name"));
         attributes.set("label_path", parentNode.get("label") + "|" + node.get("label"));
         attributes.set("icon_id", null);
         attributes.set("do_format", null);
         attributes.set("do_redirect", null);
         attributes.set("do_invoke", null);
         attributes.set("note", node.get("note"));
         syModuleDi.syncModule(_sessionConsole.makeLogic(), attributes);
         if(node.hasNode()){
            syncChildModule(syModuleDi, node, parentNode);
         }
      }
   }

   protected void syncChildTranslate(FXmlNode parentNode,
                                     ISyTranslateDi syTranslateDi,
                                     IAttributes parentAttributes){
      for(FXmlNode node : parentNode.nodes()){
         if("Item".equals(node.name())){
            return;
         }
         IAttributes attributes = new FAttributes();
         attributes.set("group_name", parentAttributes.get("group_name"));
         attributes.set("list_name", parentAttributes.get("list_name"));
         String code = parentAttributes.get("code");
         code = code + "|" + node.fullPath("name", '/');
         attributes.set("code", code);
         attributes.set("type_cd", "F");
         attributes.set("status_cd", "A");
         attributes.set("content", node.get("name"));
         syTranslateDi.doSyncTranslate(_sessionConsole.makeLogic(), attributes);
         attributes.set("language_name", "SC");
         attributes.set("translate_code", attributes.get("code"));
         attributes.set("content", node.get("label"));
         syTranslateDi.doSyncContent(_sessionConsole.makeLogic(), attributes);
         syncTranslateProperty(syTranslateDi, node, attributes);
         if(node.hasNode()){
            syncChildTranslate(node, syTranslateDi, parentAttributes);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webform.IWebFormAction#syncModule(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.design.webform.FWebFormPage)
    */
   @Override
   public String syncModule(IWebContext context,
                            ISyModuleDi syModuleDi,
                            FWebFormPage page){
      String formName = page.getSelectCollection();
      // 创建当前的表单或表格
      FXmlNode node = _formConsole.build(formName, EXmlConfig.Simple);
      if(("WebTable".equals(node.name())) || ("WebForm".equals(node.name()))){
         FAttributes attributes = new FAttributes();
         attributes.set("type_name", node.name());
         if("WebTable".equals(node.name())){
            attributes.set("type_label", "表格");
         }else{
            attributes.set("type_label", "表单");
         }
         attributes.set("parent_id", "0");
         attributes.set("top_id", "0");
         attributes.set("name_path", node.get("name"));
         attributes.set("label_path", node.get("label"));
         attributes.set("name", node.get("name"));
         attributes.set("label", node.get("label"));
         attributes.set("icon_id", null);
         attributes.set("do_format", null);
         attributes.set("do_redirect", node.get("name"));
         attributes.set("do_invoke", null);
         attributes.set("note", node.get("note"));
         attributes.pack();
         FSqlProcedure module = syModuleDi.syncModule(_sessionConsole.makeLogic(), attributes);
         FSqlParameter parameter = module.parameter("parameters_");
         String sParameter = parameter.asString();
         attributes.unpack(sParameter);
         node.set("parent_id", attributes.get("parent_id"));
         node.set("top_id", attributes.get("top_id"));
         if(node.hasNode()){
            syncChildModule(syModuleDi, node, node);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   // 同步表单及控件的属性
   protected void syncTranslateProperty(ISyTranslateDi syTranslateDi,
                                        FXmlNode node,
                                        IAttributes parentAttributes){
      if(null != node.get("edit_tip")){
         IAttributes attributes = new FAttributes();
         String code = parentAttributes.get("code") + ":edit_tip";
         attributes.set("group_name", parentAttributes.get("group_name"));
         attributes.set("list_name", parentAttributes.get("list_name"));
         attributes.set("code", code);
         attributes.set("content", node.get("name"));
         attributes.set("status_cd", "A");
         attributes.set("type_cd", "F");
         syTranslateDi.doSyncTranslate(_sessionConsole.makeLogic(), attributes);
         attributes.set("language_name", "SC");
         attributes.set("translate_code", attributes.get("code"));
         attributes.set("content", node.get("edit_tip"));
         syTranslateDi.doSyncContent(_sessionConsole.makeLogic(), attributes);
      }
      if(null != node.get("hint")){
         IAttributes attributes = new FAttributes();
         String code = parentAttributes.get("code") + ":hint";
         attributes.set("group_name", parentAttributes.get("group_name"));
         attributes.set("list_name", parentAttributes.get("list_name"));
         attributes.set("code", code);
         attributes.set("content", node.get("name"));
         attributes.set("status_cd", "A");
         attributes.set("type_cd", "F");
         syTranslateDi.doSyncTranslate(_sessionConsole.makeLogic(), attributes);
         attributes.set("language_name", "SC");
         attributes.set("translate_code", attributes.get("code"));
         attributes.set("content", node.get("hint"));
         syTranslateDi.doSyncContent(_sessionConsole.makeLogic(), attributes);
      }
      if(null != node.get("note")){
         IAttributes attributes = new FAttributes();
         String code = parentAttributes.get("code") + ":note";
         attributes.set("group_name", parentAttributes.get("group_name"));
         attributes.set("list_name", parentAttributes.get("list_name"));
         attributes.set("code", code);
         attributes.set("content", node.get("name"));
         attributes.set("status_cd", "A");
         attributes.set("type_cd", "F");
         syTranslateDi.doSyncTranslate(_sessionConsole.makeLogic(), attributes);
         attributes.set("language_name", "SC");
         attributes.set("translate_code", attributes.get("code"));
         attributes.set("content", node.get("note"));
         syTranslateDi.doSyncContent(_sessionConsole.makeLogic(), attributes);
      }
   }
}

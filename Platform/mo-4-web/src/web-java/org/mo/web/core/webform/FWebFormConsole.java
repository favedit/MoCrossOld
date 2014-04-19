package org.mo.web.core.webform;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.temp.RAttributes;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodeMap;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.cache.FCache;
import org.mo.eng.cache.ICache;
import org.mo.eng.cache.ICacheConsole;
import org.mo.eng.list.IListConsole;
import org.mo.eng.list.TListArgs;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.web.core.webform.control.XCheckPicker;
import org.mo.web.core.webform.control.XColumnSelect;
import org.mo.web.core.webform.control.XEvent;
import org.mo.web.core.webform.control.XPageSheet;
import org.mo.web.core.webform.control.XSelect;
import org.mo.web.core.webform.control.XSql;
import org.mo.web.core.webform.control.XTemplate;
import org.mo.web.core.webform.control.XWebDialog;
import org.mo.web.core.webform.control.XWebForm;
import org.mo.web.core.webform.control.XWebPicker;
import org.mo.web.core.webform.control.XWebStyle;
import org.mo.web.core.webform.control.XWebTable;
import org.mo.web.core.webform.control.XWebTemplate;

//============================================================
// <T>网页表单控制台。</T>
//============================================================
public class FWebFormConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         IWebFormConsole
{
   // 控件名称
   public final static String NAME_CONTROL = "control";

   // 标签名称
   public final static String NAME_LABEL = "label";

   public final static String PTY_DATASET_SEARCH = "dataset_search";

   // 编辑样式引用
   public final static String PTY_STYLE_EDIT = "style_edit";

   // 标签样式引用
   public final static String PTY_STYLE_LABEL = "style_label";

   // 控件样式引用
   public final static String PTY_STYLE_REFER = "style_refer";

   // 缓冲管理器的接口
   @ALink
   protected ICacheConsole _cacheConsole;

   @AProperty
   protected long _cacheTimeout;

   // 列表访问的接口
   @ALink
   protected IListConsole _listConsole;

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormConsole#build(java.lang.String, org.mo.com.xml.EXmlConfig)
    */
   @Override
   public FXmlNode build(String name,
                         EXmlConfig type){
      try{
         IXmlObject xform = get(name);
         FXmlNode config = new FXmlNode();
         buildFormConfig(EWebFormBuild.WebForm, xform, xform, config, null, new TWebFormArgs(name, type));
         return config;
      }catch(Exception e){
         throw new FFatalError(e, "Build form error. (name={1}, type={2})", name, type);
      }
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormConsole#build(org.mo.web.core.webform.TWebFormArgs)
    */
   @Override
   public FXmlNode build(TWebFormArgs args){
      try{
         // 获得表单的原始定义
         IXmlObject xform = get(args.name());
         // 创建结构对象
         FXmlNode config = new FXmlNode();
         buildFormConfig(EWebFormBuild.WebForm, xform, xform, config, null, args);
         return config;
      }catch(Exception e){
         throw new FFatalError(e, "Build form error. (args={1})", args.dump());
      }
   }

   @Override
   public FXmlNode buildConfig(TWebFormArgs args){
      return findConfig(args).copy();
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormConsole#buildDesign(java.lang.String, org.mo.com.xml.EXmlConfig)
    */
   @Override
   public FXmlNode buildDesign(String name,
                               EXmlConfig type){
      try{
         IXmlObject xform = get(name);
         FXmlNode config = new FXmlNode();
         buildFormConfig(EWebFormBuild.Design, xform, xform, config, null, new TWebFormArgs(name, type));
         return config;
      }catch(Exception e){
         throw new FFatalError(e, "Build form error. (name={1}, type={2})", name, type);
      }
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormConsole#buildEvent(java.lang.String)
    */
   @Override
   public FXmlNode buildEvent(String name){
      try{
         IXmlObject xform = get(name);
         FXmlNode config = new FXmlNode("Events");
         buildFormConfig(EWebFormBuild.Event, xform, xform, config, null, new TWebFormArgs(name, EXmlConfig.Full));
         return config;
      }catch(Exception e){
         throw new FFatalError(e, "Build event error. (name={1})", name);
      }
   }

   @Override
   public FXmlNode buildEvent(TWebFormArgs args){
      try{
         IXmlObject xform = get(args.name());
         args.setConfig(EXmlConfig.Full);
         FXmlNode config = new FXmlNode("Events");
         buildFormConfig(EWebFormBuild.Event, xform, xform, config, null, args);
         return config;
      }catch(Exception e){
         throw new FFatalError(e, "Build event error. (args={1})", args);
      }
   }

   // 建立表单设置
   protected void buildFormConfig(EWebFormBuild mode,
                                  IXmlObject xtop,
                                  IXmlObject xobject,
                                  FXmlNode config,
                                  FXmlNodeMap styleNodes,
                                  TWebFormArgs args){
      // 处理预备数据
      if(isCollection(xobject)){
         // 处理样式引用
         String styleRefer = xobject.innerGet(XWebForm.PTY_STYLE_REFER);
         if(RString.isNotEmpty(styleRefer)){
            styleNodes = buildStyleNode(styleRefer, EXmlConfig.Default);
         }
         // 处理父类继承
         String parentName = xobject.innerGet(XWebForm.PTY_PARENT_NAME);
         if(RString.isNotEmpty(parentName)){
            IXmlObject xparent = get(parentName);
            buildFormConfig(mode, xtop, xparent, config, styleNodes, args);
         }
      }
      // 处理类型为事件时，不处理样式信息
      if(EWebFormBuild.Event != mode){
         // 保存信息到XML节点
         if(null != styleNodes && !isCollection(xobject)){
            // 标签样式引用
            FXmlNode styleLabelNode = null;
            String styleLabel = xobject.innerGet(PTY_STYLE_LABEL);
            if(RString.isNotEmpty(styleLabel)){
               styleLabelNode = styleNodes.find(styleLabel);
               if(null == styleLabelNode){
                  throw new FFatalError("Style label refer is not exists. (style={1})", styleLabel);
               }
            }else{
               // 使用系统默认的标签类型查找样式
               styleLabelNode = styleNodes.get(NAME_LABEL);
            }
            // 编辑框样式引用
            FXmlNode styleEditNode = null;
            String styleEdit = xobject.innerGet(PTY_STYLE_EDIT);
            if(RString.isNotEmpty(styleEdit)){
               // 控件含有样式引用
               styleEditNode = styleNodes.find(styleEdit);
               if(null == styleEditNode){
                  throw new FFatalError("Style edit refer is not exists. (style={1})", styleEdit);
               }
            }else{
               // 使用控件的类型查找样式
               styleEditNode = styleNodes.find(xobject.name());
            }
            // 控件样式引用
            FXmlNode styleControlNode = null;
            String styleControl = xobject.innerGet(PTY_STYLE_REFER);
            if(RString.isNotEmpty(styleControl)){
               styleControlNode = styleNodes.get(styleControl);
               if(null == styleControlNode){
                  throw new FFatalError("Style control refer is not exists. (style={1})", styleControl);
               }
            }
            // 复制默认内容
            if(null != styleControlNode){
               RAttributes.appendEmpty(config.attributes(), styleControlNode.attributes());
            }
            if(null != styleEditNode){
               RAttributes.appendEmpty(config.attributes(), styleEditNode.attributes());
            }
            if(null != styleLabelNode){
               RAttributes.appendEmpty(config.attributes(), styleLabelNode.attributes());
            }
         }
         // 保存节点信息
         xobject.saveConfig(config, args.config());
         // 根据XML对象类型建立XML子节点
         String name = xobject.name();
         if(XSelect.isName(name)){
            // 处理下拉菜单
            XSelect xselect = (XSelect)xobject;
            String editRefer = xselect.getEditRefer();
            if(RString.isNotEmpty(editRefer)){
               FXmlNode xlist = _listConsole.buildListConfig(new TListArgs(editRefer, args.sqlContext()));
               config.pushNodes(xlist.nodes());
            }
         }else if(XColumnSelect.isName(name)){
            // 处理单元格内的下拉菜单
            XColumnSelect xselect = (XColumnSelect)xobject;
            String editRefer = xselect.getEditRefer();
            if(RString.isNotEmpty(editRefer)){
               FXmlNode xlist = _listConsole.buildListConfig(new TListArgs(editRefer, args.sqlContext()));
               config.pushNodes(xlist.nodes());
            }
         }else if(XCheckPicker.isName(name)){
            // 处理单元格内的多选下拉菜单
            XCheckPicker xselect = (XCheckPicker)xobject;
            String editRefer = xselect.getEditRefer();
            if(RString.isNotEmpty(editRefer)){
               FXmlNode xlist = _listConsole.buildListConfig(new TListArgs(editRefer, args.sqlContext()));
               config.pushNodes(xlist.nodes());
            }
         }else if(XPageSheet.isName(name)){
            // 处理分页的页面引用
            XPageSheet xsheet = (XPageSheet)xobject;
            String formName = xsheet.getFormRefer();
            if(RString.isNotEmpty(formName)){
               FXmlNode referNode = build(new TWebFormArgs(formName, args.config(), args.sqlContext()));
               if(referNode.isName("WebTable")){
                  referNode.setName("Table");
               }else if(referNode.isName("WebFixTable")){
                  referNode.setName("FixTable");
               }else if(referNode.isName("WebGrid")){
                  referNode.setName("Grid");
               }else if(referNode.isName("WebFixGrid")){
                  referNode.setName("FixGrid");
               }
               String formLink = xsheet.getFormLink();
               if(!RString.isEmpty(formLink)){
                  String search = referNode.get(PTY_DATASET_SEARCH);
                  if(!RString.isEmpty(search)){
                     formLink += " AND " + search;
                  }
                  referNode.set(PTY_DATASET_SEARCH, formLink);
               }
               config.push(referNode);
            }
         }
      }
      // 递归处理所有子XML对象
      if(xobject.hasChild()){
         IXmlObjects xchildren = xobject.children();
         int count = xchildren.count();
         for(int n = 0; n < count; n++){
            IXmlObject xchild = xchildren.get(n);
            // 检查节点的有效性
            if(!RBoolean.parse(xchild.innerGet("IS_VALID"))){
               continue;
            }
            // 如果是过滤模式，则去掉所有非使用节点
            if(args.isSkipUnused()){
               // 过滤查询节点
               if(XSql.isInstance(xchild)){
                  continue;
               }
            }
            // 根据模式生成节点
            switch(mode){
            // 在设计模式下，不加载模板内容
               case Design:
                  buildFormConfig(mode, xtop, xchild, config.createNode(), styleNodes, args);
                  break;
               // 在事件模式下，递归处理模板，只加载事件对象
               case Event:
                  if(XEvent.isInstance(xchild)){
                     // 处理事件节点的情况
                     FXmlNode eventNode = config.createNode();
                     xchild.saveConfig(eventNode, EXmlConfig.Simple);
                     eventNode.set(XEvent.PTY_FORM, xtop.innerGet(PTY_NAME));
                     eventNode.set(XEvent.PTY_SOURCE, xobject.innerGet(PTY_NAME));
                  }else if(XTemplate.isInstance(xchild)){
                     // 处理模板引用的情况
                     XTemplate xrefer = (XTemplate)xchild;
                     String templateName = xrefer.getTemplateName();
                     if(RString.isNotEmpty(templateName)){
                        IXmlObject xtemplate = getTemplate(templateName);
                        buildFormTemplate(mode, xtop, xtemplate, config, styleNodes, args);
                     }
                  }else{
                     // 非处理模板引用的情况
                     buildFormConfig(mode, xtop, xchild, config, styleNodes, args);
                  }
                  break;
               // 在表单模式下，递归处理模板，只加载除事件外的对象
               case WebForm:
                  //if(!XEvent.isInstance(xchild)){
                  if(XTemplate.isInstance(xchild)){
                     // 处理模板引用的情况
                     XTemplate xrefer = (XTemplate)xchild;
                     String templateName = xrefer.getTemplateName();
                     if(RString.isNotEmpty(templateName)){
                        IXmlObject xtemplate = getTemplate(templateName);
                        buildFormTemplate(mode, xtop, xtemplate, config, styleNodes, args);
                     }
                  }else{
                     // 不处理模板引用的情况
                     buildFormConfig(mode, xtop, xchild, config.createNode(), styleNodes, args);
                  }
                  //}
                  break;
            }
         }
      }
   }

   // 建立表单模板
   protected void buildFormTemplate(EWebFormBuild mode,
                                    IXmlObject xtop,
                                    IXmlObject xtemplate,
                                    FXmlNode config,
                                    FXmlNodeMap xstyles,
                                    TWebFormArgs args){
      if(xtemplate.hasChild()){
         IXmlObjects xchildren = xtemplate.children();
         int count = xchildren.count();
         for(int n = 0; n < count; n++){
            IXmlObject xchild = xchildren.get(n);
            if(XTemplate.NAME.equals(xchild.name())){
               // 处理模板引用的情况
               XTemplate xrefer = (XTemplate)xchild;
               String templateName = xrefer.getTemplateName();
               if(RString.isNotEmpty(templateName)){
                  IXmlObject xchildTemplate = getTemplate(templateName);
                  buildFormTemplate(mode, xtop, xchildTemplate, config, xstyles, args);
               }
            }else{
               // 不同控件引用的情况
               if(EWebFormBuild.Event == mode){
                  buildFormConfig(mode, xtop, xchild, config, xstyles, args);
               }else{
                  buildFormConfig(mode, xtop, xchild, config.createNode(), xstyles, args);
               }
            }
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormConsole#buildLov(java.lang.String)
    */
   @Override
   public FXmlNode buildLov(String name){
      try{
         // 获得表单定义
         IXmlObject xform = get(name);
         FXmlNode config = new FXmlNode();
         buildFormConfig(EWebFormBuild.WebForm, xform, xform, config, null, new TWebFormArgs(name, EXmlConfig.Simple));
         // 修正输出内容
         //         FXmlNode formNode = new FXmlNode(XWebTable.NAME);
         //         formNode.attributes().append(config.attributes());
         //         formNode.set("width", "100%");
         //         formNode.set("height", "100%");
         //         for(FXmlNode node : config.nodes()){
         //            String access = node.get("DISP_ACCESS");
         //            if(RPropertyMaker.hasAccess(access, RPropertyMaker.DISP_LOV)){
         //               FXmlNode columnNode = formNode.createNode(XColumn.NAME);
         //               columnNode.attributes().append(node.attributes());
         //            }
         //         }
         config.set("width", "100%");
         config.set("height", "100%");
         return config;
      }catch(Exception e){
         throw new FFatalError(e, "Build list of view error. (name={1})", name);
      }
   }

   // 建立样式节点
   protected void buildStyleNode(FXmlNodeMap styleNodes,
                                 String name,
                                 EXmlConfig type){
      // 检查样式表单名称
      IXmlObject xobject = get(name);
      if(!XWebStyle.NAME.equals(xobject.name())){
         throw new FFatalError("Form is not style form. (name={1})", name);
      }
      // 检查样式表单的样式引用
      XWebStyle webStyle = (XWebStyle)xobject;
      String styleRefer = webStyle.getStyleRefer();
      if(RString.isNotEmpty(styleRefer)){
         buildStyleNode(styleNodes, styleRefer, type);
      }
      // 建立样式
      if(webStyle.hasChild()){
         IXmlObjects xchildren = webStyle.children();
         int count = xchildren.count();
         for(int n = 0; n < count; n++){
            IXmlObject xchild = xchildren.get(n);
            String value = xchild.innerGet(PTY_NAME);
            if(RString.isNotEmpty(value)){
               FXmlNode styleNode = styleNodes.find(value);
               if(styleNode == null){
                  styleNodes.set(value, RXmlObject.convertToNode(xchild, type));
               }else{
                  xchild.saveConfig(styleNode, type);
               }
            }
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormConsole#buildStyleNode(java.lang.String, org.mo.com.xml.EXmlConfig)
    */
   @Override
   public FXmlNodeMap buildStyleNode(String name,
                                     EXmlConfig type){
      FXmlNodeMap styleNodes = new FXmlNodeMap();
      styleNodes.setCareCase(false);
      buildStyleNode(styleNodes, name, type);
      return styleNodes;
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormConsole#buildTemplate(java.lang.String, org.mo.com.xml.EXmlConfig)
    */
   @Override
   public FXmlNode buildTemplate(String name,
                                 EXmlConfig type){
      try{
         IXmlObject xform = get(name);
         FXmlNode config = new FXmlNode();
         buildTemplateConfig(xform, config, new TWebFormArgs(name, type));
         return config;
      }catch(Exception e){
         throw new FFatalError(e, "Build template form error. (name={1}, type={2})", name, type);
      }
   }

   // 建立模板设置
   protected void buildTemplateConfig(IXmlObject xobject,
                                      FXmlNode config,
                                      TWebFormArgs args){
      // 保存信息到XML节点
      xobject.saveConfig(config, args.config());
      // 根据XML对象类型建立XML节点
      String name = xobject.name();
      if(XSelect.NAME.equals(name)){
         // 处理下拉菜单
         XSelect xselect = (XSelect)xobject;
         String editRefer = xselect.getEditRefer();
         if(RString.isNotEmpty(editRefer)){
            FXmlNode xlist = _listConsole.buildListConfig(new TListArgs(editRefer, args.sqlContext()));
            config.pushNodes(xlist.nodes());
         }
      }else if(XPageSheet.NAME.equals(name)){
         // 处理分页的页面引用
         XPageSheet xsheet = (XPageSheet)xobject;
         String formName = xsheet.getFormRefer();
         if(RString.isNotEmpty(formName)){
            config.push(build(formName, args.config()));
         }
      }
      // 递归处理所有子XML对象
      if(xobject.hasChild()){
         IXmlObjects xchildren = xobject.children();
         int count = xchildren.count();
         for(int n = 0; n < count; n++){
            buildTemplateConfig(xchildren.get(n), config.createNode(), args);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.eng.data.xml.FXmlConfigConsole#createArray()
    */
   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }

   @Override
   public FXmlNode findConfig(TWebFormArgs args){
      String name = args.name();
      FXmlNode config = null;
      // 从缓冲对象中获得定义
      String cacheName = name + ":" + args.config().toString();
      ICache cache = _cacheConsole.find(IWebFormConsole.class, cacheName);
      if(null != cache){
         config = (FXmlNode)cache.instance();
      }else{
         // 生成数据结构
         config = build(args);
         // 注册缓冲对象
         cache = new FCache(config);
         cache.setTimeout(_cacheTimeout);
         _cacheConsole.register(IWebFormConsole.class, cacheName, cache);
      }
      return config;
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.webform.IWebFormConsole#getTemplate(java.lang.String)
    */
   @Override
   public XWebTemplate getTemplate(String name){
      return (XWebTemplate)get(name);
   }

   // 判断是否为集合对象
   private boolean isCollection(IXmlObject xobject){
      String name = xobject.name();
      return XWebForm.NAME.equals(name) || XWebTable.NAME.equals(name) || XWebPicker.NAME.equals(name) || XWebDialog.NAME.equals(name);
   }

}

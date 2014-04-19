package org.mo.jfa.face.design.persistence;

import org.mo.com.lang.FFatalError;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.persistence.EPersistenceBuildAction;
import org.mo.eng.persistence.EPersistenceSourceType;
import org.mo.eng.persistence.FPersistenceBuilderArgs;
import org.mo.eng.persistence.IPersistenceConsole;
import org.mo.eng.persistence.common.XPersistence;
import org.mo.eng.template.ITemplateParser;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.core.format.ESyntax;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.core.format.ISyntaxMaker;
import org.mo.web.protocol.context.IWebContext;

public class FPersistenceAction
      extends FAbsXmlObjectAction<XPersistence>
      implements
         IPersistenceAction
{

   public final static String PAGE_CATALOG = "Catalog";

   public final static String PAGE_SOURCE = "Source";

   @ALink
   protected IPersistenceConsole _persistenceConsole;

   @ALink
   protected IFormatConsole _formatConsole;

   @Override
   public String build(IWebContext context,
                       FPersistencePage page){
      String collection = context.parameter(PTY_SEL_COLLECTION);
      IXmlObject xcollection = _persistenceConsole.get(collection);
      // 设置代码生成参数
      FPersistenceBuilderArgs args = new FPersistenceBuilderArgs();
      args.setPersistence((XPersistence)xcollection);
      // 根据类型分别处理
      String type = context.parameter(PTY_SEL_TYPE);
      if(TYPE_COLLECTION.equals(type)){
         args.setAction(EPersistenceBuildAction.Store);
         args.setType(EPersistenceSourceType.Persistence);
         _persistenceConsole.build(args);
         return IPublicPage.PROCESS_SUCCESS;
      }else if(TYPE_COMPONENT.equals(type)){
         String component = context.parameter(PTY_SEL_COMPONENT);
         IXmlObject xcomponent = xcollection.search(component);
         if(null == xcomponent){
            throw new FFatalError("Xml component is not found. (collection={0},component={1})", collection, component);
         }
         args.setAction(EPersistenceBuildAction.Query);
         args.setType(EPersistenceSourceType.Component);
         args.setComponent(xcomponent);
         _persistenceConsole.build(args);
      }else{
         throw new FFatalError("Unknown select type. (type={0})", type);
      }
      // 为代码进行HTML上色
      ISyntaxMaker syntaxMaker = _formatConsole.find(ESyntax.Java);
      page.setSource(syntaxMaker.format(args.source()));
      return PAGE_SOURCE;
   }

   @Override
   public String buildAll(IWebContext context,
                          FPersistencePage page){
      // 设置代码生成参数
      FPersistenceBuilderArgs args = new FPersistenceBuilderArgs();
      args.setAction(EPersistenceBuildAction.Store);
      args.setType(EPersistenceSourceType.Persistence);
      // 建立所有的类
      for(XPersistence persistence : _persistenceConsole.list()){
         args.setPersistence(persistence);
         _persistenceConsole.build(args);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String catalog(IWebContext context,
                         FPersistencePage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FPersistencePage page){
      return delete(_persistenceConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String execute(IWebContext context,
                         FPersistencePage page){
      String collection = page.getSelectCollection();
      IXmlObject xcollection = _persistenceConsole.get(collection);
      // 设置代码生成参数
      FPersistenceBuilderArgs args = new FPersistenceBuilderArgs();
      args.setAction(EPersistenceBuildAction.Store);
      args.setPersistence((XPersistence)xcollection);
      // 根据类型分别处理
      String type = page.getSelectType();
      if(TYPE_COLLECTION.equals(type)){
         args.setType(EPersistenceSourceType.Persistence);
         _persistenceConsole.build(args);
      }else if(TYPE_COMPONENT.equals(type)){
         String component = page.getSelectComponent();
         IXmlObject xcomponent = xcollection.search(component);
         if(null == xcomponent){
            throw new FFatalError("Xml component is not found. (collection={0},component={1})", collection, component);
         }
         args.setType(EPersistenceSourceType.Component);
         args.setComponent(xcomponent);
         _persistenceConsole.build(args);
      }else{
         throw new FFatalError("Unknown select type. (type={0})", type);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String help(IWebContext context,
                      FPersistencePage page){
      ITemplateParser parser = _templateConsole.findParser("helper.design", "persistence.detail");
      return help(_persistenceConsole, context, page, parser, IPublicPage.XOBJECT_HELP);
   }

   @Override
   public String insert(IWebContext context,
                        FPersistencePage page){
      return insert(_persistenceConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FPersistencePage page){
      return list(_persistenceConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FPersistencePage page){
      return sort(_persistenceConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FPersistencePage page){
      return update(_persistenceConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}

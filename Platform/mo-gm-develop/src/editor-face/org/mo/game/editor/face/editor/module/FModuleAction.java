package org.mo.game.editor.face.editor.module;

import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.module.IModuleConsole;
import org.mo.game.editor.core.module.common.XEntityGroup;
import org.mo.game.editor.face.apl.page.IPublicPage;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.web.core.format.ESyntax;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.core.format.ISyntaxMaker;
import org.mo.web.protocol.context.IWebContext;

public class FModuleAction
      extends FAbsXmlObjectAction<XEntityGroup>
      implements
         IModuleAction{

   public final String PAGE_CATALOG = "Catalog";

   public final String SEL_COL = "sel_collection";

   public final static String PAGE_SOURCE = "Source";

   @ALink
   protected IModuleConsole _entityConsole;

   @ALink
   protected IFormatConsole _formatConsole;

   //============================================================
   @Override
   public String buildAllSource(IWebContext context,
                                FModulePage page){
      _entityConsole.buildSourceAll();
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   @Override
   public String buildSource(IWebContext context,
                             FModulePage page){
      String sourceType = context.parameter("type");
      IXmlObject xentityGroup = _entityConsole.find(context.parameter(SEL_COL));
      if(RBoolean.parse(xentityGroup.innerGet(XEntityGroup.PTY_IS_VALID))){
         String sourceName = xentityGroup.innerGet(XEntityGroup.PTY_SOURCE_NAME);
         page.setSourceName(sourceName);
         FString source = _entityConsole.buildSource(xentityGroup);
         ISyntaxMaker syntaxMaker = _formatConsole.find(ESyntax.Cpp);
         page.setSource(syntaxMaker.format(source));
         page.setSourceType(sourceType);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   @Override
   public String buildView(IWebContext context,
                           FModulePage page){
      String sourceType = context.parameter("type");
      IXmlObject xentityGroup = _entityConsole.find(context.parameter(SEL_COL));
      if(RBoolean.parse(xentityGroup.innerGet(XEntityGroup.PTY_IS_VALID))){
         String sourceName = xentityGroup.innerGet(XEntityGroup.PTY_SOURCE_NAME);
         page.setSourceName(sourceName);
         FString source = _entityConsole.buildView(xentityGroup, sourceType);
         ISyntaxMaker syntaxMaker = _formatConsole.find(ESyntax.Cpp);
         page.setSource(syntaxMaker.format(source));
         page.setSourceType(sourceType);
      }
      return PAGE_SOURCE;
   }

   @Override
   public String catalog(IWebContext context,
                         FModulePage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FModulePage page){
      return delete(_entityConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String execute(IWebContext context,
                         FModulePage page){
      IXmlObject xmessageGroup = _entityConsole.find(context.parameter(SEL_COL));
      String sourceType = context.parameter("type");
      String sourceName = xmessageGroup.innerGet(XEntityGroup.PTY_SOURCE_NAME);
      FString source = _entityConsole.buildView(xmessageGroup, sourceType);
      if("head".equalsIgnoreCase(sourceType)){
         String filepath = _entityConsole.buildStorePath(sourceName + ".h");
         RFile.saveToFile(filepath, source, REncoding.GBK);
      }else if("body".equalsIgnoreCase(sourceType)){
         String filepath = _entityConsole.buildStorePath(sourceName + ".cpp");
         RFile.saveToFile(filepath, source, REncoding.GBK);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String insert(IWebContext context,
                        FModulePage page){
      return insert(_entityConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FModulePage page){
      return list(_entityConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FModulePage page){
      return sort(_entityConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FModulePage page){
      return update(_entityConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}

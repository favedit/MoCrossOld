package org.mo.game.editor.face.editor.storage;

import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.storage.IStorageConsole;
import org.mo.game.editor.core.storage.common.XGroup;
import org.mo.game.editor.face.apl.page.IPublicPage;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.web.core.format.ESyntax;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.core.format.ISyntaxMaker;
import org.mo.web.protocol.context.IWebContext;

public class FStorageAction
      extends FAbsXmlObjectAction<XGroup>
      implements
         IStorageAction{

   public final String PAGE_CATALOG = "Catalog";

   public final String SEL_COL = "sel_collection";

   public final static String PAGE_SOURCE = "Source";

   @ALink
   protected IStorageConsole _storageConsole;

   @ALink
   protected IFormatConsole _formatConsole;

   @Override
   public String buildAllSource(IWebContext context,
                                FStoragePage page){
      FString source = new FString();
      for(IXmlObject xentityGroup : _storageConsole.list()){
         if(RBoolean.parse(xentityGroup.innerGet(XGroup.PTY_IS_VALID))){
            String sourceName = xentityGroup.innerGet(XGroup.PTY_SOURCE_NAME);
            if(RString.isNotEmpty(sourceName)){
               String filepath = RFile.format("D:/Share/StorageeGroup");
               // 保存头文件
               source = _storageConsole.buildSource(xentityGroup, "head");
               String headFile = RFile.makeFilename(filepath, (sourceName + ".h"));
               RFile.saveToFile(headFile, source, REncoding.GBK);
               // 保存体文件
               source = _storageConsole.buildSource(xentityGroup, "body");
               String bodyFile = RFile.makeFilename(filepath, (sourceName + ".cpp"));
               RFile.saveToFile(bodyFile, source, REncoding.GBK);
            }
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String buildSource(IWebContext context,
                             FStoragePage page){
      IXmlObject xgroup = _storageConsole.find(context.parameter(SEL_COL));
      if(RBoolean.parse(xgroup.innerGet(XGroup.PTY_IS_VALID))){
         String sourceType = context.parameter("type");
         String sourceName = xgroup.innerGet(XGroup.PTY_SOURCE_NAME);
         page.setSourceName(sourceName);
         FString source = _storageConsole.buildSource(xgroup, sourceType);
         ISyntaxMaker syntaxMaker = _formatConsole.find(ESyntax.Cpp);
         page.setSource(syntaxMaker.format(source));
         page.setSourceType(sourceType);
      }
      return PAGE_SOURCE;
   }

   @Override
   public String catalog(IWebContext context,
                         FStoragePage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FStoragePage page){
      return delete(_storageConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String execute(IWebContext context,
                         FStoragePage page){
      IXmlObject xgroup = _storageConsole.find(context.parameter(SEL_COL));
      String sourceType = context.parameter("type");
      String sourceName = xgroup.innerGet(XGroup.PTY_SOURCE_NAME);
      FString source = _storageConsole.buildSource(xgroup, sourceType);
      if("head".equalsIgnoreCase(sourceType)){
         String filepath = _storageConsole.buildStorePath((sourceName + ".h"));
         RFile.saveToFile(filepath, source, REncoding.UTF8);
      }else if("body".equalsIgnoreCase(sourceType)){
         String filepath = _storageConsole.buildStorePath((sourceName + ".cpp"));
         RFile.saveToFile(filepath, source, REncoding.UTF8);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String insert(IWebContext context,
                        FStoragePage page){
      return insert(_storageConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FStoragePage page){
      return list(_storageConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FStoragePage page){
      return sort(_storageConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FStoragePage page){
      return update(_storageConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}

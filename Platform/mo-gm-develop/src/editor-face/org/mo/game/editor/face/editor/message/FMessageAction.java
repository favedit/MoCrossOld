package org.mo.game.editor.face.editor.message;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.message.EMessageSource;
import org.mo.game.editor.core.message.IMessageConsole;
import org.mo.game.editor.core.message.common.XMessageGroup;
import org.mo.game.editor.face.apl.page.IPublicPage;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.protocol.context.IWebContext;

public class FMessageAction
      extends FAbsXmlObjectAction<XMessageGroup>
      implements
         IMessageAction{

   private static ILogger _logger = RLogger.find(FMessageAction.class);

   public final String PAGE_CATALOG = "Catalog";

   public final String SEL_COL = "sel_collection";

   public final String SEL_COP = "sel_component";

   public final static String PAGE_SOURCE = "Source";

   @ALink
   protected IMessageConsole _messageConsole;

   @ALink
   protected IFormatConsole _formatConsole;

   //============================================================
   @Override
   public String buildAllSource(IWebContext context,
                                FMessagePage page){
      String type = context.parameter("type");
      _logger.debug(this, "buildAllSource", "Build type source. (type={0})", type);
      // 生成全部代码
      if("all".equals(type)){
         _messageConsole.buildAll(EMessageSource.All);
      }
      // 生成CPP代码
      if("cpp".equals(type)){
         _messageConsole.buildAll(EMessageSource.AllCpp);
      }
      // 生成AS代码
      if("as".equals(type)){
         _messageConsole.buildAll(EMessageSource.AllAs);
      }
      // 生成CS代码
      if("cs".equals(type)){
         _messageConsole.buildAll(EMessageSource.AllCs);
      }
      // 生成Java代码
      if("java".equals(type)){
         _messageConsole.buildAll(EMessageSource.AllJava);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   //
   //   //============================================================
   //   @Override
   //   public String buildSource(IWebContext context,
   //                             FMessagePage page){
   //      IXmlObject xmessageGroup = _messageConsole.find(context.parameter(SEL_COL));
   //      if(RBoolean.parse(xmessageGroup.innerGet(XMessageGroup.PTY_IS_VALID))){
   //         String sourceType = context.parameter("type");
   //         if("as".equalsIgnoreCase(sourceType)){
   //            for(IXmlObject xmessage : xmessageGroup.children()){
   //               String groupFilePath = makeAsGroupFilePath(xmessageGroup);
   //               if(null != groupFilePath){
   //                  if(RBoolean.parse(xmessage.innerGet(XMessage.PTY_IS_VALID))){
   //                     String messageName = xmessage.innerGet(XMessage.PTY_NAME);
   //                     String messageFilePath = makeAsMessageFilePath(xmessageGroup, messageName);
   //                     if(null != messageFilePath){
   //                        for(IXmlObject xstruct : xmessage.children()){
   //                           if("Struct".equalsIgnoreCase(xstruct.name())){
   //                              // 生成结构体
   //                              String structName = xstruct.innerGet(XMessageStruct.PTY_NAME);
   //                              String structFilePath = makeAsStructFilePath(xmessageGroup, messageName, structName.substring(1));
   //                              if(null != messageFilePath){
   //                                 FString source = _messageConsole.buildAsStructSource(xmessageGroup, messageName, structName);
   //                                 RFile.saveToFile(structFilePath, source, REncoding.UTF8);
   //                              }
   //                           }
   //                        }
   //                        FString source = _messageConsole.buildAsMessageSource(xmessageGroup, messageName);
   //                        RFile.saveToFile(messageFilePath, source, REncoding.UTF8);
   //                     }
   //                  }
   //                  FString source = _messageConsole.buildAsGroupSource(xmessageGroup);
   //                  RFile.saveToFile(groupFilePath, source, REncoding.UTF8);
   //               }
   //            }
   //            return IPublicPage.PROCESS_SUCCESS;
   //         }else{
   //            String sourceName = xmessageGroup.innerGet(XMessageGroup.PTY_SOURCE_NAME);
   //            page.setSourceName(sourceName);
   //            FString source = _messageConsole.buildSource(xmessageGroup, sourceType);
   //            // as 代码样式
   //            if("as".equalsIgnoreCase(sourceType)){
   //               ISyntaxMaker syntaxMaker = _formatConsole.find(ESyntax.Cpp);
   //               page.setSource(syntaxMaker.format(source));
   //               page.setSourceType(sourceType);
   //            }else{
   //               ISyntaxMaker syntaxMaker = _formatConsole.find(ESyntax.Cpp);
   //               page.setSource(syntaxMaker.format(source));
   //               page.setSourceType(sourceType);
   //            }
   //         }
   //      }
   //      return PAGE_SOURCE;
   //   }

   //============================================================
   @Override
   public String catalog(IWebContext context,
                         FMessagePage page){
      return PAGE_CATALOG;
   }

   //============================================================
   @Override
   public String delete(IWebContext context,
                        FMessagePage page){
      return delete(_messageConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String insert(IWebContext context,
                        FMessagePage page){
      return insert(_messageConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String list(IWebContext context,
                      FMessagePage page){
      return list(_messageConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String sort(IWebContext context,
                      FMessagePage page){
      return sort(_messageConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   //============================================================
   @Override
   public String update(IWebContext context,
                        FMessagePage page){
      return update(_messageConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}

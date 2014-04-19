package org.mo.game.editor.face.editor.processor;

import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.message.IMessageConsole;
import org.mo.game.editor.core.processor.IProcessorConsole;
import org.mo.game.editor.core.processor.common.XFunction;
import org.mo.game.editor.core.processor.common.XProject;
import org.mo.game.editor.core.storage.common.XGroup;
import org.mo.game.editor.face.apl.page.IPublicPage;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.web.core.format.ESyntax;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.core.format.ISyntaxMaker;
import org.mo.web.protocol.context.IWebContext;

public class FProcessorAction
      extends FAbsXmlObjectAction<XProject>
      implements
         IProcessorAction{

   public final String PAGE_CATALOG = "Catalog";

   public final String SEL_COL = "sel_collection";

   public final static String PAGE_SOURCE = "Source";

   @ALink
   protected IProcessorConsole _processorConsole;

   @ALink
   protected IMessageConsole _messageConsole;

   @ALink
   protected IFormatConsole _formatConsole;

   //============================================================
   @Override
   public String build(IWebContext context,
                       FProcessorPage page){
      // 生成代码
      IXmlObject project = _processorConsole.find(context.parameter(SEL_COL));
      FString headSource = _processorConsole.buildSource(project, "head");
      // 存储代码
      System.out.println(headSource);
      //String headFile = _processorConsole.buildStorePath(sourceName + ".h");
      //RFile.saveToFile(headFile, source, REncoding.GBK);
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   @Override
   public String buildAllSource(IWebContext context,
                                FProcessorPage page){
      _processorConsole.buildAllSource();
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   @Override
   public String buildSource(IWebContext context,
                             FProcessorPage page){
      IXmlObject xgroup = _processorConsole.find(context.parameter(SEL_COL));
      if(RBoolean.parse(xgroup.innerGet(XGroup.PTY_IS_VALID))){
         String sourceType = context.parameter("type");
         String sourceName = xgroup.innerGet(XGroup.PTY_SOURCE_NAME);
         page.setSourceName(sourceName);
         FString source = _processorConsole.buildSource(xgroup, sourceType);
         ISyntaxMaker syntaxMaker = _formatConsole.find(ESyntax.Cpp);
         page.setSource(syntaxMaker.format(source));
         page.setSourceType(sourceType);
      }
      return PAGE_SOURCE;
   }

   //============================================================
   @Override
   public String catalog(IWebContext context,
                         FProcessorPage page){
      return PAGE_CATALOG;
   }

   //============================================================
   protected String convertMessageTarget(String type){
      if("UNK".equals(type)){
         return "Unknown";
      }else if("STS".equals(type)){
         return "ServerTransfer";
      }else if("ADM".equals(type)){
         return "GlobalDomain";
      }else if("AGT".equals(type)){
         return "GlobalGate";
      }else if("ALG".equals(type)){
         return "GlobalLogin";
      }else if("GPX".equals(type)){
         return "GameProxy";
      }else if("GDM".equals(type)){
         return "GameDomain";
      }else if("GGT".equals(type)){
         return "GameGate";
      }else if("GLG".equals(type)){
         return "GameLogin";
      }else if("GSC".equals(type)){
         return "GameScene";
      }else if("GBT".equals(type)){
         return "GameBattle";
      }else if("GSR".equals(type)){
         return "GameStorage";
      }else if("GCT".equals(type)){
         return "GameChat";
      }else if("GLO".equals(type)){
         return "GameLogger";
      }else if("CLT".equals(type)){
         return "ClientPlayer";
      }
      return null;
   }

   //============================================================
   @Override
   public String delete(IWebContext context,
                        FProcessorPage page){
      return delete(_processorConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String execute(IWebContext context,
                         FProcessorPage page){
      IXmlObject xgroup = _processorConsole.find(context.parameter(SEL_COL));
      String sourceType = context.parameter("type");
      String sourceName = xgroup.innerGet(XGroup.PTY_SOURCE_NAME);
      FString source = _processorConsole.buildSource(xgroup, sourceType);
      if("head".equalsIgnoreCase(sourceType)){
         String filepath = _processorConsole.buildStorePath(sourceName + ".h");
         RFile.saveToFile(filepath, source, REncoding.GBK);
      }else if("body".equalsIgnoreCase(sourceType)){
         String filepath = _processorConsole.buildStorePath(sourceName + ".cpp");
         RFile.saveToFile(filepath, source, REncoding.GBK);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   @Override
   public String fetch(IWebContext context,
                       FProcessorPage page){
      IXmlObject project = _processorConsole.find(context.parameter(SEL_COL));
      fetchProjectMethods(project);
      page.resetCommands();
      page.setTreeRefresh();
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   @Override
   public String fetchAll(IWebContext context,
                          FProcessorPage page){
      for(XProject xproject : _processorConsole.list()){
         fetchProjectMethods(xproject);
      }
      page.resetCommands();
      page.setTreeRefresh();
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   public void fetchProjectMethods(IXmlObject xproject){
      String projectCode = xproject.innerGet("code");
      for(IXmlObject processor : xproject.children()){
         // 设置消息对象
         for(IXmlObject method : processor.children()){
            method.innerSet("is_valid", RBoolean.FALSE_STR);
         }
         String processorName = processor.innerGet("name");
         String logicName = processor.innerGet("logic_name");
         if(RString.isEmpty(logicName)){
            logicName = processorName;
         }
         IXmlObject messageGroup = _messageConsole.find(logicName);
         if(null != messageGroup){
            String code = messageGroup.innerGet("code");
            //            // 生成发送器
            //            for(IXmlObject message : messageGroup.children()){
            //               // 如果消息目标是本服务器的检查
            //               String targetType = message.innerGet("source_type");
            //               if(projectCode.equalsIgnoreCase(targetType)){
            //                  // 获得消息信息
            //                  String messageType = message.innerGet("message_type");
            //                  String messageCode = message.innerGet("code");
            //                  String messageLabel = message.innerGet("label");
            //                  String methodName = "Send" + messageCode + messageType;
            //                  // 创建函数对象
            //                  IXmlObject method = processor.search("name", methodName);
            //                  if(null == method){
            //                     method = new XFunction();
            //                     method.innerSet("name", methodName);
            //                     processor.children().push(method);
            //                  }
            //                  method.innerSet("is_valid", RBoolean.TRUE_STR);
            //                  method.innerSet("type", "SM");
            //                  method.innerSet("label", messageLabel);
            //                  method.innerSet("code", messageType);
            //                  method.innerSet("full_code", code + messageCode + messageType);
            //               }
            //            }
            // 生成接收器
            for(IXmlObject message : messageGroup.children()){
               // 如果消息目标是本服务器的检查
               String targetType = message.innerGet("target_type");
               if("UNK".equals(targetType) || projectCode.equalsIgnoreCase(targetType)){
                  // 获得消息信息
                  String messageType = message.innerGet("message_type");
                  String messageCode = message.innerGet("code");
                  String messageLabel = message.innerGet("label");
                  String messageSource = message.innerGet("source_type");
                  String messageTarget = message.innerGet("target_type");
                  String messageAsynchronous = message.innerGet("is_asynchronous");
                  String messageSession = message.innerGet("is_session");
                  String messageLogin = message.innerGet("is_login");
                  String messageLoginRole = message.innerGet("is_login_role");
                  String messageManage = message.innerGet("is_manage");
                  String methodName = "On" + messageCode + messageType;
                  // 创建函数对象
                  IXmlObject method = processor.search("name", methodName);
                  if(null == method){
                     method = new XFunction();
                     method.innerSet("name", methodName);
                     processor.children().push(method);
                  }
                  method.innerSet("is_valid", RBoolean.TRUE_STR);
                  method.innerSet("type", "OM");
                  method.innerSet("label", messageLabel);
                  method.innerSet("code", messageType);
                  method.innerSet("full_code", code + messageCode + messageType);
                  if(!"UNK".equals(messageSource)){
                     method.innerSet("source_from", convertMessageTarget(messageSource));
                  }
                  if(!"UNK".equals(messageTarget)){
                     method.innerSet("source_target", convertMessageTarget(messageTarget));
                  }
                  method.innerSet("is_asynchronous", messageAsynchronous);
                  method.innerSet("is_session", messageSession);
                  method.innerSet("is_login", messageLogin);
                  method.innerSet("is_login_role", messageLoginRole);
                  method.innerSet("is_manage", messageManage);
               }
            }
         }
      }
      if(XProject.isInstance(xproject)){
         _processorConsole.store((XProject) xproject);
      }
   }

   //============================================================
   @Override
   public String insert(IWebContext context,
                        FProcessorPage page){
      return insert(_processorConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String list(IWebContext context,
                      FProcessorPage page){
      return list(_processorConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String sort(IWebContext context,
                      FProcessorPage page){
      return sort(_processorConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   //============================================================
   @Override
   public String update(IWebContext context,
                        FProcessorPage page){
      return update(_processorConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}

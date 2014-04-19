package org.mo.game.editor.face.editor.message;

import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.environment.IEnvironmentConsole;
import org.mo.eng.help.FAbstractBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;
import org.mo.eng.template.ITemplateParser;
import org.mo.game.editor.core.message.IMessageConsole;
import org.mo.game.editor.core.message.common.XMessageGroup;
import org.mo.logic.event.common.XGroup;

public class FMessageHelper
      extends FAbstractBuildHelper
      implements
         IMessageHelper{

   private static ILogger _logger = RLogger.find(FMessageHelper.class);

   private final String FILE_PATH = "file_path";

   @ALink
   protected IEnvironmentConsole _environmentConsole;

   @ALink
   protected IMessageConsole _messageConsole;

   @Override
   public void buildCatalog(XHelp xhelp){
      buildCatalog(xhelp, makeNode());
   }

   @Override
   public void buildIndex(XHelp xhelp){
      buildIndex(xhelp, makeNode());
   }

   @Override
   public void buildProject(XHelp xhelp){
      buildProject(xhelp, makeNode());
   }

   protected void buildRightDetail(String encoding,
                                   String path,
                                   String rootPath,
                                   String templateName,
                                   FXmlNode node){
      if(null != node){
         buildSaveRightDetail(encoding, path, rootPath, templateName, node);
         for(FXmlNode childNode : node.nodes()){
            buildRightDetail(encoding, path, rootPath, templateName, childNode);
         }
      }

   }

   @Override
   public void buildRightDetail(XHelp xhelp,
                                XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath();
      String rootPath = xhelp.getOutputRoot();
      FXmlNode config = makeNode();
      if(null != config){
         String templateName = xaction.getTemplateName();
         for(FXmlNode node : config.nodes()){
            node.set(FILE_PATH, node.get(FILE_PATH) + "/home");
            buildRightDetail(encoding, path, rootPath, templateName, node);
         }
      }
   }

   protected void buildSaveRightDetail(String encoding,
                                       String path,
                                       String rootPath,
                                       String templateName,
                                       FXmlNode node){
      // 建立全部数据集对象
      ITemplateParser parser = _templateConsole.getParser(templateName);
      String filePath = node.get(FILE_PATH);
      if(RString.isNotEmpty(filePath)){
         String typeName = node.name();
         String typeLabel = "";
         if("message".equalsIgnoreCase(typeName)){
            typeLabel = "消息";
         }else if("group".equalsIgnoreCase(typeName)){
            typeLabel = "消息分组";
         }else if("Enum".equalsIgnoreCase(typeName)){
            typeLabel = "枚举";
         }else if("Item".equalsIgnoreCase(typeName)){
            typeLabel = "数据项";
         }else if("Member".equalsIgnoreCase(typeName)){
            typeLabel = "数据项";
         }else if("Struct".equalsIgnoreCase(typeName)){
            typeLabel = "结构体";
         }else if("Type".equalsIgnoreCase(typeName)){
            typeLabel = "类型";
         }
         node.set("type_label", typeLabel);
         node.set("type_name", typeName);
         for(FXmlNode childNode : node.nodes()){
            typeName = childNode.name();
            if("message".equalsIgnoreCase(typeName)){
               typeLabel = "消息";
            }else if("Enum".equalsIgnoreCase(typeName)){
               typeLabel = "枚举";
            }else if("Item".equalsIgnoreCase(typeName)){
               typeLabel = "数据项";
            }else if("Member".equalsIgnoreCase(typeName)){
               typeLabel = "数据项";
            }else if("Struct".equalsIgnoreCase(typeName)){
               typeLabel = "结构体";
            }else if("Type".equalsIgnoreCase(typeName)){
               typeLabel = "类型";
            }
            childNode.set("type_label", typeLabel);
            childNode.set("type_name", typeName);
         }
         parser.reset();
         parser.define("root_path", rootPath);
         parser.define("path", path);
         parser.define("config", node);
         FString source = parser.parse();
         RFile.saveToFile(path + "/" + filePath + ".html", source.toString(), encoding);
      }

   }

   //生成表单和表格属性函数，实现方法为递归
   public void bulidAttributesChlid(IXmlObject formAttribue,
                                    FXmlNode form,
                                    String formName,
                                    ITemplateParser parser,
                                    XHelp xhelp,
                                    XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      for(IXmlObject xAttribute : formAttribue.children()){
         String name = xAttribute.innerGet("name");
         _logger.debug(null, "main", "Build form control all help (form={0}, attribute name={1})", formName, name);
         String code = RString.replace(formName, '.', '_').toLowerCase() + "." + name;
         FXmlNode fieldConfig = RXmlObject.convertToNode(xAttribute, EXmlConfig.Simple);
         parser.reset();
         parser.define("path", xaction.getProcessPath());
         parser.define("form", form);
         parser.define("control", fieldConfig);
         FString source = parser.parse();
         RFile.saveToFile(path + "/" + code + ".html", source.toString(), encoding);
         if(xAttribute.hasChild()){
            bulidAttributesChlid(xAttribute, form, formName, parser, xhelp, xaction);
         }
      }

   }

   @Override
   public void bulidRightGroupList(XHelp xhelp,
                                   XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String fileName = xaction.getOutputFile();
      String path = xhelp.getOutputPath();
      String rootPath = xhelp.getOutputRoot();
      _logger.debug(this, "bulidGroupList", "Build message group (path={0})", path);
      // 建立全部数据集对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      FXmlNode config = new FXmlNode("messageGroups");
      config.set("label", "消息帮助文档");
      for(IXmlObject xgroup : _messageConsole.list()){
         FXmlNode messageGroup = RXmlObject.convertDeepToNode(xgroup, EXmlConfig.Simple);
         String groupName = messageGroup.get(XMessageGroup.PTY_NAME);
         groupName = "group/" + (RString.replace(groupName, '.', '_').toLowerCase());
         groupName = groupName + ".html";
         messageGroup.set("file_name", groupName);
         config.push(messageGroup);
      }
      parser.reset();
      parser.define("root_path", rootPath);
      parser.define("path", path);
      parser.define("config", config);
      FString source = parser.parse();
      RFile.saveToFile(path + fileName, source.toString(), encoding);
   }

   protected FXmlNode makeNode(){
      // 建立数据集
      FXmlNode config = new FXmlNode("messageGroups");
      for(IXmlObject xgroup : _messageConsole.list()){
         if(RBoolean.parse(xgroup.innerGet(XGroup.PTY_IS_VALID))){
            //FXmlNode messageGroup = _messageConsole.buildConfig(xgroup, EXmlConfig.Full);
            //config.push(messageGroup);
         }
      }
      // 添加file_path属性
      makeNode(null, config);
      return config;
   }

   protected void makeNode(FXmlNode parentNode,
                           FXmlNode node){
      if(null != node){
         makeNodeFilePath(parentNode, node);
         for(FXmlNode childNode : node.nodes()){
            makeNode(node, childNode);
         }
      }

   }

   protected void makeNodeFilePath(FXmlNode parentNode,
                                   FXmlNode node){
      // 建立数据集
      String filePath = node.get("name");
      if(null != parentNode){
         String parentPath = parentNode.get(FILE_PATH);
         if(RString.isNotEmpty(parentPath)){
            filePath = parentNode.get(FILE_PATH) + "/" + filePath;
         }
      }
      if(RString.isNotEmpty(filePath)){
         filePath.trim();
         filePath = RString.toLower(filePath);
         node.set(FILE_PATH, filePath);
      }
   }
}

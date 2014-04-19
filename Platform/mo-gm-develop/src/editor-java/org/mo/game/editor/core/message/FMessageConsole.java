package org.mo.game.editor.core.message;

import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RHex;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.eng.template.ITemplateConsole;
import org.mo.game.editor.common.source.REditorCsSource;
import org.mo.game.editor.core.enums.IEnumConsole;
import org.mo.game.editor.core.message.common.XMessage;
import org.mo.game.editor.core.message.common.XMessageGroup;
import org.mo.game.editor.core.message.common.XMessageItem;
import org.mo.game.editor.core.message.common.XMessageStruct;

public class FMessageConsole
      extends FXmlConfigConsole<XMessageGroup>
      implements
         IMessageConsole
{
   private static ILogger _logger = RLogger.find(FMessageConsole.class);

   @AProperty
   private String _pathServer;

   @AProperty
   private String _pathClient;

   @AProperty
   private String _pathTools;

   @AProperty
   private String _pathPlatform;

   @ALink
   private ITemplateConsole _templateConsole;

   @ALink
   protected IEnumConsole _enumConsole;

   //============================================================
   // <T>建立所有指定格式的代码。</T>
   //============================================================
   @Override
   public void buildAll(EMessageSource type){
      if((EMessageSource.All == type) || (EMessageSource.AllCpp == type)){
         buildAllCpp();
      }
      if((EMessageSource.All == type) || (EMessageSource.AllAs == type)){
         buildAllAs();
      }
      if((EMessageSource.All == type) || (EMessageSource.AllCs == type)){
         buildAllCs();
      }
      if((EMessageSource.All == type) || (EMessageSource.AllJava == type)){
         buildAllJava();
      }
   }

   //============================================================
   // <T>建立所有AS代码。</T>
   //============================================================
   protected void buildAllAs(){
      for(IXmlObject xgroup : list()){
         if(RBoolean.parse(xgroup.innerGet(XMessageGroup.PTY_IS_VALID))){
            String groupFilePath = makeAsGroupFilePath(xgroup);
            if(null != groupFilePath){
               for(IXmlObject xmessage : xgroup.children()){
                  if("Message".equalsIgnoreCase(xmessage.name())){
                     if(RBoolean.parse(xmessage.innerGet(XMessage.PTY_IS_VALID))){
                        String messageName = xmessage.innerGet(XMessage.PTY_NAME);
                        String messageFilePath = makeAsMessageFilePath(xgroup, messageName);
                        if(null != messageFilePath){
                           for(IXmlObject xstruct : xmessage.children()){
                              if("Struct".equalsIgnoreCase(xstruct.name())){
                                 // 生成结构体
                                 String structName = xstruct.innerGet(XMessageStruct.PTY_NAME);
                                 String structFilePath = makeAsStructFilePath(xgroup, messageName, structName.substring(1));
                                 if(null != messageFilePath){
                                    FString source = buildAsStructSource(xgroup, messageName, structName);
                                    RFile.saveToFile(structFilePath, source, REncoding.UTF8);
                                    // _logger.debug(this, "buildAllAs", "Build action script struct file. (file_name={1})", structFilePath);
                                 }
                              }
                           }
                           FString source = buildAsMessageSource(xgroup, messageName);
                           RFile.saveToFile(messageFilePath, source, REncoding.UTF8);
                           // _logger.debug(this, "buildAllAs", "Build action script message file. (file_name={1})", messageFilePath);
                        }
                     }
                  }
               }
               FString source = buildAsGroupSource(xgroup);
               RFile.saveToFile(groupFilePath, source, REncoding.UTF8);
               _logger.debug(this, "buildAllAs", "Build action script group file. (file_name={1})", groupFilePath);
            }
         }
      }
      // 生成AS代码
      FXmlNode messageGroups = new FXmlNode("MessageGroups");
      for(IXmlObject xmessageGroup : list()){
         if(RBoolean.parse(xmessageGroup.innerGet(XMessage.PTY_IS_VALID))){
            FXmlNode group = buildConfig(xmessageGroup, EXmlConfig.Full);
            messageGroups.push(group);
         }
      }
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      // 生成控制台代码
      FString source = sourceBuilder.makeAllAsSource(messageGroups);
      String filepath = buildStoreAsPath("RNetMessage.as");
      RFile.saveToFile(filepath, source, REncoding.UTF8);
      _logger.debug(this, "buildAllAs", "Build action script file. (file_name={1})", filepath);
      // 生成提供着代码
      FString providerSource = sourceBuilder.makeAllAsProviderSource(messageGroups);
      String providerPath = buildStoreAsPath("FNetMessageProvider.as");
      RFile.saveToFile(providerPath, providerSource, REncoding.UTF8);
      _logger.debug(this, "buildAllAs", "Build action script provider file. (file_name={1})", providerPath);
   }

   //============================================================
   // <T>建立所有CPP代码。</T>
   //============================================================
   protected void buildAllCpp(){
      FXmlNode soureNode = new FXmlNode();
      // 生成头和体代码
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      for(IXmlObject xgroup : list()){
         if(RBoolean.parse(xgroup.innerGet(XMessageGroup.PTY_IS_VALID))){
            String sourceName = xgroup.innerGet(XMessageGroup.PTY_SOURCE_NAME);
            if(RString.isNotEmpty(sourceName)){
               FXmlNode config = buildConfig(xgroup, EXmlConfig.Simple);
               soureNode.push(config);
               // 保存头文件
               String headFile = buildStorePath(sourceName + ".h");
               FString headSource = sourceBuilder.makeCppHead(config);
               RFile.saveToFile(headFile, headSource, REncoding.GBK);
               _logger.debug(this, "buildAllCpp", "Build cpp include head source. (file={1})", headFile);
               // 保存体文件
               String bodyFile = buildStorePath(sourceName + ".cpp");
               FString bodySource = sourceBuilder.makeCppBody(config);
               RFile.saveToFile(bodyFile, bodySource, REncoding.GBK);
               _logger.debug(this, "buildAllCpp", "Build cpp include head source. (file={1})", bodyFile);
            }
         }
      }
      soureNode.nodes().sortByAttribute("display_index");
      // 生成包含文件
      String includeFile = buildStorePath("MoMgMessage.h");
      FString includeSource = sourceBuilder.makeCppIncludeHead(soureNode);
      RFile.saveToFile(includeFile, includeSource, REncoding.GBK);
      _logger.debug(this, "buildAllCpp", "Build cpp include heads source. (file={1})", includeFile);
      // 生成包含文件
      String factoryFile = buildStorePath("FNetMessageFactory.cpp");
      FString factorySource = sourceBuilder.makeCppIncludeBody(soureNode);
      RFile.saveToFile(factoryFile, factorySource, REncoding.GBK);
      _logger.debug(this, "buildAllCpp", "Build cpp console bodys source. (file={1})", factoryFile);
   }

   //============================================================
   // <T>建立所有CPP代码。</T>
   //============================================================
   protected void buildAllCs(){
      for(IXmlObject xgroup : list()){
         if(RBoolean.parse(xgroup.innerGet(XMessageGroup.PTY_IS_VALID))){
            String groupFilePath = makeCsGroupFilePath(xgroup);
            if(null != groupFilePath){
               for(IXmlObject xmessage : xgroup.children()){
                  if("Message".equalsIgnoreCase(xmessage.name())){
                     if(RBoolean.parse(xmessage.innerGet(XMessage.PTY_IS_VALID))){
                        boolean isScript = RBoolean.parse(xmessage.innerGet(XMessage.PTY_IS_CLIENT_CS));
                        if(isScript){
                           String messageName = xmessage.innerGet(XMessage.PTY_NAME);
                           String messageFilePath = makeCsMessageFilePath(xgroup, messageName);
                           if(null != messageFilePath){
                              FString source = buildCsMessageSource(xgroup, messageName);
                              _logger.debug(this, "buildAllSource", "Build cs source file. (name={1})", messageFilePath);
                              RFile.saveToFile(messageFilePath, source, REncoding.UTF8);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   //============================================================
   // <T>建立所有Java代码。</T>
   //============================================================
   protected void buildAllJava(){
      for(IXmlObject xgroup : list()){
         if(RBoolean.parse(xgroup.innerGet(XMessageGroup.PTY_IS_VALID))){
            String groupFilePath = makeJavaGroupFilePath(xgroup);
            if(null != groupFilePath){
               for(IXmlObject xmessage : xgroup.children()){
                  if("Message".equalsIgnoreCase(xmessage.name())){
                     if(RBoolean.parse(xmessage.innerGet(XMessage.PTY_IS_VALID))){
                        String messageName = xmessage.innerGet(XMessage.PTY_NAME);
                        String messageFilePath = makeJavaMessageFilePath(xgroup, messageName);
                        if(null != messageFilePath){
                           for(IXmlObject xstruct : xmessage.children()){
                              if("Struct".equalsIgnoreCase(xstruct.name())){
                                 // 生成结构体
                                 String structName = xstruct.innerGet(XMessageStruct.PTY_NAME);
                                 String structFilePath = makeJavaStructFilePath(xgroup, messageName, structName.substring(1));
                                 if(null != messageFilePath){
                                    FString source = buildJavaStructSource(xgroup, messageName, structName);
                                    RFile.saveToFile(structFilePath, source, REncoding.UTF8);
                                 }
                              }
                           }
                           FString source = buildJavaMessageSource(xgroup, messageName);
                           RFile.saveToFile(messageFilePath, source, REncoding.UTF8);
                        }
                     }
                  }
               }
               FString source = buildJavaGroupSource(xgroup);
               RFile.saveToFile(groupFilePath, source, REncoding.UTF8);
            }
         }
      }
      // 生成AS代码
      FXmlNode messageGroups = new FXmlNode("MessageGroups");
      for(IXmlObject xmessageGroup : list()){
         if(RBoolean.parse(xmessageGroup.innerGet(XMessage.PTY_IS_VALID))){
            FXmlNode group = buildConfig(xmessageGroup, EXmlConfig.Full);
            messageGroups.push(group);
         }
      }
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      // 生成控制台代码
      FString source = sourceBuilder.makeAllJavaSource(messageGroups);
      String filepath = buildStoreJavaPath("RNetMessage.java");
      RFile.saveToFile(filepath, source, REncoding.UTF8);
      // 生成提供着代码
      FString providerSource = sourceBuilder.makeAllJavaProviderSource(messageGroups);
      String providerPath = buildStoreJavaPath("FNetMessageProvider.java");
      RFile.saveToFile(providerPath, providerSource, REncoding.UTF8);
   }

   //============================================================
   @Override
   public FString buildAsGroupSource(IXmlObject xmessageGroup){
      FXmlNode xgroup = buildConfig(xmessageGroup, EXmlConfig.Simple);
      makeSourceConfig(xgroup);
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      return sourceBuilder.makeAsGroupSource(xgroup);
   }

   //============================================================
   @Override
   public FString buildAsMessageSource(IXmlObject xmessageGroup,
                                       String messageName){
      FXmlNode xgroup = buildConfig(xmessageGroup, EXmlConfig.Simple);
      makeSourceConfig(xgroup);
      FXmlNode xmessage = xgroup.findNode("name", messageName);
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      return sourceBuilder.makeAsMessageSource(xgroup, xmessage);
   }

   //============================================================
   @Override
   public FString buildAsStructSource(IXmlObject xmessageGroup,
                                      String messageName,
                                      String structName){
      FXmlNode xgroup = buildConfig(xmessageGroup, EXmlConfig.Simple);
      makeSourceConfig(xgroup);
      FXmlNode message = xgroup.findNode("name", messageName);
      FXmlNode struct = message.findNode("name", structName);
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      return sourceBuilder.makeAsStructSource(xgroup, message, struct);
   }

   //============================================================
   public FXmlNode buildConfig(IXmlObject xgroup,
                               EXmlConfig type){
      if(null != xgroup){
         FXmlNode config = xgroup.toNode(type);
         if(RBoolean.parse(config.get(XMessageGroup.PTY_IS_VALID, null))){
            for(IXmlObject xmessage : xgroup.children()){
               if(RBoolean.parse(xmessage.innerGet(XMessage.PTY_IS_VALID))){
                  FXmlNode messageConfig = xmessage.toNode(type);
                  for(IXmlObject xitem : xmessage.children()){
                     if(RBoolean.parse(xitem.innerGet(XMessageItem.PTY_IS_VALID))){
                        FXmlNode itemConfig = xitem.toNode(type);
                        for(IXmlObject xchild : xitem.children()){
                           if(RBoolean.parse(xchild.innerGet(XMessageItem.PTY_IS_VALID))){
                              FXmlNode childConfig = xchild.toNode(type);
                              itemConfig.push(childConfig);
                           }
                        }
                        messageConfig.push(itemConfig);
                     }
                  }
                  setMessageFullLabel(messageConfig);
                  config.push(messageConfig);
               }
            }
            makeSourceConfig(config);
            config.nodes().sortByAttribute("code_value");
            return config;
         }
      }
      return null;
   }

   //============================================================
   @Override
   public FXmlNode buildConfig(String name){
      IXmlObject xmessage = this.find(name);
      return buildConfig(xmessage, EXmlConfig.Simple);
   }

   //============================================================
   @Override
   public FString buildCsMessageSource(IXmlObject xmessageGroup,
                                       String messageName){
      FXmlNode config = buildConfig(xmessageGroup, EXmlConfig.Simple);
      makeSourceConfig(config);
      // 建立代码
      FXmlNode message = config.findNode("name", messageName);
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      return sourceBuilder.makeCsMessageSource(config, message);
   }

   //============================================================
   public FString buildJavaGroupSource(IXmlObject xmessageGroup){
      FXmlNode xgroup = buildConfig(xmessageGroup, EXmlConfig.Simple);
      makeSourceConfig(xgroup);
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      return sourceBuilder.makeJavaGroupSource(xgroup);
   }

   //============================================================
   public FString buildJavaMessageSource(IXmlObject xmessageGroup,
                                         String messageName){
      FXmlNode xgroup = buildConfig(xmessageGroup, EXmlConfig.Simple);
      makeSourceConfig(xgroup);
      FXmlNode xmessage = xgroup.findNode("name", messageName);
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      return sourceBuilder.makeJavaMessageSource(xgroup, xmessage);
   }

   //============================================================
   public FString buildJavaStructSource(IXmlObject xmessageGroup,
                                        String messageName,
                                        String structName){
      FXmlNode xgroup = buildConfig(xmessageGroup, EXmlConfig.Simple);
      makeSourceConfig(xgroup);
      FXmlNode message = xgroup.findNode("name", messageName);
      FXmlNode struct = message.findNode("name", structName);
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      return sourceBuilder.makeJavaStructSource(xgroup, message, struct);
   }

   //============================================================
   @Override
   public FString buildSource(FXmlNode messageGroup,
                              String buildType){
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      FString source = null;
      if("head".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeCppHead(messageGroup);
      }else if("body".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeCppBody(messageGroup);
      }
      return source;
   }

   //============================================================
   @Override
   public FString buildSource(IXmlObject xmessageGroup,
                              String buildType){
      // 生成设置
      FXmlNode config = buildConfig(xmessageGroup, EXmlConfig.Simple);
      makeSourceConfig(config);
      // 生成代码
      FMessageSourceBuilder sourceBuilder = new FMessageSourceBuilder(_templateConsole);
      FString source = null;
      if("cpp-head".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeCppHead(config);
      }else if("cpp-body".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeCppBody(config);
      }
      return source;
   }

   //============================================================
   @Override
   public String buildStoreAsPath(String fileName){
      return RFile.makeFilename(_pathClient, fileName);
   }

   //============================================================
   @Override
   public String buildStoreCsPath(String fileName){
      return RFile.makeFilename(_pathTools, fileName);
   }

   //============================================================
   public String buildStoreJavaPath(String fileName){
      return RFile.makeFilename(_pathPlatform, fileName);
   }

   //============================================================
   @Override
   public String buildStorePath(String fileName){
      return RFile.makeFilename(_pathServer, fileName);
   }

   //============================================================
   @Override
   public FObjects<XMessageGroup> createCollection(){
      return new FObjects<XMessageGroup>(XMessageGroup.class);
   }

   //============================================================
   protected String makeAsGroupFilePath(IXmlObject xmessageGroup){
      boolean isScript = RBoolean.parse(xmessageGroup.innerGet(XMessageGroup.PTY_IS_CLIENT_AS));
      if(!isScript){
         return null;
      }
      String fullCode = xmessageGroup.innerGet(XMessageGroup.PTY_FULL_CODE);
      String filePath = buildStoreAsPath(fullCode.toLowerCase());
      String className = "FNet" + fullCode + "Message";
      filePath = RFile.makeFilename(filePath, className + ".as");
      return filePath;
   }

   //============================================================
   protected String makeAsMessageFilePath(IXmlObject xmessageGroup,
                                          String messageName){
      boolean isScript = RBoolean.parse(xmessageGroup.innerGet(XMessageGroup.PTY_IS_CLIENT_AS));
      if(!isScript){
         return null;
      }
      String code = xmessageGroup.innerGet(XMessageGroup.PTY_CODE);
      String fullCode = xmessageGroup.innerGet(XMessageGroup.PTY_FULL_CODE);
      String filePath = buildStoreAsPath(fullCode.toLowerCase());
      IXmlObject xmessage = xmessageGroup.search(XMessage.PTY_NAME, messageName);
      if(!"Message".equals(xmessage.name())){
         return null;
      }
      boolean isAs = RBoolean.parse(xmessage.innerGet(XMessage.PTY_IS_CLIENT_AS));
      if(!isAs){
         return null;
      }
      String messageType = xmessage.innerGet(XMessage.PTY_MESSAGE_TYPE);
      String messageCode = xmessage.innerGet(XMessage.PTY_CODE);
      String className = "F" + code + messageCode + messageType;
      filePath = RFile.makeFilename(filePath, className + ".as");
      return filePath;
   }

   //============================================================
   protected String makeAsStructFilePath(IXmlObject xmessageGroup,
                                         String messageName,
                                         String structName){
      boolean isScript = RBoolean.parse(xmessageGroup.innerGet(XMessageGroup.PTY_IS_CLIENT_AS));
      if(!isScript){
         return null;
      }
      String code = xmessageGroup.innerGet(XMessageGroup.PTY_CODE);
      String fullCode = xmessageGroup.innerGet(XMessageGroup.PTY_FULL_CODE);
      String filePath = buildStoreAsPath(fullCode.toLowerCase());
      IXmlObject xmessage = xmessageGroup.search(XMessage.PTY_NAME, messageName);
      if(!"Message".equals(xmessage.name())){
         return null;
      }
      boolean isAs = RBoolean.parse(xmessage.innerGet(XMessage.PTY_IS_CLIENT_AS));
      if(!isAs){
         return null;
      }
      String messageType = xmessage.innerGet(XMessage.PTY_MESSAGE_TYPE);
      String messageCode = xmessage.innerGet(XMessage.PTY_CODE);
      String className = "F" + code + messageCode + messageType + structName;
      filePath = RFile.makeFilename(filePath, className + ".as");
      return filePath;
   }

   //============================================================
   protected String makeCsGroupFilePath(IXmlObject xmessageGroup){
      boolean isScript = RBoolean.parse(xmessageGroup.innerGet(XMessageGroup.PTY_IS_CLIENT_CS));
      if(!isScript){
         return null;
      }
      String fullCode = xmessageGroup.innerGet(XMessageGroup.PTY_FULL_CODE);
      String filePath = buildStoreCsPath(fullCode);
      String className = "FNet" + fullCode + "Message";
      filePath = RFile.makeFilename(filePath, className + ".cs");
      return filePath;
   }

   //============================================================
   protected String makeCsMessageFilePath(IXmlObject xmessageGroup,
                                          String messageName){
      boolean isScript = RBoolean.parse(xmessageGroup.innerGet(XMessageGroup.PTY_IS_CLIENT_CS));
      if(!isScript){
         return null;
      }
      String code = xmessageGroup.innerGet(XMessageGroup.PTY_CODE);
      String fullCode = xmessageGroup.innerGet(XMessageGroup.PTY_FULL_CODE);
      String filePath = buildStoreCsPath(fullCode);
      IXmlObject xmessage = xmessageGroup.search(XMessage.PTY_NAME, messageName);
      if(!"Message".equals(xmessage.name())){
         return null;
      }
      String messageType = xmessage.innerGet(XMessage.PTY_MESSAGE_TYPE);
      String messageCode = xmessage.innerGet(XMessage.PTY_CODE);
      String className = "F" + code + messageCode + messageType;
      filePath = RFile.makeFilename(filePath, className + ".cs");
      return filePath;
   }

   //============================================================
   protected String makeJavaGroupFilePath(IXmlObject xmessageGroup){
      boolean isScript = RBoolean.parse(xmessageGroup.innerGet(XMessageGroup.PTY_IS_CLIENT_AS));
      if(!isScript){
         return null;
      }
      String fullCode = xmessageGroup.innerGet(XMessageGroup.PTY_FULL_CODE);
      String filePath = buildStoreJavaPath(fullCode.toLowerCase());
      String className = "FNet" + fullCode + "Message";
      filePath = RFile.makeFilename(filePath, className + ".java");
      return filePath;
   }

   //============================================================
   protected String makeJavaMessageFilePath(IXmlObject xmessageGroup,
                                            String messageName){
      boolean isScript = RBoolean.parse(xmessageGroup.innerGet(XMessageGroup.PTY_IS_CLIENT_AS));
      if(!isScript){
         return null;
      }
      String code = xmessageGroup.innerGet(XMessageGroup.PTY_CODE);
      String fullCode = xmessageGroup.innerGet(XMessageGroup.PTY_FULL_CODE);
      String filePath = buildStoreJavaPath(fullCode.toLowerCase());
      IXmlObject xmessage = xmessageGroup.search(XMessage.PTY_NAME, messageName);
      if(!"Message".equals(xmessage.name())){
         return null;
      }
      boolean isAs = RBoolean.parse(xmessage.innerGet(XMessage.PTY_IS_CLIENT_AS));
      if(!isAs){
         return null;
      }
      String messageType = xmessage.innerGet(XMessage.PTY_MESSAGE_TYPE);
      String messageCode = xmessage.innerGet(XMessage.PTY_CODE);
      String className = "F" + code + messageCode + messageType;
      filePath = RFile.makeFilename(filePath, className + ".java");
      return filePath;
   }

   //============================================================
   protected String makeJavaStructFilePath(IXmlObject xmessageGroup,
                                           String messageName,
                                           String structName){
      boolean isScript = RBoolean.parse(xmessageGroup.innerGet(XMessageGroup.PTY_IS_CLIENT_AS));
      if(!isScript){
         return null;
      }
      String code = xmessageGroup.innerGet(XMessageGroup.PTY_CODE);
      String fullCode = xmessageGroup.innerGet(XMessageGroup.PTY_FULL_CODE);
      String filePath = buildStoreJavaPath(fullCode.toLowerCase());
      IXmlObject xmessage = xmessageGroup.search(XMessage.PTY_NAME, messageName);
      if(!"Message".equals(xmessage.name())){
         return null;
      }
      boolean isAs = RBoolean.parse(xmessage.innerGet(XMessage.PTY_IS_CLIENT_AS));
      if(!isAs){
         return null;
      }
      String messageType = xmessage.innerGet(XMessage.PTY_MESSAGE_TYPE);
      String messageCode = xmessage.innerGet(XMessage.PTY_CODE);
      String className = "F" + code + messageCode + messageType + structName;
      filePath = RFile.makeFilename(filePath, className + ".java");
      return filePath;
   }

   //============================================================
   protected void makeMessageHash(FXmlNode xmessage){
      if(!xmessage.contains("version")){
         // 计算字符串
         FString info = new FString();
         info.append(xmessage.get("name", null) + "|");
         for(FXmlNode xitem : xmessage.nodes()){
            if(xitem.isName("item")){
               info.append(xitem.get("name", null) + "|");
               info.append(xitem.get("is_collection", null) + "|");
               info.append(xitem.get("type_cd", null) + "|");
               info.append(xitem.get("type_name", null) + "|");
               info.append(xitem.get("data_size", null) + "|");
            }
         }
         String value = info.toString();
         // 计算Hash
         int hash = 0;
         int length = value.length();
         for(int n = 0; n < length; n++){
            hash += (31 * hash) + value.charAt(n);
         }
         // 计算Hash字符串
         String hashString = RHex.format(hash).toUpperCase();
         if(hashString.startsWith("-")){
            hashString = hashString.substring(1);
         }
         hashString = "0x" + RString.leftPad(RString.right(hashString, 8), 8, '0');
         //if(xgroup.get("name").indexOf("client") != -1 && xmessage.get("name").indexOf("connect") != -1){
         //   System.out.println(xgroup.get("name") + "|" + xmessage.get("name") + " = " + hashString + " : " + value);
         //}
         xmessage.set("version", hashString);
      }
   }

   //============================================================
   protected void makeSourceConfig(FXmlNode config){
      try{
         for(FXmlNode xmessage : config.allNodes("Message")){
            makeMessageHash(xmessage);
            // 修正服务类型
            String serviceType = xmessage.get("service_type", null);
            if(RString.isEmpty(serviceType)){
               xmessage.set("service_type", "Logic");
            }
            // 修正数据
            for(FXmlNode xmember : xmessage.nodes()){
               if(xmember.isName("Struct")){
                  for(FXmlNode xitem : xmember.nodes()){
                     makeSourceItemConfig(xmessage, xitem);
                  }
               }
               if(xmember.isName("Item")){
                  makeSourceItemConfig(xmessage, xmember);
               }
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Make config failure. (xml={1})", config.attributes().toString());
      }
   }

   //============================================================
   protected void makeSourceItemConfig(FXmlNode xmessage,
                                       FXmlNode xitem){
      try{
         // 设置数据大小
         String dataSize = xitem.get("data_size", null);
         if(!RString.isEmpty(dataSize)){
            String convertSize = _enumConsole.searchConstantCode(dataSize);
            xitem.set("data_size", convertSize);
            String convertSizeValue = _enumConsole.searchConstantValue(dataSize, null);
            if(null != convertSizeValue){
               xitem.set("data_size_length", convertSizeValue);
            }else if(!xitem.contains("data_size_length")){
               xitem.set("data_size_length", convertSize);
            }
         }
         // 设置类型
         String type = xitem.get("type", null);
         if("Enum".equals(type)){
            String typeName = xitem.get("type_name", null);
            FXmlNode enumNode = xmessage.findNode("Enum", "name", typeName);
            if(null != enumNode){
               xitem.set("enum_inner", "Y");
            }
         }
         if("Set".equals(type)){
            String typeName = xitem.get("type_name", null);
            if(!RString.isEmpty(typeName)){
               if(typeName.startsWith("T") && typeName.endsWith("Set")){
                  typeName = typeName.substring(1, typeName.length() - 3);
               }
               FXmlNode xenum = _enumConsole.searchEnum(typeName);
               if(null != xenum){
                  String dataTypeCd = xenum.get("set_data_type_cd", null);
                  xitem.set("data_type_cd", dataTypeCd);
                  xitem.set("data_type_cs", REditorCsSource.convertDataType(dataTypeCd, typeName));
               }
            }
         }
         if("Struct".equals(type) || "Object".equals(type)){
            String typeName = xitem.get("type_name", null);
            FXmlNode structNode = xmessage.findNode("Struct", "name", typeName);
            if(null != structNode){
               xitem.set("struct_inner", "Y");
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Make source item failure. (message={1}, item={2})", xmessage.get("name", null), xitem.attributes().toString());
      }
   }

   //============================================================
   protected void setMessageFullLabel(FXmlNode config){
      if(config.isName("Message")){
         String name = config.get("name", null);
         String value = config.get("code_value", null);
         if(null != value){
            if(value.startsWith("0x")){
               value = value.substring(2);
            }
         }else{
            value = "";
         }
         config.set("full_name", value + "-" + name);
      }
   }

   //============================================================
   @Override
   public String sourcePath(){
      return RFile.format(_pathServer);
   }
}

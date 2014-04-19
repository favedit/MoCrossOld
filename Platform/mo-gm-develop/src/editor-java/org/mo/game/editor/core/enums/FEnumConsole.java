package org.mo.game.editor.core.enums;

import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.eng.template.ITemplateConsole;
import org.mo.game.editor.common.source.REditorCsSource;
import org.mo.game.editor.core.enums.common.XEnum;
import org.mo.game.editor.core.enums.common.XEnumGroup;
import org.mo.game.editor.core.enums.common.XEnumItem;

//============================================================
// <T>枚举定义控制台。</T>
//============================================================
public class FEnumConsole
      extends FXmlConfigConsole<XEnumGroup>
      implements
         IEnumConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FEnumConsole.class);

   // 模板路径
   @AProperty
   private static String _templatePath;

   // 服务器路径
   @AProperty
   protected String _pathServer;

   @AProperty
   protected String _pathClient;

   @AProperty
   protected String _pathTool;

   @AProperty
   protected String _pathPlatform;

   @ALink
   private ITemplateConsole _templateConsole;

   //============================================================
   // <T>建立所有指定格式的代码。</T>
   //============================================================
   @Override
   public void buildAll(EEnumSource type){
      if((EEnumSource.All == type) || (EEnumSource.AllCpp == type)){
         buildAllCpp();
      }
      if((EEnumSource.All == type) || (EEnumSource.AllAs == type)){
         buildAllAs();
      }
      if((EEnumSource.All == type) || (EEnumSource.AllCs == type)){
         buildAllCs();
      }
      if((EEnumSource.All == type) || (EEnumSource.AllJava == type)){
         buildAllJava();
      }
      if((EEnumSource.All == type) || (EEnumSource.AllLua == type)){
         buildAllLua();
      }
   }

   //============================================================
   // <T>建立所有CPP代码。</T>
   //============================================================
   protected void buildAllAs(){
      FEnumSourceBuilder sourceBuilder = new FEnumSourceBuilder(_templateConsole);
      for(XEnumGroup xgroup : list()){
         // 检查有效性
         if(!RBoolean.parse(xgroup.innerGet(XEnumGroup.PTY_IS_VALID))){
            continue;
         }
         if(!RBoolean.parse(xgroup.innerGet(XEnumGroup.PTY_IS_CLIENT_AS))){
            continue;
         }
         FXmlNode config = buildConfig(xgroup, EXmlConfig.Simple);
         // 生成枚举代码
         String codeName = xgroup.innerGet("code_name");
         String asPath = RFile.makeFilename(sourceClientPath(), "E" + codeName + ".as");
         FString asSource = sourceBuilder.makeAsSource(config);
         RFile.saveToFile(asPath, asSource, REncoding.UTF8);
         _logger.debug(this, "buildAllCpp", "Build cpp head source. (file={1})", asPath);
         // 生成集合代码
         for(FXmlNode xenum : config.nodes()){
            if(RBoolean.parse(xenum.get(XEnum.PTY_IS_CLIENT_AS, null))){
               if(RBoolean.parse(xenum.get(XEnum.PTY_IS_SET, null))){
                  String name = xenum.get("name");
                  String path = RFile.makeFilename(sourceClientPath(), "T" + name + "Set.as");
                  FString source = sourceBuilder.makeAsSetSource(xenum);
                  RFile.saveToFile(path, source, REncoding.UTF8);
                  _logger.debug(this, "buildAllCpp", "Build cpp head source. (file={1})", path);
               }
            }
         }
      }
   }

   //============================================================
   // <T>建立所有CPP代码。</T>
   //============================================================
   protected void buildAllCpp(){
      FXmlNode soureNode = new FXmlNode();
      FEnumSourceBuilder sourceBuilder = new FEnumSourceBuilder(_templateConsole);
      for(XEnumGroup xgroup : list()){
         // 检查有效性
         if(!RBoolean.parse(xgroup.innerGet(XEnumGroup.PTY_IS_VALID))){
            continue;
         }
         FXmlNode config = buildConfig(xgroup, EXmlConfig.Simple);
         soureNode.push(config);
         // 生成CPP代码
         String projectName = xgroup.innerGet(XEnumGroup.PTY_PROJECT_NAME);
         String sourceName = xgroup.innerGet(XEnumGroup.PTY_SOURCE_NAME);
         // 存储文件头
         String headPath = RFile.makeFilename(sourceServerPath(), projectName, sourceName + ".h");
         FString headSource = sourceBuilder.makeCppHead(config);
         RFile.saveToFile(headPath, headSource, REncoding.GBK);
         _logger.debug(this, "buildAllCpp", "Build cpp head source. (file={1})", headPath);
         // 存储文件体
         String bodyPath = RFile.makeFilename(sourceServerPath(), projectName, sourceName + ".cpp");
         FString bodySource = sourceBuilder.makeCppBody(config);
         RFile.saveToFile(bodyPath, bodySource, REncoding.GBK);
         _logger.debug(this, "buildAllCpp", "Build cpp body source. (file={1})", bodyPath);
      }
      // 保存引用文件
      soureNode.nodes().sortByAttribute("display_index");
      String includeFile = RFile.makeFilename(sourceServerPath(), "/Common/MoMessage/MoMgEnum.h");
      FString includeSource = sourceBuilder.makeCppInclude(soureNode);
      RFile.saveToFile(includeFile, includeSource, REncoding.GBK);
      _logger.debug(this, "buildAllCpp", "Build cpp include source. (file={1})", includeFile);
   }

   //============================================================
   // <T>建立所有CPP代码。</T>
   //============================================================
   protected void buildAllCs(){
      FEnumSourceBuilder sourceBuilder = new FEnumSourceBuilder(_templateConsole);
      for(XEnumGroup xgroup : list()){
         // 检查有效性
         if(!RBoolean.parse(xgroup.innerGet(XEnumGroup.PTY_IS_VALID))){
            continue;
         }
         if(!RBoolean.parse(xgroup.innerGet(XEnumGroup.PTY_IS_CLIENT_CS))){
            continue;
         }
         FXmlNode config = buildConfig(xgroup, EXmlConfig.Simple);
         // 存储CS代码
         for(FXmlNode xenum : config.nodes()){
            if(RBoolean.parse(xenum.get(XEnum.PTY_IS_CLIENT_CS, null))){
               String name = xenum.get("name");
               String path = RFile.makeFilename(sourceToolPath(), "E" + name + ".cs");
               FString source = sourceBuilder.makeCsSource(xenum);
               RFile.saveToFile(path, source, REncoding.UTF8);
               _logger.debug(this, "buildAllCs", "Build cs source. (file={1})", path);
            }
         }
      }
   }

   //============================================================
   // <T>建立所有JAVA代码。</T>
   //============================================================
   protected void buildAllJava(){
      FEnumSourceBuilder sourceBuilder = new FEnumSourceBuilder(_templateConsole);
      for(XEnumGroup xgroup : list()){
         // 检查有效性
         if(!RBoolean.parse(xgroup.innerGet(XEnumGroup.PTY_IS_VALID))){
            continue;
         }
         if(!RBoolean.parse(xgroup.innerGet(XEnumGroup.PTY_IS_CLIENT_AS))){
            continue;
         }
         FXmlNode config = buildConfig(xgroup, EXmlConfig.Simple);
         // 生成枚举代码
         String codeName = xgroup.innerGet("code_name");
         String asPath = RFile.makeFilename(sourcePlatformPath(), "E" + codeName + ".java");
         FString asSource = sourceBuilder.makeAsSource(config);
         RFile.saveToFile(asPath, asSource, REncoding.UTF8);
         _logger.debug(this, "buildAllJava", "Build java head source. (file={1})", asPath);
         // 生成集合代码
         for(FXmlNode xenum : config.nodes()){
            if(RBoolean.parse(xenum.get(XEnum.PTY_IS_CLIENT_AS, null))){
               if(RBoolean.parse(xenum.get(XEnum.PTY_IS_SET, null))){
                  String name = xenum.get("name");
                  String path = RFile.makeFilename(sourcePlatformPath(), "T" + name + "Set.java");
                  FString source = sourceBuilder.makeAsSetSource(xenum);
                  RFile.saveToFile(path, source, REncoding.UTF8);
                  _logger.debug(this, "buildAllJava", "Build java head source. (file={1})", path);
               }
            }
         }
      }
   }

   //============================================================
   // <T>建立所有LUA代码。</T>
   //============================================================
   protected void buildAllLua(){
      FEnumSourceBuilder sourceBuilder = new FEnumSourceBuilder(_templateConsole);
      for(XEnumGroup xgroup : list()){
         // 检查有效性
         if(!RBoolean.parse(xgroup.innerGet(XEnumGroup.PTY_IS_VALID))){
            continue;
         }
         FXmlNode xconfig = buildConfig(xgroup, EXmlConfig.Simple);
         // 生成枚举代码
         String codeName = xgroup.innerGet("code_name");
         String asPath = RFile.makeFilename(sourceServerPath(), "/Game/Script/E" + codeName + ".lua");
         FString asSource = sourceBuilder.makeLuaSource(xconfig);
         RFile.saveToFile(asPath, asSource, REncoding.UTF8);
         _logger.debug(this, "buildAllLua", "Build lua source. (file={1})", asPath);
      }
   }

   //============================================================
   @Override
   public FXmlNode buildConfig(IXmlObject xobject,
                               EXmlConfig type){
      if(null == xobject){
         return null;
      }
      FXmlNode config = xobject.toNode(type);
      if(!RBoolean.parse(config.get(XEnumGroup.PTY_IS_VALID, null))){
         return null;
      }
      // 设置引用列表
      String includeHeads = config.get(XEnumGroup.PTY_INCLUDE_HEADS, null);
      if(RString.isNotEmpty(includeHeads)){
         for(String head : RString.split(includeHeads, "\n")){
            String include = head.trim();
            if(RString.isNotBlank(include)){
               XEnumGroup xgroup = find(include);
               if(null != xgroup){
                  FXmlNode includeNode = config.createNode("Include");
                  includeNode.attributes().append(xgroup.toSimpleAttributes());
               }
            }
         }
      }
      // 设置属性
      for(IXmlObject xenum : xobject.children()){
         if(RBoolean.parse(xenum.innerGet(XEnum.PTY_IS_VALID))){
            int value = 0;
            FXmlNode messageConfig = xenum.toNode(type);
            for(IXmlObject xitem : xenum.children()){
               if(RBoolean.parse(xitem.innerGet(XEnumItem.PTY_IS_VALID))){
                  FXmlNode itemConfig = xitem.toNode(type);
                  if(RString.isEmpty(itemConfig.get("value", null))){
                     itemConfig.set("value", value);
                  }
                  messageConfig.push(itemConfig);
                  value++;
               }
            }
            config.push(messageConfig);
         }
      }
      // 设置CS类型
      if(config.isName(XEnum.NAME)){
         String enumDataTypeCd = config.get("enum_data_type_cd");
         if(RString.isNotEmpty(enumDataTypeCd)){
            String dataType = REditorCsSource.convertDataType(enumDataTypeCd, null);
            config.set("enum_data_type", dataType);
         }
         String setDataTypeCd = config.get("set_data_type_cd");
         if(RString.isNotEmpty(setDataTypeCd)){
            String dataType = REditorCsSource.convertDataType(setDataTypeCd, null);
            config.set("set_data_type", dataType);
         }
      }
      return config;
   }

   //============================================================
   @Override
   public FXmlNode buildConfig(String name){
      IXmlObject xenumGroup = this.find(name);
      return buildConfig(xenumGroup, EXmlConfig.Simple);
   }

   //============================================================
   @Override
   protected FObjects<XEnumGroup> createCollection(){
      return new FObjects<XEnumGroup>(XEnumGroup.class);
   }

   //============================================================
   protected FAttributes buildGroupUuids(){
      FAttributes uuids = new FAttributes();
      for(XEnumGroup xgroup : list()){
         String groupName = xgroup.innerGet("name");
         uuids.set(groupName, xgroup.objectId());
         for(IXmlObject xenum : xgroup.children()){
            if(XEnum.isInstance(xenum)){
               String enumName = xenum.innerGet("name");
               uuids.set(groupName + "|" + enumName, xenum.objectId());
               for(IXmlObject xitem : xenum.children()){
                  if(XEnumItem.isInstance(xitem)){
                     String itemName = xitem.innerGet("name");
                     uuids.set(groupName + "|" + enumName + "|" + itemName, xitem.objectId());
                  }
               }
            }
         }
      }
      return uuids;
   }

   //============================================================
   @Override
   public boolean fetchDefineCodeList(){
      String fileName = _templatePath + "/code.list.xml";
      _logger.debug(this, "fetchDefineCodeList", "Fetch code list. (file_name={1})", fileName);
      if(!RFile.exists(fileName)){
         throw new FFatalError("File is not exists. (file_name={1})", fileName);
      }
      FAttributes uuids = buildGroupUuids();
      // 获得组定义
      XEnumGroup codelistGroup = find("game.common");
      IXmlObject codelist = codelistGroup.find("name", "GmCodeList");
      codelist.children().clear();
      // 刷新列表定义
      FXmlDocument xdoc = new FXmlDocument(fileName);
      for(FXmlNode xcodelist : xdoc.root().nodes("CodeList")){
         // 检查有效性
         if(!RBoolean.parse(xcodelist.get("is_valid"))){
            continue;
         }
         // 创建枚举
         String groupName = xcodelist.get("group_name");
         XEnumGroup xgroup = find(groupName);
         if(null != xgroup){
            String name = xcodelist.get("name");
            String typeName = xcodelist.get("type_name");
            boolean isSet = xcodelist.getBoolean("is_set");
            // 获得枚举项目
            IXmlObject xenum = xgroup.find("name", name);
            if(xenum == null){
               xenum = new XEnum();
               xenum.innerSet("name", name);
               xgroup.children().push(xenum);
            }
            xenum.children().clear();
            // 增加列表项目
            XEnumItem codeitem = new XEnumItem();
            codeitem.innerSet("is_valid", "Y");
            if(name.startsWith("Gm")){
               name = name.substring(2);
            }
            codeitem.innerSet("name", name);
            codeitem.innerSet("label", xcodelist.get("label"));
            codeitem.innerSet("value", xcodelist.get("tid"));
            codeitem.setObjectId(uuids.find(codelistGroup.innerGet("name") + "|" + codelist.innerGet("name") + "|" + name));
            codelist.children().push(codeitem);
            // 生成编号集合
            // 获得消息内容
            xenum.innerSet("is_valid", RBoolean.TRUE_STR);
            xenum.innerSet("is_set", RBoolean.toString(isSet));
            xenum.innerSet("enum_data_type_cd", typeName);
            xenum.innerSet("set_data_type_cd", "Uint32");
            xenum.innerSet("label", xcodelist.get("label"));
            FXmlNode xcodes = xcodelist.findNode("CodeList");
            if(null != xcodes){
               for(FXmlNode xcode : xcodes.nodes("Code")){
                  if(xcode.getBoolean("is_valid")){
                     String itemName = xcode.get("name");
                     String itemUuid = uuids.find(xgroup.innerGet("name") + "|" + xenum.innerGet("name") + "|" + itemName);
                     // 创建枚举项目
                     XEnumItem xitem = new XEnumItem();
                     xitem.setObjectId(itemUuid);
                     xitem.innerSet("is_valid", "Y");
                     xitem.innerSet("name", xcode.get("name"));
                     xitem.innerSet("label", xcode.get("label"));
                     xitem.innerSet("flag", xcode.get("flag", null));
                     xitem.innerSet("value", xcode.get("data_value"));
                     // 增加到指定类型内
                     xenum.children().push(xitem);
                  }
               }
            }
            store(xgroup);
         }
      }
      return true;
   }

   //============================================================
   @Override
   public boolean fetchDefineMail(){
      // 获得枚举组
      XEnumGroup xgroup = find("game.mail");
      if(xgroup == null){
         return false;
      }
      String xgroupName = xgroup.innerGet("name");
      FAttributes uuids = buildGroupUuids();
      xgroup.children().clear();
      String prefix = RString.nvl(xgroup.getCodePrefix());
      String aftfix = RString.nvl(xgroup.getCodeAftfix());
      // 检查文件
      String mailFile = _templatePath + "/mail.xml";
      _logger.debug(this, "fetchDefineMail", "Fetch mail list. (file_name={1})", mailFile);
      if(!RFile.exists(mailFile)){
         throw new FFatalError("File is not exists. (file_name={1})", mailFile);
      }
      // 读取组信息
      FDictionary<XEnum> enums = new FDictionary<XEnum>(XEnum.class);
      FXmlDocument xdoc = new FXmlDocument(mailFile);
      for(FXmlNode groupNode : xdoc.root().nodes("Mail")){
         // 获得消息内容
         String code = groupNode.get("sign_code");
         String[] items = RString.splitTwo(code, '.');
         String groupName = prefix + items[0] + aftfix;
         // 创建枚举项目
         if(!enums.contains(groupName)){
            XEnum xenum = new XEnum();
            xenum.setObjectId(uuids.find(xgroupName + "|" + groupName));
            xenum.setIsValid(Boolean.TRUE);
            xenum.setName(groupName);
            xenum.setLabel(groupName);
            enums.set(groupName, xenum);
            // 增加到指定类型内
            xgroup.children().push(xenum);
         }
      }
      for(FXmlNode groupNode : xdoc.root().nodes("Mail")){
         // 获得消息内容
         String code = groupNode.get("sign_code");
         String value = groupNode.get("tid");
         String subject = groupNode.get("subject");
         String[] items = RString.splitTwo(code, '.');
         String groupName = prefix + items[0] + aftfix;
         String codeName = items[1];
         // 创建枚举项目
         XEnum xenum = enums.get(groupName);
         String enumName = xenum.innerGet("name");
         // 创建枚举项目
         XEnumItem xitem = new XEnumItem();
         xitem.setObjectId(uuids.find(xgroupName + "|" + enumName + "|" + codeName));
         xitem.setIsValid(Boolean.TRUE);
         xitem.setName(codeName);
         xitem.setLabel(subject);
         xitem.setValue(value);
         // 增加到指定类型内
         xenum.children().push(xitem);
      }
      // 保存当前设置
      store(xgroup);
      return true;
   }

   //============================================================
   @Override
   public boolean fetchDefineMessage(){
      String functionFile = _templatePath + "/system.function.xml";
      String notifyFile = _templatePath + "/system.notify.xml";
      // 获得枚举组
      XEnumGroup xgroup = find("game.notify");
      if(null == xgroup){
         return false;
      }
      String xgroupName = xgroup.innerGet("name");
      FAttributes uuids = buildGroupUuids();
      xgroup.children().clear();
      String prefix = RString.nvl(xgroup.getCodePrefix());
      String aftfix = RString.nvl(xgroup.getCodeAftfix());
      String codeGroup = xgroup.getCodeGroup();
      // 读取组信息
      _logger.debug(this, "fetchDefineMessage", "Fetch message function list. (file_name={1})", functionFile);
      if(!RFile.exists(functionFile)){
         throw new FFatalError("File is not exists. (file_name={1})", functionFile);
      }else{
         FXmlDocument xdoc = new FXmlDocument(functionFile);
         for(FXmlNode groupNode : xdoc.root().nodes("Function")){
            // 获得消息内容
            String code = prefix + groupNode.get("name") + aftfix;
            String label = groupNode.get("label");
            if(groupNode.getBoolean("is_valid")){
               // 创建枚举项目
               XEnum xenum = new XEnum();
               xenum.setObjectId(uuids.find(xgroupName + "|" + code));
               xenum.setIsValid(Boolean.TRUE);
               xenum.setName(code);
               xenum.setLabel(label);
               // 增加到指定类型内
               xgroup.children().push(xenum);
            }
         }
      }
      // 读取文件信息
      _logger.debug(this, "fetchDefineMessage", "Fetch message notify list. (file_name={1})", notifyFile);
      if(!RFile.exists(notifyFile)){
         throw new FFatalError("File is not exists. (file_name={1})", notifyFile);
      }else{
         FXmlDocument xdoc = new FXmlDocument(notifyFile);
         for(FXmlNode xnotify : xdoc.root().nodes("Notify")){
            // 获得消息内容
            String tid = xnotify.get("tid");
            String groupCode = prefix + xnotify.get("system_function_name") + aftfix;
            IXmlObject xenum = xgroup.find("name", groupCode);
            if(xenum != null){
               String enumName = xenum.innerGet("name");
               String name = xnotify.get("name");
               String label = xnotify.get("label", null);
               String level = xnotify.get("level_name");
               if(xnotify.getBoolean("is_valid")){
                  // 创建枚举项目
                  XEnumItem xitem = new XEnumItem();
                  xitem.setObjectId(uuids.find(xgroupName + "|" + enumName + "|" + name));
                  xitem.setIsValid(Boolean.TRUE);
                  xitem.setName(name);
                  xitem.setLabel(label);
                  xitem.setLevelCd(level);
                  xitem.setValue(codeGroup + "_" + level + " + " + tid);
                  // 增加到指定类型内
                  xenum.children().push(xitem);
               }
            }
         }
      }
      // 保存当前设置
      store(xgroup);
      return true;
   }

   //============================================================
   @Override
   public boolean fetchDefineProperty(){
      String persistenceFile = _templatePath + "/system.persistence.xml";
      String propertyFile = _templatePath + "/system.property.xml";
      // 获得枚举组
      XEnumGroup xgroup = find("game.persistence");
      if(null == xgroup){
         return false;
      }
      String xgroupName = xgroup.innerGet("name");
      FAttributes uuids = buildGroupUuids();
      xgroup.children().clear();
      String prefix = RString.nvl(xgroup.getCodePrefix());
      String aftfix = RString.nvl(xgroup.getCodeAftfix());
      // 读取组信息
      _logger.debug(this, "fetchDefineProperty", "Fetch property persistence list. (file_name={1})", persistenceFile);
      if(!RFile.exists(persistenceFile)){
         throw new FFatalError("File is not exists. (file_name={1})", persistenceFile);
      }else{
         FXmlDocument xdoc = new FXmlDocument(persistenceFile);
         for(FXmlNode xpersistence : xdoc.root().nodes("Persistence")){
            // 获得消息内容
            String name = prefix + xpersistence.get("name") + aftfix;
            String label = xpersistence.get("label");
            if(xpersistence.getBoolean("is_valid")){
               // 创建枚举项目
               XEnum xenum = new XEnum();
               xenum.setObjectId(uuids.find(xgroupName + "|" + name));
               xenum.setIsValid(Boolean.TRUE);
               xenum.setName(name);
               xenum.setLabel(label);
               // 增加到指定类型内
               xgroup.children().push(xenum);
            }
         }
      }
      // 读取文件信息
      _logger.debug(this, "fetchDefineProperty", "Fetch property list. (file_name={1})", propertyFile);
      if(!RFile.exists(propertyFile)){
         throw new FFatalError("File is not exists. (file_name={1})", propertyFile);
      }else{
         FXmlDocument xdoc = new FXmlDocument(propertyFile);
         for(FXmlNode xproperty : xdoc.root().nodes("Property")){
            // 获得消息内容
            String tid = xproperty.get("tid");
            String groupCode = prefix + xproperty.get("system_persistence_name") + aftfix;
            IXmlObject xenum = xgroup.find("name", groupCode);
            if(xenum != null){
               String enumName = xenum.innerGet("name");
               String name = xproperty.get("name");
               String label = xproperty.get("label");
               if(xproperty.getBoolean("is_valid")){
                  // 创建枚举项目
                  XEnumItem xitem = new XEnumItem();
                  xitem.setObjectId(uuids.find(xgroupName + "|" + enumName + "|" + name));
                  xitem.setIsValid(Boolean.TRUE);
                  xitem.setName(name);
                  xitem.setLabel(label);
                  xitem.setValue(tid);
                  // 增加到指定类型内
                  xenum.children().push(xitem);
               }
            }
         }
      }
      // 保存当前设置
      store(xgroup);
      return true;
   }

   //============================================================
   @Override
   public String searchConstantCode(String name){
      for(XEnumGroup xgroup : list()){
         IXmlObject xconstant = xgroup.search("name", name);
         if(null != xconstant){
            return xconstant.innerGet("code");
         }
      }
      return name;
   }

   //============================================================
   @Override
   public String searchConstantValue(String name){
      for(XEnumGroup xgroup : list()){
         IXmlObject xconstant = xgroup.search("name", name);
         if(null != xconstant){
            return xconstant.innerGet("value");
         }
      }
      return name;
   }

   //============================================================
   @Override
   public String searchConstantValue(String name,
                                     String value){
      for(XEnumGroup xgroup : list()){
         IXmlObject xconstant = xgroup.search("name", name);
         if(null != xconstant){
            return xconstant.innerGet("value");
         }
      }
      return value;
   }

   //============================================================
   @Override
   public FXmlNode searchEnum(String name){
      for(XEnumGroup xgroup : list()){
         IXmlObject xenum = xgroup.search("name", name);
         if(null != xenum){
            return xenum.toSimpleNode();
         }
      }
      return null;
   }

   //============================================================
   @Override
   public XEnum searchEnumObject(String name){
      for(XEnumGroup xgroup : list()){
         IXmlObject xenum = xgroup.search("name", name);
         if(null != xenum){
            return (XEnum)xenum;
         }
      }
      return null;
   }

   //============================================================
   @Override
   public String sourceClientPath(){
      return RFile.format(_pathClient);
   }

   //============================================================
   public String sourcePlatformPath(){
      return RFile.format(_pathPlatform);
   }

   //============================================================
   @Override
   public String sourceServerPath(){
      return RFile.format(_pathServer);
   }

   //============================================================
   @Override
   public String sourceToolPath(){
      return RFile.format(_pathTool);
   }
}

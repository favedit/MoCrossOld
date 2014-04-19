package org.mo.game.editor.core.instance;

import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RInteger;
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
import org.mo.game.editor.common.source.FEditorSource;
import org.mo.game.editor.common.source.REditorCsSource;
import org.mo.game.editor.core.entity.common.XStruct;
import org.mo.game.editor.core.enums.IEnumConsole;
import org.mo.game.editor.core.enums.common.XEnum;
import org.mo.game.editor.core.enums.common.XEnumGroup;
import org.mo.game.editor.core.instance.common.XInstance;
import org.mo.game.editor.core.instance.common.XInstanceGroup;
import org.mo.game.editor.core.instance.common.XInstanceItem;

//============================================================
// <T>游戏实例控制台。</T>
//============================================================
public class FInstanceConsole
      extends FXmlConfigConsole<XInstanceGroup>
      implements
         IInstanceConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FInstanceConsole.class);

   // 模板路径
   @AProperty
   private static String _templatePath;

   @AProperty
   protected String _pathServerRoot;

   @AProperty
   protected String _pathServer;

   // 客户端路径
   @AProperty
   protected String _pathClient;

   @ALink
   private ITemplateConsole _templateConsole;

   @ALink
   protected IEnumConsole _enumConsole;

   //============================================================
   // <T>游戏实例控制台。</T>
   //============================================================
   public FInstanceConsole(){
   }

   //============================================================
   // <T>刷新集合</T>
   //============================================================
   public void refreshGroup(IXmlObject xgroup){
      for(IXmlObject xcollection : xgroup.children()){
         // 查找属性最大编号
         int propertyMaxId = 0;
         for(IXmlObject xitem : xcollection.children()){
            int propertyId = RInteger.parse(xitem.innerGet("property_id"));
            if(propertyId > propertyMaxId){
               propertyMaxId = propertyId;
            }
         }
         // 分配属性编号
         for(IXmlObject xitem : xcollection.children()){
            int propertyId = 0;
            String propertyIdValue = xitem.innerGet("property_id");
            if(RInteger.isInteger(propertyIdValue)){
               propertyId = RInteger.parse(propertyIdValue);
            }
            if(0 == propertyId){
               propertyId = ++propertyMaxId;
               xitem.innerSet("property_id", Integer.toString(propertyId));
            }
         }
      }
   }

   //============================================================
   // <T>XML节点转换为XML实例</T>
   //============================================================
   @Override
   public XInstanceGroup find(String name){
      XInstanceGroup xgroup = (XInstanceGroup)super.findInstance(name);
      if(null != xgroup){
         refreshGroup(xgroup);
      }
      return xgroup;
   }

   //============================================================
   // <T>创建数据集合。</T>
   //
   // @return 数据集合
   //============================================================
   @Override
   protected FObjects<XInstanceGroup> createCollection(){
      return new FObjects<XInstanceGroup>(XInstanceGroup.class);
   }

   // ============================================================
   protected void setMessageFullLabel(FXmlNode config){
      if(config.isName("Message")){
         String name = config.get("name");
         String value = config.get("code_value");
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

   // ============================================================
   public FXmlNode makeInstanceConfig(FXmlNode config){
      FXmlNode result = new FXmlNode();
      try{
         result.setName(config.name());
         result.attributes().append(config.attributes());
         for(FXmlNode node : config.nodes()){
            if(node.isName("Item")){
               result.nodes().push(node);
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Make entity config failure. (entity={1})", config.xml());
      }
      return result;
   }

   // ============================================================
   public FXmlNode makeGroupConfig(FXmlNode config){
      FXmlNode result = new FXmlNode();
      result.setName(config.name());
      result.attributes().append(config.attributes());
      for(FXmlNode node : config.nodes()){
         if(XStruct.isName(node.name()) || XInstance.isName(node.name())){
            node = makeInstanceConfig(node);
            result.nodes().push(node);
         }
      }
      // 设置引用列表
      String includeHeads = config.get(XInstanceGroup.PTY_INCLUDE_HEADS, null);
      if(RString.isNotEmpty(includeHeads)){
         for(String head : RString.split(includeHeads, "\n")){
            String include = head.trim();
            if(RString.isNotBlank(include)){
               XInstanceGroup xgroup = find(include);
               if(null != xgroup){
                  FXmlNode includeNode = result.createNode("Include");
                  includeNode.attributes().append(xgroup.toSimpleAttributes());
               }
            }
         }
      }
      return result;
   }

   // ============================================================
   public FXmlNode buildConfig(IXmlObject xgroup,
                               EXmlConfig type){
      if(xgroup == null){
         return null;
      }
      // 建立实体信息
      FXmlNode config = xgroup.toNode(type);
      if(RBoolean.parse(config.get(XInstanceGroup.PTY_IS_VALID, null))){
         for(IXmlObject xentity : xgroup.children()){
            if(RBoolean.parse(xentity.innerGet(XInstance.PTY_IS_VALID))){
               FXmlNode entityNode = xentity.toNode(type);
               for(IXmlObject xitem : xentity.children()){
                  if(RBoolean.parse(xitem.innerGet(XInstanceItem.PTY_IS_VALID))){
                     FXmlNode itemConfig = xitem.toNode(type);
                     for(IXmlObject xchild : xitem.children()){
                        if(RBoolean.parse(xchild.innerGet(XInstanceItem.PTY_IS_VALID))){
                           FXmlNode childConfig = xchild.toNode(type);
                           itemConfig.push(childConfig);
                        }
                     }
                     entityNode.push(itemConfig);
                  }
               }
               setMessageFullLabel(entityNode);
               config.push(entityNode);
            }
         }
         config.nodes().sortByAttribute("full_name");
      }
      // 建立信息
      if(config.isName("Group")){
         config = makeGroupConfig(config);
      }else if(config.isName("Instance")){
         config = makeInstanceConfig(config);
      }
      // 建立类型信息
      for(FXmlNode xentity : config.allNodes("Instance")){
         String entityPtyGroup = xentity.get(XInstance.PTY_PROPERTY_GROUP, null);
         for(FXmlNode xitem : xentity.allNodes("Item")){
            String name = xitem.get("name");
            // 设置属性
            if(RBoolean.parse(xitem.get("is_get", null)) || RBoolean.parse(xitem.get("is_set", null))){
               xitem.set("is_property", RBoolean.TRUE_STR);
               xentity.set("has_property", RBoolean.TRUE_STR);
               String propertyGroup = xitem.get(XInstanceItem.PTY_PROPERTY_GROUP, null);
               if(RString.isBlank(propertyGroup)){
                  propertyGroup = entityPtyGroup;
                  xitem.set(XInstanceItem.PTY_PROPERTY_GROUP, propertyGroup);
               }
               String propertyName = xitem.get(XInstanceItem.PTY_PROPERTY_NAME, null);
               if(RString.isBlank(propertyName)){
                  propertyName = RString.firstUpper(name);
                  xitem.set(XInstanceItem.PTY_PROPERTY_NAME, propertyName);
               }
               xitem.set("property_full", "E" + propertyGroup + "_" + propertyName);
            }
         }
      }
      // 建立类型信息
      for(FXmlNode node : config.allNodes("Item")){
         // 设置数据大小
         String dataSize = node.get("data_size", null);
         if(!RString.isEmpty(dataSize)){
            dataSize = _enumConsole.searchConstantCode(dataSize);
            node.set("data_size", dataSize);
         }
         String typeCd = node.get("type");
         if("Enum".equals(typeCd)){
            // 设置枚举类型
            String typeName = node.get("type_name");
            if(!RString.isEmpty(typeName)){
               if(typeName.startsWith("E")){
                  typeName = typeName.substring(1);
               }
               FXmlNode xenum = _enumConsole.searchEnum(typeName);
               if(null != xenum){
                  String dataTypeCd = xenum.get("enum_data_type_cd");
                  node.set("data_type_cd", dataTypeCd);
                  try{
                     node.set("data_type_cs", REditorCsSource.convertDataType(dataTypeCd, typeName));
                  }catch(Exception e){
                     throw new FFatalError(e, "Build enum type error. (type_name={1}), node={2}", typeName, node.xml());
                  }
               }
            }
         }else if("Set".equals(typeCd)){
            // 设置集合类型
            String typeName = node.get("type_name");
            if(!RString.isEmpty(typeName)){
               if(typeName.startsWith("T") && typeName.endsWith("Set")){
                  typeName = typeName.substring(1, typeName.length() - 3);
               }
               FXmlNode xenum = _enumConsole.searchEnum(typeName);
               if(null != xenum){
                  String dataTypeCd = xenum.get("set_data_type_cd");
                  node.set("data_type_cd", dataTypeCd);
                  try{
                     node.set("data_type_cs", REditorCsSource.convertDataType(dataTypeCd, typeName));
                  }catch(Exception e){
                     throw new FFatalError(e, "Build set type error. (type_name={1}), node={2}", typeName, node.xml());
                  }
               }
            }
         }
      }
      return config;
   }

   // ============================================================
   public String buildPath(String type,
                           String name){
      if("cpp-include".equalsIgnoreCase(type)){
         return RFile.makeFilename(_pathServerRoot, "MoMgStruct.h");
      }else if("cpp-head".equalsIgnoreCase(type)){
         return RFile.makeFilename(_pathServer, name + ".h");
      }else if("cpp-body".equalsIgnoreCase(type)){
         return RFile.makeFilename(_pathServer, name + ".cpp");
      }else if("as-entity".equalsIgnoreCase(type)){
         return RFile.makeFilename(_pathClient, name) + ".as";
      }
      return null;
   }

   // ============================================================
   // <T>生成代码内容。</T>
   // ============================================================
   public FString buildSource(FXmlNode config,
                              String type){
      FString source = null;
      FInstanceSourceBuilder sourceBuilder = new FInstanceSourceBuilder(_templateConsole);
      // 生成包含代码
      if("cpp-include".equals(type)){
         String fileName = buildPath("cpp-include", null);
         if(RFile.exists(fileName)){
            FEditorSource fileSource = new FEditorSource(fileName, REncoding.GBK);
            config.set("build_source", fileSource.find("Source"));
         }
         // 生成代码
         source = sourceBuilder.makeCppInclude(config);
      }
      // 生成CPP头代码
      if("cpp-head".equals(type)){
         String projectName = config.get("project_name");
         String sourceName = config.get("source_name");
         String fileName = RFile.makeFilename(_pathServer, projectName, sourceName + ".h");
         if(RFile.exists(fileName)){
            FEditorSource fileSource = new FEditorSource(fileName, REncoding.GBK);
            for(FXmlNode xentity : config.nodes()){
               String entityName = "S" + xentity.get("name");
               xentity.set("build_source", fileSource.find(entityName));
            }
         }
         // 生成代码
         source = sourceBuilder.makeCppHead(config);
      }
      // 生成CPP体代码
      if("cpp-body".equals(type)){
         // 读取原始信息
         String projectName = config.get("project_name");
         String sourceName = config.get("source_name");
         String fileName = RFile.makeFilename(_pathServer, projectName, sourceName + ".cpp");
         if(RFile.exists(fileName)){
            FEditorSource fileSource = new FEditorSource(fileName, REncoding.GBK);
            for(FXmlNode xentity : config.nodes()){
               String entityName = "S" + xentity.get("name");
               xentity.set("build_parse", fileSource.find(entityName + ".Parse"));
               xentity.set("build_string", fileSource.find(entityName + ".ToString"));
               xentity.set("build_display", fileSource.find(entityName + ".ToDisplay"));
               xentity.set("build_source", fileSource.find(entityName));
            }
         }
         // 生成代码
         source = sourceBuilder.makeCppBody(config);
      }
      // 生成AS实体代码
      if("as-entity".equals(type)){
         String fileName = buildPath("as-entity", "S" + config.get("name"));
         if(RFile.exists(fileName)){
            FEditorSource fileSource = new FEditorSource(fileName);
            config.set("source", fileSource.find("Source"));
         }
         source = sourceBuilder.makeAsInstance(config);
      }
      // 生成CS实体代码
      if("cs-entity".equals(type)){
         String fileName = buildPath("cs-entity", "S" + config.get("name"));
         if(RFile.exists(fileName)){
            FEditorSource fileSource = new FEditorSource(fileName);
            config.set("source", fileSource.find("Source"));
         }
         source = sourceBuilder.makeCsInstance(config);
      }
      // 生成AS实体代码
      if("java-entity".equals(type)){
         String fileName = buildPath("java-entity", "S" + config.get("name"));
         if(RFile.exists(fileName)){
            FEditorSource fileSource = new FEditorSource(fileName);
            config.set("source", fileSource.find("Source"));
         }
         source = sourceBuilder.makeJavaInstance(config);
      }
      return source;
   }

   // ============================================================
   // <T>建立所有CPP代码。</T>
   // ============================================================
   protected void buildAllCpp(){
      FXmlNode soureNode = new FXmlNode();
      for(IXmlObject xgroup : list()){
         // 检查有效性
         if(!RBoolean.parse(xgroup.innerGet(XInstanceGroup.PTY_IS_VALID))){
            continue;
         }
         // 生成CPP代码
         String projectName = xgroup.innerGet(XInstanceGroup.PTY_PROJECT_NAME);
         String sourceName = xgroup.innerGet(XInstanceGroup.PTY_SOURCE_NAME);
         FXmlNode xgroupNode = buildConfig(xgroup, EXmlConfig.Simple);
         soureNode.push(xgroupNode);
         if(RString.isNotEmpty(sourceName)){
            // 保存头文件
            String headFile = RFile.makeFilename(_pathServer, projectName, sourceName + ".h");
            FString headSource = buildSource(xgroupNode, "cpp-head");
            RFile.saveToFile(headFile, headSource, REncoding.GBK);
            _logger.debug(this, "buildAllCpp", "Build cpp head source. (file={1})", headFile);
            // 保存体文件
            String bodyFile = RFile.makeFilename(_pathServer, projectName, sourceName + ".cpp");
            FString bodySource = buildSource(xgroupNode, "cpp-body");
            RFile.saveToFile(bodyFile, bodySource, REncoding.GBK);
            _logger.debug(this, "buildAllCpp", "Build cpp body source. (file={1})", bodyFile);
         }
      }
      // 保存引用文件
      soureNode.nodes().sortByAttribute("display_index");
      String includeFile = buildPath("cpp-include", null);
      FString includeSource = buildSource(soureNode, "cpp-include");
      RFile.saveToFile(includeFile, includeSource, REncoding.GBK);
      _logger.debug(this, "buildAllCpp", "Build cpp include source. (file={1})", includeFile);
   }

   //============================================================
   // <T>建立所有CPP代码。</T>
   //============================================================
   protected void buildAllAs(){
      String clientPath = RFile.format(_pathClient);
      FInstanceSourceBuilder sourceBuilder = new FInstanceSourceBuilder(_templateConsole);
      for(XInstanceGroup xgroup : list()){
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
         String asPath = RFile.makeFilename(clientPath, "E" + codeName + ".as");
         FString asSource = sourceBuilder.makeAsInstance(config);
         RFile.saveToFile(asPath, asSource, REncoding.UTF8);
         _logger.debug(this, "buildAllAs", "Build action script source. (file={1})", asPath);
         // 生成集合代码
         for(FXmlNode xenum : config.nodes()){
            if(RBoolean.parse(xenum.get(XEnum.PTY_IS_CLIENT_AS, null))){
               if(RBoolean.parse(xenum.get(XEnum.PTY_IS_SET, null))){
                  String name = xenum.get("name");
                  String path = RFile.makeFilename(clientPath, "T" + name + "Set.as");
                  FString source = sourceBuilder.makeAsInstance(xenum);
                  RFile.saveToFile(path, source, REncoding.UTF8);
                  _logger.debug(this, "buildAllCpp", "Build cpp head source. (file={1})", path);
               }
            }
         }
      }
   }

   //============================================================
   // <T>建立代码。</T>
   //
   // @param typeCd 类型
   //============================================================
   @Override
   public void buildSource(EInstanceSource typeCd){
      if((EInstanceSource.All == typeCd) || (EInstanceSource.AllCpp == typeCd)){
         buildAllCpp();
      }
      if((EInstanceSource.All == typeCd) || (EInstanceSource.AllAs == typeCd)){
         buildAllAs();
      }
   }
}

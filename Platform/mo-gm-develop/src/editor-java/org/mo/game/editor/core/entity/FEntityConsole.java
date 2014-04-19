package org.mo.game.editor.core.entity;

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
import org.mo.game.editor.core.entity.common.XEntity;
import org.mo.game.editor.core.entity.common.XEntityGroup;
import org.mo.game.editor.core.entity.common.XEntityItem;
import org.mo.game.editor.core.entity.common.XStruct;
import org.mo.game.editor.core.enums.IEnumConsole;

//============================================================
// <T>实体控制台。</T>
//============================================================
public class FEntityConsole
      extends FXmlConfigConsole<XEntityGroup>
      implements
         IEntityConsole
{
   private static ILogger _logger = RLogger.find(FEntityConsole.class);

   @ALink
   protected ITemplateConsole _templateConsole;

   @ALink
   protected IEnumConsole _enumConsole;

   @AProperty
   protected String _pathServer;

   @AProperty
   protected String _pathClient;

   @AProperty
   protected String _pathTool;

   @AProperty
   protected String _pathPlatform;

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
   public XEntityGroup find(String name){
      XEntityGroup xgroup = (XEntityGroup)super.findInstance(name);
      if(null != xgroup){
         refreshGroup(xgroup);
      }
      return xgroup;
   }

   // ============================================================
   // <T>建立所有指定格式的代码。</T>
   // ============================================================
   @Override
   public void buildAll(EEntitySource type){
      if((EEntitySource.All == type) || (EEntitySource.AllCpp == type)){
         buildAllCpp();
      }
      if((EEntitySource.All == type) || (EEntitySource.AllAs == type)){
         buildAllAs();
      }
      if((EEntitySource.All == type) || (EEntitySource.AllCs == type)){
         buildAllCs();
      }
      if((EEntitySource.All == type) || (EEntitySource.AllJava == type)){
         buildAllJava();
      }
   }

   // ============================================================
   // <T>建立所有CPP代码。</T>
   // ============================================================
   protected void buildAllAs(){
      // 获得组
      for(IXmlObject xgroup : list()){
         if(RBoolean.parse(xgroup.innerGet(XEntityGroup.PTY_IS_VALID))){
            if(RBoolean.parse(xgroup.innerGet(XEntityGroup.PTY_IS_CLIENT_AS))){
               // 获得实体
               FXmlNode xgroupNode = buildConfig(xgroup, EXmlConfig.Simple);
               for(FXmlNode xentityNode : xgroupNode.nodes()){
                  if(XEntity.isName(xentityNode.name()) || XStruct.isName(xentityNode.name())){
                     if(RBoolean.parse(xentityNode.get(XEntity.PTY_IS_VALID, null))){
                        if(RBoolean.parse(xentityNode.get(XEntity.PTY_IS_CLIENT_AS, null))){
                           // 保存头文件
                           String sourceFile = buildPath("as-entity", "S" + xentityNode.get("name"));
                           FString source = buildSource(xentityNode, "as-entity");
                           RFile.saveToFile(sourceFile, source, REncoding.UTF8);
                           _logger.debug(this, "buildAllAs", "Build as source. (file={1})", sourceFile);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   // ============================================================
   // <T>建立所有CPP代码。</T>
   // ============================================================
   protected void buildAllCpp(){
      FXmlNode soureNode = new FXmlNode();
      for(IXmlObject xgroup : list()){
         // 检查有效性
         if(!RBoolean.parse(xgroup.innerGet(XEntityGroup.PTY_IS_VALID))){
            continue;
         }
         // 生成CPP代码
         String projectName = xgroup.innerGet(XEntityGroup.PTY_PROJECT_NAME);
         String sourceName = xgroup.innerGet(XEntityGroup.PTY_SOURCE_NAME);
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

   // ============================================================
   // <T>建立所有CPP代码。</T>
   // ============================================================
   protected void buildAllCs(){
      // 获得组
      for(IXmlObject xgroup : list()){
         if(RBoolean.parse(xgroup.innerGet(XEntityGroup.PTY_IS_VALID))){
            if(RBoolean.parse(xgroup.innerGet(XEntityGroup.PTY_IS_CLIENT_CS))){
               FXmlNode xgroupNode = buildConfig(xgroup, EXmlConfig.Simple);
               // 获得实体
               for(FXmlNode xentityNode : xgroupNode.nodes()){
                  if(XEntity.isName(xentityNode.name()) || XStruct.isName(xentityNode.name())){
                     if(RBoolean.parse(xentityNode.get(XEntity.PTY_IS_VALID, null))){
                        if(RBoolean.parse(xentityNode.get(XEntity.PTY_IS_CLIENT_CS, null))){
                           // 保存头文件
                           String sourceFile = buildPath("cs-entity", "S" + xentityNode.get("name"));
                           FString source = buildSource(xentityNode, "cs-entity");
                           RFile.saveToFile(sourceFile, source, REncoding.UTF8);
                           _logger.debug(this, "buildAllCs", "Build cs source. (file={1})", sourceFile);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   // ============================================================
   // <T>建立所有Java代码。</T>
   // ============================================================
   protected void buildAllJava(){
      // 获得组
      for(IXmlObject xgroup : list()){
         if(RBoolean.parse(xgroup.innerGet(XEntityGroup.PTY_IS_VALID))){
            if(RBoolean.parse(xgroup.innerGet(XEntityGroup.PTY_IS_CLIENT_AS))){
               // 获得实体
               FXmlNode xgroupNode = buildConfig(xgroup, EXmlConfig.Simple);
               for(FXmlNode xentityNode : xgroupNode.nodes()){
                  if(XEntity.isName(xentityNode.name()) || XStruct.isName(xentityNode.name())){
                     if(RBoolean.parse(xentityNode.get(XEntity.PTY_IS_VALID, null))){
                        if(RBoolean.parse(xentityNode.get(XEntity.PTY_IS_CLIENT_AS, null))){
                           // 保存头文件
                           String sourceFile = buildPath("java-entity", "S" + xentityNode.get("name"));
                           FString source = buildSource(xentityNode, "java-entity");
                           RFile.saveToFile(sourceFile, source, REncoding.UTF8);
                           _logger.debug(this, "buildAllJava", "Build java source. (file={1})", sourceFile);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   // ============================================================
   public FXmlNode buildConfig(IXmlObject xgroup,
                               EXmlConfig type){
      if(xgroup == null){
         return null;
      }
      // 建立实体信息
      FXmlNode config = xgroup.toNode(type);
      if(RBoolean.parse(config.get(XEntityGroup.PTY_IS_VALID, null))){
         for(IXmlObject xentity : xgroup.children()){
            if(RBoolean.parse(xentity.innerGet(XEntity.PTY_IS_VALID))){
               FXmlNode entityNode = xentity.toNode(type);
               for(IXmlObject xitem : xentity.children()){
                  if(RBoolean.parse(xitem.innerGet(XEntityItem.PTY_IS_VALID))){
                     FXmlNode itemConfig = xitem.toNode(type);
                     for(IXmlObject xchild : xitem.children()){
                        if(RBoolean.parse(xchild.innerGet(XEntityItem.PTY_IS_VALID))){
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
      }else if(config.isName("Entity")){
         config = makeEntityConfig(config);
      }
      // 建立类型信息
      for(FXmlNode xentity : config.allNodes("Entity")){
         String entityPtyGroup = xentity.get(XEntity.PTY_PROPERTY_GROUP, null);
         for(FXmlNode xitem : xentity.allNodes("Item")){
            String name = xitem.get("name");
            // 设置属性
            if(RBoolean.parse(xitem.get("is_get", null)) || RBoolean.parse(xitem.get("is_set", null))){
               xitem.set("is_property", RBoolean.TRUE_STR);
               xentity.set("has_property", RBoolean.TRUE_STR);
               String propertyGroup = xitem.get(XEntityItem.PTY_PROPERTY_GROUP, null);
               if(RString.isBlank(propertyGroup)){
                  propertyGroup = entityPtyGroup;
                  xitem.set(XEntityItem.PTY_PROPERTY_GROUP, propertyGroup);
               }
               String propertyName = xitem.get(XEntityItem.PTY_PROPERTY_NAME, null);
               if(RString.isBlank(propertyName)){
                  propertyName = RString.firstUpper(name);
                  xitem.set(XEntityItem.PTY_PROPERTY_NAME, propertyName);
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
   @Override
   public String buildPath(String type,
                           String name){
      if("cpp-include".equalsIgnoreCase(type)){
         return RFile.makeFilename(_pathServer, "MoMgStruct.h");
      }else if("cpp-head".equalsIgnoreCase(type)){
         return RFile.makeFilename(_pathServer, name + ".h");
      }else if("cpp-body".equalsIgnoreCase(type)){
         return RFile.makeFilename(_pathServer, name + ".cpp");
      }else if("as-entity".equalsIgnoreCase(type)){
         return RFile.makeFilename(_pathClient, name) + ".as";
      }else if("java-entity".equalsIgnoreCase(type)){
         return RFile.makeFilename(_pathPlatform, name) + ".java";
      }else if("cs-entity".equalsIgnoreCase(type)){
         return RFile.makeFilename(_pathTool, name) + ".cs";
      }
      return null;
   }

   // ============================================================
   // <T>生成代码内容。</T>
   // ============================================================
   public FString buildSource(FXmlNode config,
                              String type){
      FString source = null;
      FEntitySourceBuilder sourceBuilder = new FEntitySourceBuilder(_templateConsole);
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
         source = sourceBuilder.makeAsEntity(config);
      }
      // 生成CS实体代码
      if("cs-entity".equals(type)){
         String fileName = buildPath("cs-entity", "S" + config.get("name"));
         if(RFile.exists(fileName)){
            FEditorSource fileSource = new FEditorSource(fileName);
            config.set("source", fileSource.find("Source"));
         }
         source = sourceBuilder.makeCsEntity(config);
      }
      // 生成AS实体代码
      if("java-entity".equals(type)){
         String fileName = buildPath("java-entity", "S" + config.get("name"));
         if(RFile.exists(fileName)){
            FEditorSource fileSource = new FEditorSource(fileName);
            config.set("source", fileSource.find("Source"));
         }
         source = sourceBuilder.makeJavaEntity(config);
      }
      return source;
   }

   // ============================================================
   @Override
   public FObjects<XEntityGroup> createCollection(){
      return new FObjects<XEntityGroup>(XEntityGroup.class);
   }

   // ============================================================
   public FXmlNode findEntityConfig(String name){
      for(XEntityGroup xgroup : list()){
         for(IXmlObject xentity : xgroup.children()){
            if(name.equals(xentity.innerGet("name"))){
               return buildConfig(xentity, EXmlConfig.Simple);
            }
         }
      }
      return null;
   }

   // ============================================================
   public String findSource(String source,
                            String findBegin,
                            String findEnd){
      int begin = source.indexOf(findBegin);
      int end = source.indexOf(findEnd);
      if((-1 != begin) && (-1 != end)){
         return source.substring(begin + findBegin.length(), end);
      }
      return "";
   }

   // ============================================================
   public FXmlNode makeEntityConfig(FXmlNode config){
      FXmlNode result = new FXmlNode();
      try{
         result.setName(config.name());
         result.attributes().append(config.attributes());
         for(FXmlNode node : config.nodes()){
            if(node.isName("Item")){
               result.nodes().push(node);
            }else if(node.isName("ItemTemplate")){
               // 生成模板字段
               String entityName = node.get("entity_name");
               if(!RString.isEmpty(entityName)){
                  FXmlNode entityNode = findEntityConfig(entityName);
                  if(null == entityNode){
                     throw new FFatalError("Entity is not exists. (entity_name={1})", entityName);
                  }
                  entityNode = makeEntityConfig(entityNode);
                  for(FXmlNode itemNode : entityNode.nodes()){
                     result.nodes().push(itemNode);
                  }
               }
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
         if(XStruct.isName(node.name()) || XEntity.isName(node.name())){
            node = makeEntityConfig(node);
            result.nodes().push(node);
         }
      }
      // 设置引用列表
      String includeHeads = config.get(XEntityGroup.PTY_INCLUDE_HEADS, null);
      if(RString.isNotEmpty(includeHeads)){
         for(String head : RString.split(includeHeads, "\n")){
            String include = head.trim();
            if(RString.isNotBlank(include)){
               XEntityGroup xgroup = find(include);
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

   //============================================================
   @Override
   public void store(XEntityGroup xobject){
      refreshGroup(xobject);
      storeInstance(xobject);
   }
}

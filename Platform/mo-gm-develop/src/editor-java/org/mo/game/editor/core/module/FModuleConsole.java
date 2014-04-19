package org.mo.game.editor.core.module;

import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.eng.template.ITemplateConsole;
import org.mo.game.editor.core.message.common.XMessage;
import org.mo.game.editor.core.message.common.XMessageItem;
import org.mo.game.editor.core.module.common.XEntityGroup;

public class FModuleConsole
      extends FXmlConfigConsole<XEntityGroup>
      implements
         IModuleConsole
{

   private static ILogger _logger = RLogger.find(FModuleConsole.class);

   @ALink
   private ITemplateConsole _templateConsole;

   @AProperty
   private String _sourcepath;

   //============================================================
   @Override
   public void ascOrderNodes(FXmlNodes nodes,
                             String... attrNames){
      nodes.sortByAttribute(true, attrNames);
   }

   //============================================================
   @Override
   public FXmlNode buildConfig(IXmlObject xentityGroup,
                               EXmlConfig type){
      if(null != xentityGroup){
         FXmlNode config = xentityGroup.toNode(type);
         if(RBoolean.parse(config.get(XEntityGroup.PTY_IS_VALID))){
            for(IXmlObject xEntity : xentityGroup.children()){
               if(RBoolean.parse(xEntity.innerGet(XMessage.PTY_IS_VALID))){
                  FXmlNode entityConfig = xEntity.toNode(type);
                  for(IXmlObject xitem : xEntity.children()){
                     if(RBoolean.parse(xitem.innerGet(XMessageItem.PTY_IS_VALID))){
                        FXmlNode itemConfig = xitem.toNode(type);
                        for(IXmlObject xchild : xitem.children()){
                           if(RBoolean.parse(xchild.innerGet(XMessageItem.PTY_IS_VALID))){
                              FXmlNode childConfig = xchild.toNode(type);
                              itemConfig.push(childConfig);
                           }
                        }
                        entityConfig.push(itemConfig);
                     }
                  }
                  setMessageFullLabel(entityConfig);
                  config.push(entityConfig);
               }
            }
            ascOrderNodes(config.nodes(), "full_name");
            return config;
         }
      }
      return null;
   }

   //============================================================
   @Override
   public FXmlNode buildConfig(String name){
      IXmlObject xEntity = this.find(name);
      return buildConfig(xEntity, EXmlConfig.Simple);
   }

   //============================================================
   @Override
   public FString buildSource(FXmlNode entityGroup,
                              String buildType){
      FModuleSourceBuilder sourceBuilder = new FModuleSourceBuilder(_templateConsole);
      FString source = null;
      if("head".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeSharedCppHead(entityGroup);
      }else if("body".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeSharedCppBody(entityGroup);
      }
      return source;
   }

   //============================================================
   @Override
   public FString buildSource(IXmlObject xentityGroup){
      if(!RBoolean.parse(xentityGroup.innerGet("is_valid"))){
         return new FString();
      }
      String splitter = RString.repeat("-", 60);
      FXmlNode entityGroup = buildConfig(xentityGroup, EXmlConfig.Simple);
      FModuleSourceBuilder sourceBuilder = new FModuleSourceBuilder(_templateConsole);
      // 获得项目信息
      String groupName = entityGroup.get("group");
      String groupProject = entityGroup.get("project");
      String groupHead = entityGroup.get("head_name");
      String groupSource = entityGroup.get("source_name");
      // 生成共享头文件
      FString shareHeadSource = sourceBuilder.makeSharedCppHead(entityGroup);
      String shareHeadFileName = _sourcepath + "/" + groupName + "/" + groupProject + "/" + groupHead + ".h";
      RFile.saveToFile(shareHeadFileName, shareHeadSource, REncoding.GBK);
      _logger.debug(this, "buildSource", "Build share cpp head. (file_name={1})", shareHeadFileName);
      // 生成共享体文件
      FString shareBodySource = sourceBuilder.makeSharedCppBody(entityGroup);
      String shareBodyFileName = _sourcepath + "/" + groupName + "/" + groupProject + "/" + groupSource + ".cpp";
      RFile.saveToFile(shareBodyFileName, shareBodySource, REncoding.GBK);
      _logger.debug(this, "buildSource", "Build share cpp head. (file_name={1})", shareHeadFileName);
      // 头代码解析
      for(FXmlNode module : entityGroup.nodes()){
         if(!RBoolean.parse(module.get("is_valid"))){
            continue;
         }
         if(!RBoolean.parse(module.get("is_storage"))){
            continue;
         }
         // 获得信息
         String headName = module.get("head_name");
         String entityName = module.get("source_entity");
         String moduleName = module.get("source_module");
         // 生成模块头文件
         String headFileName = _sourcepath + "/" + groupName + "/" + groupProject + "/" + headName + ".h";
         if(RFile.exists(headFileName)){
            FStringFile file = new FStringFile(headFileName);
            String source = file.toString().replace("\r\n", "\n");
            // 获得用户代码
            String customBegin = "// @manual (Custom.Begin)\n//" + splitter + "\n";
            String customEnd = "//" + splitter + "\n// @manual (Custom.End)\n";
            String customSource = findSource(source, customBegin, customEnd);
            module.set("head_custom", customSource);
            // 获得用户实体代码
            String entityBegin = "// @manual (Entity.Begin)\n   //" + splitter + "\n";
            String entityEnd = "//" + splitter + "\n   // @manual (Entity.End)\n";
            String entitySource = findSource(source, entityBegin, entityEnd);
            module.set("head_entity", entitySource);
            // 获得用户实体代码
            String moduleBegin = "// @manual (Module.Begin)\n   //" + splitter + "\n";
            String moduleEnd = "//" + splitter + "\n   // @manual (Module.End)\n";
            String moduleSource = findSource(source, moduleBegin, moduleEnd);
            module.set("head_module", moduleSource);
         }
         FString headSource = sourceBuilder.makeModuleCppHead(entityGroup, module);
         headSource.append("\n");
         RFile.saveToFile(headFileName, headSource, REncoding.GBK);
         _logger.debug(this, "buildSource", "Build module cpp head. (file_name={1})", headFileName);
         // 生成模块实体代码
         String entityFileName = _sourcepath + "/" + groupName + "/" + groupProject + "/" + entityName + ".cpp";
         if(RFile.exists(entityFileName)){
            FStringFile file = new FStringFile(entityFileName);
            String source = file.toString().replace("\r\n", "\n");
            // 获得用户代码
            String includeBegin = "// @manual (Include.Begin)\n//" + splitter + "\n";
            String includeEnd = "//" + splitter + "\n// @manual (Include.End)\n";
            String includeSource = findSource(source, includeBegin, includeEnd);
            module.set("body_include", includeSource);
            // 获得用户代码
            String entityBegin = "// @manual (Entity.Begin)\n//" + splitter + "\n";
            String entityEnd = "//" + splitter + "\n// @manual (Entity.End)\n";
            String entitySource = findSource(source, entityBegin, entityEnd);
            module.set("body_entity", entitySource);
         }
         FString entitySource = sourceBuilder.makeModuleCppEntity(entityGroup, module);
         entitySource.append("\n");
         RFile.saveToFile(entityFileName, entitySource, REncoding.GBK);
         _logger.debug(this, "buildSource", "Build module cpp entity. (file_name={1})", entityFileName);
         // 生成模块代码
         String moduleFileName = _sourcepath + "/" + groupName + "/" + groupProject + "/" + moduleName + ".cpp";
         if(RFile.exists(moduleFileName)){
            FStringFile file = new FStringFile(moduleFileName);
            String source = file.toString().replace("\r\n", "\n");
            // 获得用户代码
            String includeBegin = "// @manual (Include.Begin)\n//" + splitter + "\n";
            String includeEnd = "//" + splitter + "\n// @manual (Include.End)\n";
            String includeSource = findSource(source, includeBegin, includeEnd);
            module.set("body_include", includeSource);
            // 生成代码
            String findBegin = "// @manual (Construct.Begin)\n   //" + splitter + "\n";
            String findEnd = "//" + splitter + "\n   // @manual (Construct.End)\n";
            String oldSource = findSource(source, findBegin, findEnd);
            module.set("body_construct", oldSource);
            // 获得用户代码
            String moduleBegin = "// @manual (Module.Begin)\n//" + splitter + "\n";
            String moduleEnd = "//" + splitter + "\n// @manual (Module.End)\n";
            String moduleSource = findSource(source, moduleBegin, moduleEnd);
            module.set("body_module", moduleSource);
         }
         FString moduleSource = sourceBuilder.makeModuleCppModule(entityGroup, module);
         moduleSource.append("\n");
         RFile.saveToFile(moduleFileName, moduleSource, REncoding.GBK);
         _logger.debug(this, "buildSource", "Build module cpp module. (file_name={1})", moduleFileName);
      }
      return new FString();
   }

   //============================================================
   @Override
   public void buildSourceAll(){
      for(XEntityGroup xgroup : list()){
         buildSource(xgroup);
      }
   }

   //============================================================
   @Override
   public String buildStorePath(String fileName){
      return RFile.makeFilename(_sourcepath, fileName);
   }

   //============================================================
   @Override
   public FString buildView(IXmlObject xentityGroup,
                            String buildType){
      // 获得文件
      FModuleSourceBuilder sourceBuilder = new FModuleSourceBuilder(_templateConsole);
      FXmlNode entityGroup = buildConfig(xentityGroup, EXmlConfig.Simple);
      FString source = null;
      if("head".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeSharedCppHead(entityGroup);
      }else if("body".equalsIgnoreCase(buildType)){
         source = sourceBuilder.makeSharedCppBody(entityGroup);
      }
      return source;
   }

   //============================================================
   @Override
   public FObjects<XEntityGroup> createCollection(){
      return new FObjects<XEntityGroup>(XEntityGroup.class);
   }

   //============================================================
   public String findSource(String source,
                            String findBegin,
                            String findEnd){
      String result = "";
      int begin = source.indexOf(findBegin);
      int end = source.indexOf(findEnd);
      if((-1 != begin) && (-1 != end)){
         result = source.substring(begin + findBegin.length(), end);
      }
      return result;
   }

   //============================================================
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
   public String sourcePath(){
      return RFile.format(_sourcepath);
   }
}

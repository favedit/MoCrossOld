package org.mo.game.editor.core.logic;

import org.mo.com.io.RFile;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
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
import org.mo.game.editor.core.enums.common.XEnum;
import org.mo.game.editor.core.enums.common.XEnumGroup;
import org.mo.game.editor.core.enums.common.XEnumItem;
import org.mo.game.editor.core.logic.common.XLogic;

//============================================================
// <T>枚举定义控制台。</T>
//============================================================
public class FLogicConsole
      extends FXmlConfigConsole<XLogic>
      implements
         ILogicConsole
{
   private static ILogger _logger = RLogger.find(FLogicConsole.class);

   @AProperty
   private static String _templatePath;

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
   public void buildAll(ELogicSource type){
      if((ELogicSource.All == type) || (ELogicSource.Lua == type)){
         buildAllCpp();
      }
   }

   //============================================================
   // <T>建立所有CPP代码。</T>
   //============================================================
   protected void buildAllCpp(){
      FLogicSourceBuilder sourceBuilder = new FLogicSourceBuilder(_templateConsole);
      //      for(XEnumGroup xgroup : list()){
      //         // 检查有效性
      //         if(!RBoolean.parse(xgroup.innerGet(XEnumGroup.PTY_IS_VALID))){
      //            continue;
      //         }
      //         if(!RBoolean.parse(xgroup.innerGet(XEnumGroup.PTY_IS_CLIENT_AS))){
      //            continue;
      //         }
      //         FXmlNode config = buildConfig(xgroup, EXmlConfig.Simple);
      //         // 生成枚举代码
      //         String codeName = xgroup.innerGet("code_name");
      //         String asPath = RFile.makeFilename(sourceClientPath(), "E" + codeName + ".as");
      //         FString asSource = sourceBuilder.makeAsSource(config);
      //         RFile.saveToFile(asPath, asSource, REncoding.UTF8);
      //         _logger.debug(this, "buildAllCpp", "Build cpp head source. (file={0})", asPath);
      //         // 生成集合代码
      //         for(FXmlNode xenum : config.nodes()){
      //            if(RBoolean.parse(xenum.get(XEnum.PTY_IS_CLIENT_AS))){
      //               if(RBoolean.parse(xenum.get(XEnum.PTY_IS_SET))){
      //                  String name = xenum.get("name");
      //                  String path = RFile.makeFilename(sourceClientPath(), "T" + name + "Set.as");
      //                  FString source = sourceBuilder.makeAsSetSource(xenum);
      //                  RFile.saveToFile(path, source, REncoding.UTF8);
      //                  _logger.debug(this, "buildAllCpp", "Build cpp head source. (file={0})", path);
      //               }
      //            }
      //         }
      //      }
   }

   //============================================================
   @Override
   public FXmlNode buildConfig(IXmlObject xobject,
                               EXmlConfig type){
      if(null == xobject){
         return null;
      }
      FXmlNode config = xobject.toNode(type);
      if(!RBoolean.parse(config.get(XEnumGroup.PTY_IS_VALID))){
         return null;
      }
      // 设置引用列表
      String includeHeads = config.get(XEnumGroup.PTY_INCLUDE_HEADS);
      if(RString.isNotEmpty(includeHeads)){
         for(String head : RString.split(includeHeads, "\n")){
            String include = head.trim();
            if(RString.isNotBlank(include)){
               //               XEnumGroup xgroup = find(include);
               //               if(null != xgroup){
               //                  FXmlNode includeNode = config.createNode("Include");
               //                  includeNode.attributes().append(xgroup.toSimpleAttributes());
               //               }
            }
         }
      }
      // 设置属性
      for(IXmlObject xenum : xobject.children()){
         if(RBoolean.parse(xenum.innerGet(XEnum.PTY_IS_VALID))){
            FXmlNode messageConfig = xenum.toNode(type);
            for(IXmlObject xitem : xenum.children()){
               if(RBoolean.parse(xitem.innerGet(XEnumItem.PTY_IS_VALID))){
                  FXmlNode itemConfig = xitem.toNode(type);
                  messageConfig.push(itemConfig);
               }
            }
            config.push(messageConfig);
         }
      }
      if(config.isName(XEnum.NAME)){
         // 设置CS类型
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
   protected FObjects<XLogic> createCollection(){
      return new FObjects<XLogic>(XLogic.class);
   }

   //============================================================
   @Override
   public String searchConstantCode(String name){
      //      for(XEnumGroup xgroup : list()){
      //         IXmlObject xconstant = xgroup.search("name", name);
      //         if(null != xconstant){
      //            return xconstant.innerGet("code");
      //         }
      //      }
      return name;
   }

   //============================================================
   @Override
   public String searchConstantValue(String name){
      //      for(XEnumGroup xgroup : list()){
      //         IXmlObject xconstant = xgroup.search("name", name);
      //         if(null != xconstant){
      //            return xconstant.innerGet("value");
      //         }
      //      }
      return name;
   }

   //============================================================
   @Override
   public String searchConstantValue(String name,
                                     String value){
      //      for(XEnumGroup xgroup : list()){
      //         IXmlObject xconstant = xgroup.search("name", name);
      //         if(null != xconstant){
      //            return xconstant.innerGet("value");
      //         }
      //      }
      return value;
   }

   //============================================================
   @Override
   public FXmlNode searchEnum(String name){
      //      for(XEnumGroup xgroup : list()){
      //         IXmlObject xenum = xgroup.search("name", name);
      //         if(null != xenum){
      //            return xenum.toSimpleNode();
      //         }
      //      }
      return null;
   }

   //============================================================
   @Override
   public XEnum searchEnumObject(String name){
      //      for(XEnumGroup xgroup : list()){
      //         IXmlObject xenum = xgroup.search("name", name);
      //         if(null != xenum){
      //            return (XEnum)xenum;
      //         }
      //      }
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

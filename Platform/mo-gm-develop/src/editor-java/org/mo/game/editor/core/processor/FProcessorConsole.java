package org.mo.game.editor.core.processor;

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
import org.mo.game.editor.common.source.FEditorSource;
import org.mo.game.editor.core.processor.common.XFunction;
import org.mo.game.editor.core.processor.common.XProcessor;
import org.mo.game.editor.core.processor.common.XProject;

public class FProcessorConsole
      extends FXmlConfigConsole<XProject>
      implements
         IProcessorConsole
{

   private static ILogger _logger = RLogger.find(FProcessorConsole.class);

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
   public void buildAllSource(){
      for(XProject project : list()){
         buildSource(project, null);
      }
   }

   //============================================================
   @Override
   public FXmlNode buildConfig(IXmlObject xproject,
                               EXmlConfig type){
      if(null != xproject){
         FXmlNode config = xproject.toNode(type);
         if(RBoolean.parse(config.get(XProject.PTY_IS_VALID, null))){
            for(IXmlObject xstorage : xproject.children()){
               if(RBoolean.parse(xstorage.innerGet(XProcessor.PTY_IS_VALID))){
                  FXmlNode storageConfig = xstorage.toNode(type);
                  for(IXmlObject xitem : xstorage.children()){
                     if(RBoolean.parse(xitem.innerGet(XFunction.PTY_IS_VALID))){
                        FXmlNode itemConfig = xitem.toNode(type);
                        storageConfig.push(itemConfig);
                     }
                  }
                  //setMessageFullLabel(storageConfig);
                  config.push(storageConfig);
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
      IXmlObject xstorage = find(name);
      return buildConfig(xstorage, EXmlConfig.Simple);
   }

   //============================================================
   protected void buildProcessorConfig(FXmlNode config){
      for(FXmlNode xmethod : config.nodes()){
         if(xmethod.isName("Function")){
            if("OM".equals(xmethod.get("type", null))){
               String sourceFrom = xmethod.get("source_from", null);
               if(!RString.isEmpty(sourceFrom)){
                  String[] sources = RString.trim(RString.split(sourceFrom, ","));
                  for(String source : sources){
                     FXmlNode sourceNode = xmethod.createNode("Source");
                     sourceNode.set("source_name", source);
                  }
               }
            }
         }
      }
   }

   //============================================================
   @Override
   public FString buildSource(FXmlNode processorNode,
                              String buildType){
      return new FString();
   }

   //============================================================
   @Override
   public FString buildSource(IXmlObject xproject,
                              String buildType){
      if(!RBoolean.parse(xproject.innerGet("is_valid"))){
         return new FString();
      }
      FXmlNode project = buildConfig(xproject, EXmlConfig.Simple);
      FProcessorSourceBuilder sourceBuilder = new FProcessorSourceBuilder(_templateConsole);
      // 获得项目信息
      String processorGroup = project.get("group", null);
      String processorSource = project.get("source_name", null);
      for(FXmlNode xprocessor : project.nodes()){
         if(!RBoolean.parse(xprocessor.get("is_valid", null))){
            continue;
         }
         // 建立结构
         buildProcessorConfig(xprocessor);
         // 建立头文件
         String headName = xprocessor.get("head_name", null);
         String fileName = _sourcepath + "/" + processorGroup + "/" + processorSource + "/" + headName + ".h";
         if(RFile.exists(fileName)){
            FEditorSource source = new FEditorSource(fileName, REncoding.GBK);
            xprocessor.set("source_include", source.find("Include"));
            xprocessor.set("source_logic", source.find("Logic"));
            xprocessor.set("source_processor_logic", source.find("ProcessorLogic"));
         }
         FString result = sourceBuilder.makeCppHead(project, xprocessor);
         RFile.saveToFile(fileName, result, REncoding.GBK);
         _logger.debug(this, "buildSource", "Build cpp head file. (file_name={1})", fileName);
         // 建立体文件
         String sourceName = xprocessor.get("source_name", null);
         fileName = _sourcepath + "/" + processorGroup + "/" + processorSource + "/" + sourceName + ".cpp";
         if(RFile.exists(fileName)){
            FEditorSource source = new FEditorSource(fileName, REncoding.GBK);
            xprocessor.set("source_include", source.find("Include"));
            xprocessor.set("source_construct", source.find("Construct"));
            xprocessor.set("source_process_before", source.find("ProcessBefore"));
            xprocessor.set("source_process_after", source.find("ProcessAfter"));
            xprocessor.set("source_processor_logic", source.find("ProcessorLogic"));
            xprocessor.set("source_register_all", source.find("RegisterAll"));
            // 获取代码信息
            for(FXmlNode xmethod : xprocessor.nodes()){
               String methodName = xmethod.get("name", null);
               xmethod.set("source", source.find(methodName));
            }
         }
         // 生成代码
         result = sourceBuilder.makeCppBody(project, xprocessor);
         RFile.saveToFile(fileName, result, REncoding.GBK);
         _logger.debug(this, "buildSource", "Build cpp body file. (file_name={1})", fileName);
      }
      return new FString();
   }

   //============================================================
   @Override
   public String buildStorePath(String fileName){
      return RFile.makeFilename(_sourcepath, fileName);
   }

   //============================================================
   @Override
   public FObjects<XProject> createCollection(){
      return new FObjects<XProject>(XProject.class);
   }

   //============================================================
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
      return RFile.format(_sourcepath);
   }
}

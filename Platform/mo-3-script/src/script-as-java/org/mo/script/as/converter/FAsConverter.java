package org.mo.script.as.converter;

import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FObject;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.script.as.common.FAsSourceDirectory;
import org.mo.script.as.parser.FAsClass;
import org.mo.script.as.parser.FAsParser;
import org.mo.script.as.parser.FAsParserContent;
import org.mo.script.as.parser.FAsProcessContent;

//============================================================
// <T>AS转换器。</T>
//============================================================
public class FAsConverter
      extends FObject
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FAsConverter.class);

   // 解析器
   protected FAsParser _parser = new FAsParser();

   // 转换环境
   protected FAsConvertContent _content = new FAsConvertContent();

   //============================================================
   // <T>AS转换器。</T>
   //============================================================
   public FAsConverter(){
   }

   //============================================================
   // <T>加载设置信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   public void loadConfig(FXmlNode xconfig){
      FXmlNode classConvertNode = xconfig.findNode("ClassConvert");
      if(null != classConvertNode){
         _content.classeConverts().loadConfig(classConvertNode);
      }
   }

   //============================================================
   // <T>加载设置文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void loadConfigFile(String fileName){
      _logger.debug(this, "loadConfigFile", "Load config file. (file_name={1})", fileName);
      FXmlDocument xdocument = new FXmlDocument();
      xdocument.loadFile(fileName);
      loadConfig(xdocument.root());
   }

   //============================================================
   // <T>转换处理。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void parseDirectory(String sourcePath, String targetPath){
      _parser.parseDirectory(sourcePath, targetPath);
   }

   //============================================================
   // <T>转换处理。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void convertFile(String filter){
      // 转换
      String fromDirectory = "D:/WP-Client3d/mo-1-common/1-common";
      String targetDirectory = "D:/WP-Platform/mp-app-qkb-2-client/src/common-1-common-java";
      // 打开文件
      _parser.parseFile("D:/WP-Client3d/mo-1-common/1-common/mo/cm/lang/FAbsMap.as");
      //_parser.parseDirectory(fromDirectory);
      // 存储文件
      FAsParserContent content = _parser.content();
      for(FAsClass asClass : content.classes().values()){
         String fileName = asClass.sourceFileName();
         if(!fileName.contains(filter)){
            continue;
         }
         String targetName = targetDirectory + fileName.substring(fromDirectory.length());
         String targetPath = RFile.makeDirectoryName(targetName);
         // 生成代码
         FTextSource source = new FTextSource();
         asClass.convert(_content, source);
         // 存储文件
         FStringFile file = new FStringFile();
         file.assign(source);
         file.saveFile(targetPath + "\\" + asClass.name() + ".java");
         System.out.println(targetName);
      }
   }

   //============================================================
   // <T>转换处理。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void convert(){
      FAsParserContent content = _parser.content();
      // 处理过程
      FAsProcessContent processContent = new FAsProcessContent();
      _parser.process(processContent);
      // 存储类集合
      for(FAsClass asClass : content.classes().values()){
         // 略过替换类
         String className = asClass.fullName();
         if(_content.classeConverts().contains(className)){
            continue;
         }
         // 计算路径
         FAsSourceDirectory directory = asClass.sourceDirectory();
         String fileName = asClass.sourceFileName();
         String fromDirectory = directory.sourceDirectory();
         String targetName = directory.targetDirectory();
         targetName = targetName + fileName.substring(fromDirectory.length());
         String targetPath = RFile.makeDirectoryName(targetName);
         String targetFileName = targetPath + "\\" + asClass.name() + ".java";
         // 生成代码
         FTextSource source = new FTextSource();
         asClass.convert(_content, source);
         // 存储文件
         FStringFile file = new FStringFile();
         file.assign(source);
         file.saveFile(targetFileName, "utf-8");
         _logger.debug(this, "convert", "Convert source file. (file_name={1})", targetFileName);
      }
   }

   //   public static void main(String[] args){
   //      FAsConverter converter = new FAsConverter();
   //      String configFileName = "D:/WP-Platform/mo-3-script/config/as-class.xml";
   //      converter.loadConfigFile(configFileName);
   //      converter.convertFile("FAbsMap");
   //   }
}

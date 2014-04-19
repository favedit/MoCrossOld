package org.mo.script.java.converter;

import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FObject;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.script.as.converter.FAsClassConvert;
import org.mo.script.java.parser.FJavaClass;
import org.mo.script.java.parser.FJavaParser;
import org.mo.script.java.parser.FJavaParserContent;
import org.mo.script.java.parser.FJavaProcessContent;

//============================================================
// <T>Java转换器。</T>
//============================================================
public class FJavaConverter
      extends FObject
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FJavaConverter.class);

   // 解析器
   protected FJavaParser _parser = new FJavaParser();

   // 转换环境
   protected FJavaConvertContent _content = new FJavaConvertContent();

   //============================================================
   // <T>Java转换器。</T>
   //============================================================
   public FJavaConverter(){
   }

   //============================================================
   // <T>加载设置信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   public void loadConfig(FXmlNode xconfig){
      FXmlNode classConvertNode = xconfig.findNode("ClassConvert");
      for(FXmlNode convertNode : classConvertNode.nodes()){
         if(convertNode.isName("Convert")){
            FAsClassConvert convert = new FAsClassConvert();
            convert.loadConfig(convertNode);
            _content.classeConverts().set(convert.javaClassName(), convert);
         }
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
   // <T>解析代码文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void parseFile(String fileName){
      // 转换
      String fromDirectory = "D:/WP-Platform/mo-4-cross-client-as/src/common-lang-java";
      String targetDirectory = "D:/WP-Client3d/mo-1-common-java/1-common";
      // 打开文件
      _parser.parseFile(fileName);
      //_parser.parseDirectory(fromDirectory);
      // 存储文件
      FJavaParserContent content = _parser.content();
      for(FJavaClass javaClass : content.classes().values()){
         //String fileName = javaClass.fileName();
         String targetName = targetDirectory + fileName.substring(fromDirectory.length());
         String targetPath = RFile.makeDirectoryName(targetName);
         // 生成代码
         FTextSource source = new FTextSource();
         javaClass.convert(_content, source);
         // 存储文件
         String convertFileName = targetPath + "\\" + javaClass.name() + ".as";
         FStringFile file = new FStringFile();
         file.assign(source);
         file.saveFile(convertFileName);
         _logger.debug(this, "convertFile", "Save convert file. (file_name={1})", convertFileName);
      }
   }

   //============================================================
   // <T>转换处理。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void convertFile(String filter){
      // 转换
      String fromDirectory = "D:/WP-Platform/mo-4-cross-client-as/src/common-lang-java";
      String targetDirectory = "D:/WP-Client3d/mo-1-common-java/1-common";
      // 打开文件
      _parser.parseFile("D:/WP-Platform/mo-4-cross-client-as/src/common-lang-java/mo/cm/lang/IAsObject.java");
      _parser.parseFile("D:/WP-Platform/mo-4-cross-client-as/src/common-lang-java/mo/cm/lang/FAsObject.java");
      // 处理过程
      FJavaProcessContent processContent = new FJavaProcessContent();
      _parser.process(processContent);
      //_parser.parseDirectory(fromDirectory);
      // 存储文件
      FJavaParserContent content = _parser.content();
      for(FJavaClass javaClass : content.classes().values()){
         String fileName = javaClass.fileName();
         String targetName = targetDirectory + fileName.substring(fromDirectory.length());
         String targetPath = RFile.makeDirectoryName(targetName);
         // 生成代码
         FTextSource source = new FTextSource();
         javaClass.convert(_content, source);
         // 存储文件
         String convertFileName = targetPath + "\\" + javaClass.name() + ".as";
         FStringFile file = new FStringFile();
         file.assign(source);
         file.saveFile(convertFileName);
         _logger.debug(this, "convertFile", "Save convert file. (file_name={1})", convertFileName);
      }
   }

   //============================================================
   // <T>转换处理。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void convert(){
      // 转换
      // D:\WP-Platform\mo-4-cross-client-as\src\common-lang-java\mo\cm\lang
      String fromDirectory = "D:/WP-Platform/mo-4-cross-client-as/src/common-lang-java";
      String targetDirectory = "D:/WP-Client3d/mo-1-common-java/1-common";
      // 打开文件
      _parser.parseDirectory(fromDirectory);
      // 存储文件
      FJavaParserContent content = _parser.content();
      for(FJavaClass javaClass : content.classes().values()){
         String fileName = javaClass.fileName();
         String targetName = targetDirectory + fileName.substring(fromDirectory.length());
         String targetPath = RFile.makeDirectoryName(targetName);
         // 生成代码
         FTextSource source = new FTextSource();
         javaClass.convert(_content, source);
         // 存储文件
         FStringFile file = new FStringFile();
         file.assign(source);
         file.saveFile(targetPath + "\\" + javaClass.name() + ".java");
      }
   }

   public static void main(String[] args){
      FJavaConverter converter = new FJavaConverter();
      String configFileName = "D:/WP-Platform/mo-3-script/config/java-convert.xml";
      converter.loadConfigFile(configFileName);
      converter.convertFile("IAsObject");
   }
}

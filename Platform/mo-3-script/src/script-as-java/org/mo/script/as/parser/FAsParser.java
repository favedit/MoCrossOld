package org.mo.script.as.parser;

import org.mo.com.io.FFileInfo;
import org.mo.com.io.FFileInfos;
import org.mo.com.io.FStringFile;
import org.mo.com.io.RDirectory;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.text.parser.FTextParser;
import org.mo.script.as.common.FAsSourceDirectory;
import org.mo.script.as.common.FAsTextContextL1;

//============================================================
// <T>解析器对象。</T>
//============================================================
public class FAsParser
      extends FObject
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FAsParser.class);

   // 解析环境
   protected FAsParserContent _content = new FAsParserContent();

   //============================================================
   // <T>构造解析器对象。</T>
   //============================================================
   public FAsParser(){
   }

   //============================================================
   // <T>获得解析环境。</T>
   //
   // @return 解析环境
   //============================================================
   public FAsParserContent content(){
      return _content;
   }

   //============================================================
   // <T>解析文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void parseFile(String fileName){
      // 打开文件
      FStringFile file = new FStringFile(fileName);
      FTextParser parser = new FTextParser();
      parser.setContext(new FAsTextContextL1());
      parser.parse(file.toString());
      // 创建类对象
      FAsClass clazz = new FAsClass();
      clazz.setSourceFileName(fileName);
      clazz.parse(_content, parser.token());
      _content.classes().set(clazz.name(), clazz);
   }

   //============================================================
   // <T>解析文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void parseFile(FAsSourceDirectory directory, String fileName){
      _logger.debug(this, "parseFile", "Parse source file. (file_name={1})", fileName);
      // 打开文件
      FStringFile file = new FStringFile(fileName, "utf-8");
      FTextParser parser = new FTextParser();
      parser.setContext(new FAsTextContextL1());
      parser.parse(file.toString());
      // 创建类对象
      FAsClass clazz = new FAsClass();
      clazz.setSourceDirectory(directory);
      clazz.setSourceFileName(fileName);
      clazz.parse(_content, parser.token());
      // 存储类
      String className = clazz.fullName();
      if(RString.isEmpty(className)){
         _logger.error(this, "parseFile", "Class name is null. (file={1})", fileName);
      }else{
         _content.classes().set(clazz.fullName(), clazz);
      }
   }

   //============================================================
   // <T>解析代码目录。</T>
   //
   // @param directoryName 目录
   //============================================================
   public void parseDirectory(String directoryName){
      // 获得文件集合
      FFileInfos files = RDirectory.listFiles(directoryName, null, ".as");
      // 建立类对照表
      int directoryLength = directoryName.length();
      for(FFileInfo file : files){
         String fileName = file.fileName();
         String className = fileName.substring(directoryLength + 1);
         className = className.substring(0, className.length() - 3);
         className = RString.replace(className, '\\', '.');
         // 设置导入集合
         FAsImport asImport = new FAsImport();
         asImport.setName(className);
         _content.imports().push(asImport);
      }
      // 解析类对照表
      for(FFileInfo file : files){
         parseFile(file.fileName());
      }
   }

   //============================================================
   // <T>解析代码目录。</T>
   //
   // @param directoryName 目录
   //============================================================
   public void parseDirectory(String sourcePath, String targetPath){
      // 创建代码目录
      FAsSourceDirectory directory = new FAsSourceDirectory();
      directory.setSourceDirectory(sourcePath);
      directory.setTargetDirectory(targetPath);
      // 获得文件集合
      FFileInfos files = RDirectory.listFiles(sourcePath, null, ".as");
      // 建立类对照表
      int directoryLength = sourcePath.length();
      for(FFileInfo file : files){
         String fileName = file.fileName();
         String className = fileName.substring(directoryLength + 1);
         className = className.substring(0, className.length() - 3);
         className = RString.replace(className, '\\', '.');
         // 设置导入集合
         FAsImport asImport = new FAsImport();
         asImport.setName(className);
         _content.imports().push(asImport);
      }
      // 解析类对照表
      for(FFileInfo file : files){
         parseFile(directory, file.fileName());
      }
   }

   //============================================================
   // <T>解析处理。</T>
   //
   // @param content 处理环境
   //============================================================
   public void process(FAsProcessContent content){
      content.packages().assign(_content.packages());
      content.imports().assign(_content.imports());
      content.classes().assign(_content.classes());
      for(FAsClass asClass : content.classes().toObjects()){
         asClass.process(content);
      }
   }

   public static void main(String[] args){
      // 打开文件
      //String fileName = "D:/WP-Client/mo-6-game2d/1-core/mo/gm/core/ui/control/display/FGmUiRichText.as";
      //String fileName = "D:/WP-Client3d/mo-1-common/1-common/mo/cm/lang/IObject.as";
      String fileName = "D:/WP-Client3d/mo-1-common/1-common/mo/cm/lang/FList.as";
      FStringFile file = new FStringFile(fileName);
      FTextParser parser = new FTextParser();
      parser.setContext(new FAsTextContextL1());
      parser.parse(file.toString());
      //System.out.println(parser.token().dump());
      //
      FAsClass clazz = new FAsClass();
      FAsParserContent content = new FAsParserContent();
      clazz.parse(content, parser.token());
      //
      System.out.println(clazz.dump());
      //parser.setContext(new FAsTextContextL2());
      //parser.parseSelf();
      //System.out.println(parser.token().dump());
   }
}

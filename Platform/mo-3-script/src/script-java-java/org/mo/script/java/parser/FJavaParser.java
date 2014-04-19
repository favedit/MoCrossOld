package org.mo.script.java.parser;

import org.mo.com.io.FFileInfo;
import org.mo.com.io.FFileInfos;
import org.mo.com.io.FStringFile;
import org.mo.com.io.RDirectory;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;
import org.mo.com.text.parser.FTextParser;
import org.mo.script.as.common.FAsTextContextL1;

//============================================================
// <T>解析器对象。</T>
//============================================================
public class FJavaParser
      extends FObject
{
   // 解析环境
   protected FJavaParserContent _content = new FJavaParserContent();

   //============================================================
   // <T>构造解析器对象。</T>
   //============================================================
   public FJavaParser(){
   }

   //============================================================
   // <T>获得解析环境。</T>
   //
   // @return 解析环境
   //============================================================
   public FJavaParserContent content(){
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
      FJavaClass clazz = new FJavaClass();
      clazz.setFileName(fileName);
      clazz.parse(_content, parser.token());
      _content.classes().set(clazz.fullName(), clazz);
   }

   //============================================================
   // <T>解析处理。</T>
   //
   // @param content 处理环境
   //============================================================
   public void process(FJavaProcessContent content){
      content.packages().assign(_content.packages());
      content.imports().assign(_content.imports());
      content.classes().assign(_content.classes());
      for(FJavaClass javaClass : content.classes().toObjects()){
         javaClass.process(content);
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
         FJavaImport asImport = new FJavaImport();
         asImport.setName(className);
         _content.imports().push(asImport);
      }
      // 解析类对照表
      for(FFileInfo file : files){
         parseFile(file.fileName());
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
      FJavaClass clazz = new FJavaClass();
      FJavaParserContent content = new FJavaParserContent();
      clazz.parse(content, parser.token());
      //

      System.out.println(clazz.dump());
      //parser.setContext(new FAsTextContextL2());
      //parser.parseSelf();
      //System.out.println(parser.token().dump());
   }
}

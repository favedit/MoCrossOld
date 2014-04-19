package org.mu.com.lang.collection;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.RString;

public class RMakeCollectionString
{
   protected static String sourcePath = "D:\\WP-Platform\\mo-1-common\\src\\lang-util\\org\\mu\\com\\lang\\collection";

   //protected static String filePath = "D:\\WP-Script\\1-Common\\MoCommon";
   //protected static String filePath = "D:\\WP-Script\\1-Common\\MoCommon";
   protected static String filePath = "D:\\WP-Server\\Test\\MoTstString";

   public static String makeMethodTools(String type){
      FStringFile file = new FStringFile();
      file.loadFile(sourcePath + "\\template.string.tools." + type + ".h", "utf-8");
      String source = file.toString();
      return source;
   }

   public static String makeMethodPublic(String memory,
                                         String capacity){
      FStringFile file = new FStringFile();
      file.loadFile(sourcePath + "\\template.string.methods.h", "utf-8");
      String source = file.toString();
      source = RString.replace(source, "{memory}", memory);
      source = RString.replace(source, "{capacity}", capacity);
      return source;
   }

   public static String makeMethodType(String type,
                                       String memory,
                                       String capacity){
      FStringFile file = new FStringFile();
      file.loadFile(sourcePath + "\\template.string.methods." + type + ".h", "utf-8");
      String source = file.toString();
      source = RString.replace(source, "{memory}", memory);
      source = RString.replace(source, "{capacity}", capacity);
      return source;
   }

   public static void makeHeadFile(String type){
      String source = null;
      //------------------------------------------------------------
      // 生成头文件
      FStringFile file = new FStringFile();
      file.loadFile(sourcePath + "\\template.string.h", "utf-8");
      source = file.toString();
      // 生成Array
      source = RString.replace(source, "{source_tools}", makeMethodTools(type));
      source = RString.replace(source, "{source_public_1}", makeMethodPublic("_pMemory", "_capacity"));
      source = RString.replace(source, "{source_type_1}", makeMethodType(type, "_pMemory", "_capacity"));
      source = RString.replace(source, "{source_public_2}", makeMethodPublic("_memory", "S"));
      source = RString.replace(source, "{source_type_2}", makeMethodType(type, "_memory", "S"));
      source = RString.replace(source, "{source_entry_list}", new FStringFile(sourcePath + "\\template.entry.list.h").toString());
      source = RString.replace(source, "{source_entry_iterator}", new FStringFile(sourcePath + "\\template.entry.iterator.h").toString());
      source = RString.replace(source, "{type}", type);
      source = RString.replace(source, "{char}", "TChar" + type);
      // 存储文件
      String fileName = filePath + "\\MoCmString" + type + ".h";
      FStringFile soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(fileName, "GBK");
      System.out.println(fileName);
      //      //------------------------------------------------------------
      //      // 生成体文件
      //      FStringFile mstringFile = new FStringFile();
      //      mstringFile.loadFile(sourcePath + "\\template.string.mstring.cpp", "utf-8");
      //      source = mstringFile.toString();
      //      // 生成Array
      //      source = RString.replace(source, "{type}", type);
      //      source = RString.replace(source, "{char}", "TChar" + type);
      //      // 存储文件
      //      fileName = filePath + "\\MString" + type + ".cpp";
      //      soureFile = new FStringFile();
      //      soureFile.assign(source);
      //      soureFile.saveFile(fileName, "GBK");
   }

   public static void main(String[] args){
      makeHeadFile("8");
      makeHeadFile("16");
      makeHeadFile("32");
   }
}

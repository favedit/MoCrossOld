package org.mu.com.lang.cpp;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.RString;

//============================================================
public class RMakeCppSource
{
   public static String _sourcePath = "D:\\ZW-Platform-Work\\mo-1-common\\src\\lang-util\\org\\mu\\com\\lang\\cpp";

   public static String _targetPath = "D:\\ZW-Mobile-Work\\Cross\\Common\\MoCommon";

   //============================================================
   public static void makeArray(){
      String outputFileName = _targetPath + "\\MoCmArray.h";
      // 生成代码
      String source = new FStringFile(_sourcePath + "\\template.array.h", "utf-8").toString();
      // 存储文件
      FStringFile soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
   }

   //============================================================
   public static void makeVector(){
      String outputFileName = _targetPath + "\\MoCmVector.h";
      // 生成代码
      String source = new FStringFile(_sourcePath + "\\template.vector.h", "utf-8").toString();
      // 存储文件
      FStringFile soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
   }

   //============================================================
   public static void makeList(){
      String outputFileName = _targetPath + "\\MoCmList.h";
      // 生成代码
      String source = new FStringFile(_sourcePath + "\\template.list.h", "utf-8").toString();
      source = RString.replace(source, "{source_entry_list}", new FStringFile(_sourcePath + "\\template.entry.list.h", "utf-8").toString());
      // 存储文件
      FStringFile soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
   }

   //============================================================
   public static void makeSet(){
      String outputFileName = _targetPath + "\\MoCmSet.h";
      // 生成代码
      String source = new FStringFile(_sourcePath + "\\template.set.h", "utf-8").toString();
      source = RString.replace(source, "{source_entry_list}", new FStringFile(_sourcePath + "\\template.entry.list.h", "utf-8").toString());
      // 存储文件
      FStringFile soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
   }

   //============================================================
   public static void makeMap(){
      String outputFileName = _targetPath + "\\MoCmMap.h";
      // 生成代码
      String source = new FStringFile(_sourcePath + "\\template.map.h", "utf-8").toString();
      source = RString.replace(source, "{source_entry_list}", new FStringFile(_sourcePath + "\\template.entry.list.h", "utf-8").toString());
      // 存储文件
      FStringFile soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
   }

   //============================================================
   public static void makeString(String type){
      // 生成代码
      String source = new FStringFile(_sourcePath + "\\template.string.h", "utf-8").toString();
      source = RString.replace(source, "{source_entry_list}", new FStringFile(_sourcePath + "\\template.entry.list.h", "utf-8").toString());
      source = RString.replace(source, "{type}", type);
      source = RString.replace(source, "{char}", "TChar" + type);
      source = RString.replace(source, "{string}", "String" + type);
      // 存储文件
      String outputFileName = _targetPath + "\\MoCmString" + type + ".h";
      FStringFile soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
      //............................................................
      // 生成代码MString
      source = new FStringFile(_sourcePath + "\\template.string.mstring.cpp", "utf-8").toString();
      source = RString.replace(source, "{type}", type);
      source = RString.replace(source, "{char}", "TChar" + type);
      source = RString.replace(source, "{string}", "String" + type);
      // 存储文件
      outputFileName = _targetPath + "\\MString" + type + ".cpp";
      soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
      //............................................................
      // 生成代码FStringBuffer
      source = new FStringFile(_sourcePath + "\\template.string.fstringbuffer.cpp", "utf-8").toString();
      source = RString.replace(source, "{type}", type);
      source = RString.replace(source, "{char}", "TChar" + type);
      source = RString.replace(source, "{string}", "String" + type);
      // 存储文件
      outputFileName = _targetPath + "\\FStringBuffer" + type + ".cpp";
      soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
      //............................................................
      // 生成代码TStringBuffer
      source = new FStringFile(_sourcePath + "\\template.string.tstringbuffer.cpp", "utf-8").toString();
      source = RString.replace(source, "{type}", type);
      source = RString.replace(source, "{char}", "TChar" + type);
      source = RString.replace(source, "{string}", "String" + type);
      // 存储文件
      outputFileName = _targetPath + "\\TStringBuffer" + type + ".cpp";
      soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
      //............................................................
      // 生成代码MStrings
      source = new FStringFile(_sourcePath + "\\template.string.mstrings.cpp", "utf-8").toString();
      source = RString.replace(source, "{type}", type);
      source = RString.replace(source, "{char}", "TChar" + type);
      source = RString.replace(source, "{string}", "String" + type);
      // 存储文件
      outputFileName = _targetPath + "\\MString" + type + "s.cpp";
      soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
      //............................................................
      // 生成代码MProperties
      source = new FStringFile(_sourcePath + "\\template.string.mproperties.cpp", "utf-8").toString();
      source = RString.replace(source, "{type}", type);
      source = RString.replace(source, "{char}", "TChar" + type);
      source = RString.replace(source, "{string}", "String" + type);
      // 存储文件
      outputFileName = _targetPath + "\\MProperties" + type + ".cpp";
      soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
   }

   //============================================================
   public static void makeAttributes(){
      String outputFileName = _targetPath + "\\MoCmAttributes.h";
      // 生成代码
      String source = new FStringFile(_sourcePath + "\\template.attributes.h", "utf-8").toString();
      source = RString.replace(source, "{source_entry_list}", new FStringFile(_sourcePath + "\\template.entry.list.h", "utf-8").toString());
      // 存储文件
      FStringFile soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
   }

   //============================================================
   public static void makeDictionary(){
      String outputFileName = _targetPath + "\\MoCmDictionary.h";
      // 生成代码
      String source = new FStringFile(_sourcePath + "\\template.dictionary.h", "utf-8").toString();
      source = RString.replace(source, "{source_entry_list}", new FStringFile(_sourcePath + "\\template.entry.list.h", "utf-8").toString());
      // 存储文件
      FStringFile soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
   }

   //============================================================
   public static void main(String[] args){
      //makeArray();
      //makeVector();
      //makeList();
      //makeSet();
      //makeMap();
      //makeString("8");
      //makeString("16");
      //makeString("32");
      //makeAttributes();
      //makeDictionary();
   }
}

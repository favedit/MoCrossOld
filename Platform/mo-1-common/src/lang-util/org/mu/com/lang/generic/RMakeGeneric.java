package org.mu.com.lang.generic;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.RString;

public class RMakeGeneric
{
   private static String PATH_TEMPLATE = "D:\\ZW-Platform.WK\\mo-1-common\\src\\lang-util\\org\\mu\\com\\lang\\generic";

   //============================================================
   public static void makeLanguageTypes(String filePath,
                                        String subTemplate,
                                        String typeName,
                                        String typeBase,
                                        String typeDefault,
                                        String typeLabel){
      FStringFile file = new FStringFile();
      file.loadFile(PATH_TEMPLATE + "\\template.language.types.tpl", "utf-8");
      String source = file.toString();
      source = RString.replace(source, "{compare_source}", new FStringFile(PATH_TEMPLATE + "\\template.language.types." + subTemplate + ".tpl", "utf-8").toString());
      source = RString.replace(source, "{type_name}", typeName);
      source = RString.replace(source, "{type_base}", typeBase);
      source = RString.replace(source, "{type_label}", typeLabel);
      source = RString.replace(source, "{type_default}", typeDefault);
      file.assign(source);
      String fileName = filePath + "\\RBase" + typeName + ".java";
      file.saveFile(fileName, "utf-8");
      System.out.println(fileName);
   }

   //============================================================
   public static void makeGenericTypes(String filePath,
                                       String typeName,
                                       String typeBase,
                                       String typeValue,
                                       String typeLabel){
      String fileName = filePath + "\\M" + typeName + "s.java";
      FStringFile file = new FStringFile();
      file.loadFile(PATH_TEMPLATE + "\\template.generic.types.txt", "utf-8");
      String source = file.toString();
      source = RString.replace(source, "{type_name}", typeName);
      source = RString.replace(source, "{type_base}", typeBase);
      source = RString.replace(source, "{type_value}", typeValue);
      source = RString.replace(source, "{type_label}", typeLabel);
      file.assign(source);
      file.saveFile(fileName, "utf-8");
      System.out.println(fileName);
   }

   //============================================================
   public static void makeSet(String filePath,
                              String typeName,
                              String typeBase,
                              String typeFull){
      FStringFile file = new FStringFile();
      file.loadFile(PATH_TEMPLATE + "\\template.generic.set.txt", "utf-8");
      String source = file.toString();
      source = RString.replace(source, "{type_name}", typeName);
      source = RString.replace(source, "{type_base}", typeBase);
      source = RString.replace(source, "{type_full}", typeFull);
      file.assign(source);
      file.saveFile(filePath + "\\M" + typeName + "Set.java", "utf-8");
   }

   //============================================================
   public static void makeStream(String filePath,
                                 String typeName,
                                 String typeBase,
                                 String typeLabel){
      FStringFile file = new FStringFile();
      file.loadFile(PATH_TEMPLATE + "\\template.generic.stream.txt", "utf-8");
      String source = file.toString();
      source = RString.replace(source, "{type_name}", typeName);
      source = RString.replace(source, "{type_base}", typeBase);
      source = RString.replace(source, "{type_label}", typeLabel);
      file.assign(source);
      file.saveFile(filePath + "\\M" + typeName + "Stream.java", "utf-8");
   }

   //============================================================
   public static void makeCppTypes(String filePath,
                                   String typeName,
                                   String signName,
                                   String typeLabel){
      FStringFile file = new FStringFile();
      file.loadFile(PATH_TEMPLATE + "\\template.cpp.type.int.txt", "utf-8");
      String source = file.toString();
      source = RString.replace(source, "{type_name}", typeName);
      source = RString.replace(source, "{sign_name}", signName);
      source = RString.replace(source, "{type_label}", typeLabel);
      file.assign(source);
      file.saveFile(filePath + "\\R" + typeName + ".cpp", "GB2312");
   }

   //============================================================
   public static void main(String[] args){
      String filePath = "D:\\ZW-Platform.WK\\mo-1-common\\src\\lang-java\\org\\mo\\com";
      // 生成类型集合
      makeLanguageTypes(filePath + "\\lang\\type", "boolean", "Boolean", "boolean", "false", "布尔值");
      makeLanguageTypes(filePath + "\\lang\\type", "normal", "Char", "char", "0", "字符");
      makeLanguageTypes(filePath + "\\lang\\type", "normal", "Byte", "byte", "0", "字节");
      makeLanguageTypes(filePath + "\\lang\\type", "normal", "Short", "short", "0", "短整数");
      makeLanguageTypes(filePath + "\\lang\\type", "normal", "Integer", "int", "0", "整数");
      makeLanguageTypes(filePath + "\\lang\\type", "normal", "Long", "long", "0", "长整数");
      makeLanguageTypes(filePath + "\\lang\\type", "normal", "Float", "float", "0", "浮点数");
      makeLanguageTypes(filePath + "\\lang\\type", "normal", "Double", "double", "0", "双精度浮点数");
      makeLanguageTypes(filePath + "\\lang\\type", "boolean", "Object", "Object", "null", "对象");
      // 生成类型集合
      makeGenericTypes(filePath + "\\lang\\generic", "Boolean", "boolean", "false", "布尔值");
      makeGenericTypes(filePath + "\\lang\\generic", "Char", "char", "0", "字符");
      makeGenericTypes(filePath + "\\lang\\generic", "Byte", "byte", "0", "字节");
      makeGenericTypes(filePath + "\\lang\\generic", "Short", "short", "0", "短整数");
      makeGenericTypes(filePath + "\\lang\\generic", "Int", "int", "0", "整数");
      makeGenericTypes(filePath + "\\lang\\generic", "Long", "long", "0", "长整数");
      makeGenericTypes(filePath + "\\lang\\generic", "Float", "float", "0", "浮点数");
      makeGenericTypes(filePath + "\\lang\\generic", "Double", "double", "0", "双精度浮点数");
      // 生成哈希集合
      makeSet(filePath + "\\lang\\generic", "Int", "int", "Integer");
      makeSet(filePath + "\\lang\\generic", "Long", "long", "Long");
      // 生成数据流
      makeStream(filePath + "\\io\\base", "Boolean", "boolean", "布尔值");
      makeStream(filePath + "\\io\\base", "Char", "char", "字符");
      makeStream(filePath + "\\io\\base", "Byte", "byte", "字节");
      makeStream(filePath + "\\io\\base", "Short", "short", "短整数");
      makeStream(filePath + "\\io\\base", "Int", "int", "整数");
      makeStream(filePath + "\\io\\base", "Long", "long", "长整数");
      makeStream(filePath + "\\io\\base", "Float", "float", "浮点数");
      makeStream(filePath + "\\io\\base", "Double", "double", "双精度浮点数");
      // 完成
      System.out.println("OK");
   }
}

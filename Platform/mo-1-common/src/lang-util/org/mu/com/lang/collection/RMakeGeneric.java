package org.mu.com.lang.collection;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.RString;

public class RMakeGeneric
{
   private static String PATH_TEMPLATE = "D:\\WP-Platform\\mo-1-common\\src\\lang-util\\org\\mu\\com\\lang\\generic";

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

   public static void main(String[] args){
      //String filePath = "D:\\WP-Script\\1 - Common\\MoCommon";
      // 生成类型集合
      //      String filePath = "D:\\WP-Platform\\mo-1-common\\src\\lang-java\\org\\mo\\com";
      //      makeCppTypes(filePath, "Int8", "Sign", "有符号8位整数");
      //      makeCppTypes(filePath, "Int16", "Sign", "有符号16位整数");
      //      makeCppTypes(filePath, "Int32", "Sign", "有符号32位整数");
      //      makeCppTypes(filePath, "Int64", "Sign", "有符号64位整数");
      //      makeCppTypes(filePath, "Uint8", "Unsign", "无符号8位整数");
      //      makeCppTypes(filePath, "Uint16", "Unsign", "无符号16位整数");
      //      makeCppTypes(filePath, "Uint32", "Unsign", "无符号32位整数");
      //      makeCppTypes(filePath, "Uint64", "Unsign", "无符号64位整数");
      System.out.println("OK");
   }
}

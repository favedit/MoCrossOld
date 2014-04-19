package org.mu.com.lang.collection;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.RString;

public class RMakeCollectionTypes
{
   public static String _sourcePath = "D:\\ZW-Platform.WK\\mo-1-common\\src\\lang-util\\org\\mu\\com\\lang\\collection";

   public static String _filePath = "D:\\ZW-QKB-Work\\Server\\Common\\MoCommon";

   public static String makeMethods(String memory,
                                    String capacity){
      FStringFile file = new FStringFile();
      file.loadFile(_sourcePath + "\\template.types.methods.h", "utf-8");
      String source = file.toString();
      source = RString.replace(source, "{memory}", memory);
      source = RString.replace(source, "{capacity}", capacity);
      return source;
   }

   public static void main(String[] args){
      String source = null;
      FStringFile soureFile = null;
      FStringFile file = new FStringFile(_sourcePath + "\\template.types.h", "utf-8");
      // 生成类型集合
      String arraySource1 = makeMethods("_pMemory", "_capacity");
      String arraySource2 = makeMethods("_memory", "S");
      // 生成Array
      source = file.toString();
      source = RString.replace(source, "{source_1}", arraySource1);
      source = RString.replace(source, "{source_2}", arraySource2);
      source = RString.replace(source, "{source_type_1}", new FStringFile(_sourcePath + "\\template.types.define.1.h", "utf-8").toString());
      source = RString.replace(source, "{source_type_2}", new FStringFile(_sourcePath + "\\template.types.define.2.h", "utf-8").toString());
      source = RString.replace(source, "{source_type_3}", new FStringFile(_sourcePath + "\\template.types.define.3.h", "utf-8").toString());
      source = RString.replace(source, "{source_type_4}", new FStringFile(_sourcePath + "\\template.types.define.4.h", "utf-8").toString());
      source = RString.replace(source, "{type_full}", "ARRAY");
      source = RString.replace(source, "{type}", "Array");
      source = RString.replace(source, "{memory}", "_pMemory");
      source = RString.replace(source, "{length}", "length");
      source = RString.replace(source, "{Length}", "Length");
      source = RString.replace(source, "{length_label}", "数据长度");
      soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(_filePath + "\\MoCmArray.h", "GBK");
      // 生成Vector
      arraySource1 = makeMethods("_pMemory", "_capacity");
      arraySource2 = makeMethods("_memory", "S");
      source = file.toString();
      source = RString.replace(source, "{source_1}", arraySource1);
      source = RString.replace(source, "{source_2}", arraySource2);
      source = RString.replace(source, "{source_type_1}", "");
      source = RString.replace(source, "{source_type_2}", "");
      source = RString.replace(source, "{source_type_3}", "");
      source = RString.replace(source, "{source_type_4}", "");
      source = RString.replace(source, "{type_full}", "VECTOR");
      source = RString.replace(source, "{type}", "Vector");
      source = RString.replace(source, "{memory}", "_pMemory");
      source = RString.replace(source, "{length}", "count");
      source = RString.replace(source, "{Length}", "Count");
      source = RString.replace(source, "{length_label}", "数据个数");
      soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(_filePath + "\\MoCmVector.h", "GBK");
      System.out.println("Ok");
   }
}

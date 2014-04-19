package org.mu.com.lang.collection;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.RString;

public class RMakeCollectionList
{
   public static String _filePath = "D:\\WP-Script\\1-Common\\MoCommon";

   public static String _sourcePath = "D:\\WP-Platform\\mo-1-common\\src\\lang-util\\org\\mu\\com\\lang\\collection";

   public static String makeMethods(String memory,
                                    String length){
      FStringFile file = new FStringFile();
      file.loadFile(_sourcePath + "\\template.list.methods.h", "utf-8");
      String source = file.toString();
      source = RString.replace(source, "{memory}", memory);
      source = RString.replace(source, "{length}", length);
      return source;
   }

   public static void main(String[] args){
      FStringFile soureFile = null;
      // 生成类型集合
      String arraySource1 = makeMethods("_pMemory", "length");
      String arraySource2 = makeMethods("_memory", "length");
      FStringFile file = new FStringFile();
      file.loadFile(_sourcePath + "\\template.list.h", "utf-8");
      // 生成Array
      String source = file.toString();
      source = RString.replace(source, "{source_inner}", new FStringFile(_sourcePath + "\\template.entry.list.h", "utf-8").toString());
      source = RString.replace(source, "{source_1}", arraySource1);
      source = RString.replace(source, "{source_2}", arraySource2);
      soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(_filePath + "\\MoCmList.h", "GBK");
   }
}

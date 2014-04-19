package org.mu.com.lang.collection;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.RString;

public class RMakeCollectionSet
{
   public static String makeMethods(String entries,
                                    String memory){
      FStringFile file = new FStringFile();
      file.loadFile("D:\\WP-Platform\\mo-1-common\\src\\lang-util\\org\\mu\\com\\lang\\collection\\template.set.methods.txt", "utf-8");
      String source = file.toString();
      source = RString.replace(source, "{entries}", entries);
      source = RString.replace(source, "{memory}", memory);
      return source;
   }

   public static void main(String[] args){
      String filePath = "D:\\WP-Script\\1-Common\\MoCommon";
      String sourcePath = "D:\\WP-Platform\\mo-1-common\\src\\lang-util\\org\\mu\\com\\lang\\collection";
      FStringFile soureFile = null;
      // 生成类型集合
      String arraySource1 = makeMethods("_ppEntries", "length");
      String arraySource2 = makeMethods("_entries", "length");
      FStringFile file = new FStringFile();
      file.loadFile(sourcePath + "\\template.set.txt", "utf-8");
      // 生成Array
      String source = file.toString();
      source = RString.replace(source, "{source_inner}", new FStringFile(sourcePath + "\\template.entry.list.h", "utf-8").toString());
      source = RString.replace(source, "{source_1}", arraySource1);
      source = RString.replace(source, "{source_2}", arraySource2);
      soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(filePath + "\\MoCmSet.h", "GBK");
      System.out.println("Ok");
   }
}

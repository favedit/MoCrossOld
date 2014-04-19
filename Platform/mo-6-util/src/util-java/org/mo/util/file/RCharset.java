package org.mo.util.file;

import java.io.File;
import org.mo.com.io.FStringFile;

public class RCharset
{

   public static void convertPath(String path){
      File root = new File(path);
      for(File file : root.listFiles()){
         String name = file.getAbsolutePath();
         if(name.endsWith(".h") || name.endsWith(".cpp")){
            System.out.println(name);
            // Convert
            FStringFile source = new FStringFile();
            source.loadFile(name, "UTF-8");
            source.store("GBK");
         }
      }
   }

   public static void main(String[] args){
      //convertPath("D:/Totem/Common/MoCommon");
      convertPath("D:/Totem/Common/MoCore");
      convertPath("D:/Totem/Common/MoMessage");
      convertPath("D:/Totem/Common/MoModule");
   }
}

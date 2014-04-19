package org.mo.util.lines;

import org.mo.com.io.FLinesFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.RString;

public class RPlSqlCounter{

   public static void main(String[] args){
      int fileCount = 0;
      int lineCount = 0;
      int commentCount = 0;
      String path = "D:/Workspace/eUIS/webroot/WEB-INF/config/system.deploy.store/current/logic";
      for(String fileName : RFile.listAllFile(path)){
         fileName = fileName.toLowerCase();
         if(fileName.endsWith(".pkh") || fileName.endsWith(".pky")){
            System.out.println(fileName);
            fileCount++;
            FLinesFile file = new FLinesFile(fileName);
            for(String line : file.lines()){
               if(RString.isNotBlank(line)){
                  line = line.trim();
                  if(line.startsWith("--")){
                     commentCount++;
                  }else{
                     lineCount++;
                  }
               }
            }
         }
      }
      System.out.println("fileCount" + fileCount + " lineCount=" + lineCount + " commentCount=" + commentCount);
   }
}

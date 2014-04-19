package org.mo.util.javascript;

import java.io.File;
import org.mo.com.io.FLinesFile;
import org.mo.com.io.FStringFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.logging.RLogger;

//============================================================
// <T>JavaScript路径合并。</T>
//============================================================
public class FJsPathMerger
{
   // 字符编码
   private static String CHARSET = "UTF-8";

   //============================================================
   // <T>格式化文件。</T>
   //
   // @param source 来源
   // @param commont 注释
   // @param file 文件
   //============================================================
   public static void formatFile(FString source,
                                 boolean comment,
                                 File file){
      FLinesFile fileLines = new FLinesFile(file, CHARSET);
      int count = fileLines.count();
      for(int n = 0; n < count; n++){
         String line = fileLines.line(n);
         if(!RString.isBlank(line)){
            line = RString.trimRight(line);
            line = RString.removeChars(line, '\r');
            if(comment){
               //............................................................
               // 追加含有注释的代码内容
               source.appendLine(line);
            }else{
               //............................................................
               // 追加去除注释的代码内容
               String trim = line.trim();
               if(trim.startsWith("/*")){
                  // 删除块注释
                  for(n++; n < count; n++){
                     line = fileLines.line(n);
                     if(line.trim().endsWith("*/")){
                        break;
                     }
                  }
                  continue;
               }else if(trim.startsWith("//")){
                  // 删除行注释
                  continue;
               }
               source.appendLine(line);
            }
         }
      }
   }

   //============================================================
   // <T>启动处理。</T>
   //
   // @param params 参数集合
   //============================================================
   public static void main(String[] params){
      String path = null;
      String target = null;
      boolean comment = false;
      // 检查参数
      if(params.length == 0){
         path = "D:/WP-Platform.DP/mp-platform/webroot/ajs/0-runtime";
         target = "D:/WP-Platform.DP/mp-platform/webroot/ajs/runtime.js";
         comment = false;
      }else if(params.length == 3){
         path = params[0];
         target = params[1];
         comment = RBoolean.parse(params[2]);
      }else{
         System.out.println("Param count is invalid.");
         return;
      }
      try{
         // 链接目录下内容
         FStringFile result = new FStringFile();
         for(File file : new File(path).listFiles()){
            if(file.isFile()){
               formatFile(result, comment, file);
            }
         }
         // 存储文件
         result.saveFile(target, CHARSET);
         System.out.println("Merge javascript path file. (file_name=" + target + ")");
      }catch(Throwable t){
         RLogger.find(FJsPathMerger.class).error(null, "main", t);
      }
   }
}

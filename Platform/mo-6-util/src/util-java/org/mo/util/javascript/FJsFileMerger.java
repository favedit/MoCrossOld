package org.mo.util.javascript;

import org.mo.com.io.FStringFile;
import org.mo.com.logging.RLogger;

//============================================================
//<T>JavaScript文件合并。</T>
//============================================================
public class FJsFileMerger
{
   // 字符编码
   private static String CHARSET = "UTF-8";

   //============================================================
   // <T>启动处理。</T>
   //
   // @param params 参数集合
   //============================================================
   public static void main(String[] params){
      try{
         int count = params.length;
         String target = params[count - 1];
         FStringFile file = new FStringFile(target, CHARSET, false);
         for(int n = 0; n < count - 1; n++){
            FStringFile item = new FStringFile(params[n], CHARSET);
            file.append(item);
         }
         file.store(CHARSET);
         System.out.println("Merge javascript files. (file_name=" + target + ")");
      }catch(Exception e){
         RLogger.find(FJsFileMerger.class).error(null, "main", e);
      }
   }
}

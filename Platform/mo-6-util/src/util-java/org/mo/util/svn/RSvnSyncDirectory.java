package org.mo.util.svn;

import org.mo.com.io.EFilterMode;
import org.mo.com.io.FDirectorySynchronizer;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.RApplication;

//============================================================
// <T>版本管理同步目录。</T>
//============================================================
public class RSvnSyncDirectory
{
   // 日志输出接口
   private final static ILogger _logger = RLogger.find(RSyncSvnMain.class);

   //============================================================
   // <T>启动处理。</T>
   //
   // @param args 参数集合
   //============================================================
   public static void main(String[] args){
      // 关联参数
      RApplication.linkArgements(args);
      // 获得设置
      String sourceDirectory = RApplication.findArgement("-source", "E:/ZQ-TZ-Client");
      String targetDirectory = RApplication.findArgement("-target", "D:/ZW-TZ-Client.BR");
      boolean deep = RApplication.argements().contains("-deep");
      // 复制目录
      try{
         FDirectorySynchronizer synchronizer = new FDirectorySynchronizer();
         synchronizer.setSourceDirectory(sourceDirectory);
         synchronizer.setTargetDirectory(targetDirectory);
         synchronizer.setDeep(deep);
         synchronizer.excludes().register(EFilterMode.Begin, ".svn");
         synchronizer.process();
      }catch(Exception e){
         _logger.error(null, "main", e);
      }
   }
}

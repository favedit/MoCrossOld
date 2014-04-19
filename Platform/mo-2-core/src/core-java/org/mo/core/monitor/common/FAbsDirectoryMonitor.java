package org.mo.core.monitor.common;

import java.io.File;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>文件路径监视器基类。</T>
// <P>1. 监视指定目录内的所有文件变动，进行相关处理。</P>
// <P>2. 监视指定目录下的所有文件的变动，新增和删除的操作。</P>
//============================================================
public abstract class FAbsDirectoryMonitor
      extends FFileListMonitor
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FAbsDirectoryMonitor.class);

   //============================================================
   // <T>构造文件路径监视器基类。</T>
   //============================================================
   public FAbsDirectoryMonitor(){
   }

   //============================================================
   // <T>构造文件路径监视器基类。</T>
   //
   // @param directory 目录
   //============================================================
   public FAbsDirectoryMonitor(String directory){
      listDirectory(new File(directory));
   }

   //============================================================
   // <T>检查指定目录下所有的文件。</T>
   //
   // @param directory 目录
   //============================================================
   protected void checkDirectory(File directory){
      if(directory.isDirectory()){
         for(File file : directory.listFiles()){
            if(file.isDirectory()){
               listDirectory(file);
            }else{
               // 检查文件
               FFileInfo info = _files.get(file.getAbsolutePath());
               if(null == info){
                  info = new FFileInfo(file);
                  if(onFilter(info)){
                     // 新增的文件
                     try{
                        doInitializeFile(info);
                     }catch(Exception e){
                        _logger.error(e, "File initialize event error (file={1})", info.fileName());
                     }
                     info.refresh();
                     _files.push(info);
                  }
               }else if(onFilter(info)){
                  if(info.isModified()){
                     // 存在的文件检查
                     try{
                        doFileChange(info);
                     }catch(Exception e){
                        _logger.error(this, "checkDirectory", e);
                     }
                     info.refresh();
                  }
               }
            }
         }
      }
   }

   //============================================================
   // <T>查找指定目录下所有的文件。</T>
   //
   // @param directory 目录
   //============================================================
   protected void listDirectory(File directory){
      if(directory.isDirectory()){
         for(File file : directory.listFiles()){
            if(file.isDirectory()){
               listDirectory(file);
            }else{
               // 检查文件
               FFileInfo info = _files.find(file.getAbsolutePath());
               if(null == info){
                  info = new FFileInfo(file);
                  if(onFilter(info)){
                     // 新增的文件
                     try{
                        doInitializeFile(info);
                     }catch(Exception e){
                        _logger.error(e, "File initialize event error (file={1})", info.fileName());
                     }
                     info.refresh();
                     _files.push(info);
                  }
               }
            }
         }
      }
   }
}

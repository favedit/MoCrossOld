package org.mo.com.io;

import java.io.File;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RByte;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>目录同步器。</T>
//============================================================
public class FDirectorySynchronizer
{
   // 日志输出接口
   private final static ILogger _logger = RLogger.find(FDirectorySynchronizer.class);

   // 来源路径
   protected String _sourceDirectory;

   // 目标路径
   protected String _targetDirectory;

   // 是否递归
   protected boolean _deep;

   // 包含集合
   protected FFilter _includes = new FFilter();

   // 排除集合
   protected FFilter _excludes = new FFilter();

   //============================================================
   // <T>构造目录同步器。</T>
   //============================================================
   public FDirectorySynchronizer(){
   }

   //============================================================
   // <T>构造目录同步器。</T>
   //
   // @param sourceDirectory 来源目录
   // @param targetDirectory 目标目录
   //============================================================
   public FDirectorySynchronizer(String sourceDirectory,
                                 String targetDirectory){
      _sourceDirectory = sourceDirectory;
      _targetDirectory = targetDirectory;
   }

   //============================================================
   // <T>获得来源路径。</T>
   //
   // @return 来源路径
   //============================================================
   public String sourceDirectory(){
      return _sourceDirectory;
   }

   //============================================================
   // <T>设置来源路径。</T>
   //
   // @param sourceDirectory 来源路径
   //============================================================
   public void setSourceDirectory(String sourceDirectory){
      _sourceDirectory = sourceDirectory;
   }

   //============================================================
   // <T>获得目标路径。</T>
   //
   // @return 目标路径
   //============================================================
   public String targetDirectory(){
      return _targetDirectory;
   }

   //============================================================
   // <T>设置目标路径。</T>
   //
   // @param targetDirectory 目标路径
   //============================================================
   public void setTargetDirectory(String targetDirectory){
      _targetDirectory = targetDirectory;
   }

   //============================================================
   // <T>判断是否递归。</T>
   //
   // @return 是否递归
   //============================================================
   public boolean isDeep(){
      return _deep;
   }

   //============================================================
   // <T>是否是否递归。</T>
   //
   // @param deep 是否递归
   //============================================================
   public void setDeep(boolean deep){
      _deep = deep;
   }

   //============================================================
   // <T>获得包含集合。</T>
   //
   // @return 包含集合
   //============================================================
   public FFilter includes(){
      return _includes;
   }

   //============================================================
   // <T>获得排除集合。</T>
   //
   // @return 排除集合
   //============================================================
   public FFilter excludes(){
      return _excludes;
   }

   //============================================================
   // <T>同步两个目录。</T>
   //
   // @param directory 基础目录
   // @param sourceDirectory 来源目录
   // @param targetDirectory 目标目录
   //============================================================
   public void syncDirectory(File directory,
                             File sourceDirectory,
                             File targetDirectory){
      int rootPathLength = directory.getAbsolutePath().length();
      // 处理目录文件
      for(File sourceFile : sourceDirectory.listFiles()){
         String sourceName = sourceFile.getName();
         boolean invalid = false;
         // 忽略包含外的文件
         if(!_includes.isEmpty()){
            invalid = true;
            for(FFilterItem item : _includes){
               if(item.filter(sourceName)){
                  invalid = false;
                  break;
               }
            }
         }
         // 忽略排除文件
         if(!_excludes.isEmpty()){
            for(FFilterItem item : _excludes){
               if(item.filter(sourceName)){
                  invalid = true;
                  break;
               }
            }
         }
         if(invalid){
            continue;
         }
         //............................................................
         // 处理目录的情况
         if(sourceFile.isDirectory()){
            if(_deep){
               syncDirectory(directory, sourceFile, targetDirectory);
            }
            continue;
         }
         //............................................................
         // 比较文件内容
         String sourcePath = sourceFile.getAbsolutePath();
         String fileName = targetDirectory.getAbsolutePath() + sourcePath.substring(rootPathLength);
         if(RFile.exists(fileName)){
            try(FByteFile sourceBytes = new FByteFile(sourcePath); FByteFile targetBytes = new FByteFile(fileName)){
               if(RByte.equals(sourceBytes.memory(), sourceBytes.length(), targetBytes.memory(), targetBytes.length())){
                  continue;
               }
            }
            // 复制文件
            _logger.debug(this, "syncDirectory", "Copy file. (file_name={1})", fileName);
         }else{
            // 新建文件
            _logger.debug(this, "syncDirectory", "Create file. (file_name={1})", fileName);
         }
         RFile.copyFile(sourcePath, fileName);
      }
   }

   //============================================================
   // <T>逻辑处理。</T>
   //============================================================
   public void process(){
      _logger.debug(this, "process", "Synchronizer directory. (source={1}, target={2}, deep={3})", _sourceDirectory, _targetDirectory, _deep);
      File sourceDirectory = new File(_sourceDirectory);
      if(!sourceDirectory.isDirectory()){
         throw new FFatalError("Source directory is not exists. (source_directory={1})", _sourceDirectory);
      }
      File targetDirectory = new File(_targetDirectory);
      syncDirectory(sourceDirectory, sourceDirectory, targetDirectory);
   }
}

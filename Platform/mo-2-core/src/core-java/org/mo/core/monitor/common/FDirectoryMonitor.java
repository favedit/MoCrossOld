/*
 * @(#)FDirectoryMonitor.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.core.monitor.common;

import java.io.File;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;

/**
 * <p>文件路径监视器基类</p>
 * <p>1. 监视指定目录内的所有文件变动，进行相关处理</p>
 * <p>2. 监视指定目录下的所有文件的变动，新增和删除的操作</p>
 *
 * @author ALEX
 */
public abstract class FDirectoryMonitor
      extends FAbsDirectoryMonitor
{
   // 文件根路径
   protected File _directory;

   /**
    * <p>创建文件路径监视器的实例</p>
    *
    * @param sPath 文件根路径
    */
   public FDirectoryMonitor(String directory){
      setDirectory(directory);
   }

   /**
    * <p>获得文件根路径</p>
    *
    * @return 文件根路径
    */
   public File directory(){
      return _directory;
   }

   /**
    * <p>执行监视器的逻辑</p>
    *
    */
   @Override
   public void execute(){
      checkDirectory(_directory);
   }

   /**
    * <p>获得文件的标识名称</p>
    * <p>从文件名中截取开始的路径部分， 以小写表示，将所有的分隔符变为“.”</p>
    *
    * @param filename 文件名称
    * @return 文件标识
    */
   public String makeFileCode(String filename,
                              String endWidth){
      String code = null;
      String path = _directory.getAbsolutePath();
      if(filename.startsWith(path)){
         int start = path.length();
         int end = filename.length() - endWidth.length();
         code = filename.substring(start, end).toLowerCase();
         code = code.replace('/', '.');
         code = code.replace('\\', '.');
      }
      return code;
   }

   /**
    * <p>根据文件标识计算文件名称</p>
    *
    * @param fileCode 文件标识
    * @param endWidth 文件结束名称（扩展名）
    * @return 文件名称
    */
   public String makeFileName(String fileCode,
                              String endWidth){
      String sFileName = null;
      if(!RString.isEmpty(fileCode)){
         fileCode = fileCode.replace('.', File.separatorChar);
         sFileName = _directory + fileCode + endWidth;
      }
      return sFileName;
   }

   /**
    * <p>设置文件根路径</p>
    *
    * @param directory 文件根路径
    */
   public void setDirectory(String directory){
      if(!RFile.isDirectory(directory)){
         throw new FFatalError("Directory is not exist.[{0}]", directory);
      }
      _directory = new File(directory);
      listDirectory(_directory);
   }
}

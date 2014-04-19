/*
 * @(#)FFileMonitor.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.core.monitor.common;

import java.io.File;
import org.mo.com.lang.FFatalError;

/**
 * <p>文件监视器基类</p>
 * <p>1. 监视指定文件和目录内的文件变动，进行相关处理</p>
 * 
 * @author ALEX
 */
public abstract class FFileMonitor
      extends FAbstractMonitor
{
   // 文件根路径
   private String _filename = null;

   // 文件最后修改时间
   private long _lastModified = 0;

   /**
    * <p>创建文件监视器的实例</p>
    *
    * @param sPath 监视文件名
    */
   public FFileMonitor(String filename){
      setFileName(filename);
   }

   /**
    * <p>执行监视器的逻辑</p>
    *
    */
   @Override
   public void execute(){
      File file = new File(_filename);
      if(file.isFile()){
         long lastModified = file.lastModified();
         if(_lastModified != lastModified){
            onFileChanged();
            _lastModified = lastModified;
         }
      }else{
         throw new FFatalError("Monitor file is lost [{0}]", _filename);
      }
   }

   /**
    * <p>获得文件名称</p>
    *
    * @return 文件根路径
    */
   public String fileName(){
      return _filename;
   }

   /**
    * <p>初始化监视器</p>
    *
    */
   @Override
   public void initialize(){
      super.initialize();
      onInitialize();
      _lastModified = (new File(_filename)).lastModified();
   };

   /**
    * <p>响应发生变动的文档</p>
    *
    */
   public void onFileChanged(){
   };

   /**
    * <p>响应初始化文档</p>
    *
    */
   public void onInitialize(){
   }

   /**
    * <p>设置文件名称</p>
    *
    * @param filename 文件名称
    */
   public void setFileName(String filename){
      _filename = (new File(filename)).getAbsolutePath();
   }
}

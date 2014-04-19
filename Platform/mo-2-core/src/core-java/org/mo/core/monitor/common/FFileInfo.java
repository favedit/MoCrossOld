/*
 * @(#)FFileInfo.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.core.monitor.common;

import java.io.File;

/**
 * <p>文件信息</p>
 * 
 * @author maocy
 */
public class FFileInfo
{
   // 文件名称
   private File _file;

   // 文件名称
   private String _filename;

   // 文件最后修改时间
   private long _lastModified = 0;

   private long _storeDate;

   public FFileInfo(File file){
      setFile(file);
   }

   public FFileInfo(String fileName){
      setFileName(fileName);
   }

   public boolean exists(){
      return _file.isFile();
   }

   /**
    * <p>获得文件名称</p>
    *
    * @return 文件名称
    */
   public File file(){
      return _file;
   }

   /**
    * <p>获得文件名称</p>
    *
    * @return 文件名称
    */
   public String fileName(){
      return _filename;
   }

   public boolean isModified(){
      return (_lastModified != _file.lastModified());
   }

   /**
    * <p>获得最后修改时间</p>
    *
    * @return 最后修改时间
    */
   public long lastModified(){
      return _lastModified;
   }

   public void refresh(){
      _lastModified = _file.lastModified();
   }

   /**
    * <p>设置文件名称</p>
    *
    * @return 文件名称
    */
   public void setFile(File file){
      _file = file;
      _filename = file.getAbsolutePath();
      _lastModified = file.lastModified();
   }

   public void setFileName(String fileName){
      setFile(new File(fileName));
   }

   /**
    * <p>获得最后修改时间</p>
    *
    * @return 最后修改时间
    */
   public void setLastModified(long lastModified){
      _lastModified = lastModified;
   }

   public void store(){
      _storeDate = System.currentTimeMillis();
   }

   public void stored(){
      _storeDate = 0;
      refresh();
   }

   public boolean storeTest(int interval){
      if(_storeDate > 0){
         return (System.currentTimeMillis() - _storeDate) > interval;
      }
      return false;
   }
}

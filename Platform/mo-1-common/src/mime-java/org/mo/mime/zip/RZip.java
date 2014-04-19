/*
 * @(#)RZip.java
 *
 * Copyright 2009 microbject, All Rights Reserved.
 *
 */
package org.mo.mime.zip;

/**
 * <T>ZIP压缩文件工具类。</T>
 * 
 * @history 090601 MAOCY 创建
 */
public class RZip
{
   public static String EXTENSION = "zip";

   /**
    * <T>打开一个压缩文件，返回读取用的流对象。</T>
    * 
    * @param fileName 文件路径
    * @return 读取流对象
    */
   public static IZipInput openFile(String fileName){
      return new FZipFileInput(fileName);
   }

   /**
    * <T>保存一个压缩文件，返回输出用的流对象。</T>
    * 
    * @param fileName 文件路径
    * @return 输出流对象
    */
   public static IZipOutput writeFile(String fileName){
      return new FZipFileOutput(fileName);
   }
}

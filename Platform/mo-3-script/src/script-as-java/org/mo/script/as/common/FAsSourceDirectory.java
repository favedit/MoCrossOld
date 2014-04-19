package org.mo.script.as.common;

import org.mo.com.lang.FObject;

//============================================================
// <T>AS代码目录。</T>
//============================================================
public class FAsSourceDirectory
      extends FObject
{
   // 来源文件目录
   protected String _sourceDirectory;

   // 来源文件名称
   protected String _sourceFileName;

   // 目标文件目录
   protected String _targetDirectory;

   // 目标文件名称
   protected String _targetFileName;

   //============================================================
   // <T>构造AS代码目录。</T>
   //============================================================
   public FAsSourceDirectory(){
   }

   //============================================================
   // <T>获得来源文件目录。</T>
   //
   // @return 文件目录
   //============================================================
   public String sourceDirectory(){
      return _sourceDirectory;
   }

   //============================================================
   // <T>设置来源文件目录。</T>
   //
   // @param directory 文件目录
   //============================================================
   public void setSourceDirectory(String directory){
      _sourceDirectory = directory;
   }

   //============================================================
   // <T>获得来源文件名称。</T>
   //
   // @return 文件名称
   //============================================================
   public String sourceFileName(){
      return _sourceFileName;
   }

   //============================================================
   // <T>设置来源文件名称。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void setSourceFileName(String fileName){
      _sourceFileName = fileName;
   }

   //============================================================
   // <T>获得目标文件目录。</T>
   //
   // @return 文件目录
   //============================================================
   public String targetDirectory(){
      return _targetDirectory;
   }

   //============================================================
   // <T>设置目标文件目录。</T>
   //
   // @param directory 文件目录
   //============================================================
   public void setTargetDirectory(String directory){
      _targetDirectory = directory;
   }

   //============================================================
   // <T>获得目标文件名称。</T>
   //
   // @return 文件名称
   //============================================================
   public String targetFileName(){
      return _targetFileName;
   }

   //============================================================
   // <T>设置目标文件名称。</T>
   //
   // @param fileName 目标文件名称
   //============================================================
   public void setTargetFileName(String fileName){
      _targetFileName = fileName;
   }
}

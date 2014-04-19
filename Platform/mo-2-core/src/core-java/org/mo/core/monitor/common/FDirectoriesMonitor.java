package org.mo.core.monitor.common;

import java.io.File;

//============================================================
// <T>文件路径监视器基类。</T>
// <P>1. 监视指定目录内的所有文件变动，进行相关处理。</P>
// <P>2. 监视指定目录下的所有文件的变动，新增和删除的操作。</P>
//============================================================
public abstract class FDirectoriesMonitor
      extends FAbsDirectoryMonitor
{
   // 文件列表
   private final FFileInfos _directories = new FFileInfos();

   /**
    * <p>创建文件路径监视器的实例</p>
    *
    */
   public FDirectoriesMonitor(){
   }

   /**
    * <p>设置文件根路径</p>
    *
    * @param directory 文件根路径
    */
   public void addDirectory(String directory){
      File dir = new File(directory);
      if(!dir.isDirectory()){
         listDirectory(dir);
         _directories.push(new FFileInfo(dir));
      }
   }

   public FFileInfos directories(){
      return _directories;
   }

   /**
    * <p>执行监视器的逻辑</p>
    *
    */
   @Override
   public void execute(){
      int count = _directories.count();
      for(int n = 0; n < count; n++){
         checkDirectory(_directories.value(n).file());
      }
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
      //      if(filename.startsWith(_directory)){
      //         int start = _directory.length();
      //         int end = filename.length() - endWidth.length();
      //         code = filename.substring(start, end).toLowerCase();
      //         code = code.replace('/', '.');
      //         code = code.replace('\\', '.');
      //      }
      return code;
   }

   /**
    * <p>根据文件标识计算文件名称</p>
    *
    * @param sFileCode 文件标识
    * @param sEndWidth 文件结束名称（扩展名）
    * @return 文件名称
    */
   public String makeFileName(String sFileCode,
                              String sEndWidth){
      String sFileName = null;
      //      if(!RString.isEmpty(sFileCode)){
      //         sFileCode = sFileCode.replace('.', File.separatorChar);
      //         sFileName = _directory + sFileCode + sEndWidth;
      //      }
      return sFileName;
   }
}

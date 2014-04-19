package org.mo.com.io;

import java.io.File;
import org.mo.com.lang.FStrings;

//============================================================
// <T>目录工具类。</T>
//============================================================
public class RDirectory
{
   //============================================================
   // <T>列出目录中所有文件。</T>
   //
   // @param directory 目录名称
   // @param directories 目录集合
   // @param files 文件集合
   // @param deep 递归
   // @param startsWith 开始字符串
   // @param endsWith 结束字符串
   //============================================================
   protected static void innerListDirectory(File directory,
                                            FFileInfos infos,
                                            boolean deep,
                                            String startsWith,
                                            String endsWith){
      if(directory.isDirectory()){
         for(File file : directory.listFiles()){
            if(deep && file.isDirectory()){
               innerListDirectory(file, infos, deep, startsWith, endsWith);
            }else{
               // 选择过滤器
               String fileName = file.getName();
               boolean finded = false;
               if((null == startsWith) && (null == endsWith)){
                  finded = true;
               }else if((null != startsWith) && (null == endsWith)){
                  if(fileName.startsWith(startsWith)){
                     finded = true;
                  }
               }else if((null == startsWith) && (null != endsWith)){
                  if(fileName.endsWith(endsWith)){
                     finded = true;
                  }
               }else if((null != startsWith) && (null != endsWith)){
                  if(fileName.startsWith(startsWith) && fileName.startsWith(endsWith)){
                     finded = true;
                  }
               }
               if(finded){
                  // 增加一个文件
                  FFileInfo info = new FFileInfo();
                  info.setFileName(file.getAbsolutePath());
                  infos.push(info);
               }
            }
         }
      }
   }

   //============================================================
   // <T>列出目录中所有文件。</T>
   //
   // @param directory 目录名称
   // @return 文件信息集合
   //============================================================
   public static FFileInfos listFiles(String directory){
      FFileInfos infos = new FFileInfos();
      innerListDirectory(new File(directory), infos, true, null, null);
      return infos;
   }

   //============================================================
   // <T>列出目录中所有文件。</T>
   //
   // @param directory 目录名称
   // @param deep 递归
   // @return 文件信息集合
   //============================================================
   public static FFileInfos listFiles(String directory,
                                      boolean deep){
      FFileInfos infos = new FFileInfos();
      innerListDirectory(new File(directory), infos, deep, null, null);
      return infos;
   }

   //============================================================
   // <T>列出目录中所有文件。</T>
   //
   // @param directory 目录名称
   // @param startsWith 开始字符串
   // @param endsWith 结束字符串
   // @return 文件信息集合
   //============================================================
   public static FFileInfos listFiles(String directory,
                                      String startsWith,
                                      String endsWith){
      FFileInfos infos = new FFileInfos();
      innerListDirectory(new File(directory), infos, true, startsWith, endsWith);
      return infos;
   }

   /**
    * <p>列出指定目录下的所有目录名称列表</p>
    * 
    * @param directory 指定目录
    * @return 目录名称列表
    */
   public static FStrings listDirectory(String directory){
      return listDirectory(directory, false);
   }

   /**
    * <p>列出指定目录下的所有目录名称列表</p>
    * 
    * @param directory 指定目录
    * @return 目录名称列表
    */
   public static FStrings listDirectory(String directory,
                                        boolean fullName){
      FStrings list = new FStrings();
      File dir = new File(directory);
      if(dir.isDirectory()){
         for(File file : dir.listFiles()){
            if(file.isDirectory()){
               if(fullName){
                  list.push(file.getAbsolutePath());
               }else{
                  list.push(file.getName());
               }
            }
         }
      }
      return list;
   }
}

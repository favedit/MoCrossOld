using System.Diagnostics;
using System.IO;
using MO.Common.Lang;

namespace MO.Common.IO
{
   //============================================================
   // <T>目录工具类。</T>
   //============================================================
   public static class RDirectory
   {
      //============================================================
      // <T>判断目录是否存在。</T>
      //============================================================
      public static bool Exists(string directory) {
         return Directory.Exists(directory);
      }

      //============================================================
      // <T>判断指定文件是否存在。</T>
      //
      // @param 文件地址
      // @return 结果
      //============================================================
      public static bool IsFileExists(string directory) {
         int dirIndex = directory.LastIndexOf("\\");
         string dirPath = directory.Substring(0, directory.Length - (directory.Length - dirIndex - 1));
         if (Directory.Exists(dirPath)) {
            string[] filePaths = Directory.GetFiles(dirPath);
            foreach (string path in filePaths) {
               if (directory.Equals(path)) {
                  return true;
               }
            }
         }
         return false;
      }

      //============================================================
      // <T>获得文件夹下文件列表。</T>
      //
      // @param paths 文件列表
      // @param directory 文件夹
      // @param deep 深度
      //============================================================
      private static bool InnerListFiles(FStrings paths, string directory, bool deep, string extension, bool constainsDir, bool constainsFile) {
         if(!Directory.Exists(directory)){
            return false;
         }
         // 搜索子文件夹
         foreach (string dir in Directory.GetDirectories(directory)) {
            if (constainsDir) {
               paths.Push(dir);
            }
            if (deep) {
               InnerListFiles(paths, dir, deep, extension, constainsDir, constainsFile);
            }
         }
         // 搜索文件列表
         if (constainsFile) {
            foreach (string file in Directory.GetFiles(directory)) {
               if (null == extension) {
                  paths.Push(file);
               } else if (file.EndsWith(extension)) {
                  paths.Push(file);
               }
            }
         }
         return true;
      }

      //============================================================
      // <T>列出文件夹下所有文件。</T>
      //
      // @param directory 文件夹
      // @param deep 深度
      //============================================================
      public static FStrings ListFiles(string directory, bool deep = false, string extension = null) {
         FStrings files = new FStrings();
         InnerListFiles(files, directory, deep, extension, false, true);
         files.Sort();
         return files;
      }

      //============================================================
      // <T>列出文件夹下所有子文件夹。</T>
      //
      // @param directory 文件夹
      // @param deep 深度
      //============================================================
      public static FStrings ListDirectories(string directory, bool deep = false) {
         FStrings directories = new FStrings();
         InnerListFiles(directories, directory, deep, null, true, false);
         directories.Sort();
         return directories;
      }

      //============================================================
      // <T>如果目录中文件夹不存在则创建文件夹。</T>
      //
      // @param directory 文件夹目录
      //============================================================
      public static void MakeDirectories(string directory) {
         if (!Directory.Exists(directory)) {
            Directory.CreateDirectory(directory);
         }
      }

      //============================================================
      // <T>如果目录中文件夹不存在则创建文件夹。</T>
      //
      // @param directory 文件夹目录
      //============================================================
      public static void MakeDirectoriesForFile(string fileName) {
         fileName = fileName.Replace('/', '\\');
         int index = fileName.LastIndexOf('\\');
         if (-1 != index) {
            string directory = fileName.Substring(0, index);
            if (!Directory.Exists(directory)) {
               Directory.CreateDirectory(directory);
            }
         }
      }

      //============================================================
      // <T>打开指定路径。</T>
      //============================================================
      public static void ProcessOpen(string path) {
         Process.Start(new ProcessStartInfo(path));
      }

      //============================================================
      // <T>复制来源路径所有文件到目标路径。</T>
      //
      // @param sourcePath 来源路径
      // @param targetPath 目标路径
      // @param includes 包含列表
      // @param excludes 排除列表
      //============================================================
      public static void Copy(string sourcePath, string targetPath, FStrings includes, FStrings excludes) {
      }

      //============================================================
      // <T>清空指定目录内所有文件。</T>
      //
      // @param path 指定目录
      //============================================================
      public static void Clear(string path) {
         if (Directory.Exists(path)) {
            string[] fileNames = Directory.GetFiles(path);
            foreach (string fileName in fileNames) {
               File.Delete(fileName);
            }
         }
      }

      //============================================================
      // <T>删除指定目录。</T>
      //
      // @param path 指定目录
      //============================================================
      public static void Delete(string path) {
         if (Directory.Exists(path)) {
            Directory.Delete(path, true);
         }
      }
   }
}

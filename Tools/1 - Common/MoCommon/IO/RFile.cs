using System.IO;
using MO.Common.Lang;

namespace MO.Common.IO
{
   //============================================================
   // <T>文件工具类。</T>
   //============================================================
   public class RFile
   {
      // 驱动器分割符
      public const string DRIVER_SPLITER = ":";

      // 路径分割符
      public const string SPLITER = @"\";

      // 行分割符
      public const string LINE = "\n";

      //============================================================
      public static bool Exists(string filename) {
         return File.Exists(filename);
      }

      /*//============================================================
      public static string CurrentFileName(string path) {
         if (path != null) {
            if (path.Contains(DRIVER_SPLITER)) {
               return path;
            }
            return MakeFileName(RSystem.Location(), path);
         }
         return null;
      }

      //============================================================
      public static string CurrentRelativePath(string path) {
         if (path != null) {
            string location = RSystem.Location();
            if (path.StartsWith(location)) {
               return path.Substring(location.Length);
            }
            return path;
         }
         return null;
      }*/

      //============================================================
      public static string MakePathName(string path, string name) {
         path = RString.Nvl(path).Replace('/', '\\');
         name = RString.Nvl(name).Replace('/', '\\');
         if (path.EndsWith(@"\")) {
            path = path.Substring(0, path.Length - 1);
         }
         if (!name.StartsWith(@"\")) {
            name = '\\' + name;
         }
         return path + name;
      }

      //============================================================
      public static string MakeFileName(string path, string name) {
         path = RString.Nvl(path).Replace('/', '\\');
         name = RString.Nvl(name).Replace('/', '\\');
         if (path.EndsWith(@"\")) {
            path = path.Substring(0, path.Length - 1);
         }
         if (!name.StartsWith(@"\")) {
            name = '\\' + name;
         }
         return path + name;
      }

      //============================================================
      public static string MakeFileName(string path) {
         path = RString.Nvl(path).Replace('/', '\\');
         if (path.EndsWith(@"\")) {
            path = path.Substring(0, path.Length - 1);
         }
         return path;
      }

      //============================================================
      // <T>获得文件名所在的路径。</T>
      //
      // @param fileName 文件
      // @return 文件路径
      //============================================================
      public static string GetFileName(string fileName) {
         FFileInfo info = new FFileInfo(fileName);
         return info.Name;
      }

      //============================================================
      // <T>获得文件的大小。</T>
      //
      // @param fileName 文件
      // @return 大小
      //============================================================
      public static long GetFileLength(string fileName) {
         FileInfo info = new FileInfo(fileName);
         return info.Length;
      }

      //============================================================
      // <T>获得文件的最后更新时刻。</T>
      //
      // @param fileName 文件
      // @return 时刻
      //============================================================
      public static long GetFileUpdateTick(string fileName) {
         FileInfo info = new FileInfo(fileName);
         return info.LastWriteTime.Ticks;
      }

      //============================================================
      // <T>获得文件名所在的路径。</T>
      //
      // @param fileName 文件
      // @return 文件路径
      //============================================================
      public static string GetDirectory(string fileName) {
         FFileInfo info = new FFileInfo(fileName);
         return info.DirectoryName;
      }

      //============================================================
      // <T>获取路径2相对于路径1的相对路径。</T>
      //
      // <param name="strPath1">路径1</param>
      // <param name="strPath2">路径2</param>
      // <returns>返回路径2相对于路径1的路径</returns>
      // <example>
      // string strPath = GetRelativePath(@"C:\WINDOWS\system32", @"C:\WINDOWS\system\*.*" );
      // strPath == @"..\system\*.*"
      // </example>
      //============================================================
      public static string GetRelativePath(string sourcePath, string targetPath) {
         if (!sourcePath.EndsWith(SPLITER)) {
            sourcePath += SPLITER;
         }
         int index = -1, pos = sourcePath.IndexOf(SPLITER);
         while (pos >= 0) {
            pos++;
            if (string.Compare(sourcePath, 0, targetPath, 0, pos, true) != 0) {
               break;
            }
            index = pos;
            pos = sourcePath.IndexOf(SPLITER, pos);
         }
         if (index >= 0) {
            targetPath = targetPath.Substring(index);
            pos = sourcePath.IndexOf(SPLITER, index);
            while (pos >= 0) {
               targetPath = ".." + SPLITER + targetPath;
               pos = sourcePath.IndexOf(SPLITER, pos + 1);
            }
         }
         return targetPath;
      }

      //============================================================
      // <T>将数据写入制定文件名称的文件内。</T>
      //
      // @param fileName 文件名称
      // @param data 数据
      //============================================================
      public static void WriteAllBytes(string fileName, byte[] data) {
         RDirectory.MakeDirectoriesForFile(fileName);
         File.WriteAllBytes(fileName, data);
      }

      //============================================================
      // <T>复制来源文件所有文件到目标文件。</T>
      //
      // @param source 来源文件
      // @param target 目标文件
      // @param autoDirs 是否自动建立目录
      //============================================================
      public static void Copy(string source, string target, bool autoDirs) {
         if (autoDirs) {
            RDirectory.MakeDirectoriesForFile(target);
         }
         File.Copy(source, target, true);
      }
   }
}

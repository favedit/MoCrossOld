
using MO.Common.Lang;
namespace MO.Content3d.Common
{
   public class RDrUtil
   {
      public const int BLOCK_SIZE = 1024 * 1024;

      //============================================================
      // <T>格式化路径为代码。</T>
      //============================================================
      public static string FormatPathToCode(string path) {
         path = RString.Nvl(path).Trim();
         // 置换分隔符
         path = path.Replace('\\', '.').Replace('/', '.');
         if(path.StartsWith(".")) {
            path = path.Substring(1);
         }
         // 去除前缀
         int find = path.IndexOf('_');
         if(-1 != find) {
            path = path.Substring(find);
         }
         return path;
      }
   }
}

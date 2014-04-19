using System.IO;
using MO.Common.Lang;
using System.Text;

namespace MO.Common.IO
{
   //============================================================
   // <T>字符串文件。</T>
   //============================================================
   public class FStringFile : FString
   {
      //============================================================
      // <T>构造字符串文件。</T>
      //============================================================
      public FStringFile() {
      }

      //============================================================
      // <T>构造字符串文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public FStringFile(string fileName) {
         LoadFile(fileName);
      }

      //============================================================
      // <T>加载文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void LoadFile(string fileName) {
         Append(File.ReadAllText(fileName));
      }

      //============================================================
      // <T>存储文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void SaveFile(string fileName) {
         File.WriteAllText(fileName, ToString(), Encoding.UTF8);
      }
   }
}

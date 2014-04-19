using System.IO;
using System.Text;
using MO.Common.Lang;

namespace MO.Common.IO
{
   //============================================================
   // <T>行集合文件。</T>
   //============================================================
   public class FLineFile : FStrings
   {
      //============================================================
      // <T>构造行集合文件。</T>
      //============================================================
      public FLineFile() {
      }

      //============================================================
      // <T>构造行集合文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public FLineFile(string fileName) {
         LoadFile(fileName);
      }

      //============================================================
      // <T>加载指定文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void LoadFile(string fileName) {
         string[] lines = File.ReadAllLines(fileName);
         Append(lines);
      }

      //============================================================
      // <T>保存为指定文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void SaveFile(string fileName) {
         using (FileStream stream = File.Open(fileName, FileMode.Create, FileAccess.Write)) {
            using (StreamWriter writer = new StreamWriter(stream, Encoding.Default)) {
               foreach (string line in this) {
                  writer.WriteLine(line);
               }
            }
         }
      }
   }
}

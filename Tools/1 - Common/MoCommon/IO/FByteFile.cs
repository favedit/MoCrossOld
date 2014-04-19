using System.IO;

namespace MO.Common.IO
{
   //============================================================
   // <T>字节流文件。</T>
   //============================================================
   public class FByteFile : FByteStream
   {
      //============================================================
      // <T>构造字节文件。</T>
      //============================================================
      public FByteFile() {
      }

      //============================================================
      // <T>构造字节文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public FByteFile(string fileName) {
         LoadFile(fileName);
      }

      //============================================================
      // <T>构造字节文件。</T>
      //
      // @param data 字节数组
      // @param offset 索引位置
      // @param length 字节长度
      //============================================================
      public FByteFile(byte[] data,int offset,int length) {
         WriteBytes(data,offset,length);
      }

      //============================================================
      // <T>加载文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void LoadFile(string fileName) {
         Assign(File.ReadAllBytes(fileName));
      }

      //============================================================
      // <T>加载文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void AppendFile(string fileName) {
         Append(File.ReadAllBytes(fileName));
      }

      //============================================================
      // <T>存储文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void SaveFile(string fileName) {
         RDirectory.MakeDirectoriesForFile(fileName);
         File.WriteAllBytes(fileName, ToArray());
      }
   }
}

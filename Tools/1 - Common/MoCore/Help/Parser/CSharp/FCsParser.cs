using System;
using System.Linq;
using System.IO;
using System.Text;
using MO.Core.Help.Common;
using MO.Common.Collection;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Core.Help.Parser.CSharp
{
   //============================================================
   // <T>属于CS文件的解析，提取，生成XML的一系列操作。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FCsParser : FCrHelpParser
   {
      //命名空间对象
      protected FCsSpace _space;

      //所需要查找的文件的后缀名
      protected const string _csFileType = "*.cs";
      //分行符
      protected const string _enter = "\r\n";
      //段落注释规范
      protected const string _annotate = "//============================================================";


      //============================================================
      // <T>构造函数</T>
      //============================================================
      public FCsParser() {
      }

      #region 字段对应属性

      #endregion

      //============================================================
      // <T></T>
      // 
      // 
      //============================================================
      public override void ParseDirectory(string directory) {
      }

      //============================================================
      // 
      // 
      // 
      //============================================================

      public void ParseFile(string fileName) {

      }


      //============================================================
      // <T>获取目标目录内所有的目标文件</T>
      // @param Path 文件所在目录
      // @return FileInfo[] 目标文件集合
      //============================================================
      public static FileInfo[] GetAllCsFiles(string Path) {
         if(Path == string.Empty)
            return null;
         DirectoryInfo diPath = new DirectoryInfo(Path);
         return diPath.GetFiles(_csFileType, SearchOption.AllDirectories);
      }

      //============================================================
      // <T>获取文件内的字符串。</T>
      //
      // @param fiParsered 需要读取的文件
      // @return 文件内的代码字符串
      //============================================================
      public string GetFileStr(FileInfo fiParsered) {
         return new StreamReader(fiParsered.FullName, System.Text.Encoding.UTF8, false).ReadToEnd().Trim();
      }

      //============================================================
      // <T>目标文件内的代码字符串以行来划分。</T>
      //
      // @param strFile 需要划分的代码串
      // @return 划分后的字符串集合
      //============================================================
      public FStrings GetLines(string strFile) {
         FLineFile linefile = new FLineFile(strFile);
         return linefile;

      }

      //============================================================
      // <T>目标文件内的代码字符串以行来划分。</T>
      //
      // @param fi 需要划分的代码串
      // @return 划分后的字符串集合
      //============================================================
      public static FStrings GetLines(FFileInfo fi) {
         FStrings lines = new FStrings();
         lines.Append(File.ReadAllLines(fi.DirectoryName + "\\" + fi.Name, System.Text.Encoding.UTF8));
         return lines;
      }


      //============================================================
      // <T>获取类名字符串。</T>
      //
      // @param strLine 等待解析的字符串
      // @return 返回类名
      //============================================================
      public string GetClassStr(string strLine) {
         int end = strLine.Length;
         if(strLine.Contains('{')){
            end=strLine.IndexOf('{');
         }
         int start=strLine.Trim().LastIndexOf(" ");
         return strLine.Substring(start,end).Trim();
      }

      //============================================================
      // <T>判断该字符串行是否为命名空间。</T>
      //
      // @param strLine 需要检测的行
      // @return true为命名空间，false不是命名空间
      //============================================================
      public static bool IsSpace(string strLine) {
         if(strLine.Trim().StartsWith("namespace"))
            return true;
         else
            return false;
      }

      //============================================================
      // <T>解析文件。</T>
      //
      // @parame fileinfo 要解析的文件
      // @param index 解析对象
      //============================================================
      public void ParserFile(FileInfo file, string outputPath,FClassesIndexTable indextable,StreamWriter swPrint,string relative) {
         FStrings lines = GetLines(file.DirectoryName + "\\" + file.Name);
         for(int n = 0; n < lines.Count; n++) {
            //string line = ConvertToUTF8 ( lines[n].ToString() );
            if(IsSpace(lines[n].ToString())) {
               FCsSpace space = new FCsSpace();
               space.FillValue(lines, n, space.GetPairNum(lines, n));
               string path = outputPath + "\\" + space.SpaceName.Trim();
               space.ParserSpace(lines, n,path,indextable,swPrint,file,relative);
            }
         }
      }
   }
}

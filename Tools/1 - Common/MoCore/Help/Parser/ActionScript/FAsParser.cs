using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using MO.Common.Collection;
using MO.Core.Help.Common;
using MO.Common.Lang;
using MO.Common.IO;

namespace MO.Core.Help.Parser.ActionScript
{
   public class FAsParser:FCrHelpNode
   {
      //============================================================
      // <T>获取目标目录内所有的目标文件</T>
      // @param Path 文件所在目录
      // @return FileInfo[] 目标文件集合
      //============================================================
      public static FileInfo[] GetFiles(string dirPath) {
         if(dirPath == string.Empty||dirPath==null)
            return null;
         DirectoryInfo diPath = new DirectoryInfo(dirPath);
         return diPath.GetFiles(_asFileType, SearchOption.AllDirectories);
      }

      //============================================================
      // <T>目标文件内的代码字符串以行来划分。</T>
      // @param strFile 需要划分的代码串
      // @return 划分后的字符串集合
      //============================================================
      public static FStrings GetLines(string strFile) {
         FLineFile linefile = new FLineFile(strFile);
         return linefile;
      }

      //============================================================
      // <T>解析文件。</T>
      // @parame fileinfo 要解析的文件
      // @param index 解析对象
      //============================================================
      public void ParserFile(FileInfo file, string outputPath, FAsClassesIndexTable indextable,StreamWriter swPrint,string relative) {
         FStrings lines = GetLines(file.DirectoryName + "\\" + file.Name);
         _savePath = outputPath;
         for(int n = 0; n < lines.Count; n++) {
            if(FAsPackage.IsPackage(lines, n)) {
               FAsPackage package = new FAsPackage(lines, n,GetPairNum(lines, n));
               string path = outputPath + "\\" + package.PackageString.Trim();
               package.ParserPackage(indextable,path,swPrint,file,relative);
            }
         }
      }

      
      
   }
}

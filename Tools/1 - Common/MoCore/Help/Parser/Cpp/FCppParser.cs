using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Core.Help.Common;
using System.IO;
using MO.Common.Lang;
using MO.Common.IO;
using MO.Common.Collection;
using MO.Common.Content;

namespace MO.Core.Help.Parser.Cpp
{
   //============================================================
   // <T>对CPP和H文件进行解析提取和操作相关信息。</T>
   //
   // @author CJJUN 120329
   //============================================================
   public class FCppParser : FCrHelpNode
   {
      //.h文件的后缀名
      protected const string _dotH = "*.h";

      //cpp文件的后缀名
      protected const string _dotCpp = "*.cpp";

      protected  FileInfo _fileCpp;

      protected  FCppClassesIndexTable _indextable;

      protected string _outPath;

      protected string _relativePath;

      protected StreamWriter _swPrint;

      public FCppParser() { }

      public FCppParser(FileInfo fileCpp, FCppClassesIndexTable indextable,string outPath,string relativePath, StreamWriter swPrint){
         _fileCpp=fileCpp;
         _indextable=indextable;
         _outPath=outPath;
         _relativePath=relativePath;
         _swPrint=swPrint;
      }

      public void ParserInThread() {
         ParserFileCpp(_fileCpp, _indextable, _outPath, _relativePath, _swPrint);
         System.Threading.Thread.Sleep(25);
      }

      //============================================================
      // <T>解析文件夹，并获取里面的所有*.h文件。</T>
      //
      // @param dirPath 需要解析的文件夹路径
      // @return 在需要解析的文件夹里面的所有*.h文件信息
      //============================================================
      public static FileInfo[] ParserDirH(string dirPath) {
         if(dirPath == string.Empty || dirPath == null)
            return null;
         DirectoryInfo diPath = new DirectoryInfo(dirPath);
         return diPath.GetFiles(_dotH, SearchOption.AllDirectories);
      }

      //============================================================
      // <T>解析文件夹，并获取里面的所有*.cpp文件。</T>
      //
      // @param dirPath 需要解析的文件夹路径
      // @return 在需要解析的文件夹里面的所有*.cpp文件信息
      //============================================================
      public static FileInfo[] ParserDirCpp(string dirPath) {
         if(dirPath == string.Empty || dirPath == null)
            return null;
         DirectoryInfo diPath = new DirectoryInfo(dirPath);
         return diPath.GetFiles(_dotCpp, SearchOption.AllDirectories);
      }

      //============================================================
      // <T>解析*.h文件,查找相应的类的信息。</T>
      //
      // @param fileH 要解析的*.h文件
      // @param classStr 需要匹配的类的类名信息
      // @return 返回匹配到的类的结果。若是没有则返回null
      //============================================================
      public FCppClass ParserFileH(string fileH, string classStr) {
         FLineFile lineFileH = new FLineFile(fileH);
         FStrings lines = lineFileH;
         for(int n = 0; n < lines.Count; n++) {
            if(FCppClass.IsClass(lines, n)) {
               string strTemp = lines[n].Trim();
               int end = strTemp.Length;
               if(strTemp.Contains(':')) {
                  end = strTemp.IndexOf(':');
               }
               strTemp = strTemp.Substring(0, end);
               if(strTemp.Contains(classStr)) {
                  int endIndex = GetPairNum(lines, n);
                  FCppClass cppclass = new FCppClass(lines, n, endIndex);
                  for(int i = n; i < endIndex; i++) {
                     if(FCppField.IsField(lines, i)) {
                        FCppField field = new FCppField(lines, i);
                        cppclass.Field.Add(field);
                     }
                  }
                  return cppclass;
               }
            }
         }
         return null;
      }

      //============================================================
      // <T>在字符串组中提取类名。</T>
      //
      // @param strLines 需要提取的字符串组
      // @param 需要提取的行索引
      // @return 返回提取出的额类名
      //============================================================
      public string GetClassInCpp(FStrings strLines, int index) {
         string strTemp = strLines[index].Trim();
         int end = strTemp.IndexOf("::");
         strTemp = strTemp.Substring(0, end).Trim();
         if(strTemp.Contains(' ')) {
            int start = strTemp.LastIndexOf(' ');
            strTemp = strTemp.Substring(start).Trim();
         }
         if(strTemp.Contains("*")) {
            int start = strTemp.IndexOf("*")+1;
            strTemp = strTemp.Substring(start).Trim();
         }
         return strTemp;
      }

      //============================================================
      // <T>生成查找是的MAP节点</T>
      //
      // @param strLines 文件解析出的字符串行数组
      // @param classStr 解析出的类字符串
      // @return 返回生成的节点
      //============================================================
      public FFileNode MakeNode(FStrings strLines, string classStr) {
         FArray<string> includes = GetInclude(strLines);
         return new FFileNode(classStr, includes);
      }

      //============================================================
      // <T>将被选择的文件解析为类对象数组，并解析出代码在编译器外的警告或者错误。</T>
      // 
      // @param indextable 从.h文件中解析出来的类与头文件对应的MAP表
      // @param putPath XML文档的输出路径
      // @param swPrint StringWriter对象用来输出警告或者错误。
      // @param fileCpp 当前解析的文件
      // @param relativePath 被选择解析的文件夹路径
      //============================================================
      public void ParserFileCpp(FileInfo fileCpp, FCppClassesIndexTable indextable,string outPath,string relativePath, StreamWriter swPrint) {
         FStrings strLines = FCppParser.GetLines(fileCpp.FullName);
         string strClass = string.Empty;
         FArray<FCppClass> classArr = new FArray<FCppClass>();
         int start, end = CheckParaAnnotate(strLines, 0, out  start);
         for(int n = 0; n < strLines.Count; n++) {
            if(IsInInterregional(n, start, end)) { continue; }
            if(FCppMethod.IsMethodInCpp(strLines, n)) {
               FCppMethod method = new FCppMethod(strLines, n, GetPairNum(strLines, n));
               string classStr = GetClassInCpp(strLines, n);
               strClass = classStr;
               FFileNode node = MakeNode(strLines, GetClassInCpp(strLines, n));
               string fileHpath = indextable.LookInMap(node);
               if(fileHpath == "") {
                  continue;
               }
               FCppClass cppClass = ParserFileH(fileHpath, classStr);
               int index=ExistNo(classArr, cppClass);
               if(index != -1) {
                  classArr.Get(index).Method.Add(method);
               } else {
                  cppClass.Method.Add(method);
                  classArr.Add(cppClass);
               }
            } 
         }
         ProduceXMLFile(classArr,outPath,swPrint,fileCpp,relativePath);
         if(strLines[strLines.Count - 1].Trim() != "") {
            string errID = "CPP07CL06E" + strLines.Count.ToString() + "L";
            string path = fileCpp.FullName.Substring(relativePath.Length);
            string error = "[错误][" + errID.PadRight(16) + "][" + path + "(" + strLines.Count.ToString().PadRight(5) + ")]";
            swPrint.WriteLine(error);
         }
      }

      //============================================================
      // <T>将一组提取出来的类对象解析成XML文档，并解析出代码在编译器外的警告或者错误。</T>
      // 
      // @param classArr 一组类对象
      // @param putPath XML文档的输出路径
      // @param swPrint StringWriter对象用来输出警告或者错误。
      // @param file 当前解析的文件
      // @param relativePath 被选择解析的文件夹路径
      //============================================================
      public void ProduceXMLFile(FArray<FCppClass> classArr,string outPath,StreamWriter swPrint,FileInfo file,string relativePath) {
         for(int n = 0; n < classArr.Length; n++) {
            if(classArr[n] != null) {
               FXmlDocument doc = new FXmlDocument();
               FXmlNode config = doc.Root;
               FXmlNode node=classArr[n].XMLMaker(swPrint,file,relativePath);
               config.Push(node);
               FXmlNode methods = new FXmlNode("Methods");
               node.Push(methods);
               for(int i = 0; i < classArr[n].Method.Length; i++) {
                  classArr[n].Method[i].ParserMethod(methods, swPrint, file, relativePath);
               } 
               doc.SaveFile(@outPath+"\\"+classArr[n].ClassName+".xml");
            }
         }
      }

      //============================================================
      // <T>将文件转化为以行划分的字符串集合。</T>
      //
      // @param path 文件的绝对路径
      // @return 将文件转化之后的字符串集合
      //============================================================
      public static FStrings GetLines(string path) {
         FLineFile lineFileH = new FLineFile(path);
         return lineFileH;
      }

      //============================================================
      // <T> 取得在FArray<FCppClass>中class的索引号。</T>
      //
      // @param cppClasses 被查找的Array
      // @param cppClass 需要查找的Node
      // @return 若是存在则返回索引号，否则返回-1;
      //============================================================
      public int ExistNo(FArray<FCppClass> cppClasses, FCppClass cppClass) {
         for(int n = 0; n < cppClasses.Length; n++) {
            if(cppClasses[n].ClassName ==cppClass.ClassName) {
               return n;
            }
         }
         return -1;
      }

      //============================================================
      // <T>解析H文件中的模板类。</T>
      //============================================================
      public void ParserTemplate(string filePath) {
         FStrings strLines = FCppParser.GetLines(filePath);
         for(int n=0;n<strLines.Count;n++){
            if(FCppClass.IsTemplateClass(strLines,n)){
               
            }
         }
      }
   }
}

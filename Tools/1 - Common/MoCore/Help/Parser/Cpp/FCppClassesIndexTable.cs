using System.Linq;
using System.IO;
using MO.Common.Lang;
using MO.Common.Collection;
using MO.Core.Help.Common;

namespace MO.Core.Help.Parser.Cpp
{
   //============================================================
   // <T>Map表的节点</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FMapNode
   {
      //头文件名
      protected string _fileH;

      //类名
      protected string _classArr;

      //============================================================
      // <T>头文件名</T>
      //============================================================
      public string FileDotH {
         get { return this._fileH; }
         set { this._fileH = value; }
      }

      //============================================================
      // <T>类名</T>
      //============================================================
      public string ClassStr {
         get { return this._classArr; }
         set { this._classArr = value; }
      }

      //============================================================
      // <T>构造方法</T>
      //============================================================
      public FMapNode() {
         _fileH = string.Empty;
         _classArr = string.Empty;
      }

      //============================================================
      // <T>有参数的构造</T>
      //
      // @param classStr 类名
      // @param dotHname 头文件名
      //============================================================
      public FMapNode(string classStr, string dotHname) {
         this._classArr = classStr;
         this._fileH = dotHname;
      }
   }

   //============================================================
   // <T>从文件中提取的需要在MAP表中查找的节点。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FFileNode
   {
      //类名
      protected string _class;

      //cpp文件中所包含的头文件名
      protected FArray<string> _headHs;

      //============================================================
      // <T>文件中所包含的头文件名</T>
      //============================================================
      public FArray<string> HeadHS {
         get { return this._headHs; }
         set { this._headHs = value; }
      }

      //============================================================
      // <T>文件中所包含的类名</T>
      //============================================================
      public string ClassStr {
         get { return this._class; }
         set { this._class = value; }
      }

      //============================================================
      // <T>构造方法</T>
      //============================================================
      public FFileNode() {
         _headHs = new FArray<string>();
         _class = string.Empty;
      }

      //============================================================
      // <T>构造方法，带参数的</T>
      //
      // @param classstr 类名
      // @param headhs 文件中提取的所包含的头文件名数组
      //============================================================
      public FFileNode(string classstr, FArray<string> headhs) {
         this._headHs = headhs;
         this._class = classstr;
      }
   }

   //============================================================
   // <T>类名与头文件名对应的索引表。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FCppClassesIndexTable : FCrHelpNode
   {
      //类名与文件名对应的节点
      private FMap<string, FArray<string>> _classesMap = new FMap<string, FArray<string>>();

      //============================================================
      // <T>MAP表节点</T>
      //============================================================
      public FMap<string, FArray<string>> ClassesMap {
         get { return _classesMap; }
         set { _classesMap = value; }
      }

      //============================================================
      // <T>生成MAP对象。</T>
      //
      // @param fileParserPath 需要解析的目录的路径
      //============================================================
      public void ProduceMapObject(string fileParserPath) {
         if(fileParserPath == "") { return; }
         FileInfo[] file = FCppParser.ParserDirH(fileParserPath);
         foreach(FileInfo fi in file) {
            ParserSingleFile(fi);
         }
      }

      //============================================================
      // <T>获取类名。</T>
      //
      // @param s 需要获取的字符串
      // @return 提取出的类名
      //============================================================
      public string GetClassString(string s) {
         string strTemp = s.Trim();
         int begin = strTemp.IndexOf("class ") + 6;
         int end = strTemp.Length;
         if(strTemp.Contains('{')) {
            end = strTemp.IndexOf('{');
         }
         if(strTemp.Contains(':')) {
            end = strTemp.IndexOf(':');
         }
         strTemp = strTemp.Substring(begin, end - begin).Trim();
         if(strTemp.Contains(' ')) {
            int index = strTemp.LastIndexOf(' ');
            strTemp = strTemp.Substring(index).Trim();
         }
         return strTemp;
      }

      //============================================================
      // <T>解析单个文件</T>
      //
      // @param 需要解析的单个文件
      //============================================================
      public void ParserSingleFile(FileInfo file) {
         FStrings strLines = FCppParser.GetLines(file.FullName);
         int start, end = CheckParaAnnotate(strLines, 0, out start);
         for(int n = 0; n < strLines.Count; n++) 
         {
            if(IsInInterregional(n, start, end)) { continue; }
            if(FCppClass.IsClass(strLines, n)) {
               string classStr = GetClassString(strLines[n]);
               FMapNode node = new FMapNode(classStr, file.Name);
               AddNode(node, file.FullName);
               n = GetPairNum(strLines, n);
            }
         }
      }

      //============================================================
      // <T>在MAP表中查找对应值。</T>
      //
      // @param filenode 在文件中生成的查找节点。
      // @return 查找到的值
      //============================================================
      public string LookInMap(FFileNode filenode) {
         FArray<string> heads = new FArray<string>();
         heads = filenode.HeadHS;
         string classstr = filenode.ClassStr;
         foreach(string head in heads) {
            if(_classesMap.Contains(head)) {
               if(_classesMap[head].Contains(classstr)) {
                  return _classesMap[head][0].Trim();
               }
            }
         }
         return string.Empty;
      }

      //============================================================
      // <T>向MAP表里面加入节点。</T>
      //
      // @param node 需要加工的节点
      // @param dotHFilePath H文件的路径
      //============================================================
      public void AddNode(FMapNode node, string dotHFilePath) {
         if(_classesMap.Names.Contains(node.FileDotH)) {
            _classesMap[node.FileDotH].Add(node.ClassStr);
         } else {
            FArray<string> arrNode = new FArray<string>();
            arrNode.Add(dotHFilePath);
            arrNode.Add(node.ClassStr);
            _classesMap.Set(node.FileDotH, arrNode);
         }
      }
   }
}


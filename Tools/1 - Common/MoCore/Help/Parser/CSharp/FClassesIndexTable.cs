using System.IO;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.Collection;
using MO.Core.Help.Common;
namespace MO.Core.Help.Parser.CSharp
{
   //============================================================
   // <T>属于CS文件的解析，提取，生成MAP对象的一系列操作。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FMapNode 
   {
      //命名空间名
      protected string _arrayStr;

      //类名
      protected string _classStr;

      //============================================================
      // <T>命名空间名。</T>
      //============================================================
      public string  ArrayStr{
         get{return this._arrayStr;}
         set{this._arrayStr=value;}
      }

      //============================================================
      // <T>类名。</T>
      //============================================================
      public string ClassStr{
         get{return this._classStr;}
         set{this._classStr=value;}
      }

      //============================================================
      // <T>构造方法。</T>
      //============================================================
      public FMapNode() {
         _arrayStr = string.Empty;
         _classStr = string.Empty;
      }

      //============================================================
      // <T>带参数构造方法。</T>
      //
      // @param classStr 类名
      // @param namespaceStrs 命名空间名
      //============================================================
      public FMapNode(string classStr, string namespaceStrs) {
         this._classStr = classStr;
         this._arrayStr = namespaceStrs;
      }
   }

   //============================================================
   // <T>属于CS文件的解析，提取，生成MAP对象的一系列操作。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FFileNode
   { 
      //类名
      protected string _classStr;

      //包含的命名空间包
      protected FArray<string> _usings;

      //============================================================
      // <T>包含的命名空间包。</T>
      //============================================================
      public FArray<string> Usings {
         get { return this._usings; }
         set { this._usings = value; }
      }

      //============================================================
      // <T>类名。</T>
      //============================================================
      public string ClassStr{
         get{return this._classStr;}
         set{this._classStr=value;}
      }

      //============================================================
      // <T>构造方法。</T>
      //============================================================
      public FFileNode() {
         _usings = new FArray<string>();
         _classStr = string.Empty;
      }
      //============================================================
      // <T>带参数的构造方法。</T>
      //
      // @param classStr 类名
      // @param usings 所包含的命名空间
      //============================================================
      public FFileNode(string classStr, FArray<string> usings) {
         this._classStr = classStr;
         this._usings = usings;
      } 
   }

   //============================================================
   // <T>属于CS文件的解析，提取，生成MAP对象的一系列操作。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FClassesIndexTable : FCrHelpNode
   {
      //MAP表节点
      private FMap<string, FArray<string>> _classesMap = new FMap<string, FArray<string>>();

      //============================================================
      // <T>节点。</T>
      //============================================================
      public FMap<string, FArray<string>> ClassesMap {
         get { return _classesMap; }
         set { _classesMap = value; }
      }

      //============================================================
      // <T>产生Map对象。</T>
      //
      // @param fileParserPath 需要解析的目录路径
      //============================================================
      public void ProduceMapObject(string fileParserPath) {
         if(fileParserPath == "") { return; }
         FileInfo[] file = FCsParser.GetAllCsFiles(fileParserPath);
         foreach(FileInfo fi in file){
            ParserSingleFile(fi);
         }
      }

      //============================================================
      // <T>解析单个文件。</T>
      //
      // @param file 要解析的文件
      //============================================================
      public void ParserSingleFile(FileInfo file) {
         FStrings strLines = FCsParser.GetLines(new FFileInfo(file.FullName));
         for(int n = 0; n < strLines.Count; n++) {
            if(FCsParser.IsSpace(strLines[n].ToString())) {
               string space = string.Empty;
               int endindex = FCsSpace.ParserSpace(strLines, n, out space);
               int start,end=CheckParaAnnotate(strLines,n,out start);
               for(int i = n; i < endindex; i++) {
                  if(IsInInterregional(i,start,end)){continue;}
                  if(FCsSpace.IsClass(strLines[i].ToString())) {
                     string classStr = FCsClass.ParserClass(strLines, i);
                     FMapNode node = new FMapNode(classStr, space);
                     AddNode(node);
                     n = i;
                  }
               }
            }
         }
      }

      //============================================================
      // <T>在Map表中查找在哦并返回查找到的值。</T>
      //
      // @param filenode 带查找节点信息
      // @param type 类型
      // @return 返回类型全名
      //============================================================
      public string LookInMap(FFileNode filenode,string type) {
         string fullClassString = string.Empty;
         string key = filenode.ClassStr;
         if(_classesMap.Contains(key)) {
            FArray<string> value = _classesMap[key];
            if(_classesMap.Contains(key) && value.Length == 1) {
               fullClassString = value[0].ToString();
               if(fullClassString != string.Empty) { return fullClassString + "." + type; } else { return type; }
            }
            if(_classesMap.Contains(key) && value.Length > 1) {
               fullClassString = GetCorrectNamespace(filenode.Usings, value);
               if(fullClassString != string.Empty) { return fullClassString + "." + type; } else { return type; }
            }
         }
         return type;
      }

      //============================================================
      // <T>获取正确的命名空间。</T>
      //
      // @param type 类集合
      // @param node 带查找节点
      // @return 正确的命名空间名称
      //============================================================
      public string GetCorrectNamespace(FArray<string> type, FArray<string> node) {
         for(int n = 0; n < type.Length; n++) {
            for(int i = 0; i < node.Length; i++) {
               if(type[n].ToString().Trim() == node[i].ToString().Trim()) { return type[n].ToString().Trim(); }
            }
         }
         return string.Empty;
      }

      //============================================================
      // <T>增加节点。</T>
      //
      // @param 需要增加的节点
      //============================================================
      public void AddNode(FMapNode node) {
         if(_classesMap.Contains(node.ClassStr)) {
            _classesMap[node.ClassStr].Add(node.ArrayStr);
         } else {
            FArray<string> arrNode = new FArray<string>();
            arrNode.Add(node.ArrayStr);
            _classesMap.Set(node.ClassStr, arrNode);
         }
      }
   }
}

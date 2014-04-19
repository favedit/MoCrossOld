using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Collection;
using MO.Common.IO;
using MO.Core.Help.Common;
using System.IO;
using MO.Common.Lang;

namespace MO.Core.Help.Parser.ActionScript
{
   //============================================================
   // <T>Map表的节点的数据结构。</T>
   //
   // @author CJJUN 120331
   //============================================================
   public class FMapNode 
   {
      // 包名
      protected string _arrayStr;

      //类名
      protected string _classStr;

      //============================================================
      // <T>包名</T>
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
      // <T>构造方法。</T>
      // @param classStr 类名
      // @param namespaceStrs 包名
      //============================================================
      public FMapNode(string classStr, string namespaceStrs) {
         this._classStr = classStr;
         this._arrayStr = namespaceStrs;
      }
   }

   //============================================================
   // <T>从文件中查找提取出待查找节点。</T>
   //
   // @author CJJUN 120331
   //============================================================
   public class FFileNode
   { 
      //类名
      protected string _classStr;
      
      //包含的包
      protected FArray<string> _import;

      //============================================================
      // 所包含的包名
      //============================================================
      public FArray<string> Impotrs {
         get { return this._import; }
         set { this._import = value; }
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
      //
      //============================================================
      public FFileNode() {
         _import = new FArray<string>();
         _classStr = string.Empty;
      }
      //============================================================
      // <T>构造方法。</T>
      //
      // @param classStr 类名
      // @using 文件中所包含的包名
      //============================================================
      public FFileNode(string classStr, FArray<string> usings) {
         this._classStr = classStr;
         this._import = usings;
      } 
   }

   //============================================================
   // <T>索引表的生成，查找等操作。</T>
   //
   // @author CJJUN 120331
   //============================================================
   public class FAsClassesIndexTable : FCrHelpNode
   {
      //文件中提取的Map表节点。
      private FMap<string, FArray<string>> _classesMap = new FMap<string, FArray<string>>();

      //============================================================
      // <T>文件中提取的Map表节点。</T>
      //============================================================
      public FMap<string, FArray<string>> ClassesMap {
         get { return _classesMap; }
         set { _classesMap = value; }
      }

      //============================================================
      // <T>生成Map表。</T>
      //
      // @param fileParserPath 需要生成Map表的所有文件所在的目录路径
      //============================================================
      public void ProduceMapObject(string fileParserPath) {
         if(fileParserPath == "") { return; }
         FileInfo[] file = FAsParser.GetFiles(fileParserPath);
         foreach(FileInfo fi in file){
            ParserSingleFile(fi);
         }
      }

      //============================================================
      // <T>解析单个文件</T>
      //
      // @param file 要解析的文件 
      //============================================================
      public void ParserSingleFile(FileInfo file) {
         FStrings strLines = FAsParser.GetLines(file.FullName);
         for(int n = 0; n < strLines.Count; n++) {
            if(FAsPackage.IsPackage(strLines,n) ){
               string package = string.Empty;
               int endindex = FAsPackage.ParserPackage(strLines, n, out package);
               int start,end=CheckParaAnnotate(strLines,n,out start);
               for(int i = n; i < endindex; i++) {
                  if(IsInInterregional(i,start,end)){continue;}
                  if(FAsClass.IsClass(strLines,i)) {
                     string classStr = FAsClass.ParserClass(strLines, i);
                     FMapNode node = new FMapNode(classStr, package);
                     AddNode(node);
                     n = i;
                  }
               }
            }
         }
      }

      //============================================================
      // <T>在Map表中查找类型对应的全名。</T>
      //
      // @param filenode 从文件中提取的待查找节点
      // @param type 类型 
      // @return 查找到的全名
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
               fullClassString = GetCorrectNamespace(filenode.Impotrs, value);
               if(fullClassString != string.Empty) { return fullClassString + "." + type; } else { return type; }
            }
         }
         return type;
      }

      //============================================================
      // <T>获得正确的命名空间</T>
      //
      // @param type 类型列表
      // @param node 待查找节点
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
      // <T>向类索引Map表中加入节点 。</T>
      //
      // @param node 需要加入的节点信息
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

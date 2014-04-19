using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core.Help.Common;
using MO.Common.Content;

namespace MO.Core.Help.Parser.ActionScript
{
   //============================================================
   // <T>有关包的判断，提取，生成，解析等操作。</T>
   //
   // @author CJJUN 120331
   //============================================================
   public class FAsPackage:FCrHelpNode
   {
      //包所在的字符串行
      protected FStrings _strLines = new FStrings();

      //包所在字符串行集合的开始索引
      protected int _beginIndex = -1;

      //包所在字符串行集合的结束索引
      protected int _endIndex = -1;

      //包所包含的其他包
      protected FStrings _importString = new FStrings();

      //包所拥有的类
      protected FAsClass _asClass = new FAsClass();

      //============================================================
      // <T>包所拥有的类。</T>
      //============================================================
      public FAsClass AsClass {
         get {
            return this._asClass;
         }
         set {
            this._asClass = value;
         }
      }

      //============================================================
      // <T>包的名称。</T>
      //============================================================
      public string PackageString {
         get {
            string strTemp = _strLines[_beginIndex].ToString().Trim();
            if(strTemp.Length == 7) { return "DefaulePackage"; }
            if(strTemp.Length > 7) { strTemp=strTemp.Substring(8).Trim();}
            return strTemp;
         }
      }
      //============================================================
      // <T>构造函数</T>
      //============================================================
      public FAsPackage() { }

      //============================================================
      // <T>带参数的构造函数</T>
      //
      // @param strLines 包所在的字符串行集合
      // @apram begin 包的开始索引号
      // @param end 抱得结束索引号
      //============================================================
      public FAsPackage(FStrings strLines,int begin,int end){
         this._strLines=strLines;
         this._beginIndex=begin;
         this._endIndex=end;
      }

      //============================================================
      // <T>判断所在行字符串是否为打包行。</T>
      //
      // @param strLines 正在解析的字符串组
      // @param index 正在解析的字符串行索引
      // @return 返回true，表示此行为打包行，否则不是
      //============================================================
      public static bool IsPackage(FStrings strLines, int index) {
         if(strLines[index].ToString().Trim().StartsWith("package")) { return true; } else { return false; }
      }

      //============================================================
      // <T>解析包。</T>
      //
      // @param indextable 类全名的索引表
      // @param path 解析结果的输出路径
      // @param swPrint 用于打印警告结果的StreamWriter对象
      // @param relative 当前解析文件所在的目录的路径
      //============================================================
      public void ParserPackage(FAsClassesIndexTable indextable, string path, StreamWriter swPrint, FileInfo file, string relative) {
         int start, endIndex = CheckParaAnnotate(_strLines,_beginIndex , out start);
         for(int n = 0; n < _endIndex; n++) {
            if(IsInInterregional(n, start, endIndex)) { continue; }
            if(FAsClass.IsClass(_strLines, n)) {
               int caonima=GetPairNum(_strLines, n);
               FAsClass asClass = new FAsClass(_strLines, n, caonima);
               asClass.ParserClass(indextable,this,path,swPrint,file,relative);
               this.AsClass = AsClass;
            }
         }
      }

      //============================================================
      // <T>解析包。</T>
      //
      // @param strlines 所在代码行集合
      // @param index 当前索引
      // @param out string 包名
      // @return 包的结束行索引
      //============================================================
      public static int ParserPackage(FStrings strLines, int index, out string spaceString) {
         FAsPackage package = new FAsPackage();
         package._strLines = strLines;
         package._beginIndex = index;
         string strTemp = strLines[index].ToString().Trim();
         spaceString = package.PackageString;
         int end = package.GetPairNum(strLines, index);
         return end;
      }

      //============================================================
      // <T>生成节点时，将此对象生成的节点连接到上层节点上</T>
      // @param config 需要连接的父节点。
      // @return 本对象生成的节点。
      //============================================================
      public FXmlNode XMLMaker(FXmlNode config) {
         FXmlNode node = config.CreateNode("NameSpace");
         node.Set("name", this.PackageString);
         return node;
      }

   }
}

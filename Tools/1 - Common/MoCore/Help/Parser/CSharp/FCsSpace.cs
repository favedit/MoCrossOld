using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Core.Help.Common;
using MO.Common.Collection;
using MO.Common.Lang;
using MO.Common.Content;
using System.IO;

namespace MO.Core.Help.Parser.CSharp
{
   //============================================================
   // <T>有关命名空间的信息和操作的类。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FCsSpace : FCrHelpNode
   {
      //所属命名空间的类集合对象
      protected FArray<FCsClass> _class;

      //命名空间名
      protected string _spaceName;

      //所属命名空间的代码
      protected FStrings _spaceStr = new FStrings ( );

      //行索引，开始和结束
      int _startIndex, _endIndex;

      #region 字段对应的属性
      //============================================================
      // <T>命名空间名。</T>
      //============================================================
      public string SpaceName {
         get {
            string strLine = _spaceStr[_startIndex].Trim();
            int end=strLine.Length;
            if(strLine.Contains('{')) {
               end = strLine.IndexOf('{');
               return strLine.Substring(9, end - 9).Trim();
            }
            return strLine.Substring(9).Trim();
         }
      }

      //============================================================
      // <T>所属命名空间的类的集合。</T>
      //============================================================
      public FArray<FCsClass> Class {
         get {
            return this._class;
         }
      }

      #endregion
      //============================================================
      // <T>构造函数</T>
      //============================================================
      public FCsSpace( ) {
         _spaceName = string.Empty;
         _class = new FArray<FCsClass> ( );
      }

      //============================================================
      // <T>构造函数</T>
      //============================================================
      public FCsSpace(FStrings strLine, int beginLine, int endLine) {
         _spaceStr = strLine;
         _startIndex = beginLine;
         _endIndex = endLine;
         _spaceName = string.Empty;
         _class = new FArray<FCsClass> ( );
      }



      //============================================================
      // <T>获取命名空间的名称（字符串）。</T>
      // @parame strline 需要获取的字符串。
      // @return 获取的命名方法名
      //============================================================
      public string GetSpaceName(string strLine) {
         string space= (strLine.Trim ( ).Substring ( 9 )).Trim ( );
         if(space.Contains('{')) {
            int end = space.IndexOf('{');
            space = space.Substring(0, end);
         }
         return space;
      }

      //============================================================
      // <T>判断该字符串行是否为类。</T>
      // @param strLine 需要检测的行
      // @return true为类，false不是类
      //============================================================
      public static bool IsClass(string strLine) {
         if (strLine.Contains ( " class " )&&!strLine.Contains("\"")) {
            return true;
         }
         else {
            return false;
         }
      }

      //============================================================
      // <T>获取命名空间。</T>
      // @parame strline 需要获取的字符串集合。
      // @param index 当前索引
      // @return 获取的命名空间对象
      //============================================================
      public void ParserSpace(FStrings strLine, int index,string path,FClassesIndexTable indextable,StreamWriter swPrint,FileInfo file,string relative) {
         int start, end = CheckParaAnnotate(strLine, index, out start);
         for (index++; index < strLine.Count; index++) {
            if(IsInInterregional(index,start,end)){continue;}
            if (IsClass ( strLine[index] )) {
               int endIndex = GetPairNum ( strLine, index );
               FCsClass aclass = new FCsClass ( );
               aclass.FillValue ( strLine, index, endIndex );
               aclass.ParserClass ( strLine, this , index , path ,indextable , swPrint , file , relative);
               this.Class.Add ( aclass );
            }
         }
      }

      //============================================================
      // <T>获取命名空间和结束行索引。</T>
      // @parame strlines 需要获取的字符串集合。
      // @param index 当前索引
      // @param out string 获取的命名空间对象
      // @return 命名空间的结束行索引
      //============================================================
      public static int ParserSpace(FStrings strLines, int index,out string spaceString) {
         FCsSpace space = new FCsSpace();
         int end = space.GetPairNum(strLines, index);
         string strTemp = strLines[index].ToString().Trim();
         spaceString = space.GetSpaceName(strTemp);
         return end;
      }

      //============================================================
      // <T>向对象传入相应的值。</T>
      // @param strLine 需要传入的字符串行。也就是解析文件所得的各行
      // @beginLine 属于该对象的行起始行索引
      // @endLine 属于该对象的行终止索引
      //============================================================
      public void FillValue(FStrings strLine, int beginLine, int endLine) {
         _spaceStr = strLine;
         _startIndex = beginLine;
         _endIndex = endLine;
      }

      //============================================================
      // <T>生成节点时，将此对象生成的节点连接到上层节点上</T>
      // @param config 需要连接的父节点。
      // @return 本对象生成的节点。
      //============================================================
      public FXmlNode XMLMaker(FXmlNode config) {
         FXmlNode node = config.CreateNode ( "NameSpace" );
         node.Set ( "name", this.SpaceName );
         return node;
      }
   }
}

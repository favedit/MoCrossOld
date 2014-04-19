using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Core.Help.Common;
using MO.Common.Lang;
using MO.Common.Content;
using System.IO;

namespace MO.Core.Help.Parser.ActionScript
{
   //============================================================
   // <T>有关字段的判断，提取，生成，解析等操作。</T>
   //
   // @author CJJUN 120331
   //============================================================
   public class FAsField:FCrHelpNode
   {
      //字段所在的字符串行集合
      protected FStrings _strLines = new FStrings();

      //所在行索引
      protected int _index = -1;

      //字段名
      protected string _fieldName = string.Empty;

      //可访问级别
      protected string _accessLevel = string.Empty;

      // 字段全名
      protected string _fullName = string.Empty;

      //注释信息
      protected string _description = string.Empty;

      //============================================================
      // <T>字段名。</T>
      //============================================================
      public string FieldName {
         get {
            string strTemp = _strLines[_index].Trim();
            int start = strTemp.IndexOf("var") + 4;
            int end = strTemp.IndexOf(':');
            _fieldName = strTemp.Substring(start, end - start).Trim();
            return _fieldName;
         }
      }

      //============================================================
      // <T>字段的全名。</T>
      //============================================================
      public string FullName {
         get {
            _fullName = _strLines[_index].ToString().Trim();
            int end = _fullName.IndexOf(';');
            if(_fullName.Contains('=')) { end = _fullName.IndexOf('='); }
            _fullName = _fullName.Substring(0, end);
            return _fullName;
         }
      }

      //============================================================
      // <T>字段的可访问级别。</T>
      //============================================================
      public string AccessLevel {
         get {
            string strTemp = _strLines[_index].Trim();
            int end = strTemp.IndexOf(' ');
            strTemp = strTemp.Substring(0, end);
            return strTemp.Trim();
         }
      }

      //============================================================
      // <T>字段的类型。</T>
      //============================================================
      public string Type {
         get {
            string strTemp = _strLines[_index].Trim();
            int end = strTemp.IndexOf(';');
            int begin = strTemp.IndexOf(':') + 1;
            string type = strTemp.Substring(begin, end - begin).Trim();
            return type;
         }
      }

      //============================================================
      // <T>字段的注释。</T>
      //============================================================
      public string Description {
         get {
            for(int n = _index - 1; n > 0; n--) {
               if((_strLines[n].Trim() != "") && (_strLines[n].Contains("//"))) {
                  _description = _strLines[n].Trim().Replace("//", "").Trim();
                  return _description;
               }
               if((_strLines[n].Trim() != "") && (!_strLines[n].Contains("//"))) { return string.Empty; }
            }
            return _description;  
         }
      }

      public FAsField() { }

      //============================================================
      // <T>构造方法，有参数的构造。</T>
      //
      // @param strLines 解析的代码行集合
      // @param index 当前行索引
      //============================================================
      public FAsField(FStrings strLine, int index) {
         this._strLines = strLine;
         this._index = index;
      }

      //============================================================
      // <T>构造方法，有参数的构造。</T>
      //

      // @param strLines 解析的代码行集合
      // @param index 当前行索引
      //============================================================
      public static bool IsField(FStrings strLines, int index) {
         //public var size:SIntSize2 = new SIntSize2();
         string strTemp = strLines[index].Trim();
         if(!strTemp.Contains(" var ")) { return false; }
         if(!strTemp.Contains(';')) { return false; }
         if(strTemp.Contains('(') ){if( !strTemp.Contains('=')) { return false; }}
         int indexChar = strLines[index].Trim().IndexOf(" ");
         string accessClassification;
         if(indexChar >= 0) {
            accessClassification = strTemp.Substring(0, indexChar);
            FStrings accessClass = new FStrings() { "public", "protected", "private", "partial", "internal" };
            foreach(string str in accessClass) {
               if(accessClassification == str)
                  return true;
            }
         }
         return false;
      }

      //============================================================
      // <T>构造方法，有参数的构造。</T>
      //
      // @param indextable 类全名的索引表
      // @param nodeParent 字段节点的父节点（类节点）
      // @param swPrint 用于打印警告结果的StreamWriter对象
      // @param relative 当前解析文件所在的目录的路径
      // @param file 当前解析的文件
      //============================================================
      public void Parser(FXmlNode nodeParent, FAsClassesIndexTable indextable, StreamWriter swPrint, FileInfo file, string relative) {
         XMLMaker(nodeParent, indextable,swPrint,file,relative);
      }

      //============================================================
      // <T>构造方法，有参数的构造。</T>
      //
      // @param nodeParent 字段节点的父节点（类节点）
      // @param indextable 类全名的索引表
      // @param swPrint 用于打印警告结果的StreamWriter对象
      // @param relative 当前解析文件所在的目录的路径
      // @param file 当前解析的文件
      //============================================================
      public void XMLMaker(FXmlNode nodeParent, FAsClassesIndexTable indextable,StreamWriter swPrint,FileInfo file,string relative) {
         if(!IsFieldAnnoFine()) {
            PrintAnnoWarning(swPrint, file, relative);
         }
         FXmlNode nodeField = new FXmlNode("Field");
         nodeField.Set("name", this.FieldName);
         nodeField.Set("full_name", this.FullName);
         nodeField.Set("access_level", this.AccessLevel);
         nodeField.Set("type", this.Type);
         FFileNode type = new FFileNode(this.Type, GetUsing(_strLines));
         string fulltype = indextable.LookInMap(type, this.Type);
         if(Type != this.Type) { 
            nodeField.Set("return_type", fulltype); 
         }
         if(Description != "") {
            nodeField.Set("description", Description);
         }
         nodeParent.Push(nodeField);
      }

      //============================================================
      // <T>判断参数的注释是否齐全或者符合规范。</T>
      //
      // @return true 表示符合规范，否则不符合规范。
      //============================================================
      public bool IsFieldAnnoFine() {
         if(Description != "") {
            return true;
         } else {
            return false;
         }
      }

      //============================================================
      // <T>打印不符合规范的字段注释的警告信息。</T>
      //
      // @param swPrint 用于打印警告信息的StreamWriter对象
      // @param file 当前检测的文件
      // @param relative 文件所在的目录的路径
      //============================================================
      public void PrintAnnoWarning(StreamWriter swPrint, FileInfo file, string relative) {
         string line = _index.ToString();
         string errID = "AS03F03A" + line + "L";
         errID = errID.PadRight(16);
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "] " + "可能缺少T注释或者返回值注释！！";
         swPrint.WriteLine(error);
      }
   }
}

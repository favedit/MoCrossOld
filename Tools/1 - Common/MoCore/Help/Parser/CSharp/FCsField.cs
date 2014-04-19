using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Core.Help.Common;
using MO.Common.Lang;
using MO.Common.Content;
using System.IO;

namespace MO.Core.Help.Parser.CSharp
{
   //============================================================
   // <T>属于字段的解析，提取，生成XML的一系列操作。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FCsField : FCrHelpNode
   {
      //所属字符串行集合
      protected FStrings _strLines = new FStrings();

      //索引号
      protected int _index = -1;

      //字段名
      protected string _fieldName = string.Empty;

      //可访问等级
      protected string _accessLevel = string.Empty;

      //全名
      protected string _fullName = string.Empty;

      //描述
      protected string _description = string.Empty;

      //============================================================
      // <T>字段名</T>
      //============================================================
      public string FieldName {
         get {
            _fieldName = _strLines[_index].ToString().Trim();
            int end = _fieldName.IndexOf(';');
            if(_fieldName.Contains('=')) { end = _fieldName.IndexOf('='); }
            _fieldName = _fieldName.Substring(0, end).Trim();
            end = _fieldName.Length;
            int start = _fieldName.LastIndexOf(' ');
            _fieldName = _fieldName.Substring(start, end - start).Trim();
            return _fieldName;
         }
      }

      //============================================================
      // <T>字段全名</T>
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
      // <T>可访问等级</T>
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
      // <T>字段类型</T>
      //============================================================
      public string Type {
         get {
            string strTemp = this.FullName;
            int index = strTemp.LastIndexOf(' ');
            strTemp = strTemp.Substring(index).Trim();
            return strTemp;
         }
      }

      //============================================================
      // <T>字段描述</T>
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

      //============================================================
      // <T>构造方法</T>
      //============================================================
      public FCsField() { }

      //============================================================
      // <T>构造方法，带参数的。</T>
      //
      // @param steLine 字段所在字符串行集合
      // @param index 所在行索引
      //============================================================
      public FCsField(FStrings strLine, int index) {
         this._strLines = strLine;
         this._index = index;
      }

      //============================================================
      // <T>解析。</T>
      //
      // @parame strline 需要解析的字符串集合。
      // @param index 当前索引行的行号
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路径
      //============================================================
      public void Parser(FXmlNode nodeParent, FClassesIndexTable indextable, StreamWriter swPrint, FileInfo file, string relative) {
         XMLMaker(nodeParent, indextable,swPrint,file,relative);
      }

      //============================================================
      // <T>在字段节点的子节点中连接上方法的返回值节点
      //
      // @param nodeParent 返回值节点的父节点，即隶属于的方法节点
      // @param indextable 类型全名的索引表
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路径
      //============================================================
      public void XMLMaker(FXmlNode nodeParent, FClassesIndexTable indextable, StreamWriter swPrint, FileInfo file, string relative) {
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
         string errID = "CS03F03A" + line + "L";
         errID = errID.PadRight(16);
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "] " + "可能缺少T注释或者返回值注释！！";
         swPrint.WriteLine(error);
      }
   }
}

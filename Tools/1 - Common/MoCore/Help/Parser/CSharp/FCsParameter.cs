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
   // <T>属于参数的解析，提取，生成XML的一系列操作。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FCsParameter : FCrHelpNode
   {
      //参数字符串
      protected string _parameterStr;

      //参数类型
      protected string _parameterType;

      //参数类型的全名
      protected string _fulltype = string.Empty;

      //参数名
      protected string _parameterName;

      //参数所在的字符床集合
      protected FStrings _strLine;

      //参数的注释
      protected FStrings _annotateStr = new FStrings();

      //参数所在的行索引
      protected int _index;



      //============================================================
      // <T>构造方法。</T>
      //============================================================
      public FCsParameter() {
         _parameterName = string.Empty;
         _parameterType = string.Empty;
      }

      //============================================================
      // <T>构造方法，带参数的构造</T>
      // @param strLine 所属于参数对象的的字符串组 
      // @param annotate 所属于参数对象的注释字符串组
      // @param index 字符串组中参数所在行
      //============================================================
      public FCsParameter(FStrings strLine, FStrings annotate, int index) {
         _parameterName = string.Empty;
         _parameterType = string.Empty;
         _strLine = strLine;
         _annotateStr = annotate;
         _index = index;
      }

      #region 字段对应的属性
      //============================================================
      // <T>参数的类型</T>
      //============================================================
      public string ParameterType {
         get {
            return _parameterType;
         }
      }

      //============================================================
      // <T>参数名</T>
      //============================================================
      public string ParameterName {
         get {
            return _parameterName;
         }
      }

      //============================================================
      // <T>参数全名</T>
      //============================================================
      public string FullName {
         get {
            return _parameterType + " " + _parameterName;
         }
      }

      //============================================================
      // <T>参数注释</T>
      //============================================================
      public string Annotate {
         get {
            if(!_parameterName.Equals(string.Empty)) {
               foreach(string str in _annotateStr) {
                  if(str.Contains(_parameterName)) {
                     int start = str.IndexOf(_parameterName) + _parameterName.Length;
                     string anno = str.Substring(start);
                     return anno;
                  }
               }
               return string.Empty;
            } else {
               return string.Empty;
            }
         }
      }

      
  
      #endregion

      //============================================================
      // <T>向对象传入相应的值。</T>
      // @param strLine 需要传入的字符串行。也就是解析文件所得的各行
      // @param annotate 属于该对象的注释行段
      // @endLine 属于该对象的行终止索引
      //============================================================
      public void FillValue(FStrings strLine, FStrings annotate,int index) {
         _strLine = strLine;
         _annotateStr = annotate;
         _index = index;
      }

      //============================================================
      // <T>将属于参数的字符串对象传给参数对象后，解析参数。</T>
      //
      // @param param 属于参数的字符串。
      // @param stranno属于参数的注释内容
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路径
      //============================================================
      public void ParserParam(string param, FStrings stranno,FClassesIndexTable indextable) {
         param = param.Trim();
         //int index=param.LastIndexOf(' ');
         //char[] c = param.ToArray<char>();
         string[] str = param.Trim().Split(new char[] { ' ' });
         if(str.Length > 1) {
            string paramtype = string.Empty, paramname = string.Empty;
            for(int n = 0; n < str.Length-1; n++) {
               paramtype += str[n].ToString() + " ";
            }
            paramname = str[str.Length - 1].ToString();
            this._parameterType = paramtype.Trim();
            FFileNode type=new FFileNode(_parameterType,GetUsing(_strLine));
            this._fulltype = indextable.LookInMap(type,this._parameterType);
            this._parameterName = paramname.Trim();
         }
      }

      //============================================================
      // <T>生成节点时，将此对象生成的节点连接到上层节点上</T>
      //
      // @param node 需要连接的父节点。
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路径
      // @return 本对象生成的节点。
      //============================================================
      public void XMLMaker(FXmlNode node, StreamWriter swPrint, FileInfo file, string relative) {
         if(!IsParaAnnoFine()) {
            PrintAnnoWarning(swPrint, file, relative);
         }
         FXmlNode param = new FXmlNode("Parameter");
         if(param.Name.Trim() != "") {
            param.Set("name", this.ParameterName.Trim());
         }
         if(this.ParameterType.Trim() != "") {
            param.Set("type", this.ParameterType.Trim());
            if(this.ParameterType != this._fulltype) { param.Set("full_type", this._fulltype); }
         }
         if(this.Annotate != "") {
            param.Set("description", this.Annotate);
         }
         node.Push(param);   
      }

      //============================================================
      // <T>判断参数的注释是否齐全或者符合规范。</T>
      //
      // @return true 表示符合规范，否则不符合规范。
      //============================================================
      public bool IsParaAnnoFine() {
         if(Annotate != "") {
            return true;
         } else {
            return false;
         }
      }

      //============================================================
      // <T>打印不符合规范的参数注释的警告信息。</T>
      //
      // @param swPrint 用于打印警告信息的StreamWriter对象
      // @param file 当前检测的文件
      // @param relative 文件所在的目录的路径
      //============================================================
      public void PrintAnnoWarning(StreamWriter swPrint, FileInfo file, string relative) {
         string line = _index.ToString();
         string errID = "CS06PA03A" + line + "L";
         errID = errID.PadRight(16);
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "]";
         swPrint.WriteLine(error);
      }
   }
}

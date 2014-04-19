using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Lang;
using MO.Core.Help.Common;
using MO.Common.Content;
using System.IO;

namespace MO.Core.Help.Parser.ActionScript
{
   //============================================================
   // <T>有关参数的判断，提取，生成，解析等操作。</T>
   //
   // @author CJJUN 120331
   //============================================================
   public class FAsParameter:FCrHelpNode
   {
      //方法字符串行内属于参数的部分
      protected string _parameterStr;

      // 参数的类型
      protected string _parameterType;

      // 参数的类型全名
      protected string _fulltype = string.Empty;

      // 参数名
      protected string _parameterName;

      // 属于参数的注释
      protected FStrings _annotateStr = new FStrings();

      // 参数所在的字符串行集合
      protected FStrings _strLines = new FStrings();

      // 所在字符串集合大行索引
      protected int _index = -1;


      //============================================================
      // <T>构造方法。</T>
      //============================================================
      public FAsParameter() {
         _parameterName = string.Empty;
         _parameterType = string.Empty;
      }

      //============================================================
      // <T>构造方法，带参数的构造</T>
      // @param strLine 所属于参数对象的的字符串组 
      // @param annotate 所属于参数对象的注释字符串组
      // @param index 字符串组中参数所在行
      //============================================================
      public FAsParameter(string strParam, FStrings strLines, int index) {
         _parameterName = string.Empty;
         _parameterType = string.Empty;
         _index = index;
         _parameterStr = strParam;
         _strLines = strLines;
      }

      #region 字段对应的属性
      //============================================================
      // <T>参数的类型。</T>
      //============================================================
      public string ParameterType {
         get {
            return _parameterType;
         }
      }
      //============================================================
      // <T>参数名。</T>
      //============================================================
      public string ParameterName {
         get {
            return _parameterName;
         }
      }

      //============================================================
      // <T>参数的全名。</T>
      //============================================================
      public string FullName {
         get {
            return  _parameterName+":"+_parameterType;
         }
      }

      //============================================================
      // <T>参数对象的注释信息。</T>
      //============================================================
      public string Annotate {
         get {
            _annotateStr = GetAnnotate(_strLines, _index);
            if(!_parameterName.Equals(string.Empty)) {
               foreach(string str in _annotateStr) {
                  if(str.Contains(_parameterName)) {
                     string s = str.Trim().Substring(7);
                     int start = s.Trim().IndexOf(_parameterName) + _parameterName.Length;
                     string anno = s.Trim().Substring(start);
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
      // <T>构造方法，有参数的构造。</T>
      //
      // @param indextable 类全名的索引表
      // @param nodeMethod 参数节点所属的方法节点
      // @param swPrint 用于打印警告结果的StreamWriter对象
      // @param relative 当前解析文件所在的目录的路径
      // @param file 当前解析的文件
      //============================================================
      public void ParserParameter(FAsClassesIndexTable indextable, FXmlNode nodeMethod, StreamWriter swPrint, FileInfo file, string relative) {
         string[] pa=_parameterStr.Split(':');
         if(pa.Length == 2 && pa[0] != "" && pa[1] != "") {
            this._parameterName = pa[0].Trim();
            this._parameterType = pa[1].Trim();
            FFileNode type = new FFileNode(_parameterType, GetImport(_strLines));
            this._fulltype = indextable.LookInMap(type, this._parameterType);
            XMLMaker(nodeMethod,swPrint,file,relative);
         }
      }

      //============================================================
      // <T>构造方法，有参数的构造。</T>
      //
      // @param nodeMethod 参数节点所属的方法节点
      // @param path 解析结果的输出路径
      // @param swPrint 用于打印警告结果的StreamWriter对象
      // @param relative 当前解析文件所在的目录的路径
      //============================================================
      public void XMLMaker(FXmlNode nodeMethod, StreamWriter swPrint, FileInfo file, string relative) {
         if(!IsParaAnnoFine()) {
            PrintAnnoWarning(swPrint, file, relative);
         }
         FXmlNode nodeParam = new FXmlNode("Parameter");
         nodeParam.Set("name", ParameterName);
         nodeParam.Set("type", ParameterType);
         if(ParameterType != _fulltype) {
            nodeParam.Set("full_type", _fulltype);
         }
         if(Annotate.Trim() != "") {
            nodeParam.Set("description", Annotate);
         }
         nodeMethod.Push(nodeParam);
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
         string errID = "AS06PA03A" + line + "L";
         errID = errID.PadRight(16);
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "]";
         swPrint.WriteLine(error);
      }
   }
}

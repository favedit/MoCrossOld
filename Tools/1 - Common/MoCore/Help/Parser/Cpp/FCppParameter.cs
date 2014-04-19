using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;
using System.IO;

namespace MO.Core.Help.Parser.Cpp
{
   //============================================================
   // <T>Cpp文件中的方法对象中参数对象的描述，提取，以及解析操作。</T>
   //
   // @author CJJUN 120329
   //============================================================
   public class FCppParameter
   {
      //方法的注释
      protected FStrings _annotate = new FStrings();

      //属于参数的字符串段
      protected string _param;

      //索引号
      protected int _index;

      //============================================================
      // <T>参数名称。</T>
      //============================================================
      public string ParameterName {
         get {
            if(_param.Trim() != "") {
               if(_param.Contains(' ')) {
                  string[] pa = _param.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
                  if(pa.Length > 1) {
                     string strTemp = pa[1].Trim();
                     return strTemp;
                  }
               }
            }
            return string.Empty;
         }
      }

      //============================================================
      // <T>参数的类型。</T>
      //============================================================
      public string ParameterType {
         get {
            if(_param.Trim() != "") {
               if(_param.Contains(' ')) {
                  string[] pa = _param.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
                  if(pa.Length > 1) {
                     string strTemp = pa[0].Trim();
                     return strTemp;
                  }
               }
            }
            return string.Empty;
         }
      }

      //============================================================
      // <T>参数的注释信息。</T>
      //============================================================
      public string Description {
         get {
            foreach(string s in _annotate) {
               if(s.Contains(ParameterName)) {
                  int index = s.IndexOf(ParameterName) + ParameterName.Length;
                  string strTemp = s.Substring(index).Trim();
                  return strTemp;
               }
            }
            return string.Empty;
         }
      }

      //============================================================
      // <T>构造函数。</T>
      //============================================================
      public FCppParameter() { }

      //============================================================
      // <T>构造函数。</T>
      //============================================================
      public FCppParameter(FStrings anno, string param,int index) {
         this._annotate = anno;
         this._param = param;
         this._index = index;
      }

      //============================================================
      // <T>将参数节点附加到方法节点下面。</T>
      //
      // @param nodeMethod 方法节点
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路径
      //============================================================
      public void ParserParameter(FXmlNode nodeMethod,StreamWriter swPrint, FileInfo file,string relative) {
         XMLMaker(nodeMethod,swPrint,file,relative);
      }

      //============================================================
      // <T>将参数节点附加到方法节点下面。</T>
      //
      // @param nodeMethod 方法节点
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路径
      //============================================================
      public void XMLMaker(FXmlNode nodeMethod, StreamWriter swPrint, FileInfo file,string relative) {
         if(!IsParamAnnoFine()) {
            PrintAnnotateWarning(swPrint, file,relative);
         }
         FXmlNode nodeParam = new FXmlNode("Parameter");
         nodeParam.Set("name", ParameterName);
         nodeParam.Set("type", ParameterType);
         if(Description != "") {
            nodeParam.Set("description", Description);
         }
         nodeMethod.Push(nodeParam);
      }

      //============================================================
      // <T>参数注释是否正常。</T>
      //
      // @return true为正常，反之不正常，则有警告
      //===========================================================
      public bool IsParamAnnoFine() {
         if(Description  == "") {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>打印没有参数注释的警告。</T>
      //
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路径
      //============================================================
      public void PrintAnnotateWarning(StreamWriter swPrint, FileInfo file,string relative) {
         string line = _index.ToString();
         string errID = "CPP06PA03A" + line + "L";
         errID = errID.PadRight(16);
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "]";
         swPrint.WriteLine(error);
      }
   }
}

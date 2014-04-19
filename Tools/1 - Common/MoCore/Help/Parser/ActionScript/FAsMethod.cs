using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Core.Help.Common;
using MO.Common.Lang;
using MO.Common.Collection;
using MO.Common.IO;
using MO.Common.Content;
using System.IO;

namespace MO.Core.Help.Parser.ActionScript
{
   //============================================================
   // <T>有关方法的判断，提取，生成，解析等操作。</T>
   //
   // @author CJJUN 120331
   //============================================================
   public class FAsMethod:FCrHelpNode
   {
      //方法的返回类型
      protected string _methodType=string.Empty;

      //方法民
      protected string _methodName=string.Empty;

      //方法的注释
      protected FStrings _annotates=new FStrings();

      //方法的参数
      protected FArray<FAsParameter> _parameter=new FArray<FAsParameter>();

      //方法的字符串组
      protected FStrings _strLine=new FStrings();
 
      //属于方法的整行字符串
      protected string _methodStr=string.Empty;
      
      //属于方法的代码段
      protected string _methodCode = string.Empty;
      
      //属于方法的字符串行的索引范围
      protected int _beginIndex=-1,_endIndex=-1;

      #region 各个字段对应的属性
      //============================================================
      // <T>方法的返回值。</T>
      //============================================================
      public string MethodType {
         get {
            string strTemp = _methodStr.Trim();
            int index = strTemp.LastIndexOf(':');
            int end = strTemp.Length;
            if(strTemp.Contains('{')) { end = strTemp.IndexOf('{'); }
            _methodType = strTemp.Substring(index + 1, end - index - 1);
            return _methodType;
         }
      }

      //============================================================
      // <T>方法名。</T>
      //============================================================
      public string MethodName {
         get {
            int eindex = this._strLine[_beginIndex].Trim().IndexOf("(");
            int sindex = this._strLine[_beginIndex].Trim().LastIndexOf(" ", eindex);
            string str = _strLine[_beginIndex].Trim().Substring(sindex, eindex - sindex).Trim();
            return str.Trim();
         }

      }

      //============================================================
      // <T>注释中对方法返回值的描述。</T>
      //============================================================
      public string AnnotateReturn {
         get {
            _annotates = GetAnnotate(_strLine, _beginIndex);
            string annotate = string.Empty;
            foreach(string str in _annotates) {
               if(str.Contains("@return")) {
                  int start = str.IndexOf("@return") + 7;
                  string strReturn = str.Substring(start).Trim();
                  annotate = strReturn;
               }
            }
            return annotate.Trim();
         }
      }

      //============================================================
      // <T>注释中对方法的功能的描述。</T>
      //============================================================
      public string AnnotateLabel {
         get {
            _annotates = GetAnnotate(_strLine, _beginIndex);
            string annotate = string.Empty;
            foreach(string str in _annotates) {
               if(str.Contains("<T>")) {
                  int start = str.IndexOf(">");
                  int end = str.LastIndexOf("<");
                  for(int n = start + 1; n < end; n++) {
                     annotate += str[n];
                  }
                  return annotate.Trim();
               }
            }
            return string.Empty;
         }
      }

      //============================================================
      // <T>注释中对方法整体的描述。</T>
      //============================================================
      public string AnnotateDescription {
         get {
            _annotates = GetAnnotate(_strLine, _beginIndex);
            string annotate = string.Empty;
            foreach(string str in _annotates) {
               if(str.Contains("<P>")) {
                  if(str.Contains("</P>")) {
                     string strTemp = str.Replace("</P>", "");
                     int index = strTemp.IndexOf('>') + 1;
                     strTemp = strTemp.Substring(index).Trim();
                     if(strTemp.Trim() != "") { annotate += strTemp.Trim() + "；"; }
                  }
               }
            }
            return annotate.Trim();
         }
      }

      //============================================================
      // <T>方法中的参数。</T>
      //============================================================
      public FArray<FAsParameter> Parameter {
         get {
            return this._parameter;
         }
      }

      //============================================================
      // <T>方法的访问等级。</T>
      //============================================================
      public string AccessLevel {
         get {
            string access_level = _methodStr.Trim();
            int end = access_level.IndexOf(' ');
            access_level = access_level.Substring(0, end);
            return access_level.Trim();
         }
      }

      //============================================================
      // <T>方法的全名。</T>
      //============================================================
      public string FullMethodName {
         get {
            string str = _methodStr.Trim();
            int i = str.IndexOf(' ');
            str = str.Substring(i).Trim();
            int e = str.Length;
            if(str.Contains('{')) {
               e = str.IndexOf('{');
            }
            str = str.Substring(0, e);
            return str;
         }
      }

      //============================================================
      // <T>属于该方法的代码段。</T>
      //============================================================
      public string MethodCode {
         get {
            _methodCode = "";
            string strTemp = _strLine[_beginIndex].ToString();
            int preIndex = strTemp.Length - strTemp.Trim().Length;
            for(int n = _beginIndex; n <= _endIndex; n++) {
               strTemp = string.Empty;
               if(_strLine[n].ToString().Length > preIndex) {
                  strTemp = _strLine[n].ToString().Substring(preIndex).TrimEnd() + "\r\n";
               }
               if(_strLine[n].ToString().Length == preIndex) {
                  strTemp = _strLine[n].ToString().Substring(preIndex).TrimEnd();
               }
               _methodCode += strTemp;
            }
            return _methodCode;
         }
      }
      #endregion


      //============================================================
      // <T>构造方法。</T>
      //============================================================
      public FAsMethod() {
         _methodName = string.Empty;
         _methodType = string.Empty;
      }

      //============================================================
      // <T>构造方法。</T>
      //
      // @param strLines 解析的代码行集合
      // @param beginIndex 开始行索引
      // @param endIndex 结束行索引
      //============================================================
      public FAsMethod(FStrings strlines,int beginIndex,int endIndex) {
         _methodName = string.Empty;
         _methodType = string.Empty;
         _beginIndex = beginIndex;
         _endIndex = endIndex;
         _strLine = strlines;
      }

      //============================================================
      // <T>在一个字符串数组中判断某一行是否为一个方法的开始。</T>
      //
      // @param strLines 有待解析的字符串数组
      // @param index 当前正在解析的行的行号
      // @return true表示该行为方法的开始，false表示改行不为方法的开始
      //============================================================
      public static bool IsMethod(FStrings strLines, int index) {
         string s = strLines[index].ToString().Trim();
         if(!s.Contains(" function ")) { return false; }
         if(!s.Contains('(')) { return false; }
         if(!s.Contains(')')) { return false; }
         if(s.Contains(" get ")) {
            int b = s.IndexOf(" get ") + 6;
            int e = s.IndexOf('(');
            if(s.Substring(b, e - b).Trim() != "") { return false; }
         }
         if(s.Contains(" set ")) {
            int b = s.IndexOf(" set ") + 6;
            int e = s.IndexOf('(');
            if(s.Substring(b, e - b).Trim() != "") { return false; }
         }
         int indexChar = strLines[index].Trim().IndexOf(" ");
         string accessClassification;
         if(indexChar >= 0) {
            accessClassification = s.Substring(0, indexChar);
            FStrings accessClass = new FStrings() { "public", "protected", "private", "partial", "internal" };
            foreach(string str in accessClass) {
               if((accessClassification == str) && !s.Contains('='))
                  return true;
            }
         }
         return false;
      }

      //============================================================
      // <T>对方法字符串进行解析。</T>
      //
      // @param nodeClass 需要连接上的父节点也是类节点
      // @param indextable 类的全名（命名空间+类名）和类名的索引表
      // @param relative 当前解析文件所在的目录的路径
      // @param package 类所在包的包对象
      // @param file 当前解析的文件
      //============================================================
      public void ParserMethod(FXmlNode nodeClass, FAsClassesIndexTable indextable,StreamWriter swPrint,FileInfo file,string relative) {
         _methodStr=_strLine[_beginIndex].ToString().Trim();
         int paraStart = _methodStr.IndexOf('(')+1;
         int paraEnd = _methodStr.IndexOf(')');
         string paramstring=_methodStr.Substring(paraStart,paraEnd-paraStart).Trim();
         FXmlNode nodeMethod = XMLMaker(nodeClass, indextable,swPrint,file,relative);
         FXmlNode parameters = new FXmlNode("Parameters");
         nodeMethod.Push(parameters);
         if(paramstring != "") {
            string[] pa = paramstring.Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries);
            for(int n = 0; n < pa.Length; n++) {
               FAsParameter asParameter = new FAsParameter(pa[n].Trim(), _strLine,_beginIndex);
               asParameter.ParserParameter(indextable,parameters,swPrint,file,relative);
               this.Parameter.Add(asParameter);
            }
            MethodReturnNode(indextable, nodeMethod);   
         }
         FXmlNode methodCode = new FXmlNode("Source");
         methodCode.Text = MethodCode;
         nodeMethod.Push(methodCode);
      }

      //============================================================
      // <T>根据方法的信息生成方法的节点，并附加到类节点上。</T>
      //
      // @param nodeClass 需要连接上的父节点也是类节点
      // @param indextable 类的全名（命名空间+类名）和类名的索引表
      // @param path 解析结果的输出路径
      // @param swPrint 用于打印警告结果的StreamWriter对象
      // @param relative 当前解析文件所在的目录的路径
      // @param package 类所在包的包对象
      // @param file 当前解析的文件
      // @return 返回自身的方法节点
      //============================================================
      public FXmlNode XMLMaker(FXmlNode nodeClass, FAsClassesIndexTable indextable, StreamWriter swPrint, FileInfo file, string relative) {
         if(!IsMethodAnnoFine()) {
            PrintAnnoWarning(swPrint, file, relative);
         }
         FXmlNode nodeMethod = new FXmlNode("Method");
         nodeMethod.Set("name", MethodName);
         nodeMethod.Set("full_name", FullMethodName);
         if(AnnotateLabel != "") { nodeMethod.Set("label", AnnotateLabel); }
         if(AnnotateDescription != "") { nodeMethod.Set("description", AnnotateDescription); }
         nodeClass.Push(nodeMethod);
         return nodeMethod;
      }

      //============================================================
      // <T>根据方法的返回值信息生成方法的返回值节点，并附加到方法节点上</T>
      //
      // @param nodeClass 需要连接上的父节点也是类节点
      // @param indextable 类的全名（命名空间+类名）和类名的索引表
      //============================================================
      public void MethodReturnNode(FAsClassesIndexTable indextable, FXmlNode nodeMethod) {
         if(MethodType != "" && MethodType != "void") {
            FXmlNode methodTypeNode = new FXmlNode("Return");
            methodTypeNode.Set("type", MethodType);
            FFileNode type = new FFileNode(MethodType, GetImport(_strLine));
            string _fulltype = indextable.LookInMap(type, this.MethodType);
            if(_fulltype != MethodType) { methodTypeNode.Set("full_type", _fulltype); }
            nodeMethod.Push(methodTypeNode);
         }
      }

      //============================================================
      // <T>判断参数的注释是否齐全或者符合规范。</T>
      //
      // @return true 表示符合规范，否则不符合规范。
      //============================================================
      public bool IsMethodAnnoFine() {
         if(AnnotateLabel == "") {
            return false;
         } else {
            if(MethodType != "" && MethodType != "void" && AnnotateReturn == "") {
               return false;
            } else {
               return true;
            }
         }
      }

      //============================================================
      // <T>打印不符合规范的方法注释的警告信息。</T>
      //
      // @param swPrint 用于打印警告信息的StreamWriter对象
      // @param file 当前检测的文件
      // @param relative 文件所在的目录的路径
      //============================================================
      public void PrintAnnoWarning(StreamWriter swPrint, FileInfo file, string relative) {
         string line = _beginIndex.ToString();
         string errID = "AS05M03A" + line + "L";
         errID = errID.PadRight(16);
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "] " + "可能缺少T注释或者返回值注释！！";
         swPrint.WriteLine(error);
      }
   }
}

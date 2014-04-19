using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Lang;
using MO.Core.Help.Common;
using MO.Common.Content;
using MO.Core.Help.Parser.ActionScript;
using System.IO;

namespace MO.Core.Help.Parser.Cpp
{
   //============================================================
   // <T>Cpp文件中的方法对象的描述，提取，以及解析操作。</T>
   //
   // @author CJJUN 120329
   //============================================================
   public class FCppMethod : FCrHelpNode
   {
      //方法所属的字符串数组
      protected FStrings _strLines = new FStrings();

      //属于方法的字符串数集合索引号开始
      protected int _beginIndex = -1;

      //属于方法的字符串数集合结束索引号
      protected int _endIndex = -1;

      //============================================================
      // <T>方法名</T>
      //============================================================
      public string MethodName {
         get {
            string strTemp = _strLines[_beginIndex].Trim();
            int end = strTemp.Length;
            if(strTemp.Contains('(')) {
               end = strTemp.IndexOf('(');
            }
            strTemp = strTemp.Substring(0, end).Trim();
            int start = 0;
            if(strTemp.Contains("::")) {
               start = strTemp.IndexOf("::") + 2;
            }
            strTemp = strTemp.Substring(start, end - start).Trim();
            return strTemp;
         }
      }

      //============================================================
      // <T>方法全名</T>
      //============================================================
      public string FullMethodName { 
         get{
            string strTemp = _strLines[_beginIndex].Trim();
            int end = strTemp.Length;
            if(strTemp.Contains('{')) {
               end = strTemp.IndexOf('{');
            }
            return strTemp.Substring(0, end).Trim();
         }
      }

      //============================================================
      // <T>方法的类型，返回类型</T>
      //============================================================
      public string MethodType {
         get {
            string strTemp = _strLines[_beginIndex].Trim();
            int index = 0;
            if(strTemp.Contains("::")) {
               index = strTemp.IndexOf("::");
               strTemp = strTemp.Substring(0, index).Trim();
            }
            index = 0;
            if(strTemp.Contains(' ')) {
              index= strTemp.IndexOf(' ');
            }
            strTemp = strTemp.Substring(0, index).Trim();
            return strTemp;
         }
      }

      //============================================================
      // <T>在注释中对返回值的描述</T>
      //============================================================
      public string AnnotateReturn {
         get {
            FStrings annotates = GetAnnotate(_strLines, _beginIndex);
            string annotate = string.Empty;
            foreach(string str in annotates) {
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
      // <T>在注释中对方法的功能的说明</T>
      //============================================================
      public string AnnotateLable {
         get {
            FStrings annotates = GetAnnotate(_strLines, _beginIndex);
            string annotate = string.Empty;
            foreach(string str in annotates) {
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
      // <T>方法description注释。</T>
      //============================================================
      public string AnnotateDescription {
         get {
            FStrings annotates = GetAnnotate(_strLines, _beginIndex);
            string annotate = string.Empty;
            foreach(string str in annotates) {
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
      // <T>方法代码。</T>
      //============================================================
      public string MethodCode {
         get {
            string strTemp = _strLines[_beginIndex].ToString();
            string methodCode = string.Empty;
            int preIndex = strTemp.Length - strTemp.Trim().Length;
            for(int n = _beginIndex; n <= _endIndex; n++) {
               strTemp = string.Empty;
               if(_strLines[n].ToString().Length >= preIndex) {
                  strTemp = _strLines[n].ToString().Substring(preIndex).TrimEnd() + "\r\n";
               }
               methodCode+= strTemp;
            }
            return methodCode;
         }
      }

      //============================================================
      // <T>构造方法</T>
      //============================================================
      public FCppMethod() { }

      //============================================================
      // <T>构造方法。</T>
      //
      // @param strLines 正在解析的代码行集合
      // @param begin 方法代码段的开始索引行号
       //@param end 方法代码段的结束索引行号
      //============================================================
      public FCppMethod(FStrings strLines, int begin, int end) {
         this._strLines = strLines;
         this._beginIndex = begin;
         this._endIndex = end;
      }

      //============================================================
      // <T>判断正在解析的代码是否为从cpp文件中的方法代码。</T>
      // 
      // @param strLines 正在解析的代码集合
      // @param index 当前解析的行号
      //============================================================
      public static bool IsMethodInCpp(FStrings strLines,int index){
         string strTemp = strLines[index].Trim();
         bool b = false;
         if(strTemp.Contains("{")) {
            b = true;
         } else {
            int i = index + 1;
            if(i >= strLines.Count) {
               return false;
            }
            string temp = strLines[i].Trim();
            if(temp.Contains("{")) {
               b = true;
            } else { b = false; }
         }
         if(strTemp.Contains("::")&&strTemp.Contains("(")&&!strTemp.Contains(";")&&!strTemp.Contains("#")&&b) {
            int i = strTemp.IndexOf("::");
            if(strTemp.Substring(0, i).Contains("(")) { return false; }
            int num = strTemp.Count<char>(c => c.Equals(')'));
            if(num > 1) { return false; }
            return true;
         } else {
            return false;
         }
      }

      public static bool IsMethod(FStrings strLines, int index) {
         return false;
      }

      //============================================================
      // <T>解析方法段代码。</T>
      // 
      // @param nodeParent 需要附加到的父节点
      // @param swPrint StringWriter对象用来输出警告或者错误。
      // @param file 当前解析的文件
      // @param relativePath 被选择解析的文件夹路径
      //============================================================
      public void ParserMethod(FXmlNode nodeParent, StreamWriter swPrint, FileInfo file, string relativePath) { 
         string methodLine=_strLines[_beginIndex].ToString().Trim();
         int paraStart = methodLine.IndexOf('(')+1;
         int paraEnd = methodLine.IndexOf(')');
         string paramstring=methodLine.Substring(paraStart,paraEnd-paraStart).Trim();
         FXmlNode nodeMethod = XMLMaker(nodeParent,swPrint,file,relativePath);
         if(paramstring != "") {
            string[] pa = paramstring.Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries);
            for(int n = 0; n < pa.Length; n++) {
               FCppParameter parameter = new FCppParameter(GetAnnotate(_strLines, _beginIndex), pa[n].Trim(),_beginIndex);
               parameter.ParserParameter(nodeMethod,swPrint,file,relativePath);
            }
            MethodReturnNode( nodeMethod);   
         }
         FXmlNode methodCode = new FXmlNode("Source");
         methodCode.Text = MethodCode;
         nodeMethod.Push(methodCode);
      }

      //============================================================
      // <T>将方法对象对象解析成XML文档，并解析出代码在编译器外的警告或者错误。</T>
      // 
      // @param nodeClass 需要附加到的类的节点
      // @param swPrint StringWriter对象用来输出警告或者错误。
      // @param file 当前解析的文件
      // @param relativePath 被选择解析的文件夹路径
      //============================================================
      public FXmlNode XMLMaker(FXmlNode nodeClass, StreamWriter swPrint, FileInfo file, string relativePath) {
         int index = -1;
         if(!IsMethodAnnoFine()) {
            PrintWarning(swPrint, file,relativePath);
         }
         if(!IsReturnfine()) {
            PrintError(swPrint, file, relativePath);
         }
         if(!IsSwitchFine(out index)) {
            PrintDefaultError(swPrint, file, relativePath,index);
         }
         if(!IsforFine(out index)) {
            PrintForError(swPrint, file, relativePath, index);
         }
         if(!IsIfFine(out index)) {
            PrintIfError(swPrint, file, relativePath, index);
         }
         FXmlNode nodeMethod = new FXmlNode("Method");
         nodeMethod.Set("name", MethodName);
         nodeMethod.Set("full_name", FullMethodName);
         if(AnnotateLable != "") { nodeMethod.Set("label", AnnotateLable); }
         if(AnnotateDescription != "") { nodeMethod.Set("description", AnnotateDescription); }
         nodeClass.Push(nodeMethod);
         return nodeMethod;
      }
      //============================================================
      // <T>将方法的返回值作为单独的节点附加到方法节点下面。</T>
      //
      // @param nodeMethod 需要附加上的方法节点
      //============================================================
      public void MethodReturnNode( FXmlNode nodeMethod) {
         if(MethodType != "" && MethodType != "void") {
            FXmlNode returnNode = new FXmlNode("Return");
            returnNode.Set("type", MethodType);
            nodeMethod.Push(returnNode);
         }
      }

      //============================================================
      // <T>判断函数的返回值是否正常。</T>
      //
      // @return true 表示返回值正常，反之，false表示不正常
      //============================================================
      public bool IsReturnfine() {
         if(MethodType != "void" &&MethodType!="") {
            for(int n = _endIndex-1; n >= _beginIndex; n--) {
               if(_strLines[n].Contains('{') || _strLines[n].Contains('}')) {
                  return false;
               }
               if(_strLines[n].Trim().StartsWith("return")) {
                  string temp = _strLines[n].Trim();
                  int index = temp.IndexOf(";");
                  temp = temp.Substring(7, index - 7);
                  if(temp.Trim() != "") {
                     return true;
                  } else {
                     return false;
                  }
               }
            }
         }
         return true;
      }

      //============================================================
      // <T>方法的注释是否规范而且齐全。</T>
      //
      // @return true表示规范，反之不规范
      //============================================================
      public bool IsMethodAnnoFine() {
         if(AnnotateLable == "") {
            return false;
         }
         if(MethodType != "" && MethodType != "void" && AnnotateReturn == "") {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>解析方法的代码，看是否有不合规范的switch代码段。</T>
      //
      // @param index 错误发生的行号
      // @return true 表示正常，反之有Is异常
      //============================================================
      public bool IsSwitchFine(out int index) {
         for(int n = _beginIndex; n <= _endIndex; n++) {
            if(_strLines[n].Trim().StartsWith("//")) { continue; }
            string temp = _strLines[n].Trim();
            if(temp.Contains("switch")) {
               string s = temp.Substring(temp.IndexOf("switch") + 6).Trim();
               if(s.StartsWith("(")) {
                  int endline = GetPairNum(_strLines, n);
                  for(int i = endline; i >= n; i--) {
                     if(_strLines[i].Trim().Contains("default:")) {
                        index = i;
                        return true;
                     }
                     if(_strLines[i].Trim().Contains('{') || _strLines[i].Contains('}')) {
                        index=i;
                        return false;
                     }
                     if(_strLines[i].Trim().StartsWith("case")) {
                        index = i;
                        return false;
                     }
                  }
               }
            }
         }
         index = _endIndex;
         return true;
      }

      //============================================================
      // <T>解析方法的代码，看是否有不合规范的for代码段。</T>
      //
      // @param index 错误发生的行号
      // @return true 表示正常，反之有Is异常
      //============================================================
      public bool IsforFine(out int index) {
         for(int n = _beginIndex; n <= _endIndex; n++) {
            if(_strLines[n].Trim().StartsWith("//")) { continue; }
            if(_strLines[n].Contains(" for(") || _strLines[n].Contains(" for (")) {
               string temp = _strLines[n].Trim();
               if(temp.EndsWith("{")||_strLines[n+1].Trim().StartsWith("{")) {
                  index = n;
                  return true;
               }
               index = n;
               return false;
            }
         }
         index = _endIndex;
         return true;
      }

      //============================================================
      // <T>解析方法的代码，看是否有不合规范的if代码段。</T>
      //
      // @param index 错误发生的行号
      // @return true 表示正常，反之有Is异常
      //============================================================
      public bool IsIfFine(out int index) {
         for(int n = _beginIndex; n <= _endIndex; n++) {
            if(_strLines[n].Trim().StartsWith("//")) { continue; }
            if(_strLines[n].Contains(" if(") || _strLines[n].Contains(" if ")) {
               string temp = _strLines[n];
               if(!temp.Contains(')')) {
                  index = n;
                  return false;
               }
               string s = temp.Substring(temp.IndexOf(" if"));
               if(s.Contains('{') || _strLines[n + 1].Trim().StartsWith("{")) {
                  index = n;
                  return true;
               }
               index = n;
               return false;
            }
         }
         index = _endIndex;
         return true;
      }

      //============================================================
      // <T>在switch中没有使用default。</T>
      //
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relativePath 被选取的文件夹的路径
      // @param lineIndex 错误发生的行号
      //============================================================
      public void PrintDefaultError(StreamWriter swPrint,FileInfo file,string relative,int lineIndex) {
         string line = lineIndex.ToString();
         //line = line.PadLeft(5);
         string errID = "CPP07CL02S" + line +"L";
         errID = errID.PadRight(16);
         string tips = "在switch中缺少default：关键字！！";
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "( " + line + " )";
         rpath = rpath.PadRight(45);
         string error = "[错误][" + errID + "][" + rpath + "] " + tips;
         swPrint.WriteLine(error);
      }

      //============================================================
      // <T>打印没有参数注释的警告。</T>
      //
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relativePath 被选取的文件夹的路径
      // @param lineIndex 错误发生的行号
      //============================================================
      public void PrintIfError(StreamWriter swPrint, FileInfo file, string relative, int lineIndex) {
         string line = lineIndex.ToString();
         //line = line.PadLeft(5);
         string errID = "CPP07CL05I" + line + "L";
         errID = errID.PadRight(16);
         string tips = "在If循环控制语句里缺少{}！！";
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "( " + line + " )";
         rpath = rpath.PadRight(45);
         string error = "[错误][" + errID + "][" + rpath + "] " + tips;
         swPrint.WriteLine(error);
      }

      //============================================================
      // <T>打印没有参数注释的警告。</T>
      //
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relativePath 被选取的文件夹的路径
      // @param lineIndex 错误发生的行号
      //============================================================
      public void PrintForError(StreamWriter swPrint, FileInfo file, string relative, int lineIndex) {
         string line = lineIndex.ToString();
         //line = line.PadLeft(5);
         string errID = "CPP07CL04F" + line + "L";
         errID = errID.PadRight(16);
         string tips = "在For循环控制语句里缺少{}！！";
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "( " + line.Trim() + " )";
         rpath = rpath.PadRight(45);
         string error = "[错误][" + errID + "][" + rpath + "] " + tips;
         swPrint.WriteLine(error);
      }

      //============================================================
      // <T>打印没有参数注释的警告。</T>
      //
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relativePath 被选取的文件夹的路径
      // @param lineIndex 错误发生的行号
      //============================================================
      public void PrintWarning(StreamWriter swPrint, FileInfo file, string relativePath) {
         string line=_beginIndex.ToString();
         //line=line.PadLeft(5);
         string errID = "CPP05M01R" + line + "L";
         errID=errID.PadRight(16);
         string tips = "在注释中可能存在T注释或者返回值注释的缺失！！";
         string rpath = file.FullName.Substring (relativePath.Length);
         rpath+="( "+line+" )";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "] " + tips;
         swPrint.WriteLine(error);
      }

      //============================================================
      // <T>打印没有参数注释的警告。</T>
      //
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relativePath 被选取的文件夹的路径
      // @param lineIndex 错误发生的行号
      //============================================================
      public void PrintError(StreamWriter swPrint, FileInfo file, string relativePath){
         string line = _endIndex.ToString();
         //line = line.PadLeft(5);
         string errID = "CPP05M03A" + line + "L";
         errID = errID.PadRight(16);
         string tips = "可能不是所有的代码路径都有返回值！！";
         string rpath = file.FullName.Substring(relativePath.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[错误][" + errID + "][" + rpath + "] "+tips;
         swPrint.WriteLine(error);
      }
   }
}

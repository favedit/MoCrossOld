using System;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Core.Help.Common;
using System.Collections.Generic;
using System.IO;
namespace MO.Core.Help.Parser.CSharp
{
   //============================================================
   // <T>属于方法的解析，提取，生成XML的一系列操作。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FCsMethod : FCrHelpNode
   {
      //方法类型
      protected string _methodType=string.Empty;

      //方法名
      protected string _methodName=string.Empty;

      //方法的注释
      protected FStrings _annotates;

      //属于方法的参数对象
      protected FArray<FCsParameter> _parameter;

      //方法所属于的字符串行集合
      protected FStrings _strLine;
      
      //属于方法的字符串行
      protected string _methodStr=string.Empty;
      
      //方法的代码
      protected string _methodCode = string.Empty;
      
      //返回类型
      protected string _returnType=string.Empty;
      
      //方法在字符串行中的开始和结束索引
      protected int _beginIndex,_endIndex;

      //============================================================
      // <T>构造方法</T>
      //============================================================
      public FCsMethod() {
         _methodName = string.Empty;
         _methodType = string.Empty;
         _annotates = new FStrings();
         _parameter = new FArray<FCsParameter>();
      }

      //============================================================
      // <T>构造方法</T>
      //============================================================
      public FCsMethod(FStrings strlines,int beginIndex,int endIndex) {
         _methodName = string.Empty;
         _methodType = string.Empty;
         _annotates = new FStrings();
         _parameter = new FArray<FCsParameter>();
         _strLine = strlines;
         _beginIndex = beginIndex;
         _endIndex = endIndex;
      }

      #region 各个字段对应的属性

      //============================================================
      // <T>方法的返回值类型</T>
      //============================================================
      public string ReturnType {
         get {
            if(_returnType != string.Empty) { return _returnType; }
            string method = _methodStr.Trim();
            int start = method.IndexOf(' ');
            int indexleft = method.IndexOf('(');
            method = method.Substring(start, indexleft - start).Trim();
            int end = method.LastIndexOf(' ');
            if(end != -1) {
               if((start - end) <= 0) { return string.Empty; }
               _returnType = method.Substring(0, end).Trim();
               return _returnType;
            }
            return string.Empty;
         }
         set { this._returnType = value; }
      }

      //============================================================
      // <T>方法类型</T>
      //============================================================
      public string MethodType {
         get {
            int index = _strLine[_beginIndex].Trim().IndexOf(' ');
            int end = _strLine[_beginIndex].Trim().IndexOf(' ',index);
            string strReturn= _strLine[_beginIndex].Trim().Substring(index,end-1).Trim();
            return strReturn;
         }
      }

      //============================================================
      // <T>方法名</T>
      //============================================================
      public string MethodName {
         get {
            int eindex = this._strLine[_beginIndex].Trim().IndexOf("(");
            int sindex = this._strLine[_beginIndex].Trim().LastIndexOf(" ",eindex);
            string str = _strLine[_beginIndex].Trim().Substring(sindex,eindex - sindex).Trim();
            return str.Trim();
         }

      }

      //============================================================
      // <T>注释中方法的返回值描述。</T>
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
      // <T>注释中对方法的功能描述。</T>
      //============================================================
      public string AnnotateLabel {
         get {
            _annotates = GetAnnotate(_strLine,_beginIndex);
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
      // <T>注释中对方法的使用描述。</T>
      //============================================================
      public string AnnotateDescription {
         get {
            _annotates = GetAnnotate(_strLine, _beginIndex);
            string annotate = string.Empty;
            foreach(string str in _annotates) {
               if(str.Contains("<P>")) {
                  if(str.Contains("</P>")){
                     string strTemp=str.Replace("</P>","");
                     int index=strTemp.IndexOf('>')+1;
                     strTemp=strTemp.Substring(index).Trim();
                     if(strTemp.Trim() != "") { annotate += strTemp.Trim() + "；"; }
                  }
               }
            }
            return annotate.Trim();
         }
      }

      //============================================================
      // <T>方法的参数</T>
      //============================================================
      public FArray<FCsParameter> Parameter {
         get {
            return this._parameter;
         }
      }

      //============================================================
      // <T>方法的可访问等级</T>
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
      // <T>方法的全名</T>
      //============================================================
      public string FullMethodName {
         get {
            if(_methodStr.Trim().Contains("{")) { 
               int end=_methodStr.Trim().IndexOf('{');
               string fullName=_methodStr.Trim().Substring(0,end);
               return fullName;
            }
            return _methodStr.Trim();
         }
      }

      //============================================================
      // <T>属于该方法的代码段</T>
      //============================================================
      public string MethodCode {
         get {
            string strTemp = _strLine[_beginIndex].ToString();
            int preIndex = strTemp.Length - strTemp.Trim().Length;
            for(int n = _beginIndex; n <= _endIndex; n++) {
               strTemp = string.Empty;
               if(_strLine[n].ToString().Length >= preIndex) {
                  strTemp = _strLine[n].ToString().Substring(preIndex).TrimEnd() + "\r\n";
               }
               _methodCode += strTemp;
            }
            return _methodCode;
         }
      }
      #endregion

      //============================================================
      // <T>解析方法。</T>
      //
      // @parame strline 需要解析的字符串集合。
      // @param index 当前索引行的行号
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路径
      //============================================================
      public void ParserMethod(FStrings strLine, int index, FXmlNode nodeClass,FClassesIndexTable indextable,StreamWriter swPrint,FileInfo file,string relative) { 
         int eindex = strLine[index].Trim().IndexOf("(");
         int end = strLine[index].Trim().IndexOf(")");
         string methodStr = strLine[index].Trim().Substring(eindex + 1,end - eindex - 1);
         methodStr = ScanComma(methodStr);
         string[] strParams = methodStr.Split(new string[] { "$," },StringSplitOptions.None);
         if((strParams.Length == 1) && strParams[0] == "") {
            FXmlNode xmlmethod = new FXmlNode("Method");
            xmlmethod.Set("name", this.MethodName);
            xmlmethod.Set("full_name", this.FullMethodName);
            if(this.AnnotateLabel != "") { xmlmethod.Set("label", this.AnnotateLabel); }
            if(this.AnnotateDescription != "") { xmlmethod.Set("description", this.AnnotateDescription); }
            ReturnNodeMaker(xmlmethod,indextable);
            nodeClass.Push(xmlmethod);
            return;
         }
         if(strParams.Length != 0&&strParams[0]!="") {
            FXmlNode method = XMLMaker(nodeClass,swPrint,file,relative);
            for(int n = 0; n < strParams.Length; n++) {
               FCsParameter param = new FCsParameter();
               FStrings strAnnos = GetAnnotate(strLine,index);
               param.FillValue(strLine,strAnnos,_beginIndex);
               param.ParserParam(strParams[n], strAnnos, indextable);
               param.XMLMaker(method,swPrint,file,relative);
               if(param.ParameterName != "") {
                  this.Parameter.Add(param);
               }
            }
            ReturnNodeMaker(method,indextable);
         }         
      }

      

      //============================================================
      // <T>向对象传入相应的值。</T>
      //
      // @param strLine 需要传入的字符串行。也就是解析文件所得的各行
      // @param beginLine 属于该对象的行起始行索引
      // @param endLine 属于该对象的行终止索引
      //============================================================
      public void FillValue(FStrings strlines,int beginIndex,int endIndex) {
         _strLine = strlines;
         _beginIndex = beginIndex;
         _endIndex = endIndex;
         _methodStr = strlines[beginIndex].ToString().Trim();
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
      public FXmlNode XMLMaker(FXmlNode node, StreamWriter swPrint, FileInfo file, string relative) {
         if(!IsMethodAnnoFine()) {
            PrintAnnoWarning(swPrint, file, relative);
         }
         FXmlNode method = new FXmlNode("Method");
         method.Set("name",this.MethodName);
         method.Set("full_name", this.FullMethodName.Trim());
         if(this.AnnotateLabel != "") { method.Set("label", this.AnnotateLabel); }
         if(this.AnnotateDescription != "") { method.Set("description", this.AnnotateDescription); }
         if(this.AccessLevel != "") { 
            method.Set("access_level", this.AccessLevel);
         }
         node.Push(method);
         return method;
      }

      //============================================================
      // <T>在方法节点的子节点中连接上方法的返回值节点
      //
      // @param node 返回值节点的父节点，即隶属于的方法节点
      // @param indextable 类型全名的索引表
      //============================================================
      private void ReturnNodeMaker(FXmlNode node,FClassesIndexTable indextable) {
         FXmlNode nodeRetrun = new FXmlNode("Return");
         if(this.ReturnType != "" && this.ReturnType != "void") {
            string strType = this.ReturnType;
            nodeRetrun.Set("type", this.ReturnType);
            FFileNode type = new FFileNode(this.ReturnType, GetUsing(_strLine));
            this.ReturnType = indextable.LookInMap(type,this.ReturnType);
            if(strType != this.ReturnType) { nodeRetrun.Set("return_type", this.ReturnType);  }
         }
         if(this.AnnotateReturn.Trim()!= "") {
            nodeRetrun.Set("description", this.AnnotateReturn);
         }
         if(this.ReturnType.Trim() != "" && this.ReturnType.Trim() != "void") {
            node.Push(nodeRetrun);
         }
         FXmlNode resource = new FXmlNode("Source");
         resource.Text = MethodCode;
         node.Push(resource);
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
            if(_returnType != "" && _returnType != "void" && AnnotateReturn == "") {
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
         string errID = "CS06PA03A" + line + "L";
         errID = errID.PadRight(16);
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "] "+"可能缺少T注释或者返回值注释！！";
         swPrint.WriteLine(error);
      }
   }
}

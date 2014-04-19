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
   // <T>属于类的解析，提取，生成XML的一系列操作。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FCsClass : FCrHelpNode
   {
      //方法
      protected FArray<FCsMethod> _method;

      //类
      protected FArray<FCsClass> _class = new FArray<FCsClass>();

      //属性
      protected FArray<FCsProperty> _property = new FArray<FCsProperty>();

      //字段
      protected FArray<FCsField> _field = new FArray<FCsField>();

      //可访问等级
      protected string _accessLevel;

      //类名
      protected string _className;

      //父类
      protected string _parentName = string.Empty;

      //注释
      protected FStrings _annotates;

      //所属字符串集合
      protected FStrings _strLine;

      //类的代码
      protected string _classCode = string.Empty;

      //描述
      protected string _description = string.Empty;

      //功能概述
      protected string _label = string.Empty;

      //开始和结束行索引
      protected int _startIndex, _endIndex;

      #region 字段对应的属性

      //============================================================
      // <T>所属字符串行集合。</T>
      //============================================================
      public  FStrings StrLine {
         get { return _strLine; }
         set { _strLine = value; }
      }

      //============================================================
      // <T>开始行索引。</T>
      //============================================================
      public int BeginIndex {
         get { return _startIndex; }
         set { _startIndex = value; }
      }

      //============================================================
      // <T>结束行索引。</T>
      //============================================================
      public int EndIndex {
         get { return _endIndex; }
         set { _endIndex = value; }
      }

      //============================================================
      // <T>字段。</T>
      //============================================================
      public FArray<FCsField> Field {
         get { return _field; }
         set { _field = value; }
      }

      //============================================================
      // <T>属性。</T>
      //============================================================
      public FArray<FCsProperty> Property {
         get { return _property; }
         set { _property = value; }
      }
      //============================================================
      // <T>功能性概述。</T>
      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      // <T>描述。</T>
      //============================================================
      public string Description {
         get { return _description; }
         set { _description = value; }
      }

      //============================================================
      // <T>类。</T>
      //============================================================
      public FArray<FCsClass> Class {
         get { return this._class; }
         set { this._class = value; }
      }

      //============================================================
      // <T>可访问等级。</T>
      //============================================================
      public string AccessLevel {
         get {
            int nlength = this._strLine[_startIndex].Trim().IndexOf(" ");
            return this._strLine[_startIndex].Trim().Substring(0, nlength).Trim();
         }
      }

      //============================================================
      // <T>注释。</T>
      //============================================================
      protected FStrings Annotates {
         get {
            return _annotates;
         }
         set {
            _annotates = value;
         }
      }

      //============================================================
      // <T>类名。</T>
      //============================================================
      public string ClassName {
         get {
            string line = this._strLine[_startIndex].Trim();
            if(line.Contains('{')) {
               int index = line.IndexOf('{');
               line = line.Substring(0, index);
            }
            int nstart = line.IndexOf("class") + 6;
            if(line.Contains(':')) {
               int nindex = line.IndexOf(":");
               return line.Substring(nstart, nindex - nstart).Trim();
            }
            return line.Substring(nstart).Trim();
         }
      }

      //============================================================
      // <T>类代码。</T>
      //============================================================
      public string ClassCode {
         get {
            string strTemp = _strLine[_startIndex].ToString();
            string strTrim = strTemp.TrimStart();
            int indent = strTemp.Length - strTemp.Length;
            for(int n = 0; n <= _endIndex; n++) {
               if(strTemp.Length >= indent) {
                  _classCode += _strLine[n].ToString().Substring(indent).TrimEnd() + "\r\n";
               } else {
                  _classCode += _strLine[n].ToString().TrimEnd() + "\r\n";
               }
            }
            return _classCode;
         }
      }

      //============================================================
      // <T>父类类名。</T>
      //============================================================
      public string ParentsName {
         get {
            if(_parentName != string.Empty) { return _parentName; }
            string line = this._strLine[_startIndex].Trim();
            if(line.Contains(':')) {
               int nindex = line.IndexOf(":");
               string parent = line.Substring(nindex + 1);
               if(!parent.Trim().StartsWith("I")) {
                  _parentName = ScanComma(parent);
                  string[] parents = _parentName.Split(new string[] { "$," }, StringSplitOptions.RemoveEmptyEntries);
                  foreach(string str in parents) {
                     if(!str.Trim().StartsWith("I")) {
                        _parentName = str.Trim();
                        return _parentName;
                     }
                  }
                  _parentName = parent.Trim();
                  return _parentName;
               } else { return _parentName; }
            } else {
               return _parentName;
            }
         }
         set { _parentName = value; }
      }

      //============================================================
      // <T>接口。</T>
      //============================================================
      public FArray<string> Interface {
         get {
            string line = this._strLine[_startIndex].Trim();
            if(line.Contains(':')) {
               int nindex = line.IndexOf(":");
               int endindex = line.Length;
               if(line.Contains('{')) { endindex = line.IndexOf('{'); }
               string parent = line.Substring(nindex + 1,endindex-nindex-1).Trim();
               parent = ScanComma(parent);
               string[] parents = parent.Split(new string[] { "$," }, StringSplitOptions.RemoveEmptyEntries);
               FArray<string> inherts = new FArray<string>();
               foreach(string str in parents) {
                  if(str.Trim().StartsWith("I")) {
                     inherts.Add(str.Trim());
                  }
               }
               return inherts;
            }
            return null;
         }
      }

      //============================================================
      // <T>类全名。</T>
      //============================================================
      public string AuthorInfo {
         get {
            FStrings annos = GetAnnotate(_strLine, _startIndex);
            for(int n = 0; n < _strLine.Count; n++) {
               if(_strLine[n].Contains("@author")) {
                  int index = _strLine[n].Trim().IndexOf("@author") + 8;
                  return _strLine[n].Trim().Substring(index).Trim();
               }
            }
            return string.Empty;
         }
      }

      //============================================================
      // <T>类全名。</T>
      //============================================================
      public string FullClassName {
         get {
            return AccessLevel + " class " + ClassName;
         }
      }

      //============================================================
      // <T>方法。</T>
      //============================================================
      public FArray<FCsMethod> Method {
         get {
            return this._method;
         }
      }

      #endregion

      //============================================================
      // <T>构造函数</T>
      //============================================================
      public FCsClass() {
         _accessLevel = string.Empty;
         _className = string.Empty;
         _annotates = new FStrings();
         _method = new FArray<FCsMethod>();
      }

      //============================================================
      // <T>构造函数</T>
      //============================================================R
      public FCsClass(FStrings strLine, int startIndex, int endIndex) {
         _accessLevel = string.Empty;
         _className = string.Empty;
         _annotates = new FStrings();
         _method = new FArray<FCsMethod>();
         _strLine = strLine;
         _startIndex = startIndex;
         _endIndex = endIndex;
      }

      //============================================================
      // <T>获取类。</T>
      // @parame strline 需要获取的字符串集合。
      // @param index 当前索引
      // @return 获取的类对象
      //============================================================
      public void ParserClass(FStrings strLine, FCsSpace space, int index, string path, FClassesIndexTable indextable, StreamWriter swPrint, FileInfo file, string relative) {
         FXmlDocument doc = new FXmlDocument();
         FXmlNode config = doc.Root;
         FXmlNode nodeSpace = space.XMLMaker(config);
         FXmlNode xmlClass = XMLMaker(nodeSpace,indextable,swPrint,file,relative);
         FXmlNode fields=new FXmlNode("Fields");
         FXmlNode properties=new FXmlNode("Properties");
         FXmlNode methods = new FXmlNode("Methods");
         MakeInterfaceNode(this.Interface, xmlClass, indextable);
         xmlClass.Push(fields);
         xmlClass.Push(properties);
         xmlClass.Push(methods);
         int start, end = CheckParaAnnotate(strLine, index, out start);
         for(int n = _startIndex; n < _endIndex; n++) {
            if(IsInInterregional(n, start, end)) { continue; }
            if(IsField(strLine[n])) {
               FCsField field = new FCsField(strLine, n);
               field.Parser(fields, indextable,swPrint,file,relative);
            }
            if(IsProperty(strLine[n])) {
               FCsProperty property = new FCsProperty(strLine, n, GetPairNum(strLine, n));
               property.ParserPorperty(properties, indextable, swPrint, file, relative);
            }
            if(IsMethod(strLine[n])) {
               int endIndex = GetPairNum(strLine, n);
               FCsMethod method = new FCsMethod();
               method.FillValue(strLine, n, endIndex);
               method.ParserMethod(strLine, n, methods, indextable, swPrint, file, relative);
               this.Method.Add(method);
            }
            if(FCsSpace.IsClass(strLine[n + 1])) {
               SaveSXMLFile(path, doc);
               FillValue(strLine, n + 1, GetPairNum(strLine, n + 1));
               ParserClass(strLine, space, n + 1, path, indextable, swPrint, file, relative);
            }
         }
         SaveSXMLFile(path, doc);
      }

      //============================================================
      // <T>解析类对象，并返回类名。</T>
      //
      // @param strLines 解析的字符串集合
      // @param index 索引行号
      //============================================================
      public static string ParserClass(FStrings strLines, int index) {
         string strTemp = strLines[index].ToString().Trim();
         int start = strTemp.IndexOf("class") + 6;
         int end = strTemp.Length;
         if(strTemp.Contains('{')) { end = strTemp.IndexOf('{'); }
         if(strTemp.Contains(':')) { end = strTemp.IndexOf(':'); }
         strTemp = strTemp.Substring(start, end - start).Trim();
         return strTemp;
      }

      //============================================================
      // <T>保存为XML文件。</T>
      //
      // @param path 需要保存的路径
      // @param doc XML文件节点
      //============================================================
      public void SaveSXMLFile(string path, FXmlDocument doc) {
         string filename = this.ClassName;
         if(filename.Contains("<")) { filename = filename.Replace('<', '('); }
         if(filename.Contains(">")) { filename = filename.Replace('>', ')'); }
         doc.SaveFile(path + "." + filename + ".xml");
      }

      //============================================================
      // <T>产生doc节点。</T>
      //
      // @param space 命名空间对象
      // @return 根节点
      //============================================================
      public FXmlNode RootSpaceNode(FCsSpace space) {
         FXmlDocument doc = new FXmlDocument();
         FXmlNode config = doc.Root;
         FXmlNode nodeSpace = space.XMLMaker(config);
         return nodeSpace;
      }


      //============================================================
      // <T>判断该字符串行是否为方法。</T>
      //
      // @param strLine 需要检测的行
      // @return true为方法，false不是方法
      //============================================================
      public bool IsMethod(string strLine) {
         string s = strLine.Trim();
         int index = strLine.Trim().IndexOf(" ");
         string accessClassification;
         if(index >= 0) {
            accessClassification = s.Substring(0, index);
            FStrings accessClass = new FStrings() { "public", "protected", "private", "partial", "internal" };
            foreach(string str in accessClass) {
               if((accessClassification == str) && strLine.Contains("(") && strLine.Contains(")") && !strLine.Contains('='))
                  return true;
            }
         }
         return false;
      }

      //============================================================
      // <T>判断是否为字段。</T>
      //
      // @param strLine 字符串
      // @return true 表示为字段，反之不是
      //============================================================
      public bool IsField(string strLine) {
         string s = strLine.Trim();
         int index = strLine.Trim().IndexOf(" ");
         string accessClassification;
         if(index >= 0) {
            accessClassification = s.Substring(0, index);
            FStrings accessClass = new FStrings() { "public", "protected", "private", "partial", "internal" };
            foreach(string str in accessClass) {
               if((accessClassification == str) && strLine.Contains(";"))
                  return true;
            }
         }
         return false;
      }

      //============================================================
      // <T>判断是否为属性。</T>
      //
      // @param strLine 字符串
      // @return true 表示为属性，反之不是
      //============================================================
      public bool IsProperty(string strLine) {
         string s = strLine.Trim();
         int index = strLine.Trim().IndexOf(" ");
         string accessClassification;
         if(index >= 0) {
            accessClassification = s.Substring(0, index);
            FStrings accessClass = new FStrings() { "public", "protected", "private", "partial", "internal" };
            foreach(string str in accessClass) {
               if((accessClassification == str) && !strLine.Contains("(") && !strLine.Contains('=')&&!strLine.Contains(" class ")&&!strLine.Contains(";"))
                  return true;
            }
         }
         return false;
      }


      //============================================================
      // <T>向对象传入相应的值。</T>
      // @param strLine 需要传入的字符串行。也就是解析文件所得的各行
      // @param beginLine 属于该对象的行起始行索引
      // @param endLine 属于该对象的行终止索引
      //============================================================
      public void FillValue(FStrings strLine, int startIndex, int endIndex) {
         _strLine = strLine;
         _startIndex = startIndex;
         _endIndex = endIndex;
         FStrings strs = GetAnnotate(_strLine, _startIndex);
         Annotates = strs;
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
      public FXmlNode XMLMaker(FXmlNode node, FClassesIndexTable indextable, StreamWriter swPrint, FileInfo file, string relative) {
         if(!IsClassAnnoFine()) {
            PrintAnnoWarning(swPrint, file, relative);
         }
         FXmlNode classNode = new FXmlNode("Class");
         classNode.Set("name", this.ClassName);
         if(this.AccessLevel != "") {
            classNode.Set("access_level", this.AccessLevel);
         }
         if(this.Annotates != null && Annotates.Count > 0) {
            ParserClassAnnotate();
            if(Label.Trim() != "") { classNode.Set("label", Label.Trim()); }
            if(Description.Trim() != "") { classNode.Set("description", Description.Trim()); }
         }
         if(this.ParentsName != string.Empty) {
            FFileNode type = new FFileNode(this.ParentsName.Trim(), GetUsing(_strLine));
            this.ParentsName = indextable.LookInMap(type, this._parentName.Trim());
            classNode.Set("parent_class", ParentsName.Trim());
         }
         FXmlNode classCode = new FXmlNode("Source");
         classCode.Text = ClassCode;
         classNode.Push(classCode);
         node.Push(classNode);
         return classNode;
      }

      //============================================================
      // <T>解析类的注释。</T>
      //============================================================
      public void ParserClassAnnotate() {
         FStrings description = new FStrings();
         for(int n = 0; n < Annotates.Count; n++) {
            string strtemp = Annotates[n].ToString().Trim();
            if(strtemp.Contains("</T>")) {
               strtemp = strtemp.Replace("</T>", "");
               if(strtemp.Contains("<T>")) { strtemp = strtemp.Substring(strtemp.IndexOf("<T>") + 3).Trim(); }
               Label = strtemp.Trim();
            }
            if(strtemp.Contains("</P>")) {
               strtemp = strtemp.Replace("</P>", "");
               if(strtemp.Contains("<P>")) { strtemp = strtemp.Substring(strtemp.IndexOf("<P>") + 3).Trim(); }
               description.Add(strtemp);
            }
         }
         if(description != null && description.Count > 0) {
            for(int n = 0; n < description.Count; n++) {
               Description += description[n].ToString().Trim();
            }
         }
      }

      //============================================================
      //<T>将类所继承的接口作为子节点连接到类的节点下面</T>
      // @param interface 接口的字符串组
      // @param nodeClass 要连接的父节点，也就是类的节点
      //============================================================
      public void MakeInterfaceNode(FArray<string> interfaces, FXmlNode nodeClass,FClassesIndexTable indextable) {
         if(interfaces == null || interfaces.Length < 1) {
            return;
         }
         FXmlNode nodeInterface = new FXmlNode("Inherts");
         foreach(string str in interfaces) {
            FXmlNode inhert = new FXmlNode("Inhert");
            string inhertstr=str.Trim();
            FFileNode type = new FFileNode(inhertstr, GetUsing(_strLine));
            inhertstr= indextable.LookInMap(type, inhertstr);
            inhert.Set("name", inhertstr.Trim());
            nodeInterface.Push(inhert);
         }
         nodeClass.Push(nodeInterface);
      }

      //============================================================
      // <T>判断类的注释是否齐全或者符合规范。</T>
      //
      // @return true 表示符合规范，否则不符合规范。
      //============================================================
      public bool IsClassAnnoFine() {
         if(Description != ""&&AuthorInfo!="") {
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
         string line = _startIndex.ToString();
         string errID = "CS02C03A" + line + "L";
         errID = errID.PadRight(16);
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "] " + "可能缺少T注释或者author注释！！";
         swPrint.WriteLine(error);
      }
   }
}

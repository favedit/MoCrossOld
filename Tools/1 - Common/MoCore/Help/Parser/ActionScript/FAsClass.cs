using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Core.Help.Common;
using MO.Common.Lang;
using MO.Common.Collection;
using MO.Common.Content;
using System.IO;

namespace MO.Core.Help.Parser.ActionScript
{
   //============================================================
   // <T>有关类的判断，提取，生成，解析等操作。</T>
   //
   // @author CJJUN 120331
   //============================================================
   public class FAsClass:FCrHelpNode
   {
      //方法对象
      protected FArray<FAsMethod> _method=new FArray<FAsMethod>();

      //访问级别
      protected string _accessLevel=string.Empty;

      //类名
      protected string _className=string.Empty;

      //父类名称
      protected string _parentName = string.Empty;

      //注释信息
      protected FStrings _annotates=new FStrings();

      //类对象所在的字符串行集合
      protected FStrings _strLine=new FStrings();

      //类所包含的其他包
      protected string _importString = string.Empty;

      //类的代码
      protected string _classCode = string.Empty;

      //类的注释描述
      private string _description = string.Empty;

      //类的功能性描述
      private string _label = string.Empty;

      //类对象所在字符串集合的行开始和结束行索引
      protected int _startIndex=-1, _endIndex=-1;

      #region 字段对应的属性

      //============================================================
      // <T>所属类的方法对象。</T>
      //============================================================
      public FArray<FAsMethod> Method {
         get { return this._method; }
         set { this._method = value; }
      }

      //============================================================
      // <T>功能新描述。</T>
      //============================================================
      protected string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      // <T>描述信息（注释）。</T>
      //============================================================
      protected string Description {
         get { return _description; }
         set { _description = value; }
      }

      //============================================================
      // <T>访问级别。</T>
      //============================================================
      public string AccessLevel {
         get {
            int nlength = this._strLine[_startIndex].Trim().IndexOf(" ");
            return this._strLine[_startIndex].Trim().Substring(0, nlength).Trim();
         }
      }

      //============================================================
      // <T>注释信息。</T>
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
            string line = this._strLine[_startIndex].ToString().Trim();
            if(line.Contains('{')) {
               int index = line.IndexOf('{');
               line = line.Substring(0, index);
            }
            int nstart = line.IndexOf("class") + 6;
            if(line.Contains("extends")) {
               int nindex = line.IndexOf("extends");
               return line.Substring(nstart, nindex - nstart).Trim();
            }
            return line.Substring(nstart).Trim();
         }
      }

      //============================================================
      // <T>父类。</T>
      //============================================================
      public string ParentName {
         get {
            string line = _strLine[_startIndex].ToString().Trim();
            if(line.Contains('{')) {
               int index = line.IndexOf('{');
               line = line.Substring(0, index);
            }
            if(line.Contains("extends")) {
               int nindex = line.IndexOf("extends")+8;
               if(line.Contains("implements")) {
                  int nEnd = line.IndexOf("implements");
                  _parentName= line.Substring(nindex, nEnd - nindex).Trim();
                  return _parentName;
               }
               _parentName= line.Substring(nindex).Trim();
               return _parentName;
            }
            return _parentName;
         }
      }

      //============================================================
      // <T>接口。</T>
      //============================================================
      public FStrings InterfaceStrings {
         get {
            string line = _strLine[_startIndex].ToString().Trim();
            if(line.Contains('{')){line=line.Substring(0,line.IndexOf('{'));}
            if(line.Contains("implements")) {
               line = line.Substring(line.IndexOf("implements") + 11);
               FStrings faces = new FStrings();
               string[] fa = line.Split(new char[]{','}, StringSplitOptions.RemoveEmptyEntries);
               foreach(string s in fa) {
                  if(s.Trim() != "") { faces.Add(s); }
               }
               return faces;
            }
            return null;
         }
      }

      //============================================================
      // <T>类对象的代码。</T>
      //============================================================
      public string ClassCode {
         get {
            string strTemp = _strLine[_startIndex].ToString();
            string strTrim = strTemp.TrimStart();
            int indent = strTemp.Length - strTrim.Length;
            for(int n = _startIndex; n <= _endIndex; n++) {
               strTemp = _strLine[n].ToString().TrimEnd();
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
      // <T>作者信息。</T>
      //============================================================
      public string AuthorInfo {
         get {
            FStrings annotates = GetAnnotate(_strLine, _startIndex);
            string author = string.Empty;
            foreach(string str in annotates) {
               if(str.Contains("@author")) {
                  int index = str.IndexOf("@author") + 8;
                  author = str.Substring(index);
               }
            }
            return author;
         }
      }
      #endregion 

      //============================================================
      // <T>构造方法。</T>
      //============================================================
      public FAsClass() { }

      //============================================================
      // <T>构造方法，有参数的构造。</T>
      //
      // @param strLines 解析的代码行集合
      // @param begin 开始行索引
      // @param end 结束行索引
      //============================================================
      public FAsClass(FStrings strLines, int begin, int end) {
         this._strLine = strLines;
         this._startIndex = begin;
         this._endIndex = end;
         _annotates = GetAnnotate(_strLine, _startIndex);
      }

      //============================================================
      // <T>判断该字符串行是否为类。</T>
      // @param strLine 需要检测的行
      // @param index 当前判断行的行号索引
      // @return true为类，false不是类
      //============================================================
      public static bool IsClass(FStrings strLines, int index) {
         string strTemp=strLines[index].ToString();
         if(strTemp.Contains(" class ") && !strTemp.Contains("\"")) {
            return true;
         } else {
            return false;
         }
      }

      //============================================================
      // <T>解析类。</T>
      //
      // @param indextable 类全名的索引表
      // @param path 解析结果的输出路径
      // @param swPrint 用于打印警告结果的StreamWriter对象
      // @param relative 当前解析文件所在的目录的路径
      // @param package 类所在包的包对象
      // @param file 当前解析的文件
      //============================================================
      public void ParserClass(FAsClassesIndexTable indextable,FAsPackage package,string path,StreamWriter swPrint,FileInfo file,string relative) {
         FXmlDocument doc = new FXmlDocument();
         FXmlNode config = doc.Root;
         FXmlNode nodePackage = package.XMLMaker(config);
         FXmlNode xmlClass = XMLMaker(nodePackage, indextable);
         MakeInterfaceNode( xmlClass, indextable);
         FXmlNode methods=new FXmlNode("Methods");
         FXmlNode fields=new FXmlNode("Fields");
         FXmlNode properties=new FXmlNode("Properties");
         xmlClass.Push(fields);
         xmlClass.Push(properties);
         xmlClass.Push(methods);
         //判断并得到段落注释的行号区间
         int start, end = CheckParaAnnotate(_strLine, _startIndex, out start);
         for(int n = _startIndex; n < _endIndex; n++) {
            //如果在区间中则推出当前循环继续执行
            if(IsInInterregional(n, start, end)) { continue; }
            if(FAsField.IsField(_strLine, n)) {
               FAsField field = new FAsField(_strLine, n);
               field.Parser(fields, indextable,swPrint,file,relative);
            }
            if(FAsProperty.IsProperty(_strLine, n)) {
               int endindex=GetPairNum(_strLine, n);
               FAsProperty property = new FAsProperty(_strLine, n,endindex );
               property.ParserPorperty(properties, indextable,swPrint,file,relative);
            }
            if(FAsMethod.IsMethod(_strLine, n)) {  
               int methodEnd=GetPairNum(_strLine, n);
               FAsMethod asMethod = new FAsMethod(_strLine, n,methodEnd );
               asMethod.ParserMethod(methods,indextable,swPrint,file,relative);
               this.Method.Add(asMethod);
            }
         }
         SaveXMLFile(doc,path);
      }

      //============================================================
      // <T>解析类对象。</T>
      //
      // @param strLines 类所在的行集合
      // @param index 所在的行索引
      // @return 获得的类名
      //============================================================
      public static string ParserClass(FStrings strLines, int index) {
         string strTemp = strLines[index].ToString().Trim();
         int start = strTemp.IndexOf("class") + 6;
         int end = strTemp.Length;
         if(strTemp.Contains('{')) { end = strTemp.IndexOf('{'); }
         if(strTemp.Contains("extends")) { end = strTemp.IndexOf("extends"); }
         strTemp = strTemp.Substring(start, end - start).Trim();
         return strTemp;
      }

      //============================================================
      // <T>解析类对象的注释信息。</T>
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
      // <T>储存为XML文件。</T>
      //
      // @param doc 需要存储的对象
      // @param path 解析结果的输出路径
      //============================================================
      public void SaveXMLFile( FXmlDocument doc,string path) {
         string filename = this.ClassName;
         if(filename.Contains("<")) { filename = filename.Replace('<', '('); }
         if(filename.Contains(">")) { filename = filename.Replace('>', ')'); }
         doc.SaveFile(path + "." + filename + ".xml");
      }

      //============================================================
      // <T>建立输出XML文件的根节点。</T>
      //
      // @param package 当前类所在包的包对象
      // @return 包含包对象的根节点
      //============================================================
      public FXmlNode RootPackageNode(FAsPackage package) {
         FXmlDocument doc = new FXmlDocument();
         FXmlNode config = doc.Root;
         FXmlNode nodeSpace = package.XMLMaker(config);
         return nodeSpace;
      }

      //============================================================
      // <T>生成节点时，将此对象生成的节点连接到上层节点上</T>
      // @param node 需要连接的父节点。
      // @return 本对象生成的节点。
      //============================================================
      public FXmlNode XMLMaker(FXmlNode node, FAsClassesIndexTable indextable) {
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
         if(this.ParentName != string.Empty) {
            FFileNode type = new FFileNode(this.ParentName.Trim(), GetUsing(_strLine));
            string parent = indextable.LookInMap(type, this._parentName.Trim());
            classNode.Set("parent_class", parent);
         }
         FXmlNode classCode = new FXmlNode("Source");
         classCode.Text = ClassCode;
         classNode.Push(classCode);
         node.Push(classNode);
         return classNode;
      }

      //============================================================
      //<T>将类所继承的接口作为子节点连接到类的节点下面</T>
      // @param interface 接口的字符串组
      // @param nodeClass 要连接的父节点，也就是类的节点
      //============================================================
      public void MakeInterfaceNode( FXmlNode nodeClass, FAsClassesIndexTable indextable) {
         if(InterfaceStrings == null || InterfaceStrings.Count < 1) {
            return;
         }
         FXmlNode nodeInterface = new FXmlNode("Inherts");
         foreach(string str in InterfaceStrings) {
            FXmlNode inhert = new FXmlNode("Inhert");
            string inhertstr = str.Trim();
            FFileNode type = new FFileNode(inhertstr, GetUsing(_strLine));
            inhertstr = indextable.LookInMap(type, inhertstr);
            inhert.Set("name", inhertstr.Trim());
            nodeInterface.Push(inhert);
         }
         nodeClass.Push(nodeInterface);
      }

      //============================================================
      // <T>判断函数的返回值是否正常。</T>
      //
      // @return true 表示返回值正常，反之，false表示不正常
      //============================================================
      public bool IsClassAnnoFine() {
         if(Label == "") {
            return false;
         }
         if(AuthorInfo == "") {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>类代码段中没有T或者Author注释。</T>
      //
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路经
      //============================================================
      public void PrintError(StreamWriter swPrint, FileInfo file, string relative) {
         string tips = "缺少T注释或者author注释！！";
         string line = _startIndex.ToString();
         //line = line.PadLeft(5, '0');
         string errID = "CPP02C03A" + line + "L";
         errID = errID.PadRight(16);
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "] " + tips;
         swPrint.WriteLine(error);
      }
   }
}

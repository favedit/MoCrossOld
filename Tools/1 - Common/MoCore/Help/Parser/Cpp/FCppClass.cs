using System;
using System.Linq;
using MO.Common.Lang;
using MO.Common.Content;
using MO.Common.Collection;
using MO.Core.Help.Common;
using System.IO;

namespace MO.Core.Help.Parser.Cpp
{
   //============================================================
   // <T>Cpp文件中的类对象的描述，提取，以及解析操作。</T>
   //
   // @author CJJUN 120329
   //============================================================
   public class FCppClass:FCrHelpNode
   {
      //从文件解析出的代码行集合
      protected FStrings _strLines;

      //类对象代码段的开始索引行号
      protected int _beginIndex = -1;

      //类对象代码段的结束索引行号
      protected int _endInde = -1;

      //属于类对象的字段
      protected FArray<FCppField> _field = new FArray<FCppField>();

      //属于类对象的方法
      protected FArray<FCppMethod> _method = new FArray<FCppMethod>();

      //============================================================
      // <T>字段。</T>
      //============================================================
      public  FArray<FCppField> Field {
         get { return _field; }
         set { _field = value; }
      }

      //============================================================
      // <T>方法</T>
      //============================================================
      public FArray<FCppMethod> Method {
         get { return _method; }
         set { _method = value; }
      }

      //============================================================
      // <T>类名称。</T>
      //============================================================
      public string ClassName {
         get {
            string strTemp = _strLines[_beginIndex].Trim();
            int begin = strTemp.IndexOf("class") + 6;
            if(strTemp.Contains("MO_CM_DECLARE")) {
               begin = strTemp.IndexOf("MO_CM_DECLARE") + 13;
            }
            int end = strTemp.Length;
            if(strTemp.Contains('{')) {
               end = strTemp.IndexOf('{');
            }
            if(strTemp.Contains(':')) {
               end = strTemp.IndexOf(':');
            }
            strTemp = strTemp.Substring(begin, end - begin).Trim();
            return strTemp;
         }
      }

      //============================================================
      // <T>父类。</T>
      //============================================================
      public FStrings Parent {
         get {
            string strTemp = _strLines[_beginIndex].Trim();
            for(int n=_beginIndex;n<=_endInde;n++){
               string line = string.Empty;
               if(_strLines[n].Trim().Contains('{')) {
                  int index = _strLines[n].Trim().IndexOf('{');
                  line = _strLines[n].Trim().Substring(0, index);
                  strTemp += line;
                  break;
               }
               strTemp += _strLines[n].Trim();
            }
            int begin = 0;
            if(!strTemp.Contains(':')) {
               return null;
            } else {
               begin = strTemp.IndexOf(':') + 1;
            }
            int end=strTemp.Length;
            if(strTemp.Contains('{')) {
               end = strTemp.IndexOf('{');
            }
            strTemp = strTemp.Substring(begin, end - begin).Trim();
            strTemp = ScanComma(strTemp);
            FStrings parents = new FStrings();
            string[] s = strTemp.Split(new string[] { "$," }, StringSplitOptions.None);
            if(s.Length > 0 && s[0].Trim() != "") {
               for(int n = 0; n < s.Length; n++) {
                  parents.Add(s[n].Trim());
               }
            }
            return parents;
         }
      }

      //============================================================
      // <T>类代码的T注释。</T>
      //============================================================
      public string Label {
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
      // <T>类代码的P注释。</T>
      //============================================================
      public string Description {
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
      // <T>类代码的Author注释。</T>
      //============================================================
      public string AuthorInfo {
         get {
            FStrings annotates = GetAnnotate(_strLines, _beginIndex);
            string author = string.Empty;
            foreach(string str in annotates) {
               if(str.Contains("@author")) { 
                  int index=str.IndexOf("@author")+8;
                  author = str.Substring(index);
               }
            }
            return author;
         }
      }

      //============================================================
      // <T>构造方法。</T>
      //============================================================
      public FCppClass() { }

      //============================================================
      // <T>构造方法。</T>
      //
      // @param strLines 正在解析的代码行集合
      // @param begin 方法代码段的开始索引行号
      // @param end 方法代码段的结束索引行号
      //============================================================
      public FCppClass(FStrings strLines, int begin, int end) {
         this._strLines = strLines;
         this._beginIndex = begin;
         this._endInde = end;
      }

      //============================================================
      // <T>判断是否为Class对象。</T>
      //
      // @param strLines 正在判断的所在字符串集合 
      // @param index 正在判断的当前行
      //============================================================
      public static bool IsClass(FStrings strLines, int index) {
         string str = strLines[index].Trim();
         if(str.StartsWith("class ")&&!str.Contains(";")) {
            return true;
         }
         return false;
      }

      //============================================================
      // <T>将类对象对象解析成XML文档，并解析出代码在编译器外的警告或者错误。</T>
      // 
      // @param swPrint StringWriter对象用来输出警告或者错误。
      // @param file 当前解析的文件
      // @param relative 被选择解析的文件夹路径
      //============================================================
      public FXmlNode XMLMaker(StreamWriter swPrint, FileInfo file,string relative) {
         if(!IsClassAnnoFine()) {
            PrintError(swPrint, file,relative);
         }
         FXmlNode nodeClass = new FXmlNode("Class");
         nodeClass.Set("name", ClassName);
         FXmlNode parents = new FXmlNode("Parents");
         if(Parent != null && Parent.Count != 0) {
            for(int n = 0; n < Parent.Count; n++) {
               FXmlNode parent = new FXmlNode("Parent");
               string[] pa = Parent[n].Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
               parent.Set("name", pa[1].Trim());
               parent.Set("extends_type", pa[0].Trim());
               parents.Push(parent);
            }
         }
         string classCode = GetClassCode(this);
         FXmlNode code = new FXmlNode("Source");
         code.Text = classCode;
         nodeClass.Push(code);
         FXmlNode fields = new FXmlNode("Fields");
         if(_field != null && _field.Length != 0) {
            for(int i = 0; i < _field.Length; i++) {
               _field[i].Parser(fields, swPrint, file,relative);
            }
         }
         nodeClass.Push(parents);
         nodeClass.Push(fields);
         return nodeClass;
      }

      //============================================================
      // <T>获取类对象的代码段。</T>
      // 
      // @param cppClass 类对象
      // @return 返回类对象的字符串
      //============================================================
      public string GetClassCode(FCppClass cppClass) {
         string code = string.Empty;
         if(cppClass == null) {
            return code;
         }
         for(int n = cppClass._beginIndex; n <= cppClass._endInde; n++) {
            code += cppClass._strLines[n].TrimEnd() + "\r\n";
         }
         if(cppClass.Method == null || cppClass.Method.Length == 0) {
            return code;
         }
         code += cppClass.ClassName + ".CPP\r\n";
         for(int n = 0; n < cppClass.Method.Length; n++) {
            code += cppClass.Method[n].MethodCode + "\r\n";
         }
         return code;
      }

      //============================================================
      // <T>判断是否为模板类。</T>
      // 
      // @param strLines 正在判断的所在字符串集合 
      // @param index 正在判断的当前行
      // @return true表示为模板类，反之不是
      //============================================================
      public static bool IsTemplateClass(FStrings strLines, int index) {
         string strTemp = strLines[index].Trim();
         if(strTemp.Contains(" class ")) {
            if(strTemp.StartsWith("Template") || strLines[index - 1].Trim().StartsWith("class ")) {
               return true;
            }
            return false;
         }
         return false;
      }


      public void ParserTemplateClass() {
         for(int n = _beginIndex; n <= _endInde; n++) {
            if(true){}
         }
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
      public void PrintError(StreamWriter swPrint,FileInfo file,string relative) {
         string tips = "缺少T注释或者author注释！！";
         string line = _beginIndex.ToString();
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

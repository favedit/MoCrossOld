using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Collection;
using MO.Common.Lang;
using MO.Core.Help.Common;
using MO.Common.Content;
using System.IO;

namespace MO.Core.Help.Parser.CSharp
{
   //============================================================
   // <T>有关属性对象的解析，提取，生成操作。</T>
   //
   // @author CJJUN 120330
   //============================================================
   public class FCsProperty:FCrHelpNode
   {
      //属性类型
      protected string _propertyType=string.Empty;

      //属性名
      protected string _propertyName = string.Empty;

      //属性的注释
      protected FStrings _annotates;

      //属性所在代码行
      protected FStrings _strLine;

      //属性的代码
      protected string _propertyCode = string.Empty;

      //属性的开始和结束行索引
      protected int _beginIndex,_endIndex;

      

      #region 各个字段对应的属性

      //============================================================
      // <T>构造名</T>
      //============================================================
      public string PropertyName {
         get {
            string strTemp = _strLine[_beginIndex].Trim();
            int eindex = strTemp.Length;
            if(strTemp.Contains('{')) {
               eindex = strTemp.IndexOf('{');
            }
            if(strTemp.Contains(';')) {
               eindex = strTemp.IndexOf(';');
            }
            strTemp = strTemp.Substring(0, eindex).Trim();
            int sindex = strTemp.LastIndexOf(" ");
            string str = strTemp.Substring(sindex).Trim();
            return str.Trim();
         }
      }

      //============================================================
      // <T>属性的类型</T>
      //============================================================
      public string PropertyType {
         get {
            string strTemp = _strLine[_beginIndex].Trim();
            int eindex=_strLine[_beginIndex].Trim().LastIndexOf(this.PropertyName);
            strTemp = strTemp.Substring(0, eindex).Trim();
            int sindex=strTemp.LastIndexOf(' ');
            strTemp=strTemp.Substring(sindex).Trim();
            return strTemp;
         }
      }


      //============================================================
      // <T>属性功能的描述</T>
      //============================================================
      public string AnnotateLable {
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
      // <T>描述属性的注释。</T>
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
      // <T>可访问级别</T>
      //============================================================
      public string AccessLevel {
         get {
            string access_level = _strLine[_beginIndex].Trim();
            int index = access_level.IndexOf(' ');
            access_level = access_level.Substring(0, index).Trim();
            return access_level;
         }
      }

      //============================================================
      // <T>属于属性对象的代码段</T>
      //============================================================
      public string PropertyCode {
         get {
            string strTemp = _strLine[_beginIndex].ToString();
            int preIndex = strTemp.Length - strTemp.Trim().Length;
            for(int n = _beginIndex; n <= _endIndex; n++) {
               strTemp = string.Empty;
               if(_strLine[n].ToString().Length >= preIndex) {
                  strTemp = _strLine[n].ToString().Substring(preIndex).TrimEnd() + "\r\n";
               }
               _propertyCode += strTemp;
            }
            return _propertyCode;
         }
      }
      #endregion

      //============================================================
      // <T>构造方法</T>
      //============================================================
      public FCsProperty() {
         _propertyName = string.Empty;
         _propertyType = string.Empty;
         _annotates = new FStrings();
      }

      //============================================================
      // <T>构造方法</T>
      //============================================================
      public FCsProperty(FStrings strlines, int beginIndex, int endIndex) {
         _propertyName = string.Empty;
         _propertyType = string.Empty;
         _annotates = new FStrings();
         _strLine = strlines;
         _beginIndex = beginIndex;
         _endIndex = endIndex;
      }

      //============================================================
      // <T>解析属性。</T>
      //
      // @param nodeParent 属性节点的父节点
      // @param indextable 类型全名索引表
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路径
      //============================================================
      public void ParserPorperty(FXmlNode nodeParent, FClassesIndexTable indextable,StreamWriter swPrint,FileInfo file,string relative) {
         XMLMaker(nodeParent, indextable, swPrint, file, relative);
      }

      //============================================================
      // <T>生成XML节点，并附加到父节点上。</T>
      //
      // @param nodeParent 属性节点的父节点
      // @param indextable 类型全名索引表
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路径
      //============================================================
      public void XMLMaker(FXmlNode nodeParent, FClassesIndexTable indextable,StreamWriter swPrint,FileInfo file,string relative) {
         if(!IsProAnnoFine()) {
            PrintAnnoWarning(swPrint, file, relative);
         }
         FXmlNode nodeProperty = new FXmlNode("Property");
         nodeProperty.Set("name", this.PropertyName);
         nodeProperty.Set("type", this.PropertyType);
         FFileNode type = new FFileNode(this.PropertyType, GetUsing(_strLine));
         string fulltype = indextable.LookInMap(type, this.PropertyType);
         if(fulltype != this.PropertyType) { nodeProperty.Set("full_name", fulltype); }
         if(this.AnnotateLable!=""){nodeProperty.Set("label",AnnotateLable);}
         if(this.AnnotateDescription!=""){nodeProperty.Set("description",AnnotateDescription);}
         nodeProperty.Set("access_level", AccessLevel);
         FXmlNode code = new FXmlNode("Source");
         code.Text = this.PropertyCode;
         nodeProperty.Push(code);
         nodeParent.Push(nodeProperty);
      }

      //============================================================
      // <T>判断参数的注释是否齐全或者符合规范。</T>
      //
      // @return true 表示符合规范，否则不符合规范。
      //============================================================
      public bool IsProAnnoFine() {
         if(AnnotateDescription != "") {
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
         string line = _beginIndex.ToString();
         string errID = "CS04P03A" + line + "L";
         errID = errID.PadRight(16);
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "]";
         swPrint.WriteLine(error);
      }
   }
}

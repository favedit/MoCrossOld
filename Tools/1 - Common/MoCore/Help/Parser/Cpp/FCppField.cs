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
   // <T>Cpp文件中的字段对象的描述，提取，以及解析操作。</T>
   //
   // @author CJJUN 120329
   //============================================================
   public class FCppField
   {
      //方法所属的字符串数组
      protected FStrings _strLines;

      //属于字段的字符串数集合索引号
      protected int _index;

      //============================================================
      // <T>字段名。</T>
      //============================================================
      public string FieldName { 
         get{
            string strTemp = _strLines[_index].Trim();
            int end = strTemp.IndexOf(';');
            strTemp = strTemp.Substring(0, end);
            int begin = strTemp.LastIndexOf(' ');
            strTemp = strTemp.Substring(begin,strTemp.Length- begin).Trim();
            return strTemp;
         }
      }

      //============================================================
      // <T>字段类型。</T>
      //============================================================
      public string FieldType {
         get {
            string strTemp = _strLines[_index].Trim();
            int begin = strTemp.LastIndexOf(' ');
            strTemp = strTemp.Substring(0, begin);
            return strTemp;
         }
      }

      //============================================================
      // <T>字段的全名。</T>
      //============================================================
      public string FullFieldName {
         get {
            string strTemp = _strLines[_index].Trim();
            int end = strTemp.Length;
            if(strTemp.Contains(';')) {
               end = strTemp.IndexOf(';');
            }
            strTemp = strTemp.Substring(0, end);
            return strTemp;
         }
      }

      //============================================================
      // <T>字段的T注释</T>
      //============================================================
      public string Description {
         get {
            string strTemp = string.Empty;
            for(int n = _index; n > 0; n--) {
               if(_strLines[_index].Contains("//")) {
                  string str= _strLines[_index].Trim();
                  str = str.Replace("//", "");
                  strTemp += str;
               }
               if(!_strLines[_index].Trim().StartsWith("//")) {
                  return strTemp;
               }
            }
            return strTemp;
         }
      }

      //============================================================
      // <T>构造方法。</T>
      //============================================================
      public FCppField() { }

      //============================================================
      // <T>构造方法</T>
      //
      // @param strLines 方法所属的字符串数组
      // @param index 属于字段的字符串数集合索引号
      //============================================================
      public FCppField(FStrings strLines, int index) {
         _strLines = strLines;
         _index=index;
      }

      //============================================================
      // <T>判断正在解析的代码是类对象中的字段对象代码。</T>
      // 
      // @param strLines 正在解析的代码集合
      // @param index 当前解析的行号
      //============================================================
      public static bool IsField(FStrings strLines, int index) {
         string strTemp = strLines[index].Trim();
         if(!strTemp.Contains(';')) { return false; }
         if(strTemp.Contains('(')) { return false; }
         if(strTemp.Contains('=')) { return false; }
         if(strTemp.Contains(':')) { return false; }
         for(int n = index; n > 0; n--) {
            string temp = strLines[n].Trim();
            if((temp.Contains("public:") || temp.Contains("protected:") || temp.Contains("private:"))&&temp.Contains("{")) {
               return false;
            }
            if(temp.Contains("public:") || temp.Contains("protected:") || temp.Contains("private:")) {
               return true;
            }
            if(temp.Contains("{")) {
               return false;
            }
         }
         return true;
      }

      //============================================================
      // <T>解析字段代码。</T>
      // 
      // @param nodeParent 需要附加到的父节点
      // @param swPrint StringWriter对象用来输出警告或者错误。
      // @param file 当前解析的文件
      // @param relative 被选择解析的文件夹路径
      //============================================================
      public void Parser(FXmlNode nodeParent, StreamWriter swPrint, FileInfo file,string relative) {
         XMLMaker(nodeParent, swPrint, file,relative);
      }

      //============================================================
      // <T>解析字段代码。</T>
      // 
      // @param nodeParent 需要附加到的父节点
      // @param swPrint StringWriter对象用来输出警告或者错误。
      // @param file 当前解析的文件
      // @param relative 被选择解析的文件夹路径
      //============================================================
      public void XMLMaker(FXmlNode nodeParent, StreamWriter swPrint, FileInfo file,string relative) {
         if(!IsFieldAnnofine()) {
            PrintError(swPrint, file,relative);
         }
         FXmlNode nodeField = new FXmlNode("Field");
         nodeField.Set("name", FieldName);
         nodeField.Set("type", FieldType);
         nodeField.Set("full_name", FullFieldName);
         if(Description.Trim() != "") {
            nodeField.Set("description", Description);
         }
         nodeParent.Push(nodeField);
      }
      //============================================================
      // <T>解析字段的代码，看是否有不合规范注释。</T>
      //
      // @return true 表示正常，反之有Is异常
      //============================================================
      public bool IsFieldAnnofine() {
         if(Description == "") {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>字段缺少注释或者注释错误不合规范。</T>
      //
      // @param swPrint 用于打印警告的StreamWriter对象
      // @param file 警告所处在的文件
      // @param relative 被选取的文件夹的路径
      //============================================================
      public void PrintError(StreamWriter swPrint, FileInfo file,string relative) {
         string line = _index.ToString();
         //line = line.PadLeft(5, '0');
         string errID = "CPP03F03A" + line + "L";
         errID = errID.PadRight(16);
         string rpath = file.FullName.Substring(relative.Length);
         rpath += "(" + line + ")";
         rpath = rpath.PadRight(45);
         string error = "[警告][" + errID + "][" + rpath + "]";
         swPrint.WriteLine(error);
         
      }
   }
}

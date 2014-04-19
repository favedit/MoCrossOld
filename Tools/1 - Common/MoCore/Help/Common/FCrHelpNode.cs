using System.Linq;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.Collection;

namespace MO.Core.Help.Common
{
   public class FCrHelpNode
   {
      protected FCrHelpNodeCollection _nodes;

      //所需要查找的文件的后缀名
      protected const string _csFileType = "*.cs";

      protected const string _asFileType = "*.as";
      //分行符
      protected const string _enter = "\r\n";
      //段落注释规范
      protected const string _annotate = "//============================================================";

      protected string _savePath=string.Empty;

      public bool HasNode() {
         return (null != _nodes) ? !_nodes.IsEmpty() : false;
      }

      public FCrHelpNodeCollection Nodes {
         get {
            if(null == _nodes) {
               _nodes = new FCrHelpNodeCollection();
            }
            return _nodes;
         }
      }

      public void Parse(IReader reader) {
      }

      //============================================================
      // <T>获取段落注释</T>
      //
      // @param strLine 要获取的字符串集合
      // @param index 当前索引
      // @return 段落注释
      //============================================================
      public FStrings GetAnnotate(FStrings strLine, int index) {
         FStrings str = new FStrings();
         if(!strLine[index - 1].Contains(_annotate))
            return str;
         else {
            int i = index - 2;
            while(!strLine[i].Contains("}") && !strLine[i].Contains("{") && !strLine[i].Contains(_annotate)&&strLine[i].Trim().StartsWith("//")&&i>=0) {
               i--;
            }
            for(i++; i < index - 1; i++) {
               str.Add(strLine[i]);
            }
            return str;
         }
      }

      //============================================================
      // <T>判断是否有多个同一方向花括号在同一行。</T>
      //
      // @param line 需要判断的行字符串。
      // @return  true 表示没有多个同一方向的花括号在同一行
      //============================================================
      public bool IsBracketsSameLine(string line) {
         if((line.LongCount<char>(c => c.Equals('}')) > 1) || (line.LongCount<char>(c => c.Equals('{')) > 1)) {
            System.Windows.Forms.MessageBox.Show("文件中花括号错误，是否退出查错，不然会影响帮助信息提取结果", "提示信息",
               System.Windows.Forms.MessageBoxButtons.YesNo, System.Windows.Forms.MessageBoxIcon.Information);
            return true;
         } else
            return false;
      }

      public bool IsInString(string line) {
         if(line.Contains("\"")&&(line.Contains("{")||line.Contains("}"))) {
            int b = line.IndexOf("\"");
            string strTemp = line.Substring(b + 1);
            if(!strTemp.Contains("\"")) {
               return false;
            }
            int e = strTemp.IndexOf("\"");
            strTemp = line.Substring(b + 1, e );
            if(strTemp.Contains("}") || strTemp.Contains("{")) {
               return true;
            }
            return false;
         }
         return false;
      }

      //============================================================
      // <T>为一个对象，比如命名空间，类，方法等，找到对应的花括号所在行</T>
      //
      // @param strLine 表示要获取的字符串数组
      // @beginLine  表示从哪一行开始查找对应的花括号
      // @return  对应花括号所在行，也就是对象结束行。若为-1则表示程序出错
      //============================================================
      public int GetPairNum(FStrings strLine, int beginLine) {
         int endLine;
         int left = 0;
         int right = 0;
         int startindex;
         int end = CheckParaAnnotate(strLine, beginLine, out startindex);
         for(endLine = beginLine; endLine < strLine.Count; endLine++) {
            if(strLine[endLine].Trim().StartsWith("//")) { continue; }
            if(IsInInterregional(endLine, startindex, end)) { continue; }
            if(strLine[endLine].Contains('{')) {
               int n = strLine[endLine].Count<char>(c => c.Equals('{'));
               if(!IsInString(strLine[endLine])) {
                  left += n;
               }
            }
            if(strLine[endLine].Contains('}')) {
               int n = strLine[endLine].Count<char>(c => c.Equals('}'));
               if(!IsInString(strLine[endLine])) {
                  right += n;
               }
            }
            if((left == right) && (left != 0))
               return endLine;
         }
         return -1;
      }

      //============================================================
      // <T>扫描字符串中的逗号，并在符合条件的逗号后面加上$符号。</T>
      //
      // @param commastr 需要扫描逗号的字符串
      // @return 扫面并修改之后的字符串
      //============================================================
      public string ScanComma(string commastr) {
         char[] c = commastr.ToCharArray();
         FArray<char> charArr = new FArray<char>();
         int left = 0, right = 0;
         for(int n = 0; n < c.Length; n++) {
            if(c[n] == '<') { left++; }
            if(c[n] == '>') { right++; }
            if(c[n] == ',' && left == right) { charArr.Add('$'); }
            charArr.Add(c[n]);
         }
         char[] cs = charArr.ToArray();
         string str = new string(cs);
         return str;
      }

      //============================================================
      // <T>判断是否为段落注释的开始。</T>
      //
      // @param strLine 需要判断的字符串
      // @return true 表示是段落注释的开始，反之不是
      //============================================================
      public bool IsParaAnnotateStart(string strLine) {
         if(strLine.Contains("/*")) {
            return true;
         } else {
            return false;
         }
      }

      //============================================================
      // <T>判断是否为段落注释的借宿。</T>
      //
      // @param strLine 需要判断的字符串
      // @return true 表示是段落注释的结束，反之不是
      //============================================================
      public bool IsParaAnnotateEnd(string strLine) {
         if(strLine.Contains("*/")) {
            return true;
         } else {
            return false;
         }
      }

      //============================================================
      // <T>获取段落注释的结束行的行号。</T>
      //
      // @param strLines 需要判断的字符串数组
      // @param index 需要开始判断的行号
      // @return 段落注释的结束行号
      //============================================================
      public int GetParaAnnotate(FStrings strLines, int index) {
         int end = index;
         int startAnno = 0, endAnno = 0;
         for(int n = 0; n < strLines.Count; n++) {
            if(IsParaAnnotateStart(strLines[n])) {
               startAnno = 1;
               end = n;
            }
            if(IsParaAnnotateEnd(strLines[n])) {
               endAnno = 1;
               end = n;
            }
            if((startAnno == endAnno) && (startAnno == 1)) {
               return n;
            }
            end = n;
         }
         return end;
      }

      //============================================================
      // <T>根据正在判断的行号和获取到的段落注释的起始，终止行号判断是否为注释之中。</T>
      // @param index 当前正在判断的行号。
      // @param min 段落注释的初始行号
      // @param max 段落注释的终止行号
      // @return  true表示在段落注释之中，false表示不在段落注释之中
      //============================================================
      public bool IsInInterregional(int index, int min, int max) {
         if(max != -1 && min != -1) {
            if(index <= max && index >= min) {
               return true;
            } else { return false; }
         }
         return false;
      }

      //============================================================
      // <T>获取段落注释的起始与结束行号。</T>
      // @param strLines 需要判断的字符串数组
      // @param index 需要开始判断的行号
      // @param startindex 要获取的开始行行号
      // @return 段落注释的结束行号
      //============================================================
      public int CheckParaAnnotate(FStrings strLines, int index, out int startindex) {
         int endindex = -1;
         startindex = -1;
         for(int n = index; n < strLines.Count; n++) {
            if(IsParaAnnotateStart(strLines[n])) {
               startindex = n;
               endindex = GetParaAnnotate(strLines, n);
            }
         }
         return endindex;
      }

      public FArray<string> GetUsing(FStrings strLines) {
         FArray<string> usings = new FArray<string>();
         for(int n = 0; n < strLines.Count; n++) {
            string strTemp = strLines[n].ToString().Trim();
            if(strTemp.Contains("using") && strTemp.Contains("MO")) {
               string usinginfo = strTemp.Substring(6).Trim();
               usings.Add(usinginfo);
            }
            if(!strTemp.Contains("using")) { return usings; }
         }
         return usings;
      }

      public FArray<string> GetImport(FStrings strLines) {
         FArray<string> imports = new FArray<string>();
         for(int n = 0; n < strLines.Count; n++) {
            string strTemp = strLines[n].ToString().Trim();
            if(strTemp.Contains("import") && strTemp.Contains("MO")) {
               string usinginfo = strTemp.Substring(6).Trim();
               imports.Add(usinginfo);
            }
            if(!strTemp.Contains(" class ")) { return imports; }
         }
         return imports;
      }

      public FArray<string> GetInclude(FStrings strLines) {
         FArray<string> includes = new FArray<string>();
         for(int n = 0; n < strLines.Count; n++) {
            string strTemp = strLines[n].Trim();
            if(strTemp.Contains("include") && strTemp.Contains("\"")) { 
               int start=strTemp.IndexOf("\"")+1;
               int end=strTemp.LastIndexOf("\"");
               string temp=strTemp.Substring(start,end-start);
               includes.Add(temp);
            }
            if(!strTemp.Contains("#") && strTemp != "") {
               return includes;
            }
         }
         return includes;
      }
   }
}

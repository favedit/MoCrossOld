using System;
using System.Text;

namespace MO.Common.Lang
{
   //============================================================
   // <T>字符串工具类。</T>
   //
   // @history 110209 MAOCY 创建
   //============================================================
   public class RString
   {
      // 大写字符
      public const string STR_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

      // 小写字符
      public const string STR_LOWER = "abcdefghijklmnopqrstuvwxyz";

      // 数字字符
      public const string STR_NUMBER = "0123456789";

      // 浮点数字符
      public const string STR_FLOAT = "-.0123456789";

      //============================================================
      // <T>测试字符串是否为空。</T>
      //
      // @param value 字符串
      // @return 是否为空
      //============================================================
      public static bool IsEmpty(string value) {
         return (null != value) ? (0 == value.Length) : true;
      }

      //============================================================
      // <T>测试字符串是否为空字符串。</T>
      //
      // @param value 字符串
      // @return 是否为空字符串
      //============================================================
      public static bool IsBlank(string value) {
         return (null != value) ? (0 == value.Trim().Length) : true;
      }

      //============================================================
      // <T>测试字符串是否相等。</T>
      //
      // @param source 源字符串
      // @param target 目标字符串
      // @return 是否相等
      //============================================================
      public static bool Equals(string source, string target) {
         if (null == source) {
            source = "";
         }
         if (null == target) {
            target = "";
         }
         return source.Equals(target);
      }

      //============================================================
      // <T>获得非空字符串。</T>
      //
      // @param value 对象
      // @return 字符串
      //============================================================
      public static string Nvl(object value) {
         return (null != value) ? value.ToString() : "";
      }

      //============================================================
      // <T>获得非空字符串。</T>
      //
      // @param values 对象集合
      // @return 字符串
      //============================================================
      public static string Nvl(params object[] values) {
         foreach (object value in values) {
            if (null != value) {
               string result = value.ToString();
               if (result.Length > 0) {
                  return result;
               }
            }
         }
         return "";
      }

      //============================================================
      // <T>获得非空字符串。</T>
      //
      // @param value 字符串
      // @return 字符串
      //============================================================
      public static string Nvl(string value) {
         return (null != value) ? value : "";
      }

      //============================================================
      // <T>获得非空字符串。</T>
      //
      // @param values 字符串列表
      // @return 字符串
      //============================================================
      public static string Nvl(params string[] values) {
         foreach (string value in values) {
            if (null != value) {
               if (value.Length > 0) {
                  return value;
               }
            }
         }
         return "";
      }

      //============================================================
      // <T>获得非空字符串对象。</T>
      //
      // @param value 字符串对象
      // @return 字符串对象
      //============================================================
      public static FString NvlStr(FString value) {
         return (null != value) ? value : new FString();
      }

      //============================================================
      // <T>获得首字母大写的字符串。</T>
      //
      // @param value 字符串
      // @return 字符串
      //============================================================
      public static string FirstUpper(string value) {
         if ((null != value) && (value.Length > 0)) {
            return value.Substring(0, 1).ToUpper() + value.Substring(1);
         }
         return value;
      }

      //============================================================
      // <T>获得首字母小写的字符串。</T>
      //
      // @param value 字符串
      // @return 字符串
      //============================================================
      public static string FirstLower(string value) {
         if ((null != value) && (value.Length > 0)) {
            return value.Substring(0, 1).ToLower() + value.Substring(1);
         }
         return value;
      }

      //============================================================
      // <T>获得查找字符串左侧的部分字符串。</T>
      //
      // @param value 字符串
      // @param find 查找字符串
      // @return 左字符串
      //============================================================
      public static string Left(string value, string find) {
         if ((null != value) && (null != find)) {
            int index = value.IndexOf(find);
            if (-1 != index) {
               return value.Substring(0, index);
            }
         }
         return value;
      }

      //============================================================
      // <T>获得查找字符串右侧的部分字符串。</T>
      //
      // @param value 字符串
      // @param find 查找字符串
      // @return 右字符串
      //============================================================
      public static string Right(string value, string find) {
         if ((null != value) && (null != find)) {
            int index = value.LastIndexOf(find);
            if (-1 != index) {
               return value.Substring(index + find.Length);
            }
         }
         return value;
      }

      //============================================================
      // <T>反序获得查找字符串右侧的部分字符串。</T>
      //
      // @param value 字符串
      // @param find 查找字符串
      // @return 右字符串
      //============================================================
      public static string LastRight(string value, string find) {
         if((null != value) && (null != find)) {
            int index = value.LastIndexOf(find);
            if(-1 != index) {
               return value.Substring(index + find.Length);
            }
         }
         return value;
      }

      //============================================================
      // <T>左补齐字符串到指定长度。</T>
      //
      // @param value 字符串
      // @param length 长度
      // @param code 字符
      // @return 右字符串
      //============================================================
      public static string PadLeft(string value, int length, char code) {
         if (null != value) {
            int count = length - value.Length;
            if (count > 0) {
               StringBuilder result = new StringBuilder();
               for (int n = 0; n < count; n++) {
                  result.Append(code);
               }
               result.Append(value);
               return result.ToString();
            }
         }
         return value;
      }

      //============================================================
      // <T>右补齐字符串到指定长度。</T>
      //
      // @param value 字符串
      // @param length 长度
      // @param code 字符
      // @return 右字符串
      //============================================================
      public static string PadRight(string value, int length, char code) {
         if (null != value) {
            int count = length - value.Length;
            if (count > 0) {
               StringBuilder result = new StringBuilder();
               result.Append(value);
               for (int n = 0; n < count; n++) {
                  result.Append(code);
               }
               return result.ToString();
            }
         }
         return value;
      }

      //============================================================
      // <T>获得指定字符串中间的部分字符串。</T>
      //
      // @param value 字符串
      // @param left 左字符串
      // @param right 右字符串
      // @return 中间字符串
      //============================================================
      public static string SubString(string value, string left, string right) {
         if (null == value) {
            return value;
         }
         // 计算开始位置
         int start = 0;
         if (null != left) {
            int index = value.IndexOf(left);
            if (-1 != index) {
               start = index + left.Length;
            }
         }
         // 计算结束位置
         int end = value.Length;
         if (null != right) {
            int index = value.IndexOf(right);
            if (-1 != index) {
               end = index;
            }
         }
         // 获得中间字符串
         return value.Substring(start, end - start);
      }

      //============================================================
      // <T>从后面获得指定字符串中间的部分字符串。</T>
      //
      // @param value 字符串
      // @param left 左字符串
      // @param right 右字符串
      // @return 中间字符串
      //============================================================
      public static string SubStringLast(string value, string left, string right) {
         if(null == value) {
            return value;
         }
         // 计算结束位置
         int end = value.Length;
         if(null != right) {
            int index = value.LastIndexOf(right);
            if(-1 != index) {
               end = index;
            }
         }
         // 计算开始位置
         int start = 0;
         if(null != left) {
            int index = value.LastIndexOf(left, end);
            if(-1 != index) {
               start = index + left.Length;
            }
         }
         // 获得中间字符串
         return value.Substring(start, end - start);
      }

      //============================================================
      // <T>重复字符生成字符串。</T>
      //
      // @param ch 字符
      // @param count 总数
      // @return 字符串
      //============================================================
      public static string Repeat(char ch, int count) {
         FString result = new FString();
         for (int n = 0; n < count; n++) {
            result.Append(ch);
         }
         return result.ToString();
      }

      //============================================================
      // <T>重复字符串生成字符串。</T>
      //
      // @param value 字符串
      // @param count 总数
      // @return 字符串
      //============================================================
      public static string Repeat(string value, int count) {
         FString result = new FString();
         for (int n = 0; n < count; n++) {
            result.Append(value);
         }
         return result.ToString();
      }

      //============================================================
      // <T>删除字符串结尾的空字符串。</T>
      //
      // @param value 字符串
      // @return 字符串
      //============================================================
      public static string TrimEnd(string value) {
         return (null != value) ? value.TrimEnd() : null;
      }

      //============================================================
      // <T>删除字符串两侧的空字符串。</T>
      //
      // @param value 字符串
      // @return 字符串
      //============================================================
      public static string Trim(string value) {
         return (null != value) ? value.Trim() : null;
      }

      //============================================================
      // <T>删除字符串数组两侧的空字符串。</T>
      //
      // @param value 字符串
      // @param blankLine 是否保持空行
      // @return 字符串
      //============================================================
      public static string TrimLines(string value, bool blankLine) {
         if (null != value) {
            return value;
         }
         // 追加字符串列表
         FString result = new FString();
         string[] lines = value.Trim().Split('\n');
         for (int n = 0; n < lines.Length; n++) {
            if (n > 0) {
               result.AppendLine();
            }
            string line = lines[n].Trim();
            if (blankLine) {
               result.Append(line);
            } else if (!blankLine && !RString.IsEmpty(line)) {
               result.Append(line);
            }
         }
         return result.ToString();
      }

      //============================================================
      // <T>删除字符串数组两侧的空字符串。</T>
      //
      // @param value 字符串
      // @param flag 开头字符
      // @return 字符串
      //============================================================
      public static string TrimLines(string value, char flag) {
         if (null != value) {
            return value;
         }
         FString result = new FString();
         string[] lines = value.Trim().Split('\n');
         for (int n = 0; n < lines.Length; n++) {
            if (n > 0) {
               result.AppendLine();
            }
            string line = lines[n].Trim();
            if (0 == line.IndexOf(flag)) {
               line = line.Substring(1);
            }
            result.Append(line);
         }
         return result.ToString();
      }

      //============================================================
      // <T>删除字符串数组两侧的空字符串。</T>
      //
      // @param lines 字符串数组
      // @param blankLine 是否保持空行
      // @return 字符串数组
      //============================================================
      public static string[] TrimLines(string[] lines, bool blankLine) {
         if (lines != null) {
            FStrings result = new FStrings();
            for (int n = 0; n < lines.Length; n++) {
               string line = lines[n].Trim();
               if (blankLine) {
                  result.Push(line);
               } else if (!blankLine && !RString.IsEmpty(line)) {
                  result.Push(line);
               }
            }
            return result.ToArray();
         }
         return null;
      }

      //============================================================
      // <T>生成字符模板。</T>
      //
      // @param value 模板
      // @return 字符模板
      //============================================================
      public static char[] MakePattern(string value) {
         FString result = new FString();
         foreach (char ch in value.ToLower()) {
            if (ch == 'u') {
               result.Append(STR_UPPER);
            } else if (ch == 'l') {
               result.Append(STR_LOWER);
            } else if (ch == 'n') {
               result.Append(STR_NUMBER);
            } else if (ch == 'f') {
               result.Append(STR_FLOAT);
            } else {
               result.Append(ch);
            }
         }
         return result.ToArray();
      }

      //============================================================
      // <T>检查字符串中是否为空。</T>
      //
      // @param value 字符串
      // @param name 名称
      // @return 是否含有
      //============================================================
      public static void CheckEmpty(string value, string name) {
         if (IsEmpty(value)) {
            RException.RaiseValid(typeof(RString), "Empty", name);
         }
      }

      //============================================================
      // <T>检查字符串中是否由指定字符列表中的字符构成。</T>
      //
      // @param value 字符串
      // @param pattern 字符列表
      // @return 是否含有
      //============================================================
      public static bool CheckPattern(string value, char[] pattern) {
         foreach (char ch in value) {
            if (!RChar.Contains(pattern, ch)) {
               return false;
            }
         }
         return true;
      }

      //============================================================
      // <T>获得字符串内的行数。</T>
      //
      // @param value 字符串
      // @return 行数
      //============================================================
      public static int LineCount(string value) {
         if (null != value) {
            return value.Split('\n').Length;
         }
         return 0;
      }

      //============================================================
      // <T>使用分隔符链接所有字符串。</T>
      //
      // @param split 分隔符
      // @param values 字符串列表
      // @return 字符串
      //============================================================
      public static string Join(string split, params object[] values) {
         if (null == values) {
            return null;
         }
         // 链接字符串
         FString result = new FString();
         int length = values.Length;
         for (int n = 0; n < length; n++) {
            if (n > 0) {
               result.Append(split);
            }
            result.Append(values[n]);
         }
         return result.ToString();
      }

      //============================================================
      // <T>用指定字符分割字符串为字符串列表。</T>
      //
      // @param value 字符串
      // @param split 分割符
      // @return 分割后字符串列表
      //============================================================
      public static string[] Split(string value, char split) {
         return (value != null) ? value.Split(split) : null;
      }

      //============================================================
      // <T>用指定字符分割字符串为字符串列表。</T>
      //
      // @param value 字符串
      // @param split 分割符
      // @param limit 限制次数
      // @return 分割后字符串列表
      //============================================================
      public static string[] Split(string value, char split, int limit) {
         int length = value.Length;
         char[] memory = value.ToCharArray();
         int blockSize = 1;
         for (int n = 0; n < length; n++) {
            if (memory[n] == split) {
               blockSize++;
               if (blockSize >= limit) {
                  break;
               }
            }
         }
         string[] blocks = new string[blockSize];
         if (blockSize > 1) {
            blockSize = 0;
            int pos = 0;
            for (int n = 0; n < length; n++) {
               if (memory[n] == split) {
                  if (blockSize >= limit - 1) {
                     break;
                  }
                  blocks[blockSize++] = new string(memory, pos, n - pos);
                  pos = n + 1;
               }
            }
            if (pos < length) {
               blocks[blockSize] = new string(memory, pos, length - pos);
            }
         } else {
            blocks[0] = value;
         }
         return blocks;
      }

      //============================================================
      // <T>用指定字符分割字符串为2段字符串列表。</T>
      //
      // @param value 字符串
      // @param split 分割符
      // @param remainSplit 保留分割符
      // @return 分割后字符串列表
      //============================================================
      public static string[] SplitTwo(string value, char split, bool remainSplit = false) {
         string[] items = new string[2];
         int index = value.IndexOf(split);
         if(index != -1) {
            items[0] = value.Substring(0, index);
            if (remainSplit) {
               items[1] = value.Substring(index);
            } else {
               items[1] = value.Substring(index + 1);
            }
         } else {
            items[0] = value;
            items[1] = "";
         }
         return items;
      }

      //============================================================
      // <T>用指定字符分割字符串为非空字符串列表。</T>
      //
      // @param value 字符串
      // @param split 分割符
      // @return 分割后字符串列表
      //============================================================
      public static string[] SplitNotEmpty(string value, char split) {
         // 分割字符串
         string[] blocks = value.Split(split);
         // 计算有效个数
         int count = 0;
         foreach (string line in blocks) {
            if(line.Length > 0){
               count++;
            }
         }
         // 生成有效集合
         int index = 0;
         string[] result = new string[count];
         foreach (string line in blocks) {
            if (line.Length > 0) {
               result[index++] = line;
            }
         }
         return result;
      }

      //============================================================
      // <T>格式化字符串。</T>
      //
      // @param value 字符串
      // @param args 参数列表
      // @return 字符串
      //============================================================
      public static string Format(string value, params object[] args) {
         if (args != null && args.Length > 0) {
            try {
               return String.Format(value, args);
            } catch (Exception e) {
               throw new FFatalException(e, "Format log message error.\n   message=[{0}]\n   parameters=[{1}])", value, RString.Join(",", args));
            }
         }
         return value;
      }

      //============================================================
      // <T>尝试格式化字符串。</T>
      //
      // @param value 字符串
      // @param args 参数列表
      // @return 字符串
      //============================================================
      public static string TryFormat(string value, params object[] args) {
         if (args != null && args.Length > 0) {
            try {
               return String.Format(value, args);
            } catch {
               return "[Format error] " + value + " = " + RString.Join(",", args);
            }
         }
         return value;
      }

      //============================================================
      // <T>对象变为一个字符串。</T>
      //
      // @param value 字符串
      // @return 字符串
      //============================================================
      public static string ToString(object value) {
         if (null != value) {
            if (value is string) {
               return (string)value;
            }
            return value.ToString();
         }
         return null;
      }

      //============================================================
      // <T>对象变为一个字符串。</T>
      //
      // @param value 字符串
      // @return 字符串
      //============================================================
      public static string FormatLineSplitWord(string source, char split) {
         bool first = true;
         StringBuilder sb = new StringBuilder();
         foreach(char value in source) {
            if(!first) {
               if((value >= 'A') && (value <= 'Z')) {
                  sb.Append(split);
               }
            }
            sb.Append(value);
            first = false;
         }
         return sb.ToString();
      }
   }
}

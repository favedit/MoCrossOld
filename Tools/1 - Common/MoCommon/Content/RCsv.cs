using MO.Common.Lang;
using MO.Common.IO;

namespace MO.Common.Content
{
   //============================================================
   // <T>CSV文档工具类。</T>
   //============================================================
   public class RCsv
   {
      //============================================================
      // <T>解析CSV文件行。</T>
      //
      // @param reader 读取器
      // @param format 格式器
      // @return 行对象
      //============================================================
      public static FCsvLine ParseLine(FCharReader reader, FCsvFormat format) {
         FCsvLine line = null;
         if(reader.HasNext()) {
            line = new FCsvLine();
            if(format.DataClose) {
               ParseLineWithClose(line, reader, format);
            } else {
               ParseLineWithoutClose(line, reader, format);
            }
         }
         return line;
      }

      public static void ParseLineWithClose(FCsvLine line, FCharReader reader, FCsvFormat format) {
         char delimiter = format.Delimiter;
         bool inComment = false;
         FString value = new FString();
         while(reader.HasNext()) {
            // Read
            char ch = reader.Read();
            // Check
            if(inComment) {
               if(ch == '"') {
                  char nch = reader.Get(1);
                  if(nch == '"') {
                     value.Append(ch);
                     reader.Skip(1);
                     continue;
                  }
                  if(nch == '\n') {
                     line.Append(value.Flush());
                     reader.Skip(1);
                     break;
                  }
                  if(nch == delimiter) {
                     reader.Skip(1);
                  }
                  inComment = false;
                  line.Append(value.Flush());
                  continue;
               }
            } else {
               if(ch == '\n') {
                  line.Append(value.Flush());
                  break;
               } else if(ch == '"') {
                  inComment = true;
                  continue;
               } else if(ch == delimiter) {
                  line.Append(value.Flush());
                  continue;
               }
            }
            value.Append(ch);
         }
         if(!value.IsEmpty) {
            line.Append(value.Flush());
         }
      }

      public static void ParseLineWithoutClose(FCsvLine line, FCharReader reader, FCsvFormat format) {
         char delimiter = format.Delimiter;
         FString value = new FString();
         while(reader.HasNext()) {
            // Read
            char ch = reader.Read();
            // Check
            if(ch == '\n') {
               line.Append(value.Flush());
               break;
            } else if(ch == delimiter) {
               line.Append(value.Flush());
               continue;
            }
            value.Append(ch);
         }
         if(!value.IsEmpty) {
            line.Append(value.Flush());
         }
      }

      public static void FormatField(FString result, string field, bool quot) {
         if(field.Contains(",") || field.Contains("\"")) {
            // Special
            result.Append("\"");
            foreach(char ch in field.ToCharArray()) {
               if(ch == '"') {
                  result.Append("\"\"");
               } else {
                  result.Append(ch);
               }
            }
            result.Append("\"");
         } else {
            // Normal
            if(quot) {
               result.Append("\"");
            }
            result.Append(field);
            if(quot) {
               result.Append("\"");
            }
         }
      }

      public static string FormatField(string field) {
         FString result = new FString();
         FormatField(result, field, false);
         return result.ToString();
      }
   }
}

package org.mo.mime.csv;

import org.mo.com.lang.FString;

public class RCsv
{
   public static ICsvFormat DEFAULT_FORMAT = new FCsvFormat();

   public static void formatField(FString result,
                                  String field){
      boolean hasQuot = (-1 != field.indexOf(',')) || -1 != field.indexOf('\"') || -1 != field.indexOf('\n');
      if(hasQuot){
         result.append('\"');
      }
      for(char ch : field.toCharArray()){
         if(ch == '"'){
            result.append("\"\"");
         }else{
            result.append(ch);
         }
      }
      if(hasQuot){
         result.append('\"');
      }
   }

   public static String formatField(String field){
      FString result = new FString();
      formatField(result, field);
      return result.toString();
   }

   public static boolean isEmptyLine(String line){
      int length = line.length();
      for(int n = 0; n < length; n++){
         if(line.charAt(n) != ',' && line.charAt(n) != ' '){
            return false;
         }
      }
      return true;
   }

   public static FCsvLine parseLine(String line){
      FString value = new FString();
      FCsvLine csvLine = new FCsvLine();
      int length = line.length();
      boolean inComment = false;
      for(int n = 0; n < length; n++){
         // Read
         char ch = line.charAt(n);
         // Check
         if(inComment){
            if(ch == '"'){
               if(n == length - 1){
                  csvLine.push(value.flushString());
                  break;
               }
               char nch = line.charAt(n + 1);
               if(nch == '"'){
                  value.append(ch);
                  n++;
                  continue;
               }
               if(nch == '\n'){
                  csvLine.push(value.flushString());
                  n++;
                  break;
               }
               if(nch == ','){
                  n++;
               }
               inComment = false;
               csvLine.push(value.flushString());
               continue;
            }
         }else{
            if(ch == '\n'){
               csvLine.push(value.flushString());
               break;
            }else if(ch == '"'){
               inComment = true;
               continue;
            }else if(ch == ','){
               csvLine.push(value.flushString());
               continue;
            }
         }
         value.append(ch);
         if(n == length - 1){
            csvLine.push(value.flushString());
         }
      }
      return csvLine;
   }
}

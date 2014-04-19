package org.mo.eng.template.tag;

import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.text.RDatabaseFormat;
import org.mo.com.text.RHtmlFormat;
import org.mo.com.text.RJavaFormat;
import org.mo.com.text.RWord;

//============================================================
// <T>模板工具类。</T>
//============================================================
public class RTplTag
{
   //============================================================
   // <T>格式化内容为指定格式的字符串。</T>
   //
   // @param format 格式
   // @param value 内容
   // @return 字符串
   //============================================================
   public static String format(String format,
                               String value){
      // 检查参数
      if(RString.isEmpty(format, value)){
         return value;
      }
      // 格式化内容
      if(format.equalsIgnoreCase("short")){
         return RClass.shortName(value);
      }else if(format.equalsIgnoreCase("path")){
         return RClass.classPath(value);
      }else if(format.equalsIgnoreCase("field")){
         return value;
      }else if(format.equalsIgnoreCase("lower")){
         return value.toLowerCase();
      }else if(format.equalsIgnoreCase("lower.first")){
         return RString.firstLower(value);
      }else if(format.equalsIgnoreCase("upper")){
         return value.toUpperCase();
      }else if(format.equalsIgnoreCase("upper.first")){
         return RString.firstUpper(value);
      }else if(format.equalsIgnoreCase("field.java")){
         return value.replaceAll("\\_", "");
      }else if(format.equalsIgnoreCase("method")){
         return RDatabaseFormat.toJavaMethodName(value);
      }else if(format.equalsIgnoreCase("method.upper")){
         return RDatabaseFormat.toJavaMethodName(value).toUpperCase();
      }else if(format.equalsIgnoreCase("method.lower")){
         return RDatabaseFormat.toJavaMethodName(value).toLowerCase();
      }else if(format.equalsIgnoreCase("class")){
         return RDatabaseFormat.toJavaClassName(value);
      }else if(format.equalsIgnoreCase("db.var")){
         return RDatabaseFormat.toVariableName(value);
      }else if(format.equalsIgnoreCase("db.func")){
         return RDatabaseFormat.toFunctionName(value);
      }else if(format.equalsIgnoreCase("db.object")){
         return RJavaFormat.toDatabaseObjectName(value);
      }else if(format.equalsIgnoreCase("db.func.lower")){
         return RString.toLower(RJavaFormat.toDatabaseMethodName(value, null, null, "_"));
      }else if(format.equalsIgnoreCase("length")){
         return Integer.toString(value.length());
      }else if(format.equalsIgnoreCase("class.package")){
         return RClass.packageName(value);
      }else if(format.equalsIgnoreCase("class.name")){
         return RClass.shortName(value);
      }else if(format.equalsIgnoreCase("true.name")){
         return RString.mid(value, "_", null);
      }else if(format.equalsIgnoreCase("left.name")){
         value = RString.toUpper(value);
         return RString.mid(value, null, "_ID");
      }else if(format.equalsIgnoreCase("word.class")){
         value = RString.replace(value, ".", "_");
         value = RString.replace(value, " ", "_");
         return RDatabaseFormat.toJavaClassName(value);
      }else if(format.equalsIgnoreCase("path.lower")){
         value = RString.replace(value, ".", "_");
         return value.toLowerCase();
      }else if(format.equalsIgnoreCase("html")){
         return RHtmlFormat.textToHtml(value).toString();
      }else if(format.equalsIgnoreCase("java.help")){
         String[] lines = RString.split(value, '\n');
         FString source = new FString();
         for(String line : lines){
            source.append("    * " + line + "\n");
         }
         return RString.trimRight(source.toString());
      }else if(format.equalsIgnoreCase("underline.word")){
         return RWord.formatUnderlineToWord(value);
      }else if(format.equalsIgnoreCase("underline.word.first.upper")){
         String formatValue = RWord.formatUnderlineToWord(value);
         return RString.firstUpper(formatValue);
      }else if(format.equalsIgnoreCase("underline.word.first.lower")){
         String formatValue = RWord.formatUnderlineToWord(value);
         return RString.firstLower(formatValue);
      }
      return value;
   }
}

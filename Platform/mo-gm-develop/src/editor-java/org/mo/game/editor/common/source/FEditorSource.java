package org.mo.game.editor.common.source;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.FEncoding;

public class FEditorSource
      extends FStringFile
{

   public static String SPLITTER = RString.repeat("-", 60);

   protected String _source;

   //============================================================
   public FEditorSource(String fileName){
      loadFile(fileName);
      String source = toString();
      _source = source.replaceAll("\r", "");
   }

   //============================================================
   public FEditorSource(String fileName,
                        FEncoding encoding){
      loadFile(fileName, encoding.toString());
      String source = toString();
      _source = source.replaceAll("\r", "");
   }

   //============================================================
   public String find(String name){
      String splitter = "//" + SPLITTER + "\n";
      // 查找开始位置
      String beginFlag = "// @manual (" + name + ".Begin)\n";
      int begin = _source.indexOf(beginFlag);
      if(-1 != begin){
         begin = _source.indexOf(splitter, begin + beginFlag.length());
         if(-1 != begin){
            begin += splitter.length();
         }
      }
      // 查找结束位置
      int end = -1;
      if(-1 != begin){
         String endFlag = "// @manual (" + name + ".End)\n";
         end = _source.indexOf(endFlag, begin);
         if(-1 != end){
            end = _source.lastIndexOf(splitter, end);
         }
      }
      // 截取内容
      if((begin >= 0) && (end >= 0) && (end >= begin)){
         return _source.substring(begin, end);
      }
      return "";
   }
}

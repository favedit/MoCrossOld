package org.mo.script.java.parser;

import org.mo.com.lang.RString;
import org.mo.com.text.parser.FTextToken;

//============================================================
// <T>导入对象。</T>
//============================================================
public class FJavaImport
      extends FJavaObject
{
   //============================================================
   // <T>获得短名称。</T>
   //
   // @return 短名称
   //============================================================
   public String shortName(){
      return RString.right(_name, ".");
   }

   //============================================================
   // <T>解析文本段内容。</T>
   //
   // @param content 解析环境
   // @param token 文本段
   // @param line 文本行
   //============================================================
   public void parse(FJavaParserContent content, FTextToken token, String line){
      _name = RString.mid(line, "import ", ";").trim();
   }
}

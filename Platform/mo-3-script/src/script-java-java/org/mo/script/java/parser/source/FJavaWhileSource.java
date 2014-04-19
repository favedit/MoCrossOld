package org.mo.script.java.parser.source;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.java.converter.FJavaConvertContent;
import org.mo.script.java.parser.FJavaParserContent;

//============================================================
// <T>关键词WHILE代码。</T>
//============================================================
public class FJavaWhileSource
      extends FJavaSource
{
   protected String _value;

   //============================================================
   // <T>构造代码。</T>
   //============================================================
   public FJavaWhileSource(){
   }

   //============================================================
   // <T>解析关键字。</T>
   //
   // @param content 解析环境
   // @param token 文本块
   // @param line 代码行
   //============================================================
   public void parseKeyword(FJavaParserContent content, FTextToken token, String line){
      String data = RString.midMatch(line, '(', ')');
      if(!RString.isEmpty(data)){
         _value = data;
      }
      // 解析子代码
      parse(content, token);
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   @Override
   public void convertSourceBegin(FJavaConvertContent content, FTextSource source){
      //............................................................
      source.appendIndent();
      source.append("if(");
      source.append(_value);
      source.appendLine("){");
      source.appendIndent();
      source.appendLine("}");
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      info.appendIndent();
      info.append("AsSource [");
      info.append("]");
      return info;
   }
}

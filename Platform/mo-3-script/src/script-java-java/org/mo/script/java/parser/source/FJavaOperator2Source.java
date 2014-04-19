package org.mo.script.java.parser.source;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.java.converter.FJavaConvertContent;
import org.mo.script.java.parser.FJavaParserContent;

//============================================================
// <T>关键词IF代码。</T>
//============================================================
public class FJavaOperator2Source
      extends FJavaSource
{
   protected String _operator;

   protected String _valueLeft;

   protected String _valueRight;

   //============================================================
   // <T>构造代码。</T>
   //============================================================
   public FJavaOperator2Source(){
      _hasLineBegin = false;
   }

   //============================================================
   // <T>解析关键字。</T>
   //
   // @param content 解析环境
   // @param token 文本块
   // @param line 代码行
   //============================================================
   public void parseKeyword(FJavaParserContent content, FTextToken token, String line){
      if(RString.contains(line, " == ")){
         String[] items = RString.splitTwo(line, " == ");
         _operator = "==";
         _valueLeft = items[0].trim();
         _valueRight = items[1].trim();
      }else if(RString.contains(line, " != ")){
         String[] items = RString.splitTwo(line, " != ");
         _operator = "!=";
         _valueLeft = items[0].trim();
         _valueRight = items[1].trim();
      }
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   @Override
   public void convertSourceBegin(FJavaConvertContent content, FTextSource source){
      source.append(_valueLeft);
      source.append(' ');
      source.append(_operator);
      source.append(' ');
      source.append(content.convertSource(_valueRight));
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

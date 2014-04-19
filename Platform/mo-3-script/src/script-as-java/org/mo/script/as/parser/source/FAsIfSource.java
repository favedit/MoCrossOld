package org.mo.script.as.parser.source;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.as.converter.FAsConvertContent;
import org.mo.script.as.parser.FAsParserContent;

//============================================================
// <T>关键词IF代码。</T>
//============================================================
public class FAsIfSource
      extends FAsSource
{
   protected String _value;

   //============================================================
   // <T>构造代码。</T>
   //============================================================
   public FAsIfSource(){
   }

   //============================================================
   // <T>解析关键字。</T>
   //
   // @param content 解析环境
   // @param token 文本块
   // @param line 代码行
   //============================================================
   public void parseKeyword(FAsParserContent content, FTextToken token, String line){
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
   public void convertSourceBegin(FAsConvertContent content, FTextSource source){
      //............................................................
      source.appendIndent();
      source.append("if(");
      source.append(_value);
      source.append("){");
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   @Override
   public void convertSourceEnd(FAsConvertContent content, FTextSource source){
      source.appendLine();
      source.appendIndent();
      source.append("}");
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

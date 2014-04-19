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
public class FAsOperator3Source
      extends FAsSource
{
   protected String _operator;

   protected String _value1;

   protected String _value2;

   protected String _value3;

   //============================================================
   // <T>构造代码。</T>
   //============================================================
   public FAsOperator3Source(){
      _hasLineBegin = false;
   }

   //============================================================
   // <T>解析关键字。</T>
   //
   // @param content 解析环境
   // @param token 文本块
   // @param line 代码行
   //============================================================
   public void parseKeyword(FAsParserContent content, FTextToken token, String line){
      if(RString.contains(line, " ? ")){
         // 分解内容
         String[] items = RString.splitTwo(line, " ? ");
         RString.trim(items);
         String[] subItems = RString.splitTwo(items[1], " : ");
         RString.trim(subItems);
         // 设置内容
         _operator = "?";
         _value1 = items[0].trim();
         _value2 = subItems[0].trim();
         _value3 = subItems[1].trim();
      }
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   @Override
   public void convertSourceBegin(FAsConvertContent content, FTextSource source){
      source.append(_value1);
      source.append(" ? ");
      source.append(content.convertSource(_value2));
      source.append(" : ");
      source.append(content.convertSource(_value3));
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

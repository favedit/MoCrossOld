package org.mo.script.java.parser.source;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.java.converter.FJavaConvertContent;
import org.mo.script.java.parser.FJavaParserContent;

//============================================================
// <T>关键词RETURN代码。</T>
//============================================================
public class FJavaReturnSource
      extends FJavaSource
{
   protected String _value;

   //============================================================
   // <T>构造代码。</T>
   //============================================================
   public FJavaReturnSource(){
   }

   //============================================================
   // <T>解析关键字。</T>
   //
   // @param content 解析环境
   // @param token 文本块
   // @param line 代码行
   //============================================================
   public void parseKeyword(FJavaParserContent content, FTextToken token, String line){
      // 去掉关键字
      if(RString.startsWith(line, "return ")){
         line = line.substring("return ".length());
      }
      if(RString.endsWith(line, ";")){
         line = line.substring(0, line.length() - 1);
      }
      _value = line;
      // 去掉关键字
      if(RString.contains(line, "(")){
         String data = RString.midMatch(line, '(', ')');
         if(!RString.isEmpty(data)){
            parseLine(content, token, data);
         }
      }else{
         parseLine(content, token, line);
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
      source.appendIndent();
      source.append("return ");
      if(!hasSource()){
         source.append(_value);
         if(RString.contains(_value, '.')){
            if(!RString.contains(_value, '(')){
               source.append("()");
            }
         }
         source.append(";");
      }else{
         source.append('(');
      }
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   @Override
   public void convertSourceEnd(FJavaConvertContent content, FTextSource source){
      if(hasSource()){
         source.append(')');
         source.append(";");
      }
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

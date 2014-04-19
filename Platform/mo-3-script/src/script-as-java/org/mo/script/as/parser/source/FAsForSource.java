package org.mo.script.as.parser.source;

import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.as.converter.FAsConvertContent;
import org.mo.script.as.parser.FAsParserContent;

//============================================================
// <T>关键词IF代码。</T>
//============================================================
public class FAsForSource
      extends FAsSource
{
   protected String _value;

   protected String _source1;

   protected String _source1Name;

   protected String _source1Type;

   protected String _source1Value;

   protected String _source2;

   protected String _source3;

   //============================================================
   // <T>构造代码。</T>
   //============================================================
   public FAsForSource(){
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
         // 数据分段
         String[] items = RStrings.splitTrimNotEmpty(data, ';');
         if(3 != items.length){
            _value = data;
            //throw new FFatalError("Parse for source failure.");
         }else{
            _source1 = items[0];
            _source2 = items[1];
            _source3 = items[2];
            // 解析定义
            if(RString.contains(_source1, "var ")){
               String source = _source1;
               if(RString.contains(_source1, "=")){
                  source = RString.left(_source1, "=");
                  _source1Value = RString.right(_source1, "=");
               }
               if(source.startsWith("var ")){
                  source = source.substring("var ".length()).trim();
               }
               items = RString.split(source, ':');
               _source1Name = items[0];
               _source1Type = items[1];
               _source1 = null;
            }else{
               _source1 = data;
            }
         }
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
      source.appendIndent();
      source.append("for(");
      if(!RString.isEmpty(_source1)){
         source.append(_source1);
      }else{
         source.append(_source1Type);
         source.append(' ');
         source.append(_source1Name);
         if(!RString.isEmpty(_source1Value)){
            source.append(" = ");
            source.append(_source1Value);
         }
      }
      source.append("; ");
      source.append(_source2);
      source.append("; ");
      source.append(_source3);
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

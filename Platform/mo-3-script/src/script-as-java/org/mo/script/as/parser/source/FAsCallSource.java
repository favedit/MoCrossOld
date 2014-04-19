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
public class FAsCallSource
      extends FAsSource
{
   protected String _value;

   protected String _source;

   protected String _source1;

   protected String _source1TypeName;

   protected String _source1FullTypeName;

   protected String _source2;

   protected String _params;

   //============================================================
   // <T>构造代码。</T>
   //============================================================
   public FAsCallSource(){
   }

   //============================================================
   // <T>解析关键字。</T>
   //
   // @param content 解析环境
   // @param token 文本块
   // @param line 代码行
   //============================================================
   public void parseKeyword(FAsParserContent content, FTextToken token, String line){
      // 去除尾部
      if(RString.endsWith(line, ";")){
         line = line.substring(0, line.length() - 1);
      }
      // 获得调用部分
      if(RString.contains(line, "(")){
         String source = RString.left(line, "(");
         if(RString.contains(source, '.')){
            String[] items = RString.splitTwo(source, '.');
            _source1 = items[0];
            _source1TypeName = _ownerFunction.findVariableClassName(_source1);
            //_source1FullTypeName = _ownerClass.makeTypeName(content, _source1TypeName);
            _source2 = items[1];
         }else{
            _value = line;
         }
         _params = RString.midMatch(line, '(', ')');
      }else{
         _value = line;
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
      source.appendIndent();
      if(RString.isEmpty(_value)){
         source.append(_source1);
         source.append('.');
         source.append(_source2);
         if(null != _params){
            source.append('(');
            source.append(_params);
            source.append(')');
         }
      }else{
         source.append(_value);
      }
      source.append(';');
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

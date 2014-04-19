package org.mo.script.as.parser.source;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.as.converter.FAsConvertContent;
import org.mo.script.as.parser.FAsParserContent;
import org.mo.script.as.parser.FAsVariable;

//============================================================
// <T>关键词IF代码。</T>
//============================================================
public class FAsDeclareSource
      extends FAsSource
{
   protected String _name;

   protected String _typeName;

   protected String _typeFullName;

   protected String _value;

   //============================================================
   // <T>构造代码。</T>
   //============================================================
   public FAsDeclareSource(){
   }

   //============================================================
   // <T>解析关键字。</T>
   //
   // @param content 解析环境
   // @param token 文本块
   // @param line 代码行
   //============================================================
   public void parseKeyword(FAsParserContent content, FTextToken token, String line){
      if(RString.endsWith(line, ";")){
         line = line.substring(0, line.length() - 1);
      }
      if(RString.contains(line, " = ")){
         String[] items = RString.splitTwo(line, '=');
         String nameValue = RString.right(items[0], "var ").trim();
         String[] names = RString.splitTwo(nameValue, ':');
         _name = names[0].trim();
         _typeName = names[1].trim();
         _value = items[1].trim();
      }else{
         String nameValue = RString.right(line, "var ").trim();
         String[] names = RString.splitTwo(nameValue, ':');
         _name = names[0].trim();
         _typeName = names[1].trim();
      }
      _typeFullName = _ownerClass.makeTypeName(content, _typeName);
      // 登录一个变量声明
      FAsVariable variable = new FAsVariable();
      variable.setName(_name);
      variable.setTypeName(_typeName);
      _ownerFunction.variables().push(variable);
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
      source.append(_typeFullName);
      source.append(" ");
      source.append(_name);
      if(!RString.isEmpty(_value)){
         source.append(" = ");
         source.append(_value);
         if(RString.contains(_value, '.')){
            //if(!RString.contains(_value, '(')){
            //source.append("()");
            //}
         }
      }
      source.append(";");
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

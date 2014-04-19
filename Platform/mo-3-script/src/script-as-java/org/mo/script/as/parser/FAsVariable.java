package org.mo.script.as.parser;

import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.as.converter.FAsConvertContent;

//============================================================
// <T>字段对象。</T>
//============================================================
public class FAsVariable
      extends FAsObject
{
   // 类对象
   protected FAsClass _class;

   // 类型名称
   protected String _typeName;

   // 类型名称
   protected String _typeFullName;

   //============================================================
   // <T>构造字段对象。</T>
   //============================================================
   public FAsVariable(){
   }

   //============================================================
   // <T>获得类对象。</T>
   //
   // @return 类对象
   //============================================================
   public FAsClass clazz(){
      return _class;
   }

   //============================================================
   // <T>设置类对象。</T>
   //
   // @param clazz 类对象
   //============================================================
   public void setClazz(FAsClass clazz){
      _class = clazz;
   }

   //============================================================
   // <T>获得类型名称。</T>
   //
   // @return 类型名称
   //============================================================
   public String typeName(){
      return _typeName;
   }

   //============================================================
   // <T>设置类型名称。</T>
   //
   // @param typeName 类型名称
   //============================================================
   public void setTypeName(String typeName){
      _typeName = typeName;
   }

   //============================================================
   // <T>解析文件。</T>
   //============================================================
   public void parse(FAsParserContent content, FTextToken token, String line){
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convert(FAsConvertContent content, FTextSource source){
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
      info.append("AsVariable[");
      info.append(_name);
      info.append(':');
      info.append(_typeName);
      info.append("]");
      return info;
   }
}

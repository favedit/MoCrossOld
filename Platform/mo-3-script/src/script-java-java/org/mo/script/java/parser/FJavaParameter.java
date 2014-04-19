package org.mo.script.java.parser;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.text.parser.FTextSource;
import org.mo.script.java.converter.FJavaConvertContent;

//============================================================
// <T>参数对象。</T>
//============================================================
public class FJavaParameter
      extends FJavaObject
{
   protected FJavaFunction _function;

   // 类型名称
   protected String _typeName;

   // 类型名称
   protected String _typeFullName;

   // 默认内容
   protected String _defaultValue;

   //============================================================
   // <T>构造参数对象。</T>
   //============================================================
   public FJavaParameter(){
   }

   //============================================================
   // <T>获得函数。</T>
   //
   // @return 函数
   //============================================================
   public FJavaFunction function(){
      return _function;
   }

   //============================================================
   // <T>设置函数。</T>
   //
   // @param function 函数
   //============================================================
   public void setFunction(FJavaFunction function){
      _function = function;
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
   // <T>获得默认内容。</T>
   //
   // @return 默认内容
   //============================================================
   public String defaultValue(){
      return _defaultValue;
   }

   //============================================================
   // <T>构造参数对象。</T>
   //============================================================
   public void parse(FJavaParserContent content, String line){
      int index = line.indexOf("=");
      if(-1 != index){
         _defaultValue = RString.right(line, "=").trim();
         line = line.substring(0, index);
      }
      if(RString.startsWith(line, "...")){
         _name = RString.right(line, "...").trim();
         _typeName = "...";
      }else{
         String[] items = RString.splitTwo(line, ':');
         _name = items[0].trim();
         _typeName = items[1].trim();
      }
      FJavaClass clazz = _function.clazz();
      _typeFullName = clazz.makeTypeName(content, _typeName);
      if(RString.isEmpty(_typeFullName)){
         throw new FFatalError("Full type name is empty.");
      }
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convert(FJavaConvertContent content, FTextSource source){
      source.append(_typeFullName);
      source.append(" ");
      source.append(_name);
   }
}

package org.mo.script.java.parser;

import org.mo.com.lang.RString;
import org.mo.com.text.parser.FTextToken;

//============================================================
// <T>实现对象。</T>
//============================================================
public class FJavaImplement
      extends FJavaObject
{
   // 类型
   protected FJavaClass _javaClass;

   //============================================================
   // <T>获得短名称。</T>
   //
   // @return 短名称
   //============================================================
   public String shortName(){
      return RString.right(_name, ".");
   }

   //============================================================
   // <T>获得类对象。</T>
   //
   // @return 类对象
   //============================================================
   public FJavaClass javaClass(){
      return _javaClass;
   }

   //============================================================
   // <T>设置类对象。</T>
   //
   // @param javaClass 类对象
   //============================================================
   public void setJavaClass(FJavaClass javaClass){
      _javaClass = javaClass;
   }

   //============================================================
   // <T>解析文本段内容。</T>
   //
   // @param content 解析环境
   // @param token 文本段
   // @param line 文本行
   //============================================================
   public void parse(FJavaParserContent content, FTextToken token, String line){
      _name = line;
   }
}

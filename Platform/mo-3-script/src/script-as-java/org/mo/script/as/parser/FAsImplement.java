package org.mo.script.as.parser;

import org.mo.com.lang.RString;
import org.mo.com.text.parser.FTextToken;

//============================================================
// <T>实现对象。</T>
//============================================================
public class FAsImplement
      extends FAsObject
{
   // 类型
   protected FAsClass _asClass;

   //============================================================
   // <T>获得短名称。</T>
   //
   // @return 短名称
   //============================================================
   @Override
   public String shortName(){
      return RString.right(_name, ".");
   }

   //============================================================
   // <T>获得类对象。</T>
   //
   // @return 类对象
   //============================================================
   public FAsClass asClass(){
      return _asClass;
   }

   //============================================================
   // <T>设置类对象。</T>
   //
   // @param asClass 类对象
   //============================================================
   public void setAsClass(FAsClass asClass){
      _asClass = asClass;
   }

   //============================================================
   // <T>解析文本段内容。</T>
   //
   // @param content 解析环境
   // @param token 文本段
   // @param line 文本行
   //============================================================
   public void parse(FAsParserContent content, FTextToken token, String line){
      _name = line;
   }
}

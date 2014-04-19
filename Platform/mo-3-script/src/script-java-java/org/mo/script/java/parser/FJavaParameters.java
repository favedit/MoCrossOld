package org.mo.script.java.parser;

import org.mo.com.lang.generic.MObjects;
import org.mo.com.text.parser.FTextSource;
import org.mo.script.java.converter.FJavaConvertContent;

//============================================================
// <T>参数对象集合。</T>
//============================================================
public class FJavaParameters
      extends MObjects<FJavaParameter>
{
   //============================================================
   // <T>构造参数对象集合。</T>
   //============================================================
   public FJavaParameters(){
      super(FJavaParameter.class);
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convert(FJavaConvertContent content, FTextSource source){
      for(int n = 0; n < _count; n++){
         // 增加
         if(n > 0){
            source.append(", ");
         }
         // 建立参数信息
         FJavaParameter parameter = _items[n];
         parameter.convert(content, source);
      }
   }
}

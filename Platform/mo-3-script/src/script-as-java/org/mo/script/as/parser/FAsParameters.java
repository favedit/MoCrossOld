package org.mo.script.as.parser;

import org.mo.com.lang.generic.MObjects;
import org.mo.com.text.parser.FTextSource;
import org.mo.script.as.converter.FAsConvertContent;

//============================================================
// <T>参数对象集合。</T>
//============================================================
public class FAsParameters
      extends MObjects<FAsParameter>
{
   //============================================================
   // <T>构造参数对象集合。</T>
   //============================================================
   public FAsParameters(){
      super(FAsParameter.class);
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convert(FAsConvertContent content, FTextSource source){
      for(int n = 0; n < _count; n++){
         // 增加
         if(n > 0){
            source.append(", ");
         }
         // 建立参数信息
         FAsParameter parameter = _items[n];
         parameter.convert(content, source);
      }
   }

   //============================================================
   // <T>根据变量名称查找变量对象。</T>
   //
   // @param name 变量名称
   // @return 变量对象
   //============================================================
   public FAsParameter find(String name){
      int count = _count;
      for(int n = 0; n < count; n++){
         FAsParameter parameter = _items[n];
         if(parameter.name().equals(name)){
            return parameter;
         }
      }
      return null;
   }
}

package org.mo.script.as.converter;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;

//============================================================
// <T>AS转换环境。</T>
//============================================================
public class FAsConvertContent
      extends FObject
{
   protected FAsClassConverts _classeConverts = new FAsClassConverts();

   //============================================================
   // <T>构造AS转换环境。</T>
   //============================================================
   public FAsConvertContent(){
   }

   //============================================================
   // <T>获得转换集合。</T>
   //
   // @return 转换集合
   //============================================================
   public FAsClassConverts classeConverts(){
      return _classeConverts;
   }

   //============================================================
   // <T>转换类名称。</T>
   //
   // @param typeName 类名称
   // @return 类名称
   //============================================================
   public String convertTypeName(String typeName){
      FAsClassConvert convert = _classeConverts.find(typeName);
      if(null != convert){
         return convert.javaClassName();
      }
      return typeName;
   }

   //============================================================
   // <T>转换类名称。</T>
   //
   // @param typeName 类名称
   // @return 类名称
   //============================================================
   public String convertSource(String source){
      if(RString.contains(source, "[")){
         String name = RString.left(source, "[");
         String index = RString.mid(source, "[", "]");
         return name + ".get(" + index + ")";
      }
      if(RString.contains(source, '.')){
         //if(!RString.contains(source, '(')){
         //   return source + "()";
         //}
      }
      return source;
   }
}

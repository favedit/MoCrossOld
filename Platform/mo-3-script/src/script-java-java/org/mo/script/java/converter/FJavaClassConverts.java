package org.mo.script.java.converter;

import org.mo.com.lang.INamePair;
import org.mo.com.lang.generic.MDictionary;

//============================================================
// <T>Java类转换器集合。</T>
//============================================================
public class FJavaClassConverts
      extends MDictionary<INamePair<FJavaClassConvert>, FJavaClassConvert>
{
   //============================================================
   // <T>构造Java类转换器集合。</T>
   //============================================================
   public FJavaClassConverts(){
      super(FJavaClassConvert.class);
   }
}

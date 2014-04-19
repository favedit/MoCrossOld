package org.mo.script.java.parser;

import org.mo.com.lang.INamePair;
import org.mo.com.lang.generic.MDictionary;

//============================================================
// <T>类对象集合。</T>
//============================================================
public class FJavaClasses
      extends MDictionary<INamePair<FJavaClass>, FJavaClass>
{
   //============================================================
   // <T>构造类对象集合。</T>
   //============================================================
   public FJavaClasses(){
      super(FJavaClass.class);
   }
}

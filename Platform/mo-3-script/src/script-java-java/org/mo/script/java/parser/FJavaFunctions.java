package org.mo.script.java.parser;

import org.mo.com.lang.INamePair;
import org.mo.com.lang.generic.MDictionary;

//============================================================
// <T>函数对象集合。</T>
//============================================================
public class FJavaFunctions
      extends MDictionary<INamePair<FJavaFunction>, FJavaFunction>
{
   //============================================================
   // <T>函数对象集合。</T>
   //============================================================
   public FJavaFunctions(){
      super(FJavaFunction.class);
   }
}

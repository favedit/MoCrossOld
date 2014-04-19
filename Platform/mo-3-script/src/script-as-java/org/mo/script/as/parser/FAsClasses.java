package org.mo.script.as.parser;

import org.mo.com.lang.INamePair;
import org.mo.com.lang.generic.MDictionary;

//============================================================
// <T>类对象集合。</T>
//============================================================
public class FAsClasses
      extends MDictionary<INamePair<FAsClass>, FAsClass>
{
   //============================================================
   // <T>构造类对象集合。</T>
   //============================================================
   public FAsClasses(){
      super(FAsClass.class);
   }
}

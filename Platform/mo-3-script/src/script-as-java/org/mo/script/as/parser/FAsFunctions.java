package org.mo.script.as.parser;

import org.mo.com.lang.INamePair;
import org.mo.com.lang.generic.MDictionary;

//============================================================
// <T>函数对象集合。</T>
//============================================================
public class FAsFunctions
      extends MDictionary<INamePair<FAsFunction>, FAsFunction>
{
   //============================================================
   // <T>函数对象集合。</T>
   //============================================================
   public FAsFunctions(){
      super(FAsFunction.class);
   }
}

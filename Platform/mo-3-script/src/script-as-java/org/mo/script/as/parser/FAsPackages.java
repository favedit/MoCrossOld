package org.mo.script.as.parser;

import org.mo.com.lang.INamePair;
import org.mo.com.lang.generic.MDictionary;

//============================================================
// <T>包对象集合。</T>
//============================================================
public class FAsPackages
      extends MDictionary<INamePair<FAsPackage>, FAsPackage>
{
   //============================================================
   // <T>构造包对象集合。</T>
   //============================================================
   public FAsPackages(){
      super(FAsPackage.class);
   }
}

package org.mo.script.java.parser;

import org.mo.com.lang.INamePair;
import org.mo.com.lang.generic.MDictionary;

//============================================================
// <T>包对象集合。</T>
//============================================================
public class FJavaPackages
      extends MDictionary<INamePair<FJavaPackage>, FJavaPackage>
{
   //============================================================
   // <T>构造包对象集合。</T>
   //============================================================
   public FJavaPackages(){
      super(FJavaPackage.class);
   }
}

package org.mo.script.java.parser;

//============================================================
// <T>处理环境。</T>
//============================================================
public class FJavaProcessContent
      extends FJavaContent
{

   //============================================================
   // <T>构造处理环境。</T>
   //============================================================
   public FJavaProcessContent(){
   }

   //============================================================
   // <T>获得包集合。</T>
   //
   // @param packageName 包名称
   // @return 包集合
   //============================================================
   @Override
   public FJavaPackage syncPackage(String packageName){
      FJavaPackage asPackage = _packages.find(packageName);
      if(null == asPackage){
         asPackage = new FJavaPackage();
         asPackage.setName(packageName);
         _packages.set(packageName, asPackage);
      }
      return asPackage;
   }
}

package org.mo.script.java.parser;

//============================================================
// <T>解析环境。</T>
//============================================================
public class FJavaParserContent
      extends FJavaContent
{
   //============================================================
   // <T>构造解析环境。</T>
   //============================================================
   public FJavaParserContent(){
   }

   //============================================================
   // <T>增加类对象。</T>
   //
   // @param javaClass 类对象
   //============================================================
   public void pushClass(FJavaClass javaClass){
      _classes.set(javaClass.fullName(), javaClass);
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

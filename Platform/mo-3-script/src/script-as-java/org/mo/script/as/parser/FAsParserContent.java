package org.mo.script.as.parser;

//============================================================
// <T>解析环境。</T>
//============================================================
public class FAsParserContent
      extends FAsContent
{
   //============================================================
   // <T>构造解析环境。</T>
   //============================================================
   public FAsParserContent(){
   }

   //============================================================
   // <T>增加类对象。</T>
   //
   // @param javaClass 类对象
   //============================================================
   public void pushClass(FAsClass asClass){
      _classes.set(asClass.fullName(), asClass);
   }

   //============================================================
   // <T>获得包集合。</T>
   //
   // @param packageName 包名称
   // @return 包集合
   //============================================================
   @Override
   public FAsPackage syncPackage(String packageName){
      FAsPackage asPackage = _packages.find(packageName);
      if(null == asPackage){
         asPackage = new FAsPackage();
         asPackage.setName(packageName);
         _packages.set(packageName, asPackage);
      }
      return asPackage;
   }
}

package org.mo.script.as.parser;

//============================================================
// <T>处理环境。</T>
//============================================================
public class FAsProcessContent
      extends FAsContent
{
   //============================================================
   // <T>构造处理环境。</T>
   //============================================================
   public FAsProcessContent(){
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

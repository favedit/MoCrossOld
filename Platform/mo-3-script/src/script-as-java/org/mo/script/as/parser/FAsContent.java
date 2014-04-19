package org.mo.script.as.parser;

import org.mo.com.lang.FObject;

//============================================================
// <T>处理环境。</T>
//============================================================
public class FAsContent
      extends FObject
{
   // 引用集合
   protected FAsImports _imports = new FAsImports();

   // 包集合
   protected FAsPackages _packages = new FAsPackages();

   // 类集合
   protected FAsClasses _classes = new FAsClasses();

   //============================================================
   // <T>构造处理环境。</T>
   //============================================================
   public FAsContent(){
   }

   //============================================================
   // <T>获得引用集合。</T>
   //
   // @return 引用集合
   //============================================================
   public FAsImports imports(){
      return _imports;
   }

   //============================================================
   // <T>获得包集合。</T>
   //
   // @return 包集合
   //============================================================
   public FAsPackages packages(){
      return _packages;
   }

   //============================================================
   // <T>获得类集合。</T>
   //
   // @return 类集合
   //============================================================
   public FAsClasses classes(){
      return _classes;
   }

   //============================================================
   // <T>获得包集合。</T>
   //
   // @param packageName 包名称
   // @return 包集合
   //============================================================
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

package org.mo.script.java.parser;

import org.mo.com.lang.FObject;

//============================================================
// <T>处理环境。</T>
//============================================================
public class FJavaContent
      extends FObject
{
   // 引用集合
   protected FJavaImports _imports = new FJavaImports();

   // 包集合
   protected FJavaPackages _packages = new FJavaPackages();

   // 类集合
   protected FJavaClasses _classes = new FJavaClasses();

   //============================================================
   // <T>构造处理环境。</T>
   //============================================================
   public FJavaContent(){
   }

   //============================================================
   // <T>获得引用集合。</T>
   //
   // @return 引用集合
   //============================================================
   public FJavaImports imports(){
      return _imports;
   }

   //============================================================
   // <T>获得包集合。</T>
   //
   // @return 包集合
   //============================================================
   public FJavaPackages packages(){
      return _packages;
   }

   //============================================================
   // <T>获得类集合。</T>
   //
   // @return 类集合
   //============================================================
   public FJavaClasses classes(){
      return _classes;
   }

   //============================================================
   // <T>获得包集合。</T>
   //
   // @param packageName 包名称
   // @return 包集合
   //============================================================
   public FJavaPackage syncPackage(String packageName){
      FJavaPackage javaPackage = _packages.find(packageName);
      if(null == javaPackage){
         javaPackage = new FJavaPackage();
         javaPackage.setName(packageName);
         _packages.set(packageName, javaPackage);
      }
      return javaPackage;
   }
}

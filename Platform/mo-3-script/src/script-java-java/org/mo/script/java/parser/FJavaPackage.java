package org.mo.script.java.parser;

//============================================================
// <T>包对象。</T>
//============================================================
public class FJavaPackage
      extends FJavaObject
{
   // 类集合
   protected FJavaClasses _classes = new FJavaClasses();

   //============================================================
   // <T>构造包对象。</T>
   //============================================================
   public FJavaPackage(){
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
   // <T>增加类对象。</T>
   //
   // @param asClass 类对象
   //============================================================
   public void push(FJavaClass javaClass){
      _classes.set(javaClass.name(), javaClass);
   }
}

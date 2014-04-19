package org.mo.com.system;

//============================================================
// <T>应用程序工具类。</T>
//============================================================
public class RApplication
{
   // 获得参数集合
   protected static FApplicationArgements _argements = new FApplicationArgements();

   //============================================================
   // <T>关联应用参数。</T>
   //
   // @param args 参数集合
   //============================================================
   public static void linkArgements(String[] args){
      for(String arg : args){
         _argements.push(arg);
      }
   }

   //============================================================
   // <T>获得参数集合。</T>
   //
   // @return 参数集合
   //============================================================
   public static FApplicationArgements argements(){
      return _argements;
   }

   //============================================================
   // <T>根据名称查找参数。</T>
   //
   // @param name 名称
   // @return 参数
   //============================================================
   public static String findArgement(String name){
      return _argements.find(name);
   }

   //============================================================
   // <T>根据名称查找参数。</T>
   //
   // @param name 名称
   // @param defaultValue 默认内容
   // @return 参数
   //============================================================
   public static String findArgement(String name,
                                     String defaultValue){
      return _argements.find(name, defaultValue);
   }
}

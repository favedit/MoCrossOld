package org.mo.com.xml.persistent;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>配置类字典。</T>
//============================================================
public class FXmlClasses
      extends FDictionary<FXmlClass>
{
   //============================================================
   // <T>构造配置类字典。</T>
   //============================================================
   public FXmlClasses(){
      super(FXmlClass.class);
   }

   //============================================================
   // <T>根据类同步一个配置类对象。</T>
   //
   // @param clazz 类对象
   // @return 配置类对象
   //============================================================
   public FXmlClass sync(Class<?> clazz){
      String name = clazz.getName();
      FXmlClass xclass = find(name);
      if(xclass == null){
         xclass = new FXmlClass(clazz);
         set(name, xclass);
      }
      return xclass;
   }
}

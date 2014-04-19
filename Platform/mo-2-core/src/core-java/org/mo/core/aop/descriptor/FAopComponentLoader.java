package org.mo.core.aop.descriptor;

//============================================================
// <T>AOP组件加载器。</T>
//============================================================
public class FAopComponentLoader
      extends ClassLoader
{
   // 类对象
   protected Class<?> _class;

   //============================================================
   // <T>构造AOP组件加载器。</T>
   //
   // @param parent 父对象
   //============================================================
   public FAopComponentLoader(Object parent){
      super(parent.getClass().getClassLoader());
   }

   //============================================================
   // <T>构造类对象。</T>
   //
   // @param name 名称
   // @param data 数据
   // @return 类对象
   //============================================================
   public Class<?> buildClass(String name,
                              byte[] data){
      if(null == _class){
         _class = defineClass(name, data, 0, data.length);
      }
      return _class;
   }
}

package org.mo.com.resource;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.reflect.RClass;

//============================================================
// <T>资源管理器。</T>
//============================================================
public class RResource
{
   // 资源字典
   private static FResourceDictionary _resources = new FResourceDictionary();

   //============================================================
   // <T>获得指定名称的资源对象。</T>
   //
   // @param name 名称
   // @return 资源对象
   //============================================================
   public static IResource find(String name){
      FResource resource = _resources.find(name);
      if(null == resource){
         Class<?> clazz = RClass.findClass(name);
         if(null == clazz){
            throw new FFatalError("No found resource class {0}", name);
         }
         return find(clazz);
      }
      return resource;
   }

   //============================================================
   // <T>获得类关联的资源对象。</T>
   //
   // @param clazz 类对象
   // @return 资源对象
   //============================================================
   public static IResource find(Class<?> clazz){
      String name = clazz.getName();
      FResource resource = _resources.find(name);
      if(null == resource){
         try{
            resource = new FResource();
            resource.construct(clazz);
            _resources.set(name, resource);
         }catch(Exception e){
            throw new FFatalError(e, "Find resource for class failure. (class={0})", name);
         }
      }
      return resource;
   }
}

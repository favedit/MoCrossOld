using System;
using System.Collections.Generic;

namespace MO.Common.Lang
{
   //============================================================
   // <T>资源工具类。</T>
   //============================================================
   public class RResource
   {
      // 日志对象
      private static ILogger _logger = RLogger.Find(typeof(RResource));

      // 资源管理对象
      protected static IResourceConsole _console;

      // 资源对象字典集合
      protected static Dictionary<string, IResource> _resource = new Dictionary<string, IResource>();

      //============================================================
      // <T>查找资源对象。</T>
      //
      // @param type 类型
      // @return 资源对象
      //============================================================
      public static IResource Find(Type type) {
         string name = type.FullName;
         IResource face = _resource[name];
         if (null == face) {
            IResource resource = _console.CreateResource(type);
            _resource[name] = resource;
            return resource;
         }
         return face;
      }
   }
}

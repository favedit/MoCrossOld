using System;
using MO.Common.Lang;

namespace MO.Common.System
{
   public class FResourceConsole : IResourceConsole
   {
      // 日志对象
      private static ILogger _logger = RLogger.Find(typeof(RResource));

      //============================================================
      // <T>创建资源对象。</T>
      //
      // @param type 类型
      // @return 资源对象
      //============================================================
      public IResource CreateResource(Type type) {
         FResource resource = new FResource();
         try {
            resource.LoadResource(type);
         } catch (Exception e) {
            _logger.Debug(null, "Find", "Can't load resource. {0}", e.Message);
         }
         return resource;
      }
   }
}

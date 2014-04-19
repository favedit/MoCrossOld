using System;
using System.IO;
using System.Reflection;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Common.Lang.Reflection
{
   //============================================================
   // <T>组件工具类。</T>
   //============================================================
   public class RAssembly
   {
      // 日志对象
      private static ILogger _logger = RLogger.Find(typeof(RAssembly));

      //============================================================
      // <T>加载组件资源。</T>
      //
      // @param assembly 组件对象
      // @param name 名称
      // @return 数据流
      //============================================================
      public static Stream LoadResource(Assembly assembly, string name) {
         string path = name.Replace('\\', '.').Replace('/', '.');
         path = assembly.GetName().Name + '.' + path;
         Stream stream = assembly.GetManifestResourceStream(path);
         if(null == stream) {
            throw new FFatalException("Can't load resource from {0}[{1}]", name, path);
         }
         if(_logger.DebugAble) {
            _logger.Debug(null, "LoadResource", "Load resource [{0}]", name);
         }
         return stream;
      }

      //============================================================
      // <T>加载类型资源。</T>
      //
      // @param type 类型对象
      // @return 数据流
      //============================================================
      public static Stream LoadTypeResource(Type type) {
         string resource = type.FullName + RXml.EXTENSION;
         Stream stream = type.Assembly.GetManifestResourceStream(resource);
         if(null == stream) {
            throw new FFatalException("Can't load resource from {0}[{1}]", type.Name, type.FullName);
         }
         if(_logger.DebugAble) {
            _logger.Debug(null, "LoadTypeResource", "Load type resource [{0}]", type.FullName);
         }
         return stream;
      }
   }
}

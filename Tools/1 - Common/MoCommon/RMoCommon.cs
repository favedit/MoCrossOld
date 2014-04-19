using MO.Common.Lang;
using MO.Common.System;
using MO.Common.Collection;
using MO.Common.Content;

namespace MO.Common
{
   //============================================================
   // <T>共同库管理类。</T>
   //============================================================
   public class RMoCommon
   {
      // 环境内容
      protected static FAttributes _environments = new FAttributes();

      //============================================================
      // <T>初始化处理。</T>
      //============================================================
      public static void Initialize() {
         FLoggerConsole loggerConsole = new FLoggerConsole();
         loggerConsole.Listeners.Push(new FLoggerCommandListener());
         RLogger.LoggerConsole = loggerConsole;
      }

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public static void Release() {
      }

      //============================================================
      // <T>根据名称查找环境内容。</T>
      //
      // @param name 名称
      // @return 内容
      //============================================================
      public static string FindEnvironment(string name) {
         return _environments.Find(name);
      }

      //============================================================
      // <T>根据名称获得环境内容。</T>
      //
      // @param name 名称
      // @return 内容
      //============================================================
      public static string GetEnvironment(string name) {
         return _environments.Get(name);
      }

      //============================================================
      // <T>解析环境字符串。</T>
      //
      // @param value 字符串
      // @return 字符串
      //============================================================
      public static string ParseEnvironment(string value) {
         if(null != value) {
            foreach(IStringPair pair in _environments) {
               value = value.Replace("${" + pair.Name + "}", pair.Value);
            }
         }
         return value;
      }

      //============================================================
      // <T>加载环境设置。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public static void LoadEnvironment(string fileName) {
         FXmlDocument xdoc = new FXmlDocument(fileName);
         foreach(FXmlNode xnode in xdoc.Root.Nodes){
            if (xnode.IsName("Property")) {
               _environments.Set(xnode.Get("name"), ParseEnvironment(xnode.Text));
            }
         }
      }
   }
}

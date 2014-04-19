using System;
using MO.Common.Lang;

namespace MO.Common.System
{
   //============================================================
   // <T>日志管理器。</T>
   //============================================================
   public class FLoggerConsole : ILoggerConsole
   {
      // 日志级别
      protected ELoggerLevel _level = ELoggerLevel.All;

      // 监听器集合
      protected FListeners _listeners = new FListeners();

      //============================================================
      public FListeners Listeners {
         get { return _listeners; }
      }

      //============================================================
      // <T>创建日志对象。</T>
      //
      // @return 日志对象
      //============================================================
      public ILogger CreateLogger(Type type) {
         return new FLogger(type);
      }

      //============================================================
      // <T>获得或设置当前日志级别。</T>
      //
      // @param value 日志级别
      // @return 日志级别
      //============================================================
      public ELoggerLevel Level {
         get { return _level; }
         set { _level = value; }
      }

      //============================================================
      // <T>输出消息。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      public void Write(ELoggerLevel level, object refer, string method, Exception exception, string message, params object[] parameters) {
         // 监听处理
         foreach (IListener listener in _listeners) {
            listener.Process(this, level, refer, method, exception, message, parameters);
         }
      }


      /*

      protected static FDictionary<bool> _excludes = new FDictionary<bool>();

      //============================================================
      static RLogger() {
         RSystem.SystemEnding += Release;
#if DEBUG
         _level = ELoggerLevel.All;
         _listeners.Push(new FLoggerConsoleListener());
#endif
      }

      //============================================================
      public static FLoggerListeners Listeners {
         get { return _listeners; }
      }

      //============================================================
      public static void AddExclude(string value) {
         _excludes[value] = true;
      }

      //============================================================
      public static void AddExclude(Type type) {
         _excludes[type.FullName] = true;
      }

      //============================================================
      public static void AddExclude(Type type, string method) {
         _excludes[type.FullName + "." + method] = true;
      }

      //============================================================
      internal static bool TestExcludes(Type type, string method) {
         // Test class
         string typeName = type.FullName;
         if(_excludes.Contains(typeName)) {
            return _excludes[typeName];
         }
         // Test class.method
         string methodName = typeName + "." + method;
         if(_excludes.Contains(methodName)) {
            return _excludes[methodName];
         }
         // Test like
         int count = _excludes.Count;
         for(int n = 0; n < count; n++) {
            string likeName = _excludes.Name(n);
            if(likeName.EndsWith("*")) {
               if(typeName.Substring(0, likeName.Length - 1) == likeName) {
                  return _excludes[typeName];
               }
            }
         }
         return false;
      }

      //============================================================
      public static FDictionary<bool> Excludes {
         get { return RLogger._excludes; }
         set { RLogger._excludes = value; }
      }

      //============================================================
      // <T>输出日志信息。</T>
      //============================================================
      public static void Output(FLoggerParameters parameters) {
         object refer = parameters.Reference;
         Type type = (refer is Type) ? (Type)refer : refer.GetType();
         if(TestExcludes(type, parameters.Method)) {
            return;
         }
         // Listener process
         foreach(FLoggerListener listener in _listeners) {
            listener.Process(parameters);
         }
      }

      //============================================================
      public static void Release() {
         _listeners.Dispose();
      }*/
   }
}

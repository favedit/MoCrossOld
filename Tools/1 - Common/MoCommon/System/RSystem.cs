using System;
using System.Diagnostics;
using System.Reflection;
using MO.Common.IO;
using MO.Common.Lang;
using SystemDiagnostics = System.Diagnostics;

namespace MO.Common.System
{
   //============================================================
   // <T>系统工具类。</T>
   //============================================================
   public class RSystem
   {
      // 日志对象
      protected static ILogger _logger = RLogger.Find(typeof(RSystem));

      // 位置
      protected static string _location;

      // 运行器
      protected static bool _inRuning = true;

      // 关闭事件回调
      public static event HSystemEnding EventEnding;

      //============================================================
      // <T>获得系统是否正在运行。</T>
      //
      // @return 是否正在运行
      //============================================================
      public static bool InRuning {
         get { return _inRuning; }
      }

      //============================================================
      // <T>获得指定模块的路径。</T>
      //
      // @param assembly 模块对象
      // @return 路径
      //============================================================
      protected static string Location(Assembly assembly) {
         if(assembly != null) {
            string location = assembly.Location;
            string name = assembly.ManifestModule.Name;
            location = location.Substring(0, location.Length - name.Length);
            if(location.EndsWith(@"\")) {
               location = location.Substring(0, location.Length - 1);
            }
            return location;
         }
         return null;
      }

      //============================================================
      // <T>获得指定类型的路径。</T>
      //
      // @param type 类型对象
      // @return 路径
      //============================================================
      public static string Location(Type type) {
         return Location(Assembly.GetAssembly(type));
      }

      //============================================================
      // <T>获得指定文件名称的路径。</T>
      //
      // @param fileName 文件名称
      // @return 路径
      //============================================================
      public static string Location(string fileName) {
         if((null != fileName) && !fileName.Contains(RFile.DRIVER_SPLITER)) {
            return RFile.MakeFileName(Location(), fileName);
         }
         return fileName;
      }

      //============================================================
      // <T>获得当前模块的路径。</T>
      //
      // @return 路径
      //============================================================
      public static string Location() {
         if(_location == null) {
            _location = Location(Assembly.GetEntryAssembly());
         }
         return _location;
      }

      //============================================================
      public static void Process(HSystemRunner runner) {
         RSpeedTest.TestStart();
         try {
            runner();
         } catch(Exception e) {
            _logger.Fatal(null, "Process", e);
            throw new FFatalException(e);
         } finally {
            RSystem.Release();
            _logger.Debug(null, "Process", "Run all in time {0}", RSpeedTest.TestEnd().Dump());
         }
      }

      //============================================================
      public static void ProcessAll(HSystemRunner runner) {
         RSpeedTest.TestStart();
         try {
            runner();
         } catch(Exception e) {
            _logger.Error(null, "ProcessAll", null, e.Message);
         } finally {
            RSystem.Release();
            _logger.Debug(null, "ProcessAll", "Run all in time {0}", RSpeedTest.TestEnd().Dump());
         }
      }

      //============================================================
      public static void ProcessCommand(string command, string commandParams = "") {
         ProcessStartInfo info = new ProcessStartInfo();
         info.FileName = command;
         info.WorkingDirectory = command.Substring(0, command.LastIndexOf("/"));
         info.Arguments = commandParams;
         info.WindowStyle = ProcessWindowStyle.Hidden;
         Process pro = SystemDiagnostics.Process.Start(info);
         pro.WaitForExit();
         //string outputInfo = pro.StandardOutput.ReadToEnd();
         //string errorInfo = pro.StandardError.ReadToEnd();
         //_logger.Debug(null, "ProcessCommand", "Process command. (output={1}, error={2})", outputInfo, errorInfo);
      }

      //============================================================
      // <T>强制回收垃圾<T>
      //============================================================
      public static void Collect() {
         GC.Collect();
         GC.WaitForPendingFinalizers();
      }

      //============================================================
      // <T>释放系统。</T>
      //============================================================
      public static void Release() {
         _inRuning = false;
         if(null != EventEnding) {
            EventEnding();
         }
      }
   }
}

using MO.Common.Content;
using MO.Core.Forms.Module.Configuration;
using MO.Core.Logic.Task;
using MO.Core.Logic.Track;

namespace MO.Core
{
   //============================================================
   // <T>核心管理器。</T>
   //============================================================
   public class RMoCore
   {
      // 配置管理器
      protected static FConfigurationConsole _configurationConsole = new FConfigurationConsole();

      // 跟踪管理器
      protected static FTrackConsole _trackConsole = new FTrackConsole();

      // 任务管理器
      protected static FTaskConsole _taskConsole = new FTaskConsole();

      //============================================================
      // <T>获得配置管理器。</T>
      //============================================================
      public static FConfigurationConsole ConfigurationConsole {
         get { return _configurationConsole; }
      }

      //============================================================
      // <T>获得跟踪管理器。</T>
      //============================================================
      public static FTrackConsole TrackConsole {
         get { return _trackConsole; }
      }

      //============================================================
      // <T>获得任务管理器。</T>
      //============================================================
      public static FTaskConsole TaskConsole {
         get { return _taskConsole; }
      }

      //============================================================
      // <T>初始化。</T>
      //============================================================
      public static void Initialize() {
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public static void LoadConfig(FXmlNode xconfig) {
         // 读取节点配置
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Console")) {
               string name = xnode.Get("name");
               // 配置跟踪管理器
               if(name == _trackConsole.Name) {
                  _trackConsole.LoadConfig(xnode);
               }
               // 配置任务管理器
               if(name == _taskConsole.Name) {
                  _taskConsole.LoadConfig(xnode);
               }
            }
         }
      }

      //============================================================
      // <T>配置信息。</T>
      //============================================================
      public static void Setup() {
         // 启动任务控制台
         _taskConsole.Start();
      }

      //============================================================
      // <T>释放。</T>
      //============================================================
      public static void Release() {
         // 启动任务控制台
         _taskConsole.Stop();
         _trackConsole.Close();
      }
   }
}

using MO.Common.Content;
using MO.Design2d.Frame;

namespace MO.Design2d
{
   //============================================================
   // <T>资源管理器。</T>
   //============================================================
   public class RDesign2dManager
   {
      // 界面控制台
      protected static FUiFrameConsole _frameConsole = new FUiFrameConsole();

      //============================================================
      // <T>获得界面控制台。</T>
      //============================================================
      public static FUiFrameConsole FrameConsole {
         get { return _frameConsole; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public static void LoadConfig(FXmlNode xconfig) {
         // 读取节点配置
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Console")) {
               string name = xnode.Get("name");
               // 读取界面配置
               if (name == _frameConsole.Name) {
                  _frameConsole.LoadConfig(xnode);
               }
            }
         }
      }

      //============================================================
      // <T>配置处理。</T>
      //============================================================
      public static void Setup() {
      }

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public static void Release() {
      }
   }
}
